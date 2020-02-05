package commands;


import java.io.*;
import java.util.Scanner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class AddtoFile extends Command {
	
	public AddtoFile() {
		super.name = "addtofile";
		super.arguments = "filename.txt, arg";
		super.help = "Adds the argument to the text file.";
		super.ownerCommand = true;
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] messageA = event.getMessage().getContentRaw().split(" ");
		
		//error checks argument length
		if (messageA.length < 3)
			event.reply("You need to add more arguments.");
		
		String filename = messageA[1];
		String string = messageA[2].toLowerCase();
		
		File file = new File(filename);
		
		try {
			if (checkContainsFile(file, string) == false) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				bw.write(string + "\n");
				event.reply("\"" + string + "\" successfully added to \"" + filename + "\".");
				bw.close();
			} else {
				event.reply("\"" + string + "\" is already in \"" + filename + "\".");
			}
		} catch (IOException e){
			event.reply("This file does not exist");
			try {
				file.createNewFile();
				event.reply("New file " + filename + " has been created.");
			} catch (IOException e1) {
				event.reply("I don't know what the fuck just happened.");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	/**checks if the desired input (args[2]) is already in the file. Returns true if so, false otherwise. **/
	public static boolean checkContainsFile(File file, String input) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (line.equals(input)) {
				scanner.close();
				return true; 
			}
				
		}
		scanner.close();
		return false;
	}

}
