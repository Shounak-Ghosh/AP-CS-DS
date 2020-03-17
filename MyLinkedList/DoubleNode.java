/**
 * Creates a DoubleNode object that points to the next and previous node 
 * along with storing an object
 * @author Shounak Ghosh
 * @version 10.14.2018
 */
public class DoubleNode
{
    private Object value;
    private DoubleNode previous;
    private DoubleNode next;
    /**
     * Creates a DoubleNode object
     * @param v element to be stored in the DoubleNode
     */
    public DoubleNode(Object v)
    {
        value = v;
        previous = null;
        next = null;
    }
    /**
     * Gets the value stored at the node
     * @return Object stored at the node
     */
    public Object getValue()
    {
        return value;
    }
    /** 
     * Gets the previous DoubleNode
     * @precondition there is an element before the current node
     * @return the previous DoubleNode
     */
    public DoubleNode getPrevious()
    {
        return previous;
    }
    /**
     * Gets the next DoubleNode
     * @precondition there is an element after the current node
     * @return the next DoubleNode
     */
    public DoubleNode getNext()
    {
        return next;
    }
    /**
     * Changes the value stored in the node
     * @param v the new object to be stored in value
     */
    public void setValue(Object v)
    {
        value = v;
    }
    /**
     * Changes the previous node
     * @param p the new DoubleNode to be stored in previous
     * @precondition there is node before the current node
     */
    public void setPrevious(DoubleNode p)
    {
        previous = p;
    }
    /**
     * Changes the next node
     * @param n the new DoubleNode to be stored in next
     * @precondition there is node after the current node
     */
    public void setNext(DoubleNode n)
    {
        next = n;
    }
}