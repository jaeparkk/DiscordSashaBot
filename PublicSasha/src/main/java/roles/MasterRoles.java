package roles;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MasterRoles extends ListenerAdapter {

	private static final String MASTER = "Respect#3248";
	private static final String SASHA = "592555124572815418";
	
	//GuildMessageReceivedEvent e = new GuildMessageReceivedEvent();
	public static String getMaster() {
		return MASTER;
	}
	
	public static String getSasha() {
		return SASHA;
	}
	
	
	
	
}
