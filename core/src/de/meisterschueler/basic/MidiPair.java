package de.meisterschueler.basic;

public class MidiPair implements Comparable<MidiPair> {
	private NoteOn noteOn;
	private NoteOff noteOff;

	public MidiPair(NoteOn noteOn, NoteOff noteOff) {
		this.noteOn = noteOn;
		this.noteOff = noteOff;
	}

	public MidiPair(NoteOn noteOn) {
		this.noteOn = noteOn;
	}

	@Override
	public int compareTo(MidiPair rhs) {
		if (this.getNoteOn().getTime() != rhs.getNoteOn().getTime()) {
			return (this.getNoteOn().getTime() < rhs.getNoteOn().getTime() ? -1 : 1);
		} else {
			return (this.getNoteOn().getNote() < rhs.getNoteOn().getNote() ? -1 : 1);
		}
	}

	public NoteOn getNoteOn() {
		return noteOn;
	}

	public void setNoteOn(NoteOn noteOn) {
		this.noteOn = noteOn;
	}

	public NoteOff getNoteOff() {
		return noteOff;
	}

	public void setNoteOff(NoteOff noteOff) {
		this.noteOff = noteOff;
	}
}
