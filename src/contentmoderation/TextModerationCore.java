package contentmoderation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import core.ApiInterface;
import core.Configurations;
import data.MysqlCore;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class TextModerationCore extends ListenerAdapter {
	private ApiInterface api = null;
	
	@Override public void onMessageReceived(MessageReceivedEvent event) {
		
		Message scannedMethod = event.getMessage();
		
		this.api = new ApiInterface();
		
		//String element = getApiResponse(getTextJsonString(event.getMessage().toString()));
		String element = getApiResponse(event.getMessage().toString());
			
		if(!getServerElegibility(element)) {
				
			addStrike(event);
			scannedMethod.deleteMessage().queue();
			//event.getChannel().sendMessage("Deleted an some text for not following the content filter properly").queue();
		}
	}
		
	//If true, it's elegable. Otherwise it is not.
	private boolean getServerElegibility(String jsonObject) {
		
		Configurations config = new Configurations();
		
		boolean badLanguageAllowed = Boolean.valueOf(config.getPropertyValue("Allow_bad_language"));
		
		boolean languageDetected = parseForLanguage(jsonObject);
		
		if(badLanguageAllowed)
			return true;
			
		else if(languageDetected && !badLanguageAllowed)
			return false;
		
		
		return true;
		
		
	}
	private String getApiResponse(String jsonBody) {
		
		Configurations config = new Configurations();
		
		String retrievedElement = null;
		
		String requestUrl = "https://westus.api.cognitive.microsoft.com/contentmoderator/moderate/v1.0/ProcessText/Screen/?language=eng";
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			
			HttpPost request = new HttpPost(requestUrl);
			StringEntity params = new StringEntity(jsonBody);
			request.addHeader("content-type", "text/plain");
			request.addHeader("Ocp-Apim-Subscription-Key", config.getPropertyValue("Text_Moderation_API_key"));
			request.setEntity(params);
						
			HttpResponse result = httpClient.execute(request);
			String json = EntityUtils.toString(result.getEntity(), "UTF-8");
						
			//Now that the server response is saved in "response"..
			retrievedElement = json;
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retrievedElement;
		
	}
	
	//Returns true if elegable to be displayed
	private boolean parseForLanguage(String json) {
		
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(json).getAsJsonObject();

		
		try {
		JsonArray potentalBadTerms = object.get("Terms").getAsJsonArray();

		if(potentalBadTerms == null) {
			
			return false;
		}
		
		else {
			
			return true;
		}
			
		} catch (IllegalStateException e) {
			
			return false;
		}
		
		
	}
	
	private void addStrike(MessageReceivedEvent event) {
		
		MysqlCore mysql = new MysqlCore();
		Connection connection = mysql.getMysqlConnection();
		
		try {
			String query = "UPDATE discordusers SET muted = muted+1 WHERE discordId = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, event.getAuthor().getId());
			
			statement.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
