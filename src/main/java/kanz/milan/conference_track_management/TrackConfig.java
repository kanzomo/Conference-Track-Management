package kanz.milan.conference_track_management;

import java.time.LocalTime;

public class TrackConfig {
	
	public static final int MORNING_SESSION_DURATION = 180;
	
	public static final int AFTERNOON_SESSION_DURATION = 240;
	
	public static final LocalTime AFTERNOON_SESSION_START = LocalTime.of(13, 0);
	public static final LocalTime MORNING_SESSION_START = LocalTime.of(9, 0);
	public static final LocalTime LUNCH_START = LocalTime.of(12, 0);
	public static final LocalTime NETWORKING_EARLIEST_START = LocalTime.of(16, 0);
	public static final LocalTime NETWORKING_LATEST_START = LocalTime.of(17, 0);
	
}
