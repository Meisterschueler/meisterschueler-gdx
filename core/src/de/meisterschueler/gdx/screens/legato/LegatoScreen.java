package de.meisterschueler.gdx.screens.legato;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.ClusterHandler;
import de.meisterschueler.gdx.Utils;
import de.meisterschueler.gdx.screens.MidiScreen;
import de.meisterschueler.gdx.screens.ScrollEffect.Bubble;

public class LegatoScreen extends MidiScreen {

	private class Background extends Actor {

		private ShapeRenderer shapeRenderer;

		public Background() {
			shapeRenderer = new ShapeRenderer();
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.end();

			List<NoteRectangle> noteOnRectangles = clusterXYHandler.getNoteOnRectangles();
			List<NoteRectangle> noteOffRectangles = clusterXYHandler.getNoteOffRectangles();
			List<ClusterXY> clusters = clusterXYHandler.getMidiPairClusters();

			shapeRenderer.begin(ShapeType.Filled);
			float deltaTime = SPEED * Gdx.graphics.getDeltaTime();
			if (showLegatoStaccatoBars) {
				drawLegatoStaccatoBars(clusters, batch);
			}
			if (showOverlapRegions) {
				drawOverlapRegions(noteOnRectangles, noteOffRectangles, deltaTime);
			}
			drawNotes(clusters, deltaTime);
			shapeRenderer.end();

			batch.begin();
		}

		private void drawLegatoStaccatoBars(List<ClusterXY> clusters, Batch batch) {
			// Draw legato/staccato bars
			Pixmap pixmap = new Pixmap(WIDTH, 1, Pixmap.Format.RGBA8888);
			pixmap.setColor(1, 0, 0, 0.1f);
			for (ClusterXY cluster : clusters) {
				pixmap.drawLine(cluster.fromX, 0, cluster.toX, 0);
			}

			Color c = null;
			for (int i = 0; i < pixmap.getWidth(); i++) {
				c = new Color(pixmap.getPixel(i, 0));
				if (c.a == 0) {
					c = new Color(0, 0.2f, 0, 1);
				} else if (c.a <= 0.1) {
					c = Color.BLACK;
				} else if (c.a <= 0.19) {
					c = new Color(0.2f, 0, 0, 1);
				} else if (c.a <= 0.26) {
					c = new Color(0.4f, 0, 0, 1);
				} else if (c.a <= 0.33) {
					c = new Color(0.6f, 0, 0, 1);
				} else if (c.a <= 0.40) {
					c = new Color(0.8f, 0, 0, 1);
				} else {
					c = new Color(1, 0, 0, 1);
				}

				pixmap.drawPixel(i, 0, Color.rgba8888(c));
			}

			if (startWithBlackRegion) {
				if (clusters.isEmpty()) {
					pixmap.setColor(Color.BLACK);
					pixmap.fill();
				} else if (clusters.get(0).fromX <= 0) {
					startWithBlackRegion = false;
				} else {
					pixmap.setColor(Color.BLACK);
					pixmap.drawLine(0, 0, clusters.get(0).fromX, 0);
				}
			}

			Texture texture = new Texture(pixmap);

			batch.begin();
			batch.draw(texture, 0, 0, WIDTH, HEIGHT);
			batch.end();

			texture.dispose();
			pixmap.dispose();
		}

