package algorithms.string;

/**
 * 
 * Compute the substring of a given text or stream
 * by designing a definitive finite-state automata
 * with different states that corresponds to the pattern
 * text. This allow the algorithm to run without any
 * backup in O(M + N) OR O(N)
 * 
 * @author An Nguyen
 *
 */
public class KnuthMorrisPratt {
	private static final int R = 265; // The radix or range of all possible characters
	private int[][] dfa; // the state machine
	private int M; // the range of the pattern text
	
	/**
	 * Create a new Knuth Morris Pratt object and compute
	 * the state machine. This operation will take O(RM) time
	 * where R is the radix of the structure and M is the length
	 * of the pattern
	 * 
	 * @param pattern the pattern substring
	 */
	public KnuthMorrisPratt(String pattern) {
		if (pattern == null)
			throw new NullPointerException("Cannot analyze a null pattern");
		M = pattern.length();
		// This denotes the state machine of the pattern,
		// 	whereby the row is the state that it is in and
		// 	column is the value of the current character
		dfa = new int[M][R];
		dfa[0][pattern.charAt(0)] = 1;
		// i denotes the current state, X denotes the state
		// that the machine is in if passed in the prefix of the
		// pattern from 0 to i - 1
		for (int i = 1, X = 0; i < dfa.length; i++) {
			for (int c = 0; c < R; c++) 
				dfa[i][c] = dfa[X][c];
			dfa[i][pattern.charAt(i)] = i + 1;
			X = dfa[X][pattern.charAt(i)];
		}
	}
	
	/**
	 * Search through the text using the state
	 * machine and compute the index of the first substring
	 * @param text the text to search the pattern in
	 * @return the index of the first substring
	 */
	public int search(String text) {
		int i; 
		int N = text.length();
		int state;
		for (i = 0, state = 0; i < N && state < M; i++) 
			state = dfa[state][text.charAt(i)];
		if (state != M)
			return -1;
		return i - M;
	}
}
