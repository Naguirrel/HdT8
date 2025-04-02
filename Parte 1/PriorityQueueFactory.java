/**
 * Factory class to create different types of priority queues.
 */
public class PriorityQueueFactory {
    /**
     * Creates a priority queue based on the specified implementation type.
     *
     * @param <E>           The type of elements stored in the queue.
     * @param useVectorHeap If true, creates a VectorHeap; otherwise, creates a WrapperPriorityQueue.
     * @return A new instance of the chosen priority queue implementation.
     */
    public static <E extends Comparable<E>> IPriorityQueue<E> createQueue(boolean useVectorHeap) {
        return useVectorHeap ? new VectorHeap<>() : new WrapperPriorityQueue<>();
    }
}
