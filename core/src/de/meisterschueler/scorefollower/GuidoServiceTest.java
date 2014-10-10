package de.meisterschueler.scorefollower;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Test;

import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.score.Finger;
import de.meisterschueler.basic.score.Score;

public class GuidoServiceTest {

	private GuidoService guidoService = new GuidoService();

	@Test
	public void gmnToScorePitchTest() throws Exception {
		assertEquals(60, guidoService.gmnToScore("c").getPitch());
		assertEquals(60, guidoService.gmnToScore("c1").getPitch());
		assertEquals(36, guidoService.gmnToScore("c-1").getPitch());
		assertEquals(84, guidoService.gmnToScore("c3").getPitch());
		
		assertEquals(61, guidoService.gmnToScore("c#1").getPitch());
		assertEquals(62, guidoService.gmnToScore("c##1").getPitch());

		assertEquals(59, guidoService.gmnToScore("c&1").getPitch());
		assertEquals(58, guidoService.gmnToScore("c&&1").getPitch());
	}
	
	@Test
	public void gmnToScoreMeasureTest() throws Exception {
		assertEquals( new Fraction(1, 4), guidoService.gmnToScore("c").getMeasure() );
		assertEquals( new Fraction(3, 8), guidoService.gmnToScore("c.").getMeasure() );
		assertEquals( new Fraction(7, 16), guidoService.gmnToScore("c..").getMeasure() );
		assertEquals( new Fraction(15, 32), guidoService.gmnToScore("c...").getMeasure() );
	}
	
	@Test
	public void gmnToScorePauseTest() throws Exception {
		assertEquals( guidoService.gmnToScore("_"), Score.PAUSE );
	}
	
	@Test
	public void gmnToScoresTest() {
		List<Score> scores = guidoService.gmnToScores( "c-1 c0 {d,f#,a}" );
	    assertEquals( 5, scores.size()); 
	    assertEquals( 36, scores.get(0).getPitch());
	    assertEquals( 48, scores.get(1).getPitch());
	    assertEquals( 50, scores.get(2).getPitch());
	    assertEquals( 54, scores.get(3).getPitch());
	    assertEquals( 57, scores.get(4).getPitch());
	    
	    scores = guidoService.gmnToScores( "{c1,e,g,a&&,b,d2,f2,g##2}" );
	    assertEquals( 8, scores.size() );
	    assertEquals( 60, scores.get(0).getPitch() );
	    assertEquals( 64, scores.get(1).getPitch() );
	    assertEquals( 67, scores.get(2).getPitch() );
	    assertEquals( 67, scores.get(3).getPitch() );
	    assertEquals( 71, scores.get(4).getPitch() );
	    assertEquals( 74, scores.get(5).getPitch() );
	    assertEquals( 77, scores.get(6).getPitch() );
	    assertEquals( 81, scores.get(7).getPitch() );
	}
	
	@Test
	public void gmnToScoresPositionTest() {
		List<Score> scores = guidoService.gmnToScores( "{c/8,e,g} {d/4,f}" );
		assertEquals( 5, scores.size() );
		assertEquals( new Fraction(0,1), scores.get(0).getPosition() );
		assertEquals( new Fraction(0,1), scores.get(1).getPosition() );
		assertEquals( new Fraction(0,1), scores.get(2).getPosition() );
		assertEquals( new Fraction(1,8), scores.get(3).getPosition() );
		assertEquals( new Fraction(1,8), scores.get(4).getPosition() );
	}
	
	@Test
	public void gmnToScoresPositionTest2() {
		List<Score> scores = guidoService.gmnToScores( "c/4 d e f/8 g c/4 {e/8,g} {e,g} c/2" );
		assertEquals( 11, scores.size() );
		assertEquals( new Fraction(0,1), scores.get(0).getPosition() );
		assertEquals( new Fraction(1,4), scores.get(1).getPosition() );
		assertEquals( new Fraction(2,4), scores.get(2).getPosition() );
		assertEquals( new Fraction(3,4), scores.get(3).getPosition() );
		assertEquals( new Fraction(7,8), scores.get(4).getPosition() );
		assertEquals( new Fraction(4,4), scores.get(5).getPosition() );
		assertEquals( new Fraction(5,4), scores.get(6).getPosition() );
		assertEquals( new Fraction(5,4), scores.get(7).getPosition() );
		assertEquals( new Fraction(11,8), scores.get(8).getPosition() );
		assertEquals( new Fraction(11,8), scores.get(9).getPosition() );
		assertEquals( new Fraction(6,4), scores.get(10).getPosition() );
	}
	
