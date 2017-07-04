package algorithms.treealgo;

import java.util.LinkedList;
import java.util.Queue;

import datastructures.trees.BinaryTree;

/**
 * Find lowest common divisor in a Binary
 * Search Tree in O(h) where h is the height of the
 * tree by traversing the search tree from the
 * leaf up to the root and comparing the travel
 * path
 * 
 * @author An Nguyen
 *
 */
public class LowestCommonAncestor<T> {
	
	@SuppressWarnings("rawtypes")
	private BinaryTree<T> tree; // the binary tree to search
	
	/**
	 * Initiate an object that can compute different lca
	 * of a specific binary tree in O(h) if the index
	 * is given
	 * @param tree the tree to search in
	 */
	public LowestCommonAncestor(BinaryTree<T> tree) {
		this.tree = tree;
	}
	
	/**
	 * Compute the lowest common ancestor of two 
	 * nodes in the tree
	 * @param v the index of the first node
	 * @param w the index of the second node
	 * @return the lowest common ancestor of the two
	 */
	public T LCA(int v, int w) {
		Queue<BinaryTree<T>.Node> pq = new LinkedList<BinaryTree<T>.Node>();
		Queue<BinaryTree<T>.Node> qp = new LinkedList<BinaryTree<T>.Node>();
		pq.add(tree.node(v));
		qp.add(tree.node(w));
		while (pq.peek().parent() != null) 
			pq.add(pq.peek().parent());
		while (qp.peek().parent() != null)
			qp.add(qp.peek().parent());
		BinaryTree<T>.Node last = pq.poll(); qp.poll();
		while (pq.peek() == qp.peek()) 
			last = pq.poll(); qp.poll();
		return last.obj();
	}
}
