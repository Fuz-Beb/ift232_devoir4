package banalytics.state;

import banalytics.log.MediaLog;

public class PlayingState extends BanalyserState {

	@Override
	public BanalyserState stop(MediaLog log, long pos) {
		log.closePlaySegment(pos);
		return STOPPED;
	}

	@Override
	public BanalyserState pause(MediaLog log, long pos) {
		log.openPauseEntry(pos);
		return PAUSED;
	}

	@Override
	public BanalyserState move(MediaLog log, long source,long dest) {
		log.openPauseEntry(source);
		log.addMoveEntry(dest);
		log.closePauseEntry(0);
		return PLAYING;
	}

	@Override
	public BanalyserState buffer(MediaLog log, long pos) {
		log.openBufferingEntry(pos);
		return BUFFERING;
	}

}
