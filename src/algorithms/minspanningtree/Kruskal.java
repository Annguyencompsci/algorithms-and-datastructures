package algorithms.minspanningtree;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

import datastructures.disjointset.WeightedQuickUnionFind;
import datastructures.edges.WeightedEdge;

/**
 * Kruskal's Greedy Algorithm to find the minimum
 * spanning tree that runs in |E|log|V|. The algorithm attempts
 * to add edges into the Min Spanning Tree so as to not
 * violate the tree constraint
 * 
 * @author An Nguyen
 *
 */
public class Kruskal {
	private Set<WeightedEdge> mst; // Set that stores the edges in the min spanning tree
	private double value;			// Value, or length, of the mst
	
	/**
	 * Create a Kruskal's algo object that computes the min spanning
	 * tree of a certain weighted graph
	 * @param N the size of the graph
	 * @param adj the adjacency list of the graph
	 */
	public Kruskal(int N, List<WeightedEdge>[] adj) {
		mst = new TreeSet<WeightedEdge>();
		PriorityQueue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>();
		for (int v = 0; v < N; v++) 
			for (WeightedEdge e : adj[v]) 
				pq.add(e);
		WeightedQuickUnionFind forest = new WeightedQuickUnionFind(N);
		while (!pq.isEmpty()) {
			WeightedEdge e = pq.poll();
			int v = e.from(); int w = e.to();
			if (forest.connected(v, w))
				continue;
			forest.union(v, w);
			mst.add(e);
			value += e.weight();
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
