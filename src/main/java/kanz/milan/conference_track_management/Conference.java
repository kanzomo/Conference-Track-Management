package kanz.milan.conference_track_management;

import java.util.ArrayList;
import java.util.List;

public class Conference {
	
	private List<Track> tracks = new ArrayList<>();
	
	public void buildSchedule(List<Talk> talks) {
		int trackCount = 1;
		while (talks.size() > 0) {
			Track currentTrack = new Track(talks, ""+ trackCount);
			tracks.add(currentTrack);
			trackCount++;
		}
	}
	
	public List<Track> getSchedule() {
		return tracks;
	}
	
}
