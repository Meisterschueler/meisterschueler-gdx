package de.meisterschueler.gdx;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.Derepeater;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.effects.BubblesEffect;
import de.meisterschueler.gdx.effects.MidiScreen;
import de.meisterschueler.gdx.effects.ScrollEffect;
import de.meisterschueler.gdx.effects.SpectrumEffect;
import de.meisterschueler.gdx.effects.TextEffect;
import de.meisterschueler.gdx.effects.legato.LegatoEffect;
import de.meisterschueler.gpgs.ScoreService;

public class Meisterschueler extends Game {
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

	private Derepeater derepeater = new Derepeater() {

		@Override
		public void onNoteOn(NoteOn noteOn) {
			if (getScreen() instanceof MidiScreen)
				((MidiScreen)getScreen()).onMidiNoteOn(noteOn);
		}

		@Override
		public void onNoteOff(NoteOff noteOff) {
			if (getScreen() instanceof MidiScreen)
				((MidiScreen)getScreen()).onMidiNoteOff(noteOff);
		}
	};

	private Debounce rightPedal;
	private Debounce leftPedal;
	private ScoreService scoreService;
	private MidiOutputService midiOutputService;

	public Meisterschueler(ScoreService scoreService, MidiOutput midiOutput) {
		this.scoreService = scoreService;
		midiOutputService = new MidiOutputService(midiOutput);
	}

	@Override
	public void create () {
		MyInputProcessor inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
		
		setScreen(new ScrollEffect());

		leftPedal = new Debounce(100, 10) {

			@Override
			public void execute(boolean set) {
				if (set && rightPedal.isSet())
					changeEffect();
			}
		};

		rightPedal = new Debounce(100, 10) {

			@Override
			public void execute(boolean set) {
				if (set && leftPedal.isSet())
					changeEffect();
			}
		};
	}

	public void onMidiNoteOn(NoteOn noteOn) {		
		derepeater.noteOnEvent(noteOn);
	}

	public void onMidiNoteOff(NoteOff noteOff) {
		derepeater.noteOffEvent(noteOff);
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

	public void onDeviceAttached(String usbDeviceName) {
		midiOutputService.dideldii();
	}

	public void onDeviceDetached(String deviceName) {
	}

	private void changeEffect() {
		Screen screen = getScreen();
		if (screen instanceof SpectrumEffect) {
			setScreen(new ScrollEffect());
		} else if (screen instanceof ScrollEffect) {
			setScreen(new BubblesEffect());
		} else if (screen instanceof BubblesEffect) {
			setScreen(new LegatoEffect());
		} else if (screen instanceof LegatoEffect) {
			setScreen(new TextEffect());
		} else if (screen instanceof TextEffect) {
			setScreen(new ScrollEffect());
		}

		midiOutputService.ping();
	}
}
