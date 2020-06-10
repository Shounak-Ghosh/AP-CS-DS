/**
 * ListNode objects store objects and a reference to the next ListNode
 * @author Shounak Ghosh
 *@version 1.26.2019
 * @param <E> parameter type placeholder
 */
public class ListNode <E>
{
    private E value;
    private ListNode<E> next;

    /**
     * Creates a ListNode object with a specified value
     * @param initValue the value to be stored at the node
     * @param initNext the next ListNode object in the LinkedList
     */
    public ListNode(E initValue, ListNode<E> initNext)
    { 
        value = initValue;
        next = initNext; 
    }

    /**
     * Retrieves the value stored at the node
     * @return the value stored at the node
     */
    public E getValue() 
    { 
        return value; 
    }

    /**
     * Retrieves the next ListNode in the linked list
     * @return the next ListNode in the linked list
     */
    public ListNode<E> getNext() 
    { 
        return next; 
    }

    /**
     * Mutates the value stored at the node
     * @param theNewValue the new value to be stored at the node
     */
    public void setValue(E theNewValue) 
    { 
        value = theNewValue;
    }

    /**
     * Mutates the reference to the next ListNode object in the linked list
     * @param theNewNext the new next ListNode in the  linked list
     */
    public void setNext(ListNode<E> theNewNext) 
    { 
        next = theNewNext; 
    }

}