	@Test
	public void gmnToScoresFingerTest() {
		String gmnString = "c d e {f,g} a";
		int fingers[] = {5, 4, 3, 2, 1, 3};
		List<Score> scores = guidoService.gmnToScores(gmnString, fingers);
		assertEquals( 6, scores.size() );
		assertEquals(Finger.LITTLE, scores.get(0).getFinger());
		assertEquals(Finger.RING, scores.get(1).getFinger());
		assertEquals(Finger.MIDDLE, scores.get(2).getFinger());
		assertEquals(Finger.POINTER, scores.get(3).getFinger());
		assertEquals(Finger.THUMB, scores.get(4).getFinger());
		assertEquals(Finger.MIDDLE, scores.get(5).getFinger());
	}
	
	@Test
	public void gmnToScoresStepsTest() {
		String gmnString = "c d";
		int fingers[] = {5, 4};
		int steps[] = {0, 2, 4};
		List<Score> scores = guidoService.gmnToScores(gmnString, fingers, steps);
		assertEquals( 6, scores.size() );
		assertEquals( 60, scores.get(0).getPitch() );
		assertEquals( 62, scores.get(1).getPitch() );
		assertEquals( 64, scores.get(2).getPitch() );
		assertEquals( 65, scores.get(3).getPitch() );
		assertEquals( 67, scores.get(4).getPitch() );
		assertEquals( 69, scores.get(5).getPitch() );
		
		gmnString = "{c,d}";
		scores = guidoService.gmnToScores(gmnString, fingers, steps);
		assertEquals( 6, scores.size() );
		assertEquals( 60, scores.get(0).getPitch() );
		assertEquals( 62, scores.get(1).getPitch() );
		assertEquals( 64, scores.get(2).getPitch() );
		assertEquals( 65, scores.get(3).getPitch() );
		assertEquals( 67, scores.get(4).getPitch() );
		assertEquals( 69, scores.get(5).getPitch() );
	}
	
	// TODO: Sollten Pausen wirklich eigene Scores bekommen oder einfach entfallen?
	@Test
	public void gmnToScoresPauseTest() {
		List<Score> notes = guidoService.gmnToScores("c/4 {e,g} _ d _ {f,g}");
		assertEquals( 8, notes.size() );
		assertEquals( notes.get(3), Score.PAUSE );
		assertEquals( notes.get(5), Score.PAUSE );
		assertEquals( 1.25, notes.get(7).getPosition().doubleValue(), 1.0 );
	}
	
	@Test
	public void gmnToScoresRepeatTest() {
		String gmn = "\\clef<\"bass\"> \\meter<\"4/4\"> c2 \\repeatBegin d e f g \\repeatEnd a b";
		List<Score> notes = guidoService.gmnToScores(gmn);
		assertEquals( 11, notes.size() );
	}
	
	@Test
	public void gmnToMidiTest() {
		List<MidiPair> notes = guidoService.gmnToMidi( "c/8 e/4 {d/16,f,g}" );
		assertEquals( 5, notes.size() );
		assertEquals( 60, notes.get(0).getNoteOn().getNote() );
		assertEquals( 0, notes.get(0).getNoteOn().getTime() );
		assertEquals( 60, notes.get(0).getNoteOff().getNote() );
		assertEquals( 500, notes.get(0).getNoteOff().getTime() );
		
		assertEquals( 64, notes.get(1).getNoteOn().getNote() );
		assertEquals( 500, notes.get(1).getNoteOn().getTime() );
		assertEquals( 64, notes.get(1).getNoteOff().getNote() );
		assertEquals( 1500, notes.get(1).getNoteOff().getTime() );
		
		assertEquals( 62, notes.get(2).getNoteOn().getNote() );
		assertEquals( 1500, notes.get(2).getNoteOn().getTime() );
		assertEquals( 62, notes.get(2).getNoteOff().getNote() );
		assertEquals( 1750, notes.get(2).getNoteOff().getTime() );
		
		assertEquals( 65, notes.get(3).getNoteOn().getNote() );
		assertEquals( 1500, notes.get(3).getNoteOn().getTime() );
		assertEquals( 65, notes.get(3).getNoteOff().getNote() );
		assertEquals( 1750, notes.get(3).getNoteOff().getTime() );
		
		assertEquals( 67, notes.get(4).getNoteOn().getNote() );
		assertEquals( 1500, notes.get(4).getNoteOn().getTime() );
		assertEquals( 67, notes.get(4).getNoteOff().getNote() );
		assertEquals( 1750, notes.get(4).getNoteOff().getTime() );
	}

