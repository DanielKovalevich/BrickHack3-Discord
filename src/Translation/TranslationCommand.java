package Translation;

import command.CommandABS;
import core.Configurations;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class TranslationCommand extends CommandABS {
	
	protected String to;
	protected String text = "";
	String token;
	
	
	public TranslationCommand(String t, String txt){
		to = t;
		text = txt;
	}
	
	public void translate(){
		
	}
	
	public void doCommand(MessageReceivedEvent event){
		
		Configurations configManager = new Configurations();
		token = configManager.getPropertyValue("Translator_Key");
		
		this.translate();
		
	}

}
