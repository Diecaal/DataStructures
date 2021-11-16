package avl;

public class AVLNode<T extends Comparable<T>> {

	private T element;
	private AVLNode<T> left;
	private AVLNode<T> right;
	private int height;

	public AVLNode(T element, AVLNode<T> left, AVLNode<T> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}

	public AVLNode(T element) {
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
		this.element = element;
	}

	/**
	 * Returns the left children of the node
	 * @return
	 */
	public AVLNode<T> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	/**
	 * Returns the right children of the node
	 * @return
	 */
	public AVLNode<T> getRight() {
		return right;
	}

	public void setRight(AVLNode<T> right) {
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
	
	/**
	 * Returns the current balance factor of the node given by:
	 * 		 BF = rightChildren.height - leftChildren.height
	 * @return
	 */
	public int getBF() {
		if(isLeafNode()) {
			return 0;
		} 
		else if(getRight() == null) {
			return (getLeft().getHeight() * -1) - 1;
		}
		else if(getLeft() == null) {
			return (getRight().getHeight()) + 1;
		} else {
			return getRight().getHeight() - getLeft().getHeight();
		}
		
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", element.toString(), getBF());
	}
}
