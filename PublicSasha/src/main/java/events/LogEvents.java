package events;


import java.awt.Color;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import commands.*;

public class LogEvents extends ListenerAdapter {
	
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		if (Log.getLogChannelCheck() == true) {
			MasterEvents.log(event.getMember().getUser(), "Member Join Report",
				" has joined the server.", event.getGuild().getTextChannelById(Log.getLogChannel()), Color.GREEN);
		}
		
	}
	
	public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
		if (Log.getLogChannelCheck() == true) {
			MasterEvents.log(event.getMember().getUser(), "Member Leave Report",
				" has left the server.", event.getGuild().getTextChannelById(Log.getLogChannel()), Color.RED);
		}
		
	}
	
}
