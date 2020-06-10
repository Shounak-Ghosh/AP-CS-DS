/**
 * Creates TreeNode objects that come together to form binary trees
 * @version November 28 2018
 * @author Shounak Ghosh
 */
public class TreeNode
{
    private Object value;
    private TreeNode left;
    private TreeNode right;
    /**
     * Creates a TreeNode object
     * @param initValue the value to be stored in the TreeNode
     */
    public TreeNode(Object initValue)
    { 
        this(initValue, null, null);
    }
    /**
     * @param initValue the value stored in the node
     * @param initLeft the left child TreeNode
     * @param initRight the right  child TreeNode
     */
    public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
    { 
        value = initValue; 
        left = initLeft; 
        right = initRight; 
    }
    /**
     *  retrieves the value stored in the node
     *  @return the value stored at the node
     */
    public Object getValue() { return value; }
    /**
     *  retrieves the left child
     *  @return the left TreeNode
     */
    public TreeNode getLeft() { return left; }
    /**
     * retrieves the right child
     * @return the right TreeNode
     */
    public TreeNode getRight() { return right; }
    /**
     * Mutates the value stored at the TreeNode
     * @param theNewValue the new Object to be stored at the TreeNode
     */
    public void setValue(Object theNewValue) { value = theNewValue; }
    /**
     * Mutates the left child
     * @param  theNewLeft the new left TreeNode
     */
    public void setLeft(TreeNode theNewLeft) { left = theNewLeft; }
    /**
     * Mutates the right child
     * @param theNewRight the new right TreeNode
     */
    public void setRight(TreeNode theNewRight) { right = theNewRight; }
}