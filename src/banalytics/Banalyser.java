package banalytics;

import banalytics.advertisement.Advertisement;
import banalytics.log.MediaLog;
import banalytics.media.Media;
import banalytics.state.BanalyserState;

/*
 * Classe qui sert à journaliser les événements qui surviennent durant l'utilisation d'un contenu
 * multimédia par un utilisateur. Une fois les événements correctement journalisés, il deviendra
 * possible d'analyser le comportement de l'utilisateur et d'en tirer des statistiques d'utilisation. 
 * 
 */

public class Banalyser {

	private Media media;

	private MediaLog log;

	private BanalyserState state;
	
	
	/*
	 * TP3
	 * Nouvelle variable: annonce présentement affixée au media
	 */
	private Advertisement advert;
	

	public Banalyser(Media m,Advertisement ad) {
		
		this.init(m,m.createLog(),BanalyserState.INITIAL,ad);
	}

	public Banalyser(Media med, MediaLog l, BanalyserState st,Advertisement ad) {
		this.init(med,l,st,ad);		
	}
	
	private final void init (Media med, MediaLog l, BanalyserState st,Advertisement ad){
		media=med;
		log=l;
		state=st;
		
		//TP3: Obtention d'une annonce
		advert=ad;
	}
	
	public double getAdRevenue(){
		return advert.monetize();
	}
	

	public String getTextLog() {

		String res = "" + media;
		res += "\n" + log + "\n";
		return res;

	}

	public void start(long position) {

		state = state.start(log, position);
	}

	public void stop(long position) {

		state = state.stop(log, position);
		advert.verifySegment(log.getLastSegment());
	}

	public void pause(long position) {

		state = state.pause(log, position);
		advert.verifySegment(log.getLastSegment());
	}

	public void resume(long time) {

		state = state.resume(log, time);
	}

	public void move(long source,long destination) {

		state = state.move(log, source,destination);
	}

	public void buffer(long position) {

		state = state.buffer(log, position);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Banalyser))
			return false;
		Banalyser other = (Banalyser) obj;
		if (state != other.state)
			return false;
		if (!log.equals(other.log))
			return false;
		if (!media.equals(other.media))
			return false;

		return true;
	}

}
