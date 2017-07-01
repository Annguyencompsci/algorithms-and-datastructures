package algorithms.adhoc;

/**
 * Used to calculate the number of inversions (violations to
 * the natural order) of an array. An inversion is present
 * iff there is a value j > i whereby a[j] < a[i]. This
 * operation is done in NlogN time due to Divide and Conquer
 * @author An Nguyen
 *
 */
public class InversionMergeSort {
	
	/**
	 * Calculate the number of inversions inside of an array
	 * @return the number of inversions inside of an array
	 */
	public static int calculateInversions(int[] arr) {
		int N = arr.length;
		int[] copy = new int[N];
		for (int i = 0; i < N; i++) 
			copy[i] = arr[i];
		return mergeSort(arr, new int[N], 0, N - 1);
	}
	
	/**
	 * Perform merge sort using an auxiliary array and computing
	 * the number of inversions
	 * @param arr the array to sort
	 * @param aux the auxiliary / temporary array
	 * @param lt the left tree
	 * @param rb the right tree
	 * @return the number of inversions
	 */
	private static int mergeSort(int[] arr, int[] aux, int lt, int rb) {
		int mid, inverse = 0;
		if (lt < rb) {
			mid = (rb + lt) / 2;
			inverse += mergeSort(arr, aux, lt, mid) + 
					mergeSort(arr, aux, mid + 1, rb);
			inverse += merge(arr, aux, lt, mid + 1, rb);
			inverse += merge(arr, aux, lt, mid + 1, rb);
		}
		return inverse;
	}
	
	/**
	 * Merge the two sorted arrays using partitioning and
	 * an extra auxiliary array and compute the inversions
	 * @param arr the original array
	 * @param aux the auxiliary array
	 * @param lt the leftmost index
	 * @param mid the middle index
	 * @param rb the rightmost index
	 * @return the number of inversions
	 */
	private static int merge(int[] arr, int[] aux, int lt, int mid, int rb) {
		int inverse = 0;
		int p = lt;
		int lo = lt;
		int hi = mid;
		while (lo < mid && hi <= rb) {
			if (lo >= mid)
				aux[p++] = arr[hi++];
			else if (hi > rb)
				aux[p++] = arr[lo++];
			else if (arr[lo] < arr[hi])
				aux[p++] = arr[lo++];
			else {
				aux[p++] = arr[hi++];
				inverse += mid - lo;
			}
		}
		for (int i = lt; i <= rb; i++) 
			arr[i] = aux[i];
		return inverse;
	}
}
