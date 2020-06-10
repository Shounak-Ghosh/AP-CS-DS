import java.util.*;
/**
 * My implementation of a hash set. 
 * The following methods are provided/implemented
 * 
 * size(), 
 * contains(Object obj), 
 * add(E obj), 
 * remove(Object obj), 
 * toString()
 * 
 * @author Anu Datar
 * @author Shounak Ghosh
 * @version 1.23.2019
 * @param <E> parameter type placeholder
 */

public class MyHashSet<E>
{
    // global variables
    private static final int NUM_BUCKETS = 5;
    private LinkedList<E>[] buckets; // an array of linked lists
    private int size;

    /**
     *  Creates a MyHashSet object
     */
    public MyHashSet()
    {
        buckets = new LinkedList[NUM_BUCKETS];
        size = 0;

        //MISSING CODE
    }

    //returns the index of the bucket where obj might be found

    /**
     * Converts an Object to an int using Java's String hashCode() method
     * @param obj The object to be converted 
     * @return the index at which the object will be stored
     */
    private int toBucketIndex(Object obj)
    {
        //throw new RuntimeException("IMPLEMENT ME!");
        return Math.abs(obj.hashCode()) % NUM_BUCKETS; 
        // Math.abs() because hashCode() can return any integer
    }

    /**
     * Retrieves the current number of elements stored in the MyHashSet 
     * @return how many objects are stored within the MyHashSet
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks whether an object is present within the MyHashSet
     * @param obj the object to be checked for
     * @return true if the object is found; false otherwise
     */
    public boolean contains(Object obj)
    {
        //throw new RuntimeException("IMPLEMENT ME!");
        int index = toBucketIndex(obj);

        if (buckets[index] != null) 
        {
            for (int i = 0; i < buckets[index].size(); i++) 
            {
                if (obj.equals(buckets[index].get(i))) 
                // if the object is found at the specified bucket
                {
                    return true;
                }
            }
        }
        return false;
    }

    // if obj is not present in this set, adds obj and
    // returns true; otherwise returns false

    /**
     * Adds an object to the MyHashSet if it is not already present
     * @param obj the object to be added if it is not already there
     * @return true if the object was added; false otherwise
     */
    public boolean add(E obj)
    {
        //throw new RuntimeException("IMPLEMENT ME!");

        if (!contains(obj)) // if the object is not already present
        {
            int index = toBucketIndex(obj);
            if (buckets[index] == null) 
            {
                buckets[index] = new LinkedList<E>(); 
                // creates a new LinkedList if index is null

            }
            buckets[index].add(obj);
            size++;
            return true;
        }
        return false;
    }

    // if obj is present in this set, removes obj and
    // returns true; otherwise returns false

    /**
     * Removes a given object from the MyHashSet
     * @param obj the object to be removed from the myHashSet
     * @return true if the object was removed successfully; false otherwise
     */
    public boolean remove(Object obj)
    {
        //throw new RuntimeException("IMPLEMENT ME!");

        if (contains(obj)) // if the object is present
        {
            int index = toBucketIndex(obj);
            buckets[index].remove(obj); // remove it
            size--;
            return true;
        }
        return false;
    }

    /**
     * Retrieves a String representation of the MyHashSet
     * @return A String representation of the MyHashSet
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < buckets.length; i++)
            if (buckets[i] != null &&  buckets[i].size() > 0)
                s += i + ":" + buckets[i] + " ";
        return s;
    }
    
    private class MyHashSetIterator implements Iterator<E>
    {
        
        
        
        
    }
    
}