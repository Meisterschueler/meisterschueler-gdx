package de.meisterschueler.basic;


public abstract class Derepeater {

	private boolean pressed[] = new boolean[128];

	public abstract void onNoteOn(NoteOn noteOn);
	public abstract void onNoteOff(NoteOff noteOff);

	public void noteOnEvent(NoteOn noteOn) {
		if (noteOn.getVelocity() > 0 && !pressed[noteOn.getNote()]) {
			pressed[noteOn.getNote()] = true;
			onNoteOn(noteOn);
		} else if (noteOn.getVelocity() == 0) {
			noteOffEvent(new NoteOff(noteOn.getTime(), noteOn.getCable(), noteOn.getChannel(), noteOn.getNote(), noteOn.getVelocity()));
		}
	}

	public void noteOffEvent(NoteOff noteOff) {
		if (pressed[noteOff.getNote()]) {
			pressed[noteOff.getNote()] = false;
			onNoteOff(noteOff);
		}
	}
}
