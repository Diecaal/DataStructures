package priorityQueue.binaryHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {
	
	List<T> heap;

	public BinaryHeap() {
		this.heap = new ArrayList<T>();
	}
	
	public BinaryHeap(T[] elements) {
		this.heap = new ArrayList<T>();
		
		for(int i = 0; i < elements.length; i++) {
			if(elements[i] == null) 
				throw new IllegalArgumentException("Any of the arguments given can not be null");
		}
		
		for(int i = 0; i < elements.length; i++) {
			heap.add(elements[i]);
		}

		// CALL N/2 time a filter operation to obtain the valid binary heap
		for(int i = 0; i < elements.length/2; i++) {
			filterDown(i);
		}
	}
	
	/**
	 * Starts filtering up the heap from a given position. Keep the filter while the position is not the
	 * root in the heap AND parent value is greater than current position (Swaping to be done)
	 * @param pos int to start the filtering up from
	 */
	private void filterUp(int pos) {
		if(pos < 0 || pos > heap.size() - 1)
			throw new IllegalArgumentException("Filter up given position can not be lower than zero or greater than heap size");
		while(pos != 0 && getParentOfNode(pos).compareTo(heap.get(pos)) > 0) {
			Collections.swap(heap, pos, (pos-1)/2);
			pos = (pos-1)/2;
		}
	}
	
	/**
	 * Removes the element with highest priority from the heap (lowest key), which is the root.
	 * Settles the last element as root and begins the filter down afterwads
	 * @return
	 */
	public T getMin() {
		T aux = heap.get(0);
		heap.set(0, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);
		filterDown(0);
		return aux;
	}
	
	/**
	 * Starts filtering down the heap from a given position. Keep the filter while the position is not a
	 * leaf node AND current pos value is greater that the lowest value of its children
	 * @param pos int to start the filtering down from
	 */
	private void filterDown(int pos) {
		if(pos < 0) //code: [pos > heap.size() - 1], not checked in here as it is done by isLeafNode() method
			throw new IllegalArgumentException("Filter Down given position can not be lower than zero");
		while(!isLeafNode(pos) && heap.get(pos).compareTo(heap.get(getChildrenWithMinimumValue(pos))) > 0) {
			int childrenToBeSwaped = getChildrenWithMinimumValue(pos);
			Collections.swap(heap, pos, childrenToBeSwaped);
			pos = childrenToBeSwaped;
		}
	}
	
	private int getChildrenWithMinimumValue(int pos) {
		if(heap.get((pos * 2) + 1).compareTo(heap.get((pos * 2) + 2)) < 0) 
			return (pos * 2) + 1;
		else 
			return (pos * 2) + 2;
	}

	private boolean isLeafNode(int pos) {
		 return ( (pos*2)+1 > heap.size() - 1 || (pos*2)+2 > heap.size() - 1 );
	}

	public void add(T element) {
		heap.add(element);
		filterUp(heap.size() - 1);
	}
	
	private T getParentOfNode(int pos) {
		if(pos < 0 || pos > heap.size() - 1)
			throw new IllegalArgumentException("Position not present in the binary heap");
		return heap.get((pos-1)/2);
	}
	
	/**
	 * Checks if the binary heap contains any elements inside of it.
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