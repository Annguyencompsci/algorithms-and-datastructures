package algorithms.maxflow;

import java.util.LinkedList;
import java.util.Queue;

import datastructures.edges.FlowEdge;

/**
 * Use Folk Fulkerson to find maxflow (prioritize shortest path) 
 * This is actually Edmonds Karp and runs in |V||E|^2 where V denotes the
 * vertices and E denotes the edges
 *
 * @author An Nguyen
 *
 */
public class FordFulkerson {
	private boolean[] visited; 	// stores which index the algorithm has visited
								// in a single augmenting path run
	private double value; // stores the value of the max flow
	
	/**
	 * Create a FordFulkerson object that automatically
	 * compute the maxflow of a flow graph. This algorithm also
	 * allows the extraction of the cut vertices
	 * @param N the size of the graph
	 * @param adj the adjacency list of flow edges that represents
	 * 				the flow graph
	 * @param s the source vertex
	 * @param t the sink vertex
	 */
	public FordFulkerson(int N, LinkedList<FlowEdge>[] adj, int s, int t) {
		visited = new boolean[N]; FlowEdge[] edgeTo = new FlowEdge[N];
		value = 0;
		while (hasAugmentingPath(adj, s, t, N, edgeTo)) {
			visited = new boolean[N];
			double bottle = Double.POSITIVE_INFINITY;
			for (int v = t; v != s; v = edgeTo[v].other(v)) 
				if (edgeTo[v].residualCapacityTo(v) < bottle)
					bottle = edgeTo[v].residualCapacityTo(v);
			for (int v = t; v != s; v = edgeTo[v].other(v)) 
				edgeTo[v].addFlow(bottle, v);
			value += bottle;
		}
	}
	
	/**
	 * Determines if there is still an augmenting path in the
	 * flow graph by going only through edges with positive residual capacity
	 * @param adj the adjacency list of flow edges that represents the flow graph
	 * @param s the source vertex
	 * @param t the sink vertex
	 * @param N the size of the graph
	 * @param edgeTo an array storing the edge to get to a certain vertex
	 * @return whether there is an augmenting path from s to t
	 */
	private boolean hasAugmentingPath(LinkedList<FlowEdge>[] adj, int s, int t, int N, FlowEdge[] edgeTo) {
		Queue<Integer> pq = new LinkedList<Integer>();
		pq.add(s);
		visited[s] = true;
		
		while (!pq.isEmpty()) {
			int v = pq.poll();
			if (v == t) break;
			
			for (FlowEdge e : adj[v]) {
				int w = e.other(v);
				if (!visited[w] && e.residualCapacityTo(w) > 0) {
					edgeTo[w] = e;
					visited[w] = true;
					pq.add(w);
				} 
			}
		}
		
		return visited[t];
	}
	
	/**
	 * Compute the value / maxflow of the residual flow graph
	 * @return the maxflow of the graph
	 */
	public double value() {
		return value;
	}
	
	/**
	 * Compute whether a vertex v belongs to a cut of the Graph G
	 * @param v the vertex to inspect
	 * @return whether v belongs to the minimum cut
	 */
	public boolean inCut(int v) {
		return visited[v];
	}
}

