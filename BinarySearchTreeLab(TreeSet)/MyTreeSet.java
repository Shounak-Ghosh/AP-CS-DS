import java.util.Iterator;
/**
 * My implementation of a set using a binary search tree to store the values
 * @author Shounak Ghosh
 * @version 12.02.2018
 *
 * @param <E> parameter type placeholder
 */
public class MyTreeSet<E>
{
    private TreeNode root;
    private int size;
    private TreeDisplay display;

    /**
     * Creates a MyTreeSet object that can store a set of values
     */
    public MyTreeSet()
    {
        root = null;
        size = 0;
        display = new TreeDisplay();

        //wait 1 millisecond when visiting a node
        display.setDelay(1);
    }

    /**
     * Retrieves the size of the set
     * @return the number of elements stored in the set
     */
    public int size()
    {
        display.displayTree(root);
        return size;
    }

    /**
     * Checks whether a value is stored within the set
     * @param obj value to be checked for
     * @return true if the object is found within the set, false otherwise
     */
    public boolean contains(Object obj)
    {
        //throw new RuntimeException("Implement me!");
        display.displayTree(root);
        return BSTUtilities.contains(root, (Comparable) obj, display);
    }

    // if obj is not present in this set, adds obj and
    // returns true; otherwise returns false
    /**
     * Adds an object to the set given that
     * it is not already present within the set
     * @param obj the value to be added
     * @return true if the object was added, false otherwise
     */
    public boolean add(E obj)
    {
        //throw new RuntimeException("Implement me!");

        if (!contains(obj)) 
        {
            root = BSTUtilities.insert(root, (Comparable) obj, display);
            display.displayTree(root);
            size++;
            return true;
        }
        return false;
    }

    // if obj is present in this set, removes obj and
    // returns true; otherwise returns false
    /**
     * Removes an object from the set given that it is present in the set
     * @param obj the object to be removed
     * @return true if the object was removed, false otherwise
     */
    public boolean remove(Object obj)
    {
        //throw new RuntimeException("Implement me!");
        if (contains(obj)) 
        {
            root = BSTUtilities.delete(root, (Comparable) obj, display);
            display.displayTree(root);
            size--;
            return true;
        }
        return false;
    }

    /**
     * Creates a String representation of the set
     * @return a String representation of the set
     */
    public String toString()
    {
        return toString(root);
    }

    /**
     * Recursively prints out all the values stored in the set
     * @param t the root of the binary search tree 
     *          used to store the values of the set
     * @return a String representation of the values stored in the set
     */
    private String toString(TreeNode t)
    {
        if (t == null)
            return " ";
        return toString(t.getLeft()) + t.getValue() + toString(t.getRight());
    }
    /*
    public Iterator iterator() // make a new TreeIterator class
    {
        throw new RuntimeException("IMPLEMENT ME!");
    }
    */
}