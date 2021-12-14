package com.github.kanzomo.conference_track_management;

import java.io.FileNotFoundException;
import java.util.List;

import com.github.kanzomo.conference_track_management.io.FileReader;
import com.github.kanzomo.conference_track_management.io.Writer;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		String filePath = null;
		if (args.length == 0) {
			System.out.println("Try again with file path as argument.");
			return;
		} else {
			filePath = args[0];
		}
		
		List<Talk> talks = FileReader.getInstance().read(filePath);
		Conference conference = new Conference();
		conference.buildSchedule(talks);
		Writer writer = new Writer(conference);

		writer.toConsole();
	}
	

}
