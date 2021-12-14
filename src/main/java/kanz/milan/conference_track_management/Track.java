package kanz.milan.conference_track_management;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Track {
	
	enum Session {
		MORNING,
		AFTERNOON
	}
	
	private List<Event> morningSession = new ArrayList<>();
	private List<Event> afternoonSession = new ArrayList<>();
	private List<Event> fullSchedule = new ArrayList<>();
	private String id;
	
	public Track(List<Talk> talks, String trackID) {
		id = trackID;
		fillSession(talks, Session.MORNING);
		fillSession(talks, Session.AFTERNOON);
		fullSchedule.addAll(morningSession);
		fullSchedule.addAll(afternoonSession);
	}
	
	private void fillSession(List<Talk> unscheduledTalks, Session session) {
		int remainingMinutes = 0;
		List<Event> sessionEvents = null;
		LocalTime currentTime = null;
		if (session == Session.MORNING) {
			remainingMinutes = TrackConfig.MORNING_SESSION_DURATION;
			currentTime = TrackConfig.MORNING_SESSION_START;
			sessionEvents = morningSession;
		} else if (session == Session.AFTERNOON) {
			remainingMinutes = TrackConfig.AFTERNOON_SESSION_DURATION;
			currentTime = TrackConfig.AFTERNOON_SESSION_START;
			sessionEvents = afternoonSession;
		}
		
		List<Talk> toBeRemoved = new ArrayList<>();
		int i = 0;
		while (remainingMinutes >= 5 && unscheduledTalks.size() > 0 && i<unscheduledTalks.size()) {
			Talk talk = unscheduledTalks.get(i);
			int talkDuration = talk.getLengthMinutes();
			if (talkDuration <= remainingMinutes) {
				sessionEvents.add(new Event( talk.getTitle(), talkDuration, currentTime) );
				currentTime = currentTime.plusMinutes(talkDuration);
				remainingMinutes -= talkDuration;
				//talks shouldn't be removed immediately to preserve index integrity during loop
				toBeRemoved.add(talk);
			}
			i++;
		}
		unscheduledTalks.removeAll(toBeRemoved);
		addNonTalkEvents(session, currentTime);
	}
	
	private void addNonTalkEvents(Session s, LocalTime currentTime) {
		if (s == Session.MORNING) {
			morningSession.add(new Event("Lunch", 0, TrackConfig.LUNCH_START));
		} else if (s == Session.AFTERNOON) {
			boolean later = currentTime.compareTo(TrackConfig.NETWORKING_EARLIEST_START) > 0;
			boolean beforeLatest = currentTime.compareTo(TrackConfig.NETWORKING_LATEST_START) < 0;
			LocalTime networkStart;
			if (!later) {
				networkStart = TrackConfig.NETWORKING_EARLIEST_START;
			} else if (later && beforeLatest) {
				networkStart = currentTime;
			} else {
				networkStart = TrackConfig.NETWORKING_LATEST_START;
			}
			afternoonSession.add(new Event("Networking Event", 0, networkStart));
		}
			
	}
	
	public List<Event> getSchedule() {
		return fullSchedule;
	}
	
	public String getID() {
		return id;
	}
	
}
