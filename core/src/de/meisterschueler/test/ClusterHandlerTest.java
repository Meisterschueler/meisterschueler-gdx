package de.meisterschueler.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.meisterschueler.basic.MidiPairCluster;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.ClusterHandler;

public class ClusterHandlerTest {

	@Test
	public void test() {
		ClusterHandler clusterHandler = new ClusterHandler();
		
		clusterHandler.onMidiNoteOn(new NoteOn(10, 50, 20));
		clusterHandler.onMidiNoteOff(new NoteOff(20, 50, 64));
		assertTrue(clusterHandler.getMidiPairClusters().size() == 1);
		assertTrue(clusterHandler.getMidiPairClusters().get(0).getStart() == 10);
		assertTrue(clusterHandler.getMidiPairClusters().get(0).getEnd() == 20);
		
		clusterHandler.onMidiNoteOn(new NoteOn(70, 50, 20));
		assertTrue(clusterHandler.getMidiPairClusters().size() == 2);
		
		clusterHandler.onMidiNoteOn(new NoteOn(72, 51, 20));
		clusterHandler.onMidiNoteOff(new NoteOff(73, 51, 64));
		assertTrue(clusterHandler.getMidiPairClusters().size() == 2);
		
		MidiPairCluster cluster0 = clusterHandler.getMidiPairClusters().get(0);
		clusterHandler.removeCluster(cluster0);
		assertTrue(clusterHandler.getMidiPairClusters().size() == 1);
		
		clusterHandler.clear();
		assertTrue(clusterHandler.getMidiPairClusters().size() == 0);
	}
}
