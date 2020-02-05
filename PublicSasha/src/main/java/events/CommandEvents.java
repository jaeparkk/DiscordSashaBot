package events;

import java.util.*;
import java.util.concurrent.*;

import commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**This class is a helper class for all the commands in the command folder**/
public class CommandEvents extends ListenerAdapter {
	
	static List<Role> roles;

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String messageReceived = event.getMessage().getContentStripped();
		String[] messageA = event.getMessage().getContentStripped().split(" ");
		
		
		try {
			roles = Arrays.asList(event.getGuild().getRolesByName("Moderators", true).get(0),
				event.getGuild().getRolesByName("Assistant", true).get(0), event.getGuild().getRolesByName("Official  Staff", true).get(0));
		} catch (Exception e) {
			
		} finally {
			
		}
		
			//To prevent the bot replying to its own messages
			if (!event.getAuthor().isBot()) {
				
				/**TechBug code starts here**/
				if(TechBug.getTechBug() == true && event.getChannel().getId().equals(TechBug.getTechBugChannel())
						&& !userRole(event.getMember(), roles)) {
					
					//This is for pic/vid messages
					if (messageReceived.equals(""))
						return;
					
					if (!messageReceived.equalsIgnoreCase(".techbug disable")) {
						String[] inputList = {"version:", "cell phone model:", "server:", "character name:", "bug description", "screenshot"};
						int count = 0;
						
						//Implement later
						int muteCount = 0;
						
						//Counts if the message contains all(or more) of the inputList strings
						for (String string : inputList) {
							if (messageReceived.toLowerCase().contains(string)) {
								count++;
							}
						}
						
						//If the message doesn't contain the inputList strings, deletes the member's message and tells them to follow instructions
						if (count < 6) {
							event.getMessage().delete().queueAfter(1, TimeUnit.SECONDS);
							ebBuilder(event, "Please follow the format provided in the pinned message.");
							//MasterEvents.log(event.getAuthor(), event.getGuild().getTextChannelById(TechBug.getTechBugChannel()).getAsMention() + " Report"
							//		, description, channel, color);
						}
							
					} else {
						//This part is for when people without permissions try to type '.techbug disable'
						event.getMessage().delete().queueAfter(1, TimeUnit.SECONDS);
						ebBuilder(event, "Baka. You don't have enough :potato: points for this command :stuck_out_tongue:");
						
					}
			
				} else if (TimerEvents.getStamina()) {
					if (messageReceived.equals("check")) {
						
					}
				}
			}
		
	}
	
	//Helper method to create and send an embedbuilder message.
	public void ebBuilder(GuildMessageReceivedEvent event, String titleMessage) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(titleMessage);
		event.getChannel().sendMessage(eb.build()).queue(message -> message.delete().queueAfter(6, TimeUnit.SECONDS));
		
	}
	
	//Helper method for techbug, which says whether or not the member has the right permissions
	public boolean userRole(Member member, List<Role> roles) {
		List<Role> userRoles = member.getRoles();
		for (Role role : userRoles) {
			if (roles.contains(role))
				return true;
		}
		
		return false;
	}
	
}