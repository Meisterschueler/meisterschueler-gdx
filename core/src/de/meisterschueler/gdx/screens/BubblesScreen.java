package de.meisterschueler.gdx.screens;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.Utils;

public class BubblesScreen extends MidiScreen {
	
	private class Background extends Actor {
		
		private ShapeRenderer shapeRenderer;
		
		public Background() {
			shapeRenderer = new ShapeRenderer();
		}
		
		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.end();
			
			shapeRenderer.begin(ShapeType.Filled);
			long currentTime = System.currentTimeMillis();
			for (MidiPair midiPair : midiPairs) {
				int x = (int) ((midiPair.getNoteOn().getNote()/128.0) * Gdx.graphics.getWidth());
				int y = (int) ((midiPair.getNoteOn().getVelocity()/128.0) * Gdx.graphics.getHeight());

				Color color = new Color(1, 0, 0, 1);
				if (midiPair.getNoteOff() != null) {
					long length = currentTime - midiPair.getNoteOff().getTime();
					float value = (float)length;
					color = Utils.getSpectralColor(value, 0, 500, 1, 0);

					if (length > 500) {
						midiPairs.remove(midiPair);
					}
				}
				shapeRenderer.setColor(color);
				shapeRenderer.circle(x, y, 10);
			}
			shapeRenderer.end();
			
			batch.begin();
		}
	}
	
	boolean[] keyPressed = new boolean[128];
	int[][] keyCounter = new int[128][128];

	List<MidiPair> midiPairs = new CopyOnWriteArrayList<MidiPair>();

	private Background background;

	public BubblesScreen() {
		super();
		
		background = new Background();
		gameGroup.addActor(background);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		updateFPS();
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		keyPressed[noteOn.getNote()] = true;
		keyCounter[noteOn.getNote()][noteOn.getVelocity()]++;

		midiPairs.add(new MidiPair(noteOn));
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		keyPressed[noteOff.getNote()] = false;

		for (MidiPair pair : midiPairs) {
			if (pair.getNoteOn().getNote() == noteOff.getNote() && pair.getNoteOff() == null) {
				pair.setNoteOff(noteOff);
				return;
			}
		}
	}
}
