package de.meisterschueler.scorefollower;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import de.meisterschueler.basic.Score;

public class ScoreServiceTest {
	private GuidoService guidoService = new GuidoService();
	private ScoreService scoreService = new ScoreService();
	
	@Test
	public void concatTest() {
		String gmn = "c0/4 d e";
		List<Score> scores1 = guidoService.gmnToScores(gmn);
		List<Score> scores2 = guidoService.gmnToScores(gmn);
		
		List<Score> scores3 = scoreService.concat(scores1, scores2);
		assertEquals( 6,  scores3.size() );
		
		for (int i=0; i<scores3.size(); i++) {
			assertEquals( i*0.25, scores3.get(i).getPosition().doubleValue(), 0.01 );
		}
	}
	
	@Test
	public void shiftTest() {
		String gmn = "c0/4 d e/8 f g/2";
		List<Score> scores = guidoService.gmnToScores(gmn);
		
		assertEquals( 0.0, scores.get(0).getPosition().doubleValue(), 0.01 );
		assertEquals( 0.25, scores.get(1).getPosition().doubleValue(), 0.01 );
		assertEquals( 0.5, scores.get(2).getPosition().doubleValue(), 0.01 );
		assertEquals( 0.625, scores.get(3).getPosition().doubleValue(), 0.01 );
		assertEquals( 0.75, scores.get(4).getPosition().doubleValue(), 0.01 );
		
		Score lastScore = scores.get(scores.size()-1);
		List<Score> shiftedScores = scoreService.shiftScores( scores, lastScore.getPosition().add(lastScore.getMeasure()) );
		
		assertEquals( 1.25, shiftedScores.get(0).getPosition().doubleValue(), 0.01 );
		assertEquals( 1.5, shiftedScores.get(1).getPosition().doubleValue(), 0.01 );
		assertEquals( 1.75, shiftedScores.get(2).getPosition().doubleValue(), 0.01 );
		assertEquals( 1.875, shiftedScores.get(3).getPosition().doubleValue(), 0.01 );
		assertEquals( 2.0, shiftedScores.get(4).getPosition().doubleValue(), 0.01 );
	}
	
	@Test
	public void mergeTest() {
		String rightGmn = "c0/4 g/8 f {c/4,e}";
		String leftGmn = "_/8 c-1 {b-2,d-1} g1 c-1";
		List<Score> rightScores = guidoService.gmnToScores(rightGmn);
		List<Score> leftScores = guidoService.gmnToScores(leftGmn);
		List<Score> merged = scoreService.merge(rightScores, leftScores);
		
		merged = scoreService.removePause(merged);
		
		assertEquals( 10, merged.size() );
		
		assertEquals( 48, merged.get(0).getPitch() );
		assertEquals( 36, merged.get(1).getPitch() );
		assertEquals( 35, merged.get(2).getPitch() );
		assertEquals( 38, merged.get(3).getPitch() );
		assertEquals( 55, merged.get(4).getPitch() );
		assertEquals( 53, merged.get(5).getPitch() );
		assertEquals( 67, merged.get(6).getPitch() );
		assertEquals( 36, merged.get(7).getPitch() );
		assertEquals( 48, merged.get(8).getPitch() );
		assertEquals( 52, merged.get(9).getPitch() );
	}
	
	@Test
	public void removePauseTest() {
		String gmn = "c0 _ d e _ f g";
		List<Score> scores = guidoService.gmnToScores(gmn);
		
		assertEquals( 7, scores.size() );
		
		scores = scoreService.removePause(scores);
		
		assertEquals( 5, scores.size() );
	}
}
