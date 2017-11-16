package banalytics.state;

import banalytics.log.MediaLog;

public class PausedState extends BanalyserState{
	
	@Override
	public BanalyserState move(MediaLog log, long source,long dest) {
		log.addMoveEntry(dest);		
		return PAUSED;
	}
	@Override
	public BanalyserState resume(MediaLog log, long time) {
		log.closePauseEntry(time);		
		return PLAYING;
	}
	
	@Override
	public BanalyserState stop(MediaLog log, long pos) {
		log.closePauseEntry(pos);
		log.closePlaySegment(pos);		
		return STOPPED;
	}
	
}
