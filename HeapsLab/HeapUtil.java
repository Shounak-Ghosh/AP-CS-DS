/**
 * A collection of methods that can be used to manipulate heaps
 * 
 * @author 22shounakg
 * @version 1.12.2019
 */
public class HeapUtil 
{
    /**
     * Heapify converts a binary tree into a heap. A binary tree can be any tree
     * where each non-leaf node can have at most two children, 
     * whereas a heap must be a complete binary tree in 
     * which a node is greater than both of its children. (max heap) 
     * The runtime complexity of heapify is O(log n).
     * 
     * @param heap     An array representation of the heap
     * @param index    the index of the subtree to be converted 
     *                 from a binary tree to a heap
     * @param heapSize the size of the heap
     */
    public static void heapify(Comparable[] heap, int index, int heapSize) 
    {
        int left = index; // left child
        int right = index; // right child
        int maxIndex = -1; // the index with the largest value

        if (2 * index <= heapSize) 
        // 2 * index is the formula for the left child
        {
            left = 2 * index;
        }
        if (2 * index + 1 <= heapSize) 
        // 2 * index is the formula for the right child 
        {
            right = 2 * index + 1;
        }

        if (heap[left].compareTo(heap[index]) > 0) 
        { // if the left child is greater than the root
            maxIndex = left;
        }
        if (heap[right].compareTo(heap[index]) > 0) 
        {
            if (heap[right].compareTo(heap[left]) > 0) 
            { // if the right child is greater than the root and the left child
                maxIndex = right;
            }

        }
        // if the root is larger than both its children
        if (maxIndex == -1) 
        {
            return;
        }
        // swap the largest value and the root
        swap(heap, maxIndex, index);

        // recursively fix the subtree rooted at maxIndex
        heapify(heap, maxIndex, heapSize);

    }

    /**
     * Builds a heap from a binary tree.
     * Traversing in bottom-up order, heapify is
     * called on each non-leaf node as a means 
     * to convert the binary tree into a max heap. 
     * The runtime is O(n).
     * 
     * @param heap     An array representation of the heap
     * @param heapSize the size of the heap
     */
    public static void buildHeap(Comparable[] heap, int heapSize) 
    {
        // heapify each element starting from the last leaf node up to the root
        for (int i = heapSize / 2; i >= 1; i--) 
        {
            heapify(heap, i, heapSize);
        }
    }

    /**
     * Removes the root node from the heap by swapping the root and the last
     * element. Then  decrease the heapSize and call heapify in
     * order to sink the last element (which is currently the root)
     * Runtime is O(log n).
     * 
     * @param heap An array representation of the heap
     * @param heapSize the current size of the heap
     * @return The value of the root, which was the node removed from the heap.
     */
    public static Comparable remove(Comparable[] heap, int heapSize) 
    {
        swap(heap, 1, heapSize); //swap the root and last leaf node
        // decrement heapSize
        //the "deleted" element is still stored in the array
        heapSize--;
        // call heapify on the root node
        heapify(heap, 1, heapSize); 
        return heap[heapSize];
    }

    /**
     * This inserts a value into a heap by adding a node 
     * such that a complete binary tree is maintained. 
     * Then heapify is called in order to sift up the value to
     * it's correct position. The worst-case runtime is O(log n).
     * 
     * @param heap     An array representation of the heap
     * @param item     The Comparable object to be added to the heap
     * @param heapSize The current size of the heap
     * @return An array with the value properly inserted into the heap
     */
    public static Comparable[] insert
    (Comparable[] heap, Comparable item, int heapSize) 
    {
        // create array with one more value than before
        Comparable[] newHeap = new Comparable[heap.length + 1];
        for (int i = 1; i <= heapSize; i++) {
            newHeap[i] = heap[i];
        }
        heapSize++;
        //make the last leaf node the item to be added
        newHeap[newHeap.length - 1] = item;
        int index = heapSize;
        // sift the value just added up the heap to its correct position
        while (newHeap[index/2] != null && newHeap[index / 2].compareTo(item) >= 0) {
            swap(newHeap, index / 2, index);
            index /= 2;
        }
        return newHeap;
    }

    /**
     * Swaps two elements of an array. Runtime is O(1).
     * 
     * @param array An array of Comparable objects
     * @param r     The index of the first element to be swapped
     * @param c     The index of the second element to be swapped
     */
    private static void swap(Comparable[] array, int r, int c) 
    {
        Comparable temp = array[r];
        array[r] = array[c];
        array[c] = temp;
    }

    /**
     * Builds a heap out of an array of values, 
     * and continously removes the root from the max heap
     * in order to sort the values in ascending order.
     * 
     * @param heap An array representation of the heap
     * @param heapSize the current size of the heap
     */
    public static void heapSort(Comparable[] heap, int heapSize) 
    {
        buildHeap(heap, heapSize); // make a heap from the given values

        for (int i = heapSize; i >= 1; i--) 
        {
            remove(heap, heapSize); // remove the root
            heapSize--; // decrement heapSize
        }

    }

}
