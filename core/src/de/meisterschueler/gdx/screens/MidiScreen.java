package de.meisterschueler.gdx.screens;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.MainMenu;

public class MidiScreen extends InputAdapter implements Screen {

	private static final Map<Integer, Integer> noteMap;
	static {
		Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
		tempMap.put(Keys.A, 48);
		tempMap.put(Keys.W, 49);
		tempMap.put(Keys.S, 50);
		tempMap.put(Keys.E, 51);
		tempMap.put(Keys.D, 52);
		tempMap.put(Keys.F, 53);
		tempMap.put(Keys.T, 54);
		tempMap.put(Keys.G, 55);
		tempMap.put(Keys.Z, 56);
		tempMap.put(Keys.H, 57);
		tempMap.put(Keys.U, 58);
		tempMap.put(Keys.J, 59);
		noteMap = Collections.unmodifiableMap(tempMap);
	}
	
	public MidiScreen() {
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		}
		
		// Parse noteOn
		int shift = 0;
		if ((keycode == Keys.SHIFT_LEFT) || (keycode == Keys.SHIFT_RIGHT)) {
			shift = 12;
		}
		for (Integer i : noteMap.keySet()) {
			if (keycode == i) {
				int note = noteMap.get(i)+shift;
				onMidiNoteOn(new NoteOn(System.currentTimeMillis(), 0, 0, note, 10));
			}
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// Parse noteOff
		int shift = 0;
		if ((keycode == Keys.SHIFT_LEFT) || (keycode == Keys.SHIFT_RIGHT)) {
			shift = 0;
		}
		for (Integer i : noteMap.keySet()) {
			if (keycode == i) {
				int note = noteMap.get(i)+shift;
				onMidiNoteOff(new NoteOff(System.currentTimeMillis(), 0, 0, note, 64));
			}
		}

		return false;
	}

	public void onMidiNoteOn(NoteOn noteOn) {};
	public void onMidiNoteOff(NoteOff noteOff) {};
	public void onMidiControlChange(ControlChange controlChange) {};

	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
