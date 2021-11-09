package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 * Graph class for performing different algorithms
 * 
 * @author UO271506
 *
 * @param <T>
 */
public class Graph<T> {
	public static final int INDEX_NOT_FOUND = -1;
	public static final int EMPTY = -1;
	public static final double INFINITE = Double.POSITIVE_INFINITY;
	public static final double INFINITE_NEGATIVE = Double.NEGATIVE_INFINITY;

	ArrayList<GraphNode<T>> nodes;
	protected boolean[][] edges;
	protected double[][] weight;

	/*-------------- GRAPH --------------*/

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
		// Floyd structures initialization
		A = new double[capacity][capacity];
		P = new int[capacity][capacity];
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

		nodes.remove(getSize() - 1);
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
			if (edges[i][j]) // If node reaches another nodes is not a drain one
				return false;
			if (edges[j][i])
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
			if (edges[i][j])
				isSource = true;
			if (edges[j][i]) // If node is reached by another node, is not source
				return false;
		}
		return isSource;
	}

	/*-------------- EXTRA EXERCISES  ---------------------------*/

	/**
	 * Determines if current graph is strongly connected, meaning that there exists
	 * a path from every node to every other node in the graph
	 * 
	 * @return boolean true if graph is strongly connected. False otherwise
	 */
	public boolean isStronglyConnected() {
		boolean stronglyConnected = true;

		/* Floyd usage */
		floyd();
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (A[i][j] == INFINITE)
					return false;
			}
		}

		/* Dijkstra usage */
