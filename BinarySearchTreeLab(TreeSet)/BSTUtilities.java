/**
 * A collection of static methods for operating on binary search trees
 * @author Shounak Ghosh
 * @version 12.01.2018
 *
 */
public abstract class BSTUtilities
{
    /**
     * Checks if a given value is present within a binary search tree
     * @param t a binary search tree in ascending order
     * @param x the value to be checked for in the binary search tree
     * @param display the graphical framework used to display the tree
     * @return true if the value is found in the tree, and false otherwise
     */
    public static boolean contains(TreeNode t, 
                                   Comparable x, TreeDisplay display)
    {
        //throw new RuntimeException("IMPLEMENT ME!!!");
        if (t == null)
            return false;
        if (x.compareTo(t.getValue()) == 0)
        {
            display.visit(t);
            return true;
        }
        if (x.compareTo(t.getValue()) < 0)
        {
            display.visit(t);
            return contains(t.getLeft(), x, display);
        }

        if (x.compareTo(t.getValue()) > 0) 
        {
            display.visit(t);
            return contains(t.getRight(), x , display);
        }

        return false;
    }

    //precondition:  t is a binary search tree in ascending order
    //postcondition: if t is empty, returns a new tree containing x;
    //               otherwise, returns t, with x having been inserted
    //               at the appropriate position to maintain the binary
    //               search tree property; x is ignored if it is a
    //               duplicate of an element already in t; only one new
    //               TreeNode is created in the course of the traversal
    /**
     * Adds a given value to a binary search tree
     * Does not modify the tree if the value is already present
     * @precondition duplicate values are not inserted 
     *               into the binary search tree
     * @param t a binary search tree in ascending order
     * @param x the value to be added to the tree if
     * @param display the graphical framework that displays the tree
     * @return the new binary search tree with the value x added to it
     */
    public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
    {
        //throw new RuntimeException("IMPLEMENT ME!!!");
        if (t == null) 
        {
            return new TreeNode(x);
        }
        if (x.compareTo(t.getValue()) == 0) 
        {
            display.visit(t);
            System.out.println("Value already found in BST.");
        }
        if (x.compareTo(t.getValue()) < 0) 
        {
            display.visit(t);
            t.setLeft(insert(t.getLeft(), x, display));
        }

        if (x.compareTo(t.getValue()) > 0) 
        {
            display.visit(t);
            t.setRight(insert(t.getRight(), x, display)); 
        }

        return t;
    }

    //precondition:  t is a binary search tree in ascending order
    //postcondition: returns a pointer to a binary search tree,
    //               in which the value at node t has been deleted
    //               (and no new TreeNodes have been created)
    /**
     * Deletes a node from a binary search tree
     * @param t the node to be deleted
     * @param display the graphical framework for display the tree
     * @return the new tree with t deleted
     */
    private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
    {
        // t is the node you want to delete in this method
        // return the new tree with t deleted

        // no successor
        if (t.getRight() == null)
            return t.getLeft();

        // successor is the right child
        if (t.getRight().getLeft() == null)
        {
            TreeNode temp = t.getRight().getRight();
            t.setValue(t.getRight().getValue());
            t.setRight(temp);
            return t;
        } 
        // successor is NOT the right child
        else 
        {
            t.setValue(TreeUtil.leftmost(t.getRight()));
            TreeNode temp = minNode(t.getRight()).getRight();
            secondMinNode(t.getRight()).setLeft(temp);
            return t;
        }
    }

    /**
     * finds the node with the smallest value
     * @param t a binary search tree in ascending order
     * @return the node with the smallest value in the binary search tree t
     */
    private static TreeNode minNode(TreeNode t) 
    {

        if (t == null || t.getLeft() == null) 
        {
            return t;
        }
        return minNode(t.getLeft());

    }
    /**
     *  find the node with the second smallest value
     *  @param t a binary search tree i ascending order
     *  @return the node with the second smallest value 
     *          in the binary search tree t
     */
    private static TreeNode secondMinNode(TreeNode t) 
    {
        if (t == null) 
        {
            return null;
        }
        if (t.getLeft() == null) 
        {
            return null;
        }
        if (t.getLeft().getLeft() == null) 
        {
            return t;
        }
        return secondMinNode(t.getLeft());
    }

    //precondition:  t is a binary search tree in ascending order
    //postcondition: returns a pointer to a binary search tree,
    //               in which the value x has been deleted (if present)
    //               (and no new TreeNodes have been created)
    /**
     * deletes a value from a binary search tree
     * the tree is not modified if the value is not present in the tree
     * @param t a binary search tree in ascending order
     * @param x the value to be deleted from the tree
     * @param display the graphical framework for displaying the tree
     * @return the new binary search tree with the value deleted from it
     */
    public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
    {
        //throw new RuntimeException("IMPLEMENT ME!!!");

        if (!contains(t, x, display)) 
        {
            System.out.println("Value not present in BST.");
            return t;
        }

        // base case
        if (t == null) 
        {
            return null;
        }
        // find the node to be deleted
        if (x.compareTo(t.getValue()) < 0) 
        {
            display.visit(t);
            t.setLeft(delete(t.getLeft(), x, display));
        }
        else if (x.compareTo(t.getValue()) > 0) 
        {
            display.visit(t);
            t.setRight(delete(t.getRight(), x, display));
        }
        // node to be deleted
        else
        {
            display.visit(t);

            return deleteNode(t, display);
        }

        return t;
    }
}