package banalytics.advertisement;

import banalytics.log.PlaySegment;
/*
 * Type d'annonce qui appara�t comme une banni�re superpos�e au contenu.
 * L'utilisateur peut la faire dispara�tre en cliquant dessus.
 */

public class BannerAd extends Advertisement
{

    private long position;

    public BannerAd(String p, long pos)
    {
        super(p);
        position = pos;
    }

    /*
     * On consid�re que l'annonce est vue si un segment de lecture est pass� au
     * moment o� elle doit appara�tre (visonnement instantan�).
     */
    @Override
    public void verifySegment(PlaySegment segment)
    {
        if (segment.includes(new PlaySegment(position, position)))
            viewCount++;
    }

    @Override
    public void update(PlaySegment playSegment)
    {
        verifySegment(playSegment);
    }
}