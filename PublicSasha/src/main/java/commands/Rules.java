package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Rules extends Command {
	
	private static boolean rules;
	private static String rulesChannel;
	
	//private EventWaiter waiter;
	
	public Rules() {
		super.name = "rules";
		super.arguments = "enable/disable";
		super.help = "Toggles the .iam Community thing in the #rules channel.";
		super.ownerCommand = true;
		rules = false;
		rulesChannel = "";
		
		//this.waiter = waiter;
		
	}
	
	@Override
	protected void execute(CommandEvent event) {
		
		String message = event.getMessage().getContentStripped();
		String[] messageA = event.getMessage().getContentStripped().split(" ");
		
		//debug - checks the enable/disable argument
		//event.reply(messageA[1]);
		
			try {
				if (messageA[1].equalsIgnoreCase("enable")) {
					rules = true;
					event.reply("Enabled!");
					rulesChannel = event.getChannel().getId();
				} else if (messageA[1].equalsIgnoreCase("disable")) {
					rules = false;
					event.reply("Disabled!");
					//rulesChannel = "";
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("User did not enter the correct argument.");
				event.reply("You need to specify enable/disable");
			}
			//event.reply(Boolean.toString(rules));
	}
	
	public static boolean getRules() {
		return rules;
	}
	
	public void setRules(boolean b) {
		rules = b;
	}
	
	public static String getRulesChannel() {
		return rulesChannel;
	}
	
	public void setRulesChannel(String channel) {
		rulesChannel = channel;
	}
	
}
