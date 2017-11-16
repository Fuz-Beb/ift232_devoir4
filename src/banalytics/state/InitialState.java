package banalytics.state;

import banalytics.log.MediaLog;

public class InitialState extends BanalyserState{
	
	public BanalyserState start(MediaLog log,long pos){
		log.openPlaySegment(pos);
		return PLAYING;
	}

}
