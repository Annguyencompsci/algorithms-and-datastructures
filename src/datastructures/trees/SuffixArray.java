package datastructures.trees;

import java.util.Arrays;
import java.util.Comparator;

/**
 * An algorithm to compute the array of suffixes that
 * can be used to prompt for the longest repeated substring.
 * The construction of the Suffix Array (non naive) takes 
 * N log N log N by exploiting the fact that these strings
 * are substrings of the same string
 * 
 * @author An Nguyen
 * 
 * TODO create a functionality to inspect the longest common
 * 	substring
 */
public class SuffixArray {
	// private String LCS; 	// The longest common substring
	private int[] arr; 	// The order of the suffix array
	
	/**
	 * Create a suffix array from string s
	 * @param S the string to create a suffix array from
	 */
	public SuffixArray(String S) {
		int N = S.length();
		Tuple[] L = new Tuple[N];
		Tuple[] L_p;
		
		for (int i = 0; i < N; i++) 
			L[i] = new Tuple(i);
		Arrays.sort(L, new Comparator<Tuple>() {
			@Override
			public int compare(Tuple phi, Tuple psi) {
				return S.charAt(phi.index) - S.charAt(psi.index);
			}
		});
		L[0].arr[1] = 0;
		for (int i = 1; i < N; i++) 
			L[i].arr[1] = L[i - 1].arr[1] + (S.charAt(L[i].index) == S.charAt(L[i - 1].index) ? 0 : 1);
		for (int step = 1; step < N; step *= 2, L = L_p) {
			L_p = new Tuple[N];
			L[0].sortIndex = 0;
			for (int i = 1; i < N; i++) 
				L[i].sortIndex = L[i - 1].sortIndex + (L[i].compareTo(L[i - 1]) == 0 ? 0 : 1);
			int[] qp = new int[N];
			for (int i = 0; i < N; i++) 
				qp[L[i].index] = i;
			for (int i = 0; i < N; i++) 
				L_p[i] = new Tuple(L[i].index, L[i].sortIndex, 
					L[i].index + step < N ? qp[L[i].index + step]: -1);
			Arrays.sort(L_p);
		}
		arr = new int[N];
		for (int i = 0; i < N; i++) 
			arr[i] = L[i].index;
	}
	
	/**
	 * Class that represents a mutable Tuple from
	 * python used to store the index, sort index,
	 * and compare suffixes
	 * 
	 * @author An Nguyen
	 *
	 */
	class Tuple implements Comparable<Tuple> {
		private int[] arr = new int[2];
		private int index;
		private int sortIndex;
		
		/**
		 * Create a new Tuple of a certain index
		 * with priority arr1 and arr2
		 * @param index the index of the suffix 
		 * @param arr1 the first priority
		 * @param arr2 the second priority
		 */
		public Tuple(int index, int arr1, int arr2) {
			this.index = index;
			this.arr[0] = arr1;
			this.arr[1] = arr2;
		}
		
		/**
		 * Create an empty tuple with a certain index
		 * @param index the index of the suffix
		 */
		public Tuple(int index) {
			this.index = index;
		}

		@Override
		/**
		 * Compare two Tuple to inspect whether one is
		 * larger than another
		 * @param that the object to compare to
		 * @return negative if this Tuple is smaller, positive if the inverse,
		 * 		and 0 if they are equivalent
		 */
		public int compareTo(Tuple that) {
			int cmp1 = arr[0] - that.arr[0];
			if (cmp1 != 0)
				return cmp1;
			return arr[1] - that.arr[1];
		}
	}
}
