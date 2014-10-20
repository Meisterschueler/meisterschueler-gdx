package de.meisterschueler.scorefollower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.fraction.Fraction;

import de.meisterschueler.basic.MidiPair;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.basic.score.Finger;
import de.meisterschueler.basic.score.Score;

public class GuidoService {

	private static String REPEAT_PATTERN = "\\\\repeatBegin([a-g0-9\\-\\*\\/,\\{\\}#&\\s]*)\\\\repeatEnd";

	// ^([a-g_])(#|##|&|&&)?(-?[0-9]+)?(\*[0-9]+)?(\/[0-9]+)?(\.{1,3})?$
	private static String NOTE_PATTERN = new String("^([a-g_])(#|##|&|&&)?(-?[0-9]+)?(\\*[0-9]+)?(\\/[0-9]+)?(\\.{1,3})?$");

	// ^\{(\S+)\}$
	private static String CHORD_PATTERN = new String("^\\{(\\S+)\\}$");

	// ^\\([a-zA-Z]+)(\<(.*)\>)?(\(.*\))?$
	private static String TAG_PATTERN = new String("^\\\\([a-zA-Z]+)(\\<(.*)\\>)?(\\(.*\\))?$");

	private ScoreService scoreService = new ScoreService();

	private interface AbstractCommand {
		public String foundChord(String[] gmnStrings);

		public String foundTag(String gmnString);

		public String foundNote(String gmnString);
	}

	private class InflateCommand implements AbstractCommand {
		public Score prevScore = new Score();

		@Override
		public String foundNote(String gmnNote) {
			Score score = gmnToScore(gmnNote, prevScore);
			prevScore = score;
			return score.toGmn();
		}

		@Override
		public String foundChord(String[] gmnStrings) {
			List<String> parts = new ArrayList<String>();
			for (String gmnNote : gmnStrings) {
				Score score = gmnToScore(gmnNote, prevScore);
				prevScore = score;
				parts.add(score.toGmn());
			}
			return "{" + StringUtils.join(parts, ",") + "}";
		}

		@Override
		public String foundTag(String gmnTag) {
			if (gmnTag.equals("clef")) {
				// ignore it
			} else {
				// throw new Exception(tagMatcher.group(1) +
				// " not handled yet in string: " + tagMatcher.group(0));
			}
			return gmnTag;
		}
	}

	private class DeflateCommand implements AbstractCommand {
		public Score prevScore = new Score();

		@Override
		public String foundNote(String gmnNote) {
			Score score = gmnToScore(gmnNote, prevScore);
			String result = score.toGmn(prevScore);
			prevScore = score;
			return result;
		}

		@Override
		public String foundChord(String[] gmnStrings) {
			List<String> parts = new ArrayList<String>();
			for (String gmnNote : gmnStrings) {
				Score score = gmnToScore(gmnNote, prevScore);
				parts.add(score.toGmn(prevScore));
				prevScore = score;
			}
			return "{" + StringUtils.join(parts, ",") + "}";
		}

		@Override
		public String foundTag(String gmnTag) {
			if (gmnTag.equals("clef")) {
				// ignore it
			} else {
				// throw new Exception(tagMatcher.group(1) +
				// " not handled yet in string: " + tagMatcher.group(0));
			}
			return gmnTag;
		}
	}

	private class TransposeCommand implements AbstractCommand {
		private int natural_delta;
		private int pitch_delta;

		TransposeCommand(String fromGmn, String toGmn) {
			Score from = gmnToScore(fromGmn);
			Score to = gmnToScore(toGmn);
			this.natural_delta = to.getNatural() - from.getNatural();
			this.pitch_delta = to.getPitch() - from.getPitch();
		}

		@Override
		public String foundNote(String gmnNote) {
			return transposeGmn(gmnNote, natural_delta, pitch_delta);
		}

		@Override
		public String foundChord(String[] gmnStrings) {
			List<String> parts = new ArrayList<String>();
			for (String gmnNote : gmnStrings) {
				parts.add(transposeGmn(gmnNote, natural_delta, pitch_delta));
			}
			return "{" + StringUtils.join(parts, ",") + "}";
		}

