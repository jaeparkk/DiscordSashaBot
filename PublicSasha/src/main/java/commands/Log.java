package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Log extends Command {
	
	private static String logChannel;
	private static boolean logChannelCheck;
	
	public Log() {
		super.name = "log";
		super.aliases = new String[] {"logcheck"};
		super.arguments = "enable/disable";
		super.help = "Enables/disables log events in the desired channel.";
		super.ownerCommand = true;
		logChannelCheck = false;
		logChannel = "";
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] messageA = event.getMessage().getContentRaw().split(" ");
		
		if (messageA.length > 2)
			event.reply("Too many args for this command.");
		
		if (messageA[0].equals(".logcheck")) {
			if (logChannel.equals("")) {
				event.reply("There is no log channel.");
			} else {
				event.reply(event.getGuild().getTextChannelById(logChannel).getAsMention() + " " + logChannelCheck);
			}
			
		} else if (messageA[0].equals(".log")) {
			if (messageA[1].equals("enable")) {
				setLogChannel(event.getChannel().getId());
				setLogChannelCheck(true);
				event.reply("Logs have been set up in this channel.");
			} else {
				setLogChannelCheck(false);
				event.reply("Logs have been disabled in this channel.");
			}
			
		}
		
	}

	public static String getLogChannel() {
		return logChannel;
	}
	
	public static boolean getLogChannelCheck() {
		return logChannelCheck;
	}
	
	public void setLogChannel(String log) {
		logChannel = log;
	}
	
	public void setLogChannelCheck(boolean bool) {
		logChannelCheck = bool;
	}
}
