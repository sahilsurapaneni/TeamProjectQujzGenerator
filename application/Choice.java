package application;

public class Choice {
	private Boolean isCorrect;
	String choiceText;
	
	public Choice (Boolean isCorrect, String choiceText) {
		this.isCorrect = isCorrect;
		this.choiceText = choiceText;
	}
	
	public Boolean getIsCorrect() {
		return isCorrect;
	}
	
	public String getChoiceText() {
		return choiceText;
	}
}
