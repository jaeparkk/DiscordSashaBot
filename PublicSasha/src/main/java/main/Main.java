package main;



import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import commands.*;
import events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Activity.ActivityType;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		JDA sasha = new JDABuilder("A secret code :3").build();
		
		EventWaiter waiter = new EventWaiter();
		CommandClientBuilder builder = new CommandClientBuilder();
		builder.setOwnerId("592555124572815418");
		builder.setCoOwnerIds("171406801860820993");
		builder.setPrefix(".");
		builder.setHelpWord("help");
		builder.setActivity(Activity.watching("my 🥔 bake :3 "));
		
		
		builder.addCommand(new Log());
		builder.addCommand(new Rules());
		builder.addCommand(new DiscordEvents());
		builder.addCommand(new TimerEvents());
		builder.addCommand(new TechBug());
		builder.addCommand(new AddtoFile());
		builder.addCommand(new JoinVoiceChannel());
		
		CommandClient client = builder.build();
	
//		sasha.addEventListener(new LogEvents());
//		//sasha.addEventListener(new ReactionEvents());
//		sasha.addEventListener(new MasterEvents());
//		sasha.addEventListener((new RulesHelper()));
//		sasha.addEventListener(new CommandEvents());
//		sasha.addEventListener(new JoinEvent());
//		//sasha.addEventListener(new Ellipses());
//		//sasha.addEventListener(new Prune());
		
		
		sasha.addEventListener(client);
		sasha.addEventListener(waiter);
		
		/*For mentions(i.e. <@id>), if you include the "!" like <@!id> it only responds to the owner. Otherwise, it responds to everyone else.
		 * See MasterEvents for more details.
		 */
	}
}

