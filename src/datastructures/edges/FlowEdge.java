package datastructures.edges;

/**
 * An edge object that represents an edge in
 * a flow graph, with a certain flow and capacity
 * 
 * @author An Nguyen
 *
 */
public class FlowEdge {
	private int v, w;
	private double flow;
	private double capacity;

	/**
	 * Create a flow edge in a flow network
	 * @param v the source vertex
	 * @param w the sink vertex
	 * @param cap the capacity of the edge
	 */
	public FlowEdge(int v, int w, double cap) {
		this.v = v;
		this.w = w;
		this.capacity = cap;
	}

	/**
	 * Add flow into the edge to a certain vertex,
	 * reducing the residual capacity to that vertex
	 * @param value the value of flow the increase
	 * @param v the vertex to add flow into
	 * @throws IllegalArgumentException if v is not one of the vertices connected
	 * 		by the edge
	 */
	public void addFlow(double value, int v) {
		if (v == this.v)
			flow -= value;
		else if (v == this.w)
			flow += value;
		throw new IllegalArgumentException(v + " is not one of the edges in edge " + this.v + 
				" -> " + this.w);
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
				" -> " + w);
	}

	/**
	 * Compute the residual capacity of this edge
	 * to a vertex
	 * @param i the vertex to inspect
	 * @return the residual flow into that edge
	 * @throws IllegalArgumentException if i is not one of the vertices connected
	 * 		by the edge
	 */
	public double residualCapacityTo(int i) {
		if (i == v)
			return flow;
		if (i == w)
			return capacity - flow;
		throw new IllegalArgumentException(i + " is not one of the edges in edge " + v + 
				" -> " + w);
	}
	
	/**
	 * Compute the sink vertex, w
	 * @return w, the target vertex
	 */
	public int to() {
		return w;
	}
}
