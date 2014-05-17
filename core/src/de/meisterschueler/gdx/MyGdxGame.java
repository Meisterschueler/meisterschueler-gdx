package de.meisterschueler.gdx;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.effects.BubblesEffect;
import de.meisterschueler.gdx.effects.ClusterEffect;
import de.meisterschueler.gdx.effects.Effect;
import de.meisterschueler.gdx.effects.ScrollEffect;
import de.meisterschueler.gdx.effects.TextEffect;

public class MyGdxGame extends ApplicationAdapter {
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

	private class MyInputProcessor extends InputAdapter {
		private int shift = 0;

		@Override
		public boolean keyDown(int keycode) {
			// Parse noteOn
			if ((keycode == Keys.SHIFT_LEFT) || (keycode == Keys.SHIFT_RIGHT)) {
				shift = 12;
			}
			for (Integer i : noteMap.keySet()) {
				if (keycode == i) {
					int note = noteMap.get(i)+shift;
					onMidiNoteOn(new NoteOn(System.currentTimeMillis(), 0, 0, note, 10));
				}
			}

			// Parse screen change
			if (keycode == Keys.SPACE) {
				changeEffect();
			}

			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// Parse noteOff
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
	}

	private Effect currentEffect;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;


	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		
		currentEffect = new ScrollEffect(shapeRenderer, spriteBatch);
		currentEffect.onCreate();

		MyInputProcessor inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render () {

		if (currentEffect != null)
			currentEffect.onRender();
	}

	public void onMidiNoteOn(NoteOn noteOn) {
		if (noteOn.getNote() == 96) {
			changeEffect();
		}
		if (currentEffect != null)
			currentEffect.onMidiNoteOn(noteOn);
	}

	public void onMidiNoteOff(NoteOff noteOff) {
		if (currentEffect != null)
			currentEffect.onMidiNoteOff(noteOff);
	}

	public void onMidiControlChange(ControlChange controlChange) {
		changeEffect();		
	}

	private void changeEffect() {
		if (currentEffect instanceof ScrollEffect) {
			currentEffect = new BubblesEffect(shapeRenderer, spriteBatch);
		} else if (currentEffect instanceof BubblesEffect) {
			currentEffect = new ClusterEffect(shapeRenderer, spriteBatch);
		} else if (currentEffect instanceof ClusterEffect) {
			currentEffect = new TextEffect(shapeRenderer, spriteBatch);
		} else if (currentEffect instanceof TextEffect) {
			currentEffect = new ScrollEffect(shapeRenderer, spriteBatch);
		}
		currentEffect.onCreate();
	}
}
