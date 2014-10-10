package de.meisterschueler.scorefollower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.fraction.Fraction;

import de.meisterschueler.basic.score.Score;


public class ScoreService {

	public List<Score> merge(List<Score> rightScores, List<Score> leftScores) {
		List<Score> result = new ArrayList<Score>();
		result.addAll(rightScores);
		result.addAll(leftScores);
		
		Score[] resultArray = new Score[result.size()];
		result.toArray(resultArray);
		
		Arrays.sort(resultArray);
		
		return new ArrayList<Score>(Arrays.asList(resultArray));
	}

	public List<Score> removePause(List<Score> scores) {
		Iterator<Score> scoreIt = scores.iterator();
		while (scoreIt.hasNext()) {
			Score score = scoreIt.next();
			if (score == Score.PAUSE) {
				scoreIt.remove();
			}
		}
		
		return scores;
	}

	public List<Score> shiftScores(List<Score> scores, Fraction position) {
		for (Score score : scores) {
			score.setPosition(new Fraction(score.getPosition().getNumerator(), score.getPosition().getDenominator()).add(position));
		}
		return scores;
	}

	public List<Score> concat(List<Score> scores1, List<Score> scores2) {
		Score lastScore1 = scores1.get(scores1.size()-1);
		List<Score> result = scores1;
		result.addAll(shiftScores(scores2, lastScore1.getPosition().add(lastScore1.getMeasure())));
		return result;
	}
}
