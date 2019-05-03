package application;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: TeamProjectGUI
//Files: Choice.java, Main.java, Question.java, Quiz.java, Topic.java, HashTableQuiz.java
//Course: CS 400, Semester 1, and Freshman
//
//Author: Varun Sudhakaran
//Email: vsudhakaran@wisc.edu
//Lecturer: Professor Debra Deppeler
//Lecturer Number: 002
//Due Date: 5/3/19
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.List;

/**
 * This Question class has a text and a choice array and an image
 * 
 * @author varunsudhakaran
 *
 */

public class Question {
	private String questionText; // initializes question text
	private List<Choice> choiceArray; // initializes choice array
	private String image; // initializes image
	
	/**
	 * This is a contructor of Question that holds
	 * questionText, choiceArray, and image and sets them.
	 * 
	 * @param questionText
	 * 
	 * @param choiceArray
	 * 
	 * @param image
	 * 
	 * @return: none
	 * 
	 */
	
	public Question(String questionText, List<Choice> choiceArray,String image) {
		this.questionText = questionText; // sets questionText
		this.image = image; // sets image
		this.choiceArray = choiceArray; // sets choiceArray
	}
	
	/**
	 * getQuestionText() returns questionText
	 * 
	 * @param: none
	 * 
	 * @return: String questionText
	 * 
	 */
	public String getQuestionText() {
		return questionText; // returns questionText
	}
	
	/**
	 * This getChoices() method that returns a
	 * List<Choice>
	 * 
	 * @param: none
	 * 
	 * @return: List<Choice>
	 * 
	 */
	public List<Choice> getChoices(){
		return choiceArray;
	}
	
	
	/**
	 * This correctChoice() gets the right choice
	 * from the choice array.
	 * 
	 * @param: none
	 * 
	 * @return: String right choice
	 * 
	 */
	public String correctChoice() {
		for(int i = 0; i<choiceArray.size(); i++) { // runs through loop
			if(choiceArray.get(i).getIsCorrect()) { // checks if choice is right
				return choiceArray.get(i).getChoiceText(); // returns choice
			}
		}
		return null; // returns null
	}
	
	/**
	 * The getImageString() gets the String image
	 * String.
	 * 
	 * @param: none
	 * 
	 * @return: String image
	 * 
	 */
	public String getImageString() {
		return image; // returns image
	}
}
