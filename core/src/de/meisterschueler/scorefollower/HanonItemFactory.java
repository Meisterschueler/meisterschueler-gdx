package de.meisterschueler.scorefollower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.meisterschueler.basic.score.Hand;
import de.meisterschueler.basic.score.Score;

public class HanonItemFactory implements MatchingItemFactory {

	private GuidoService guidoService = new GuidoService();
	private ScoreService scoreService = new ScoreService();

	@Override
	public List<MatchingItem> getItems() {
		List<MatchingItem> items = new ArrayList<MatchingItem>();
		for (int i = 0; i <= 19; i++) {
			Song hanonSong = getNo(i);
			MatchingItem item = hanonSong.toMatchingItem();
			items.add(item);
		}

		return items;
	}

	private Song getNo(int no) {
		Song hanonSong = new Song();

		switch (no) {
		case 0: {
			hanonSong.setName("1");
			hanonSong.setId(100L);

			String patternUp = "c0/16 e f g a g f e";
			int leftFingersUp[] = { 5, 4, 3, 2, 1, 2, 3, 4 };
			int rightFingersUp[] = { 1, 2, 3, 4, 5, 4, 3, 2 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 e d c b1 c2 d e";
			int leftFingersDown[] = { 1, 2, 3, 4, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 4, 3, 2, 1, 2, 3, 4 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 1: {
			hanonSong.setName("2");
			hanonSong.setId(200L);

			String patternUp = "c0/16 e a g f g f e";
			int leftFingersUp[] = { 5, 3, 1, 2, 3, 2, 3, 4 };
			int rightFingersUp[] = { 1, 2, 5, 4, 3, 4, 3, 2 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 d b1 c2 d c d e";
			int leftFingersDown[] = { 1, 3, 5, 4, 3, 4, 3, 2 };
			int rightFingersDown[] = { 5, 2, 1, 2, 3, 2, 3, 4 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 2: {
			hanonSong.setName("3");
			hanonSong.setId(300L);

			String patternUp = "c0/16 e a g f e f g";
			int leftFingersUp[] = { 5, 3, 1, 2, 3, 4, 3, 2 };
			int rightFingersUp[] = { 1, 2, 5, 4, 3, 2, 3, 4 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 d b1 c2 d e d c";
			int leftFingersDown[] = { 1, 3, 5, 4, 3, 2, 3, 4 };
			int rightFingersDown[] = { 5, 2, 1, 2, 3, 4, 3, 2 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 3: {
			hanonSong.setName("4");
			hanonSong.setDescription("Special exercise for the 3rd, 4th and 5th fingers of the hand.");
			hanonSong.setId(400L);

			String patternUp = "c0/16 d c e a g f e";
			int leftFingersUp[] = { 5, 4, 5, 3, 1, 2, 3, 4 };
			int rightFingersUp[] = { 1, 2, 1, 2, 5, 4, 3, 2 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 f g d b1 c2 d e";
			int leftFingersDown[] = { 1, 2, 1, 3, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 4, 5, 2, 1, 2, 3, 4 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 4: {
			hanonSong.setName("5");
			hanonSong.setDescription("We repeat, that the fingers should be lifted high, and with precision, until this entire volm is mastered.");
			hanonSong.setId(500L);

			String patternUp = "c0/16 a g a f g e f";
			int leftFingersUp[] = { 5, 1, 2, 1, 3, 2, 4, 3 };
			int rightFingersUp[] = { 1, 5, 4, 5, 3, 4, 2, 3 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "c2/16 d c e d f e g";
			int leftFingersDown[] = { 5, 4, 5, 3, 4, 2, 3, 1 };
			int rightFingersDown[] = { 1, 2, 1, 3, 2, 4, 3, 5 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 5: {
			hanonSong.setName("6");
			hanonSong
			.setDescription("To obtain the good results which we promise those who study this work, it is indispensable to play daily, at least once, the exercise already learned.");
			hanonSong.setId(600L);

			String patternUp1 = "c0/16 a g a f a e a";
			String patternUp2 = "c0/16 a g a f a e d";
			int leftFingersUp1[] = { 5, 1, 2, 1, 3, 1, 4, 1 };
			int leftFingersUp2[] = { 5, 1, 2, 1, 3, 1, 4, 5 };
			int rightFingersUp1[] = { 1, 5, 4, 5, 3, 5, 2, 5 };
			int rightFingersUp2[] = { 1, 5, 4, 5, 3, 5, 2, 1 };
			int stepsUp1[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
			int stepsUp2[] = { 13 };

			String patternDown1 = "g2/16 b1 c2 b1 d2 b1 e2 b1";
			String patternDown2 = "g2/16 b1 c2 b1 d2 b1 e2 d2";
			int leftFingersDown1[] = { 1, 5, 4, 5, 3, 5, 2, 5 };
			int leftFingersDown2[] = { 1, 5, 4, 5, 3, 5, 2, 3 };
			int rightFingersDown1[] = { 5, 1, 2, 1, 3, 1, 4, 1 };
			int rightFingersDown2[] = { 5, 1, 2, 1, 3, 1, 4, 3 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };
			int stepsDown2[] = { -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp1, patternUp2, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp1, leftFingersUp2, leftFingersDown1, leftFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp1, patternUp2, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp1, rightFingersUp2, rightFingersDown1, rightFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));

			break;
		}

		case 6: {
			hanonSong.setName("7");
			hanonSong.setDescription("Exercise of the greatest importance for the 3rd, 4th and 5th fingers.");
			hanonSong.setId(700L);

			String patternUp = "c0/16 e d f e g f e";
			int leftFingersUp[] = { 5, 3, 4, 2, 3, 1, 2, 3 };
			int rightFingersUp[] = { 1, 3, 2, 4, 3, 5, 4, 3 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 e f d e c d e";
			int leftFingersDown[] = { 1, 3, 2, 4, 3, 5, 4, 3 };
			int rightFingersDown[] = { 5, 3, 4, 2, 3, 1, 3, 4 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 7: {
			hanonSong.setName("8");
			hanonSong.setDescription("Very important exercise for all five fingers.");
			hanonSong.setId(800L);

			String patternUp = "c0/16 e g a f g e f";
			int leftFingersUp[] = { 5, 4, 2, 1, 3, 2, 4, 3 };
			int rightFingersUp[] = { 1, 2, 4, 5, 3, 4, 2, 3 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 e c b1 d2 c e d";
			int leftFingersDown[] = { 1, 2, 4, 5, 3, 4, 2, 3 };
			int rightFingersDown[] = { 5, 4, 2, 1, 3, 2, 4, 3 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 8: {
			hanonSong.setName("9");
			hanonSong.setDescription("Extension of the 4th and 5th, and general finger-exercise.");
			hanonSong.setId(900L);

			String patternUp = "c0/16 e f e g f a g";
			int leftFingersUp[] = { 5, 4, 3, 4, 2, 3, 1, 2 };
			int rightFingersUp[] = { 1, 2, 3, 2, 4, 3, 5, 4 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown1 = "g2/16 e d e c d b1 c2";
			String patternDown2 = "g2/16 e d e c d c d";
			int leftFingersDown1[] = { 1, 2, 3, 2, 4, 3, 5, 4 };
			int leftFingersDown2[] = { 1, 2, 3, 2, 4, 3, 4, 3 };
			int rightFingersDown1[] = { 5, 4, 3, 4, 2, 3, 1, 2 };
			int rightFingersDown2[] = { 5, 4, 3, 4, 2, 3, 2, 3 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };
			int stepsDown2[] = { -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp, leftFingersDown1, leftFingersDown2), Arrays.asList(stepsUp, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp, rightFingersDown1, rightFingersDown2), Arrays.asList(stepsUp, stepsDown1, stepsDown2)));
			break;
		}

		case 9: {
			hanonSong.setName("10");
			hanonSong
			.setDescription("Preparation for the trill, for the 3rd and 4th fingers of the left hand in ascending (1); and for the 3rd and 4th of the right, descending (2).");
			hanonSong.setId(1000L);

			String patternUp = "c0/16 a g f e f e f";
			int leftFingersUp[] = { 5, 1, 2, 3, 4, 3, 4, 3 };
			int rightFingersUp[] = { 1, 5, 4, 3, 2, 3, 2, 3 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 b1 c2 d e d e d";
			int leftFingersDown[] = { 1, 5, 4, 3, 2, 3, 2, 3 };
			int rightFingersDown[] = { 5, 1, 2, 3, 4, 3, 4, 3 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 10: {
			hanonSong.setName("11");
			hanonSong.setDescription("Another preparation for the trill, for the 4th and 5th fingers.");
			hanonSong.setId(1100L);

			String patternUp = "c0/16 e a g a g f g";
			int leftFingersUp[] = { 5, 3, 1, 2, 1, 2, 3, 2 };
			int rightFingersUp[] = { 1, 2, 5, 4, 5, 4, 3, 4 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 d b1 c2 b1 c2 d c";
			int leftFingersDown[] = { 1, 3, 5, 4, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 2, 1, 2, 1, 2, 3, 2 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 11: {
			hanonSong.setName("12");
			hanonSong.setDescription("Extension of 1-5, and exercise for 3-4-5.");
			hanonSong.setId(1200L);

			String patternUp1 = "g0/16 c e d c d e c";
			String patternUp2 = "a0/16 c e d c d e c";
			String patternUp3 = "a0/16 c e d c d e f";
			int leftFingersUp12[] = { 1, 5, 3, 4, 5, 4, 3, 5 };
			int leftFingersUp3[] = { 1, 5, 3, 4, 5, 4, 3, 2 };
			int rightFingersUp12[] = { 5, 1, 3, 2, 1, 2, 3, 1 };
			int rightFingersUp3[] = { 5, 1, 3, 2, 1, 2, 3, 4 };
			int stepsUp1[] = { 0 };
			int stepsUp2[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
			int stepsUp3[] = { 13 };

			String patternDown1 = "c2/16 a f g a g f a";
			String patternDown2 = "c0/16 g e f g f e f";
			int leftFingersDown12[] = { 1, 5, 3, 4, 5, 4, 3, 5 };
			int rightFingersDown12[] = { 5, 1, 3, 2, 1, 2, 3, 4 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };
			int stepsDown2[] = { 0 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp1, patternUp2, patternUp3, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp12, leftFingersUp12, leftFingersUp3, leftFingersDown12, leftFingersDown12),
					Arrays.asList(stepsUp1, stepsUp2, stepsUp3, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp1, patternUp2, patternUp3, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp12, rightFingersUp12, rightFingersUp3, rightFingersDown12, rightFingersDown12),
					Arrays.asList(stepsUp1, stepsUp2, stepsUp3, stepsDown1, stepsDown2)));
			break;
		}

		case 12: {
			hanonSong.setName("13");
			hanonSong.setDescription("(3-4-5)");
			hanonSong.setId(1300L);

			String patternUp = "e0/16 c f d g e f g";
			int leftFingersUp[] = { 3, 5, 2, 4, 1, 3, 2, 1 };
			int rightFingersUp[] = { 3, 1, 4, 2, 5, 3, 4, 5 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "e2/16 g d f e c d e";
			int leftFingersDown[] = { 3, 1, 4, 2, 3, 5, 4, 3 };
			int rightFingersDown[] = { 3, 5, 2, 4, 3, 1, 3, 4 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 13: {
			hanonSong.setName("14");
			hanonSong.setDescription("(3-4) Another preparation for the trill, for the 3rd and 4th fingers.");
			hanonSong.setId(1400L);

			String patternUp = "c0/16 d f e f e g f";
			int leftFingersUp[] = { 5, 4, 2, 3, 2, 3, 1, 2 };
			int rightFingersUp[] = { 1, 2, 4, 3, 4, 3, 5, 4 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 f d e d e c d";
			int leftFingersDown[] = { 1, 2, 4, 3, 4, 3, 5, 4 };
			int rightFingersDown[] = { 5, 4, 2, 3, 2, 3, 1, 3 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 14: {
			hanonSong.setName("15");
			hanonSong.setDescription("Extension of 1-2, and exercise for all 5 fingers.");
			hanonSong.setId(1500L);

			String patternUp1 = "c0/16 e d f e g f a";
			String patternUp2 = "c0/16 e d f e g f g";
			int leftFingersUp1[] = { 5, 3, 4, 2, 3, 1, 2, 1 };
			int leftFingersUp2[] = { 5, 3, 4, 2, 3, 1, 3, 2 };
			int rightFingersUp1[] = { 1, 2, 1, 3, 2, 4, 3, 5 };
			int rightFingersUp2[] = { 1, 2, 1, 3, 2, 4, 3, 4 };
			int stepsUp1[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
			int stepsUp2[] = { 13 };

			String patternDown1 = "g2/16 e f d e c d b1";
			String patternDown2 = "g2/16 e f d e c d c";
			int leftFingersDown1[] = { 1, 2, 1, 3, 2, 4, 3, 5 };
			int leftFingersDown2[] = { 1, 2, 1, 3, 2, 4, 3, 4 };
			int rightFingersDown1[] = { 5, 3, 4, 2, 3, 1, 2, 1 };
			int rightFingersDown2[] = { 5, 3, 4, 2, 3, 1, 3, 2 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };
			int stepsDown2[] = { -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp1, patternUp2, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp1, leftFingersUp2, leftFingersDown1, leftFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp1, patternUp2, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp1, rightFingersUp2, rightFingersDown1, rightFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));

			break;
		}

		case 15: {
			hanonSong.setName("16");
			hanonSong.setDescription("Extension of 3-5, and exercise for 3-4-5.");
			hanonSong.setId(1600L);

			String patternUp = "c0/16 e0 d0 e0 a0 g0 f0 g0";
			int leftFingersUp[] = { 5, 3, 4, 3, 1, 2, 3, 2 };
			int rightFingersUp[] = { 1, 3, 2, 3, 5, 4, 3, 4 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 d2 e2 d2 b1 c2 d2 c2";
			int leftFingersDown[] = { 1, 3, 2, 3, 5, 4, 3, 4 };
			int rightFingersDown[] = { 1, 3, 2, 3, 5, 4, 3, 4 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 16: {
			hanonSong.setName("17");
			hanonSong.setDescription("Extension of 1-2, 2-4, 4-5, and exercise for 3-4-5.");
			hanonSong.setId(1700L);

			String patternUp1 = "c0/16 e a g b a g a";
			String patternUp2 = "c0/16 e a g b a g f";
			int leftFingersUp1[] = { 5, 4, 2, 3, 1, 2, 3, 2 };
			int leftFingersUp2[] = { 5, 4, 2, 3, 1, 2, 3, 4 };
			int rightFingersUp1[] = { 1, 2, 4, 3, 5, 4, 3, 4 };
			int rightFingersUp2[] = { 1, 2, 4, 3, 5, 4, 3, 2 };
			int stepsUp1[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
			int stepsUp2[] = { 13 };

			String patternDown1 = "g2/16 d b1 c2 a1 b c2 a1";
			String patternDown2 = "g2/16 d b1 c2 a1 b c2 b1";
			int leftFingersDown1[] = { 1, 2, 4, 3, 5, 4, 3, 5 };
			int leftFingersDown2[] = { 1, 2, 4, 3, 5, 4, 3, 5 };
			int rightFingersDown1[] = { 5, 3, 2, 3, 1, 2, 3, 1 };
			int rightFingersDown2[] = { 5, 3, 2, 3, 1, 2, 3, 1 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11 };
			int stepsDown2[] = { -12 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp1, patternUp2, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp1, leftFingersUp2, leftFingersDown1, leftFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp1, patternUp2, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp1, rightFingersUp2, rightFingersDown1, rightFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));

			break;
		}

		case 17: {
			hanonSong.setName("18");
			hanonSong.setDescription("(1-2-3-4-5)");
			hanonSong.setId(1800L);

			String patternUp = "c0/16 d f e g f d e";
			int leftFingersUp[] = { 5, 4, 2, 3, 1, 2, 4, 3 };
			int rightFingersUp[] = { 1, 2, 4, 3, 5, 4, 2, 3 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 f d e c d f e";
			int leftFingersDown[] = { 1, 2, 4, 3, 5, 4, 2, 3 };
			int rightFingersDown[] = { 5, 4, 2, 3, 1, 2, 4, 3 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 18: {
			hanonSong.setName("19");
			hanonSong.setDescription("(1-2-3-4-5)");
			hanonSong.setId(1900L);

			String patternUp = "c0/16 a f g a f e g";
			int leftFingersUp[] = { 5, 1, 3, 2, 1, 3, 4, 2 };
			int rightFingersUp[] = { 1, 5, 3, 4, 5, 3, 2, 4 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 b1 d2 c b1 d2 e c";
			int leftFingersDown[] = { 1, 5, 3, 4, 5, 3, 2, 4 };
			int rightFingersDown[] = { 5, 1, 3, 2, 1, 3, 4, 2 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}

		case 19: {
			hanonSong.setName("20");
			hanonSong.setDescription("Extension of 2-4, 4-5, and exercise for 2-3-4.");
			hanonSong.setId(2000L);

			String patternUp1 = "e0/16 g c1 e c b0 c1 a0";
			String patternUp2 = "e0/16 g c1 e c b0 c1 g0";
			int leftFingersUp12[] = { 5, 4, 2, 1, 2, 3, 2, 4 };
			int rightFingersUp12[] = { 1, 2, 4, 5, 4, 3, 4, 2 };
			int stepsUp1[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
			int stepsUp2[] = { 14 };

			String patternDown1 = "e3/16 c3 g2 e2 g2 f2 g2 e2";
			String patternDown2 = "e3/16 c3 g2 e2 g2 f2 g2 f2";
			int leftFingersDown1[] = { 1, 2, 4, 5, 3, 4, 3, 5 };
			int leftFingersDown2[] = { 1, 2, 4, 5, 3, 4, 3, 4 };
			int rightFingersDown1[] = { 5, 4, 2, 1, 3, 2, 3, 1 };
			int rightFingersDown2[] = { 5, 4, 2, 1, 3, 2, 3, 2 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };
			int stepsDown2[] = { -14 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp1, patternUp2, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp12, leftFingersUp12, leftFingersDown1, leftFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp1, patternUp2, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp12, rightFingersUp12, rightFingersDown1, rightFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));
			break;
		}
		case 20: {
			hanonSong.setName("21");
			hanonSong.setId(2100L);
			hanonSong.setMeter("4/4");

			String patternUp = "c0/16 d e d c e f g a g f g a g f e";
			int leftFingersUp[] = { 5, 4, 3, 4, 5, 4, 3, 2, 1, 2, 3, 2, 1, 2, 3, 4 };
			int rightFingersUp[] = { 1, 2, 3, 2, 1, 2, 3, 4, 5, 4, 3, 4, 5, 4, 3, 2 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown1 = "g2/16 f e f g e d c b1 c2 d c b1 c2 d e";
			String patternDown2 = "a0/16 g f g a f e d c d e d c d e d";
			int leftFingersDown1[] = { 1, 2, 3, 2, 1, 2, 3, 4, 5, 4, 3, 4, 5, 4, 3, 2 };
			int leftFingersDown2[] = { 1, 2, 3, 2, 1, 2, 3, 4, 5, 4, 3, 4, 5, 4, 3, 4 };
			int rightFingersDown1[] = { 5, 4, 3, 4, 5, 4, 3, 2, 1, 2, 3, 2, 1, 2, 3, 4 };
			int rightFingersDown2[] = { 5, 4, 3, 4, 5, 4, 3, 2, 1, 2, 3, 2, 1, 2, 3, 2 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };
			int stepsDown2[] = { 0 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp, leftFingersDown1, leftFingersDown2), Arrays.asList(stepsUp, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp, rightFingersDown1, rightFingersDown2), Arrays.asList(stepsUp, stepsDown1, stepsDown2)));

			break;
		}
		case 21: {
			hanonSong.setName("22");
			hanonSong.setId(2200L);
			hanonSong.setMeter("C");

			String patternUp = "c0/16 e d e c e f g a f g f a g f e";
			int leftFingersUp[] = { 5, 3, 2, 3, 5, 4, 3, 2, 1, 3, 2, 3, 1, 2, 3, 4 };
			int rightFingersUp[] = { 1, 3, 2, 3, 1, 2, 3, 4, 5, 3, 4, 3, 5, 4, 3, 2 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown1 = "g2/16 e f e g e d c b1 d2 c d b1 c2 d e";
			String patternDown2 = "a0/16 f g f a f e d c e d e c f e d";
			int leftFingersDown1[] = { 1, 3, 2, 3, 1, 2, 3, 4, 5, 3, 4, 3, 5, 4, 3, 2 };
			int leftFingersDown2[] = { 1, 3, 2, 3, 1, 2, 3, 4, 5, 3, 4, 3, 5, 2, 3, 4 };
			int rightFingersDown1[] = { 5, 3, 4, 3, 5, 4, 3, 2, 1, 3, 2, 3, 1, 2, 3, 4 };
			int rightFingersDown2[] = { 5, 3, 4, 3, 5, 4, 3, 2, 1, 3, 2, 3, 1, 4, 3, 2 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };
			int stepsDown2[] = { 0 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp, leftFingersDown1, leftFingersDown2), Arrays.asList(stepsUp, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp, rightFingersDown1, rightFingersDown2), Arrays.asList(stepsUp, stepsDown1, stepsDown2)));

			break;
		}
		case 22: {
			hanonSong.setName("23");
			hanonSong.setId(2300L);
			hanonSong.setMeter("C");

			String patternUp = "c0 d e d c d e d c a g f e f g f";
			int leftFingersUp[] = { 5, 4, 3, 4, 5, 4, 3, 4, 5, 1, 2, 3, 4, 3, 2, 3 };
			int rightFingersUp[] = { 1, 2, 3, 2, 1, 2, 3, 2, 1, 5, 4, 3, 2, 3, 4, 3 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2 f e f g f e f g b1 c2 d e d c d";
			int leftFingersDown[] = { 1, 2, 3, 2, 1, 2, 3, 2, 1, 5, 4, 3, 2, 3, 4, 3 };
			int rightFingersDown[] = { 5, 4, 3, 4, 5, 4, 3, 4, 5, 1, 2, 3, 4, 3, 2, 3 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}
		case 23: {
			hanonSong.setName("24");
			hanonSong.setId(2400L);
			hanonSong.setMeter("C");

			String patternUp = "e0/16 d e c e d e c e d e c a f g e";
			int leftFingersUp[] = { 3, 4, 3, 5, 3, 4, 3, 5, 3, 4, 3, 5, 1, 3, 2, 4 };
			int rightFingersUp[] = { 3, 2, 3, 1, 3, 2, 3, 1, 3, 2, 3, 1, 5, 3, 4, 2 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "e2/16 g f g e g f g e g f g b1 d2 c e";
			int leftFingersDown[] = { 3, 1, 2, 1, 3, 1, 2, 1, 3, 1, 2, 1, 5, 3, 4, 2 };
			int rightFingersDown[] = { 3, 5, 4, 5, 3, 5, 4, 5, 3, 5, 4, 5, 1, 3, 2, 4 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}
		case 24: {
			hanonSong.setName("25");
			hanonSong.setId(2500L);
			hanonSong.setMeter("C");

			String patternUp12 = "c0/16 d e c d e f d e f g f e g f e";
			int leftFingersUp1[] = { 5, 4, 3, 5, 4, 3, 2, 4, 3, 2, 1, 2, 3, 1, 3, 4 };
			int leftFingersUp2[] = { 5, 4, 3, 5, 4, 3, 2, 4, 3, 2, 1, 2, 3, 1, 3, 4 };
			int rightFingersUp1[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 4, 3, 5, 4, 3 };
			int rightFingersUp2[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 4, 3, 5, 3, 2 };
			int stepsUp1[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
			int stepsUp2[] = { 13 };

			String patternDown12 = "g2/16 f e g f e d f e d c e d c d e";
			int leftFingersDown1[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 3, 4, 5, 4, 3 };
			int leftFingersDown2[] = { 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5, 3, 4, 5, 3, 2 };
			int rightFingersDown12[] = { 5, 4, 3, 5, 4, 3, 2, 4, 3, 2, 1, 3, 2, 1, 3, 4 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };
			int stepsDown2[] = { -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp12, patternUp12, patternDown12, patternDown12),
					Arrays.asList(leftFingersUp1, leftFingersUp2, leftFingersDown1, leftFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp12, patternUp12, patternDown12, patternDown12),
					Arrays.asList(rightFingersUp1, rightFingersUp2, rightFingersDown12, rightFingersDown12),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));

			break;
		}
		case 25: {
			hanonSong.setName("26");
			hanonSong.setId(2600L);
			hanonSong.setMeter("C");

			String patternUp = "e0/16 f g e d e f d c d e c a g a g";
			int leftFingersUp[] = { 3, 2, 1, 3, 4, 3, 2, 4, 5, 4, 3, 5, 1, 2, 1, 2 };
			int rightFingersUp[] = { 3, 4, 5, 3, 2, 3, 4, 2, 1, 2, 3, 1, 5, 4, 5, 4 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 e f g f d e f e c d e d c d c";
			int leftFingersDown[] = { 1, 3, 2, 1, 2, 4, 3, 2, 3, 5, 4, 3, 4, 3, 4, 3 };
			int rightFingersDown[] = { 5, 3, 4, 5, 4, 2, 3, 4, 3, 1, 2, 3, 2, 1, 2, 1 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}
		case 26: {
			hanonSong.setName("27");
			hanonSong.setId(2700L);
			hanonSong.setMeter("C");

			String patternUp = "e0/16 f d e c e f g a g a g a g f e";
			int leftFingersUp[] = { 3, 2, 4, 2, 5, 4, 3, 2, 1, 2, 1, 2, 1, 2, 3, 4 };
			int rightFingersUp[] = { 3, 4, 2, 3, 1, 2, 3, 4, 5, 4, 5, 4, 5, 4, 3, 2 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 f g e f e d c b1 c2 b1 c2 b1 c2 d e";
			int leftFingersDown[] = { 1, 2, 1, 3, 1, 2, 3, 4, 5, 4, 5, 4, 5, 4, 3, 2 };
			int rightFingersDown[] = { 5, 4, 5, 3, 5, 4, 3, 2, 1, 2, 1, 2, 1, 2, 3, 4 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}
		case 27: {
			hanonSong.setName("28");
			hanonSong.setId(2800L);
			hanonSong.setMeter("C");

			String patternUp = "c0/16 e d e c e d e c a g a f g e f";
			int leftFingersUp[] = { 5, 3, 4, 3, 5, 3, 4, 3, 5, 1, 2, 1, 3, 2, 4, 3 };
			int rightFingersUp[] = { 1, 3, 2, 3, 1, 3, 2, 3, 1, 5, 4, 5, 3, 4, 2, 3 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "g2/16 e f e g e f e g b1 c2 b1 d2 c e d";
			int leftFingersDown[] = { 1, 3, 2, 3, 1, 3, 2, 3, 1, 5, 4, 5, 3, 4, 2, 3 };
			int rightFingersDown[] = { 5, 3, 4, 3, 5, 3, 4, 3, 5, 1, 2, 1, 3, 2, 4, 3 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}
		case 28: {
			hanonSong.setName("29");
			hanonSong.setId(2900L);
			hanonSong.setMeter("C");

			String patternUp12 = "c0/16 d c e d e d f e f e g f g f g";
			int leftFingersUp1[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 2, 1, 2, 1 };
			int leftFingersUp2[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 3, 2, 3, 2 };
			int rightFingersUp1[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 4, 5, 4, 5 };
			int rightFingersUp2[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 4, 5, 3, 4 };
			int stepsUp1[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
			int stepsUp2[] = { 13 };

			String patternDown1 = "g2/16 f g e f e f d e d e c d e d e";
			String patternDown2 = "a0/16 g a f g f g e f e f d e f e d";
			int leftFingersDown1[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 4, 3, 4, 3 };
			int leftFingersDown2[] = { 1, 2, 1, 3, 2, 3, 2, 4, 3, 4, 3, 5, 3, 2, 3, 4 };
			int rightFingersDown1[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 2, 3, 2, 3 };
			int rightFingersDown2[] = { 5, 4, 5, 3, 4, 3, 4, 2, 3, 2, 3, 1, 3, 4, 3, 2 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };
			int stepsDown2[] = { 0 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp12, patternUp12, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp1, leftFingersUp2, leftFingersDown1, leftFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp12, patternUp12, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp1, rightFingersUp2, rightFingersDown1, rightFingersDown2),
					Arrays.asList(stepsUp1, stepsUp2, stepsDown1, stepsDown2)));

			break;
		}
		case 29: {
			hanonSong.setName("30");
			hanonSong.setId(3000L);
			hanonSong.setMeter("C");

			String patternUp = "c0/16 d c d c d c e a g a g a g a f";
			int leftFingersUp[] = { 5, 4, 5, 4, 5, 4, 5, 3, 1, 2, 1, 2, 1, 2, 1, 3 };
			int rightFingersUp[] = { 1, 2, 1, 2, 1, 2, 1, 3, 5, 4, 5, 4, 5, 4, 5, 3 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown1 = "g2/16 f g f g f g e b1 c2 b1 c2 b1 c2 b1 d2";
			String patternDown2 = "g2 f g f g f g e b1 c2 b1 c2 b1 c2 b1 c2";
			int leftFingersDown1[] = { 1, 2, 1, 2, 1, 2, 1, 3, 5, 4, 5, 4, 5, 4, 5, 3 };
			int leftFingersDown2[] = { 1, 2, 1, 2, 1, 2, 1, 3, 5, 4, 5, 4, 5, 4, 5, 4 };
			int rightFingersDown1[] = { 5, 4, 5, 4, 5, 4, 5, 3, 1, 2, 1, 2, 1, 2, 1, 3 };
			int rightFingersDown2[] = { 5, 4, 5, 4, 5, 4, 5, 3, 1, 2, 1, 2, 1, 2, 1, 2 };
			int stepsDown1[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };
			int stepsDown2[] = { -13 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp, leftFingersDown1, leftFingersDown2), Arrays.asList(stepsUp, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp, rightFingersDown1, rightFingersDown2), Arrays.asList(stepsUp, stepsDown1, stepsDown2)));
			break;
		}
		case 30: {
			hanonSong.setName("31");
			hanonSong.setId(3100L);
			hanonSong.setMeter("3/4");

			String leftPatternUp = "c0/16 c1 b0 c1 a0 c1 g0 c1 f0 c1 e0 c1";
			String rightPatternUp = "e1/16 e2 d e c e b1 e2 a1 e2 g1 e2";
			int leftFingersUp[] = { 5, 1, 2, 1, 3, 1, 4, 1, 5, 1, 5, 1 };
			int rightFingersUp[] = { 1, 5, 4, 5, 3, 5, 2, 5, 1, 5, 1, 5 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String leftPatternDown = "c3/16 c2 d c e c f c g c a c";
			String rightPatternDown = "e4/16 e3 f e g e a e b e c4 e3";
			int leftFingersDown[] = { 1, 5, 4, 5, 3, 5, 2, 5, 1, 5, 1, 5 };
			int rightFingersDown[] = { 5, 1, 2, 1, 3, 1, 4, 1, 5, 1, 5, 1 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(leftPatternUp, leftPatternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(rightPatternUp, rightPatternDown),
					Arrays.asList(rightFingersUp, rightFingersDown), Arrays.asList(stepsUp, stepsDown)));

			break;
		}
		case 31: {
			hanonSong.setName("38");
			hanonSong.setId(3800L);
			hanonSong.setMeter("2/4");

			String patternUp = "c0/16 d e f g a b c1";
			int leftFingersUp[] = { 5, 4, 3, 2, 1, 3, 2, 1 };
			int rightFingersUp[] = { 1, 2, 3, 1, 2, 3, 4, 5 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

			String patternDown = "c3/16 b2 a2 g2 f2 e2 d2 c2";
			int leftFingersDown[] = { 1, 2, 3, 1, 2, 3, 4, 5 };
			int rightFingersDown[] = { 5, 4, 3, 2, 1, 3, 2, 1 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;
		}
		case 32: {
			hanonSong.setName("50");
			hanonSong.setId(5000L);
			hanonSong.setMeter("C");
			;

			String patternUp12 = "{c0,e0} {d0,f0} {e0,g0} {d0,f0}";
			int leftFingersUp12[] = { 5, 3, 4, 2, 3, 1, 4, 2 };
			int rightFingersUp12[] = { 1, 3, 2, 4, 3, 5, 2, 4 };
			int stepsUp1[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			int stepsUp2[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

			String patternDown = "{e2,g2} {d2,f2} {c2,e2} {d2,f2}";
			int leftFingersDown[] = { 3, 1, 4, 2, 5, 3, 4, 2 };
			int rightFingersDown[] = { 3, 5, 2, 4, 1, 3, 2, 4 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp12, patternUp12, patternDown),
					Arrays.asList(leftFingersUp12, leftFingersUp12, leftFingersDown), Arrays.asList(stepsUp1, stepsUp2, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp12, patternUp12, patternDown),
					Arrays.asList(rightFingersUp12, rightFingersUp12, rightFingersDown), Arrays.asList(stepsUp1, stepsUp2, stepsDown)));
			break;
		}

		case 33: {
			hanonSong.setName("50a");
			hanonSong.setId(5001L);
			hanonSong.setMeter("C");
			;

			String patternUp = "{c0,e} {d,f} {e,g}";
			int leftFingersUp[] = { 5, 3, 4, 2, 3, 1 };
			int rightFingersUp[] = { 1, 3, 2, 4, 3, 5 };
			int stepsUp[] = { 0, 3, 6, 9, 12, 15, 18, 21 };

			String patternDown1 = "{d3,f} {c,e} {b2,d3}";
			String patternDown2 = "{d0,f} {c,e} {d,f}";
			int leftFingersDown1[] = { 4, 2, 5, 3, 3, 1 };
			int leftFingersDown2[] = { 4, 2, 5, 3, 4, 2 };
			int rightFingersDown1[] = { 2, 4, 1, 3, 3, 5 };
			int rightFingersDown2[] = { 2, 4, 1, 3, 2, 4 };
			int stepsDown1[] = { 0, -3, -6, -9, -12, -15, -18 };
			int stepsDown2[] = { 0 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown1, patternDown2),
					Arrays.asList(leftFingersUp, leftFingersDown1, leftFingersDown2), Arrays.asList(stepsUp, stepsDown1, stepsDown2)));
			hanonSong.setRightScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown1, patternDown2),
					Arrays.asList(rightFingersUp, rightFingersDown1, rightFingersDown2), Arrays.asList(stepsUp, stepsDown1, stepsDown2)));

			break;
		}

		case 34: {
			hanonSong.setName("54");
			hanonSong.setId(5400L);
			hanonSong.setMeter("C");
			;

			String patternUp = "{c1,e} {d,f} {c,e} {d,f} {c,e} {d,f} {c,e} {d,f} {e,g} {d,f} {e,g} {d,f} {e,g} {d,f} {e,g} {d,f}";
			int leftFingersUp[] = { 5, 3, 4, 2, 5, 3, 4, 2, 5, 3, 4, 2, 5, 3, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2 };
			int rightFingersUp[] = { 1, 3, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4, 3, 5, 2, 4, 3, 5, 2, 4, 3, 5, 2, 4, 3, 5, 2, 4 };
			int stepsUp[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

			String patternDown = "{d2,f} {c,e} {d,f} {c,e} {d,f} {c,e} {d,f} {c,e} {b1,d2} {c,e} {b1,d2} {c,e} {b1,d2} {c,e} {b1,d2} {c,e}";
			int leftFingersDown[] = { 3, 1, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2, 3, 1, 4, 2, 5, 3, 4, 2, 5, 3, 4, 2, 5, 3, 4, 2, 5, 3, 4, 2 };
			int rightFingersDown[] = { 3, 5, 2, 4, 3, 5, 2, 4, 3, 5, 2, 4, 3, 5, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4, 1, 3, 2, 4 };
			int stepsDown[] = { 0, -1, -2, -3, -4, -5, -6 };

			hanonSong.setLeftScores(generateScores(Hand.LEFT, Arrays.asList(patternUp, patternDown), Arrays.asList(leftFingersUp, leftFingersDown),
					Arrays.asList(stepsUp, stepsDown)));
			hanonSong.setRightScores(generateScores(Hand.RIGHT, Arrays.asList(patternUp, patternDown), Arrays.asList(rightFingersUp, rightFingersDown),
					Arrays.asList(stepsUp, stepsDown)));

			break;

		}

		case 35: {
			hanonSong.setName("0");
			hanonSong.setDescription("Scale");
			hanonSong.setId(0L);

			String pattern = "c0 d e f g a b c1 d e f g a b c2 b1 a g f e d c b0 a g f e d";
			int leftFingers[] = { 5, 4, 3, 2, 1, 3, 2, 1, 4, 3, 2, 1, 3, 2, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 5 };
			int rightFingers[] = { 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 2, 1, 4, 3, 2, 1, 3, 2, 1 };

			hanonSong.setLeftScores(guidoService.gmnToScores(pattern, leftFingers));
			hanonSong.setRightScores(guidoService.gmnToScores(guidoService.oneOctaveUp(pattern), rightFingers));

			break;
		}

		}

		return hanonSong;
	}

	private List<Score> generateScores(Hand hand, List<String> patterns, List<int[]> fingers, List<int[]> steps) {
		List<List<Score>> scores = new ArrayList<List<Score>>();

		for (int i = 0; i < patterns.size(); i++) {
			if (hand == Hand.LEFT) {
				scores.add(guidoService.gmnToScores(patterns.get(i), fingers.get(i), steps.get(i)));
			} else {
				scores.add(guidoService.gmnToScores(guidoService.oneOctaveUp(patterns.get(i)), fingers.get(i), steps.get(i)));
			}
		}

		List<Score> result = scores.get(0);
		for (int i = 1; i < scores.size(); i++) {
			result = scoreService.concat(result, scores.get(i));
		}
		return result;
	}
}