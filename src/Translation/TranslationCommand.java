package Translation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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
		
		token = configManager.getPropertyValue("Translator_Token");
		
	}
	
	public void translate(MessageReceivedEvent event) throws IOException{
		String translatedText = null;
		
		TranslationAPI translate = new TranslationAPI(token, to, text);
		translatedText = translate.getApiCall();
		
		translatedText = getTranslation(translatedText);
		
		try {
			event.getChannel().sendMessage("`" + translatedText + "`").queue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void doCommand(MessageReceivedEvent event){
		
		try {
			this.translate(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String getTranslation(String input) {
		String translation = null;
		int beginning = input.indexOf('>');
		int end = input.indexOf('<', beginning);
		
		
		translation = input.substring(beginning + 1, end);
		return translation;
		
	}
}
