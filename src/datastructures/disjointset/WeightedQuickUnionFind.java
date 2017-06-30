package datastructures.disjointset;

/**
 * Disjoint Set Data Structure that allows for efficient
 * dynamic joining of trees
 * 
 * @author An Nguyen adapted from Robert Sedgewick
 * @version 1.0
 * 
 * TODO : implement a feature that allows you to cut trees
 *
 */
public class WeightedQuickUnionFind {
	private int[] parent; // the array storing the parent of that element
	private int[] size; // the array storing the size of that tree
	
	
	/**
	 * Create a forest of disconnected trees 
	 * that can be joined and inspect their connectivity
	 * @param N the size of the forest
	 */
	public WeightedQuickUnionFind(int N) {
		this.parent = new int[N];
		this.size = new int[N];

		for (int i = 0; i < N; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	/**
	 * Determine whether two elements are connected (belong to the same tree)
	 * by their corresponding roots
	 * @param p the first element
	 * @param q the second element
	 * @return whether the two are connected
	 * @throws IllegalArgumentException if either indices are not valid
	 */
	public boolean connected(int p, int q) {
		if (p >= length() || q >= length() || q < 0 || p < 0)
			throw new IllegalArgumentException("The value " + p + " or " + q + 
					" is not in the range [0, " + length() + "]");
		return root(p) == root(q);
	}

	/**
	 * Combine two trees (the larger tree becomes the parent)
	 * @param p the index of one of the elements of the first tree
	 * @param q the index of one of the elements of the second tree
	 */
	public void union(int p, int q) {
		
		if (p >= length() || q >= length() || q < 0 || p < 0)
			throw new IllegalArgumentException("The value " + p + " or " + q + 
					" is not in the range [0, " + length() + "]");
	
		// Find the roots of p and q
		int i = root(p);
		int j = root(q);

		// Make the bigger tree the root of the smaller tree to minimize
		// 		the tree's depth
		if (i != j) {
			if (size[i] < size[j]) {
				parent[i] = j;
				// Change the size of the tree accordingly
				size[j] += size[i];
			} else {
				parent[j] = i;
				// Change the size of the tree accordingly
				size[i] += size[j];
			}
		}
	}
	
	/**
	 * Find the root of a tree by traveling up its parent tree.
	 * This also implemented path compression to make root operations 
	 * shorter each time you run
	 * @param i the index of the element to inspect
	 * @return the root index of that element
	 */
	private int root(int i) {
		if (i >= length() || i < 0)
			throw new IllegalArgumentException("The value " + i + 
					" is not in the range [0, " + length() + "]");
		// Run through the id until a root is found
		while (i != parent[i]) {
			// Flatten the tree by making an object link to it's grandparent
			parent[i] = parent[parent[i]];
			i = parent[i];
		}
		return i;
	}
	
	/**
	 * Determine the size of the tree
	 * @return size of the tree
	 */
	public int length() {
		return parent.length;
	}
}
