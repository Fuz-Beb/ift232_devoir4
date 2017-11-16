package banalytics.advertisement;

import banalytics.log.PlaySegment;
/*
 * Type d'annonce qui apparaît comme une bannière superposée au contenu.
 * L'utilisateur peut la faire disparaître en cliquant dessus.
 */

public class BannerAd extends Advertisement{

	private long position;
	
	public BannerAd(String p,AdGenerator g,long pos) {
		super(p,g);
		position=pos;
	}
	
	/*
	 * On considère que l'annonce est vue si un segment de lecture est passé
	 * au moment où elle doit apparaître (visonnement instantané).
	 */
	@Override
	public void verifySegment(PlaySegment segment) {
		if(segment.includes(new PlaySegment(position,position))){
			viewCount++;
		}		
	}

}
