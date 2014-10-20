package de.meisterschueler.basic.test;

import junit.framework.TestCase;

import org.junit.Test;

import de.meisterschueler.basic.NoteOn;

public class AbstractNoteTest extends TestCase {

	@Test
	public void testComparison() {
		NoteOn note = new NoteOn(100, 40, 10);
		NoteOn higher = new NoteOn(100, 48, 0);
		NoteOn lower = new NoteOn(100, 32, 0);

		assertTrue(note.compareTo(higher) == -1);
		assertTrue(note.compareTo(lower) == 1);
	}
}
