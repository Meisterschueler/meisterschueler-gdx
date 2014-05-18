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
import de.meisterschueler.gdx.effects.LegatoEffect;
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

	private Debounce rightPedal;
	private Debounce leftPedal;
	private MidiOutput midiOutput;

	public MyGdxGame(MidiOutput midiOutput) {
		this.midiOutput = midiOutput;
	}

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();

		currentEffect = new ScrollEffect(shapeRenderer, spriteBatch);

		MyInputProcessor inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);

		leftPedal = new Debounce(100, 10) {

			@Override
			public void execute(boolean state) {
				if (state)
					changeEffect();
			}
		};

		rightPedal = new Debounce(100, 10) {

			@Override
			public void execute(boolean state) {
				// TODO Auto-generated method stub	
			}
		};
	}
	
	@Override
	public void render () {
		if (currentEffect != null)
			currentEffect.onRender();
	}

	public void onMidiNoteOn(NoteOn noteOn) {		
		if (currentEffect != null)
			currentEffect.onMidiNoteOn(noteOn);
	}

	public void onMidiNoteOff(NoteOff noteOff) {
		if (currentEffect != null)
			currentEffect.onMidiNoteOff(noteOff);
	}

	public void onMidiControlChange(ControlChange controlChange) {
		switch (controlChange.getFunction()) {
		case 64:
			if (controlChange.getValue() == 127) {
				rightPedal.hit(true);
			} else {
				rightPedal.hit(false);
			}
			break;
		case 67:
			if (controlChange.getValue() == 127) {
				leftPedal.hit(true);
			} else {
				leftPedal.hit(false);
			}
			break;
		}
	}

	private void changeEffect() {
		if (currentEffect instanceof ScrollEffect) {
			currentEffect = new BubblesEffect(shapeRenderer, spriteBatch);
		} else if (currentEffect instanceof BubblesEffect) {
			currentEffect = new LegatoEffect(shapeRenderer, spriteBatch);
		} else if (currentEffect instanceof LegatoEffect) {
			currentEffect = new TextEffect(shapeRenderer, spriteBatch);
		} else if (currentEffect instanceof TextEffect) {
			currentEffect = new ScrollEffect(shapeRenderer, spriteBatch);
		}
		
		new Thread() {
			public void run() {
				midiOutput.sendNoteOn(new NoteOn(0, 108, 60));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				midiOutput.sendNoteOff(new NoteOff(0, 108, 60));
			}
		}.start();
	}
}
