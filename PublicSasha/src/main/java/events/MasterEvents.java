package events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.*;
import net.dv8tion.jda.api.hooks.*;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

public class MasterEvents extends ListenerAdapter {
	
	private static final String MASTER = "Respect#3248";

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

		Member name = event.getMember();
		Role mod = event.getGuild().getRolesByName("Moderators", true).get(0);
		if (!event.getAuthor().isBot()) {
			
			String message = event.getMessage().getContentRaw().toLowerCase();
			String[] messageA = event.getMessage().getContentRaw().split(" ");
			
			//ban sequence
			if (message.contains("<@!592555124572815418> get the ban potato") || message.contains("<@!592555124572815418> where's the ban potato") ||
					message.contains("<@!592555124572815418> you ready") || message.contains("<@!592555124572815418> you up")) {
				banSequence(event, message);
				return;
			}
			if (containsBan(messageA) && (name.getUser().getAsTag().equals(getMaster()) || name.getRoles().contains(mod))) {
				banSequence2(event, messageA);
				return;
			}
			//greeting reply
			boolean greeting = greetingReply(message);
			if (greeting && messageA.length <= 3) {
				String[] greetings = {"Hi ", "Hello ", "Hewwo ", "Sup ", "Hey "};
				event.getChannel().sendMessage(greetings[new Random().nextInt(5)] + name.getAsMention() + "!").queue();
			}
			
			//"who is" 8ball
			if (message.contains("who is ") || message.contains("whos")) {
				if (messageA.length <= 6) {
					reply(event, message);
				}
			}
		}
	}
	
	public static boolean containsBan(String[] messageA) {
		for (String string : messageA) {
			if (string.equals("ban")) {
				return true;
			}
		}
		return false;
	}
	
	public static void banSequence(GuildMessageReceivedEvent event, String message) {
		File file = new File("Sasha1.gif");
		event.getChannel().sendFile(file).queue();
	}
	
	public static void banSequence2(GuildMessageReceivedEvent event, String[] message) {
		String banid = "";
		try {
			banid = event.getMessage().getMentionedMembers().get(0).getId();
			if(banid.equals("592555124572815418")) {
				banid = event.getMessage().getMentionedMembers().get(1).getId();
			} else if (banid.equals("171406801860820993")) {
				event.getChannel().sendMessage("Sorry, he's under my protection :3").queue();
				return;
			}
			
			File file = new File("Sasha2.gif");
			EmbedBuilder eb = new EmbedBuilder();
			eb.setColor(Color.RED);
			eb.setTitle("Ban");
			eb.setDescription(event.getGuild().getMemberById(banid).getAsMention() + " *has been banned.*");
			
			event.getChannel().sendFile(file).queue(e -> event.getChannel().sendMessage(eb.build()).queue());
			event.getGuild().getMemberById(banid).ban(1).queueAfter(1, TimeUnit.SECONDS);
			
			
		} catch (IndexOutOfBoundsException e) {
			event.getChannel().sendMessage(":eyes:").queue(e2 -> event.getChannel().sendMessage("Are we gonna ban someone?").queue());
		} catch (IllegalArgumentException e) {
			event.getChannel().sendMessage(":eyes:").queue(e3 -> event.getChannel().sendMessage("If you're gonna ban someone, you have to @ them! :3").queue());
		}
	}
	
	public static void reply(GuildMessageReceivedEvent event, String message) {
		if (message.contains("sasha")) {
			event.getChannel().sendMessage("A potato lover! :potato:").queue();
			return;
		} else if (message.contains("shaft god")) {
			event.getChannel().sendMessage("Only Shaft Gang members know :raised_hands:").queue();
			return;
		} else if (message.contains("mack")) {
			event.getChannel().sendMessage("Some kid that got yeeted recently :smirk:").queue();
			return;
		}
		
		int random = new Random().nextInt(11) + 1;
		
		switch (random) {
			case 1:
				event.getChannel().sendMessage("A loser.").queue();
				break;
			case 2:
				event.getChannel().sendMessage("A cool potato :sunglasses:").queue();
				break;
			case 3:
				event.getChannel().sendMessage("I dunno, some idiot :smile:").queue();
				break;
			case 4:
				event.getChannel().sendMessage("A nobody :smirk:").queue();
				break;
			case 5:
				event.getChannel().sendMessage(":clown:").queue();
				break;
			case 6:
				event.getChannel().sendMessage("Are you stupid, you *just* said their name.").queue();
				break;
			case 7:
				event.getChannel().sendMessage("Kinda busy right now, I'm eating some potatoes :3").queue();
				break;
			case 8:
				event.getChannel().sendMessage("I dunno, but they're ugly :nauseated_face:").queue();
				break;
			case 9:
				event.getChannel().sendMessage("Better question, who tf are YOU? :thinking:").queue();
				break;
			case 10:
				event.getChannel().sendMessage("The owner of this server :wink:").queue();
				break;
			case 11:
				event.getChannel().sendMessage("Who cares, I have this -> :potato: :D").queue();
				break;
		}
		
	}
	
	public boolean greetingReply(String message) {
		boolean reply = message.contains("<@592555124572815418>") || message.contains("<@!592555124572815418>") || message.contains("sasha");
		boolean reply2 = message.contains("hi") || message.contains("hello") || message.contains("hey") ||
				message.contains("yo") || message.contains("sup") || message.contains("whats up") || message.contains("wassup") ||
				message.contains("what's up") || message.contains("greetings") || message.contains("salutations");
		return reply && reply2;
	}
	
	public static void log(User user, String title, String description, TextChannel channel, Color color) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(title);
		eb.setDescription(user.getAsMention() + description);
		eb.setColor(color);
		channel.sendMessage(eb.build()).queue();
	}
	
	public static void log(User user, String title, String description, TextChannel channel, Color color, String reason) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(title);
		eb.setDescription(user.getAsMention() + description);
		channel.sendMessage(eb.build()).queue();
	}
	
	public static String getMaster() {
		return MASTER;
	}
}

