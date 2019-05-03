package application;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: TeamProjectQuizGenerator
//Files: Choice.java, Main.java, Question.java, Quiz.java, Topic.java, HashTableQuiz.java
//Course: CS 400
//
//Author: Sahil Surapaneni,Maurya Adhitya, Chad Spalding, Sathvik Gurupalli, Varun Sudhakaran
//Email: surapaneni2@wisc.edu, mpulipati@wisc.edu, ctspalding@wisc.edu, vsudhakaran@wisc.edu, gurupalli@wisc.edu
//Lecturer: Professor Debra Deppeler, Andrew Kuemmel
//Lecturer Number: 001, 002, 004.
//Due Date: 5/3/19
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class just sets up Choice
 * 
 * @author Sahil Surapaneni, Maurya Pulipati, Chad Spalding, Sathvik Gurupalli, Varun Sudhakaran
 *
 */
public class Choice {
	private Boolean isCorrect; // sets up the isCorrect
	String choiceText; // sets up the choiceText

	/**
	 * This is a constructor contains isCorrect and sets it up and contains
	 * choiceText and sets it up.
	 * @param isCorrect 
	 * @param choiceText
	 * @return: none
	 */
	public Choice(Boolean isCorrect, String choiceText) {
		this.isCorrect = isCorrect; // sets up isCorrect
		this.choiceText = choiceText; // sets up choiceText
	}

	/**
	 * The getIsCorrect() returns isCorrect
	 * @param: none
	 * @return: Boolean isCorrect
	 */
	public Boolean getIsCorrect() {
		return isCorrect;
	}

	/**
	 * The setter setIsCorrect() sets up the isCorrect
	 * @param bool
	 * @return: none
	 */
	public void setIsCorrect(Boolean bool) {
		isCorrect = bool; // sets isCorrect to bool
	}

	/**
	 * This getChoiceText() returns String choiceText.
	 * @param: none
	 * @return: String choiceText
	 */
	public String getChoiceText() {
		return choiceText; // returns choiceText
	}
}