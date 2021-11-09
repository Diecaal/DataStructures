package bst;

public class BSTNode<T extends Comparable<T>> {
	
	private T element;
    private BSTNode<T> left;
    private BSTNode<T> right;
    private int height;
    
    public BSTNode(T element, BSTNode<T> left, BSTNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
    
    public BSTNode(T element) {
        this.element = element;
    }
    
    public T getElement() {
        return element;
    }
    
    public void setElement(T element) {
    	this.element = element;
    }
    
    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

    public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void updateHeight() {
		if(isLeafNode())
			this.height = 0;
		else if(getRight() == null)
			this.height = getLeft().getHeight() + 1;
		else if(getLeft() == null)
			this.height = getRight().getHeight() + 1;
		else
			this.height = getMaxHeight() + 1;
	}
	
	private int getMaxHeight() {
		return getLeft().getHeight() >= getRight().getHeight()
			? getLeft().getHeight() 
			: getRight().getHeight(); 
	}
	
	private boolean isLeafNode() {
		if(getLeft() == null && getRight() == null)
			return true;
		return false;
	}
	
	@Override
    public String toString() {
        return String.format("%s(%s)", element.toString(),  height);
    }    
}
