package de.meisterschueler.gdx.screens.legato;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClusterXY {
	public long time;
	private List<MidiPairXY> midiPairs = new CopyOnWriteArrayList<MidiPairXY>();
	public int fromX;
	public int toX;

	public ClusterXY(MidiPairXY midiPairXY) {
		this.time = midiPairXY.getNoteOn().getTime();
		this.fromX = midiPairXY.fromX;
		this.toX = midiPairXY.toX;
		this.midiPairs.add(midiPairXY);
	}

	public List<MidiPairXY> getMidiPairs() {
		return midiPairs;
	}
}