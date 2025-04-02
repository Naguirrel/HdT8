import java.util.PriorityQueue;

/**
 * Wrapper for Java's built-in PriorityQueue.
 * Uses remove() instead of poll() for better compatibility.
 *
 * @param <E> The type of elements stored in the queue (must be Comparable).
 */
public class WrapperPriorityQueue<E extends Comparable<E>> implements IPriorityQueue<E> {
    private PriorityQueue<E> queue;

    /**
     * Constructs an empty priority queue.
     */
    public WrapperPriorityQueue() {
        queue = new PriorityQueue<>();
    }

    @Override
    public void add(E data) {
        queue.add(data);
    }

    @Override
    public E remove() {
        return queue.isEmpty() ? null : queue.poll();
    }
}
