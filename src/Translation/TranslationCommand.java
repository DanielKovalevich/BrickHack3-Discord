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
		
		token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzY29wZSI6Imh0dHBzOi8vYXBpLm1pY3Jvc29mdHRyYW5zbGF0b3IuY29tLyIsInN1YnNjcmlwdGlvbi1pZCI6ImZmY2NlMDBjYzM4ZDQyM2JiMzE2MzFlNTYxYWQzYjQ3IiwicHJvZHVjdC1pZCI6IlRleHRUcmFuc2xhdG9yLkYwIiwiY29nbml0aXZlLXNlcnZpY2VzLWVuZHBvaW50IjoiaHR0cHM6Ly9hcGkuY29nbml0aXZlLm1pY3Jvc29mdC5jb20vaW50ZXJuYWwvdjEuMC8iLCJhenVyZS1yZXNvdXJjZS1pZCI6Ii9zdWJzY3JpcHRpb25zLzI1NTIwNmRjLTkxOWYtNDBlMi1iODg0LTYwYWY4MmM3ODYxOS9yZXNvdXJjZUdyb3Vwcy9Ccmlja0hhY2szL3Byb3ZpZGVycy9NaWNyb3NvZnQuQ29nbml0aXZlU2VydmljZXMvYWNjb3VudHMvQ2FybCIsImlzcyI6InVybjptcy5jb2duaXRpdmVzZXJ2aWNlcyIsImF1ZCI6InVybjptcy5taWNyb3NvZnR0cmFuc2xhdG9yIiwiZXhwIjoxNDg2ODk2NjIzfQ.hHa6Q4zemCqDznqjIaP58ROmY1nyq-GBJmheq2R8Zs0";
		
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
