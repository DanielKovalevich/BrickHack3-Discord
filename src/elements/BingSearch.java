package elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;

import command.CommandABS;
import core.ApiInterface;
import core.Configurations;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BingSearch extends ListenerAdapter {
	private String query = null;
	String token;
	
	private Configurations config = null;
	
	public BingSearch(String query) {
		this.query = query;
		config = new Configurations();
		
		token = "c8d14b6f7d9d4a4191d3b7d68e95cd3c";
	}
	
	private void search(String query, MessageReceivedEvent event) throws ParseException, IOException {
		ImageClassificationElement retrievedElement = null;
        
        String requestUrl = "https://api.cognitive.microsoft.com/bing/v5.0/search?q=" + query + "&count=10&offset=0&mkt=en-us&safesearch=Moderate HTTP/1.1";
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            
        HttpPost request = new HttpPost(requestUrl);
 //      StringEntity params = new StringEntity(jsonBody);
        request.addHeader("Ocp-Apim-Subscription-Key", "c8d14b6f7d9d4a4191d3b7d68e95cd3c");
 //       request.setEntity(params);
            
        HttpResponse result = (HttpResponse) httpClient.execute(request);
        String json = EntityUtils.toString(((org.apache.http.HttpResponse) result).getEntity(), "UTF-8");
		
		JsonParser parser = new JsonParser();
		JsonObject webJson = parser.parse(json).getAsJsonObject().get("webPages").getAsJsonObject();

		String displayUrl = webJson.get("displayUrl").getAsString();
		
		try {
			event.getChannel().sendMessage("`" + displayUrl + "`").queue();
		} catch (Exception e) {
			e.printStackTrace();

		}
        }
	}

	@Override
	public void doCommand(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
}
