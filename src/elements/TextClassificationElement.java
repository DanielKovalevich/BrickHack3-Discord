package elements;

public class TextClassificationElement {
	private boolean IsTextRacyClassified = false;
	private boolean IsTextAdultClassified = false;
	
	public boolean isRacy() {
		return IsTextRacyClassified;
	}

	public void setRacy(boolean isRacy) {
		this.IsTextRacyClassified = isRacy;
	}

	public boolean isAdult() {
		return IsTextAdultClassified;
	}

	public void setAdult(boolean isAdult) {
		this.IsTextAdultClassified = isAdult;
	}
}
