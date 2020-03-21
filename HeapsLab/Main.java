import java.util.Arrays;

/**
 * The driver class that will run the heap methods
 * @author 22shounakg
 * @version 1.12.2019
 */

public class Main
{
    /**
     * Driver method
     * 
     * @param args command-line arguement
     */
    public static void main(String[] args) 
    {
        Comparable[] array = new Integer[12];
        for (int i = 1; i < 12; i++) 
        {
            array[i] = (int) (Math.random() * 99 + 1); 
        }
        HeapDisplay display = new HeapDisplay();
        display.heapArrayToTree(array, 11);
        display.displayHeap(array, 11);
        System.out.println("Original Array: " + Arrays.toString(array));
        
        HeapUtil util = new HeapUtil();
       
        util.buildHeap(array, 11);
        
        display.heapArrayToTree(array, 11);
        display.displayHeap(array, 11);
        
        System.out.println("Heap Array: " + Arrays.toString(array));
        
        util.insert(array, (Comparable) 0, 11);
        
        display.heapArrayToTree(array, 11);
        display.displayHeap(array, 11);
        
        
        
    }
} 