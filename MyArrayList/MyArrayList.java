import java.util.Iterator;
import java.util.ListIterator;
import java.util.ConcurrentModificationException;

/**
 * An implementation of the Java ArrayList class
 * @author Shounak Ghosh
 * @version 10.6.2018
 * @param <E> parameter type placeholder
 */
public class MyArrayList<E>
{
    private int size; //stores the displayed size of the arrayList
    private Object[] values;  //(Java doesn't let us make an array of type E)
    // values.length is the actual size(capacity) of the underlying array
    /**
     * Constructor that initializes size to 0 and
     * values to an Object array of length 1
     */
    public MyArrayList()
    {
        size = 0;
        values = new Object[1];
    }

    /**
     * Prints the elements stored in the MyArrayList object to the terminal
     * @return a String representation of all the elements stored in the MyArrayList object 
     */
    public String toString()
    {
        if (size == 0)
        {
            return "[]";
        }
        String s = "[";
        for (int i = 0; i < size - 1; i++)
        {
            s += values[i] + ", ";
        }
        return s + values[size - 1] + "]";
    }

    /**
     * doubles the total possible size (capacity) of the SmartArray
     * @postcondition replaces the array with one that is
     *                twice as long, and copies all of the
     *                old elements into it
     */
    private void doubleCapacity()
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        Object[] temp = new Object[2 * values.length];
        for(int i = 0; i < size; i++)
        {
            temp[i] = values[i];
        }
        values = temp;
    }

    /**
     * Returns the length of the underlying array used to store the Objects.
     * @postcondition returns the length of the array
     * @return length of values
     */
    public int getCapacity()
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        return values.length;
    }

    /**
     * acesses the size of the MyArrayList object
     * @return the number of elements stored in the MyArrayList object
     */
    public int size()
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        return size;
    }

    /**
     * Returns the value of the object stored at the given index in the SmartArray.
     * @param index position of the element
     * @return element stored at the position index.
     */
    public E get(int index)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        return (E) values[index];
        //(You will need to promise the return value is of type E.)
    }

    /** 
     * Mutates(changes) the value at index to obj.
     * @param index position of element to be changed
     * @param obj the new element at position index
     * @precondition index must be between 0 and size()-1, inclusive
     * @postcondition replaces the element at position index with obj
     *                returns the element formerly at the specified position
     * @return element previously stored at position index
     */
    public E set(int index, E obj)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        Object temp = values[index];
        values[index] = obj;
        return (E) temp ;
        //(You will need to promise the return value is of type E.)
    }

    /**
     * Appends obj to the end of the list; resizes MyArrayList if size equals capacity
     * @param obj element to be added to MyArrayList
     * @postcondition appends obj to end of list; returns true
     * @return true if add method was sucessful,
     *         false otherwise
     */
    public boolean add(E obj)
    {
        /* if values is already full, call doubleCapacity before adding */
        if(size == values.length)
        {
            doubleCapacity();
        }
        values[size] = obj;
        size++;
        return true;
        //throw new RuntimeException("INSERT MISSING CODE HERE");
    }

    /**
     * Removes the element at index, and shifts all elements accordingly.
     * @param index position of element to be removed
     * @postcondition removes element from position index, moving elements
     *                at position index + 1 and higher to the left
     *                (subtracts 1 from their indices) and adjusts size
     *                returns the element formerly at the specified position
     *@return element removed from MyArrayList
     */
    public E remove(int index)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        Object temp = get(index); 
        for(int i = index; i < size - 1; i++)
        {
            values[i] = values[i+1];
        }
        size--;
        return (E) temp;
        //(You will need to promise the return value is of type E.)
    }

    /**
     * Creates an unidirectional iterator that traverses the elements of a list
     * @return a MyArrayListIterator object
     */
    public Iterator<E> iterator()
    {
        return new MyArrayListIterator();
    }

    /**
     * Creates an bidirectional iterator that traverses the elements of a list
     * @return a MyArrayListListIterator object
     */
    public ListIterator<E> listIterator()
    {
        return new MyArrayListListIterator();
    }

    /**
     * @param index position of element to be inserted
     * @param obj element to be inserted at position index
     * @precondition  0 <= index <= size
     * @postcondition inserts obj at position index,
     *               moving elements at position index and higher
     *               to the right (adds 1 to their indices) and adjusts size
     */
    public void add(int index, E obj)
    {
        //throw new RuntimeException("INSERT MISSING CODE HERE");
        if(size == values.length)
        {
            doubleCapacity();
        }

        for(int i = size - 1; i > index; i--) // go from the back
        {                                     // storage of element is not needed then
            values[i+1] = values[i];
        }
        set(index, obj);
    }
    /**
     * 
     */
    private class MyArrayListIterator implements Iterator<E>
    {
        //the index of the value that will be returned by next()
        // Think of iterator as to be pointing between elements
        private int nextIndex;
        /**
         * initializes nextIndex to 0
         */
        public MyArrayListIterator()
        {
            //throw new RuntimeException("INSERT MISSING CODE HERE");
            nextIndex = 0;
        }

        /**
         * checks if there is an element after the current element
         * @return true if next element exists;
         *         false otherwise
         */
        public boolean hasNext()
        {
            //throw new RuntimeException("INSERT MISSING CODE HERE");
            if(nextIndex == MyArrayList.this.size())
            {
                return false;
            }
            return values[nextIndex + 1] != null;
        }

        /**
         * acesses the next element in the MyArrayList
         * @precondition there is an element to access
         * @return the next element in the MyArrayList
         */
        public E next()
        {
            //throw new RuntimeException("INSERT MISSING CODE HERE");
            //Object temp = values[nextIndex];
            nextIndex++;
            return (E) values[nextIndex -1];
            //(You will need to promise the return value is of type E.)
        }

        /**
         * removes the last element returned by next()
         */
        //@postcondition removes the last element that was returned by next
        public void remove()
        {
            //throw new RuntimeException("INSERT MISSING CODE HERE");
            MyArrayList.this.remove(nextIndex - 1);
        }
    }
    /**
     * Creates a MyArrayListListIterator 
     * that traverses MyArrayList in both directions
     * @author Shounak Ghosh
     * @version 10.9.2018
     */
    private class MyArrayListListIterator extends MyArrayListIterator implements ListIterator<E>
    {
        // note the extends MyArrayListIterator 
        // Remember this class thus inherits the methods from the parent class.

        private int nextIndex;
        private int previousIndex;
        // Index of element that will be returned by the next call of next()
        private boolean forward; //direction of traversal

        /**
         * Constructs a new MyArrayListListIterator
         */
        public MyArrayListListIterator()
        {
            nextIndex = 0;
            previousIndex = -1;
            forward = true;
        }

        /**
         * checks if there is an element after the current element
         * @return true if next element exists;
         *         false otherwise
         */
        public boolean hasNext()
        {
            //throw new RuntimeException("INSERT MISSING CODE HERE");
            if(nextIndex == MyArrayList.this.size())
            {
                return false;
            }
            return values[nextIndex + 1] != null;
        }

        /**
         * Returns next element and moves pointer forward
         * @precondition there exists a next element in the ArrayList
         * @return next Object in MyArrayList
         */
        public E next()
        {
            /* /
            if (modified)
            {
            throw new ConcurrentModificationException();
            }
            System.out.println("INSERT MISSING CODE HERE");
            /* */
            nextIndex++;
            previousIndex++;
            return (E) values[previousIndex]; 
        }

        /**
         * Adds element before element that would be returned by next
         * @param obj element to add
         */
        public void add(E obj)
        {
            //System.out.println("INSERT MISSING CODE HERE");\
            MyArrayList.this.add(nextIndex , obj);
            nextIndex++;
            previousIndex++;
            forward = true;
        }

        /**
         * Determines whether there is another element in MyArrayList
         * while traversing in the backward direction
         * @return true if there is another element, 
         *         false otherwise
         */
        public boolean hasPrevious()
        {
            //System.out.println("INSERT MISSING CODE HERE");
            return previousIndex >= 0;
        }

        /**
         * Returns previous element and moves pointer backward
         * @precondition a previous element exists in the ArrayList
         * @return previous Object in MyArrayList
         */
        public E previous()
        {
            //System.out.println("INSERT MISSING CODE HERE");
            nextIndex--;
            previousIndex--;
            forward = false;
            return (E) values[nextIndex];
        }

        /**
         * Returns index of the next element 
         * @return index of element that would be 
         *         returned by a call to next()
         */
        public int nextIndex()
        {
            System.out.println("INSERT MISSING CODE HERE");
            return nextIndex;
        }

        /**
         * Returns index of the previous element 
         * @return index of element that would be 
         *         returned by a call to previous()
         */
        public int previousIndex()
        {
            System.out.println("INSERT MISSING CODE HERE");
            return previousIndex;
        }

        /**
         * Removes element that was returned by next() or previous()
         * USE direction FOR THIS
         */
        public void remove() // use direction to choose between nextIndex
        // or previousIndex
        {
            //System.out.println("INSERT MISSING CODE HERE");
            if(forward)
            {
                MyArrayList.this.remove(previousIndex);
            }
            else
            {
                MyArrayList.this.remove(nextIndex);
            }
        }

        /**
         * Mutates element that was returned by next() or previous() to obj
         * @param obj the new element to be stored 
         */
        public void set(E obj)
        {
            //System.out.println("INSERT MISSING CODE HERE");
            if(forward)
            {
                MyArrayList.this.set(previousIndex, obj);
            }
            else
            {
                MyArrayList.this.set(nextIndex, obj);
            }
        }
    }

}