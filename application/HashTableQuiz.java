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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * This class makes a data structure of Hash Table 
 * to be implemented for our data.
 * 
 * @author Sahil Surapaneni,Maurya Adhitya, Chad Spalding, Sathvik Gurupalli, Varun Sudhakaran
 *
 */
public class HashTableQuiz {

	private int size; //number of elements in hash table
	private double loadFactor; //current load factor of the table.
	private double loadFactorThreshold; //max load factor before hash table must rehash
	
	private int initialCapacity;//max capacity of hashTable
	private LinkedList<Topic>[] topicArray; //hashTable
	private List<String> listTopics;
	
		
	/**
	 * Default constructor that sets hashTable size to java default of 16.
	 * Sets loadFactorThreshold to java default of 0.75.
	 * Initialized hash table as an array of linked list.
	 * 
	 * @param: none
	 * 
	 * @return: none
	 * 
	 */
	public HashTableQuiz() {
		size = 0; // size is 0
		initialCapacity = 16; // initialCapacity is 16
		
		loadFactor = size/initialCapacity; // calculates loadFactor
		loadFactorThreshold = .75; // sets threshold to 0.75
		
		topicArray = new LinkedList[initialCapacity]; //makes hash table an array of linked lists.
		listTopics = new ArrayList<String>(); // makes a new ArrayList of Topics
	}
	
	// TODO: add all unimplemented methods so that the class can compile
	
	 // Returns the load factor threshold that was 
    // passed into the constructor when creating 
    // the instance of the HashTable.
    // When the current load factor is greater than or 
    // equal to the specified load factor threshold,
    // the table is rehashed and elements are rehashed.
	/**
	 * Getter method returns loadFactorThreshold
	 * 
	 * @param: none
	 * 
	 * @return: double loadFactorThreshold
	 * 
	 */
    public double getLoadFactorThreshold() {
    	return loadFactorThreshold; // returns loadFactorThreshold
    }
    // Returns the current load factor for this hash table
    // load factor = number of items / current table size
    /**
     * Getter method that returns the load factor
     * 
     * @param: none
     * 
     * @return: double load factor
     * 
     */
    public double getLoadFactor() {
    	//calculates loadFactor by dividing number of elements by max size of hashTable.
    	return (double)size/(double)initialCapacity; // returns load factor
    }
    // Return the current Capacity (table size)
    // of the hash table array.
    //
    // The initial capacity must be a positive integer, 1 or greater
    // and is specified in the constructor.
    // 
    // REQUIRED: When the load factor threshold is reached, 
    // the capacity must increase to: 2 * capacity + 1
    //
    // Once increased, the capacity never decreases
    /**
     * Getter method that returns the initialCapacity
     * 
     * @param: none
     * 
     * @return: int initialCapacity
     */
    public int getCapacity() {
    	return initialCapacity; //
    }
   
   /**
     * Returns a hash index for a passed in key.
     * Uses Java's default hashCode() method, and then %'s it by table size.
     * Then takes the absolute value of the value above.
     * 
     * @param key The  key for which the hashIndex is being gotten
     * 
     * @return the hashIndex for key.
     * 
     */
    private int getHashIndex(String name) {
    	return Math.abs(name.hashCode()%initialCapacity); // gets the hash index
    }
    
