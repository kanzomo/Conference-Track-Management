package kanz.milan.conference_track_management;

public class Talk {
	private int durationMinutes;
	private	String title;
	
	public Talk(String title, int durationMinutes) {
		this.title = title;
		this.durationMinutes = durationMinutes;
	}
	
	public int getLengthMinutes() {
		return durationMinutes;
	}
	
	public String getTitle() {
		return title;
	}
	
}
