package elements;

public class TextReceivedElement {
	private String dataRepresentation = "text";
	private String text = null;
	public TextReceivedElement(String value) {
		setText(value);
	}

	public String getDataRepresentation() {
		return dataRepresentation;
	}

	public void setDataRepresentation(String dataRepresentation) {
		this.dataRepresentation = dataRepresentation;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