/***Previous stuff***/

//if (messageA[0].toLowerCase().equals(".random")) {
//	event.getChannel().sendMessage(String.valueOf(new Random().nextInt(Integer.parseInt(messageA[1]) + 1))).queue();
//}


//switch (message) {
//	case "hi <@592555124572815418>":
//		event.getChannel().sendMessage("Hi " + name.getAsMention() + "!").queue();
//		break;
//	case "hello <@!592555124572815418>":
//		event.getChannel().sendMessage("Hi " + name.getAsMention() + "!").queue();
//		break;
//	case "wassup <@!592555124572815418>":
//		event.getChannel().sendMessage("Hi " + name.getAsMention() + "!").queue();
//		break;
//	case "sup <@!592555124572815418>":
//		event.getChannel().sendMessage("Hi " + name.getAsMention() + "!").queue();
//		break;
//	case "whats up <@!592555124572815418>":
//		event.getChannel().sendMessage("Hi " + name.getAsMention() + "!").queue();
//		break;
//	case "what's up <@!592555124572815418>":
//		event.getChannel().sendMessage("Hi " + name.getAsMention() + "!").queue();
//		break;
//	case "hey <@!592555124572815418>":
//		event.getChannel().sendMessage("Hi " + name.getAsMention() + "!").queue();
//		break;
//	case "whatcha doin <@!592555124572815418>":
//		if (name.getUser().getAsTag().equals(getMaster())) {
//			event.getChannel().sendMessage("Baking potatoes because you ran out :3").queue();
//		} else {
//			event.getChannel().sendMessage("Baking potatoes because <@171406801860820993> ran out :3").queue();
//		}
//}


//if (message.equals("hi <@592555124572815418>")) {
//	event.getChannel().sendMessage("Hi " + name.getAsMention() + "!").queue();
//	
//} else if (message.equals("Whatcha doin <@592555124572815418>")) {
//	if (name.getUser().getAsTag().equals(getMaster())) {
//		event.getChannel().sendMessage("Baking potatoes because you ran out :3").queue();
//	} else {
//		event.getChannel().sendMessage("Baking potatoes because <@171406801860820993> ran out :3").queue();
//	}
//	
//} else if (message.toLowerCase().contains("pfp") && message.contains("<@592555124572815418>")
//		&& (message.toLowerCase().contains("where") || message.toLowerCase().contains("why"))) {
//	event.getChannel().sendMessage("Someone said they would make me a pretty pfp but they have yet to :(").queue();
//} else if (messageA[0].toLowerCase().equals(".random")) {
//	event.getChannel().sendMessage(String.valueOf(new Random().nextInt(Integer.parseInt(messageA[1])) + 1)).queue();
//} else if (message.toLowerCase().contains("who is ")) {
//	if (message.toLowerCase().contains("levi") || message.toLowerCase().contains("<@171406801860820993>")) {
//		event.getChannel().sendMessage("A cool guy :sunglasses:").queue();
//	} else {
//		event.getChannel().sendMessage("I dunno, some idiot :smile:").queue();
//	}
//	
//}