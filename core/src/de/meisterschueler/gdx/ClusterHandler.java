package de.meisterschueler.gdx;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.meisterschueler.basic.AbstractNoteCluster;
import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.MidiPairCluster;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class ClusterHandler {
	private static final long CLUSTER_GAP = 50;

	private List<AbstractNoteCluster> noteOnClusters = new CopyOnWriteArrayList<AbstractNoteCluster>();
	private List<AbstractNoteCluster> noteOffClusters = new CopyOnWriteArrayList<AbstractNoteCluster>();
	private List<MidiPairCluster> midiPairClusters = new CopyOnWriteArrayList<MidiPairCluster>();

	public void onMidiNoteOn(NoteOn noteOn) {

		// NoteOnCluster
		if (noteOnClusters.isEmpty()) {
			AbstractNoteCluster noteOnCluster = new AbstractNoteCluster(noteOn);
			noteOnClusters.add(noteOnCluster);
		} else {
			AbstractNoteCluster lastNoteCluster = noteOnClusters.get(noteOnClusters.size()-1);
			if (noteOn.getTime() - lastNoteCluster.getTime() > CLUSTER_GAP) {
				AbstractNoteCluster noteOnCluster = new AbstractNoteCluster(noteOn);
				noteOnClusters.add(noteOnCluster);
			} else {
				lastNoteCluster.getNotes().add(noteOn);
			}
		}

		// MidiPairCluster
		if (midiPairClusters.isEmpty()) {
			MidiPairCluster midiPairCuster = new MidiPairCluster(new MidiPair(noteOn));
			midiPairClusters.add(midiPairCuster);
		} else {
			MidiPairCluster lastMidiPairCluster = midiPairClusters.get(midiPairClusters.size()-1);
			if (noteOn.getTime() - lastMidiPairCluster.getStart() > CLUSTER_GAP) {
				MidiPairCluster midiPairCluster = new MidiPairCluster(new MidiPair(noteOn));
				midiPairClusters.add(midiPairCluster);
			} else {
				lastMidiPairCluster.getMidiPairs().add(new MidiPair(noteOn));
			}
		}
	}

	public void onMidiNoteOff(NoteOff noteOff) {

		// NoteOffCluster
		if (noteOffClusters.isEmpty()) {
			AbstractNoteCluster noteOffCluster = new AbstractNoteCluster(noteOff);
			noteOffClusters.add(noteOffCluster);
		} else {
			AbstractNoteCluster lastNoteCluster = noteOffClusters.get(noteOffClusters.size()-1);
			if (noteOff.getTime() - lastNoteCluster.getTime() > CLUSTER_GAP) {
				AbstractNoteCluster noteOffCluste = new AbstractNoteCluster(noteOff);
				noteOffClusters.add(noteOffCluste);
			} else {
				lastNoteCluster.getNotes().add(noteOff);
			}
		}

		// MidiPairCluster
		for (int i=midiPairClusters.size()-1; i>=0; i--) {
			MidiPairCluster midiPairCluster = midiPairClusters.get(i);
			for (MidiPair midiPair : midiPairCluster.getMidiPairs()) {
				if (midiPair.getNoteOn().getNote() == noteOff.getNote() && midiPair.getNoteOff() == null) {
					midiPair.setNoteOff(noteOff);
					midiPairCluster.setEnd(noteOff.getTime());
					return;
				}
			}
		}
	}

	public List<AbstractNoteCluster> getNoteOnClusters() {
		return noteOnClusters;
	}

	public List<AbstractNoteCluster> getNoteOffClusters() {
		return noteOffClusters;
	}

	public List<MidiPairCluster> getMidiPairClusters() {
		return midiPairClusters;
	}

	public void clear() {
		noteOnClusters.clear();
		noteOffClusters.clear();
		midiPairClusters.clear();
	}
}
