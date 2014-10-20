package de.meisterschueler.scorefollower;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringMatcherTest {
	@Test
	public void emptySequencesTest() {
		String alignment = StringMatcher.getAlignments("", "ABC");
		assertEquals("iii", alignment);
		
		alignment = StringMatcher.getAlignments("ABC", "");
		assertEquals("ddd", alignment);
		
		alignment = StringMatcher.getAlignments("", "");
		assertEquals("", alignment);
	}
	
	@Test
	public void ABC_ABC_Test() {
		String alignment = StringMatcher.getAlignments("ABC", "ABC");
		assertEquals( "mmm", alignment);
	}
	
	@Test
	public void ABC_AAC_Test() {
		String alignment = StringMatcher.getAlignments("ABC", "AAC");
		assertEquals( "mwm", alignment);
	}
	
	@Test
	public void ABC_AZC_Test() {
		String alignment = StringMatcher.getAlignments("ABC", "AZC");
		assertEquals( "mwm", alignment);
	}
	
	@Test
	public void ABC_ABZC_Test() {
		String alignment = StringMatcher.getAlignments("ABC", "ABZC");
		assertEquals( "mmim", alignment);
	}
	
	@Test
	public void ABCD_ABD_Test() {
		String alignment = StringMatcher.getAlignments("ABCD", "ABD");
		assertEquals( "mmdm", alignment);
	}

	@Test
	public void complexTest() {
		String alignment = StringMatcher.getAlignments("BCEFGH", "ABCDFG");
		assertEquals( "immwmmd", alignment );
	}
	
	@Test
	public void matchFirstTest() {
		String alignment = StringMatcher.getAlignments("AAA", "AAAA");
		assertEquals( "mmmi", alignment);
		
		alignment = StringMatcher.getAlignments("AAAA", "AAA");
		assertEquals( "mmmd", alignment);
	}
}
