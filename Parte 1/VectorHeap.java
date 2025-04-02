import java.util.Vector;

/**
 * Custom implementation of a priority queue using a binary heap.
 *
 * @param <E> The type of elements stored in the queue (must be Comparable).
 */
public class VectorHeap<E extends Comparable<E>> implements IPriorityQueue<E> {
    private Vector<E> heap;

    /**
     * Constructs an empty priority queue.
     */
    public VectorHeap() {
        heap = new Vector<>();
    }

    /**
     * Adds an element to the priority queue while maintaining heap order.
     *
     * @param data The element to add.
     */
    @Override
    public void add(E data) {
        heap.add(data); // Add element to the end
        upHeap(heap.size() - 1); // Restore heap property
    }

    /**
     * Removes and returns the highest-priority element (smallest).
     *
     * @return The removed element, or null if the queue is empty.
     */
    @Override
    public E remove() {
        if (heap.isEmpty()) return null; // Prevent errors on empty heap

        E min = heap.get(0); // Get the highest-priority element (root)
        
        // If there's only one element, just remove it
        if (heap.size() == 1) {
            heap.remove(0);
            return min;
        }

        // Replace root with last element and restore heap property
        heap.set(0, heap.remove(heap.size() - 1));
        downHeap(0);

        return min;
    }

    /**
     * Restores heap order after adding an element.
     *
     * @param index The index of the newly added element.
     */
    private void upHeap(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parent)) >= 0) break; // Correct order
            swap(index, parent);
            index = parent;
        }
    }

    /**
     * Restores heap order after removing the root element.
     *
     * @param index The index to start the downHeap operation.
     */
    private void downHeap(int index) {
        int left, right, smallest;
        while ((left = 2 * index + 1) < heap.size()) {
            right = left + 1;
            smallest = (right < heap.size() && heap.get(right).compareTo(heap.get(left)) < 0) ? right : left;
            if (heap.get(index).compareTo(heap.get(smallest)) <= 0) break; // Heap order restored
            swap(index, smallest);
            index = smallest;
        }
    }

    /**
     * Swaps two elements in the heap.
     *
     * @param i First index.
     * @param j Second index.
     */
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
