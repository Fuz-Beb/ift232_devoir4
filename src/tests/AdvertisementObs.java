package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import banalytics.Banalyser;
import banalytics.advertisement.AdGenerator;
import banalytics.media.Media;
import banalytics.media.Music;
import banalytics.media.Video;

/**
 * Classe de test Vérification de l'ajout de plusieurs annonces sur un même
 * banaliser
 */
public class AdvertisementObs
{
    private Media music1;
    private Media video1;

    private Banalyser musicAnalyser;
    private Banalyser videoAnalyser;

    @Before
    public void setup()
    {
        music1 = new Music("The Space Explorers", "Big Falcon Rocket", 180000);
        video1 = new Video("ESA Channel", "The Beagle hasn't landed", 953000);

        musicAnalyser = new Banalyser(music1, AdGenerator.getInstance().generateBannerAd(music1));
        musicAnalyser.attach(AdGenerator.getInstance().generateBannerAd(music1));

        videoAnalyser = new Banalyser(video1, AdGenerator.getInstance().generateVideoAd(video1));
        videoAnalyser.attach(AdGenerator.getInstance().generateVideoAd(video1));
    }

    @Test
    public void testAdRevenueAll()
    {
        // Bannière

        // Pour deux annonces (bannière) diffusées
        musicAnalyser.start(30000);
        musicAnalyser.stop(180000);
        assertEquals(musicAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(2), 0.000000001);

        // Pour quatre annonces (bannière) diffusées
        musicAnalyser.attach(AdGenerator.getInstance().generateBannerAd(music1));
        musicAnalyser.attach(AdGenerator.getInstance().generateBannerAd(music1));
        musicAnalyser.start(30000);
        musicAnalyser.stop(180000);
        assertEquals(musicAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(6), 0.000000001);
        
        musicAnalyser.start(100000);
        musicAnalyser.stop(180000);
        assertEquals(musicAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(6), 0.000000001);

        // Video

        // Pour deux annonces (videos) diffusées
        videoAnalyser.start(400000);
        videoAnalyser.stop(800000);
        assertEquals(videoAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(2), 0.000000001);

        // Pour quatre annonces (videos) diffusées
        videoAnalyser.attach(AdGenerator.getInstance().generateBannerAd(video1));
        videoAnalyser.attach(AdGenerator.getInstance().generateBannerAd(video1));
        videoAnalyser.start(200000);
        videoAnalyser.stop(800000);
        assertEquals(videoAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(6), 0.000000001);
        
        videoAnalyser.start(500000);
        videoAnalyser.stop(800000);
        assertEquals(videoAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(6), 0.000000001);
    }
}