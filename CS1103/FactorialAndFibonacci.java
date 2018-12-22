/*
 * Factorial and Fibonacci calculator
 * 
 * Output:
 * Enter the value of number: 15
 * Factorial of 15 is: 1307674368000
 * Fibonacci numbers are: 
 * 0
 * 1
 * 1
 * 2
 * 3
 * 5
 * 8
 * 13
 * 21
 * 34
 * 55
 * 89
 * 144
 * 233
 * 377
 */

import java.util.Scanner;
import java.math.BigInteger;

public class FactorialAndFibonacci {
	public static BigInteger factorial(int number) {
		if (number <= 1) {
			return BigInteger.ONE;
		} else {
			return factorial(number - 1).multiply(BigInteger.valueOf(number));
		}
	}

	public static BigInteger fibonacci(int n) {
		if (n == 0 || n == 1) {
			return BigInteger.ONE;
		}
		return fibonacci(n - 2).add(fibonacci(n - 1));
	}

	public static void main(String[] args) {
		System.out.print("Enter the value of number: ");
		Scanner in = new Scanner(System.in);

		BigInteger number = in.nextBigInteger();
		in.close();

		// Output Factorial of number
		System.out.println("Factorial of "+number+" is: "+(factorial(number.intValue())));

		// Output Fibonacci numbers
		System.out.println("Fibonacci numbers are: ");
		System.out.println(0);
		for (int i = 0; i < number.intValue() - 1; i++) {
			System.out.println(fibonacci(i));
		}
	}
}
