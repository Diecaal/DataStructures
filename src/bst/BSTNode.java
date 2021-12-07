package bst;

public class BSTNode<T extends Comparable<T>> {

	private T element;
	private BSTNode<T> left;
	private BSTNode<T> right;
	private int height;

	public BSTNode(T element, BSTNode<T> left, BSTNode<T> right) {
		if(element == null)
			throw new IllegalArgumentException("Element can not be null");
		this.element = element;
		this.left = left;
		this.right = right;
	}

	public BSTNode(T element) {
		if(element == null)
			throw new IllegalArgumentException("Element can not be null");
		this.element = element;
	}

	/**
	 * Retur the current element contained by the node
	 * @return
	 */
	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		if(element == null)
			throw new IllegalArgumentException("Element can not be null");
		this.element = element;
	}

	/**
	 * Returns the left children of the node
	 * @return
	 */
	public BSTNode<T> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	/**
	 * Returns the right children of the node
	 * @return
	 */
	public BSTNode<T> getRight() {
		return right;
	}

	public void setRight(BSTNode<T> right) {
		this.right = right;
	}

	/**
	 * Returns the current height of the node
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Updates the height of the current node
	 */
	public void updateHeight() {
		if (isLeafNode())
			this.height = 0;
		else if (getRight() == null)
			this.height = getLeft().getHeight() + 1;
		else if (getLeft() == null)
			this.height = getRight().getHeight() + 1;
		else
			this.height = getMaxHeight() + 1;
	}

	/**
	 * Local method used when the node has two children to get the highest height
	 * among both children
	 * @return int maximum height among both children nodes
	 */
	private int getMaxHeight() {
		return getLeft().getHeight() >= getRight().getHeight() ? getLeft().getHeight() : getRight().getHeight();
	}

	/**
	 * Checks if this node is a leaf one, meaning it has no children
	 * @return True if node is a lead. False otherwise.
	 */
	private boolean isLeafNode() {
		if (getLeft() == null && getRight() == null)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", element.toString(), height);
	}
}
