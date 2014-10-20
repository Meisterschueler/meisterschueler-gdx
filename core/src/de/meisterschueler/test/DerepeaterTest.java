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
		assertEquals(counterNoteOn[30], 1);
		assertEquals(counterNoteOff[30], 1);

		// NoteOn with velocity 0 instead of NoteOff
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  35, 64));
		derepeater.noteOffEvent(new NoteOff(0, 0, 0, 35, 0));
		assertEquals(counterNoteOn[35], 1);
		assertEquals(counterNoteOff[35], 1);

		// Multiple NoteOnEvents
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  40, 64));
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  40, 70));
		assertEquals(counterNoteOn[40], 2);
		assertEquals(counterNoteOff[40], 1);

		// Repeat notes
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  50, 64));
		derepeater.noteOffEvent(new NoteOff(0, 0, 0,  50, 64));
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  50, 64));
		derepeater.noteOffEvent(new NoteOff(0, 0, 0,  50, 64));
		derepeater.noteOnEvent(new NoteOn(0, 0, 0,  50, 64));
		derepeater.noteOffEvent(new NoteOff(0, 0, 0,  50, 64));
		assertEquals(counterNoteOn[50], 3);
		assertEquals(counterNoteOff[50], 3);

		// NoteOff without NoteOn
		derepeater.noteOffEvent(new NoteOff(0, 0, 0, 60, 64));
		assertEquals(counterNoteOn[60], 0);
		assertEquals(counterNoteOff[60], 1);
	}
}
