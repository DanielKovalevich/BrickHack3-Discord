package users;

import java.io.IOException;
import java.net.URI;

import org.apache.http.ParseException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import command.CommandABS;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class BingSearch extends CommandABS {

	@Override
	public void doCommand(MessageReceivedEvent event) {
		
		String result = null;
		
		try {
			result = search(event.getMessage().toString().split(" ")[2]);
			
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		
		event.getChannel().sendMessage(result).queue();
		

	}
	
	 private String search(String query) throws ParseException, IOException { 
		 //   ImageClassificationElement retrievedElement = null; 
		         
		 HttpClient httpclient = HttpClients.createDefault();

	        try
	        {
	            URIBuilder builder = new URIBuilder("https://api.cognitive.microsoft.com/bing/v5.0/search");

	            builder.setParameter("q", query);
	            builder.setParameter("count", "1");
	            builder.setParameter("offset", "0");
	            builder.setParameter("mkt", "en-us");
	            builder.setParameter("safesearch", "Moderate");

	            URI uri = builder.build();
	            HttpPost request = new HttpPost(uri);
	            request.setHeader("Ocp-Apim-Subscription-Key", "d5c6704474064ea5be7eccbeda557e95");


	            // Request body
	            StringEntity reqEntity = new StringEntity("{body}");
	            request.setEntity(reqEntity);

	            HttpResponse response = httpclient.execute(request);
	            HttpEntity entity = response.getEntity();
	            
	            String jsonResult = null;

	            if (entity != null) 
	            {
	            	JsonParser parser = new JsonParser();
	            	System.out.println(EntityUtils.toString(entity));
	                JsonObject webJson = parser.parse(EntityUtils.toString(entity)).getAsJsonObject();
	                jsonResult = webJson.get("webPages").getAsJsonObject().get("displayUrl").getAsString();
	               // System.out.println(jsonResult);
	                
	                return jsonResult;
	            }
	        }
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	            return "Error";
	        }
	        return "";
	    
		  //  JsonObject webJson = parser.parse(json).getAsJsonObject().get("webPages").getAsJsonObject(); 
		  

}
	 }
