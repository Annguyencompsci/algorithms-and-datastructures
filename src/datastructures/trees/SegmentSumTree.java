package datastructures.trees;

/**
 * Segment tree algorithm used for fast computation
 * of sum of a range
 * @author An Nguyen
 *
 */
public class SegmentSumTree {
	private int N; // size of the list of elements
	private int[] elements; // individual elements in the segment tree
	private int[] nodes; // nodes representing different ranges in the
						// segment tree, with root at index 1 (range 0 to N)
	
	
	/**
	 * Construct the segment trees given its elements
	 * @param elements the elements of the segment tree
	 */
	public SegmentSumTree(int[] elements) {
		this.elements = elements;
		N = elements.length;
		nodes = new int[2 * N - 1];
		constructTree(1, 0, N - 1);
	}
	
	/**
	 * Construct the segment tree by their range.
	 * The left segment represents the range a -> (a + b) / 2.
	 * The right segment represents the range (a + b) / 2 + 1 -> b
	 * @param node the index of the node
	 * @param a the start of the range
	 * @param b the end of the range
	 * @return the value of that segment
	 */
	private int constructTree(int node, int a, int b) {
		// If the node is a leaf node
		if (a == b) 
			nodes[node] = elements[a];
		else 
			nodes[node] = constructTree(2 * node, a, (a + b) / 2) +
					constructTree(2 * node + 1, (a + b) / 2 + 1, b);
		return nodes[node];
	}
	
	/**
	 * Compute the sum of a range of values from a to b
	 * inclusive
	 * @param a the initial index
	 * @param b the ending index
	 * @return the sum of the elements of index a to b
	 * @throws IllegalArgumentException if a is greater than b
	 */
	public int sumRange(int a, int b) {
		if (a > b)
			throw new IllegalArgumentException("Invalid range: " + a + " is larger than " + b);
		return sum(1, 0, N - 1, a, b);
	}
	
	/**
	 * Compute the sum of a range of values from a to b inside
	 * of range i to j
	 * @param node the node of the tree
	 * @param a the inner range's start
	 * @param b the inner range's end
	 * @param i the outer range's start
	 * @param j the outer range's end
	 * @return the sum of the elements inside a -> b U i -> j
	 */
	private int sum(int node, int a, int b, int i, int j) {
		if (a > b || a > j || b < i)
			return 0;
		if (a >= i && b <= j)
			return nodes[node];
		return sum(node, a, (a + b) / 2, i, j) + sum(node, (a + b) / 2 + 1, b, i, j);
	}
	
	/**
	 * Change the value of one of the variables inside
	 * of the segment tree
	 * @param node the current node
	 * @param a the start of the current node
	 * @param b the end of the current node
	 * @param i the index to update
	 * @param value the value to update to
	 */
	public int updateValue(int node, int a, int b, int i, int value) {
		if (i < a || i > b)
			return 0;
		if (a == b && b == i)
			nodes[node] = value;
		else
			nodes[node] = updateValue(node, a, (a + b) / 2, i, value) + 
				updateValue(node, (a + b) / 2 + 1, b, i, value);
		return nodes[node];
	}
}
