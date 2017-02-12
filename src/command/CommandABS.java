package command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class CommandABS {
	
	public abstract void doCommand(MessageReceivedEvent event);
	
	public void execute(MessageReceivedEvent event) {
		
		String str = String.format("[Command] User %s called command %s",
		event.getAuthor().getName(), event.getMessage().getContent().substring(1));
		System.out.println(str);
	
		// execute the current command
		this.doCommand(event);
	}

	public void onMessageReceived(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
