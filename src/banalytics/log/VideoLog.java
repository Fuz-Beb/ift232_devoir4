package banalytics.log;

/*
 * Journal spécialisé pour les vidéos.
 */

public class VideoLog extends MediaLog {


	
	public VideoLog(){
		super();
	}
	
	
	
	@Override
	public String toString(){
		
		String res = "----------Video usage log----------\n";
		
		for(LogEntry entry:entries){
			
			res += entry+"\n";
		}
		
		return res;
		
	}
	
	@Override
	public boolean equals(Object other) {

		if (!(other instanceof VideoLog)) {
			return false;
		}
		return super.equals(other);
	}
	
	

}