		private void drawNotes(List<ClusterXY> clusters, float delta) {
			// Calc color
			int velocitySum = 0;
			int count = 0;
			for (ClusterXY cluster : clusters) {
				for (MidiPairXY midiPair : cluster.getMidiPairs()) {
					velocitySum += midiPair.getNoteOn().getVelocity();
					count++;
				}
			}
			int meanVelocity = (count > 0) ? velocitySum / count : 0;

			// draw notes
			float rectHight = HEIGHT / 128f;
			for (ClusterXY cluster : clusters) {
				boolean clusterFinished = true;
				for (MidiPairXY midiPair : cluster.getMidiPairs()) {

					Color color = null;
					color = Utils.getSpectralColor(midiPair.getNoteOn().getVelocity(), meanVelocity - 20, meanVelocity + 20, 5, 1);
					shapeRenderer.setColor(color);

					if (midiPair.getNoteOff() == null) {
						shapeRenderer.rect(midiPair.fromX, midiPair.y, WIDTH - midiPair.fromX, rectHight);
						clusterFinished = false;
					} else {
						shapeRenderer.rect(midiPair.fromX, midiPair.y, midiPair.toX - midiPair.fromX, rectHight);
					}

					midiPair.fromX -= delta;
					midiPair.toX -= delta;
				}

				if (!clusterFinished) {
					cluster.toX = WIDTH;
				}

				cluster.fromX -= delta;
				cluster.toX -= delta;

				if (cluster.toX < 0) {
					clusterXYHandler.removeCluster(cluster);
				}
			}
		}

		private void drawOverlapRegions(List<NoteRectangle> noteOnRectangles, List<NoteRectangle> noteOffRectangles, float delta) {
			shapeRenderer.setColor(Color.WHITE);

			for (NoteRectangle noteRectangle : noteOnRectangles) {
				shapeRenderer.rect(noteRectangle.x, noteRectangle.y, noteRectangle.width, noteRectangle.height);
				noteRectangle.setX((int) (noteRectangle.x - delta));

				if (noteRectangle.x + noteRectangle.width < 0) {
					clusterXYHandler.removeNoteOnRectangle(noteRectangle);
				}
			}

			for (NoteRectangle noteRectangle : noteOffRectangles) {
				shapeRenderer.rect(noteRectangle.x, noteRectangle.y, noteRectangle.width, noteRectangle.height);
				noteRectangle.setX((int) (noteRectangle.x - delta));

				if (noteRectangle.x + noteRectangle.width < 0) {
					clusterXYHandler.removeNoteOffRectangle(noteRectangle);
				}
			}
		}

	}

	private Background background;

	List<Bubble> bubbles = new CopyOnWriteArrayList<Bubble>();
	
	private boolean showLegatoStaccatoBars = true;
	private boolean showOverlapRegions = true;

	private TextButton legatoStaccatoToggleButton;
	private TextButton overlapRegionsButton;

	public LegatoScreen() {
		super();
		
		legatoStaccatoToggleButton = new TextButton("Legato bars", skin);
		legatoStaccatoToggleButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		legatoStaccatoToggleButton.setPosition(WIDTH - BUTTON_WIDTH, HEIGHT - 1 * BUTTON_HEIGHT);
		legatoStaccatoToggleButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showLegatoStaccatoBars = !showLegatoStaccatoBars;
			}
		});

		overlapRegionsButton = new TextButton("Overlap regions", skin);
		overlapRegionsButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		overlapRegionsButton.setPosition(WIDTH - BUTTON_WIDTH, HEIGHT - 2 * BUTTON_HEIGHT);
		overlapRegionsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				showOverlapRegions = !showOverlapRegions;
			}
		});
		
		//uiGroup.addActor(legatoStaccatoToggleButton);
		//uiGroup.addActor(overlapRegionsButton);

		background = new Background();
		gameGroup.addActor(background);
	}

	private static final float SPEED = 100;

	private ClusterXYHandler clusterXYHandler = new ClusterXYHandler();
	private ClusterHandler clusterHandler = new ClusterHandler();

	private boolean startWithBlackRegion = true;

	@Override
	public synchronized void onMidiNoteOn(NoteOn noteOn) {
		int x = WIDTH;
		int y = (int) ((noteOn.getNote() / 128.0) * HEIGHT);
		clusterXYHandler.onMidiNoteOn(noteOn, x, y);

		clusterHandler.onMidiNoteOn(noteOn);
	}

	@Override
	public synchronized void onMidiNoteOff(NoteOff noteOff) {
		int x = WIDTH;
		int y = (int) ((noteOff.getNote() / 128.0) * HEIGHT);
		clusterXYHandler.onMidiNoteOff(noteOff, x, y);

		clusterHandler.onMidiNoteOff(noteOff);
	}
}
