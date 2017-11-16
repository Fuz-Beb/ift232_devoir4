package banalytics.advertisement;

import banalytics.log.PlaySegment;

/*
 * Type d'annonce repr�sentant un vid�o incrust� dans le contenu.
 * TP3: � COMPL�TER
 */

public class VideoAd extends Advertisement{

    private long position;
    private long duree;
    
	public VideoAd(String p, long pos, long duree) {
		super(p);
		this.position = pos;
		this.duree = duree;
	}

	@Override
	public void verifySegment(PlaySegment segment) {
        if(segment.includes(new PlaySegment(position,position + duree))){
            viewCount++;
        }   
	}

    /**
     * @return the position
     */
    public long getPosition()
    {
        return position;
    }

    /**
     * @return the duree
     */
    public long getDuree()
    {
        return duree;
    }

}