//		for(int currentNode = 0; currentNode < getSize(); currentNode++) {
//			double[] PD  = dijkstra(nodes.get(currentNode).getElement());
//			for(int i = 0; i < PD.length; i++) {
//				if(i != currentNode && PD[i] == INFINITE) {
//					return false;
//				}
//			}
//		}

		return stronglyConnected;
	}

	/**
	 * Determines if current graph is semi connected, meaning that a path from a
	 * node to any other node in the graph contains every other node
	 * 
	 * @return boolean true if graph is strongly connected. False otherwise
	 */
	public boolean isSemiConnected() {
		boolean stronglyConnected = true;
		for (int currentNode = 0; currentNode < getSize(); currentNode++) {
			double[] PD = dijkstra(nodes.get(currentNode).getElement());
			for (int i = 0; i < PD.length; i++) {
				if (i != currentNode && PD[i] == INFINITE) {
					stronglyConnected = false;
				}
			}
		}
		return stronglyConnected;
	}

	/**
	 * Determines if current graph is weakly connected. It is checked turning all
	 * the edges and weights in our graph to bidirectional ones, and checking over
	 * this new graph distribution if it is strongly connected. At the end the
	 * previos edges and weight are settled again
	 * 
	 * @return boolean true if the graph is strongly connected after turning their
	 *         edges into bidirectional ones. False otherwise
	 */
	public boolean isWeaklyConnected() {
		boolean toRet;

		boolean[][] edgesAux = edges;
		double[][] weightAux = weight;

		turnEdgesToBidirectional();
		toRet = this.isStronglyConnected();

		edges = edgesAux;
		weight = weightAux;

		return toRet;
	}

	/**
	 * Turn all the directed edges and weights in the graph into bidirectional ones.
	 */
	public void turnEdgesToBidirectional() {
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (edges[i][j]) {
					edges[j][i] = true;
					weight[j][i] = weight[i][j];
				}
			}
		}
	}

	/**
	 * Calculate the cheapest path costh from a node to another one by using path
	 * cost matrix [D] of dijkstra algorithm
	 * 
	 * @param origin      T element for origin of the path
	 * @param destination T element for the end of the path
	 * @return double path cost contained by D matrix after dijkstra execution
	 */
	public double cheapestPathCost(T origin, T destination) {
		checkInitialNodes(origin, destination);

		double[] D = dijkstra(origin);
		int destNode = getNode(destination);

		return D[destNode];
	}

	/**
	 * Calculate the shortest path cost from a node to another one by using minimum
	 * path cost matrix [P] of floyd algorithm
	 * 
	 * New floyd method and floyd initializacion are created, as for the correct
	 * perform of this algorithm all the weight among nodes with existing edges will
	 * be 1. This way floyd will forget about cost measurements, and only take into
	 * accounts shortest paths as all are weighted the same
	 * 
	 * @param origin      T element for origin of the path
	 * @param destination T element for the end of the path
	 * @return int shortest path cost contained by P matrix after floyd execution
	 */
	public int shortestPathLength(T origin, T destination) {
		checkInitialNodes(origin, destination);
		int i = getNode(origin);
		int j = getNode(destination);

		// double[] D = dijkstraForShortestPath(origin);
		// return D[j];

		// Easier if performed with floyd as it only used its A & P matrix
		// If dijkstra used we can obtain wrong data as it accesed weight original
		// matrix
		// (more changes needes, adding 1 in compute dijkstra method)
		floydForShortestPathLength();

		return (int) A[i][j];
	}

	/**
	 * Returns the center element of a graph by performing floyd algorithm,
	 * obtaining the node with maximum cost per column, and following the smallest
	 * one among them
	 * 
	 * @return T element being the center of the graph
	 */
	public T getCenter() {
		floyd();

		List<Double> maximumCostsPerColumn = new ArrayList<Double>();
		for (int i = 0; i < getSize(); i++) {
			maximumCostsPerColumn.add(INFINITE_NEGATIVE);
		}

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (A[i][j] > maximumCostsPerColumn.get(i) && A[i][j] != INFINITE) {
					maximumCostsPerColumn.set(i, A[i][j]);
				}
			}
		}

		double minCost = INFINITE;
		int minCostPosition = EMPTY;
		for (int i = 0; i < maximumCostsPerColumn.size(); i++) {
			if (maximumCostsPerColumn.get(i) < minCost) {
				minCost = maximumCostsPerColumn.get(i);
				minCostPosition = i;
			}
		}

		return nodes.get(minCostPosition).getElement();
	}

	/**
	 * Determines if the graph has any cycle existing, meaning it can perform a path
	 * starting and finishing in the same node. This implementation is based Depth
	 * First algorithm
	 * 
	 * @return true if graph contains cycles. False otherwise
	 */
	public boolean containsCycles() {
		boolean containCycle = false;

		for (int i = 0; i < getSize(); i++) {
			resetVisited();
			String traversed = nodes.get(i).getElement().toString() + "-";
			for (int j = 0; j < getSize(); j++) {
				if (edges[i][j]) {
					traversed += DFPrint(j);
				}
			}

			String[] nodes = traversed.split("-");
			// Set tracing if repeated elements were added in the traversal (cycle found)
			Set<String> uniqueNodes = new HashSet<String>();

			// we try to find a cycle in the string of the traversal
			for (int currentNode = 0; currentNode < nodes.length; currentNode++) {
				// Repeated element found if add returns false = cycle
				if (!uniqueNodes.add(nodes[currentNode]))
					return true;
			}
		} // for every node in the graph
		return containCycle;
	}

	/**
	 * Prints the path traversed by Breath First Search
	 * 
	 * @param element source node to begin the search
	 * @return String representing the traversed nodes
	 */
	public String BFPrint(T element) {
		checkInitialNode(element);

		int startingNode = getNode(element);
		resetVisited();
		Queue<Integer> queue = new ArrayDeque<Integer>(getSize());
		queue.add(startingNode);

		String traversed = nodes.get(startingNode).getElement() + "-";

		while (!queue.isEmpty()) {
			for (int i = 0; i < getSize(); i++) {
				if (edges[queue.peek()][i] && !nodes.get(i).isVisited()) {
					nodes.get(i).setVisited(true);
					traversed += nodes.get(i).getElement() + "-";
					queue.add(i);
				}
			}
			queue.remove();
		}

		return traversed;
	}

	/*-------------- DEPTH FIRST SEARCH ALGORITHMS --------------*/

	/**
	 * Method to initiate the traversal of the graph with Depth First Search given
	 * an starting element. All nodes are reinitialized to false before.
	 * 
	 * @param element T element to begin the traversal
	 * @return String containing the traversal order of the algorithm
	 * @throws Exception if the given element not corresponds to any node
	 */
	public String traverseGraphDF(T element) throws Exception {
		int i = getNode(element);
		if (i == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Node does not exist");

		resetVisited();

		return DFPrint(i);
	}

	/**
	 * Recursive method for traversing through all the children nodes of a given
	 * current index, setting visited to true in all the nodes and retrieving its
	 * elements.
	 * 
	 * @param currentIndex integer to perform the search
	 * @return String containing the element of current iteration and child ones
	 */
	public String DFPrint(int currentIndex) {
		nodes.get(currentIndex).setVisited(true);
		String traversed = nodes.get(currentIndex).getElement().toString() + "-";
		for (int j = 0; j < getSize(); j++) {
			if (edges[currentIndex][j] == true && !nodes.get(j).isVisited()) {
				traversed += DFPrint(j);
			}
		}
		return traversed;
	}

	/**
	 * Resets all the graph nodes in the graph to not visited
	 */
	private void resetVisited() {
		for (int i = 0; i < getSize(); i++) {
			nodes.get(i).setVisited(false);
		}
	}

	/*-------------- FLOYD ALGORITHMS --------------*/

	protected double[][] A; // Matrix with minimum cost
	protected int[][] P; // Matrix with minimum path cost (intermediate nodes)

	/**
	 * Call to perform the floyd algorithm over our graph.
	 * 
	 * @param iterations integer of nodes to be taken into account for floyd
	 *                   algorithm
	 */
	public void floyd(int iterations) {
		initsFloyd(); // Initialize structures to start floyd algorithm
		for (int k = 0; k < iterations; k++) {
			for (int i = 0; i < getSize(); i++) {
				for (int j = 0; j < getSize(); j++) {
					// better path from i to j passing through k than
					// the one already exising in A
					if (A[i][k] + A[k][j] < A[i][j]) {
						A[i][j] = A[i][k] + A[k][j]; // upade cost in A
						P[i][j] = k; // update cost path in P (currentNode)
					}
				}
			}
		}
	}

	/**
	 * Floyd method used specifically to solve shortest part algorithm, initializing
	 * its A matrix weight to 1 when edges among nodes existing
	 */
	public void floydForShortestPathLength() {
		initsFloydForShortestPathLength(); // Initialize structures to start floyd algorithm
		for (int k = 0; k < getSize(); k++) {
			for (int i = 0; i < getSize(); i++) {
				for (int j = 0; j < getSize(); j++) {
					// better path from i to j passing through k than
					// the one already exising in A
					if (A[i][k] + A[k][j] < A[i][j]) {
						A[i][j] = A[i][k] + A[k][j]; // upade cost in A
						P[i][j] = k; // update cost path in P (currentNode)
					}
				}
			}
		}
	}

	/**
	 * Call to perform the floyd algorithm over our graph algorithm
	 */
	public void floyd() {
		this.floyd(this.getSize());
	}

	/**
	 * Method used prior to initialize Floyd algorithm, filling our auxiliar
	 * structures. A matrix with INFINITE values and P matrix with -1 values.
	 */
	private void initsFloyd() {
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (i == j) // Fill diagonal with zeros
					A[i][j] = 0.0;
				else {
					if (edges[i][j]) // A direct graph between nodes exists
						A[i][j] = weight[i][j];

					else // Othetwise INFINITE cost among these nodes
						A[i][j] = INFINITE;
				}
				P[i][j] = EMPTY;
			}
		}
	}

	/**
	 * Sets the floyd A matrix to 1 when edges among nodes exist, so that algorithm
	 * only takes into account the number of edges among nodes (shortest path) and
	 * not the weights among them
	 */
	private void initsFloydForShortestPathLength() {
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (i == j) // Fill diagonal with zeros
					A[i][j] = 0.0;
				else {
					if (edges[i][j]) // A direct graph between nodes exists
						A[i][j] = 1;

					else // Othetwise INFINITE cost among these nodes
						A[i][j] = INFINITE;
				}
				P[i][j] = EMPTY;
			}
		}
	}

	/**
	 * Return the path given by floyd between two nodes
	 * 
	 * @param origin      T element of the origin node
	 * @param destination T element of the destination node
	 * @return string representation of the path between both nodes
	 */
	public String printFloydPath(T origin, T destination) {
		int i = getNode(origin);
		int j = getNode(destination);
		if (i == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Origin node does not exist");
		if (j == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Destination node does not exist");

		int k = P[i][j];

		String path = String.format("%s-", origin.toString());
		if (k != EMPTY) {
			path += String.format("%s-", nodes.get(k).getElement().toString());
			path += printFloyPathRec(origin, nodes.get(k).getElement());
			path += printFloyPathRec(nodes.get(k).getElement(), destination);
		}
		path += String.format("%s", destination.toString());

		return path;
	}

	/**
	 * Recursive call for print floyd call that will be called from main print path
	 * method once the original [origin-destination] edge is evaluated
	 * 
	 * @param origin      T element of origin in each call
	 * @param destination T element of destination in each all
	 * @return string representation of path between both nodes in each call
	 */
	private String printFloyPathRec(T origin, T destination) {
		int i = getNode(origin);
		int j = getNode(destination);

		String path = "";
		int k = P[i][j];

		if (k != EMPTY) {
			path += printFloyPathRec(origin, nodes.get(k).getElement());
			path += String.format("%s-", nodes.get(k).getElement().toString());
			path += printFloyPathRec(nodes.get(k).getElement(), destination);
		}

		return path;
	}

	/*-------------- DIJKSTRA ALGORITHMS --------------*/

	protected double[] D;
	protected int[] PD;
	protected List<GraphNode<T>> S; // Set containing the pivots already used for dijkstra algorithm

	/**
	 * Call to peform Dijkstra algorihm over the graph from a starting node
	 * 
	 * @param i int representing node selected for Dijkstra algorithm
	 * @return []double minimum costs array (D)
	 */
	public double[] dijkstra(T element) {
		int initialElementIndex = getNode(element);
		initsDijkstra(initialElementIndex);

		S = new ArrayList<>(getSize());

		int pivot = initialElementIndex;
		nodes.get(initialElementIndex).setVisited(true);

		while (S.size() < getSize()) {
			S.add(nodes.get(pivot));
			for (int i = 0; i < getSize(); i++) {
				if (edges[pivot][i] && D[pivot] != INFINITE) {
					if (D[pivot] + weight[pivot][i] < D[i]) {
						D[i] = D[pivot] + weight[pivot][i];
						PD[i] = pivot;
					}
				}
			}
			pivot = getPivot();
		}

		return D;
	}

	/**
	 * Implementation of dijkstra for resolution of shortest path algorithm, calling
	 * its init method to add 1 in D matrix when edges existing. At every single
	 * time dijkstra algorithm used weights, we will replace it by 1 (comparing and
	 * updating values)
	 * 
	 * @param element T source element to start dijkstra from
	 * @return double[] D matrix containing the costs to every node from selected
	 *         node
	 */
	private double[] dijkstraForShortestPath(T element) {
		int initialElementIndex = getNode(element);
		initsDijkstraForShortestPathLenght(initialElementIndex);

		S = new ArrayList<>(getSize());

		int pivot = initialElementIndex;
		nodes.get(initialElementIndex).setVisited(true);

		while (S.size() < getSize()) {
			S.add(nodes.get(pivot));
			for (int i = 0; i < getSize(); i++) {
				if (edges[pivot][i] && D[pivot] != INFINITE) {
					if (D[pivot] + 1 < D[i]) {
						D[i] = D[pivot] + 1;
						PD[i] = pivot;
					}
				}
			}
			pivot = getPivot();
		}

		return D;
	}

	/**
	 * Gets the best pivot for dijkstra taking into account D cost matrix current
	 * status
	 * 
	 * @return int representing the node selected to be pivot
	 */
	private int getPivot() {
		double minCost = INFINITE;
		int minCostPosition = 0;
		for (int i = 0; i < getSize(); i++) {
			if (D[i] < minCost && !nodes.get(i).isVisited()) {
				minCost = D[i];
				minCostPosition = i;
			}
		}
		nodes.get(minCostPosition).setVisited(true);
		return minCostPosition;
	}

	/**
	 * Initialize the D and PD matrices for dijkstra algorithm
	 * 
	 * @param elementIndex T source element for dijkstra
	 */
	public void initsDijkstra(int elementIndex) {
		D = new double[getSize()];
		PD = new int[getSize()];

		// Initialize all nodes to not visited before next execution
		// for a correct working of S set
		resetVisited();

		for (int i = 0; i < getSize(); i++) {
			if (i == elementIndex)
				D[i] = 0;
			else if (edges[elementIndex][i] && i != elementIndex)
				D[i] = weight[elementIndex][i];
			else
				D[i] = INFINITE;
		}

		for (int i = 0; i < getSize(); i++) {
			if (i == elementIndex)
				PD[i] = EMPTY;
			else if (edges[elementIndex][i])
				PD[i] = elementIndex;
			else
				PD[i] = EMPTY;
		}
	}

	/**
	 * Initializes specially the D matrix of costs in Dijkstra for shortest path
	 * algorithm, setting to 1 the cost where edges exists so algorithm only takes
	 * into account taking the less number od edges possible to reach destination
	 * 
	 * @param elementIndex
	 */
	private void initsDijkstraForShortestPathLenght(int elementIndex) {
		D = new double[getSize()];
		PD = new int[getSize()];

		// Initialize all nodes to not visited before next execution
		// for a correct working of S set
		resetVisited();

		for (int i = 0; i < getSize(); i++) {
			if (i == elementIndex)
				D[i] = 0;
			else if (edges[elementIndex][i] && i != elementIndex)
				D[i] = 1; // Where edges exists replace all cost by 1 to get shortest path
			else
				D[i] = INFINITE;
		}

		for (int i = 0; i < getSize(); i++) {
			if (i == elementIndex)
				PD[i] = EMPTY;
			else if (edges[elementIndex][i])
				PD[i] = elementIndex;
			else
				PD[i] = EMPTY;
		}
	}

	/**
	 * Condition at every method asking for two elements to perform some operations.
	 * Either if the origin or departure element does not correspond to any node in
	 * the graph, exception is throw.
	 * 
	 * @param origin      T element of origin
	 * @param destination T element of destination
	 */
	private void checkInitialNodes(T origin, T destination) {
		int i = getNode(origin);
		int j = getNode(destination);
		if (i == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Origin node does not exist");
		if (j == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Destination node does not exist");
	}

	/**
	 * Condition checking the given element corresponds to some node in the graph
	 * 
	 * @param element T element
	 */
	private void checkInitialNode(T element) {
		int i = getNode(element);
		if (i == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Element given node does not exist");
	}

	/**
	 * Return our A matrix containing the minimum cost for every node to reach every
	 * other node
	 * 
	 * @return
	 */
	public double[][] getA() {
		return this.A;
	}

	/**
	 * Return our matri P containing the minimum cost path (intermmdiate nodes) for
	 * every node to reach every other node.
	 * 
	 * @return
	 */
	public int[][] getP() {
		return this.P;
	}

	/**
	 * Testing purposes -- Return the list containing the nodes of the graph
	 * 
	 * @return
	 */
	protected ArrayList<GraphNode<T>> getNodes() {
		return nodes;
	}

	/**
	 * Testing purposes -- Return the edges matrix of the graph
	 * 
	 * @return
	 */
	protected boolean[][] getEdges() {
		return edges;
	}

	/**
	 * Testing purposes -- Return the weight matrix of the graph
	 * 
	 * @return
	 */
	protected double[][] getWeight() {
		return weight;
	}
	
	public String prim(T element) {
		List<Integer> nodesToInspectEdges = new ArrayList<Integer>();
		List<T> nodesTraversed = new ArrayList<T>();
		double costPath = 0.0;
		nodesToInspectEdges.add( getNode(element) );
		nodesTraversed.add(element);
		nodes.get( getNode(element) ).setVisited(true);
		while(nodesToInspectEdges.size() < getSize()) {
			int[] edge = getMinimumCostEdge(nodesToInspectEdges); 
			costPath += weight[edge[0]][edge[1]]; 
			nodesTraversed.add( nodes.get(edge[1]).getElement() );
			nodesToInspectEdges.add( edge[1] );
		}
		
		System.out.println(String.format("Cost of the solution: %s", costPath));
		return nodesTraversed.toString();
	}

	private int[] getMinimumCostEdge(List<Integer> nodesToInspectEdges) {
		int[] edge = new int[2];
		double minCost = INFINITE;
		for(Integer i : nodesToInspectEdges) {
			for(int j=0;j<getSize();j++) {
				if(edges[i][j] && weight[i][j] < minCost && !nodes.get(j).isVisited()) {
					minCost = weight[i][j];
					edge[0] = i;
					edge[1] = j;
				}
			}
		}
		nodes.get( edge[1] ).setVisited(true);
		return edge;
	}
}
