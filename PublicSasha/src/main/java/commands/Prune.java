package commands;

import java.util.List;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Prune extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] message = event.getMessage().getContentStripped().split(" ");
		List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(message[1])).complete();
		if (message[0].equals("delete")) {
			//for (int i = 0; i < 100; i++) {
			event.getChannel().purgeMessages(messages);
			
		}
		if (message[0].equals("prune")) {
			for (int i = 0; i < 100; i++) {
				List<Message> x = event.getChannel().getHistory().retrievePast(50).complete();
				//x.delete();
				event.getChannel().deleteMessages(x).queue();
				
			}
		}
	}
}
