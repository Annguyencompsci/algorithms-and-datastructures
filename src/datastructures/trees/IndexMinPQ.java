package datastructures.trees;

import java.util.NoSuchElementException;

/**
 * A Minimum Priority Queue with different index sets
 * whereby an element belongs to an index
 * 
 * @author An Nguyen
 * 
 * @param <Key> Keys to compare (Comparable not allowed yet)
 */
public class IndexMinPQ<Key extends Comparable<Key>> {
	private int[] pq; 	// reference that refers to the position of an object
						// index used is the index of that object
	private int[] qp;	// reference that refers to the object
						// index used is the position of that object
	private Key[] keys; // stores the objects
	private int n; // the current size of the pq
	
	/**
	 * Create the min priority queue of size N
	 * @param N the size of the queue
	 */
	@SuppressWarnings("unchecked")
	public IndexMinPQ(int N) {
		pq = new int[N + 1];
		qp = new int[N + 1];
		keys = (Key[]) new Comparable[N + 1];
		n = 0;
		for (int i = 0; i < keys.length; i++) 
			pq[i] = -1;
	}
	
	/**
	 * Determines if the Priority Queue contains an
	 * index i
	 * @param i the index to test
	 * @return whether the queue contains the index i
	 * @throws IllegalArgumentException if i is not a valid index
	 */
	public boolean contains(int i) {
		if (i < 0 || i >= pq.length)
			throw new IllegalArgumentException(i + " is not in the range [0, " + pq.length + "]");
		return keys[i] != null;
	}
	
	/**
	 * Decrease the value of the index i to key
	 * @param i the index whose value it is to decrease
	 * @param key the new element to put into the index,
	 * 		won't perform any operations if the new key is
	 * 		larger than the key already inside of the index
	 * @throws IllegalArgumentException if i is not a valid index
	 */
	public void decreaseKey(int i, Key key) {
		if (i < 0 || i >= pq.length)
			throw new IllegalArgumentException(i + " is not in the range [0, " + pq.length + "]");
		if (keys[i] == null)
			throw new NullPointerException("The element at index " + i + " does not exist");
		if (keys[i].compareTo(key) < 0)
			return;
		keys[i] = key;
		swim(qp[i]);
	}
	
	/**
	 * Insert an item into the minimum pq
	 * @param i the index
	 * @param key the key / object that will be in that index
	 * @return whether the insertion fails or succeed. The insertion 
	 * 		fails if there is already a variable inside of index i
	 * @throws IllegalArgumentException if i is not a valid index
	 */
	public boolean insert(int i, Key key) {
		if (i < 0 || i >= pq.length)
			throw new IllegalArgumentException(i + " is not in the range [0, " + pq.length + "]");
		if (contains(i))
			return false;
		keys[i] = key;
		pq[++n] = i;
		qp[i] = n;
		swim(n);
		return true;
	}
	
	/**
	 * Determine whether the priority queue is empty
	 * @return whether the queue is empty
	 */
	public boolean isEmpty() {
		return n == 0;
	}
	
	/**
	 * Return the head of the priority queue
	 * @return the head of the priority queue, as a Key object
	 * @throws NoSuchElementException if the queue is empty
	 */
	public Key dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Cannot dequeue from an empty queue");
		int min = pq[1];
		exch(1, n--);
		sink(1);
		pq[n + 1] = -1;
		qp[min] = -1;
		Key k = keys[min];
		keys[min] = null;
		return k;
	}
	
	/**
	 * Swim a key up the priority tree, switching it with
	 * its parent if necessary
	 * @param k the initial index of the key
	 */
	private void swim(int k) {
		while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
	}
	
	/**
	 * Sink a key down the priority tree, switching it with
	 * its children if necessary. This operation prioritize the
	 * smallest of the two children
	 * @param k the initial index of the key
	 */
	private void sink(int k) {
		while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) 
            	j++;
            if (!greater(k, j)) 				
            	break;
            exch(k, j);
            k = j;
        }
	}
	
	/**
	 * Determine if elements in two indices 
	 * @param i the index of one of the elements
	 * @param j the index of one of the elements
	 * @return true if i is greater, false if not
	 */
	private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }
	
	/**
	 * Exchange the two elements in two indices in terms
	 * of their position in the priority queue
	 * @param i the first element
	 * @param j the second element
	 */
	private void exch(int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
}

