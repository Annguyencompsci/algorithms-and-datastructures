package algorithms.numbertheory;

/**
 * Method used to find certain properties of numbers.
 * 
 * @author An Nguyen
 *
 */
public class Factors {
	
	/**
	 * Tau function returns the number of integer 
	 * divisors of a certain integer
	 * @complexity O(sqrtN)
	 * @param num the integer to examine
	 * @return the number of unique factors the integer
	 * 		has. 0 if the number is negative or 0
	 */
	public static int tau(int num) {
		
		if (num < 0)
			return 0;
		
		int tau = 1;
		
		// Prime factorization (counting prime factors)
		
		test_2: {
			int a = 0;
			while ((num & 1) == 0) {
				a++; num /= 2;
			}
			tau *= a + 1;
		}
		
		test_odd: {
			for (int i = 3; i * i < num; i += 2) {
				int a = 0;
				while (num % i == 0) {
					a++; num /= i;
				}
				tau *= a + 1;
			}
			
			if (num > 0)
				tau *= 2;
		}
		
		return tau;
	}
	
	/**
	 * Test certain methods in this class
	 * @param args the terminal input
	 */
	public static void main(String[] args) {
		System.out.println(tau(84));
	}
	
}
