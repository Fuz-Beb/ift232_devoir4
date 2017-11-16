package banalytics.log;

public class MusicLog extends MediaLog {

	
	public MusicLog() {
		super();
	}

	

	@Override
	public String toString() {

		String res = "----------Music usage log----------\n";

		for (LogEntry entry : entries) {

			res += entry+"\n";
		}

		return res;

	}
	
	@Override
	public boolean equals(Object other) {

		if (!(other instanceof MusicLog)) {
			return false;
		}
		return super.equals(other);
	}
	

}
