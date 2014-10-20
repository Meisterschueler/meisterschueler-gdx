package de.meisterschueler.scorefollower.test;

import org.junit.Test;

import de.meisterschueler.scorefollower.BachItemFactory;
import de.meisterschueler.scorefollower.BrahmsItemFactory;
import de.meisterschueler.scorefollower.HanonItemFactory;

public class MatchingItemFactoryTest {

	@Test
	public void test() {
		HanonItemFactory hanon = new HanonItemFactory();
		hanon.getItems();
		
		BachItemFactory bach = new BachItemFactory();
		bach.getItems();
		
		BrahmsItemFactory brahms = new BrahmsItemFactory();
		brahms.getItems();
	}
}
