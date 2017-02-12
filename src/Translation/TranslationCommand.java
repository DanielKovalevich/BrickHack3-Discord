package Translation;


import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import command.CommandABS;
import core.Configurations;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class TranslationCommand extends CommandABS {
	
	protected String to;
	protected String text = "";
	String token;
	
	private Configurations configManager = null;
	
	
	public TranslationCommand(String t, String txt){
		
		this.configManager = new Configurations();
		to = t;
		text = txt;
		
		token = configManager.getPropertyValue("Translator_Key");
		
	}
	
	public void translate(){
		
		Translate.setClientId(configManager.getPropertyValue("Azure_Translate_ID"));
		Translate.setClientSecret(configManager.getPropertyValue("Azure_Translate_Secret"));
		
		String translatedText;
		try {
			translatedText = Translate.execute(this.text, Language.ENGLISH);
			System.out.println(translatedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void doCommand(MessageReceivedEvent event){
		


		this.translate();
		
	}
	
	
		
	

}
