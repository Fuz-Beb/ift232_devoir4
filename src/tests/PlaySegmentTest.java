package tests;

import org.junit.Before;
import org.junit.Test;

import banalytics.log.PlaySegment;
import static org.junit.Assert.*;

public class PlaySegmentTest {

	private PlaySegment seg1;
	private PlaySegment seg2;
	private PlaySegment seg3;
	private PlaySegment seg4;
	
	@Before
	public void setUp(){
		
		seg1 = new PlaySegment(100, 500);
		seg2 = new PlaySegment(200, 300);
		seg3 = new PlaySegment(250, 600);
		seg4 = new PlaySegment(100, 250);
		
	}
	
	@Test
	public void testIncludes() {
		
		assertTrue(seg1.includes(seg2));
		assertFalse(seg2.includes(seg1));
		assertFalse(seg2.includes(seg4));
	}
	
	@Test
	public void testOverlap() {
		
		assertEquals(seg1.calculateOverlap(seg2),seg2.size());
		assertEquals(seg1.calculateOverlap(seg3),250);
		assertEquals(seg2.calculateOverlap(seg3),50);
		assertEquals(seg2.calculateOverlap(seg4),50);
		assertEquals(seg3.calculateOverlap(seg4),0);
	}

}
