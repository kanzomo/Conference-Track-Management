package kanz.milan.conference_track_management;

public class Talk {
	private int lengthMinutes;
	private	String title;
	
	public Talk(String title, int length) {
		this.title = title.trim();
		this.lengthMinutes = length;
	}
	
	public int getLengthMinutes() {
		return lengthMinutes;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String toString() {
		return title + " " + lengthMinutes + "min";
	}
}
