package algorithms.minspanningtree;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import datastructures.trees.IndexMinPQ;

/**
 * 
 * @author anvilnguyen
 *
 */
public class PrimOptimized {

	private Set<WeightedEdge> mst;
	private double value;
	
	public PrimOptimized(int N, List<WeightedEdge>[] adj) {
		mst = new TreeSet<WeightedEdge>();
		IndexMinPQ<WeightedEdge> pq = new IndexMinPQ<WeightedEdge>(N);
		boolean[] visited = new boolean[N];
		explore(adj, 0, visited, pq);
		
		while (!pq.isEmpty() && mst.size() < N - 1) {
			WeightedEdge e = pq.dequeue();
			int v = e.v; int w = e.w;
			if (visited[v] && visited[w]) continue;
			mst.add(e);
			value += e.weight;
			if (!visited[v]) explore(adj, v, visited, pq);
			if (!visited[w]) explore(adj, w, visited, pq);
		}
	}
	
	private void explore(List<WeightedEdge>[] adj, int v, boolean[] visited, IndexMinPQ<WeightedEdge> pq) {
		visited[v] = true;
		for (WeightedEdge e : adj[v]) {
			int w = e.other(v);
			if (visited[w]) continue;
			if (!pq.insert(w, e))
				pq.decreaseKey(w, e);
		}
	}
	
	public Iterable<WeightedEdge> getMST() {
		return mst;
	}
	
	public double value() {
		return value;
	}
}
