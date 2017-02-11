package exceptions;

@SuppressWarnings("serial")
public class JsonContentNotFound extends Throwable{
	
	public JsonContentNotFound() {
		
		System.out.println("[Warning] Failed to get content from API call.");
	}

}
