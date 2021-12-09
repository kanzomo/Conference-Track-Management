package kanz.milan.conference_track_management.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import kanz.milan.conference_track_management.Talk;

class FileReaderTest {
	
	FileReader reader = FileReader.getInstance();
	
	//HAPPY PATH
	@Test
	void testAssignmentTalksTotalNumber() throws FileNotFoundException  {
		List<Talk> talks = reader.read("assignment_input.txt");
		assertEquals(19, talks.size());
	}
	
	@Test
	void testAssignmentTalksTotalDuration() throws FileNotFoundException {
		List<Talk> talks = reader.read("assignment_input.txt");
		int duration = 0;
		for (Talk talk : talks) {
			duration += talk.getLengthMinutes();
		}
		assertEquals(785, duration);
	}
	
	//UNHAPPY PATH
	@Test
	void testFileNotFoundException() {
		assertThrows(FileNotFoundException.class, () -> {
			reader.read("nonexistant_file");
		});
	}
	
	@Test
	void testEmptyFile() throws FileNotFoundException {
		List<Talk> talks = reader.read("empty_file.txt");
		assertEquals(0, talks.size());
	}
	
	@Test
	void testTalkWithoutDuration() throws FileNotFoundException {
		List<Talk> talks = reader.read("no_duration.txt");
		assertEquals(0, talks.size());
	}
}