    /**
     * This method is called when the loadFactor reaches the loadFactorThreshold.
     * Sets the hash table to a new hash table of twice the size plus one. 
     * Rehashes all the data stored in the old hash table and puts it in the new table accordingly.
     * 
     * @param: none
     * 
     * @return The new hash table of increased size
     * 
     */
    private LinkedList<Topic>[] rehash(){
    	//create temp array to hold current hash table
    	LinkedList<Topic>[] oldtopicArray = topicArray;
    	//sets hash table to new array of linked lists of Topics
    	//size is doubled and plus one
    	topicArray= new LinkedList[initialCapacity*2 + 1];
    	//updates max capacity of hash table
    	int newCapacity = initialCapacity*2 + 1;
    	
    	//sets number of elements to zero
    	size = 0;
    	//copies elements over from old hash table into new hash table.
    	for(int i = 0; i<oldtopicArray.length; i++) { // goes through help
    		LinkedList<Topic> list = oldtopicArray[i]; // creates linked list
    		if(list!=null) { // checks list if not null
	    		for(Topic element : oldtopicArray[i]) { // runs through for each loop
	    			insert(element); // inserts element
	    		}
    		}
    	}
    	
    	initialCapacity = newCapacity; // sets initial capacity to new capacity
    	return topicArray; // returns topicArray
    }
	//Add the key,value pair to the data structure and increase the number of keys.
    /**
     * 
     * @param: Topic key
     * 
     * @return: none
     * 
     */
    public void insert(Topic key){
    	//throw exception for null key
    	//checks if hash table needs to be rehashed.
    	if(getLoadFactor()>= getLoadFactorThreshold()) { //checks if the load factor is greater 
    		// than the load factor threshold
    		rehash(); // rehashes
    	}
    	int arrayPos = getHashIndex(key.getName()); // gets the hash index
    	
    	//if the position of hash index in the array is null (no data stored there yet)
    	//makes the position a new LinkedList and adds the Topic that holds the passed in key and value.
    	
    	if(topicArray[arrayPos]==null) { // checks if this topic array is null
    		topicArray[arrayPos] = new LinkedList<Topic>(); // makes a new Linked List in that position
    		topicArray[arrayPos].add(key); // adds the topic at the position
    		listTopics.add(key.getName()); // adds the name of the topic to that list
    		size++; // increments size
    	}
    	
    	//if there is already an element stored in that location, checks if that key is already stored
    	//if it is not stored, it adds it to the beginning of the linked list.
    	else {
    		for(Topic element:topicArray[arrayPos]) { // goes through the for each loop
        		if(element.getName().equals(key.getName())) { // checks if the element is equal to the key
        			return; // returns
        		}
        	}
    		topicArray[arrayPos].add(key); // adds the key to that position
    		listTopics.add(key.getName()); // adds the name to the list of topics
    		size++; // increment size
    		
    	}
   
    }
  
    // Returns the value associated with the specified key
    // Does not remove key or decrease number of keys
    /**
     * 
     * @param: String name
     * 
     * @return: Topic
     * 
     */
    public Topic get(String name){
    	int hashIndex = getHashIndex(name); // finds hash index
    	LinkedList<Topic> location = topicArray[hashIndex]; // makes the linked list at
    	// the location of the hash index
    	if(location == null) { // checks if the location is null
    		return null; // returns null
    	}
    	for(Topic element:location) { // runs for each loop
    		if(element.getName().equals(name)) { // if element's name equals name
    			return element; // return element
    		}
    	}
    	return null; // returns null
    }
    // Returns the number of key,value pairs in the data structure
    /**
     * Getter method that returns numKeys
     * 
     * @param: none
     * 
     * @return: int numKeys
     * 
     */
    public int numKeys() {
    	return size; // returns size
    }
    
    /**
     * This method returns a list of Questions
     * 
     * @param: nooe
     * 
     * @return: List<Question> 
     * 
     */
    public List<Question> getQuestionList(){
    	List<Question> qList = new ArrayList<Question>(); // creates an arraylist of questions
    	for(int i = 0; i<topicArray.length; i++) { // goes through the topic array
    		if(topicArray[i]!=null) { // checks if topic array is null
    			for(int j = 0; j<topicArray[i].size(); j++) { // goes through the 2d array
        			qList.addAll(topicArray[i].get(j).getQuestionList()); // adds all
        		}
    		}
    	}
    	return qList; // returns q list
	} 
    
    /**
     * Getter method that gets all topic names
     * 
     * @param: none
     * 
     * @return: List<String>
     * 
     */
    public List<String >getTopicNames(){
    	return listTopics; // returns listTopics
    }
		
}