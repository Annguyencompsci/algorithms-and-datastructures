package algorithms.dynamicprogramming;

/**
 * Algorithm used to calculate the minimum jumps to
 * reach the end of an array. An agent can only jump 
 * arr[i] steps if he is on the ith position of the array.
 * 
 * @author An Nguyen
 *
 */
public class MinimumJumps {
	
	/**
	 * This uses the concept of a ladder and effective
	 * range of a jump to greedily solve for 
	 * the minimum jump in O(N)
	 * 
	 * @param arr the array
	 * @return the minimum jumps neccessary to reach the end
	 */
	public static int minimumJumps(int[] arr) {
		int N = arr.length;
		
		if (N == 1)
			return 0;
		
		int jumps = 1; 			// the first visit is the first one
		int range = arr[0]; 		// the number of steps before we have to jump onto another ladder
		int effRange = arr[0]; 	// the longest effective range that we've visit
		
		for (int i = 1; i < N - 1; i++) {
			if (i + arr[i] > effRange)
				effRange = i + arr[i];
			if (--range == 0) {
				range = effRange - i;
				jumps++;
			}
		}
		
		return jumps;
	}
}