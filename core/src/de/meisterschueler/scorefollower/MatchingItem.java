package de.meisterschueler.scorefollower;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;

import de.meisterschueler.basic.score.Status;

public class MatchingItem {

	private String scorePitchSequence;
	private String notePitchSequence;

	private String scoreIntervalSequence;
	private String noteIntervalSequence;
	
	private String pitchAlignment;
	private String intervalAlignment;

	private int transposition;

	public MatchingItem() {
	}

	// For testing: isFinished, getProgress, getQuality
	public MatchingItem(String pitchAlignment) {
		this.pitchAlignment = pitchAlignment;
	}

	// For testing: getTransposition
	public MatchingItem(String scorePitchSequence, String notePitchSequence, String intervalAlignment) {
		this.scorePitchSequence = scorePitchSequence;
		this.notePitchSequence = notePitchSequence;
		this.intervalAlignment = intervalAlignment;
	}

	public void init() {
		pitchAlignment = "";
		intervalAlignment = "";
	}

	public boolean isFinished() {
		return pitchAlignment.matches("[m]+[i]*$");
	}

	public double getProgress() {
		int playedNotes = StringUtils.countMatches(pitchAlignment.replaceAll("[mw]", "."), ".");
		int pressedNotes = StringUtils.countMatches(pitchAlignment.replaceAll("[MW]", "."), ".");
		int stillToPlayNotes = StringUtils.countMatches(pitchAlignment.replaceAll("[o]", "."), ".");

		return (playedNotes + pressedNotes / 2.0) / (playedNotes + pressedNotes + stillToPlayNotes);
	}

	public double getQuality() {
		String alignment = pitchAlignment;

		int firstHitChord = alignment.replaceAll("[mwi]", ".").indexOf(".");
		int lastHitChord = alignment.replaceAll("[mwi]", ".").lastIndexOf(".");

		int open = StringUtils.countMatches(alignment, String.valueOf(Status.OPEN));
		int played = StringUtils.countMatches(alignment, String.valueOf(Status.MATCH));
		int missed = StringUtils.countMatches(alignment, String.valueOf(Status.DELETED));
		int extra = StringUtils.countMatches(alignment, String.valueOf(Status.INSERT));
		int wrong = StringUtils.countMatches(alignment, String.valueOf(Status.WRONG));

		double rangeFactor = (double) (played + wrong + extra) / (double) (lastHitChord - firstHitChord + 1);
		double progressFactor = (double) (played + wrong) / (double) (played + wrong + extra + open);
		double matchFactor = (double) (played) / (double) (played + wrong + extra);

		double transpositionFactor;
		if (transposition >= 0) {
			transpositionFactor = 1.0 - transposition / 1100.0;
		} else {
			transpositionFactor = 0.99 + transposition / 1100.0;
		}

		double quality = rangeFactor * matchFactor * Math.max(0.9, progressFactor) * transpositionFactor;

		if (Double.isNaN(quality) || Double.isInfinite(quality)) {
			return 0.0;
		} else {
			return quality;
		}
	}

	public int getTransposition() {
		int posAlignment = intervalAlignment.lastIndexOf("mmm");
		if (posAlignment < 0) {
			return 0;
		}

		String goodString = intervalAlignment.substring(0, posAlignment);

		int scorePosition = goodString.replaceAll("i", "").length();
		int notePosition = goodString.replaceAll("d", "").length();

		char scorePitch = scorePitchSequence.charAt(scorePosition);
		char notePitch = notePitchSequence.charAt(notePosition);

		return notePitch - scorePitch;
	}

	public String getScorePitchSequence() {
		return scorePitchSequence;
	}

	public void setScorePitchSequence(String scorePitchSequence) {
		this.scorePitchSequence = scorePitchSequence;
	}

	public String getNotePitchSequence() {
		return notePitchSequence;
	}

	public void setNotePitchSequence(String notePitchSequence) {
		this.notePitchSequence = notePitchSequence;
	}

	public String getScoreIntervalSequence() {
		return scoreIntervalSequence;
	}

	public void setScoreIntervalSequence(String scoreIntervalSequence) {
		this.scoreIntervalSequence = scoreIntervalSequence;
	}

	public String getNoteIntervalSequence() {
		return noteIntervalSequence;
	}

	public void setNoteIntervalSequence(String noteIntervalSequence) {
		this.noteIntervalSequence = noteIntervalSequence;
	}

	public void setPitchAlignment(String pitchAlignment) {
		this.pitchAlignment = pitchAlignment;
	}

	public void setIntervalAlignment(String intervalAlignment) {
		this.intervalAlignment = intervalAlignment;
	}

}
