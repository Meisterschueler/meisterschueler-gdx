package de.meisterschueler.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chordfinder {

	static private class Interval {

		public Interval(String name, int semitones) {
			super();
			this.name = name;
			this.semitones = semitones;
		}

		String name;
		int semitones;
	}

	static private class Chord {

		public Chord(String name, int[] notes) {
			super();
			this.name = name;
			this.notes = notes;
			this.steps = new int[notes.length - 1];
			for (int i = 1; i < notes.length; i++) {
				steps[i - 1] = notes[i] - notes[i - 1];
			}
		}

		String name;
		int notes[];
		int steps[];
	}

	private static ArrayList<Interval> intervals = new ArrayList<Interval>();
	private static ArrayList<Chord> chords = new ArrayList<Chord>();
	private static List<String> keys = Arrays.asList("C", "C#", "D", "Eb", "E", "F", "F#", "G", "G#", "A", "Bb", "B");
	private static List<String> inversions = Arrays.asList("1st Inv", "2nd Inv", "3rd Inv");

	static {
		// ----- Intervals -----
		intervals.add(new Interval("Perfect unison", 0));
		intervals.add(new Interval("Minor second", 1));
		intervals.add(new Interval("Major second", 2));
		intervals.add(new Interval("Minor third", 3));
		intervals.add(new Interval("Major third", 4));
		intervals.add(new Interval("Perfect fourth", 5));
		intervals.add(new Interval("Tritone", 6));
		intervals.add(new Interval("Perfect fifth", 7));
		intervals.add(new Interval("Minor sixth", 8));
		intervals.add(new Interval("Major sixth", 9));
		intervals.add(new Interval("Minor seventh", 10));
		intervals.add(new Interval("Major seventh", 11));
		intervals.add(new Interval("Perfect octave", 12));

		// ----- Accords -----
		// Major
		chords.add(new Chord("", new int[] {0,4,7}));					// maj, major {c1,e,g}
		chords.add(new Chord("2", new int[] {0,2,7}));					// sus2 {c1,d,g}
		chords.add(new Chord("4", new int[] {0,5,7}));					// sus4, m(sus4) {c1,f,g}
		chords.add(new Chord("(b5)", new int[] {0,4,6}));				// - {c1,e,g&}
		chords.add(new Chord("6", new int[] {0,4,7,9}));				// add6, maj6, add23 {c1,e,g,a}
		chords.add(new Chord("maj7", new int[] {0,4,7,11}));			// j7, M7, Δ {c1,e,g,b}
		chords.add(new Chord("maj7(b5)", new int[] {0,4,6,11}));		// - {c1,e,g&,b}
		chords.add(new Chord("maj7(#5)", new int[] {0,4,8,11}));		// - {c1,e,g#,b}
		chords.add(new Chord("add9", new int[] {0,4,7,14}));			// add2 {c1,e,g,d2}
		chords.add(new Chord("add21", new int[] {0,4,7,17}));			// add4 {c1,e,g,f2}
		chords.add(new Chord("6/9", new int[] {0,4,7,9,14}));			// maj6/9 {c1,e,g,a,d2}
		chords.add(new Chord("maj7(b9)", new int[] {0,4,7,11,13}));		// - {c1,e,g,b,d&2}
		chords.add(new Chord("maj7(#9)", new int[] {0,4,7,11,15}));		// - {c1,e,g,b,d#2}
		chords.add(new Chord("maj7/#11", new int[] {0,4,7,11,18}));		// - {c1,e,g,b,f#2}
		chords.add(new Chord("maj7/13", new int[] {0,4,7,11,21}));		// - {c1,e,g,b,a2}
		chords.add(new Chord("maj9", new int[] {0,4,7,11,14}));			// maj7/9 {c1,e,g,b,d2}
		chords.add(new Chord("6/9/#11", new int[] {0,3,7,9,14,18}));	// - {c1,e&,g,a,d2,f#}
		chords.add(new Chord("maj7(#9)11", new int[] {0,4,7,11,15,17})); // - {c1,e,g,b,d#2,f}
		chords.add(new Chord("maj9/13", new int[] {0,4,7,11,14,21}));	// maj7/9/13 {c1,e,g,b,d2,a}
		chords.add(new Chord("maj11", new int[] {0,4,7,11,14,17}));		// maj7/9/11, maj9/11 {c1,e,g,b,d2,f}

		// Minor
		chords.add(new Chord("m", new int[] {0,3,7}));					// min, minor, moll {c1,e&,g}
		chords.add(new Chord("m6", new int[] {0,3,7,9}));				// min6 {c1,e&,g,a}
		chords.add(new Chord("m7", new int[] {0,3,7,10}));				// -7, mi7, min7 {c1,e&,g,b&}
		chords.add(new Chord("m(maj7)", new int[] {0,3,7,11}));			// m(j7) {c1,e&,g,b}
		chords.add(new Chord("m(add9)", new int[] {0,3,7,14}));			// m(add2) {c1,e&,g,d2}
		chords.add(new Chord("m(add21)", new int[] {0,3,7,17}));		// m(add4) {c1,e&,g,f2}
		chords.add(new Chord("m6/9", new int[] {0,3,7,9,14}));			// min6/9 {c1,e&,g,a,d2}
		chords.add(new Chord("m7/11", new int[] {0,3,7,10,17}));		// min7/11 {c1,e&,g,b&,f2}
		chords.add(new Chord("m7/11(b5)", new int[] {0,3,6,10,17}));	// min7/11(b5) {c1,e&,g&,b&,f2}
		chords.add(new Chord("m7/b13", new int[] {0,3,7,10,20}));		// min7/b13 {c1,e&,g,b&,a&2}
		chords.add(new Chord("m7/13", new int[] {0,3,7,10,21}));		// min7/13 {c1,e&,g,b&,a2}
		chords.add(new Chord("m(maj9)", new int[] {0,3,7,11,14}));		// m(j9) {c1,e&,g,b,d2}
		chords.add(new Chord("m9", new int[] {0,3,7,10,14}));			// min9 {c1,e&,g,b&,d}
		chords.add(new Chord("m6/9/11", new int[] {0,3,7,9,14,17}));	// min6/9/11 {c1,e&,g,a,d2,f}
		chords.add(new Chord("m11", new int[] {0,3,7,10,14,17}));		// min11, m7/9/11, m9/11 {c1,e&,g,b&,d2,f}
		chords.add(new Chord("m13", new int[] {0,3,7,10,14,17,21}));	// min13 {c1,e&,g,b&,d2,f,a}

		// Dominant-Sept
		chords.add(new Chord("7", new int[] {0,4,7,10}));				// dom7 {c1,e,g,b&}
		chords.add(new Chord("7/4", new int[] {0,5,7,10}));				// 7sus4 {c1,f,g,b&}
		chords.add(new Chord("7(b5)", new int[] {0,4,6,10}));			// Ø {c1,e,g&,b&}
		chords.add(new Chord("7(#5)", new int[] {0,4,8,10}));			// - {c1,e,g#,b&}
		chords.add(new Chord("7(b9)", new int[] {0,4,7,10,13}));		// dom(b9) {c1,e,g,b&,d&2}
		chords.add(new Chord("7(b9)4", new int[] {0,5,7,10,13}));		// - {c1,f,g,b&,d&2}
		chords.add(new Chord("7(#9)", new int[] {0,4,7,10,15}));		// dom(#9) {c1,e,g,b&,d#2}
		chords.add(new Chord("7/11", new int[] {0,4,7,10,17}));			// - {c1,e,g,b&,f2}
		chords.add(new Chord("7/#11", new int[] {0,4,7,10,18}));		// - {c1,e,g,b&,f#2}
		chords.add(new Chord("7/b13", new int[] {0,4,7,10,20}));		// - {c1,e,g,b&,a&2}
		chords.add(new Chord("7/13", new int[] {0,4,7,10,21}));			// - {c1,e,g,b&,a2}
		chords.add(new Chord("9", new int[] {0,4,7,10,14}));			// dom9, 7/9 {c1,e,g,b&,d2}
		chords.add(new Chord("9/4", new int[] {0,5,7,10,14}));			// 9sus4, 11/4, 11sus4 {c1,f,g,b&,d2}
		chords.add(new Chord("9(b5)", new int[] {0,4,6,10,14}));		// 7/9(b5), 9(#11) {c1,e,g&,b&,d2}
		chords.add(new Chord("9(#5)", new int[] {0,4,8,10,14}));		// 7/9(#5) {c1,e,g#,b&,d2}
		chords.add(new Chord("7(b9)#11", new int[] {0,4,7,10,13,18}));	// - {c1,e,g,b&,d&2,f#}
		chords.add(new Chord("7(b9)b13", new int[] {0,4,7,10,13,20}));	// - {c1,e,g,b&,d&2,a&}
		chords.add(new Chord("7(b9)13", new int[] {0,4,7,10,13,21}));	// - {c1,e,g,b&,d&2,a}
		chords.add(new Chord("7(#9)#11", new int[] {0,4,7,10,15,18}));	// - {c1,e,g,b&,d#2,f#}
		chords.add(new Chord("7(#9)b13", new int[] {0,4,7,10,15,20}));	// - {c1,e,g,b&,d#2,a&}
		chords.add(new Chord("9/#11", new int[] {0,4,7,10,14,18}));		// - {c1,e,g,b&,d2,f#}
		chords.add(new Chord("9/b13", new int[] {0,4,7,10,14,20}));		// - {c1,e,g,b&,d2,a&}
		chords.add(new Chord("9/13", new int[] {0,4,7,10,14,21}));		// 9/6, 7/9/13 {c1,e,g,b&,d2,a}
		chords.add(new Chord("13/4", new int[] {0,5,7,10,14,21}));		// 13sus4, 9/13sus4 {c1,f,g,b&,d2,a}
		chords.add(new Chord("11", new int[] {0,4,7,10,14,17}));		// dom11 {c1,e,g,b&,d2,f}
		chords.add(new Chord("11(b9)", new int[] {0,4,7,10,13,17}));	// - {c1,e,g,b&,d&2,f}
		chords.add(new Chord("11(#9)", new int[] {0,4,7,10,15,17}));	// - {c1,e,g,b&,d#2,f}
		chords.add(new Chord("7(#9)#11", new int[] {0,4,7,10,15,18}));	// - {c1,e,g,b&,d#2,f#}
		chords.add(new Chord("9/#11/13", new int[] {0,4,7,10,14,18,21})); // - {c1,e,g,b&,d2,f#,a}
		chords.add(new Chord("13", new int[] {0,4,7,10,14,17,21}));		// dom13 {c1,e,g,b&,d2,f,a}
		chords.add(new Chord("13(b9)", new int[] {0,4,7,10,13,17,21}));	// - {c1,e,g,b&,d&2,f,a}
		chords.add(new Chord("13(#9)", new int[] {0,4,7,10,15,17,21}));	// - {c1,e,g,b&,d#2,f,a}

		// Vermindert
		chords.add(new Chord("°", new int[] {0,3,6}));					// dim, verm, m(b5) {c1,e&,g&}
		chords.add(new Chord("°7", new int[] {0,3,6,9}));				// - {c1,e&,g&,b&&}
		chords.add(new Chord("°7/b13", new int[] {0,3,6,9,14}));		// - {c1,e&,g&,b&&,d2}

		// Halb-Vermindert
		chords.add(new Chord("m7(b5)", new int[] {0,3,6,10}));			// mØ, dim7, min7(b5) {c1,e&,g&,b&}

		// Übermäßig
		chords.add(new Chord("+", new int[] {0,4,8}));					// (#5), aug, Überm {c1,e,g#}

		// Powerchords
		chords.add(new Chord("1-b3-x", new int[] {0,3}));				// - {c1,e&}
		chords.add(new Chord("1-3-x", new int[] {0,4}));				// - {c1,e}
		chords.add(new Chord("1-4-x", new int[] {0,5}));				// - {c1,f}
		chords.add(new Chord("1-x-b5", new int[] {0,6}));				// - {c1,g&}
		chords.add(new Chord("5", new int[] {0,7}));					// 1-x-5 {c1,g}
		chords.add(new Chord("1-x-6", new int[] {0,9}));				// - {c1,a}
		chords.add(new Chord("1-x-b7", new int[] {0,10}));				// - {c1,b&}
		chords.add(new Chord("1-x-7", new int[] {0,11}));				// - {c1,b}

		calcInversions();
	}

	public static String getIntervalName(int[] notes) {
		int semitones = notes[1] - notes[0];
		for (int i = 0; i < intervals.size(); i++) {
			Interval interval = intervals.get(i);
			if (interval.semitones == semitones) {
				return interval.name;
			}
		}
		return null;
	}

	private static void calcInversions() {
		List<Chord> invertedChords = new ArrayList<Chord>();
		for (Chord chord : chords) {
			int[] notes = chord.notes;
			if (chord.notes[notes.length-1] < 12) {

				for (int i = 1; i < notes.length; i++) {
					// copy notes to invertedNotes and rotate it for i steps
					int[] invertedNotes = new int[notes.length];
					for (int j = 0; j < notes.length; j++) {
						invertedNotes[j] = notes[(j+i) % notes.length] + ((j+i)>=notes.length?12:0);
					}

					Chord invertedChord = new Chord(chord.name + " " + inversions.get(i-1), invertedNotes);
					invertedChords.add(invertedChord);
				}
			}
		}

		chords.addAll(invertedChords);
	}

	public static String getChordName(int[] notes) {
		if (notes.length == 0) {
			return "?";
		}

		int steps[] = new int[notes.length - 1];
		for (int i = 1; i < notes.length; i++) {
			steps[i - 1] = notes[i] - notes[i - 1];
		}

		Chord chord = getChord(steps);
		if (chord != null) {
			String keyName = keys.get((notes[0] - chord.notes[0]) % 12);
			return keyName + chord.name;			
		} 
		return keys.get(notes[0] % 12) + " ?";
	}

	private static Chord getChord(int[] steps) {
		for (int i = 0; i < chords.size(); i++) {
			Chord chord = chords.get(i);
			if (Arrays.equals(chord.steps, steps)) {
				return chords.get(i);
			}
		}
		return null;
	}
}
