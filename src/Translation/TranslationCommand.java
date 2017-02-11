package Translation;

import command.CommandABS;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class TranslationCommand extends CommandABS {
	
	protected String to;
	protected String from;
	protected String text = "";
	
	
	public abstract void translate();
	
	public void doCommand(MessageReceivedEvent event){
		
	}
	
	public void execute(MessageReceivedEvent event){
		
	}

}
