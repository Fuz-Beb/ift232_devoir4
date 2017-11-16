package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import banalytics.BanalyserException;
import banalytics.log.MediaLog;
import banalytics.log.MusicLog;
import banalytics.log.PlaySegment;
import banalytics.log.VideoLog;


public class LogTest {

	private MediaLog sample1;
	private MediaLog sample2;
	private MediaLog sample3;
	private MediaLog sample4;
	
	@Before
	public void setup(){
		
		sample1 = new VideoLog();
		sample2 = new MusicLog();
		sample3 = new VideoLog();
		sample4 = new VideoLog();
		
		sample1.addMoveEntry(2000);
		sample1.openPlaySegment(2000);		
		sample1.openPauseEntry(2500);
		sample1.closePauseEntry(100);
		sample1.openBufferingEntry(2700);
		sample1.closeBufferingEntry(500);
		sample1.closePlaySegment(3000);
		
		
		sample2.addMoveEntry(1000);
		sample2.openPlaySegment(1000);		
		sample2.openBufferingEntry(2700);
		sample2.closeBufferingEntry(500);
		sample2.openPauseEntry(2500);
		sample2.closePauseEntry(100);		
		sample2.closePlaySegment(5000);
		
		/*
		 * Journeaux pour la génération de segments
		 */
		
		sample3.openPlaySegment(0);
		sample3.closePlaySegment(1000);
		sample3.openBufferingEntry(0);
		sample3.closeBufferingEntry(500);
		sample3.openPlaySegment(1000);
		sample3.openBufferingEntry(300);
		sample3.closeBufferingEntry(500);
		sample3.closePlaySegment(3000);
		sample3.openBufferingEntry(100);
		sample3.closeBufferingEntry(500);
		
		sample4.closePlaySegment(1000);
		sample4.openBufferingEntry(0);
		sample4.closeBufferingEntry(500);
		sample4.openPlaySegment(1000);
		sample4.openBufferingEntry(300);
	}
	
	
	/*
	 * Test pour nouveau code: retrouve des segments de lecture dans le journal
	 */
	@Test(expected=BanalyserException.class)
	public void testSegment(){
		
		PlaySegment expected = new PlaySegment(1000,3000);
		
		assertEquals(sample3.getLastSegment(),expected);
		
		sample4.getLastSegment();
		
	}
	
	@Test
	public void testEquals() {
		
		VideoLog test1 = new VideoLog();
		MusicLog test2 = new MusicLog();
		
		assertNotEquals(test1,test2);
		assertNotEquals(test2,test1);
		
		test1.addMoveEntry(2000);
		test1.openPlaySegment(2000);		
		test1.openPauseEntry(2500);
		test1.closePauseEntry(100);
		
		assertNotEquals(sample1,test1);
		
		test1.openBufferingEntry(2700);
		test1.closeBufferingEntry(500);
		test1.closePlaySegment(3000);
		
		assertEquals(sample1,test1);
		
		test2.addMoveEntry(1000);
		test2.openPlaySegment(1000);		
		test2.openBufferingEntry(2700);
		
		assertNotEquals(sample2,test2);
		test2.closeBufferingEntry(500);
		test2.openPauseEntry(2500);
		test2.closePauseEntry(100);		
		test2.closePlaySegment(5000);
		
		assertEquals(sample2,test2);
	}

}
