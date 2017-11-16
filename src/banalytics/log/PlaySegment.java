package banalytics.log;

/*
 * Cette classe représente un segment de lecture ininterrompue.
 * Utile pour déterminer si une annonce a été vue.
 * Une annonce devrait être elle-même représentée comme un segment de lecture,
 * représentant la durée de sa présentation. 
 */

public class PlaySegment {
	
	private long beginning;
	private long end;
	
	public PlaySegment(long b, long e){
		beginning=b;
		end=e;
	}
	
	public long size(){
		return end-beginning;
	}
	
	/*
	 * Vérifie si le segment contient complètement le segment visé
	 */	
	public boolean includes(PlaySegment other){		
		
		return (beginning <= other.beginning && end >=other.end);		
		
	}
	
	/*
	 * Vérifie si le segment contient une partie du segment visé et si oui, quelle longueur s'entrecoupe
	 */
	public long calculateOverlap(PlaySegment other){
		
		if(beginning <=other.beginning && end >=other.end){
			return other.size();
		}
		else if(end <=other.beginning || beginning >=other.end){
			return 0;
		}		
		else if(beginning >=other.beginning){
			return other.end-beginning;
		}
		else if(end <=other.end){
			return end-other.beginning;
		}
		else return 0;
		
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PlaySegment))
			return false;
		PlaySegment other = (PlaySegment) obj;
		if (beginning != other.beginning)
			return false;
		if (end != other.end)
			return false;
		return true;
	}
	
	

}
