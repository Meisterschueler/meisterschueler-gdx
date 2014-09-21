package de.meisterschueler.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.meisterschueler.basic.AbstractNoteCluster;
import de.meisterschueler.basic.NoteOn;

public class AbstractNoteClusterTest {

	@Test
	public void testConstructor() {
		NoteOn noteOn = new NoteOn(12, 34, 56);
		AbstractNoteCluster noteOnCluster = new AbstractNoteCluster(noteOn);

		assertEquals(noteOn.getTime(), noteOnCluster.getTime());
		assertTrue(noteOnCluster.getNotes() != null && noteOnCluster.getNotes().size() == 1);
	}
}
