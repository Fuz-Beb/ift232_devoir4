package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import banalytics.Banalyser;
import banalytics.BanalyserException;
import banalytics.advertisement.AdGenerator;
import banalytics.log.MediaLog;
import banalytics.log.MusicLog;
import banalytics.log.VideoLog;
import banalytics.media.Media;
import banalytics.media.Music;
import banalytics.media.Video;
import banalytics.state.BanalyserState;

public class BanalyserTest {

	private Media music1;
	private Media video1;

	private Banalyser musicAnalyser;
	private Banalyser videoAnalyser;

	private MediaLog sample1;
	private MediaLog sample2;

	@Before
	public void setup() {

		music1 = new Music("The Space Explorers", "Big Falcon Rocket", 180000);
		video1 = new Video("ESA Channel", "The Beagle hasn't landed", 953000);
		musicAnalyser = new Banalyser(music1,AdGenerator.getInstance().generateBannerAd(music1));
		videoAnalyser = new Banalyser(video1,AdGenerator.getInstance().generateBannerAd(video1));
		

		sample1 = new MusicLog();
		sample2 = new VideoLog();

		sample1.openPlaySegment(0);
		sample1.openPauseEntry(1000);
		sample1.addMoveEntry(2000);		
		sample1.closePauseEntry(0);
		sample1.openPauseEntry(2500);
		sample1.closePauseEntry(100);
		sample1.openBufferingEntry(2700);
		sample1.closeBufferingEntry(500);
		sample1.closePlaySegment(3000);

		sample2.openPlaySegment(0);
		sample2.openPauseEntry(500);
		sample2.addMoveEntry(1000);		
		sample2.closePauseEntry(0);
		sample2.openBufferingEntry(1700);
		sample2.closeBufferingEntry(500);
		sample2.openPauseEntry(2500);
		sample2.closePauseEntry(100);
		sample2.closePlaySegment(5000);

	}
	
	/*
	 * Nouveau test: v�rifie les revenus obtenus des annonces
	 */
	
	@Test
	public void testAdRevenue(){
		
		musicAnalyser.start(0);
		musicAnalyser.stop(180000);
		assertEquals(musicAnalyser.getAdRevenue(),AdGenerator.getInstance().viewsToDollars(1),0.000000001);	
		musicAnalyser.start(160000);
		musicAnalyser.stop(180000);
		assertEquals(musicAnalyser.getAdRevenue(),AdGenerator.getInstance().viewsToDollars(1),0.000000001);
		musicAnalyser.start(50000);
		musicAnalyser.stop(160000);
		assertEquals(musicAnalyser.getAdRevenue(),AdGenerator.getInstance().viewsToDollars(2),0.000000001);	
	}
	

	@Test
	public void testEquals() {

		Banalyser exp1 = new Banalyser(music1,AdGenerator.getInstance().generateBannerAd(music1));
		Banalyser exp2 = new Banalyser(video1,AdGenerator.getInstance().generateBannerAd(video1));
		Banalyser exp3 = new Banalyser(music1, sample1, BanalyserState.STOPPED,AdGenerator.getInstance().generateBannerAd(music1));
		Banalyser exp4 = new Banalyser(video1, sample2, BanalyserState.STOPPED,AdGenerator.getInstance().generateBannerAd(video1));

		assertNotEquals(exp1, exp2);

		musicAnalyser.start(0);
		musicAnalyser.move(1000,2000);		
		musicAnalyser.pause(2500);
		musicAnalyser.resume(100);
		musicAnalyser.buffer(2700);
		musicAnalyser.resume(500);
		musicAnalyser.stop(3000);

		assertEquals(exp3, musicAnalyser);

		videoAnalyser.start(0);
		videoAnalyser.move(500,1000);		
		videoAnalyser.buffer(1700);
		videoAnalyser.resume(500);
		videoAnalyser.pause(2500);
		videoAnalyser.resume(100);
		videoAnalyser.stop(5000);

		assertEquals(exp4, videoAnalyser);

	}

	@Test(expected = BanalyserException.class)
	public void testIllegalPlay() {

		musicAnalyser.start(0);
		musicAnalyser.start(0);
	}

	@Test(expected = BanalyserException.class)
	public void testIllegalPause() {

		musicAnalyser.start(0);
		musicAnalyser.stop(0);
		musicAnalyser.pause(0);
	}

	@Test(expected = BanalyserException.class)
	public void testIllegalResume() {

		musicAnalyser.start(0);
		musicAnalyser.stop(0);
		musicAnalyser.resume(0);

	}

	/* S�quence qui ne cause pas d'exception. provient du test original */

	@Test
	public void testMusicSequence() {

		musicAnalyser.start(0);
		musicAnalyser.pause(30000);
		musicAnalyser.resume(10000);
		musicAnalyser.move(132000,102000);		
		musicAnalyser.buffer(150000);
		musicAnalyser.resume(5000);
		musicAnalyser.stop(180000);

	}

	/* S�quence qui ne cause pas d'exception. provient du test original */
	@Test
	public void testVideoSequence() {

		videoAnalyser.start(0);
		videoAnalyser.buffer(5000);
		videoAnalyser.resume(60000);
		videoAnalyser.pause(30000);
		videoAnalyser.resume(105000);
		videoAnalyser.move(160000,570000);		
		videoAnalyser.stop(900000);
	}	
}
