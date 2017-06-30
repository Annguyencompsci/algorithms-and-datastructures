package algorithms.minspanningtree;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import datastructures.edges.WeightedEdge;
import datastructures.trees.IndexMinPQ;

/**
 * Prim's algorithm used for computing the MST
 * by adding minimum weight edges in the neighborhood
 * of the current MST
 * 
 * @author anvilnguyen
 *
 */
public class PrimOptimized {

	private Set<WeightedEdge> mst; // Set that stores the edges in the min spanning tree
	private double value;			// Value, or length, of the mst
	
	/**
	 * Create a Prim's Object to compute the minimum spanning tree
	 * in |E|log|V| time
	 * @param N the size of the graph
	 * @param adj the adjacency list representing the graph
	 */
	public PrimOptimized(int N, List<WeightedEdge>[] adj) {
		mst = new TreeSet<WeightedEdge>();
		IndexMinPQ<WeightedEdge> pq = new IndexMinPQ<WeightedEdge>(N);
		boolean[] visited = new boolean[N];
		explore(adj, 0, visited, pq);
		
		while (!pq.isEmpty() && mst.size() < N - 1) {
			WeightedEdge e = pq.dequeue();
			int v = e.from(); int w = e.to();
			if (visited[v] && visited[w]) continue;
			mst.add(e);
			value += e.weight();
			if (!visited[v]) explore(adj, v, visited, pq);
			if (!visited[w]) explore(adj, w, visited, pq);
		}
	}
	
	/**
	 * Explore a vertex and update its neighborhooad
	 * @param adj the adjacency list representing the graph
	 * @param v the vertex to explore
	 * @param visited an array storing which vertices are visited
	 * @param pq the priority queue storing the neighborhood
	 */
	private void explore(List<WeightedEdge>[] adj, int v, boolean[] visited, IndexMinPQ<WeightedEdge> pq) {
		visited[v] = true;
		for (WeightedEdge e : adj[v]) {
			int w = e.other(v);
			if (visited[w]) continue;
			if (!pq.insert(w, e))
				pq.decreaseKey(w, e);
		}
	}
	
	/**
	 * Get an iterable object that can iterate
	 * through the edges in the mst
	 * @return the iterable object
	 */
	public Iterable<WeightedEdge> getMST() {
		return mst;
	}
	
	/**
	 * Compute the length of the MST
	 * @return the length of the MST
	 */
	public double value() {
		return value;
	}
}
