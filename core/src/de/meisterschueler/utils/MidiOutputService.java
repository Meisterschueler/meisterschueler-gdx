package de.meisterschueler.utils;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class MidiOutputService {
	private MidiOutput midiOutput;

	public MidiOutputService(MidiOutput midiOutput) {
		this.midiOutput = midiOutput;
	}

	public void dideldii() {
		new Thread() {
			public void run() {
				try {
					midiOutput.sendNoteOn(new NoteOn(0, 67, 60));
					Thread.sleep(150);
					midiOutput.sendNoteOff(new NoteOff(0, 67, 60));
					midiOutput.sendNoteOn(new NoteOn(0, 69, 60));
					Thread.sleep(200);
					midiOutput.sendNoteOff(new NoteOff(0, 69, 60));
					midiOutput.sendNoteOn(new NoteOn(0, 67, 60));
					Thread.sleep(400);
					midiOutput.sendNoteOff(new NoteOff(0, 67, 60));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.start();
	}

	public void ping() {
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
