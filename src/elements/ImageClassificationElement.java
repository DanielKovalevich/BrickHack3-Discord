package elements;

public class ImageClassificationElement {
	
	private boolean IsImageRacyClassified = false;
	private boolean IsImageAdultClassified = false;
	
	public boolean isRacy() {
		return IsImageRacyClassified;
	}

	public void setRacy(boolean isRacy) {
		this.IsImageRacyClassified = isRacy;
	}

	public boolean isAdult() {
		return IsImageAdultClassified;
	}

	public void setAdult(boolean isAdult) {
		this.IsImageAdultClassified = isAdult;
	}

}
