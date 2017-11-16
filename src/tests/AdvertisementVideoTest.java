package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import banalytics.advertisement.AdGenerator;
import banalytics.advertisement.Advertisement;
import banalytics.advertisement.VideoAd;
import banalytics.log.PlaySegment;
import banalytics.media.Music;
import banalytics.media.Video;

public class AdvertisementVideoTest
{
    private Video video1;
    private VideoAd ad1;
    private VideoAd ad2;

    @Before
    public void setup()
    {
        video1 = new Video("ESA Channel", "The Beagle hasn't landed", 320000);
        ad1 = (VideoAd) AdGenerator.getInstance().generateVideoAd(video1);
        ad2 = (VideoAd) AdGenerator.getInstance().generateVideoAd(video1);
    }

    @Test
    public void verifySegmentTest()
    {
        ad1.verifySegment(new PlaySegment(0, 180000));
        assertEquals(ad1.getViewCount(), 0);
        ad1.verifySegment(new PlaySegment(ad1.getPosition(), ad1.getPosition() + ad1.getDuree()));
        assertEquals(ad1.getViewCount(), 1);

        ad2.verifySegment(new PlaySegment(150000, 500000));
        assertEquals(ad2.getViewCount(), 1);
        ad2.verifySegment(new PlaySegment(110000, 715000));
        assertEquals(ad2.getViewCount(), 2);
        ad2.verifySegment(new PlaySegment(0, 230000));
        assertEquals(ad2.getViewCount(), 2);
    }
}
