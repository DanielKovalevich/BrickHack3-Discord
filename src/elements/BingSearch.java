package elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import command.CommandABS;
import core.ApiInterface;
import core.Configurations;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class BingSearch extends CommandABS {
	private String query = null;
	String token;
	
	private Configurations config = null;
	
	public BingSearch(String query) {
		this.query = query;
		config = new Configurations();
		
		token = config.getPropertyValue("Search_API");
	}
	
	public String search() throws MalformedURLException {
		String hyperlink = null;
		String retrievedElement = null;
		String requestUrl = "https://api.cognitive.microsoft.com/bing/v5.0/search?" + query;
		
		URL url = new URL(requestUrl);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    for (String line; (line = reader.readLine()) != null;) {
		        //This will read the result line by line. Do something with the line here using the variable 'line' as the container
		    	retrievedElement = line;
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return hyperlink;
	}
}
