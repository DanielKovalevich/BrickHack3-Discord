package elements;

import core.Configurations;

public class TranslateApiStringElement {
	
	private String translateAppId = null;
	private String translateString = null;
	private String translateLanguage = null;
	
	public TranslateApiStringElement(String translateString, String translateLanguage) {
		
		Configurations config = new Configurations();
		
		this.setTranslateAppId("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzY29wZSI6Imh0dHBzOi8vYXBpLm1pY3Jvc29mdHRyYW5zbGF0b3IuY29tLyIsInN1YnNjcmlwdGlvbi1pZCI6ImZmY2NlMDBjYzM4ZDQyM2JiMzE2MzFlNTYxYWQzYjQ3IiwicHJvZHVjdC1pZCI6IlRleHRUcmFuc2xhdG9yLkYwIiwiY29nbml0aXZlLXNlcnZpY2VzLWVuZHBvaW50IjoiaHR0cHM6Ly9hcGkuY29nbml0aXZlLm1pY3Jvc29mdC5jb20vaW50ZXJuYWwvdjEuMC8iLCJhenVyZS1yZXNvdXJjZS1pZCI6Ii9zdWJzY3JpcHRpb25zLzI1NTIwNmRjLTkxOWYtNDBlMi1iODg0LTYwYWY4MmM3ODYxOS9yZXNvdXJjZUdyb3Vwcy9Ccmlja0hhY2szL3Byb3ZpZGVycy9NaWNyb3NvZnQuQ29nbml0aXZlU2VydmljZXMvYWNjb3VudHMvQ2FybCIsImlzcyI6InVybjptcy5jb2duaXRpdmVzZXJ2aWNlcyIsImF1ZCI6InVybjptcy5taWNyb3NvZnR0cmFuc2xhdG9yIiwiZXhwIjoxNDg2ODk2NjIzfQ.hHa6Q4zemCqDznqjIaP58ROmY1nyq-GBJmheq2R8Zs0");
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
