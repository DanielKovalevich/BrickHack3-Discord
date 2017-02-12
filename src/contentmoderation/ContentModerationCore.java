package contentmoderation;

import java.io.IOException;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import core.ApiInterface;
import core.Configurations;
import elements.ImageClassificationElement;
import elements.ImageEmbedElement;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageEmbedEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ContentModerationCore extends ListenerAdapter {
	
	private ApiInterface api = null;
	
	@Override public void onMessageEmbed(MessageEmbedEvent event) {
		
		if(event.getPrivateChannel().getUser().isBot())
			return;
		
		this.api = new ApiInterface();
		
		//For each of the embeded elements, create a json object and filter content
		if(event.getMessageEmbeds().size() > 1) {
			
			Iterator<MessageEmbed> iterator = event.getMessageEmbeds().iterator();
			
			while(iterator.hasNext()) {
				
				ImageClassificationElement element = getApiResponse(getImageJsonString(iterator.next()));
				
				if(!getServerElegibility(element)) {
					
					event.getChannel().deleteMessageById(event.getMessageId()).queue();
					event.getChannel().sendMessage("Deleted an image for not following the content filter properly").queue();
				}
				
			}
		}
		
		else {
			
			ImageClassificationElement element = getApiResponse(getImageJsonString(event.getMessageEmbeds().get(0)));
			
			if(!getServerElegibility(element)) {
				
				event.getChannel().deleteMessageById(event.getMessageId()).queue();
				event.getChannel().sendMessage("Deleted an image for not following the content filter properly").queue();
			}
		}
		
	}
	
	private String getImageJsonString(MessageEmbed embedElement) {
		
		ImageEmbedElement element = new ImageEmbedElement(embedElement.getUrl());
		
		return api.putObjectIntoJson(element);
		
	}
	
	//If true, it's elegable. Otherwise it is not.
	private boolean getServerElegibility(ImageClassificationElement retrievedContentValues) {
		
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
		
	
	private ImageClassificationElement getApiResponse(String jsonBody) {
		
		Configurations config = new Configurations();
		
		ImageClassificationElement retrievedElement = null;
		
		String requestUrl = "https://westus.api.cognitive.microsoft.com/contentmoderator/moderate/v1.0/ProcessImage/Evaluate";
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
			
			retrievedElement = gson.fromJson(json, ImageClassificationElement.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retrievedElement;
		
	}

}
