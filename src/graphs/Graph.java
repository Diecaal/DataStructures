package graphs;

import java.util.ArrayList;

public class Graph<T> {
	public static final int INDEX_NOT_FOUND = -1;
	ArrayList<GraphNode<T>> nodes;
	protected boolean[][] edges;
	protected double[][] weight;
	
	/**
	 * Constructor to initialize the Graph, storing memory for its
	 * node list, edges and weight matrix
	 * @param capacity
	 */
	public Graph(int capacity) {
		if(capacity < 0) {
			throw new IllegalArgumentException("Capacity must be equal/greater zero");
		}
		nodes = new ArrayList<GraphNode<T>>(capacity);
		edges = new boolean[capacity][capacity];
		weight = new double[capacity][capacity];
	}
	
	public int getNode(T element) {
		int elementIndex = INDEX_NOT_FOUND; //By default, element not found
		for(int i=0; i<nodes.size(); i++) {
			if(nodes.get(i).getElement().equals(element)) {
				elementIndex = i;
			}
		}
		return elementIndex;
	}
	
	public void addNode(T element) {
		if(getNode(element) != INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Element already existing in the graph");
		nodes.add(new GraphNode<T>(element));
		for(int i=0; i<=getSize()-1; i++) {
			edges[nodes.size()-1][i] = false;
			edges[i][nodes.size()-1] = false;
			weight[nodes.size()-1][i] = 0.0;
			weight[i][nodes.size()-1] = 0.0;
		}
	}
	
	public int getSize() {
		return nodes.size();
	}
	
	public boolean existsEdge(T origin, T destination) throws Exception {
		int i = getNode(origin);
		int j = getNode(destination);	
		if(i == INDEX_NOT_FOUND) 
			throw new IllegalArgumentException("Origin node does not exist");
		if(j == INDEX_NOT_FOUND) 
			throw new IllegalArgumentException("Destination node does not exist");
	
		return edges[i][j];
	}
	
	public void addEdge(T origin, T destination, double weight) throws Exception {
		int i = getNode(origin);
		int j = getNode(destination);
		if(i == INDEX_NOT_FOUND) 
			throw new IllegalArgumentException("Origin node does not exist");
		if(j == INDEX_NOT_FOUND) 
			throw new IllegalArgumentException("Destination node does not exist");
		
		this.edges[i][j] = true;
		this.weight[i][j] = weight;
	}
	
	public void removeNode(T element) {
		int i = getNode(element);
		if(i == INDEX_NOT_FOUND) 
			throw new IllegalArgumentException("Element asked to remove does not exist");
		
		if(i != getSize()-1) { // Not the last node
			nodes.set(i, nodes.get(getSize()-1)); //Element to be removed will be replaced by the last node
			
			//replace elements in edges and weights
			for(int j=0; j<getSize()-1; j++) {
				edges[j][i] = edges[j][getSize()-1];
				edges[i][j] = edges[getSize()-1][j];
				weight[i][j] = weight[getSize()-1][j];
				weight[j][i] = weight[j][getSize()-1];
			} // loop -> diagonal
			edges[i][i] = edges[getSize()-1][getSize()-1];
			weight[i][i] = weight[getSize()-1][getSize()-1];
		}
	}
	
	public void print() {
		for(int k=0; k<getSize()-1; k++) {
			nodes.get(k).print();
		}
		
		for (int i = 0; i <= getSize()-1; i++) {
			for (int j = 0; j <= getSize()-1; j++) {
				System.out.print(edges[i][j] + "(");
				System.out.print(weight[i][j] + ") ");
			}
			System.out.println();
		}
	}
	
	public void removeEdge(T origin, T destination) throws Exception {
		int i = getNode(origin);
		int j = getNode(destination);
		if(i == INDEX_NOT_FOUND) 
			throw new IllegalArgumentException("Origin node does not exist");
		if(j == INDEX_NOT_FOUND) 
			throw new IllegalArgumentException("Destination node does not exist");
		
		edges[i][j] = false;
		weight[i][j] = 0.0;
	}
	
	public boolean isDrainNode(T element) {
		int i = getNode(element);
		if(i == INDEX_NOT_FOUND) 
			throw new IllegalArgumentException("Node does not exist");
		
		boolean isDrain = false;
		for(int j=0;i<=getSize()-1;j++) {
			if(edges[i][j] == true) // If node reaches another nodes is not a drain one
				return false;
			if(edges[j][i] == true)
				isDrain = true;
		}
		return isDrain;
	}
	
	public boolean isSourceNode(T element) {
		int i = getNode(element);
		if(i == INDEX_NOT_FOUND) 
			throw new IllegalArgumentException("Node does not exist");
		
		boolean isSource = false;
		for(int j=0;i<=getSize()-1;j++) {
			if(edges[i][j] == true) // If node reaches another nodes is not a drain one
				isSource = true;
			if(edges[j][i] == true)
				return false;
		}
		return isSource;
	}
	
	public int countDrainNodes() {
		int count = 0;
		for (int i = 0; i < nodes.size(); i++) {
			if(isDrainNode(nodes.get(i).getElement()))
				count++;
		}
		return count;
	}
	
	public int countSourceNodes() {
		int count = 0;
		for (int i = 0; i < nodes.size(); i++) {
			if(isSourceNode(nodes.get(i).getElement()))
				count++;
		}
		return count;
	}
	
	protected ArrayList<GraphNode<T>> getNodes() {
		return nodes;
	}
	
	protected boolean[][] getEdges() {
		return edges;
	}
	
	protected double[][] getWeight() {
		return weight;
	}

}
