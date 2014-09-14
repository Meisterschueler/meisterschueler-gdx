package de.meisterschueler.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.Derepeater;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.effects.BubblesEffect;
import de.meisterschueler.gdx.effects.ChromaticEffect;
import de.meisterschueler.gdx.effects.MidiScreen;
import de.meisterschueler.gdx.effects.ScrollEffect;
import de.meisterschueler.gdx.effects.SpectrumEffect;
import de.meisterschueler.gdx.effects.TextEffect;
import de.meisterschueler.gdx.effects.legato.LegatoEffect;
import de.meisterschueler.gpgs.ScoreService;

public class Meisterschueler extends Game {

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
		//setScreen(new ScrollEffect());
		setScreen(new MainMenu());

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
			setScreen(new ChromaticEffect(scoreService));
		} else if (screen instanceof ChromaticEffect) {
			setScreen(new ScrollEffect());
		}

		midiOutputService.ping();
	}
}
