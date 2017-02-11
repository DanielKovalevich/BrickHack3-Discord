package command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface CommandINF {
	
	public void doCommand(MessageReceivedEvent event);
	
	void execute(MessageReceivedEvent event);

}
