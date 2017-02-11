package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import exceptions.JsonContentNotFound;

public class ApiInterface {
	
	private String getJsonFromUrl(String apiUrl) throws Exception {
		
		BufferedReader reader = null;
		try {
			//Open the URL to retrieve the contents
			URL url = new URL(apiUrl);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			
			int read;
			//Set a data rate limit and begin reading characters if they exist in the stream
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			
			//Return the result
			return buffer.toString();
		} finally {
			//Close the reader if it's done!
			if (reader != null)
				reader.close();
		}
	}
	
	public JsonElement getJsonElementFromApi(String apiUrl) throws JsonContentNotFound {
		
		JsonParser parser = new JsonParser();
		
		try {
			String apiResponse = getJsonFromUrl(apiUrl);
			return parser.parse(apiResponse);
			
		} catch (Exception e) {
			throw new JsonContentNotFound();
			
		}
		
	}

	public JsonElement putStringIntoJson(Object informationObject) {
		
		
		
	}
}
