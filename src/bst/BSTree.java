package bst;

public class BSTree<T extends Comparable<T>> {

	private BSTNode<T> root;

	/**
	 * Adds an element to the BSTree
	 * @param element
	 */
    public void add(T element) {
        root = add(element, root);
    }
    
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

        return theRoot;
    }
    	  
    /**
     * Given an element checks if it exists in the BSTree
     * @param element
     * @return
     */
    public boolean search(T element) {
        return search(root, element);
    }

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
    
    public BSTNode<T> remove(T element) {
    	return remove(root, element);
    }
    
    private BSTNode<T> remove(BSTNode<T> theRoot, T element) {
		if(theRoot == null) {
			throw new IllegalArgumentException("Element does not exist");
		}
		
		if(element.compareTo(theRoot.getElement()) < 0) {
			theRoot.setLeft( remove(theRoot.getLeft(), element) );
		}
		
		else if(element.compareTo(theRoot.getElement()) > 0) {
			theRoot.setRight( remove(theRoot.getRight(), element) );
		}
		// Element to be removed found
		else {
			/* Root has no children nodes */
			if(theRoot.getRight() == null && theRoot.getLeft() == null) {
				return null;
			}
			/* Root has only left child node */
			else if(theRoot.getRight() == null) {
				return theRoot.getLeft();
			}
			/* Root has only right child node */
			else if(theRoot.getLeft() == null) {
				return theRoot.getRight();
			}
			/* Root has two child nodes */
			else {
				// Assign to root max element node of left subtree
				theRoot.setElement( getMax(theRoot.getLeft() ));
				// Later remove that max element node of left subtree found
				theRoot.setLeft( remove(theRoot.getLeft(), theRoot.getElement()) );
			}
		}
		
		return theRoot;
	}

    /**
     * Iterative version to find the node with greatest node for a given
     * subtree root node
     * @param theRoot
     * @return
     */
	private T getMax(BSTNode<T> theRoot) {
		while(theRoot.getRight() != null) {
			theRoot = theRoot.getRight();
		}
		return theRoot.getElement();
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
