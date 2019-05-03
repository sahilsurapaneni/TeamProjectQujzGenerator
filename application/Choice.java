package application;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: TeamProjectGUI
//Files: Choice.java, Main.java, Question.java, Quiz.java, Topic.java, HashTableQuiz.java
//Course: CS 400, Semester 2, and Freshman
//
//Author: Varun Sudhakaran
//Email: vsudhakaran@wisc.edu
//Lecturer: Professor Debra Deppeler
//Lecturer Number: 002
//Due Date: 5/3/19
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


/**
 * This class just sets up Choice
 * 
 * @author varunsudhakaran
 *
 */
public class Choice {
	private Boolean isCorrect; // sets up the isCorrect
	String choiceText; // sets up the choiceText
	
	/**
	 * This is a constructor contains 
	 * isCorrect and sets it up and contains choiceText
	 * and sets it up.
	 * 
	 * @param isCorrect
	 * 
	 * @param choiceText
	 * 
	 * @return: none
	 * 
	 */
	public Choice (Boolean isCorrect, String choiceText) {
		this.isCorrect = isCorrect; // sets up isCorrect
		this.choiceText = choiceText; // sets up choiceText
	}
	
	/**
	 * The getIsCorrect() returns isCorrect
	 * 
	 * @param: none
	 * 
	 * @return: Boolean isCorrect
	 */
	public Boolean getIsCorrect() {
		return isCorrect;
	}
	
	/**
	 * The setter setIsCorrect() sets up the isCorrect
	 * 
	 * @param bool
	 * 
	 * @return: none
	 * 
	 */
	public void setIsCorrect(Boolean bool) {
		isCorrect = bool; // sets isCorrect to bool
	}
	
	/**
	 * This getChoiceText() returns String choiceText.
	 * 
	 * @param: none
	 * 
	 * @return: String choiceText
	 */
	public String getChoiceText() {
		return choiceText; // returns choiceText
	}
}
