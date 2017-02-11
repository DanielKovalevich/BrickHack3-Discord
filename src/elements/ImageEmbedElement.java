package elements;

public class ImageEmbedElement {
	
	private String dataRepresentation = "URL";
	private String value = null;
	
	public ImageEmbedElement(String value) {
		
		
		this.value = value;
		
	}

	public String getDataRepresentation() {
		return dataRepresentation;
	}

	public void setDataRepresentation(String dataRepresentation) {
		this.dataRepresentation = dataRepresentation;
	}

	public String getImageUrls() {
		return value;
	}

	
	

}
