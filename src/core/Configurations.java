package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Configurations {
	private Properties configManager = null;
	private String configFilePath = "C:\\Users\\Conrad\\Desktop\\config.conf";
	
	public Configurations() {
		this.configManager = new Properties();
		File configFile = new File(configFilePath);
		
		try {
			FileReader reader = new FileReader(configFile);
			configManager.load(reader);
		} catch (FileNotFoundException ex) {
			System.out.println(String.format("Missing configuration file, please input details" 
					+ " at the following path: %s", configFilePath));
			try {
				createEmptyConfigFile();
			}
			catch (IOException e) {
				System.out.println("Something went terribly wrong creating the config file.");
				e.printStackTrace();
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPropertyValue(String propertyName) {
		if (configManager.getProperty(propertyName) == null) {
			System.out.println("[Warning] failed to read property value for: "
					+ configManager.getProperty(propertyName));
			return "";
		}
		else {
			return configManager.getProperty(propertyName);
		}
	}
	
	private void createEmptyConfigFile() throws IOException{
		configManager.setProperty("Discord_Bot_API_Key", "Value needed");
		configManager.setProperty("Translator_Key", "Value needed");
		configManager.setProperty("Allow_Adult", "true");
		configManager.setProperty("Allow_Racy", "true");
		configManager.setProperty("Moderation_API_key", "Value needed");
		configManager.setProperty("Allow_bad_language", "true");
		// New modules with API keys will go here
		
		File configFile = new File(configFilePath);
		FileWriter writer = new FileWriter(configFile);
		configManager.store(writer, "Bot Settings");
		writer.close();
	}
}
