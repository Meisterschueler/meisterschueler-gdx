package de.meisterschueler.gdx.screens.legato;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class ClusterXYHandler {
	private static final long CLUSTER_GAP = 50;

	private List<NoteRectangle> noteOnRectangles = new CopyOnWriteArrayList<NoteRectangle>();
	private List<NoteRectangle> noteOffRectangles = new CopyOnWriteArrayList<NoteRectangle>();
	private List<ClusterXY> midiPairClusters = new CopyOnWriteArrayList<ClusterXY>();

	public void onMidiNoteOn(NoteOn noteOn, int x, int y) {

		// NoteOnCluster
		if (noteOnRectangles.isEmpty()) {
			NoteRectangle noteOnRectangle = new NoteRectangle(noteOn.getTime());
			noteOnRectangle.set(x, 0, 0, 20);
			noteOnRectangles.add(noteOnRectangle);
		} else {
			NoteRectangle lastNoteRectangle = noteOnRectangles.get(noteOnRectangles.size()-1);
			if (noteOn.getTime() - lastNoteRectangle.getTime() > CLUSTER_GAP) {
				NoteRectangle noteOnRectangle = new NoteRectangle(noteOn.getTime());
				noteOnRectangle.set(x, 0, 0, 20);
				noteOnRectangles.add(noteOnRectangle);
			} else {
				lastNoteRectangle.setWidth(x - lastNoteRectangle.x);
			}
		}

		// MidiPairClusters
		if (midiPairClusters.isEmpty()) {
			ClusterXY cluster = new ClusterXY(new MidiPairXY(noteOn, x, y));
			cluster.fromX = x;
			midiPairClusters.add(cluster);
		} else {
			ClusterXY lastMidiPairCluster = midiPairClusters.get(midiPairClusters.size()-1);
			if (noteOn.getTime() - lastMidiPairCluster.time > CLUSTER_GAP) {
				ClusterXY cluster = new ClusterXY(new MidiPairXY(noteOn, x, y));
				cluster.fromX = x;
				midiPairClusters.add(cluster);
			} else {
				lastMidiPairCluster.toX = x;
				lastMidiPairCluster.getMidiPairs().add(new MidiPairXY(noteOn, x, y));
			}
		}
	}

	public void onMidiNoteOff(NoteOff noteOff, int x, int y) {

		// NoteOffCluster
		if (noteOffRectangles.isEmpty()) {
			NoteRectangle noteOffRectangle = new NoteRectangle(noteOff.getTime());
			noteOffRectangle.set(x, Gdx.graphics.getHeight()-20, 0, 20);
			noteOffRectangles.add(noteOffRectangle);
		} else {
			NoteRectangle lastNoteRectangle = noteOffRectangles.get(noteOffRectangles.size()-1);
			if (noteOff.getTime() - lastNoteRectangle.getTime() > CLUSTER_GAP) {
				NoteRectangle noteOffRectangle = new NoteRectangle(noteOff.getTime());
				noteOffRectangle.set(x, Gdx.graphics.getHeight()-20, 0, 20);
				noteOffRectangles.add(noteOffRectangle);
			} else {
				lastNoteRectangle.setWidth(x - lastNoteRectangle.x);
			}
		}

		// MidiPairClusters
		for (int i=midiPairClusters.size()-1; i>=0; i--) {
			for (MidiPairXY midiPair : midiPairClusters.get(i).getMidiPairs()) {
				if (midiPair.getNoteOn().getNote() == noteOff.getNote() && midiPair.getNoteOff() == null) {
					midiPair.setNoteOff(noteOff);
					midiPair.toX = x;
					midiPairClusters.get(i).toX = x;
					return;
				}
			}
		}
	}

	public void removeNoteOnRectangle(NoteRectangle noteRectangle) {
		noteOnRectangles.remove(noteRectangle);
	}

	public void removeNoteOffRectangle(NoteRectangle noteRectangle) {
		noteOffRectangles.remove(noteRectangle);
	}

	public void removeCluster(ClusterXY cluster) {
		midiPairClusters.remove(cluster);
	}

	public List<NoteRectangle> getNoteOnRectangles() {
		return noteOnRectangles;
	}

	public List<NoteRectangle> getNoteOffRectangles() {
		return noteOffRectangles;
	}

	public List<ClusterXY> getMidiPairClusters() {
		return midiPairClusters;
	}

	public void clear() {
		midiPairClusters.clear();
	}
}