		@Override
		public String foundTag(String gmnString) {
			if (gmnString.equals("clef")) {
				// ignore it
			} else {
				// throw new Exception(tagMatcher.group(1) +
				// " not handled yet in string: " + tagMatcher.group(0));
			}
			return gmnString;
		}
	}

	public List<Score> gmnToScores(String gmnString) {
		gmnString = gmnConvertRepeats(gmnString);

		List<Score> result = new ArrayList<Score>();

		Pattern notePattern = Pattern.compile(NOTE_PATTERN);
		Pattern chordPattern = Pattern.compile(CHORD_PATTERN);
		Pattern tagPattern = Pattern.compile(TAG_PATTERN);

		Fraction chordPosition = new Fraction(0, 4);

		Score prevScore = new Score();

		for (String part : gmnString.split(" ")) {
			Matcher noteMatcher = notePattern.matcher(part);
			Matcher chordMatcher = chordPattern.matcher(part);
			Matcher tagMatcher = tagPattern.matcher(part);

			if (chordMatcher.find()) {
				Score score = null;
				for (String part2 : chordMatcher.group(1).split(",")) {
					score = gmnToScore(part2, prevScore);
					score.setPosition(chordPosition);
					result.add(score);
					prevScore = score;
				}
				chordPosition = chordPosition.add(score.getMeasure());
			} else if (noteMatcher.find()) {
				Score score = gmnToScore(part, prevScore);
				score.setPosition(chordPosition);
				result.add(score);
				chordPosition = chordPosition.add(score.getMeasure());
				prevScore = score;
			} else if (tagMatcher.find()) {
				if (tagMatcher.group(1).equals("clef")) {
					// ignore it
				} else {
					System.err.println(tagMatcher.group(1) + " not handled yet in string: " + tagMatcher.group(0));
				}
			} else {
				System.err.println("getGmnCode failed for " + part);
			}
		}

		return result;
	}

	public List<Score> gmnToScores(String gmnString, int fingers[]) {
		List<Score> scores = gmnToScores(gmnString);
		scores = addFingersToScores(fingers, scores);
		return scores;
	}

	public List<Score> gmnToScores(String gmnString, int[] fingers, int[] steps) {
		List<Score> scores = gmnToScores(gmnString);
		scores = addFingersToScores(fingers, scores);
		List<Score> result = new ArrayList<Score>();
		Fraction lastPosition = new Fraction(0, 1);
		for (int step : steps) {
			List<Score> transposedScores = transposeScoresByNatural(scores, step);
			List<Score> shiftedScores = scoreService.shiftScores(transposedScores, lastPosition);
			result.addAll(shiftedScores);

			Score lastScore = shiftedScores.get(shiftedScores.size() - 1);
			lastPosition = lastScore.getPosition().add(lastScore.getMeasure());
		}
		return result;
	}

	public Score gmnToScore(String gmnNote) {
		return gmnToScore(gmnNote, new Score());
	}

	public List<MidiPair> gmnToMidi(String gmnString) {
		List<MidiPair> notes = new ArrayList<MidiPair>();
		List<Score> scores = gmnToScores(gmnString);
		long tick = 0;
		int velocity = 50;

		Score oldScore = null;
		long deltaTick = 0;
		Iterator<Score> scoreIt = scores.iterator();
		while (scoreIt.hasNext()) {
			Score score = scoreIt.next();

			if (oldScore != null && oldScore.getPosition() != score.getPosition())
				tick += deltaTick;

			deltaTick = (long) (score.getMeasure().doubleValue() * 4.0 * 1000.0);

			if (score != Score.PAUSE) {
				NoteOn noteOn = new NoteOn(tick, score.getPitch(), velocity);
				NoteOff noteOff = new NoteOff(tick + deltaTick, score.getPitch(), velocity);
				notes.add(new MidiPair(noteOn, noteOff));
			}

			oldScore = score;

		}
		return notes;
	}

	public String inflateGmn(String gmnString) {
		InflateCommand command = new InflateCommand();
		return traverseGmn(gmnString, command);
	}

