package datastructures.trees;

/**
 * A scheme for building a generic binary
 * tree
 * 
 * @author An Nguyen
 */
public abstract class BinaryTree<T> {
	
	private Node root; // the root of the tree
	private Node[] nodes;
	
	/**
	 * A Node used in Binary Trees that
	 * represent a vertex in the tree
	 * @author An Nguyen
	 *
	 */
	public class Node {
		private Node parent; // the parent of this node
							// the parent of root is null
		private Node left; // the left leaf of this node
		private Node right; // the right leaf of this node
		private T obj; // the object that represents this node
		
		/**
		 * Return the parent node of this node
		 * @return the parent node of this node
		 */
		public Node parent() {
			return parent;
		}
		
		/**
		 * Determine the object that this node
		 * refers to
		 * @return the object in reference
		 */
		public T obj() {
			return obj;
		}
	}
	
	/**
	 * Determine if an object is inside the balanced tree
	 * @param obj the object to search for in the tree
	 * @return whether the object is in the tree
	 */
	public abstract boolean contains(T obj);
	
	/**
	 * Get the node of the tree at position i
	 * @param i the position of the node
	 * @return the node at i
	 */
	public Node node(int i) {
		return nodes[i];
	}
	
	/**
	 * Insert an object to the balanced binary tree
	 * @param obj the object to insert into the tree
	 */
	public abstract void insert(T obj);
	
	/**
	 * Retrieve the root of this tree
	 * @return the root node of the tree
	 */
	protected Node root() {
		return root;
	}
	
	/**
	 * Search for a specific object in the tree
	 * @param obj the object to search for in the tree
	 * @return the node of that object in the tree, 
	 * 		null if one does not exist
	 */
	public abstract T search(T obj);
}
