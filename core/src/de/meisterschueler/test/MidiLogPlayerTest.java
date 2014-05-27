package de.meisterschueler.test;

import java.io.IOException;

import org.junit.Test;

import de.meisterschueler.basic.MidiLogPlayer;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class MidiLogPlayerTest {

	@Test
	public void test() throws IOException, InterruptedException {
		String file = "/Users/konstantin/Downloads/joseftest";
		MidiLogPlayer midiLogPlayer = new MidiLogPlayer(file) {

			@Override
			public void onNoteOn(NoteOn noteOn) {
				System.out.println("NoteOn: " + noteOn.getNote() + " " + noteOn.getVelocity());
			}

			@Override
			public void onNoteOff(NoteOff noteOff) {
				System.out.println("NoteOff: " + noteOff.getNote() + " " + noteOff.getVelocity());
			}
		};
	}
}