	public String deflateGmn(String gmnString) {
		DeflateCommand command = new DeflateCommand();
		return traverseGmn(gmnString, command);
	}

	public String transposeGmn(final String fromGmn, final String toGmn, String gmnString) {
		gmnString = inflateGmn(gmnString);

		TransposeCommand command = new TransposeCommand(fromGmn, toGmn);
		gmnString = traverseGmn(gmnString, command);

		gmnString = deflateGmn(gmnString);

		return gmnString;
	}

	public String scoresToString(List<Score> scores) {
		String result = "";
		for (Score score : scores) {
			result += (char) score.getPitch();
		}
		return result;
	}

	public List<String> gmnToInvertedChords(String gmnString) {
		List<String> result = new ArrayList<String>();

		result.add(gmnString);

		gmnString = inflateGmn(gmnString);
		Pattern chordPattern = Pattern.compile(CHORD_PATTERN);
		Matcher chordMatcher = chordPattern.matcher(gmnString);
		if (chordMatcher.find()) {
			List<String> gmnParts = new ArrayList<String>(Arrays.asList(chordMatcher.group(1).split(",")));
			for (int i = 0; i < gmnParts.size() - 1; i++) {
				Score base = gmnToScore(gmnParts.get(0));
				Score top = gmnToScore(gmnParts.get(gmnParts.size() - 1));

				while (base.getPitch() < top.getPitch()) {
					base.setOctave(base.getOctave() + 1);
					base.setNatural(base.getNatural() + 7);
				}

				gmnParts.remove(0);
				gmnParts.add(base.toGmn());
				String temp = "{" + StringUtils.join(gmnParts.toArray(), ",") + "}";
				temp = deflateGmn(temp);
				result.add(temp);
			}
		}

		return result;
	}

	private List<Score> transposeScoresByNatural(List<Score> scores, int step) {
		List<Score> result = new ArrayList<Score>();
		for (Score score : scores) {
			int preNatural = score.getNatural();
			int postNatural = preNatural + step;
			int postOctave = postNatural / Score.NATURALS_PER_OCTAVE;

			String accidental = score.getAccidental();
			Fraction measure = score.getMeasure();
			Fraction position = score.getPosition();
			Finger finger = score.getFinger();
			char status = score.getStatus();

			Score transScore = new Score();
			transScore.setNatural(postNatural);
			transScore.setOctave(postOctave);
			transScore.setAccidental(accidental);
			transScore.setMeasure(new Fraction(measure.getNumerator(), measure.getDenominator()));
			transScore.setPosition(new Fraction(position.getNumerator(), position.getDenominator()));

			transScore.setFinger(finger);
			transScore.setStatus(status);

			result.add(transScore);
		}
		return result;
	}

	private List<Score> addFingersToScores(int[] fingers, List<Score> scores) {
		int f = 0;
		int i = 0;

		if (scores.isEmpty()) {
			return scores;
		}

		Score currentScore = scores.get(i);
		boolean finished = false;
		do {
			currentScore.setFinger(Finger.getFinger(fingers[f % fingers.length]));
			f++;
			if (i < scores.size() - 1) {
				i++;
				currentScore = scores.get(i);
			} else {
				finished = true;
			}
		} while (!finished);

		return scores;
	}

	private String transposeGmn(String gmnNote, int natural_delta, int pitch_delta) {

		Score score = gmnToScore(gmnNote);
		int natural_soll = score.getNatural() + natural_delta;

		Score scoreSoll = new Score();
		scoreSoll.setNatural(natural_soll);
		scoreSoll.setOctave(natural_soll / Score.NATURALS_PER_OCTAVE);

		switch (pitch_delta + (score.getPitch() - scoreSoll.getPitch())) {
		case -2:
			scoreSoll.setAccidental("&&");
			break;
		case -1:
			scoreSoll.setAccidental("&");
			break;
		case 0:
			break;
		case 1:
			scoreSoll.setAccidental("#");
			break;
		case 2:
			scoreSoll.setAccidental("##");
			break;
		default:
			break; // Mach eine Exception
		}

		String result = "" + Score.naturalToChar(scoreSoll.getNatural());
		result += scoreSoll.getAccidental();
		result += (scoreSoll.getOctave() + Score.GUIDO_OCTAVE_OFFSET);
		result += "*" + score.getMeasure().getNumerator() + "/" + score.getMeasure().getDenominator();

		return result;
	}