	@Test
	public void gmnToMidiPauseTest() {
		List<MidiPair> events = guidoService.gmnToMidi("c {e,g} _ d _ {f,g}");
		assertEquals( 6, events.size() );
	}
	
	@Test
	public void inflateScoresTest() throws Exception {
		String result = guidoService.inflateGmn("c");
		assertTrue(result.equals("c1*1/4"));
		
		result = guidoService.inflateGmn("c d2 e");
		assertTrue(result.equals("c1*1/4 d2*1/4 e2*1/4"));
		
		result = guidoService.inflateGmn("c#-1*1/2.. {d,e,f} g&&1*4/2");
		//assertTrue(result.equals("c#-1*1/2.. {d-1*1/2,e-1*1/2,f-1*1/2} g&&1*4/2"));
		assertTrue(result.equals("c#-1*7/8 {d-1*7/8,e-1*7/8,f-1*7/8} g&&1*2/1"));
	}
	
	@Test
	public void deflateScoresTest() throws Exception {
		String result = guidoService.deflateGmn("c1*1/4");
		assertTrue(result.equals("c"));
		
		result = guidoService.deflateGmn("c1*1/4 d2*1/4 e2*1/4");
		assertTrue(result.equals("c d2 e"));
		
		//result = noteService.deflateGmn("c#-1*7/8 {d-1*7/8,e-1*7/8,f-1*7/8} g&&1*2/1");
		//assertTrue(result.equals("c#-1*1/2.. {d-1*1/2,e-1*1/2,f-1*1/2} g&&1*4/2"));
		//assertTrue(result.equals("c#-1*1/2.. {d,e,f} g&&1*4/2"));
	}
	
	@Test
	public void transposeTest() throws Exception {
		assertEquals( "{d0,f#,a}", guidoService.transposeGmn( "c0", "d0",  "{c0,e,g}" ) );
		assertEquals( "{c#0,e#,g#}", guidoService.transposeGmn( "d&0", "c#0", "{d&0,f,a&}" ) );
		assertEquals( "{g,b&,d&2}", guidoService.transposeGmn( "f#0", "e&1", "{a#0,c#1,e}" ) );
		assertEquals( "{e0,g##}", guidoService.transposeGmn( "c0", "e0",  "{c0,e#}" ) );
		assertEquals( "{f&0,a&&}", guidoService.transposeGmn( "c0", "f&0", "{c0,e&}" ) );
		assertEquals( "f#2 g# a# b c#3", guidoService.transposeGmn( "c0", "f#2", "c0 d e f g" ) );
		assertEquals( "c#-2 {e#,g#}", guidoService.transposeGmn( "c3", "c#1", "c0 {e,g}" ) );
		assertEquals( "c#2 {e#,g#}", guidoService.transposeGmn( "c1", "c#3", "c0 {e,g}" ) );
		
		//assertEquals( "c/2. e/4 g/2 c2 a1 c2/4 a1 g/1", guidoService.transposeGmn( "f#-1", "c0", "f#0/2. a#/4 c#1/2 f# d# f#/4 d# c#/1" ) );
		assertEquals( "c*3/4 e/4 g/2 c2 a1 c2/4 a1 g/1", guidoService.transposeGmn( "f#-1", "c0", "f#0/2. a#/4 c#1/2 f# d# f#/4 d# c#/1" ) );
	}
	
	@Test
	public void scoresToStringTest() {
		List<Score> scores = guidoService.gmnToScores("f {g,b}");
		String string = guidoService.scoresToString(scores);
		assertEquals( 'A', string.charAt(0) );
		assertEquals( 'C', string.charAt(1) );
		assertEquals( 'G', string.charAt(2) );
	}
	
	@Test
	public void chordInversionTest() {
		List<String> chords = guidoService.gmnToInvertedChords("{c0,e,g}");
		assertEquals( "{c0,e,g}", chords.get(0));
		assertEquals( "{e0,g,c1}", chords.get(1));
		assertEquals( "{g0,c1,e}", chords.get(2));
	}
}
