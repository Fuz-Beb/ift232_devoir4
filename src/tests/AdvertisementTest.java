package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import banalytics.advertisement.AdGenerator;
import banalytics.advertisement.Advertisement;
import banalytics.log.PlaySegment;
import banalytics.media.Music;
import banalytics.media.Video;

public class AdvertisementTest {

	private Music music1;
	private Video video1;
	private Advertisement ad1;
	private Advertisement ad2;
	
	@Before
	public void setup(){		
		music1 = new Music("The Space Explorers", "Big Falcon Rocket", 180000);
		video1 = new Video("ESA Channel", "The Beagle hasn't landed", 953000);
		ad1=AdGenerator.getInstance().generateBannerAd(music1);
		ad2=AdGenerator.getInstance().generateBannerAd(video1);
	}
	
	@Test
	public void verifySegmentTest() {
		
		ad1.verifySegment(new PlaySegment(0,180000));
		assertEquals(ad1.getViewCount(),1);
		ad1.verifySegment(new PlaySegment(0,50000));
		assertEquals(ad1.getViewCount(),1);
		
		ad2.verifySegment(new PlaySegment(600000,950000));
		assertEquals(ad2.getViewCount(),0);
		ad2.verifySegment(new PlaySegment(450000,550000));
		assertEquals(ad2.getViewCount(),1);
		ad2.verifySegment(new PlaySegment(0,950000));
		assertEquals(ad2.getViewCount(),2);
		
	}

}
