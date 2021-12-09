package kanz.milan.conference_track_management.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kanz.milan.conference_track_management.Talk;

public class FileReader {
	
	private static FileReader instance;
	
	private FileReader() {}
	
	public static FileReader getInstance() {
		if (instance == null) {
			instance = new FileReader();
		}
		return instance;
	}
	
	public List<Talk> read(String filename) throws FileNotFoundException {
		List<Talk> talks;
		File inputFile = new File(filename);
		Scanner sc = null;
		sc = new Scanner(inputFile);
		talks = parseFile(sc);
		sc.close();
		return talks;
	}
	
	private List<Talk> parseFile(Scanner sc) {
		List<Talk> talks = new ArrayList<>();
		while (sc.hasNext()) {
			//this is done to make sure the array after split has no empty elements
			String currentLine = sc.nextLine().trim();
			//split by whitespace
			String[] words = currentLine.split("\\s+");
			String title = "";
			int length = words.length;
			for (int i=0; i<length; i++) {
				boolean lastElement = i==length-1;
				if (lastElement) {
					int duration = removeLettersToInt(words[i]);
					if (duration >= 0) {
						talks.add(new Talk(title, duration));
					} else {
						//if duration is invalid, skip entry
						break;
					}
				} else {
					title += words[i] +" ";
				}
			}
		}
		return talks;
	}
	
	private int removeLettersToInt(String length) {
		int filtered = 5;
		if (!length.equals("lightning")) {
			length = length.replaceAll("[^0-9]", "");
			if (length.isEmpty()) {
				//mark duration as invalid
				filtered = -1;
			} else {
				filtered = Integer.parseInt(length);
			}
		}
		return filtered;
	}
}
