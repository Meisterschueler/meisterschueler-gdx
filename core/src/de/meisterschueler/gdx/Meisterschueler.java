package de.meisterschueler.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.Derepeater;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.screens.MidiScreen;
import de.meisterschueler.gpgs.ScoreService;

public class Meisterschueler extends Game {

	private Derepeater derepeater = new Derepeater() {
		@Override
		public void onNoteOn(NoteOn noteOn) {
			Screen screen = getScreen();
			if (screen instanceof MidiScreen)
				((MidiScreen) screen).onMidiNoteOn(noteOn);
		}

		@Override
		public void onNoteOff(NoteOff noteOff) {
			Screen screen = getScreen();
			if (screen instanceof MidiScreen)
				((MidiScreen) screen).onMidiNoteOff(noteOff);
		}
	};

	private Debounce rightPedal;
	private Debounce leftPedal;
	private ScoreService scoreService;
	private MidiOutputService midiOutputService;

	public Meisterschueler(MidiOutput midiOutput) {
		midiOutputService = new MidiOutputService(midiOutput);
	}

	public ScoreService getScoreService() {
		return scoreService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	@Override
	public void create() {
		setScreen(new MainMenu());

		leftPedal = new Debounce(100, 10) {
			@Override
			public void execute(boolean set) {
				if (set && rightPedal.isSet())
					toggleUI();
			}
		};

		rightPedal = new Debounce(100, 10) {
			@Override
			public void execute(boolean set) {
				if (set && leftPedal.isSet())
					toggleUI();
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

	private void toggleUI() {
		Screen screen = getScreen();
		if (screen instanceof MidiScreen) {
			((MidiScreen) screen).toggleUI();
		}

		midiOutputService.ping();
	}
}
