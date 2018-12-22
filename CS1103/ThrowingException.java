

public class ThrowingException {
	/**
	 * Returns the larger of the two roots of the quadratic equation
	 * A*x*x + B*x + C = 0, provided it has any roots.  If A == 0 or
	 * if the discriminant, B*B - 4*A*C, is negative, then an exception
	 * of type IllegalArgumentException is thrown.
	 */
	static public double root( double A, double B, double C ) 
			throws IllegalArgumentException {
		if (A == 0) {
			throw new IllegalArgumentException("A can't be zero.");
		}
		else {
			double disc = B*B - 4*A*C;
			if (disc < 0)
				throw new IllegalArgumentException("Discriminant < zero.");
			return  (-B + Math.sqrt(disc)) / (2*A);
		}
	}

	/**
	 * Main method
	 * @param arguments
	 */
	public static void main(String[] args) {
		double A, B, C;
		double solution;  // The solution computed for the equation.

		System.out.println("This program will print a solution of an equation");
		System.out.println("of the form A*X*X + B*X + C = 0, where A, B, and");
		System.out.println("C are values that you enter.");

		System.out.println("\nEnter values for A, B, and C:");
		System.out.print("A = ");
		A = TextIO.getlnDouble();
		System.out.print("B = ");
		B = TextIO.getlnDouble();
		System.out.print("C = ");
		C = TextIO.getlnDouble();
		System.out.println();

		try {
			solution = root(A, B, C);
			System.out.println("A solution of the equation is: " + solution);  
		} catch (IllegalArgumentException e) {
			System.out.println("No solutions: " + e.getMessage());
		}
	}
}
