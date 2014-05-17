package de.meisterschueler.gdx.effects;

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

public class BubblesEffect extends Effect {

	public BubblesEffect(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
		super(shapeRenderer, spriteBatch);
	}

	boolean[] keyPressed = new boolean[128];
	int[][] keyCounter = new int[128][128];

	List<MidiPair> midiPairs = new CopyOnWriteArrayList<MidiPair>();
	int bubbleAppear;

	@Override
	public void onCreate() {
		super.onCreate();
		bubbleAppear = Gdx.graphics.getWidth() - 20;
	}

	@Override
	public void onRender() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		font.draw(spriteBatch, "BubblesEffect", 20, 20);
		spriteBatch.end();

		long currentTime = System.currentTimeMillis();
		for (MidiPair midiPair : midiPairs) {
			int x = (int) ((midiPair.getNoteOn().getNote()/128.0) * Gdx.graphics.getWidth());
			int y = (int) ((midiPair.getNoteOn().getVelocity()/128.0) * Gdx.graphics.getHeight());

			shapeRenderer.begin(ShapeType.Filled);
			Color color = new Color(1, 0, 0, 1);
			if (midiPair.getNoteOff() != null) {
				long delta = currentTime - midiPair.getNoteOff().getTime();
				float value = (float)delta;
				color = getSpectralColor(value, 0, 500, 1, 0);

				if (delta > 500) {
					midiPairs.remove(midiPair);
				}
			}
			shapeRenderer.setColor(color);
			shapeRenderer.circle(x, y, 10);
			shapeRenderer.end();
		}
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
