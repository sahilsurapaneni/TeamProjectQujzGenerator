package application;

import java.util.ArrayList;

public class Question {
	private String questionText;
	private ArrayList<Choice> choiceArray;
	private String image;
	
	public Question(String questionText, ArrayList<Choice> choiceArray,String image) {
		this.questionText = questionText;
		this.image = image;
		this.choiceArray = choiceArray;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	
	public ArrayList<Choice> getChoices(){
		return choiceArray;
	}
	
	public String getImageString() {
		return image;
	}
}
