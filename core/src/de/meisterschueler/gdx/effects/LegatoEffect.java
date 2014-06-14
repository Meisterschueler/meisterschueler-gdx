package de.meisterschueler.gdx.effects;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.MidiPairCluster;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.ClusterHandler;
import de.meisterschueler.gdx.ClusterXYHandler;

public class LegatoEffect extends Effect {

	public LegatoEffect(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, BitmapFont font) {
		super(shapeRenderer, spriteBatch, font);
	}

	private static final float SPEED = 100;

	private ClusterXYHandler clusterXYHandler = new ClusterXYHandler();
	private ClusterHandler clusterHandler = new ClusterHandler();

	private long legato;
	private long staccato;

	private boolean startWithBlackRegion = true;

	@Override
	public void onRender() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);

		List<ClusterXY> clusters = clusterXYHandler.getMidiPairClusters();

		//		// Draw legato/staccato bars
		//		shapeRenderer.begin(ShapeType.Filled);
		//		for (ClusterXY cluster : clusters) {
		//			shapeRenderer.setColor(1, 0, 0, 0.1f);
		//			shapeRenderer.rect(cluster.fromX, 0, cluster.toX-cluster.fromX, Gdx.graphics.getHeight());
		//		}
		//		shapeRenderer.end();

		// Draw legato/staccato bars
		Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(1, 0, 0, 0.1f);
		for (ClusterXY cluster : clusters) {
			pixmap.drawLine(cluster.fromX, 0, cluster.toX, 0);
		}

		Color c = null;
		for (int i=0; i<pixmap.getWidth(); i++) {
			c = new Color(pixmap.getPixel(i, 0));
			if (c.a == 0) {
				c = new Color(0, 0.2f, 0, 1);
			} else if (c.a <= 0.1) {
				c = Color.BLACK;
			} else if (c.a <= 0.2) {
				c = new Color(0.2f, 0, 0, 1);
			} else if (c.a <= 0.3) {
				c = new Color(0.4f, 0, 0, 1);
			} else if (c.a <= 0.4){
				c = new Color(0.6f, 0, 0, 1);
			} else if (c.a <= 0.5){
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

		spriteBatch.begin();
		spriteBatch.draw(new Texture(pixmap), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		spriteBatch.end();

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
		float delta = SPEED*Gdx.graphics.getDeltaTime();
		float rectHight = Gdx.graphics.getHeight()/128f;
		for (ClusterXY cluster : clusters) {
			boolean clusterFinished = true;
			for (MidiPairXY midiPair : cluster.getMidiPairs()) {
				shapeRenderer.begin(ShapeType.Filled);

				Color color = getSpectralColor(midiPair.getNoteOn().getVelocity(), meanVelocity-20, meanVelocity+20, 5, 1);			
				shapeRenderer.setColor(color);

				if (midiPair.getNoteOff() == null) {
					shapeRenderer.rect(midiPair.fromX, midiPair.y, Gdx.graphics.getWidth()-midiPair.fromX, rectHight);
					clusterFinished = false;
				} else {
					shapeRenderer.rect(midiPair.fromX, midiPair.y, midiPair.toX-midiPair.fromX, rectHight);
				}

				shapeRenderer.end();

				midiPair.fromX -= delta;
				midiPair.toX -= delta;
			}

			if (!clusterFinished) {
				cluster.toX = Gdx.graphics.getWidth();
			}

			cluster.fromX -= delta;
			cluster.toX -= delta;

			if (cluster.toX < 0) {
				clusterXYHandler.removeCluster(cluster);
			}
		}

		// Draw statistics
		spriteBatch.begin();
		font.draw(spriteBatch, "Legato:   " + legato, 20, Gdx.graphics.getHeight()-20);
		font.draw(spriteBatch, "Staccato: " + staccato, 20, Gdx.graphics.getHeight()-40);
		spriteBatch.end();

		spriteBatch.begin();
		font.draw(spriteBatch, "LegatoEffect", 20, 20);
		spriteBatch.end();
	}

	@Override
	public synchronized void onMidiNoteOn(NoteOn noteOn) {
		int width = Gdx.graphics.getWidth();
		int y = (int) ((noteOn.getNote()/128.0) * Gdx.graphics.getHeight());
		clusterXYHandler.onMidiNoteOn(noteOn, width, y);

		clusterHandler.onMidiNoteOn(noteOn);
		updateStatistics();
	}

	@Override
	public synchronized void onMidiNoteOff(NoteOff noteOff) {
		int initX = Gdx.graphics.getWidth();
		clusterXYHandler.onMidiNoteOff(noteOff, initX);

		clusterHandler.onMidiNoteOff(noteOff);
		updateStatistics();
	}

	@Override
	public void onMidiControlChange(ControlChange controlChange) {
		// TODO Auto-generated method stub

	}

	private void updateStatistics() {
		int tmpLegato = 0;
		int tmpStaccato = 0;

		List<MidiPairCluster> clusters = clusterHandler.getMidiPairClusters();
		for (int i=0; i<clusters.size()-1; i++) {
			MidiPairCluster c1 = clusters.get(i);
			MidiPairCluster c2 = clusters.get(i+1);
			if (c1.getEnd() > c2.getStart()) {
				tmpLegato += c1.getEnd() - c2.getStart();
			} else if (c1.getEnd() < c2.getStart()) {
				tmpStaccato += c2.getStart() - c1.getEnd();
			} 
		}

		legato = tmpLegato;
		staccato = tmpStaccato;
	}
}
