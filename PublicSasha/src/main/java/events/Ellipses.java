package events;

import events.MasterEvents;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ellipses extends ListenerAdapter{

	//• • •
	
	//Deletes sentences that contain and end with ellipses
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		int count = 0;
		int ccount = 0;
		
		//If the author of the message is not a bot, create an embed message that 
		if (!event.getAuthor().isBot()) {
			
			//splits the message into an array of the words, and counts the number of ellipses.
			String[] message = event.getMessage().getContentStripped().split(" ");
			for (int i = 0; i < message.length; i++) {
				String[] word = message[i].split("");
				for (String s: word) {
					if (s.equals("."))
						count++;
					if (s.equals("•"))
						ccount++;
					
				}
			}
			String[] beg = message[0].split("");
			String[] end = message[message.length-1].split("");
			for (String s: beg) {
				//event.getChannel().sendMessage(s).queue();
			}
			//event.getChannel().sendMessage(beg[0]).queue();
			if (beg[0].equals(".") && beg[1].equals(".") || count >= 4 || ccount >= 1) {
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("NO ELLIPSES.");
				event.getChannel().sendMessage(eb.build()).queue();
				event.getMessage().delete().queue();
				//event.getChannel().sendMessage("Hi").queue();
				
			}
		}
			
	}
	
	public void onMessageDelete(MessageDeleteEvent event) {
		//event.
	}


}
