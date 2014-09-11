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
	public void submitScoreGPGS(int score) {
		System.out.println("GPGS: submitScoreGPGS");
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		System.out.println("GPGS: submitScoreGPGS for " + achievementId);
	}

	@Override
	public void getLeaderboardGPGS() {
		System.out.println("GPGS: getLeaderboardGPGS");
	}

	@Override
	public void getAchievementsGPGS() {
		System.out.println("GPGS: getAchievementsGPGS");
	}

}
