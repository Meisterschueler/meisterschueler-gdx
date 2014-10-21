package de.meisterschueler.basic;

import org.apache.commons.math3.fraction.Fraction;

public class Score implements Comparable<Score> {

	public static final int GUIDO_OCTAVE_OFFSET = -4;
	public static final int NOTES_PER_OCTAVE = 12;	
	public static final int NATURALS_PER_OCTAVE = 7;

	private Finger finger;
	private Fraction measure;

	private Fraction position;

	private int octave;
	private int natural;
	private String accidental;
	private char status;
	private MidiPair midiPair;
	
	private boolean pause;

	public Score() {
		this.natural = 0;
		this.octave = 1-GUIDO_OCTAVE_OFFSET;
		this.accidental = "";
		this.measure = new Fraction(1, 4);

		this.position = new Fraction(0, 4);

		this.finger = Finger.NONE;

		this.status = Status.NONE;
	}

	public Score(Score score) {
		this.natural = score.natural;
		this.octave = score.octave;
		this.accidental = score.accidental;
		this.measure = new Fraction(score.measure.getNumerator(), score.measure.getDenominator());

		this.position = new Fraction(score.position.getNumerator(), score.position.getDenominator());

		this.finger = score.finger;

		this.status = score.status;
	}

	public static char naturalToChar(int natural) {
		String notes = "cdefgab";
		return notes.charAt(natural % 7);
	}

	public static int charToNatural(char c) {
		String notes = "cdefgab";
		return notes.indexOf(c);
	}

	public String toGmn() {
		return naturalToChar(natural) + accidental + (octave+GUIDO_OCTAVE_OFFSET) + "*" + measure.getNumerator() + "/" + measure.getDenominator();
	}

	public String toGmn(Score prevScore) {
		String result = Score.naturalToChar(natural) + accidental;
		if (octave != prevScore.getOctave()) {
			result += (octave + GUIDO_OCTAVE_OFFSET);
		}
		if (measure.doubleValue() != prevScore.getMeasure().doubleValue()) {
			if (measure.getNumerator() != 1) {
				result += "*" + measure.getNumerator() + "/" + measure.getDenominator();
			} else {
				result += "/" + measure.getDenominator();
			}
		}
		return result;
	}

	public int getNatural() {
		return natural;
	}

	public void setNatural(int natural) {
		this.natural = natural;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}

	public String getAccidental() {
		return accidental;
	}

	public void setAccidental(String accidental) {
		this.accidental = accidental;
	}

	public Fraction getMeasure() {
		return measure;
	}

	public void setMeasure(Fraction measure) {
		this.measure = measure;
	}

	public int getPitch() {
		String notes = "c d ef g a b";
		int pitch = notes.indexOf(naturalToChar(natural));

		if ( accidental != null ) {
			if (accidental.equals("&")) pitch--;
			if (accidental.equals("&&")) pitch=pitch-2;
			if (accidental.equals("#")) pitch++;
			if (accidental.equals("##")) pitch=pitch+2;
		}

		pitch += octave*12;
		return pitch;
	}

	public Finger getFinger() {
		return finger;
	}

	public void setFinger(Finger finger) {
		this.finger = finger;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public MidiPair getNote() {
		return midiPair;
	}

	public void setNote(MidiPair midiPair) {
		this.midiPair = midiPair;
	}

	public Fraction getPosition() {
		return position;
	}

	public void setPosition(Fraction position) {
		this.position = position;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	@Override
	public int compareTo(Score other) {
		if (other.getPosition().doubleValue() > position.doubleValue()) {
			return -1;
		} else if (other.getPosition().doubleValue() < position.doubleValue()) {
			return 1;
		} else {
			if (other.getPitch() > getPitch()) {
				return -1;
			} else if (other.getPitch() < getPitch()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
