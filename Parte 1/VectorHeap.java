import java.util.Vector;

/**
 * Custom implementation of a priority queue using a binary heap.
 *
 * @param <E> The type of elements stored in the queue (must be Comparable).
 */
public class VectorHeap<E extends Comparable<E>> implements IPriorityQueue<E> {
    private Vector<E> heap;

    public VectorHeap() {
        heap = new Vector<>();
    }

    @Override
    public void add(E data) {
        heap.add(data);
        upHeap(heap.size() - 1);
    }

    @Override
    public E remove() {
        if (heap.isEmpty()) return null;

        E min = heap.get(0);
        if (heap.size() == 1) {
            heap.remove(0);
            return min;
        }

        heap.set(0, heap.remove(heap.size() - 1));
        downHeap(0);
        return min;
    }

    private void upHeap(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parent)) >= 0) break;
            swap(index, parent);
            index = parent;
        }
    }

    private void downHeap(int index) {
        int left, right, smallest;
        while ((left = 2 * index + 1) < heap.size()) {
            right = left + 1;
            smallest = (right < heap.size() && heap.get(right).compareTo(heap.get(left)) < 0) ? right : left;
            if (heap.get(index).compareTo(heap.get(smallest)) <= 0) break;
            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
