package graphs;

import java.util.ArrayList;

public class Graph<T> {
	public static final int INDEX_NOT_FOUND = -1;
	ArrayList<GraphNode<T>> nodes;
	protected boolean[][] edges;
	protected double[][] weight;

	/**
	 * Constructor to initialize the Graph, storing memory for its node list, edges
	 * and weight matrix
	 * 
	 * @param maximum capacity of the graph
	 */
	public Graph(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity must be equal/greater zero");
		}
		nodes = new ArrayList<GraphNode<T>>(capacity);
		edges = new boolean[capacity][capacity];
		weight = new double[capacity][capacity];
	}

	/**
	 * Return the index of a a node given an element or INDEX_NOT_FOUND (-1) if no
	 * node contains the element
	 * 
	 * @param element T to be searched
	 * @return INDEX_NOT_FOUND (-1) if element not present in the graph. Otherwise
	 *         >=0
	 */
	public int getNode(T element) {
		int elementIndex = INDEX_NOT_FOUND; // By default, element not found
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getElement().equals(element)) {
				elementIndex = i;
			}
		}
		return elementIndex;
	}

	/**
	 * Adds a new node to the graph with the given element. It initializes its
	 * position in edge matrix to false and weight matrix to 0.0
	 * 
	 * @param element T to be added to a node
	 * @throws Exception if element already exists into the graph or maximum
	 *                   capacity of nodes into the graph is reached
	 */
	public void addNode(T element) throws Exception {
		if (getNode(element) != INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Element already existing in the graph");
		nodes.add(new GraphNode<T>(element));
		for (int i = 0; i <= getSize() - 1; i++) {
			edges[nodes.size() - 1][i] = false;
			edges[i][nodes.size() - 1] = false;
			weight[nodes.size() - 1][i] = 0.0;
			weight[i][nodes.size() - 1] = 0.0;
		}
	}

	/**
	 * Returns the current number of nodes in the graph
	 * 
	 * @return integer number of nodes
	 */
	public int getSize() {
		return nodes.size();
	}

	/**
	 * Indicates if there's an existing edge between one node and another one given
	 * its elements
	 * 
	 * @param origin      T element of the origin node
	 * @param destination T element of the destination node
	 * @return true if edge exists between those nodes. Otherwise false
	 * @throws Exception if some of the nodes does not exist
	 */
	public boolean existsEdge(T origin, T destination) throws Exception {
		int i = getNode(origin);
		int j = getNode(destination);
		if (i == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Origin node does not exist");
		if (j == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Destination node does not exist");

		return edges[i][j];
	}

	/**
	 * Adds and edge between one node and another one given its elements
	 * 
	 * @param origin      T element of the origin node
	 * @param destination T element of the destination node
	 * @param weight      double to be assigned to the edge among nodes
	 * @throws Exception if some of the nodes does not exist or weight is < 0
	 */
	public void addEdge(T origin, T destination, double weight) throws Exception {
		int i = getNode(origin);
		int j = getNode(destination);
		if (weight < 0)
			throw new IllegalArgumentException("Weight of the edge must be >=0");
		if (i == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Origin node does not exist");
		if (j == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Destination node does not exist");

		this.edges[i][j] = true;
		this.weight[i][j] = weight;
	}

	/**
	 * Removes a node from the graph given its elements
	 * 
	 * @param element T element of the node to be removed
	 */
	public void removeNode(T element) throws Exception {
		int i = getNode(element);
		if (i == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Element asked to remove does not exist");

		if (i != getSize() - 1) { // Not the last node
			nodes.set(i, nodes.get(getSize() - 1)); // Element to be removed will be replaced by the last node

			// replace elements in edges and weights
			for (int j = 0; j < getSize() - 1; j++) {
				edges[j][i] = edges[j][getSize() - 1];
				edges[i][j] = edges[getSize() - 1][j];
				weight[i][j] = weight[getSize() - 1][j];
				weight[j][i] = weight[j][getSize() - 1];
			} // loop -> diagonal
			edges[i][i] = edges[getSize() - 1][getSize() - 1];
			weight[i][i] = weight[getSize() - 1][getSize() - 1];
		}
	}

	/**
	 * Prints out the current state of the graph. Showing the nodes with its
	 * information, and the edges and weight matrix.
	 */
	public void print() {
		for (int k = 0; k < getSize(); k++) {
			nodes.get(k).print();
		}

		for (int i = 0; i <= getSize() - 1; i++) {
			for (int j = 0; j <= getSize() - 1; j++) {
				System.out.print(edges[i][j] + "(");
				System.out.print(weight[i][j] + ") ");
			}
			System.out.println();
		}
	}

	/**
	 * Removes and edge between two nodes given its elements
	 * 
	 * @param origin      T element of the origin node
	 * @param destination T element of the destination node
	 * @throws Exception if some of the nodes does not exist
	 */
	public void removeEdge(T origin, T destination) throws Exception {
		int i = getNode(origin);
		int j = getNode(destination);
		if (i == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Origin node does not exist");
		if (j == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Destination node does not exist");
		if (!existsEdge(origin, destination))
			throw new IllegalArgumentException("No existing edge between these nodes");

		edges[i][j] = false;
		weight[i][j] = 0.0;
	}

	/**
	 * Count the number of drain node into the graph
	 * 
	 * @return integer number of drain nodes
	 */
	public int countDrainNodes() {
		int count = 0;
		for (int i = 0; i < nodes.size(); i++) {
			if (isDrainNode(nodes.get(i).getElement()))
				count++;
		}
		return count;
	}

	/**
	 * Count the number of source nodes into the graph
	 * 
	 * @return integer number of source nodes
	 */
	public int countSourceNodes() {
		int count = 0;
		for (int i = 0; i < nodes.size(); i++) {
			if (isSourceNode(nodes.get(i).getElement()))
				count++;
		}
		return count;
	}

	/**
	 * Determines if a node is drain given its element. A node is drain when he does
	 * not reach any node, but it can be reached by other nodes.
	 * 
	 * @param element T element of the node the analyzed
	 * @return true if node is drain. False otherwise.
	 */
	public boolean isDrainNode(T element) {
		int i = getNode(element);
		if (i == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Node does not exist");

		boolean isDrain = false;
		for (int j = 0; j <= getSize() - 1; j++) {
			if (edges[i][j] == true) // If node reaches another nodes is not a drain one
				return false;
			if (edges[j][i] == true)
				isDrain = true;
		}
		return isDrain;
	}

	/**
	 * Determines if a node is source given its element. A node is source when he
	 * reaches some node, but it can not be reached by other nodes.
	 * 
	 * @param element T element of the node the analyzed
	 * @return true if node is source. False otherwise.
	 */
	public boolean isSourceNode(T element) {
		int i = getNode(element);
		if (i == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Node does not exist");

		boolean isSource = false;
		for (int j = 0; j <= getSize() - 1; j++) {
			if (edges[i][j] == true)
				isSource = true;
			if (edges[j][i] == true) // If node is reached by another node, is not source
				return false;
		}
		return isSource;
	}

	/**
	 * Testing purposes -- Return the list containing the nodes of the graph
	 * @return
	 */
	protected ArrayList<GraphNode<T>> getNodes() {
		return nodes;
	}

	/**
	 * Testing purposes -- Return the edges matrix of the graph
	 * @return
	 */
	protected boolean[][] getEdges() {
		return edges;
	}
	
	/**
	 * Testing purposes -- Return the weight matrix of the graph
	 * @return
	 */
	protected double[][] getWeight() {
		return weight;
	}

}
