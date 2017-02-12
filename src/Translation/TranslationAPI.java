package Translation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class TranslationAPI {
	private String token = null;
	private String language = null;
	private String text = null;
	
	public TranslationAPI(String token, String language, String text) { 
		this.token = token;
		this.language = language;
		this.text = text;
	}
	
	public String getApiCall() throws MalformedURLException {
		String retrievedElement = null;
		text = text.replace(" ", "%20");
		String requestUrl = "https://api.microsofttranslator.com/v2/http.svc/Translate?appid=Bearer%20" + token + "&text=" + text + "&to=" + language;
		URL url = new URL(requestUrl);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    for (String line; (line = reader.readLine()) != null;) {
		        //This will read the result line by line. Do something with the line here using the variable 'line' as the container
		    	retrievedElement = line;
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retrievedElement;
	}
}
