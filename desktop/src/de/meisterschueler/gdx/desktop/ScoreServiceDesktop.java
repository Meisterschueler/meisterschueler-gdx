package de.meisterschueler.gdx.desktop;

import de.meisterschueler.gpgs.ScoreService;

public class ScoreServiceDesktop implements ScoreService {

	@Override
	public boolean getSignedInGPGS() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void loginGPGS() {
		System.out.println("GPGS: loginGPGS");
	}

	@Override
	public void submitEventGPGS(String id, int score) {
		System.out.println("GPGS: submitEventGPGS score:" + score + " id:" + id);
	}

	@Override
	public void submitScoreGPGS(String id, int score) {
		System.out.println("GPGS: submitScoreGPGS score:" + score + " id:" + id);
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		System.out.println("GPGS: submitScoreGPGS for " + achievementId);
	}

	@Override
	public void getLeaderboardGPGS(String id) {
		System.out.println("GPGS: getLeaderboardGPGS " + id);
	}

	@Override
	public void getAchievementsGPGS() {
		System.out.println("GPGS: getAchievementsGPGS");
	}

	@Override
	public void submitEventGPGS_chromaticStart() {
		System.out.println("GPGS: submitEventGPGS_chromaticStart");
	}

	@Override
	public void submitScoreGPGS_chromatic(int score) {
		System.out.println("GPGS: submitScoreGPGS_chromatic score:" + score);
	}

	@Override
	public void getLeaderboardGPGS_chromatic() {
		System.out.println("GPGS: getLeaderboardGPGS_chromatic");
	}
}
