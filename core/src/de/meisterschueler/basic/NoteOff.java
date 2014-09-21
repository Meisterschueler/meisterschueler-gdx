package de.meisterschueler.basic;

public class NoteOff extends AbstractNote {
	public NoteOff(long time, int cable, int channel, int note, int velocity) {
		super(time, cable, channel, note, velocity);
	}

	public NoteOff(long time, int note, int velocity) {
		super(time, note, velocity);
	}
}
