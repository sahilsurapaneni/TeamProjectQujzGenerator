package application;

import java.util.LinkedList;

public class HashTableQuiz {

	private int size; //number of elements in hash table
	private double loadFactor; //current load factor of the table.
	private double loadFactorThreshold; //max load factor before hash table must rehash
	
	private int initialCapacity;//max capacity of hashTable
	private LinkedList<Topic>[] topicArray; //hashTable
	
		
	/**
	 * Default constructor that sets hashTable size to java default of 16.
	 * Sets loadFactorThreshold to java default of 0.75.
	 * Initialized hash table as an array of linked list.
	 */
	public HashTableQuiz() {
		size = 0;
		initialCapacity = 16;
		
		loadFactor = size/initialCapacity;
		loadFactorThreshold = .75;
		
		topicArray = new LinkedList[initialCapacity]; //makes hash table an array of linked lists.
	}
	
	// TODO: add all unimplemented methods so that the class can compile
	
	 // Returns the load factor threshold that was 
    // passed into the constructor when creating 
    // the instance of the HashTable.
    // When the current load factor is greater than or 
    // equal to the specified load factor threshold,
    // the table is rehashed and elements are rehashed.
    public double getLoadFactorThreshold() {
    	return loadFactorThreshold; 
    }
    // Returns the current load factor for this hash table
    // load factor = number of items / current table size 
    public double getLoadFactor() {
    	//calculates loadFactor by dividing number of elements by max size of hashTable.
    	return (double)size/(double)initialCapacity; 
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
    public int getCapacity() {
    	return initialCapacity;
    }
   
   /**
     * Returns a hash index for a passed in key.
     * Uses Java's default hashCode() method, and then %'s it by table size.
     * Then takes the absolute value of the value above.
     * @param key The  key for which the hashIndex is being gotten
     * @return the hashIndex for key.
     */
    private int getHashIndex(String name) {
    	return Math.abs(name.hashCode()%initialCapacity);
    }
    
    /**
     * This method is called when the loadFactor reaches the loadFactorThreshold.
     * Sets the hash table to a new hash table of twice the size plus one. 
     * Rehashes all the data stored in the old hash table and puts it in the new table accordingly.
     * @return The new hash table of increased size
     * @throws IllegalNullKeyException If insert also throws an IllegalNullKeyException on the key.
     * @throws DuplicateKeyException If insert also throws an DuplicateKeyException on the key.
     */
    private LinkedList<Topic>[] rehash(){
    	//create temp array to hold current hash table
    	LinkedList<Topic>[] oldtopicArray = topicArray;
    	//sets hash table to new array of linked lists.
    	//size is doubled and plus one
    	topicArray= new LinkedList[initialCapacity*2 + 1];
    	//updates max capacity of hash table
    	int newCapacity = initialCapacity*2 + 1;
    	
    	//sets number of elements to zero
    	size = 0;
    	//copies elements over from old hash table into new hash table.
    	for(int i = 0; i<oldtopicArray.length; i++) {
    		LinkedList<Topic> list = oldtopicArray[i];
    		if(list!=null) {
	    		for(Topic element : oldtopicArray[i]) {
	    			insert(element);
	    		}
    		}
    	}
    	
    	initialCapacity = newCapacity;
    	return topicArray;
    }
	//Add the key,value pair to the data structure and increase the number of keys.
    // If key is null, throw IllegalNullKeyException;
    // If key is already in data structure, throw DuplicateKeyException();
    public void insert(Topic key){
    	//throw exception for null key
    	//checks if hash table needs to be rehashed.
    	if(getLoadFactor()>= getLoadFactorThreshold()) {
    		rehash();
    	}
    	int arrayPos = getHashIndex(key.getName());
    	
    	//if the position of hash index in the array is null (no data stored there yet)
    	//makes the position a new LinkedList and adds the Topic that holds the passed in key and value.
    	if(topicArray[arrayPos]==null) {
    		topicArray[arrayPos] = new LinkedList<Topic>();
    		topicArray[arrayPos].add(key);
    		size++;
    	}
    	
    	//if there is already an element stored in that location, checks if that key is already stored
    	//if it is not stored, it adds it to the beginning of the linked list.
    	else {
    		for(Topic element:topicArray[arrayPos]) {
        		if(element.getName().equals(key.getName())) {
        			return;
        		}
        	}
    		topicArray[arrayPos].add(key);
    		size++;
    		
    	}
   
    }
  
    // Returns the value associated with the specified key
    // Does not remove key or decrease number of keys
    //
    // If key is null, throw IllegalNullKeyException 
    // If key is not found, throw KeyNotFoundException().
    public Topic get(String name){
    	int hashIndex = getHashIndex(name);
    	LinkedList<Topic> location = topicArray[hashIndex];
    	if(location == null) {
    		return null;
    	}
    	for(Topic element:location) {
    		if(element.getName().equals(name)) {
    			return element;
    		}
    	}
    	return null;
    }
    // Returns the number of key,value pairs in the data structure
    public int numKeys() {
    	return size;
    }
		
}
