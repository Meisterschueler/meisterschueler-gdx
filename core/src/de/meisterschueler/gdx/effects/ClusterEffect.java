package de.meisterschueler.gdx.effects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class ClusterEffect extends Effect {

	public ClusterEffect(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
		super(shapeRenderer, spriteBatch);
	}

	public class MidiPairXY extends MidiPair {
		private int fromX;
		private int toX;

		public MidiPairXY(NoteOn noteOn, int fromX, int toX) {
			super(noteOn);
			this.fromX = fromX;
			this.toX = toX;
		}
	}

	public class ClusterXY {
		public long time;
		public List<MidiPairXY> midiPairs = new ArrayList<MidiPairXY>();
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

		spriteBatch.begin();
		font.draw(spriteBatch, "ClusterEffect", 20, 20);
		spriteBatch.end();

		int initX = Gdx.graphics.getWidth();
		float delta = SPEED*Gdx.graphics.getDeltaTime();
		for (ClusterXY cluster : clusters) {
			List<MidiPairXY> midiPairs = cluster.midiPairs;
			for (int i=0; i<midiPairs.size(); i++) {
				MidiPairXY midiPair = midiPairs.get(i);
				if (midiPair.getNoteOff() == null) {
					midiPair.toX = initX;
				}
				
				shapeRenderer.begin(ShapeType.Filled);

				shapeRenderer.setColor(Color.RED);
				shapeRenderer.rect(midiPair.fromX, i*20, midiPair.toX-midiPair.fromX, (i+1)*20);
				shapeRenderer.end();
				
				midiPair.fromX -= delta;
				midiPair.toX -= delta;
			}
			cluster.fromX -= delta;
			cluster.toX -= delta;
		}
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		int initX = Gdx.graphics.getWidth();
		if (clusters.isEmpty()) {
			ClusterXY bubble = new ClusterXY(new MidiPairXY(noteOn, initX, initX));
			clusters.add(bubble);
		} else {
			ClusterXY lastMidiPairCluster = clusters.get(clusters.size()-1);
			if (noteOn.getTime() - lastMidiPairCluster.time > CLUSTER_GAP) {
				clusters.add(new ClusterXY(new MidiPairXY(noteOn, initX, initX)));
			} else {
				lastMidiPairCluster.midiPairs.add(new MidiPairXY(noteOn, initX, initX));
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
