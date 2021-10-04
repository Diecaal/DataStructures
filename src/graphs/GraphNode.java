package graphs;

public class GraphNode<T> {

	private T element;
	private boolean visited;
	
	public GraphNode(T element) {
		this.element = element;
	}
	
	public GraphNode(T element, boolean visited) {
		this.element = element;
		this.visited = visited;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void print() {
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		return String.format("GN(N:%s/V:%s)", getElement(), isVisited());
	}
}
