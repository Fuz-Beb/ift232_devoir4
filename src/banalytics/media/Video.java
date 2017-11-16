package banalytics.media;

import banalytics.log.MediaLog;
import banalytics.log.VideoLog;

public class Video extends Media{

	public Video(String author, String title,long duration) {
		super(author, title, duration);
		
	}

	@Override
	public MediaLog createLog() {
		
		return new VideoLog();
	}

	@Override
	protected String header() {
		
		return "Video: ";
	}

}
