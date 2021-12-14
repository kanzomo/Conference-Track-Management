package com.github.kanzomo.conference_track_management.io;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.github.kanzomo.conference_track_management.Conference;
import com.github.kanzomo.conference_track_management.Event;
import com.github.kanzomo.conference_track_management.Track;

public class Writer {
	
	private Conference conference;
	
	public Writer(Conference conference) {
		this.conference = conference;
	}
	
	public void toConsole() {
		List<Track> tracks = conference.getSchedule();
		String output = "";
		
		for (Track track : tracks) {
			output += "Track " + track.getID() + ":\n";
			List<Event> events = track.getSchedule();
			for (Event event : events) {
				
				String lengthString;
				int duration = event.getDurationMinutes();
				if (duration == 5) {
					lengthString = "lightning";
				} else if (duration == 0) {
					lengthString = "";
				} else {
					lengthString = duration + "min";
				}
				LocalTime time = event.getStartTime();
				DateTimeFormatter amPM = DateTimeFormatter.ofPattern("hh:mm a");
				String timeString = time.format(amPM);
				output += timeString + " " + event.getTitle() + " " + lengthString + "\n";
				
			}
			output += "\n";
		}
		System.out.println(output);
	}
	
}
