package de.meisterschueler.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.meisterschueler.chords.Chordfinder;

public class ChordfinderTest {
	
	@Test
	public void invalidChordTest() {
		int emptyNotes[] = {};
		String emptyName = Chordfinder.getChordName(emptyNotes);
		assertEquals("?", emptyName);
	}

	@Test
	public void chordTest() {
		int cMajorNotes[] = { 48, 52, 55 };
		String cMajorName = Chordfinder.getChordName(cMajorNotes);
		assertEquals("C", cMajorName);

		int fSharpMinorNotes[] = { 54, 57, 61 };
		String fSharpMinorName = Chordfinder.getChordName(fSharpMinorNotes);
		assertEquals("F#m", fSharpMinorName);

		int lowestChordNotes[] = { 0, 4, 7 };
		String lowestChordName = Chordfinder.getChordName(lowestChordNotes);
		assertEquals("C", lowestChordName);

		int highestChordNotes[] = { 120, 124, 127 };
		String highestChordName = Chordfinder.getChordName(highestChordNotes);
		assertEquals("C", highestChordName);
	}

	@Test
	public void inversionTest() {
		int cMajorFirstInversionNotes[] = { 52, 55, 60 };
		String cMajorFirstInversionName = Chordfinder.getChordName(cMajorFirstInversionNotes);
		assertEquals("C 1st Inv", cMajorFirstInversionName);

		int cMajorSecondInversionNotes[] = { 55, 60, 64 };
		String cMajorSecondInversionName = Chordfinder.getChordName(cMajorSecondInversionNotes);
		assertEquals("C 2nd Inv", cMajorSecondInversionName);

		int g7MajorThirdInversionNotes[] = { 54, 55, 59, 62 };
		String g7MajorThirdInversionName = Chordfinder.getChordName(g7MajorThirdInversionNotes);
		assertEquals("Gmaj7 3rd Inv", g7MajorThirdInversionName);
	}

	@Test
	public void intervalTest() {
		int quintNotes[] = { 0, 7 };
		String quintName = Chordfinder.getIntervalName(quintNotes);
		assertEquals("Perfect fifth", quintName);
	}
}
