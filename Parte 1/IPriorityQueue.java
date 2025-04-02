/**
 * Generic interface for a priority queue.
 *
 * @param <E> The type of elements stored in the queue (must be Comparable).
 */
public interface IPriorityQueue<E extends Comparable<E>> {
    /**
     * Adds an element to the priority queue.
     *
     * @param data The element to add.
     */
    void add(E data);

    /**
     * Removes and returns the highest-priority element.
     *
     * @return The removed element, or null if the queue is empty.
     */
    E remove();
}
