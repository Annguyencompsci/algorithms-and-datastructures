package algorithms.maxflow;

import java.util.LinkedList;
import java.util.Queue;

import datastructures.edges.FlowEdge;

/**
 * Dinic's Algorithm to find the maxflow in a flow network. Dinic's Algorithm
 * tries to build a tree on each iteration in which a vertex's distance from the source
 * its shortest distance from the source. Dinic's then try to add blocking flow into
 * the flow network (a blocking flow is simply a flow going through vertices of level
 * 1, 2, 3, 4, ... t)
 * 
 * @author An Nguyen
 *
 */
public class DinicNaive {
	private double value; 	// the maxflow of the graph
	private int N;			// the size of the graph
	private int[] level;		// the array representing the level / distance of a vertex
							// from the source
	
	/**
	 * The Naive Dinic's Algorithm to find maxflow in a flow network in |E||V|^2.
	 * This performance can be drastically increased to |E||V|log|V| using a 
	 * dynamic link/cut tree
	 * @param N the size of the graph
	 * @param adj the adjacency list that represents the flow graph
	 * @param s the source vertex
	 * @param t the sink vertex
	 */
	public DinicNaive(int N, LinkedList<FlowEdge>[] adj, int s, int t) {
		this.N = N;
		while (BFS(adj, s, t)) {
			int[] pos = new int[N];
			double flow;
			while ((flow = sendFlow(adj, pos, s, t, Double.POSITIVE_INFINITY)) > 0) {
				value += flow;
			}
		}
	}
	
	/**
	 * Send flow along a vertex v to a higher level until 
	 * it reaches v. This guarantees a blocking flow
	 * @param adj the adjacency list that represents the flow graph
	 * @param pos the offset on the vertex's adjacency list (to not visit an edge twice)
	 * @param v the source vertex
	 * @param t the sink vertex
	 * @param flow the current flow
	 * @return the blocking flow
	 */
	private double sendFlow(LinkedList<FlowEdge>[] adj, int[] pos, int v, int t, double flow) {
		if (v == t) return flow;
		while (pos[v] != N) {
			FlowEdge e = adj[v].poll();
			adj[v].addLast(e);
			pos[v]++;
			int w = e.other(v);
			if (level[w] == level[v] + 1 && e.residualCapacityTo(w) > 0) {
				double temp = sendFlow(adj, pos, w, t, Math.min(flow, e.residualCapacityTo(w)));
				if (temp > 0) {
					e.addFlow(temp, w);
					return temp;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Compute the hierarchy tree and compute whether the sink
	 * can be reached from the source
	 * @param adj the adjacency list that represents the flow graph
	 * @param s the source vertex
	 * @param t the sink vertex
	 * @return whether the sink can be reached
	 */
	private boolean BFS(LinkedList<FlowEdge>[] adj, int s, int t) {
		level = new int[N];
		boolean[] visited = new boolean[N];
		Queue<Integer> pq = new LinkedList<Integer>();
		level[s] = 0;
		pq.add(s);
		visited[s] = true;
		while (!pq.isEmpty()) {
			int v = pq.poll();
			for (FlowEdge e : adj[v]) {
				int w = e.other(v);
				if (e.residualCapacityTo(w) > 0 && !visited[w]) {
					visited[w] = true;
					pq.add(w);
					level[w] = level[v] + 1;
				}
			}
		}
		return visited[t];
	}
	
	/**
	 * Compute the value of the max flow
	 * @return the max flow
	 */
	public double value() {
		return value;
	}
}
