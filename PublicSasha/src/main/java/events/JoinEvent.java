package events;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinEvent extends ListenerAdapter {

	public void onGuildJoin(GuildJoinEvent event) {
		event.getGuild().getSystemChannel().sendMessage("Welcome! Would you like a potato? :D").queue(); 
	}
}


