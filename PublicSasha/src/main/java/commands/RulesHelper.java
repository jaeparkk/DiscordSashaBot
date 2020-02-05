package commands;

import java.io.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import roles.MasterRoles;

public class RulesHelper extends ListenerAdapter {

	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String message = event.getMessage().getContentStripped();
		String[] messageA = event.getMessage().getContentStripped().split(" ");
		
		
		/**Helper for Rules.java**/
		//If the author of the message isn't a bot, the message doesn't say ".rules disable", or the rules is true, 
		//AND the rules channel equals the channel of the message AND the author isn't the MASTER
		if (!(event.getAuthor().isBot() || message.equalsIgnoreCase(".rules disable") || !Rules.getRules() == true)
				&& Rules.getRulesChannel().equals(event.getChannel().getId()) 
				&& !event.getMember().getUser().getAsTag().equals(MasterRoles.getMaster())) {
			event.getChannel().sendMessage("Boo").queue();
		}
		
		/**Helper for DiscordEvents.java**/
		if (!(event.getAuthor().isBot() || message.equalsIgnoreCase(".events disable")) && DiscordEvents.getEvents() == true
				&& DiscordEvents.getEventsChannel().equals(event.getChannel().getId())) {
			if (message.contains("Name:")) {
				// && message.contains("Reason:")
				FileWriter writer;
				PrintWriter p;
				try {
					writer = new FileWriter("Events.txt", true);
					p = new PrintWriter(writer);
					p.println(message);
					p.close();
					event.getChannel().sendMessage("Done!").queue();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				event.getChannel().sendMessage("Type your shit right " + event.getMember().getUser().getAsMention()).queue();
			}
			
			//event.getChannel().sendMessage("Hewwo").queue();
		}
	}
}
//