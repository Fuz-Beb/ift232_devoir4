package banalytics.log;

/*
 * Cette classe repr�sente un segment de lecture ininterrompue.
 * Utile pour d�terminer si une annonce a �t� vue.
 * Une annonce devrait �tre elle-m�me repr�sent�e comme un segment de lecture,
 * repr�sentant la dur�e de sa pr�sentation. 
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
	 * V�rifie si le segment contient compl�tement le segment vis�
	 */	
	public boolean includes(PlaySegment other){		
		
		return (beginning <= other.beginning && end >=other.end);		
		
	}
	
	/*
	 * V�rifie si le segment contient une partie du segment vis� et si oui, quelle longueur s'entrecoupe
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
