package avl;

public class AVLTree<T extends Comparable<T>> {

	/**
	 * AVLNode root of the BSTree
	 */
	private AVLNode<T> root;

	/**
	 * Adds an element to the BSTree
	 * 
	 * @param element
	 */
	public void add(T element) {
		root = add(element, root);
	}

	/**
	 * Recursive call to add an element to the BSTree
	 * 
	 * @param element {@link T} to be added
	 * @param theRoot {@link AVLNode} current subtree root
	 * @return
	 */
	private AVLNode<T> add(T element, AVLNode<T> theRoot) {
		if (theRoot == null) {
			return new AVLNode<T>(element);
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
		 * Acts as a recursive call peforming updateHeight on every single node as add
		 * is a recursive method
		 */
		return updateBF(theRoot);
	}

	/**
	 * Given an element checks if it exists in the BSTree
	 * 
	 * @param element {@link T} to be searched
	 * @return
	 */
	public boolean search(T element) {
		return search(root, element);
	}

	/**
	 * Recursive call to search for an element in the BSTree
	 * 
	 * @param theRoot {@link AVLNode} current subtree root
	 * @param element {@link T} to be searched
	 * @return
	 */
	private boolean search(AVLNode<T> theRoot, T element) {
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
	 * 
	 * @param element {@link T} to be removed
	 * @return {@link AVLNode} node removed
	 */
	public void remove(T element) {
		root = remove(root, element);
	}

	/**
	 * Recursive call to remove an element in the BSTree
	 * 
	 * Once reached element found stage, return statement means what node with
	 * element to be removed [current theRoot] will be replaced by (null || other
	 * node)
	 * 
	 * @param theRoot {@link AVLNode} current subtree root
	 * @param element {@link T} to be removed
	 * @return {@link AVLNode} node removed
	 */
	private AVLNode<T> remove(AVLNode<T> theRoot, T element) {
		if (theRoot == null)
			throw new IllegalArgumentException("Node to be removed not present in the tree");
		if (element == null)
			throw new IllegalArgumentException("Element to be removed can not be null");
		/*
		 * Recursive calls iterating through the BSTree until node with given element
		 * is found
		 */
		if (element.compareTo(theRoot.getElement()) < 0) {
			theRoot.setLeft(remove(theRoot.getLeft(), element));
		} else if (element.compareTo(theRoot.getElement()) > 0) {
			theRoot.setRight(remove(theRoot.getRight(), element));
		}
		/* Element to be removed found */
		else {
			/* Root has no children nodes */
			if (theRoot.getRight() == null && theRoot.getLeft() == null) {
				return null;
			}
			/* Root has only left child node */
			else if (theRoot.getRight() == null) {
				return theRoot.getLeft(); // Replace theRoot by only left children
			}
			/* Root has only right child node */
			else if (theRoot.getLeft() == null) {
				return theRoot.getRight(); // Replace theRoot by only right children
			}
			/* Root has two child nodes */
			else {
				// Assign to root max element node of left subtree
				theRoot.setElement(getMax(theRoot.getLeft()));
				// Later remove node with element we just assigned to root
				theRoot.setLeft(remove(theRoot.getLeft(), theRoot.getElement()));
			}
		}

		/*
		 * Acts as a recursive call peforming updateHeight on every single node as
		 * remove is a recursive method
		 */
		return updateBF(theRoot);
	}

	private AVLNode<T> updateBF(AVLNode<T> theRoot) {
		// left rotation needed
		if (theRoot.getBF() == -2) {
			// double left rotation required
			if (theRoot.getLeft().getBF() == 1) {
				theRoot = doubleLeftRotation(theRoot);
			}
			// single left rotation required (with 0 values as well)
			if (theRoot.getLeft().getBF() == -1 || theRoot.getLeft().getBF() == 0) {
				theRoot = singleLeftRotation(theRoot);
			}
		}
		// right rotation required
		if (theRoot.getBF() == 2) {
			// single right rotation required (with 0 values as well)
			if (theRoot.getRight().getBF() == 1 || theRoot.getRight().getBF() == 0) {
				theRoot = singleRightRotation(theRoot);
			}
			// double right rotation required
			if (theRoot.getRight().getBF() == -1) {
				theRoot = doubleRightRotation(theRoot);
			}
		}
		
		theRoot.updateHeight();
		return theRoot;
	}

	private AVLNode<T> doubleRightRotation(AVLNode<T> theRoot) {
		AVLNode<T> newRoot = theRoot.getRight().getLeft();
		AVLNode<T> newRootRightChild = theRoot.getRight(); 
		
		newRootRightChild.setLeft(newRoot.getRight());
		theRoot.setRight(newRoot.getLeft());
		newRoot.setLeft(theRoot);
		newRoot.setRight(newRootRightChild);
		
		newRootRightChild.updateHeight();
		theRoot.updateHeight();
		newRoot.updateHeight();
		return newRoot;
	}

	private AVLNode<T> doubleLeftRotation(AVLNode<T> theRoot) {
		AVLNode<T> newRoot = theRoot.getLeft().getRight();
		AVLNode<T> newRootLeftChild = theRoot.getLeft();
		
		newRootLeftChild.setRight(newRoot.getLeft());
		theRoot.setLeft(newRoot.getRight());
		newRoot.setLeft(newRootLeftChild);
		newRoot.setRight(theRoot);
		
		newRootLeftChild.updateHeight();
		theRoot.updateHeight();
		newRoot.updateHeight();
		return newRoot;
	}
	
	private AVLNode<T> singleRightRotation(AVLNode<T> theRoot) {
		AVLNode<T> newRoot = theRoot.getRight();
		
		theRoot.setRight(newRoot.getLeft());
		newRoot.setLeft(theRoot);
		
		theRoot.updateHeight();
		newRoot.updateHeight();
		return newRoot;
	}

	private AVLNode<T> singleLeftRotation(AVLNode<T> theRoot) {
		AVLNode<T> newRoot = theRoot.getLeft();
		
		theRoot.setLeft(newRoot.getRight());
		newRoot.setRight(theRoot);
		
		theRoot.updateHeight();
		newRoot.updateHeight();
		return newRoot;
	}
	
	/**
	 * RECURSIVE method to obtain the height of a tree without
	 * using nodes height attribute
	 * @return
	 */
	public int getHeight() {
		return getHeightRec(root);
	}

	private int getHeightRec(AVLNode<T> theRoot) {
		if(theRoot == null) {
			return 0;
		}
		return getHeightRec(theRoot) + 1;
	}

	private int getHeight(AVLNode<T> theRoot) {
		if(theRoot == null) {
			return 0;
		}
			
		int a = getHeight(theRoot.getLeft());
		int b = getHeight(theRoot.getRight());
		
		if(a >= b) {
			return getHeight(theRoot.getLeft()) + 1;
		} else {
			return getHeight(theRoot.getRight()) + 1;
		}
	}

	/**
	 * Iterative version to find node with greatest element for a given subtree root
	 * node 
	 *
	 * @param theRoot {@link AVLNode} current subtree root
	 * @return {@link T} maximum element from subtree
	 */
	public T getMax(AVLNode<T> theRoot) {
		while (theRoot.getRight() != null) {
			theRoot = theRoot.getRight();
		}
		return theRoot.getElement();
	}

	/**
	 * Recursive version to find node with greatest element for a given subtree root
	 * node
	 * 
	 * @param theRoot {@link AVLNode} current subtree root
	 * @return {@link T} maximum element from subtree
	 */
	public T getMaxRec(AVLNode<T> theRoot) {
		if (theRoot.getRight() != null) {
			return getMaxRec(theRoot.getRight());
		} else {
			return theRoot.getElement();
		}
	}

	public void setRoot(AVLNode<T> root) {
		this.root = root;
	}

	public AVLNode<T> getRoot() {
		return this.root;
	}

	@Override
	public String toString() {
		String aux = "";
		aux += toString(root);
		return aux;
	}

	/**
	 * Returns the representation of the BSTree in a String with the Pre-Order
	 * notation
	 * 
	 * @param theRoot
	 * @return
	 */
	private String toString(AVLNode<T> theRoot) {
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
