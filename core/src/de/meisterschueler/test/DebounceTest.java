package de.meisterschueler.test;

import org.junit.Test;

import static org.junit.Assert.*;
import de.meisterschueler.utils.Debounce;

public class DebounceTest {
	boolean pressed = false;

	@Test
	public void test() throws InterruptedException {

		Debounce pedal = new Debounce(100, 10) {

			@Override
			public void execute(boolean state) {
				if (state)
					pressed = true;
			}
		};

		pedal.hit(true);
		assertFalse(pedal.isSet());
		pedal.hit(false);
		assertFalse(pedal.isSet());

		assertFalse(pressed);
		Thread.sleep(100);
		assertFalse(pressed);

		pedal.hit(true);
		assertFalse(pedal.isSet());
		assertFalse(pressed);
		Thread.sleep(110);
		assertTrue(pressed);
		assertTrue(pedal.isSet());

		pedal.hit(false);
		assertTrue(pressed);
		assertTrue(pedal.isSet());
	}
}
