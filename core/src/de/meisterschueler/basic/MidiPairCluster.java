package de.meisterschueler.basic;

import java.util.ArrayList;
import java.util.List;

public class MidiPairCluster {
	private long start;
	private long end;
	private List<MidiPair> midiPairs = new ArrayList<MidiPair>();

	public MidiPairCluster(MidiPair midiPair) {
		midiPairs.add(midiPair);
		start = midiPair.getNoteOn().getTime();
	}
	
	public long getStart() {
		return start;
	}
	
	public void setStart(long time) {
		this.start = time;
	}
	
	public long getEnd() {
		return end;
	}
	
	public void setEnd(long time) {
		this.end = time;
	}
	
	public List<MidiPair> getMidiPairs() {
		return midiPairs;
	}
	
	public void setMidiPairs(List<MidiPair> midiPairs) {
		this.midiPairs = midiPairs;
	}

}
