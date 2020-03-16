

/**
 * Creates ListNode object
 * @author Shounak Ghosh
 * @version 11.09.2018
 */
public class ListNode
{
    private Object value;
    private ListNode next;
    /**
     * creates a ListNode object
     * @param initValue the Object to be stored in the ListNode
     */
    public ListNode(Object initValue)
    { 
        value = initValue;
        next = null; 
    }
    /**
     * retrieves the value of the object
     * @return object stored int the ListNode
     */
    public Object getValue() 
    {
        return value; 
    }
    /**
     * retrieves the next ListNode
     * @return the next ListNode
     */
    public ListNode getNext() 
    {
        return next; 
    }
    /**
     * Mutates the value stored in the ListNode
     * @param theNewValue the new value to be stored in the ListNode
     */
    public void setValue(Object theNewValue) 
    {
        value = theNewValue; 
    }
    /**
     * Mutates the next ListNode
     * @param theNewNext the new next  ListNode
     */
    public void setNext(ListNode theNewNext) 
    {
        next = theNewNext; 
    }
} 