	private String traverseGmn(String gmnString, AbstractCommand command) {
		List<String> result = new ArrayList<String>();

		Pattern notePattern = Pattern.compile(NOTE_PATTERN);
		Pattern chordPattern = Pattern.compile(CHORD_PATTERN);
		Pattern tagPattern = Pattern.compile(TAG_PATTERN);

		for (String chordPart : gmnString.split(" ")) {
			Matcher noteMatcher = notePattern.matcher(chordPart);
			Matcher chordMatcher = chordPattern.matcher(chordPart);
			Matcher tagMatcher = tagPattern.matcher(chordPart);

			if (chordMatcher.find()) {
				result.add(command.foundChord(chordMatcher.group(1).split(",")));
			} else if (noteMatcher.find()) {
				result.add(command.foundNote(chordPart));
			} else if (tagMatcher.find()) {
				result.add(command.foundTag(tagMatcher.group(1)));
			} else {
				// throw new Exception("getGmnCode failed for " + gmnString);
			}
		}
		return StringUtils.join(result, " ");
	}

	private Fraction getMeasure(String num, String den, String dots, Score prevScore) {
		int numerator = prevScore.getMeasure().getNumerator();
		int denominator = prevScore.getMeasure().getDenominator();

		if (den != null) {
			denominator = Integer.parseInt(den.replace("/", "").replace(".", ""));
			numerator = 1;
		}
		if (num != null) {
			numerator = Integer.parseInt(num.replace("*", ""));
		}

		if (dots != null) {
			switch (dots.length()) {
			case 0:
				break;
			case 1:
				numerator = numerator * (2 + 1);
				denominator = denominator * 2;
				break;
			case 2:
				numerator = numerator * ((2 + 1) * 2 + 1);
				denominator = denominator * 2 * 2;
				break;
			case 3:
				numerator = numerator * (((2 + 1) * 2 + 1) * 2 + 1);
				denominator = denominator * 2 * 2 * 2;
				break;
			}
		}

		return new Fraction(numerator, denominator);
	}

	private Score gmnToScore(String gmnString, Score prevScore) {
		Score score = new Score();

		Pattern pattern = Pattern.compile(NOTE_PATTERN);
		Matcher matcher = pattern.matcher(gmnString);
		if (matcher.find()) {
			if (matcher.group(1).charAt(0) == '_') {
				score = Score.PAUSE;
			} else {

				if (matcher.group(2) != null) {
					score.setAccidental(matcher.group(2));
				}

				if (matcher.group(3) != null) {
					score.setOctave(Integer.parseInt(matcher.group(3)) - Score.GUIDO_OCTAVE_OFFSET);
				} else {
					score.setOctave(prevScore.getOctave());
				}

				score.setNatural(Score.charToNatural(matcher.group(1).charAt(0)) + score.getOctave() * Score.NATURALS_PER_OCTAVE);
			}

			Fraction measure = getMeasure(matcher.group(4), matcher.group(5), matcher.group(6), prevScore);
			score.setMeasure(measure);
		} else {
			// throw new Exception("Not a valid note string: " + gmnString);
		}

		return score;
	}

	public String oneOctaveUp(String gmnString) {
		return transposeGmn("c0", "c1", gmnString);
	}

	private String gmnConvertRepeats(String gmn) {
		Pattern pattern = Pattern.compile(REPEAT_PATTERN);
		Matcher matcher = pattern.matcher(gmn);

		String result = gmn;
		while (matcher.find()) {
			String snippet = matcher.group(0);
			snippet = snippet.substring(12, snippet.length() - 10).trim();
			result = result.replaceFirst(REPEAT_PATTERN, snippet + " " + snippet);
		}
		return result;
	}
}
