package algorithms.string;

/**
 * An Algo used to compute the minimum lexicographic rotation
 * of a string, which unfortunately I cannot understand. Please feel
 * free to recommend me resources related to this algorithm!! It runs in O(N), which
 * is simple to see, but I cannot reason out what it was doing
 * 
 * 
 * Wikipedia on this: https://en.wikipedia.org/wiki/Lexicographically_minimal_string_rotation
 * 
 * 
 * @author An Nguyen
 *
 */
public class Booth {
	
	/**
	 * Find the lexigraphically minimal circular substring using Booth's algorithm
	 * @param S the string to rotate
	 * @return the index of the start of the least rotation
	 */
	public static int lcs(String S) {
		S = S + S; // prevent modulus operations by duplicating the string
		int N = S.length();
		int[] f = new int[N]; // Initiate state machine ?
		for (int i = 0; i < N; i++) 
			f[i] = -1;
		int k = 0; // Least rotation
		for (int j = 1; j < N; j++) {
			char sj = S.charAt(j);
			int i = f[j - k - 1];
			while (i != -1 && sj != S.charAt(k + i + 1)) {
				if (sj < S.charAt(k + i + 1))
					k = j - i - 1;
				i = f[i];
			}
			if (sj != S.charAt(k + i + 1)) {
				if (sj < S.charAt(k))
					k = j;
				f[j - k] = -1;
			}
			else
				f[j - k] = i + 1;
		}
		return k;
	}
	
	public static void main(String[] args) {
		System.out.println(lcs("interestingzzz"));
	}
}
