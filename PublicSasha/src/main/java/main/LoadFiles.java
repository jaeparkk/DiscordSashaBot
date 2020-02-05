package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadFiles {
	
	/** Reads a file and puts contents in a String ArrayList **/
	public ArrayList<String> fileToStringArray(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		ArrayList<String> array = new ArrayList<String>();
		
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			array.add(input);
		}
		scanner.close();
		return array;
	}
}
