package banalytics.media;

import banalytics.log.MediaLog;

/*
 * Structure représentant un contenu multimédia.
 */

public abstract class Media {

	

	private String author;
	private String title;
	
	private long duration;

	public abstract MediaLog createLog();

	protected abstract String header();

	

	public long getDuration(){
		return duration;
	}
	public Media(String author, String title, long duration) {

		this.author = author;
		this.title = title;
		
		this.duration = duration;
	}

	public String toString() {

		String res = "";

		res += header();

		long hours = duration / (3600 * 1000);
		long minutes = (duration % (3600 * 1000)) / (60 * 1000);
		long seconds = ((duration) % (60 * 1000)) / 1000;
		res += author + ": " + title + " (" + hours + "h" + minutes + "m" + seconds + "s)";

		return res;

	}

	/* Méthode equals */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Media))
			return false;
		Media other = (Media) obj;

		if (!author.equals(other.author))
			return false;
		if (duration != other.duration)
			return false;
		if (!title.equals(other.title))
			return false;
		
		return true;
	}

}
