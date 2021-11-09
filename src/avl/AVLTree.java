package avl;

public class AVLTree<T extends Comparable<T>> {

	private AVLNode<T> root;

	public AVLNode<T> getRoot() {
		return root;
	}

	public void setRoot(AVLNode<T> root) {
		this.root = root;
	}

	@Override
	public String toString() {
		String aux = "";
        aux += toString(root);
        return aux;
	}
	
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
