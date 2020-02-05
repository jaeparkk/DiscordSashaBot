package commands;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import roles.MasterRoles;

public class DiscordEvents extends Command {
	
	private static boolean events;
	private static String eventsChannel;
	
	public DiscordEvents() {
		super.name = "events";
		super.arguments = "enable/disable";
		super.help = "Toggles the DiscordEvents event.";
		super.ownerCommand = true;
		events = false;
	}

	@Override
	protected void execute(CommandEvent event) {
		
		String message = event.getMessage().getContentStripped();
		String[] messageA = event.getMessage().getContentStripped().split(" ");
		
		//debug - checks the enable/disable argument
		//event.reply(messageA[1]);
		
		//if (event.getMember().getUser().getAsTag().equals(MasterRoles.getMaster())) {
			try {
				if (messageA[1].equalsIgnoreCase("enable")) {
					events = true;
					event.reply("Enabled!");
					//event.reply(event.getChannel().getId());
					eventsChannel = event.getChannel().getId();
				} else if (messageA[1].equalsIgnoreCase("disable")) {
					events = false;
					event.reply("Disabled!");
					eventsChannel = "";
				} else if (messageA[1].equalsIgnoreCase("showresults")) {
					Scanner scanner;
					try {
						scanner = new Scanner(new File("Events.txt"));
						String string = "";
						while (scanner.hasNextLine()) {
							if (string.length() < 2000) {
								string += scanner.nextLine();
							} else {
								event.reply(string);
								string = "";
							}
							//System.out.println(scanner.nextLine());
							
							
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("User did not enter the correct argument.");
				event.reply("You need to specify enable/disable");
			}
				
			
			//event.reply(Boolean.toString(rules));
		//}
		
	}
	
	public static boolean getEvents() {
		return events;
	}
	
	public static String getEventsChannel() {
		return eventsChannel;
	}
}
