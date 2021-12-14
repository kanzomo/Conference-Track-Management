package kanz.milan.conference_track_management;

import java.time.LocalTime;

public class Event {
	private String title;
	private int durationMinutes;
	private LocalTime startTime;
	
	public Event(String title, int durationMinutes, LocalTime start) {
		this.title = title.trim();
		this.durationMinutes = durationMinutes;
		this.startTime = start;
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}
	
	public int getDurationMinutes() {
		return durationMinutes;
	}
	
	public String getTitle() {
		return title;
	}

}
