package de.meisterschueler.scorefollower;

import java.util.List;

import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.score.Score;

public class MatchingService {

	public String scoresToIntervalSequence(List<Score> scores) {
		String seq = scoresToPitchSequence(scores);
		return sequenceToIntervalSequence(seq);
	}

	public String scoresToPitchSequence(List<Score> scores) {
		String sequence = new String();
//		for (Score score : scores) {
//			if (!score.isPause()) {
//				sequence += (char) (score.getPitch());
//			}
//		}
		return sequence;
	}

	public String midiEventsToIntervalSequence(List<MidiPair> notes) {
		String seq = midiEventsToPitchSequence(notes);
		return sequenceToIntervalSequence(seq);
	}

	public String midiEventsToPitchSequence(List<MidiPair> notes) {
		String sequence = new String();
		for (MidiPair event : notes) {
			char value = (char) event.getNoteOn().getNote();
			sequence += value;
		}
		return sequence;
	}

	public String midiEventsToPressedSequence(List<MidiPair> notes) {
		String sequence = new String();
		for (MidiPair event : notes) {
			if (event.getNoteOff() != null) {
				sequence += ".";
			} else {
				sequence += "D";
			}
		}
		return sequence;
	}

	private String sequenceToIntervalSequence(String seq) {
		final int intervalOffset = 128;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < seq.length(); i++) {
			sb.append((char) (seq.charAt(i) - seq.charAt(i - 1) + intervalOffset));
		}
		return sb.toString();
	}

	public void matchPitch(MatchingItem item) {
		String scorePitchSequence = item.getScorePitchSequence();
		String notePitchSequence = item.getNotePitchSequence();

		String pitchAlignment = StringMatcher.getAlignments(scorePitchSequence, notePitchSequence);
		item.setPitchAlignment(pitchAlignment);
	}

	public void matchInterval(MatchingItem item) {
		String scoreIntervalSequence = item.getScoreIntervalSequence();
		String noteIntervalSequence = item.getNoteIntervalSequence();

		String intervalAlignment = StringMatcher.getAlignments(scoreIntervalSequence, noteIntervalSequence);
		item.setIntervalAlignment(intervalAlignment);
	}

//	public void matchPitchPrunned(MatchingItem item) {
//		int saveRegion = item.getSaveRegion();
//		String saveAlignment = item.getPitchAlignment().substring(0, saveRegion);
//
//		String prunnedScorePitchSequence = item.getScorePitchSequence().substring(saveAlignment.replaceAll("[i]", "").length());
//		String prunnedNotePitchSequence = item.getNotePitchSequence().substring(saveAlignment.replaceAll("[d]", "").length());
//
//		String prunnedAlignment = StringMatcher.getAlignments(prunnedScorePitchSequence, prunnedNotePitchSequence);
//
//		item.setPitchAlignment(saveAlignment + prunnedAlignment);
//	}
}
