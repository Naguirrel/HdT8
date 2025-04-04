import java.util.PriorityQueue;

/**
 * Wrapper for Java's built-in PriorityQueue that implements IPriorityQueue.
 *
 * @param <E> The type of elements stored in the queue.
 */
public class WrapperPriorityQueue<E extends Comparable<E>> implements IPriorityQueue<E> {
    private PriorityQueue<E> queue;

    public WrapperPriorityQueue() {
        queue = new PriorityQueue<>();
    }

    @Override
    public void add(E item) {
        queue.add(item);
    }

    @Override
    public E remove() {
        return queue.poll();
    }
}
