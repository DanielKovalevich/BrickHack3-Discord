package elements;

import core.Configurations;

public class TranslateApiStringElement {
	
	private String translateAppId = null;
	private String translateString = null;
	private String translateLanguage = null;
	
	public TranslateApiStringElement(String translateString, String translateLanguage) {
		
		Configurations config = new Configurations();
		
		this.setTranslateAppId(config.getPropertyValue("Translator_Key"));
		this.setTranslateString(translateString);
		this.setTranslateLanguage(translateLanguage);
	}

	public String getTranslateString() {
		return translateString;
	}

	public void setTranslateString(String translateString) {
		this.translateString = translateString;
	}

	public String getTranslateLanguage() {
		return translateLanguage;
	}

	public void setTranslateLanguage(String translateLanguage) {
		this.translateLanguage = translateLanguage;
	}

	public String getTranslateAppId() {
		return translateAppId;
	}

	public void setTranslateAppId(String translateAppId) {
		this.translateAppId = translateAppId;
	}
	
	

}
