/**
 * Interface for a priority queue.
 *
 * @param <E> The type of elements stored in the queue.
 */
public interface IPriorityQueue<E> {
    void add(E item);
    E remove();
}
