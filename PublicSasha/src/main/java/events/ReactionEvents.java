package events;

import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionEvents extends ListenerAdapter {
	
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
		
		if (!event.getUser().isBot()) {
			
			//if the command is enabled and the channel is the correct channel AND the message ID of the reaction is the same as the stamina message:
			if (TimerEvents.getStamina() && event.getChannel().getId().equals(TimerEvents.getStaminaChannel())
					&& event.getMessageId().equals(TimerEvents.getStaminaID())) {
				
				Role staminaRole = event.getGuild().getRolesByName("Stamina", true).get(0);
				
				if (event.getReactionEmote().getName().equals("âœ…")) {
					if (!event.getMember().getRoles().contains(staminaRole)) {
						event.getChannel().sendMessage(event.getMember().getAsMention() + ", you will now receive stamina notifications.").queue(
							message -> message.delete().queueAfter(3, TimeUnit.SECONDS));
						event.getGuild().addRoleToMember(event.getMember(), staminaRole).queue();

					} else {
						event.getChannel().sendMessage(event.getMember().getAsMention() + ", you already have the Stamina role.").queue(
								message -> message.delete().queueAfter(3, TimeUnit.SECONDS));

					}
					
				} else if (event.getReactionEmote().getName().equals("â�Œ") && event.getMember().getRoles().contains(staminaRole)) {
					
					event.getChannel().sendMessage(event.getMember().getAsMention() + ", you have removed yourself from receiving stamina notifications.").queue(
							message -> message.delete().queueAfter(3, TimeUnit.SECONDS));
					event.getGuild().removeRoleFromMember(event.getMember(), staminaRole).queue();
				}
			}
		}
	}
}
