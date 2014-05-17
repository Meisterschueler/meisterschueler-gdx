package de.meisterschueler.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.NoteOn;

public class MidiPairTest {

	@Test
	public void testComparison() {
		MidiPair noteOn = new MidiPair(new NoteOn(100, 40, 10));
		MidiPair higher = new MidiPair(new NoteOn(100, 48, 10));
		MidiPair lower = new MidiPair(new NoteOn(100, 32, 10));
		MidiPair later = new MidiPair(new NoteOn(150, 40, 10));
		MidiPair earlier = new MidiPair(new NoteOn(50, 40, 10));
		
		assertTrue( noteOn.compareTo(higher) == -1 );
		assertTrue( noteOn.compareTo(lower) == 1 );
		assertTrue( noteOn.compareTo(later) == -1 );
		assertTrue( noteOn.compareTo(earlier) == 1 );
		
		MidiPair earlierAndLower = new MidiPair(new NoteOn(50, 32, 10));
		MidiPair laterAndHigher = new MidiPair(new NoteOn(150, 48, 10));
		
		assertTrue( noteOn.compareTo(earlierAndLower) == 1 );
		assertTrue( noteOn.compareTo(laterAndHigher) == -1 );
	}
}
