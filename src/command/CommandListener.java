package command;

import exceptions.CommandNotFoundException;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

	private AbstractFactory factory = null;
	
	@Override public void onMessageReceived(MessageReceivedEvent event) {
		
		Message message = event.getMessage();
		String contents = message.getContent();
		String fact;
		
		//This line is important. This sets the prefix which the system responds to.
		if (!contents.startsWith("!")) {
			
			return;
		}
		
		//	
		//	This removes the ! then gets the first word from the string
		//
		fact = contents.substring(1,  contents.length());
		fact = fact.split("\\s+")[0];
		this.factory = FactoryMaker.getFactory(fact);
		
		//
		//	here we need to set the contents to actually hold what the rest of the contents are so there
		//	is going to be some code here.
		//
		
		try {
			
			//
			//	This passes the content of the command to the factory
			//	so that it can create the correct command dynamically. 
			//
			//	we get the second substring after we split where there are spaces for the command	
			//
			String cmd = contents.split("\\s+")[1];
			CommandABS command = this.factory.getCommand(cmd);
			command.execute(event);
			
			//Handle if the command was not found
		} catch (CommandNotFoundException ex) {
			
			System.out.println(String.format("[Command] User %s entered invalid command %s", message.getAuthor().getName(), contents));
		}
	}
}