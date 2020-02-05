package events;



import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.role.update.RoleUpdateMentionableEvent;



public class TimerEvents extends Command {
	
	private static boolean stamina;
	private static String staminaChannel;
	private static String staminaID;

	
	public TimerEvents() {
		super.name = "stamina";
		super.arguments = "enable/disable";
		super.ownerCommand = true;
		super.help = "Toggles the stamina notifications.";
		stamina = false;
	}

	@Override
	protected void execute(CommandEvent event) {
		String message = event.getMessage().getContentStripped();
		String[] messageA = event.getMessage().getContentStripped().split(" ");
		
		Role staminaRole = event.getGuild().getRolesByName("Stamina", true).get(0);
		
		if (!event.getAuthor().isBot()) {
			try {
				if (messageA[1].equalsIgnoreCase("enable")) {
					stamina = true;
					event.reply("Enabled!");
					staminaChannel = event.getChannel().getId();

					//debug
					//Calendar now = Calendar.getInstance();
					//event.reply(now.getTime().toString());

					staminaTimer(event, 19, staminaRole, 1);
					staminaTimer(event, 1, staminaRole, 2);
					staminaTimer(event, 5, staminaRole, 3);
					
				} else if (messageA[1].equalsIgnoreCase("disable")) {
					stamina = false;
					event.reply("Disabled!");
					staminaChannel = "";

				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("User did not enter the correct argument.");
				event.reply("You need to specify enable/disable");
			}
		}
			
	}
	
	public void staminaTimer(CommandEvent event, int dateHourOfDay, Role staminaRole, int num) {
		Timer timer = new Timer();
		Calendar staminaCalendar = createStaminaCalendar(dateHourOfDay);
		
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				staminaMessage(event, num, staminaRole);
			}
		}, staminaCalendar.getTime(), 1000 * 60 * 60 * 24);	
		
		//debug
		//event.reply(staminaCalendar.getTime().toString());

	}
	
	//creates a calendar for a stamina alert. However, we will only be using the Date method of the Calendar.
	//Compares 'now' to the stamina alert time/date. If 'now' is after the time/date,
	//then 24 hours is added to the time/date of the stamina alert
	public Calendar createStaminaCalendar(int dateHourOfDay) {
		
		Calendar calendar = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		
		calendar.set(Calendar.HOUR_OF_DAY, dateHourOfDay);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		if (now.getTime().after(calendar.getTime())) {
			calendar.add(Calendar.DATE, 1);
		}
		
		return calendar;
	}
	
	public void staminaMessage(CommandEvent event, int number, Role role) {
		EmbedBuilder eb = new EmbedBuilder();
		
		eb.setTitle("\n\t\t:potato::potato::potato: Stamina #" + number + " is available! :potato::potato::potato:");
		eb.setDescription("If you would like to receive stamina notifications, head over to "
		+ event.getGuild().getTextChannelById("589245266503270410").getAsMention() + " and self-assign yourself the Stamina role!");
		role.getManager().setMentionable(true).queue(
				mentionMessage -> event.getChannel().sendMessage(role.getAsMention()).queue(
					mentionFalse -> role.getManager().setMentionable(false).queue(
						staminaMessage -> event.getChannel().sendMessage(eb.build()).queue())));
		/*eb.setDescription("If you want to receive stamina notifications, press the :white_check_mark: button."
				+ "\nIf not, press the :x: button.");
		role.getManager().setMentionable(true).queue(
				mentionMessage -> event.getChannel().sendMessage(role.getAsMention()).queue(
					mentionFalse -> role.getManager().setMentionable(false).queue(
						staminaMessage -> event.getChannel().sendMessage(eb.build()).queue(
								message -> message.addReaction("âœ…").queue(message2 -> message.addReaction("â�Œ").queue(
									messageID -> setStaminaID(message.getId())))))));*/
		
	}

	public static Boolean getStamina() {
		return stamina;
	}
	
	public static String getStaminaChannel() {
		return staminaChannel;
	}
	
	public void setStaminaID(String stamID) {
		staminaID = stamID;
	}
	
	public static String getStaminaID() {
		return staminaID;
	}
	
}



