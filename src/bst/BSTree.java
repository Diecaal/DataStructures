package bst;

public class BSTree<T extends Comparable<T>> {

	/**
	 * BSTNode root of the BSTree
	 */
	private BSTNode<T> root;

	/**
	 * Adds an element to the BSTree
	 * @param element
	 */
    public void add(T element) {
    	if(element == null)
			throw new IllegalArgumentException("Element can not be null");
    	if (search(element))
			throw new IllegalArgumentException("Given element already exist in the tree");
    	
        root = add(element, root);
    }
    
    /**
     * Recursive call to add an element to the BSTree
     * @param element {@link T} to be added
     * @param theRoot {@link BSTNode} current subtree root
     * @return
     */
    private BSTNode<T> add(T element, BSTNode<T> theRoot) {
        if (theRoot == null) {
            return new BSTNode<T>(element);
        }

        if (element.compareTo(theRoot.getElement()) == 0) {
            throw new RuntimeException("Given element already present in the tree"); 
        }
        
        else if (element.compareTo(theRoot.getElement()) < 0) {
            theRoot.setLeft(add(element, theRoot.getLeft()));
        }
        
        else if (element.compareTo(theRoot.getElement()) > 0) {
            theRoot.setRight(add(element, theRoot.getRight()));
        }

        /*
		 * Acts as a recursive call peforming updateHeight on every single node
		 * as add is a recursive method
		 */
        theRoot.updateHeight();
        return theRoot;
    }
    	  
    /**
     * Given an element checks if it exists in the BSTree
     * @param element {@link T} to be searched
     * @return
     */
    public boolean search(T element) {
    	if(element == null)
			throw new IllegalArgumentException("Element can not be null");
        return search(root, element);
    }

    /**
     * Recursive call to search for an element in the BSTree
     * @param theRoot {@link BSTNode} current subtree root
     * @param element {@link T} to be searched
     * @return
     */
    private boolean search(BSTNode<T> theRoot, T element) {
        if (theRoot == null) {
            return false;
        }

        if (element.compareTo(theRoot.getElement()) == 0) {
            return true;
        }
        
        else if (element.compareTo(theRoot.getElement()) < 0) {
            return search(theRoot.getLeft(), element);
        } 
        
        else {
            return search(theRoot.getRight(), element);
        }
    }
    
    /**
     * Given a element, it removes it from the BSTree
     * @param element {@link T} to be removed
     * @return {@link BSTNode} node removed
     */
    public void remove(T element) {
    	if(element == null)
			throw new IllegalArgumentException("Element to be removed can not be null");
		if (!search(element))
			throw new IllegalArgumentException("Given element does not exist in the tree");
		root = remove(root, element);
    }
    
    /**
     * Recursive call to remove an element in the BSTree
     * 
     * Once reached element found stage, return statement means what node 
     * with element to be removed [current theRoot] will be replaced by (null || other node)
     * @param theRoot {@link BSTNode} current subtree root
     * @param element {@link T} to be removed
     * @return {@link BSTNode} node removed
     */
    private BSTNode<T> remove(BSTNode<T> theRoot, T element) {
		if(theRoot == null) 
			throw new IllegalArgumentException("Node to be removed not present in the tree");
		if(element == null) 
			throw new IllegalArgumentException("Element to be removed can not be null");
		/* 
		 * Recursive calls iterating throught the BSTree 
		 * until node with given element is found
		 */
		if(element.compareTo(theRoot.getElement()) < 0) {
			theRoot.setLeft( remove(theRoot.getLeft(), element) );
		} else if(element.compareTo(theRoot.getElement()) > 0) {
			theRoot.setRight( remove(theRoot.getRight(), element) );
		}
		/* Element to be removed found */
		else {
			/* Root has no children nodes */
			if(theRoot.getRight() == null && theRoot.getLeft() == null) {
				return null;
			}
			/* Root has only left child node */
			else if(theRoot.getRight() == null) {
				return theRoot.getLeft(); // Replace theRoot by only left children
			}
			/* Root has only right child node */
			else if(theRoot.getLeft() == null) {
				return theRoot.getRight(); // Replace theRoot by only right children
			}
			/* Root has two child nodes */
			else {
				// Assign to root max element node of left subtree
				theRoot.setElement( getMax(theRoot.getLeft() ));
				// Later remove node with element we just assigned to root 
				theRoot.setLeft( remove(theRoot.getLeft(), theRoot.getElement()) );
			}
		}
		
		/*
		 * Acts as a recursive call peforming updateHeight on every single node
		 * as remove is a recursive method
		 */
		theRoot.updateHeight();
		return theRoot;
	}

	/**
     * Iterative version to find node with greatest element for 
     * a given subtree root node
     * @param theRoot {@link BSTNode} current subtree root
     * @return {@link T} maximum element from subtree
     */
	private T getMax(BSTNode<T> theRoot) {
		while(theRoot.getRight() != null) {
			theRoot = theRoot.getRight();
		}
		return theRoot.getElement();
	}
	
	/**
	 * Recursive version to find node with greatest element for a 
	 * given subtree root node
	 * @param theRoot {@link BSTNode} current subtree root
	 * @return {@link T} maximum element from subtree
	 */
	private T getMaxRec(BSTNode<T> theRoot) {
		if(theRoot.getRight() != null) {
			return getMaxRec(theRoot.getRight());
		} else {
			return theRoot.getElement();
		}
	}
	
	/**
	 * RECURSIVE method to obtain the height of a tree without using nodes height
	 * attribute
	 * 
	 * @return
	 */
	public int getHeight() {
		return getHeightRec(root);
	}

	/**
	 * Recursive call of method to obtain the height of the sub-tree whose root is
	 * theRoot
	 * 
	 * @param theRoot
	 * @return
	 */
	private int getHeightRec(BSTNode<T> theRoot) {
		if (theRoot == null) {
			return 0;
		}
		int left = getHeightRec(theRoot.getLeft()) + 1;
		int right = getHeightRec(theRoot.getRight()) + 1;

		return left > right ? left : right;
	}
	
	public void setRoot(BSTNode<T> root) {
        this.root = root;
    }
    
    public BSTNode<T> getRoot() {
        return this.root;
    }
    
    @Override
    public String toString() {
        String aux = "";
        aux += toString(root);
        return aux;
    }
    
    /**
     * Returns the representation of the BSTree in a String
     * with the Pre-Order notation
     * @param theRoot
     * @return
     */
    private String toString(BSTNode<T> theRoot) {
        String aux = "";

        if (theRoot == null) {
            return "-";
        }

        aux += theRoot.toString();

        aux += toString(theRoot.getLeft());

        aux += toString(theRoot.getRight());

        return aux;
    }
}
