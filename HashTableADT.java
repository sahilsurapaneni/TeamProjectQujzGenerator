package application;

/**
 * Filename:   HashTableADT.java
 * Project:    p3-201901
 * Course:     cs400
 * Authors:    Maurya Pulipati
 * 
 * Interface for the HashTable
 */
public interface HashTableADT {

     // Returns the load factor threshold that was 
     // passed into the constructor when creating 
     // the instance of the HashTable.
     // When the current load factor is greater than or 
     // equal to the specified load factor threshold,
     // the table is resized and elements are rehashed.
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
     public double getLoadFactorThreshold() ;

     // Returns the current load factor for this hash table
     // load factor = number of items / current table size 
     /**
 	 * Getter method returns loadFactorThreshold
 	 * 
 	 * @param: none
 	 * 
 	 * @return: double loadFactorThreshold
 	 * 
 	 */
     public double getLoadFactor() ;

     // Return the current Capacity (table size)
     // of the hash table array.
     //
     // The initial capacity must be a positive integer, 1 or greater
     // and is specified in the constructor.
     /**
      * Getter method that returns the load factor
      * 
      * @param: none
      * 
      * @return: double load factor
      * 
      */
     public int numKeys() ;
    

     //Add the key to the data structure and increase the number of keys.
     /**
      * 
      * @param: Topic key
      * 
      * @return: none
      * 
      */
     public void insert(Topic key);


     // Returns the value associated with the specified key
     // Does not remove key or decrease number of keys
     /**
      * 
      * @param: String name
      * 
      * @return: Topic
      * 
      */
     public Topic get(String name);
}

