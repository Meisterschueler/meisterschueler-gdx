package de.meisterschueler.gpgs;

public interface ScoreService {
	public boolean getSignedInGPGS();
	public void loginGPGS();
	public void submitEventGPGS(String id, int score);
	public void submitScoreGPGS(String id, int score);
	public void unlockAchievementGPGS(String id);
	public void getLeaderboardGPGS(String id);
	public void getAchievementsGPGS();

	public void submitEventGPGS_chromaticStart();
	public void submitScoreGPGS_chromatic(int score);
	public void getLeaderboardGPGS_chromatic();
}