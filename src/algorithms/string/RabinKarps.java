package algorithms.string;

/**
 * Use hashing to achieve O(M + N) substring search. This algorithm
 * works best when multiple substrings need to be searched (when
 * KnuthMorrisPratt is not practical) but can encounter collisions
 * 
 * @author An Nguyen
 *
 */
public class RabinKarps {
	
	private static final int R = 265;  // the size of the character dictionary
	private static final int Q = 8191; // the prime used in hashing
	private final long hash; // the hash of the pattern string
	private final int M; // the length of the pattern string
	private int RM; // the position in 0 to Q where 1 would end at after the hash
	
	/**
	 * Create an Rabin Karps object for efficient substring
	 * search
	 * @param pattern the substring to search for
	 */
	public RabinKarps(String pattern) {
		M = pattern.length();
		hash = hash(pattern, M);
		// Compute where 1 would end at after the hash
		for (int i = 1, RM = 1; i < M; i++) 
			RM = (R * RM) % Q;
	}
	
	/**
	 * Search through the text using the state
	 * machine and compute the index of the first substring
	 * @param text the text to search the pattern in
	 * @return the index of the first substring, -1 if it does not exist
	 */
	public int search(String text) {
		if (text == null)
			throw new NullPointerException("Cannot search for a substring in a null object");
		int N = text.length();
		long txtHash = hash(text, M);
		if (txtHash == hash)
			return 0;
		for (int i = M; i < N; i++) {
			// Remove the first letter from the hash
			txtHash = (txtHash + Q - (RM * text.charAt(i - M)) % Q) % Q;
			// Add a new last letter to the hash
			txtHash = (txtHash * R + text.charAt(i)) % Q;
			if (txtHash == hash)
				return i - M + 1;
		}
		return -1;
	}
	
	/**
	 * Compute the hash of the first M letters modulus
	 * a huge prime
	 * of a certain string
	 * @param s the string
	 * @param M the length of the hashed part
	 * @return the hash of the first M characters
	 */
	private long hash(String s, int M) {
		if (s == null) 
			throw new NullPointerException("Cannot hash a null object");
		long hash = 0;
		for (int i = 0; i < M; i++) 
			hash = (hash * R + s.charAt(i)) % Q;
		return hash;
	}
}
