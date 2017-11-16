package banalytics.log;

import java.util.ArrayList;

import banalytics.BanalyserException;

/*
 * Interface générale pour un journal.
 */

public abstract class MediaLog {
	
	protected ArrayList<LogEntry> entries;

	public MediaLog(){
		entries = new ArrayList<LogEntry>();
	}
	
	/*
	 * Nouvelle méthode: lit le journal à rebours et retrouve la dernière lecture continue.
	 * Ne supporte pas encore les segments bornés par des pauses. 
	 * TP3: ne vous souciez pas de ce détail. Utilisez des segments bornés par PLAY/STOP pour tester.
	 */
	
	public PlaySegment getLastSegment(){
		
		boolean start=false;
		boolean end=false;	
		LogEntry stopEntry=null;
		LogEntry startEntry=null;
		
		
		for (int i=entries.size()-1;i>=0;i--){
			
			LogEntry current = entries.get(i);
			
			if(current.stopsSegment()){
				
				if(end){break;} //Journal mal formé
				stopEntry=current;
				end=true;
			}
			
			else if(current.startsSegment()){
				if(!end){break;}//Journal mal formé
				startEntry=current;
				start=true;
				break;
			}
			
		}
		
		/*
		 * Aucun segment complet trouvé dans ce journal... n'aurait pas dû être appelé
		 */
		if(!(start && end)){
			throw new BanalyserException();
		}
		
		return new PlaySegment(startEntry.getTime(),stopEntry.getTime());
	}
	

	/* Méthode equals générée par l'IDE. */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MediaLog))
			return false;
		MediaLog other = (MediaLog) obj;		
		
		if (!entries.equals(other.entries))
			return false;
		
		return true;
	}


	public void openPlaySegment(long pos) {
		entries.add(new LogEntry(LogEntry.OPENPLAY, pos));

	}

	
	public void openPauseEntry(long pos) {
		entries.add(new LogEntry(LogEntry.OPENPAUSE, pos));

	}


	public void closePauseEntry(long time) {
		entries.add(new LogEntry(LogEntry.CLOSEPAUSE, time));

	}

	
	public void closePlaySegment(long pos) {
		entries.add(new LogEntry(LogEntry.CLOSEPLAY, pos));

	}


	public void openBufferingEntry(long pos) {
		entries.add(new LogEntry(LogEntry.OPENBUFFERING, pos));

	}


	public void closeBufferingEntry(long time) {
		entries.add(new LogEntry(LogEntry.CLOSEBUFFERING, time));

	}

	
	public void addMoveEntry(long pos) {
		entries.add(new LogEntry(LogEntry.MOVE, pos));

	}

}
