package de.meisterschueler.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.utils.Derepeater;

public class DerepeaterTest {

	private int counterNoteOn[] = new int[128];
	private int counterNoteOff[] = new int[128];

	@Test
	public void test() {

		Derepeater derepeater = new Derepeater() {

			@Override
			public void onNoteOn(NoteOn noteOn) {
				counterNoteOn[noteOn.getNote()]++;
			}

			@Override
			public void onNoteOff(NoteOff noteOff) {
				counterNoteOff[noteOff.getNote()]++;
			}
		};

		// Common case
		derepeater.noteOnEvent(new NoteOn(0, 0, 0, 30, 64));
		derepeater.noteOffEvent(new NoteOff(0, 0, 0, 30, 0));
		assertEquals(1, counterNoteOn[30]);
		assertEquals(1, counterNoteOff[30]);

		// NoteOn with velocity 0 instead of NoteOff
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  35, 64));
		derepeater.noteOffEvent(new NoteOff(0, 0, 0, 35, 0));
		assertEquals(1, counterNoteOn[35]);
		assertEquals(1, counterNoteOff[35]);

		// Multiple NoteOnEvents
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  40, 64));
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  40, 70));
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  40, 68));
		assertEquals(3, counterNoteOn[40]);
		assertEquals(2, counterNoteOff[40]);

		// Repeat notes
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  50, 64));
		derepeater.noteOffEvent(new NoteOff(0, 0, 0,  50, 64));
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  50, 64));
		derepeater.noteOffEvent(new NoteOff(0, 0, 0,  50, 64));
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  50, 64));
		derepeater.noteOffEvent(new NoteOff(0, 0, 0,  50, 64));
		assertEquals(3, counterNoteOn[50]);
		assertEquals(3, counterNoteOff[50]);

		// NoteOff without NoteOn
		derepeater.noteOffEvent(new NoteOff(0, 0, 0, 60, 64));
		assertEquals(0, counterNoteOn[60]);
		assertEquals(0, counterNoteOff[60]);
	}
}
