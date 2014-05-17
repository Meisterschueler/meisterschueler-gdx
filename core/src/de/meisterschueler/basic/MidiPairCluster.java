package de.meisterschueler.basic;

import java.util.ArrayList;
import java.util.List;

public class MidiPairCluster {
	private long time;
	private List<MidiPair> midiPairs = new ArrayList<MidiPair>();

	public MidiPairCluster(MidiPair midiPair) {
		midiPairs.add(midiPair);
		time = midiPair.getNoteOn().getTime();
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public List<MidiPair> getMidiPairs() {
		return midiPairs;
	}
	public void setMidiPairs(List<MidiPair> midiPairs) {
		this.midiPairs = midiPairs;
	}
}
