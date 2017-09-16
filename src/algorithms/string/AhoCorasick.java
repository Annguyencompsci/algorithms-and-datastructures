package algorithms.string;

import java.util.LinkedList;
import java.util.Queue;

/**
 * An algorithm that matches a string with a finite dictionary
 * in O(N + M) time, where N is the length of the string, 
 * and M is the combined length of all dictionary words.
 * The algorithm builds a finite state machine
 * similar to KMP, but includes different strings. It then uses BFS
 * and the KMP algorithm to construct a Trie that acts as a finite 
 * state machine
 * 
 * @author An Nguyen
 */
public class AhoCorasick {
	
	private static final int R = 265; // The radix or range of all possible characters
	private int[][] dfa; // Definitive finite automata, used for string search
	private boolean[] success;
	private int K; // the maximum length of the words in the dictionary
	
	/**
	 * Construct an Aho Corasick Machine with the given
	 * dictionary
	 * @param dict the dictionary of strings
	 * @param k the combined length of all dictionary words
	 */
	public AhoCorasick(String[] dict, int k) {
		
		if (dict == null)
			throw new IllegalArgumentException("Dictionary given to "
					+ "Aho Corasick algorithm cannot be null");
		this.K = k;
		int M = dict.length;
		dfa = new int[K][R];
		success = new boolean[K + 1];
		
		int state = 1;
		
		// Basis for breadth first construct
		Queue<Integer> index = new LinkedList<Integer>(); 
		Queue<Integer> current_state = new LinkedList<Integer>();
		Queue<Integer> failed_state = new LinkedList<Integer>(); 
		
		// Construct the first layer of nodes of the dfa
		for (int j = 0; j < M; j++) {
			if (dict[j].length() == 0)
				continue;
			if (dfa[0][dict[j].charAt(0)] != 0)
				dfa[0][dict[j].charAt(0)] = ++state;
			index.add(j);
			current_state.add(dfa[0][dict[j].charAt(0)]);
			failed_state.add(0);
		}
		
		// Construct the ith layer of nodes of the dfa
		
		int layer = 2;
		while (!current_state.isEmpty()) {
			M = current_state.size();
			
			for (int s = 0; s < M; s++) {
				int j = index.poll();
				int i = current_state.poll();
				int X = failed_state.poll();
				
				// Exit level if i is more than the string's length
				if (dict[j].length() == layer) {
					success[i] = true;
					continue;
				}
				
				// Determine next level if success
				if (dfa[i][dict[j].charAt(i)] != 0)
					dfa[i][dict[j].charAt(i)] = ++state;
				
				// Configure failed states
				for (int c = 0; c < R; c++)
					if (dfa[i][c] == 0)
						dfa[i][c] = dfa[X][c];
				// Update X and i
				X = dfa[X][dict[j].charAt(i)];
				
				// Put next stage into the queue
				index.add(j);
				current_state.add(dfa[i][dict[j].charAt(i)]);
				failed_state.add(X);
			}
			
			
			// Layer denotes the length of the current level
			layer++;
		}
	}
	
	/**
	 * Search in the dfa to find if the text matches any
	 * of the strings in the original dictionary
	 * 
	 * @param text the plaintext
	 * @return whether the plaintext contains a word
	 * 			in the dictionary
	 */
	public boolean search(String text) {
		int i;
		int state;
		int N = text.length();
		for (state = 0, i = 0; !success[state] && i < N; i++) 
			state = dfa[state][text.charAt(i)];
		return success[state];
	}
}
