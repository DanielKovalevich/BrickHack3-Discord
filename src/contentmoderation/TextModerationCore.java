package contentmoderation;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import core.ApiInterface;
import core.Configurations;
import elements.TextClassificationElement;
import elements.TextReceivedElement;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class TextModerationCore extends ListenerAdapter {
	private ApiInterface api = null;
	
	@Override public void onMessageReceived(MessageReceivedEvent event) {
		System.out.println("[Notice] Text Detected\n");
		
		this.api = new ApiInterface();
		
		TextClassificationElement element = getApiResponse(getTextJsonString(event.getMessage().toString()));
			
		if(!getServerElegibility(element)) {
				
			event.getChannel().deleteMessageById(event.getMessage().getId());
			event.getChannel().sendMessage("Deleted an image for not following the content filter properly").queue();
		}
	}
	
	private String getTextJsonString(String text) {
		
		TextReceivedElement element = new TextReceivedElement(text);
		
		return api.putObjectIntoJson(element);
		
	}
	
	//If true, it's elegable. Otherwise it is not.
	private boolean getServerElegibility(TextClassificationElement retrievedContentValues) {
		
		Configurations config = new Configurations();
		
		boolean adultAllowed = Boolean.valueOf(config.getPropertyValue("Allow_Adult"));
		boolean racyAllowed = Boolean.valueOf(config.getPropertyValue("Allow_Racy"));
		
		
		if(adultAllowed && racyAllowed)
			return true;
		
		else if(!adultAllowed && retrievedContentValues.isAdult())
			return false;
		
		else if(!racyAllowed && retrievedContentValues.isRacy())
			return false;
		
		else
			return true;
		
		
	}
	private TextClassificationElement getApiResponse(String jsonBody) {
		
		Configurations config = new Configurations();
		
		TextClassificationElement retrievedElement = null;
		
		String requestUrl = "https://westus.api.cognitive.microsoft.com/contentmoderator/moderate/v1.0/ProcessText/DetectLanguage";
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			
			HttpPost request = new HttpPost(requestUrl);
			StringEntity params = new StringEntity(jsonBody);
			request.addHeader("content-type", "application/json");
			request.addHeader("Ocp-Apim-Subscription-Key", config.getPropertyValue("Moderation_API_key"));
			request.setEntity(params);
			
			//System.out.print(request.getAllHeaders());
			
			HttpResponse result = httpClient.execute(request);
			String json = EntityUtils.toString(result.getEntity(), "UTF-8");
			
		//	System.out.print(json);
			
			//Now that the server response is saved in "response"..
			Gson gson = new Gson();
			
			retrievedElement = gson.fromJson(json, TextClassificationElement.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retrievedElement;
		
	}
}
