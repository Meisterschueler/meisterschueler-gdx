package de.meisterschueler.scorefollower;

import java.util.List;

import de.meisterschueler.basic.score.Score;

public class Song {
	private String name;
	private long id;
	private String description;
	private String meter;
	private List<Score> leftScores;
	private List<Score> rightScores;
	private Key key;

	void HanonSong() {
		this.meter = "2/4";
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long Id) {
		this.id = Id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLeftScores(List<Score> leftScores) {
		this.leftScores = leftScores;
	}

	public void setRightScores(List<Score> rightScores) {
		this.rightScores = rightScores;
	}

	public void setMeter(String meter) {
		this.meter = meter;
	}

	// TODO move it to the service
	public String getLeftScorePitchSequence() {
		StringBuffer sb = new StringBuffer();
		for (Score score : leftScores) {
			sb.append(score.toString());
		}
		return sb.toString();
	}

	public MatchingItem toMatchingItem() {
		MatchingItem result = new MatchingItem();
		result.setScorePitchSequence(getLeftScorePitchSequence());
		return result;
	}

	public void setKey(Key key) {
		this.key = key;
	}

}