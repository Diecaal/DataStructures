package graphs;

public class GraphNode<T> {

	private T element;
	private boolean visited;
	
	/** 
	 * Constructor to initialize the GraphNode
	 * @param element T element to be assigned to the node
	 */
	public GraphNode(T element) {
		this.element = element;
	}
	
	/** 
	 * Constructor to initialize the GraphNode
	 * @param element T element to be assigned to the node
	 * @param visited boolean if not has been visited or not
	 */
	public GraphNode(T element, boolean visited) {
		this.element = element;
		this.visited = visited;
	}

	/**
	 * Return the element contained by the node
	 * @return T element contained by the node
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Sets the element of the node
	 * @param element T element to be assigned to the node
	 */
	public void setElement(T element) {
		this.element = element;
	}

	/**
	 * Returns if the node has been visited or not
	 * @return true if the node has been visited. False otherwise.
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Sets if the node has been visited or not.
	 * @param visited boolean determining if node has been visited.
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Prints out the current status and information of the node
	 */
	public void print() {
		System.out.println(toString());
	}
	
	/**
	 * Overriding of the toString method in GraphNode for our purposes 
	 */
	@Override
	public String toString() {
		return String.format("GN(N:%s/V:%s)", getElement(), isVisited());
	}
}
