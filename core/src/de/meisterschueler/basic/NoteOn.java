package de.meisterschueler.basic;

public class NoteOn extends AbstractNote {

	public NoteOn(long time, int cable, int channel, int note, int velocity) {
		super(time, cable, channel, note, velocity);
	}

	public NoteOn(long time, int note, int velocity) {
		super(time, note, velocity);
	}

}
