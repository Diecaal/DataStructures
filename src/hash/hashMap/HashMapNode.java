package hash.hashMap;

public class HashMapNode<K, V> {

	private static final int EMPTY = 0;
	private static final int VALID = 1;
	private static final int DELETED = 2;
	
	private K key;
	private V value;
	private int status;
	
	public HashMapNode() {
		this.status = EMPTY;
	}
	
	public HashMapNode(K key, V value) {
		this.key = key;
		this.value = value;
		this.status = VALID;
	}
	
	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
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
