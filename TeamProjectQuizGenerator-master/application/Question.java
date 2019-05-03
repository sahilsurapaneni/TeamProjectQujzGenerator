package application;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private String questionText;
	private List<Choice> choiceArray;
	private String image;
	
	public Question(String questionText, List<Choice> choiceArray,String image) {
		this.questionText = questionText;
		this.image = image;
		this.choiceArray = choiceArray;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	
	public List<Choice> getChoices(){
		return choiceArray;
	}
	
	public String correctChoice() {
		for(int i = 0; i<choiceArray.size(); i++) {
			if(choiceArray.get(i).getIsCorrect()) {
				return choiceArray.get(i).getChoiceText();
			}
		}
		return null;
	}
	public String getImageString() {
		return image;
	}
}
