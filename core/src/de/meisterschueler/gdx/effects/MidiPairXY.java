package de.meisterschueler.gdx.effects;

import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.NoteOn;

	public class MidiPairXY extends MidiPair {
		public int fromX;
		public int toX;
		public int y;

		public MidiPairXY(NoteOn noteOn, int x, int y) {
			super(noteOn);
			this.fromX = x;
			this.toX = x;
			this.y = y;
		}
	}