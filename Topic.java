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

import java.util.LinkedList;

/**
 * This class just sets up the Topic for each index of the Hash Table
 * 
 * @author varunsudhakaran
 *
 */
public class Topic {
	private LinkedList<Question> questionList; // initializes question list
	private String topic; // initializes String topic
	
	/**
	 * This is a constructor that sets
	 * up the String topic inputted makes 
	 * a new Linked List
	 * 
	 * @param topic
	 * 
	 * @return: none
	 *
	 */
	public Topic(String topic) {
		this.topic = topic; // sets up tpoic
		questionList = new LinkedList<Question>(); // sets question list to 
		// linked list
	}
	
	/**
	 *This addQuestion() adds question to question list
	 *
	 * @param q
	 * 
	 * @return: Question q
	 * 
	 */
	public void addQuestion(Question q){
		questionList.add(q); // adds to q to questionList
	}
	
	/**
	 * The  getName() method gets the name of the topic
	 * 
	 * @param: none
	 * 
	 * @return: String topic
	 * 
	 */
	public String getName() {
		return topic; // returns topic
	}
	
	/**
	 * This getQuestionList() gets the list of questions
	 * 
	 * @param: none
	 * 
	 * @return: LinkedList<Question>
	 * 
	 */
	public LinkedList<Question> getQuestionList(){
		return questionList; // returns list of questions
	}
	
	
}
