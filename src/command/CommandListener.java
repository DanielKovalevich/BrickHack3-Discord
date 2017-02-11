package command;

import exceptions.CommandNotFoundException;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

	private CommandFactory factory = null;
	
	@Override public void onMessageReceived(MessageReceivedEvent event) {
		
		Message message = event.getMessage();
		String contents = message.getContent();
		
		//This line is important. This sets the prefix which the system responds to.
		if (!contents.startsWith("!")) {
			
			return;
		}
		
		this.factory = CommandFactory.getInstance();
		contents = contents.substring(1,  contents.length());
		
		try {
			
			//
			//	This passes the content of the command to the factory
			//	so that it can create the correct command dynamically. 
			//	
			this.factory.createNewCommand(contents);
			CommandINF command = this.factory.getCurrentCommand();
			command.execute(event);
			
			//Handle if the command was not found
		} catch (CommandNotFoundException ex) {
			
			System.out.println(String.format("[Command] User %s entered invalid command %s", message.getAuthor().getName(), contents));
		}
	}
}