package banalytics.media;

import banalytics.log.MediaLog;
import banalytics.log.MusicLog;

public class Music extends Media {

	public Music(String author, String title, long duration) {
		super(author, title, duration);

	}

	@Override
	public MediaLog createLog() {

		return new MusicLog();
	}

	@Override
	protected String header() {
		
		return "Audio: ";
	}

}
