package hash.hashTable;

public class HashNode<T> {

	private static final int EMPTY = 0;
	private static final int VALID = 1;
	private static final int DELETED = 2;
	
	private T element;
	private int status;
	
	public HashNode() {
		this.status = EMPTY;
	}
	
	public HashNode(T element) {
		this.element = element;
		this.status = VALID;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public boolean isValid() {
		return this.status == VALID;
	}
	
	public void setDeleted() {
		this.status = DELETED;
	}

	public boolean isEmpty() {
		return this.status == EMPTY;
	}
}
