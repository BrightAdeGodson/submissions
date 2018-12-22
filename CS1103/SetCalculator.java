/**  
 * Set calculator
 * Outputs:
 * Enter set 1 elements(-1 to finish): 
 * 1
 * 2
 * 3
 * 4
 * -1
 * Enter set 2 elements(-1 to finish): 
 * 3
 * 4
 * 5
 * -1
 * Enter Operation to perform +,*,-
 * +
 * [1, 2, 3, 4, 5]
 * 
 * Enter set 1 elements(-1 to finish): 
 * 1
 * 2
 * 3
 * 4
 * -1
 * Enter set 2 elements(-1 to finish): 
 * 3
 * 4
 * 5
 * -1
 * Enter Operation to perform +,*,-
 * *
 * [3, 4]
 * 
 * Enter set 1 elements(-1 to finish): 
 * 1
 * 2
 * 3
 * 4
 * 5
 * -1
 * Enter set 2 elements(-1 to finish): 
 * 3
 * 4
 * 5
 * -1
 * Enter Operation to perform +,*,-
 * -
 * [1, 2]
 * 
 */

import java.util.Scanner;
import java.util.TreeSet;

public class SetCalculator {

	public static void main(String[] args) {
		TreeSet<Integer> a = new TreeSet<Integer>();
		TreeSet<Integer> b = new TreeSet<Integer>();
		TreeSet<Integer> res = new TreeSet<Integer>();

		System.out.println("Enter set 1 elements(-1 to finish): ");
		int num = 0;
		Scanner sc = new Scanner(System.in);

		while (true) {
			num = sc.nextInt();
			if (num < 0)
				break;
			a.add(num);
		}

		System.out.println("Enter set 2 elements(-1 to finish): ");
		while (true) {
			num = sc.nextInt();
			if (num < 0)
				break;
			b.add(num);
		}

		System.out.println("Enter Operation to perform +,*,-");
		sc.nextLine();

		char operation = sc.nextLine().charAt(0);
		switch (operation) {
		case '+':
			// adds all elements to the res
			res.addAll(a);
			res.addAll(b);
			break;
		case '-':
			// removes all elements in a which are in b
			res.addAll(a);
			res.removeAll(b);
			break;
		case '*':
			// keep only elements which are common
			res.addAll(a);
			res.retainAll(b);
			break;
		}

		System.out.println(res);
	}
}