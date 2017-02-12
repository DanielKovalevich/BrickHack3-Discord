package Translation;

import command.CommandABS;
import core.Configurations;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class TranslationCommand extends CommandABS {
	
	Configurations configManager = new Configurations();
	
	protected String to;
	protected String from;
	protected String text = "";
	
	protected String token = configManager.getPropertyValue("League_of_Legends_API_Key");
	
	public TranslationCommand(String t, String f, String txt){
		to = t;
		from = f;
		text = txt;
	}
	
	public abstract void translate();
	
	public void doCommand(MessageReceivedEvent event){
		
	}

}
