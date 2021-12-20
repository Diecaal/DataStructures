package avl;

import java.util.ArrayList;

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
		if (element == null)
			throw new IllegalArgumentException("Element to be added can not be null");
		if (search(element))
			throw new IllegalArgumentException("Given element already exist in the tree");

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
		 * Acts as a recursive call performing updateHeight on every single node as add
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
		if (element == null)
			throw new IllegalArgumentException("Element to be searched can not be null");
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
		if (element == null)
			throw new IllegalArgumentException("Element to be removed can not be null");
		if (!search(element))
			throw new IllegalArgumentException("Given element does not exist in the tree");

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
		 * Recursive calls iterating through the BSTree until node with given element is
		 * found
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
		 * Acts as a recursive call performing updateHeight on every single node as
		 * remove is a recursive method
		 */
		return updateBF(theRoot);
	}

	/**
	 * Method in charge of applying the rotation needed depending on the Balance
	 * Factor of the root of a subtree. By default, when a node has a BF of |2| and
	 * its children has a BF of 0, single rotations are performed.
	 * 
	 * @param theRoot
	 * @return
	 */
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

	/**
	 * Performs a double right rotation over the subtree whose root is theRoot.
	 * 
	 *  		a (2)                 c 
	 * 			 \                   / \
	 *			  b (-1)    ->      a   b
	 * 			 /
	 * 			c (0)
	 * 
	  * @param theRoot expressed as (a) node
	 * @return {@link AVLNode} new root of rotated subtree
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T> theRoot) {
		AVLNode<T> a = theRoot;
		AVLNode<T> c = a.getRight().getLeft();
		AVLNode<T> b = a.getRight();

		b.setLeft(c.getRight());
		a.setRight(c.getLeft());
		c.setLeft(a);
		c.setRight(b);

		// We need to update the height of the nodes whose height has changed
		b.updateHeight();
		a.updateHeight();
		c.updateHeight();
		
		return c;
	}

	/**
	 * Performs a double left rotation over the subtree whose root is theRoot.
	 * 
	 *  		a (-2)                      
	 * 		   /                     c
	 *		  b (1)       ->        / \
	 * 		   \                   b   a
	 * 			c (0)
	 * 
	  * @param theRoot expressed as (a) node
	 * @return {@link AVLNode} new root of rotated subtree
	 */
	private AVLNode<T> doubleLeftRotation(AVLNode<T> theRoot) {
		AVLNode<T> a = theRoot;
		AVLNode<T> c = a.getLeft().getRight();
		AVLNode<T> b = a.getLeft();

		b.setRight(c.getLeft());
		a.setLeft(c.getRight());
		c.setLeft(b);
		c.setRight(a);

		// We need to update the height of the nodes whose height has changed
		b.updateHeight();
		a.updateHeight();
		c.updateHeight();
		return c;
	}

	/**
	 * Performs a single right rotation over the subtree whose root is theRoot.
	 * 
	 *  		a (2)                   b 
	 * 			 \                     / \
	 *			  b (1|0)    ->       a   c
	 * 			   \
	 * 				c (0)
	 * 
	 * @param theRoot expressed as (a) node
	 * @return {@link AVLNode} new root of rotated subtree
	 */
	private AVLNode<T> singleRightRotation(AVLNode<T> theRoot) {
		AVLNode<T> a = theRoot;
		AVLNode<T> b = a.getRight();

		a.setRight(b.getLeft());
		b.setLeft(a);

		// We need to update the height of the nodes whose height has changed
		a.updateHeight();
		b.updateHeight();
		
		return b;
	}

	/**
	 * Performs a single left rotation over the subtree whose root is theRoot.
	 * 
	 *  		a (-2)              b 
	 * 		   /                   / \
	 *		  b (-1|0)    ->      c   a
	 * 	     /
	 * 	    c (0)
	 * 
	 * @param theRoot expressed as (a) node
	 * @return {@link AVLNode} new root of rotated subtree
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T> theRoot) {
		AVLNode<T> a = theRoot;
		AVLNode<T> b = theRoot.getLeft();

		a.setLeft(b.getRight());
		b.setRight(a);

		// We need to update the height of the nodes whose height has changed
		a.updateHeight();
		b.updateHeight();
		return b;
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
	private int getHeightRec(AVLNode<T> theRoot) {
		if (theRoot == null) {
			return 0;
		}
		int left = getHeightRec(theRoot.getLeft()) + 1;
		int right = getHeightRec(theRoot.getRight()) + 1;

		return (left > right) ? left : right;
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

	public T getBrother(T element) {
		if(element == null)
			throw new IllegalArgumentException("Null elements not valid");
		if(!search(element))
			throw new IllegalArgumentException("Element not existing in the tree");
		// Root node does not have any parent
		if(root.getElement().equals(element)) 
			return null;
				
		AVLNode<T> parent = getParentNode(root, element);
		if(parent.getRight() == null || parent.getLeft() == null) 
			return null;
		if(parent.getRight().getElement().equals(element))
			return parent.getLeft().getElement();
		else
			return parent.getRight().getElement();
	}
	
	public AVLNode<T> getParentNode(T element) {
		if(element == null)
			throw new IllegalArgumentException("Null elements not valid");
		if(!search(element))
			throw new IllegalArgumentException("Element not existing in the tree");
		// Root node does not have any parent
		if(root.getElement().equals(element)) 
			return null;
		
		return getParentNode(root, element);
	}
	
	private AVLNode<T> getParentNode(AVLNode<T> theRoot, T element) {
		AVLNode<T> node = null;
		if(theRoot != null) {
			// Right children is the element we are looking for
			if(theRoot.getRight() != null && theRoot.getRight().getElement().equals(element))
				return theRoot;
			// Left children is the element we are looking for
			if(theRoot.getLeft() != null && theRoot.getLeft().getElement().equals(element))
				return theRoot;
			
			// Otherwise keep recursively searching
			node = getParentNode(theRoot.getLeft(), element);
			
			if(node == null) // Left search failed
				node = getParentNode(theRoot.getRight(), element);			
		}
		
		return node;
	}
	
	/** RECURSIRVE APPROACH TO GET THE BALANCE FACTOR MEAN IN THE TREE **/

	/**
	 * Method calling two recursive methods. First one will get the sum of all BFs
	 * in the tree and second one will get the number of nodes in the tree
	 * 
	 * @return Balance Factor average in the tree
	 */
	public double getBFMean() {
		if (root == null)
			return 0;

		return getBFMeanRec(root) / getNumberOfNodes();
	}

	private double getBFMeanRec(AVLNode<T> theRoot) {
		if (theRoot == null)
			return 0;

		double currentBFMean = theRoot.getBF();
		currentBFMean += getBFMeanRec(theRoot.getLeft());
		currentBFMean += getBFMeanRec(theRoot.getRight());

		return currentBFMean;
	}

	/**
	 * Method calling a recursive method to retrieve the number of nodes in the tree
	 * 
	 * @return number of nodes in the tree
	 */
	public int getNumberOfNodes() {
		return getNumberOfNodesRec(root);
	}

	private int getNumberOfNodesRec(AVLNode<T> theRoot) {
		if (theRoot == null)
			return 0;

		// Retrieve the number of nodes from left sub tree
		int numberOfNodes = getNumberOfNodesRec(theRoot.getLeft());
		// Retrieve the number of nodes from right sub tree
		numberOfNodes += getNumberOfNodesRec(theRoot.getRight());
		// Add current node
		numberOfNodes++;

		return numberOfNodes;
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
	 * Returns the representation of the subtree for a given root. PREORDER notation
	 * is being used
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

	/** UTIL METHODS **/

	/**
	 * Given a root node, return the elements of its subtree with PREORDER notation
	 * 
	 * @param theRoot
	 * @param arr
	 */
	void treeToArrayPreOrder(AVLNode<T> theRoot, ArrayList<AVLNode<T>> arr) {
		if (theRoot != null) {
			arr.add(theRoot);
			treeToArrayPreOrder(theRoot.getLeft(), arr);
			treeToArrayPreOrder(theRoot.getRight(), arr);	
		}
	}

	/**
	 * Given a root node, return the elements of its subtree with INORDER notation
	 * 
	 * @param theRoot
	 * @param arr
	 */
	void treeToArrayInOrder(AVLNode<T> theRoot, ArrayList<AVLNode<T>> arr) {
		if (theRoot != null) {
			treeToArrayInOrder(theRoot.getLeft(), arr);
			arr.add(theRoot);
			treeToArrayInOrder(theRoot.getRight(), arr);	
		}
	}
}
