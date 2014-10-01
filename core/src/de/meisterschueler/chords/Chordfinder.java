package de.meisterschueler.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chordfinder {

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

	private static ArrayList<Chord> chords = new ArrayList<Chord>();
	private static List<String> keys = Arrays.asList("C", "C#", "D", "Eb", "E", "F", "F#", "G", "G#", "A", "Bb", "B");

	static {
		// ----- Akkorde -----
		// Dur
		chords.add(new Chord("", new int[] { 0, 4, 7 })); // maj, major
		chords.add(new Chord(" 1. Umkehrung", new int[] { 4, 7, 12 }));
		chords.add(new Chord(" 2. Umkehrung", new int[] { 7, 12, 16 }));

		chords.add(new Chord("2", new int[] { 0, 2, 7 })); // sus2
		chords.add(new Chord("4", new int[] { 0, 5, 7 })); // sus4, m(sus4)
		chords.add(new Chord("(b5)", new int[] { 0, 4, 6 })); // -
		chords.add(new Chord("6", new int[] { 0, 4, 7, 9 })); // add6, maj6, add23
		chords.add(new Chord("maj7", new int[] { 0, 4, 7, 11 })); // j7, M7, ��
		chords.add(new Chord("maj7(b5)", new int[] { 0, 4, 6, 11 })); // -
		chords.add(new Chord("maj7(#5)", new int[] { 0, 4, 8, 11 })); // -
		chords.add(new Chord("add9", new int[] { 0, 4, 7, 14 })); // add2
		chords.add(new Chord("add21", new int[] { 0, 4, 7, 17 })); // add4
		chords.add(new Chord("6/9", new int[] { 0, 4, 7, 9, 14 })); // maj6/9
		chords.add(new Chord("maj7(b9)", new int[] { 0, 4, 7, 11, 13 })); // -
		chords.add(new Chord("maj7(#9)", new int[] { 0, 4, 7, 11, 15 })); // -
		chords.add(new Chord("maj7/#11", new int[] { 0, 4, 7, 11, 18 })); // -
		chords.add(new Chord("maj7/13", new int[] { 0, 4, 7, 11, 21 })); // -
		chords.add(new Chord("maj9", new int[] { 0, 4, 7, 11, 14 })); // maj7/9
		chords.add(new Chord("6/9/#11", new int[] { 0, 3, 7, 9, 14, 18 })); // -
		chords.add(new Chord("maj7(#9)11", new int[] { 0, 4, 7 })); // -
		chords.add(new Chord("maj9/13", new int[] { 0, 4, 7, 11, 14, 21 })); // maj7/9/13
		chords.add(new Chord("maj11", new int[] { 0, 4, 7, 11, 14, 17 })); // maj7/9/11, maj9/11

		// Moll
		chords.add(new Chord("m", new int[] { 0, 3, 7 })); // min, minor, moll
		chords.add(new Chord("m6", new int[] { 0, 3, 7, 9 })); // min6
		chords.add(new Chord("m7", new int[] { 0, 3, 7, 10 })); // -7, mi7, min7
		chords.add(new Chord("m(maj7)", new int[] { 0, 3, 7, 11 })); // m(j7)
		chords.add(new Chord("m(add9)", new int[] { 0, 3, 7, 14 })); // m(add2)
		chords.add(new Chord("m(add21)", new int[] { 0, 3, 7, 17 })); // m(add4)
	}

	public static String getChordName(int[] notes) {
		int steps[] = new int[notes.length - 1];
		for (int i = 1; i < notes.length; i++) {
			steps[i - 1] = notes[i] - notes[i - 1];
			System.out.println(notes[i] - notes[i - 1]);
		}

		Chord chord = getChord(steps);
		String keyName = keys.get((notes[0] - chord.notes[0]) % 12);

		return keyName + chord.name;
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
