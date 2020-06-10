/**
 * Tests the Binary Tree. 
 * 
 * @author Anu Datar
 * @version 1/14/15
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BinarySearchTreeTester extends BinaryTreeTester
{
    public BinarySearchTreeTester()
    {
        System.out.println("Welcome to the Binary Search Tree Tester.");
        System.out.println("This will allow you to test your contains, insert and delete methods");

        System.out.println("Starting with testing insert ... ");
        
        TreeDisplay display = new TreeDisplay();
        // to get the display to send back the values when it visits a node:
        display.setTester(this);

        Comparable x = (Comparable)(8);
        TreeNode t = null;
        t = BSTUtilities.insert(t,x,display);
        x = 10;
        t = BSTUtilities.insert(t,x,display);
        x = 2;
        t = BSTUtilities.insert(t,x,display);
        x = 4;
        t = BSTUtilities.insert(t,x,display);
        x = 14;
        t = BSTUtilities.insert(t,x,display);
        x = 7;
        t = BSTUtilities.insert(t,x,display);
        
        display.displayTree(t);
        
        //System.out.println("The tree is a BST ? " + BSTUtilities.checkBST(t, display));
        Scanner sc = new Scanner(System.in);
        System.out.println("Continue?[Y/N] ... Press Y to insert and N to commence delete ");
        while (sc.hasNext() && (sc.nextLine().equalsIgnoreCase("y")))
        {
            System.out.println("Enter Value to insert - ");
            if (sc.hasNext())
            {
                int newNum = Integer.parseInt(sc.nextLine());
                System.out.println("Input was " + newNum);
                x = newNum;
                BSTUtilities.insert(t,x,display);
                display.displayTree(t);
            }   
            
            System.out.println("Continue?[Y/N] ... Press Y to insert and N to commence delete ");            
        }
  
        System.out.println("Now commencing delete nodes ");
        System.out.println("Continue?[Y/N] ... Press Y to delete and N to exit");
        while (sc.hasNext() && (sc.nextLine().equalsIgnoreCase("y")))
        {
            System.out.println("Enter Value to delete - ");
            if (sc.hasNext())
            {
                int newNum = Integer.parseInt(sc.nextLine());
                System.out.println("Input was " + newNum);
                x = newNum;
                BSTUtilities.delete(t, x, display);
                display.displayTree(t);
            }   
            
            System.out.println("Continue?[Y/N]... Press Y to delete and N to exit ");            
        }


 
        System.out.println("Done");
    }
    /**
    * called by the display object to send back the node value
    * when a node is visited
    */
    public void sendValue(Object value)
    {
        //System.out.println(value);
    }

	public static void main(String[] args)
	{
		BinarySearchTreeTester myStudents = new BinarySearchTreeTester();
		System.out.println("Thank you!!!");		
	}
}