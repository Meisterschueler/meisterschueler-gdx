package de.meisterschueler.gdx;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.MidiPairCluster;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class ClusterHandler {
	private static final long CLUSTER_GAP = 50;
	
	private List<MidiPairCluster> clusters = new CopyOnWriteArrayList<MidiPairCluster>();
	
	public void onMidiNoteOn(NoteOn noteOn) {
		if (clusters.isEmpty()) {
			MidiPairCluster cluster = new MidiPairCluster(new MidiPair(noteOn));
			clusters.add(cluster);
		} else {
			MidiPairCluster lastMidiPairCluster = clusters.get(clusters.size()-1);
			if (noteOn.getTime() - lastMidiPairCluster.getStart() > CLUSTER_GAP) {
				MidiPairCluster cluster = new MidiPairCluster(new MidiPair(noteOn));
				clusters.add(cluster);
			} else {
				lastMidiPairCluster.getMidiPairs().add(new MidiPair(noteOn));
			}
		}
	}
	
	public void onMidiNoteOff(NoteOff noteOff) {
		for (int i=clusters.size()-1; i>=0; i--) {
			MidiPairCluster cluster = clusters.get(i);
			for (MidiPair midiPair : cluster.getMidiPairs()) {
				if (midiPair.getNoteOn().getNote() == noteOff.getNote() && midiPair.getNoteOff() == null) {
					midiPair.setNoteOff(noteOff);
					cluster.setEnd(noteOff.getTime());
					return;
				}
			}
		}
	}
	
	public void removeCluster(MidiPairCluster midiPairCluster) {
		clusters.remove(midiPairCluster);
	}
	
	public List<MidiPairCluster> getMidiPairClusters() {
		return clusters;
	}

	public void clear() {
		clusters.clear();
	}
}
