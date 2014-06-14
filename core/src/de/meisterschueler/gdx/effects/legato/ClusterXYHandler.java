package de.meisterschueler.gdx.effects.legato;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class ClusterXYHandler {
	private static final long CLUSTER_GAP = 50;
	
	private List<ClusterXY> clusters = new CopyOnWriteArrayList<ClusterXY>();
	
	public void onMidiNoteOn(NoteOn noteOn, int width, int y) {
		
		if (clusters.isEmpty()) {
			ClusterXY cluster = new ClusterXY(new MidiPairXY(noteOn, width, y));
			cluster.fromX = width;
			clusters.add(cluster);
		} else {
			ClusterXY lastMidiPairCluster = clusters.get(clusters.size()-1);
			if (noteOn.getTime() - lastMidiPairCluster.time > CLUSTER_GAP) {
				ClusterXY cluster = new ClusterXY(new MidiPairXY(noteOn, width, y));
				cluster.fromX = width;
				clusters.add(cluster);
			} else {
				lastMidiPairCluster.toX = width;
				lastMidiPairCluster.getMidiPairs().add(new MidiPairXY(noteOn, width, y));
			}
		}
	}
	
	public void onMidiNoteOff(NoteOff noteOff, int initX) {
		for (int i=clusters.size()-1; i>=0; i--) {
			for (MidiPairXY midiPair : clusters.get(i).getMidiPairs()) {
				if (midiPair.getNoteOn().getNote() == noteOff.getNote() && midiPair.getNoteOff() == null) {
					midiPair.setNoteOff(noteOff);
					midiPair.toX = initX;
					clusters.get(i).toX = initX;
					return;
				}
			}
		}
	}
	
	public void removeCluster(ClusterXY cluster) {
		clusters.remove(cluster);
	}
	
	public List<ClusterXY> getMidiPairClusters() {
		return clusters;
	}

	public void clear() {
		clusters.clear();
	}
}
