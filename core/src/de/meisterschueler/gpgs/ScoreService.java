package de.meisterschueler.gpgs;

public interface ScoreService {
	public boolean getSignedInGPGS();
	public void loginGPGS();
	public void submitScoreGPGS(int score,String id);
	public void unlockAchievementGPGS(String achievementId);
	public void getLeaderboardGPGS(String id);
	public void getAchievementsGPGS();
	
	public void submitScoreGPGS_chromatic(int score);
	public void getLeaderboardGPGS_chromatic();
}