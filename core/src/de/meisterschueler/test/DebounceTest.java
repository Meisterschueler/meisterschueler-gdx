package de.meisterschueler.test;

import org.junit.Test;

import static org.junit.Assert.*;
import de.meisterschueler.gdx.Debounce;

public class DebounceTest {
	boolean pressed = false;

	@Test
	public void test() throws InterruptedException {

		Debounce linkesPedal = new Debounce(100, 10) {

			@Override
			public void execute(boolean state) {
				if (state)
				pressed = true;
			}
		};
		
		linkesPedal.hit(true);
		linkesPedal.hit(false);
		
		assertFalse(pressed);	
		Thread.sleep(100);
		assertFalse(pressed);
		
		linkesPedal.hit(true);
		assertFalse(pressed);
		Thread.sleep(200);
		assertTrue(pressed);
	}

}
