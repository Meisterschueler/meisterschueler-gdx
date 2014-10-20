package de.meisterschueler.scorefollower;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatchingItemTest {
	
	double eps = 1e-5;

	@Test
	public void isFinishedTest() {
		MatchingItem item = new MatchingItem("mmm");
		assertTrue(item.isFinished());
		
		item = new MatchingItem("mmmi");
		assertTrue(item.isFinished());
		
		item = new MatchingItem("mmM");
		assertFalse(item.isFinished());
	}
	
	@Test
	public void getProgressTest() {
		MatchingItem item = new MatchingItem("ooo");
		double progress_ooo = item.getProgress();
		
		item = new MatchingItem("Moo");
		double progress_Moo = item.getProgress();
		
		item = new MatchingItem("moo");
		double progress_moo = item.getProgress();
		
		item = new MatchingItem("mmM");
		double progress_mmM = item.getProgress();
		
		item = new MatchingItem("mmm");
		double progress_mmm = item.getProgress();
		
		item = new MatchingItem("mmmiiiI");
		double progress_mmmiiiI = item.getProgress();
		
		assertEquals(0.0, progress_ooo, eps);
		assertTrue(progress_Moo > progress_ooo);
		assertTrue(progress_moo > progress_Moo);
		assertTrue(progress_mmM > progress_moo);
		assertTrue(progress_mmm > progress_mmM);
		assertEquals(1.0, progress_mmm, eps);
		assertEquals(1.0, progress_mmmiiiI, eps);
	}
	
	@Test
	public void getQualityTest() {
		MatchingItem item = new MatchingItem("ooo");
		assertEquals(0.0, item.getQuality(), eps);
		
		item = new MatchingItem("moo");
		assertTrue(item.getQuality() > 0.0);
		
		item = new MatchingItem("mmo");
		assertTrue(item.getQuality() < 1.0);
		
		item = new MatchingItem("mmm");
		assertEquals(1.0, item.getQuality(), eps);
	}

}
