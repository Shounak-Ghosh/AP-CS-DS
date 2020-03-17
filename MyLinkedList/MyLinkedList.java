import java.util.*;
/**
 * My implementation of a linked list;
 * based of the actual Java Linked List data structure
 * @author Shounak Ghosh
 * @version 10.12.18
 * @param <E> parameter type placeholder
 */
public class MyLinkedList<E>
{
    private DoubleNode first;
    private DoubleNode last;
    private int size;
    /**
     * Constructor that creates a MyLinkedList object
     */
    public MyLinkedList()
    {
        first = null;
        last = null;
        size = 0;
    }
    /**
     * displays the contents of the MyLinkedList object as a String
     * @return String representation of MyLinkedList
     */
    public String toString()
    {
        DoubleNode node = first;
        if (node == null)
            return "[]";
        String s = "[";
        while (node.getNext() != null)
        {
            s += node.getValue() + ", ";
            node = node.getNext();
        }
        return s + node.getValue() + "]";
    }
    /** 
    * Acesses a node starting from the first node 
    * @param index the position of the node to be accessed
    * @precondition  0 <= index <= size / 2
    * @postcondition starting from first, returns the node
    *               with given index (where index 0
    *               returns first)
    * @return the DoubleNode at the specified index
    */
    private DoubleNode getNodeFromFirst(int index)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        int i = 0;
        DoubleNode temp = first;
        while(i < index)
        {
            temp = temp.getNext();
            i++;
        }
        return temp;
    }
    /** 
     * @param index
     * @precondition  size / 2 <= index < size
     * @postcondition starting from last, returns the node
     *               with given index (where index size-1
     *               returns last)
     * @return DoubleNode at position index
     */
    private DoubleNode getNodeFromLast(int index)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        int i = size - 1;
        DoubleNode temp = last;
        while( i > index)
        {
            temp = temp.getPrevious();
            i--;
        }
        return temp;
    }
    /** 
     * @param index the position of the node to be retrieved
     * @precondition 0 <= index < size
     * @postcondition starting from first or last (whichever
     *               is closer), returns the node with given
     *               index
     * @return DoubleNode at position index
     */
    private DoubleNode getNode(int index)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        if(size/2 > index)
        {
            return getNodeFromFirst(index);
        }
        return getNodeFromLast(index);
    }
    /**
     * The total number of elements in the LinkedList
     * @return number of elements in the linked list
     */
    public int size()
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        return size;
    }
    /**
     * retrieves the element at position index
     * @param index position of the element to be accessed 
     * @return the value of the object stored at position index
     */
    public E get(int index)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        if(index == 0)
        {
            return getFirst();
        }
        else if(index == size -1)
        {
            return getLast();
        }
        return (E) getNode(index).getValue();
        //(You will need to promise the return value is of type E.)
    }
    /** 
     * Replaces the value of the object at position index
     * @param index the position of the element to be mutated
     * @param obj the new object to be stored at position index
     * @postcondition replaces the element at position index with obj
     *                returns the element formerly at the specified position
     * @return the original value stored at position index
     */
    public E set(int index, E obj)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        Object temp = getNode(index).getValue();
        getNode(index).setValue(obj);
        return (E) temp;
        //(You will need to promise the return value is of type E.)
    }
    /**
     * @param obj element to be added to the MyLinkedList
     * @postcondition appends obj to end of list; returns true
     * @return true if addition was successful 
     */
    public boolean add(E obj)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        DoubleNode newLast = new DoubleNode(obj);
        if(size == 0)
        {
            first = newLast;
            last = newLast;
            size++;
            return true;
        }
        last.setNext(newLast);
        newLast.setPrevious(last);
        last = newLast;
        // no need to set next (of newLast) to null 
        // because the DoubleNode constructor takes care of that
        size++;
        return true;
    }
    /** 
     * @param index positon of the element to be removed
     * @postcondition removes element from position index, moving elements
     *                at position index + 1 and higher to the left
     *                (subtracts 1 from their indices) and adjusts size
     *                returns the element formerly at the specified position
     * @return the previous element stored at position index
     */
    public E remove(int index)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        DoubleNode temp = getNode(index);
        if(index == size - 1)
        {
            return removeLast();
        }
        else if(index == 0)
        {
            return (E) removeFirst();
        }
        DoubleNode p = temp.getPrevious();
        DoubleNode n = temp.getNext();
        p.setNext(n);
        n.setPrevious(p);
        size--;
        return (E) temp.getValue();
        //(You will need to promise the return value is of type E.)
    }
    /** 
     * @param index position at which DoubleNode will be inserted
     * @param obj element to be stored in the DoubleNode
     * @precondition  0 <= index <= size
     * @postcondition inserts obj at position index,
     *                moving elements at position index and higher
     *                to the right (adds 1 to their indices) and adjusts size
     */
    public void add(int index, E obj)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        if(index == 0)
        {
            addFirst(obj);
        }
        else if(index == size)
        {
            addLast(obj);
        }
        else
        {
            DoubleNode temp = new DoubleNode(obj);
            DoubleNode next = getNode(index);
            DoubleNode prev = getNode(index -1);
            next.setPrevious(temp);
            prev.setNext(temp);
            temp.setNext(next);
            temp.setPrevious(prev);
            size++;
        }
    }
    /**
     * Adds a DoubleNode object to the very beginning of the MyLinkedList
     * @param obj object of type E to be added to position 0
     */
    public void addFirst(E obj)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        DoubleNode temp = new DoubleNode(obj);
        if(size == 0)
        {
            first = temp;
            last = temp;
            size++;
        }
        else
        {
            first.setPrevious(temp);
            temp.setNext(first);
            first = temp;
            size++;
        }
    }
    /**
     * Adds a DoubleNode object to the very end of the MyLinkedList
     * @param obj object of type E to be added to position size - 1
     */
    public void addLast(E obj)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        DoubleNode temp = new DoubleNode(obj);
        last.setNext(temp);
        temp.setPrevious(last);
        last = temp;
        size++;
    }
    /**
     * retrieves the values stored at the first DoubleNode object
     * @return the value stored at position 0
     */
    public E getFirst()
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        return (E) first.getValue();
        //(You will need to promise the return value is of type E.)
    }
    /**
     * retrieves the values stored at the last DoubleNode object
     * @return the value stored at position size - 1
     */
    public E getLast()
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        return (E) last.getValue();
        //(You will need to promise the return value is of type E.)
    }
    /**
     * Removes the first value stored in the MyLinkedList
     * and changes first accordingly
     * @return the value stored at the DoubleNode which was removed
     */
    public E removeFirst()
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        DoubleNode temp = first;
        if(size == 1)
        {
            first = null;
            last = null;
            size = 0;
            return (E) temp.getValue();
        }
        first = first.getNext();
        first.setPrevious(null);
        //first.getNext().setPrevious(null);
        size--;
        //first = first.getNext();
        return (E) temp.getValue();
        //(You will need to promise the return value is of type E.)
    }
    /**
     * Removes the last value stored in the MyLinkedList
     * and changes last accordingly
     * @return the value stored at the DoubleNode which was removed
     */
    public E removeLast()
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        DoubleNode temp = last;
        if(size == 1)
        {
            first = null;
            last = null;
            size = 0;
            return (E) temp.getValue();
        }
        last = last.getPrevious();
        last.setNext(null);
        //last.getPrevious().setNext(null);
        size--;
        //last = last.getPrevious();
        return (E) temp.getValue();
        //(You will need to promise the return value is of type E.)
    }
    /**
     * Creates a unidirectional iterator that 
     * traverses the elements of the MyLinkedList
     * @return a MyLinkedListIterator object
     */
    public Iterator<E> iterator()
    {
        return new MyLinkedListIterator();
    }
    /**
     * Creates a MyLinkedListIterator object that traverses the elements of 
     * a MyLinkedList
     * @author Shounak Ghosh
     * @version 10.17.2018
     */
    private class MyLinkedListIterator implements Iterator<E>
    {
        private DoubleNode nextNode;
        private int nextIndex;
        /**
         * Creates a MyLinkedListIterator object
         */
        public MyLinkedListIterator()
        {
            //throw new RuntimeException("INSERT MISSING CODE HERE");
            nextNode = first;
            nextIndex = 0;
        }
        /**
         * checks if there is an element after the current
         * @return true if next element exists
         *         false otherwise
         */
        public boolean hasNext()
        {
            //throw new RuntimeException("INSERT MISSING CODE HERE");
            return (nextIndex < size);
        }
        /**
         * returns the next element in the MyLinkedList object
         * @return the value stored
         */
        public E next()
        {
            //throw new RuntimeException("INSERT MISSING CODE HERE");
            try
            {
                Object temp = nextNode.getValue();
                nextNode = nextNode.getNext();
                nextIndex++;
                return (E) temp;
            }
            catch(NoSuchElementException e)
            {
                System.out.println("Error: Element does not exist.");
            }
            catch(ConcurrentModificationException e)
            {
                System.out.println("Error: MyLinkedList cannot be modified by MyLinkedListIterator.");
            }
            Object temporary = nextNode.getValue();
            return (E) temporary;
            //(You will need to promise the return value is of type E.)
        }
        /**
         * @postcondition removes the last element that was returned by next
         * 
         */
        public void remove()
        {
            //throw new RuntimeException("INSERT MISSING CODE HERE");
            /*
            if(nextNode.getPrevious() == first)
            {
                MyLinkedList.this.removeFirst();
                nextNode = first;
                //size--;
            }
            else if(nextNode == last)
            {
                MyLinkedList.this.removeLast();
                nextNode = last;
                //size--;
            }
            else
            {
                DoubleNode prev = nextNode.getPrevious();
                DoubleNode next = nextNode.getNext();
                prev.setNext(next);
                next.setPrevious(prev);
                nextNode = next;
                size--;
            }*/
            nextIndex--;
            MyLinkedList.this.remove(nextIndex);
        }
    }
}