package de.meisterschueler.utils;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public abstract class Derepeater {

	private boolean pressed[] = new boolean[128];

	public abstract void onNoteOn(NoteOn noteOn);

	public abstract void onNoteOff(NoteOff noteOff);

	public void noteOnEvent(NoteOn noteOn) {
		if (noteOn.getVelocity() > 0 && !pressed[noteOn.getNote()]) {
			pressed[noteOn.getNote()] = true;
			onNoteOn(noteOn);
		} else if (noteOn.getVelocity() > 0 && pressed[noteOn.getNote()]) {
			noteOffEvent(new NoteOff(noteOn.getTime(), noteOn.getCable(), noteOn.getChannel(), noteOn.getNote(), noteOn.getVelocity()));
			onNoteOn(noteOn);
		} else if (noteOn.getVelocity() == 0) {
			noteOffEvent(new NoteOff(noteOn.getTime(), noteOn.getCable(), noteOn.getChannel(), noteOn.getNote(), noteOn.getVelocity()));
		}
	}

	public void noteOffEvent(NoteOff noteOff) {
		if (pressed[noteOff.getNote()] == true) {
			pressed[noteOff.getNote()] = false;
			onNoteOff(noteOff);
		}
	}
}
