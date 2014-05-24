package de.meisterschueler.gdx;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public interface MidiOutput {

	void sendNoteOn(NoteOn noteOn);

	void sendNoteOff(NoteOff noteOff);
}
