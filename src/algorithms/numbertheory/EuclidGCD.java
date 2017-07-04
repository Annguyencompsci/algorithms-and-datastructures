package algorithms.numbertheory;

/**
 * Euclid's greatest common divisor used for finding the
 * gcd of two numbers
 * @author An Nguyen
 *
 */
public class EuclidGCD {
	
	/**
	 * Euclid's algorithm for finding the gcd of two numbers,
	 * which plays on the fact that GCD(a, b) <=> GCD(b, a % b)
	 * @param a the first number
	 * @param b the second number
	 * @return the gcd of the two
	 */
	public static int GCD(int a, int b) {
		if (a < b)
			return GCD(b, a);
		if (b == 0)
			return a;
		else
			return GCD(b, a % b);
	}
}
