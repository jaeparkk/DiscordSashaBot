package commands;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class TechBug extends Command {

	private static boolean techbug;
	private static String techbugChannel;
	
	public TechBug () {
		super.name = "techbug";
		super.arguments = "enable/disable";
		super.ownerCommand = true;
		super.help = "Toggles the techbug autodelete feature. If you don't have the correct format,"
				+ " then your message will be automatically deleted. Look at the pinned message in this channel for help.";
		techbug = false;
	}

	@Override
	protected void execute(CommandEvent event) {
		
		String message = event.getMessage().getContentStripped();
		String[] messageA = event.getMessage().getContentStripped().split(" ");
		
		/**debug - checks the enable/disable argument**/
		//event.reply(messageA[1]);
		
			try {
				if (messageA[1].equalsIgnoreCase("enable")) {
					techbug = true;
					event.getMessage().delete().queueAfter(2, TimeUnit.SECONDS);
					event.reply("Enabled!", m -> m.delete().queueAfter(3, TimeUnit.SECONDS));
					techbugChannel = event.getChannel().getId();
				} else if (messageA[1].equalsIgnoreCase("disable")) {
					techbug = false;
					event.getMessage().delete().queueAfter(2, TimeUnit.SECONDS);
					event.reply("Disabled!", m -> m.delete().queueAfter(3, TimeUnit.SECONDS));
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("User did not enter the correct argument.");
				event.reply("You need to specify enable/disable");
			}
		
	}
	
	public static boolean getTechBug() {
		return techbug;
	}
	
	public static String getTechBugChannel() {
		return techbugChannel;
	}
}
