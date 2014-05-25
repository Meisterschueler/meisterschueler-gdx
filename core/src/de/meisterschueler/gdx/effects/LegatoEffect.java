package de.meisterschueler.gdx.effects;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class LegatoEffect extends Effect {

	public LegatoEffect(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, BitmapFont font) {
		super(shapeRenderer, spriteBatch, font);
	}

	public class MidiPairXY extends MidiPair {
		private int fromX;
		private int toX;
		private int y;

		public MidiPairXY(NoteOn noteOn, int x, int y) {
			super(noteOn);
			this.fromX = x;
			this.toX = x;
			this.y = y;
		}
	}

	public class ClusterXY {
		public long time;
		public List<MidiPairXY> midiPairs = new CopyOnWriteArrayList<MidiPairXY>();
		private int fromX;
		private int toX;

		public ClusterXY(MidiPairXY midiPairXY) {
			this.time = midiPairXY.getNoteOn().getTime();
			this.fromX = midiPairXY.fromX;
			this.toX = midiPairXY.toX;
			this.midiPairs.add(midiPairXY);
		}
	}

	private static final long CLUSTER_GAP = 50;
	private static final float SPEED = 100;
	List<ClusterXY> clusters = new CopyOnWriteArrayList<ClusterXY>();

	@Override
	public void onRender() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		for (int i=1; i<clusters.size(); i++) {
			ClusterXY act = clusters.get(i);
			ClusterXY pre = clusters.get(i-1);
			shapeRenderer.begin(ShapeType.Filled);
			int overlap = pre.toX - act.fromX; // negativ: gap, positiv: legato
			if (overlap < 0) {
				shapeRenderer.setColor(0, 0.2f, 0, 1);
				shapeRenderer.rect(pre.toX, 0, -overlap, Gdx.graphics.getHeight());
			} else {
				shapeRenderer.setColor(0.2f, 0, 0, 1);
				shapeRenderer.rect(act.fromX, 0, overlap, Gdx.graphics.getHeight());
			}			
			
			shapeRenderer.end();
		}

		int velocitySum = 0;
		int count = 0; 
		for (ClusterXY cluster : clusters) {
			for (MidiPairXY midiPair : cluster.midiPairs) {
				velocitySum += midiPair.getNoteOn().getVelocity();
				count++;
			}
		}
		int meanVelocity = (count > 0) ? velocitySum / count : 0;

		float delta = SPEED*Gdx.graphics.getDeltaTime();
		float rectHight = Gdx.graphics.getHeight()/128f;
		for (ClusterXY cluster : clusters) {
			boolean clusterFinished = true;
			for (MidiPairXY midiPair : cluster.midiPairs) {
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
				clusters.remove(cluster);
			}
		}
		
		spriteBatch.begin();
		font.draw(spriteBatch, "LegatoEffect", 20, 20);
		spriteBatch.end();
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		int initX = Gdx.graphics.getWidth();
		int y = (int) ((noteOn.getNote()/128.0) * Gdx.graphics.getHeight());
		if (clusters.isEmpty()) {
			ClusterXY cluster = new ClusterXY(new MidiPairXY(noteOn, initX, y));
			cluster.fromX = initX;
			clusters.add(cluster);
		} else {
			ClusterXY lastMidiPairCluster = clusters.get(clusters.size()-1);
			if (noteOn.getTime() - lastMidiPairCluster.time > CLUSTER_GAP) {
				ClusterXY cluster = new ClusterXY(new MidiPairXY(noteOn, initX, y));
				cluster.fromX = initX;
				clusters.add(cluster);
			} else {
				lastMidiPairCluster.toX = initX;
				lastMidiPairCluster.midiPairs.add(new MidiPairXY(noteOn, initX, y));
			}
		}
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		int initX = Gdx.graphics.getWidth();
		for (int i=clusters.size()-1; i>=0; i--) {
			for (MidiPairXY midiPair : clusters.get(i).midiPairs) {
				if (midiPair.getNoteOn().getNote() == noteOff.getNote() && midiPair.getNoteOff() == null) {
					midiPair.setNoteOff(noteOff);
					midiPair.toX = initX;
					clusters.get(i).toX = initX;
					return;
				}
			}
		}
		Gdx.app.log("WTF", "Got NoteOff and couldnt find a appropriate noteOn");
	}
}
