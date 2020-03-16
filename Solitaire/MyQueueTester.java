
/**
 * Tests the MyQueue object methods
 *
 * @author Shounak Ghosh
 * @version 11.09.2018
 */
public class MyQueueTester
{
    /**
     * Tests the MyQueue object
     * @param args command-line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("*** NEW TEST ***");
        MyQueue<Integer> q = new MyQueue<Integer>();
        //
        q.isEmpty();
        q.add(new Integer(0));
        q.add(new Integer(1));
        q.add(new Integer(2));
        q.add(new Integer(3));
        q.add(new Integer(4));
        q.add(new Integer(5));
        q.add(new Integer(6));
        q.add(new Integer(7));
        q.add(new Integer(8));
        q.add(new Integer(9));
        q.isEmpty();
        //now test remove
        q.remove(); // prints 0
        q.remove(); // prints 1
        q.remove(); // prints 2
        q.remove(); // prints 3
        q.remove(); // prints 4
        q.remove(); // prints 5
        q.remove(); // prints 6
        q.remove(); // prints 7
        q.remove(); // prints 8
        q.remove(); // prints 9
        q.remove(); // should not do print anything 
        q.remove(); // should not do print anything 
        q.remove(); // should not do print anything 
        q.remove(); // should not do print anything 
        q.isEmpty();
        //queue is now empty
        q.add(new Integer(0));
        q.add(new Integer(1));
        q.add(new Integer(2));
        q.add(new Integer(3));
        q.add(new Integer(4));
        q.isEmpty(); // should return false
        
        q.peek(); // should print 0
        q.peek(); // should print 0
        q.peek(); // should print 0
        q.remove();
        q.peek();
        q.add(new Integer(0));
        q.peek(); // should return 1
        q.isEmpty();
        
        
        
        
        
    }
}
