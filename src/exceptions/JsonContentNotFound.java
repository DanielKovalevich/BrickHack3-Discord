package exceptions;

public class JsonContentNotFound extends Throwable{
	

	private static final long serialVersionUID = 1L;

	public JsonContentNotFound() {
		
		System.out.println("[Warning] Failed to get content from API call.");
	}

}
