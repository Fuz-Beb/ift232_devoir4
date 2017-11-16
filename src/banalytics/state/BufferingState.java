package banalytics.state;

import banalytics.log.MediaLog;

public class BufferingState extends BanalyserState{
	
	@Override
	public BanalyserState resume(MediaLog log, long time) {
		log.closeBufferingEntry(time);		
		return PLAYING;
	}

}
