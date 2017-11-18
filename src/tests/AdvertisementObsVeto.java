package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import banalytics.Banalyser;
import banalytics.advertisement.AdGenerator;
import banalytics.log.MediaLog;
import banalytics.media.Media;
import banalytics.media.Music;
import banalytics.media.Video;
import browser.Browser;
import browser.Filtre;

/**
 * Classe de test Vérification de l'ajout de plusieurs annonces sur un même
 * banaliser
 */
public class AdvertisementObsVeto
{
    private Media music1;

    private Banalyser musicAnalyser;
    private ArrayList<Browser> browserList = new ArrayList<Browser>(); 

    @Before
    public void setup()
    {
        music1 = new Music("The Space Explorers", "Big Falcon Rocket", 180000);
        
        musicAnalyser = new Banalyser(music1, AdGenerator.getInstance().generateBannerAd(music1));
        musicAnalyser.attach(AdGenerator.getInstance().generateBannerAd(music1));
  
        browserList.add(new Filtre(true, true));
        browserList.add(new Filtre(true, false));
        browserList.add(new Filtre(false, false));
        browserList.add(new Filtre(false, true));        
    }

    @Test
    public void testAdRevenueFilter()
    {
        // isAdBlocked = true & isLooking = true
        musicAnalyser.attachBrowser(browserList.get(0));
        
        // Pour deux annonces (bannière) diffusées
        musicAnalyser.start(30000);
        musicAnalyser.stop(180000);
        assertEquals(musicAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(0), 0.000000001);     
        
        
        // isAdBlocked = true & isLooking = false
        musicAnalyser.attachBrowser(browserList.get(1));
        
        // Pour deux annonces (bannière) diffusées
        musicAnalyser.start(30000);
        musicAnalyser.stop(180000);
        assertEquals(musicAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(0), 0.000000001);
        
        
        // isAdBlocked = false & isLooking = false
        musicAnalyser.attachBrowser(browserList.get(2));
        
        // Pour deux annonces (bannière) diffusées
        musicAnalyser.start(30000);
        musicAnalyser.stop(180000);
        assertEquals(musicAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(0), 0.000000001);
        
        
        // isAdBlocked = false & isLooking = true
        musicAnalyser.attachBrowser(browserList.get(3));
        
        // Pour deux annonces (bannière) diffusées
        musicAnalyser.start(30000);
        musicAnalyser.stop(180000);
        assertEquals(musicAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(2), 0.000000001);
        
        
        // isAdBlocked = false & isLooking = true
        musicAnalyser.attachBrowser(browserList.get(3));
        
        // Pour deux annonces (bannière) diffusées
        musicAnalyser.start(30000);
        musicAnalyser.stop(180000);
        assertEquals(musicAnalyser.getAdRevenue(), AdGenerator.getInstance().viewsToDollars(6), 0.000000001);
    }
}