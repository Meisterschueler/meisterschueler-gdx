import static org.junit.Assert.*;

import org.junit.Test;

public class ChordfinderTest {

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
	public void intervalTest() {
		int quintNotes[] = {0, 7};
		String quintName = Chordfinder.getIntervalName(quintNotes);
		assertEquals("Perfect fifth", quintName);
	}
}
