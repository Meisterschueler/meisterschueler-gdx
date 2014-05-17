package de.meisterschueler.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.MidiPairCluster;
import de.meisterschueler.basic.NoteOn;

public class MidiPairClusterTest {

	@Test
	public void testConstructor() {
		NoteOn noteOn = new NoteOn(12, 34, 56);
		MidiPair midiPair = new MidiPair(noteOn);
		MidiPairCluster midiPairCluster = new MidiPairCluster(midiPair);
		
		assertEquals(noteOn.getTime(), midiPairCluster.getTime());
	}

}
