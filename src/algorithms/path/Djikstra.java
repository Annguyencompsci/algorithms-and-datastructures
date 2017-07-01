package algorithms.path;

import java.util.LinkedList;
import java.util.PriorityQueue;

import datastructures.edges.WeightedEdge;

/**
 * Dijkstra's Greedy algorithm to find the shortest
 * path between two vertices in a weighted graph with
 * efficiency |E| + |V| log |V|. Technically just a
 * best first search
 * @author An Nguyen
 *
 */
public class Djikstra {
	
	private double[] distTo; // Stores the distance from the source s
							// to any vertex
	
	/**
	 * Node object used for priority queue construction in Dijkstra
	 * @author An Nguyen
	 *
	 */
	private class Node implements Comparable<Node> {
		private int v; // the vertex of that node
		private double dist; // the distance to that node
		
		/**
		 * Create a new node object directed at v
		 * with weight dist
		 * @param v the vertex this Node is pointing at
		 * @param dist the weight of the node
		 */
		public Node(int v, double dist) {
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node that) {
			double cmp = this.dist - that.dist;
			return cmp < 0 ? -1 : cmp > 0 ? 1 : 0;
		}
	}
	
	/**
	 * Create a shortest path instance which computes
	 * the shortest distance to any vertex from the source
	 * @param N the size of the graph
	 * @param s the source of the graph
	 * @param adj the adjacency list representing the graph
	 */
	public Djikstra(int N, int s, LinkedList<WeightedEdge>[] adj) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		distTo = new double[N];
		for (int i = 0; i < N; i++) 
			distTo[s] = Double.POSITIVE_INFINITY;
		distTo[s] = 0;
		pq.add(new Node(s, 0));
		while (!pq.isEmpty()) {
			Node X = pq.poll();
			for (WeightedEdge e : adj[X.v]) {
				if (X.dist + e.weight() < distTo[e.other(X.v)]) {
					pq.add(new Node(e.other(X.v), X.dist + e.weight()));
					distTo[e.other(X.v)] = X.dist + e.weight();
				}
			}
		}
	}
	
	/**
	 * Compute the distance to a particular vertex
	 * @param v the vertex to inspect
	 * @return the distance to that vertex from the source
	 * @throws IllegalArgumentException if v is not a valid vertex
	 */
	public double distTo(int v) {
		if (v < 0 || v >= distTo.length)
			throw new IllegalArgumentException();
		return distTo[v];
	}
}
