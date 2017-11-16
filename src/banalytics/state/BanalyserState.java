package banalytics.state;

import banalytics.BanalyserException;
import banalytics.log.MediaLog;

public abstract class BanalyserState {
	final static public BanalyserState INITIAL= new InitialState();
	final static public BanalyserState PAUSED = new PausedState();
	final static public BanalyserState PLAYING = new PlayingState();
	final static public BanalyserState STOPPED = new StoppedState();
	final static public BanalyserState BUFFERING = new BufferingState();
	
	
	public BanalyserState start(MediaLog log,long pos){
		throw new BanalyserException();
	}
	public BanalyserState stop(MediaLog log,long pos){
		throw new BanalyserException();
	}
	public BanalyserState pause(MediaLog log,long pos){
		throw new BanalyserException();
	}
	public BanalyserState resume(MediaLog log,long time){
		throw new BanalyserException();
	}
	public BanalyserState move(MediaLog log,long source,long dest){
		throw new BanalyserException();
	}
	public BanalyserState buffer(MediaLog log,long pos){
		throw new BanalyserException();
	}
}
