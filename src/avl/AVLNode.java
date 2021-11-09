package avl;

public class AVLNode<T extends Comparable<T>> {

	private T element;
	private AVLNode<T> left;
	private AVLNode<T> right;
	private int BF;

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public AVLNode<T> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	public AVLNode<T> getRight() {
		return right;
	}

	public void setRight(AVLNode<T> right) {
		this.right = right;
	}

	public int getBF() {
		return BF;
	}

	public void setBF(int bF) {
		BF = bF;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", getElement(), getBF());
	}

}
