package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinVoiceChannel extends Command {

	public JoinVoiceChannel() {
		super.name = "vc";
		super.aliases = new String[] {"voicechannel"};
		super.arguments = "join/leave";
		super.help = "Join/Leaves a voice channel.";
		super.ownerCommand = true;
	}

	@Override
	protected void execute(CommandEvent event) {
		VoiceChannel channel = event.getMember().getVoiceState().getChannel();
		
		if(channel == null) {
			event.reply("You're not in a voice channel!");
			return;
		}
		VoiceChannel bot = event.getGuild().getSelfMember().getVoiceState().getChannel();
		AudioManager manager = event.getGuild().getAudioManager();
		if (bot == null) {
			manager.openAudioConnection(channel);
			event.reply("Connected!");
		} else {
			event.reply("Already connected to a voice channel!");
		}
		
		
	}
}
