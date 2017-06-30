package datastructures.edges;

/**
 * An object that represents a weighted edge, which 
 * allows comparing between other weighted edge basing on
 * their length
 * 
 * @author An Nguyen
 *
 */
public class WeightedEdge implements Comparable<WeightedEdge> {
	protected int v, w;
	protected double weight;
	
	/**
	 * Create a new weighted edge 
	 * @param v the starting vertex
	 * @param w the target vertex
	 * @param weight the weight, or length of that edge
	 */
	public WeightedEdge(int v, int w, double weight) {
		this.v = v; this.w = w;
		this.weight = weight;
	}
	
	/**
	 * Compare the two edges by their length
	 * @return -1 if this edge is shorter, 1 if not
	 * @throws NullPointerException if try to compare with null
	 */
	@Override
	public int compareTo(WeightedEdge that) {
		if (that == null)
			throw new NullPointerException("Cannot compare an object to null");
		double cmp = this.weight - that.weight;
		return cmp < 0 ? -1: 1;
	}
	
	/**
	 * Compute the initial vertex, v
	 * @return v, the starting vertex
	 */
	public int from() {
		return v;
	}
	
	/**
	 * Get the other end of the edge
	 * @param i one end of the edge
	 * @return the other vertex
	 * @throws IllegalArgumentException if i is not one of the vertices connected
	 * 		by the edge
	 */
	public int other(int i) {
		if (i == v) return w;
		if (i == w) return v;
		throw new IllegalArgumentException(i + " is not one of the edges in edge " + v + 
				" -> " + w + " of weight " + weight);
	}
	
	/**
	 * Compute the sink vertex, w
	 * @return w, the target vertex
	 */
	public int to() {
		return w;
	}
	
	/**
	 * Inspect the weight of the edge 
	 * @return length of the edge
	 */
	public double weight() {
		return weight;
	}
}
