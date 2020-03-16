
/**
 * A crude implementation of the Queue data structure provided by Java
 * The print statements in the methods are for testing purposesm
 * 
 *
 * @author Shounak Ghosh
 * @version 11.09.2018
 * @param <E> parameter type placeholder
 */
public class MyQueue<E>
{
    // instance variables - replace the example below with your own
    private ListNode front;
    private ListNode rear;

    /**
     * Constructor for objects of class MyQueue
     */
    public MyQueue()
    {
        // initialise instance variables
        this.front = null;
        this.rear = null;
    }
    /**
     * adds an element to the back of the queue
     * @param obj the element to be added to the back of the queue
     * @return true if addition was sucessful, false otherwise
     */
    public boolean add(E obj)
    {
        ListNode element = new ListNode(obj);
        if (isEmpty())
        {
            front = element;
            rear = element;
        }
        else
        {
            rear.setNext(element);
            rear = element;
        }
        System.out.println("Item Added: " + (E) element.getValue());
        return true;
    }
    /**
     * removes the element at the front of the queue
     * @return the object that was removed, or null if the queue is empty
     */
    public E remove()
    {
        if (isEmpty())
        {
            return null;
        }
        ListNode temp = front;
        ListNode nextEl = front.getNext();
        front = nextEl;
        System.out.println("Item Removed: " + (E) temp.getValue());
        return (E) temp.getValue();
    }
    /**
     * retrieves the element at the front of the queue
     * @return the object at the front of the queue, 
     *         or null if the queue is empty
     */
    public E peek()
    {
        if (isEmpty())
        {
            System.out.println("Item Peeked: null");
            return null;
        }
        System.out.println("Item Peeked: " + (E) front.getValue());
        return (E) front.getValue();
    }
    /**
     * Checks if the queue is empty
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty()
    {
        System.out.println("isEmpty: " + (front == null || rear == null) );
        return front == null || rear == null;
    }
}
