package com.github.kanzomo.conference_track_management;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TrackTest {

	@Test
	void emptyTalks() {
		List<Talk> empty = new ArrayList<>();
		Track track = new Track(empty, "");
		List<Event> schedule = track.getSchedule();
		//the two events are Lunch and Networking
		assertEquals(2, schedule.size());
	}
	
	@Test
	void onlyMorningSession() {
		List<Talk> onlyMorning = new ArrayList<>();
		for (int i=1; i<=3; i++) {
			onlyMorning.add(new Talk("", TrackConfig.MORNING_SESSION_DURATION/3));
		}
		Track track = new Track(onlyMorning, "");
		List<Event> schedule = track.getSchedule();
		int size = schedule.size();
		String lastEvent = schedule.get(size-1).getTitle();
		String secondToLastEvent = schedule.get(size-2).getTitle();
		
		assertTrue(lastEvent.equals("Networking Event"));
		assertTrue(secondToLastEvent.equals("Lunch"));
	}
	
	@Test
	void dynamicNetworkingEventScheduling() {
		List<Talk> talks = new ArrayList<>();
		Talk fillMorning = new Talk("", TrackConfig.MORNING_SESSION_DURATION);
		int lessThanAfternoon = TrackConfig.AFTERNOON_SESSION_DURATION - 10;
		Talk fillAfternoon = new Talk("", lessThanAfternoon);
		talks.add(fillMorning);
		talks.add(fillAfternoon);
		Track track = new Track(talks, "");
		List<Event> schedule = track.getSchedule();
		LocalTime networkStart = schedule.get(schedule.size()-1).getStartTime();
		LocalTime expectedNetworkStart = TrackConfig.AFTERNOON_SESSION_START.plusMinutes(lessThanAfternoon);
		
		assertEquals(expectedNetworkStart, networkStart);
		
	}

}
