package priorityQueue.binaryHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

	private List<T> heap;

	public BinaryHeap() {
		this.heap = new ArrayList<T>();
	}

	/**
	 * Constructor for binary heap adding elements from an array into it. All
	 * elements are added and then filtered down from [0,N/2] are sorted. So
	 * complexity is improved by not calling filter methods N times.
	 * 
	 * @param elements array to add and filter in the heap
	 */
	public BinaryHeap(T[] elements) {
		this.heap = new ArrayList<T>();

		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == null)
				throw new IllegalArgumentException("Any of the arguments given can not be null");
			heap.add(elements[i]);
		}

		for (int i = heap.size() / 2; i >= 0; i--) {
			filterDown(i);
		}
	}

	/**
	 * Starts filtering up the heap from a given position. Keep the filter while the
	 * position is not the root in the heap AND parent value is greater than current
	 * position. Current position(pivot) is swapped with parent in that situation
	 * and execution continues.
	 * 
	 * @param pos int to start the filtering up from
	 */
	private void filterUp(int pos) {
		if (pos < 0 || pos > heap.size() - 1)
			return;

		while (pos != 0 && getParentOfNode(pos).compareTo(heap.get(pos)) > 0) {
			Collections.swap(heap, pos, getParentPosition(pos));
			pos = getParentPosition(pos);
		}
	}

	/**
	 * Removes the element with highest priority from the heap (lowest key), which
	 * is the root. Settles the last element as root and begins the filter down
	 * after it
	 * 
	 * @return
	 */
	public T getMin() {
		if (heap.isEmpty())
			throw new IllegalStateException("Heap is empty");

		if (heap.size() == 1)
			return heap.remove(0);

		T aux = heap.get(0);
		heap.set(0, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);
		filterDown(0);
		return aux;
	}

	/**
	 * Starts filtering down the heap from a given position. Keep the filter while
	 * the position is not a leaf node AND current position(pivot) value is greater
	 * that the lowest value of its children. Current position(pivot) is swapped
	 * with the children with lowest value and execution continues.
	 * 
	 * @param pos int to start the filtering down from
	 */
	private void filterDown(int pos) {
		if (pos < 0 || pos > heap.size() - 1)
			return;

		while (!isLeafNode(pos)) {
			int child = getLeftChildPosition(pos);

			if (getRightChildPosition(pos) <= heap.size() - 1) {
				child = getChildrenWithMinimumValue(pos);
			}

			if (heap.get(pos).compareTo(heap.get(child)) > 0) {
				Collections.swap(heap, pos, child);
				pos = child;
			} else {
				break;
			}
		}
	}

	private int getChildrenWithMinimumValue(int pos) {
		if (heap.get(getLeftChildPosition(pos)).compareTo(heap.get(getRightChildPosition(pos))) < 0)
			return getLeftChildPosition(pos);

		return getRightChildPosition(pos);
	}

	/**
	 * Given the position of a child, returns the position of its parent
	 * 
	 * @param pos child of the parent
	 * @return int position of the parent
	 */
	private int getParentPosition(int pos) {
		return (pos - 1) / 2;
	}

	/**
	 * Given the position of a parent, return the position of left child
	 * 
	 * @param pos parent
	 * @return int position of the left child
	 */
	private int getLeftChildPosition(int pos) {
		return (pos * 2) + 1;
	}

	/**
	 * Given the position of a parent, return the position of right child
	 * 
	 * @param pos parent
	 * @return int position of the right child
	 */
	private int getRightChildPosition(int pos) {
		return (pos * 2) + 2;
	}

	/**
	 * Given a position in the queue, determines if it is a leaf node or not. A
	 * position is leaf when there is no position in the queue for its right AND
	 * left child (the position of its left AND right child does not exist)
	 * 
	 * @param pos to determine if it is a leaf node
	 * @return true if is a leaf node. False otherwise.
	 */
	private boolean isLeafNode(int pos) {
		return getLeftChildPosition(pos) > heap.size() - 1 && getRightChildPosition(pos) > heap.size() - 1;
	}

	/**
	 * Add and element in the last position of the queue and filters it up
	 * 
	 * @param element T to be added
	 */
	public void add(T element) {
		if (element == null)
			throw new IllegalArgumentException("Element to be added can not be null");

		heap.add(element);
		filterUp(heap.size() - 1);
	}

	/**
	 * Given a child position, return the element of its parent
	 * 
	 * @param pos child of the parent
	 * @return T element contained by parent of the child
	 */
	private T getParentOfNode(int pos) {
		return heap.get(getParentPosition(pos));
	}

	/**
	 * Checks if the binary heap contains any elements inside of it.
	 * 
	 * @return True if the heap has no elements. False otherwise.
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	@Override
	public String toString() {
		return heap.toString();
	}
}