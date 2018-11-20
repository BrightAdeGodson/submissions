/**
 * Analyze runtime of the array sizes of 1000, 10000, 100000.
 * Array size: 1000
 * Run time of the insertion sort is: 0.002 sec
 * Run time of the selection sort is: 0.005 sec
 * Run time of the built-in sort method is: 0.001 sec
 * 
 * Array size: 10000
 * Run time of the insertion sort is: 0.06 sec
 * Run time of the selection sort is: 0.053 sec
 * Run time of the built-in sort method is: 0.001 sec
 * 
 * Array size: 100000
 * Run time of the insertion sort is: 1.365 sec
 * Run time of the selection sort is: 2.579 sec
 * Run time of the built-in sort method is: 0.002 sec
 */

import java.util.Arrays;

public class BenchmarkingSortingAlgorithms {

	/**
	 * Creates and returns an array with random integers
	 * @param size the size of the array to be created
	 * @return array
	 */
	static int[] createArrayWithRandomInts(int size) {
		int[] array = new int[size];

		for (int i = 0; i < size; i++) {
			array[i] = (int)(Integer.MAX_VALUE * Math.random());
		}
		return array;
	}

	/**
	 * Sorts array by insertion sort
	 * Copied from Section 7.4.3 of the textbook
	 * @param array
	 */
	static void insertionSort(int[] A) {
		// Sort the array A into increasing order.
		int itemsSorted; // Number of items that have been sorted so far.
		for (itemsSorted = 1; itemsSorted < A.length; itemsSorted++) {
			// Assume that items A[0], A[1], ... A[itemsSorted-1]
			// have already been sorted. Insert A[itemsSorted]
			// into the sorted part of the list.
			int temp = A[itemsSorted]; // The item to be inserted.
			int loc = itemsSorted - 1; // Start at end of list.
			while (loc >= 0 && A[loc] > temp) {
				A[loc + 1] = A[loc]; // Bump item from A[loc] up to loc+1.
				loc = loc - 1; // Go on to next location.
			}
			A[loc + 1] = temp; // Put temp in last vacated space.
		}
	}

	/**
	 * Sorts array by selection sort
	 * Copied from Section 7.4.4 of the textbook
	 * @param array
	 */
	static void selectionSort(int[] A) {
		// Sort A into increasing order, using selection sort
		for (int lastPlace = A.length-1; lastPlace > 0; lastPlace--) {
			// Find the largest item among A[0], A[1], ...,
			// A[lastPlace], and move it into position lastPlace
			// by swapping it with the number that is currently
			// in position lastPlace.
			int maxLoc = 0; // Location of largest item seen so far.
			for (int j = 1; j <= lastPlace; j++) {
				if (A[j] > A[maxLoc]) {
					// Since A[j] is bigger than the maximum we’ve seen
					// so far, j is the new location of the maximum value
					// we’ve seen so far.
					maxLoc = j;
				}
			}
			int temp = A[maxLoc]; // Swap largest item with A[lastPlace].
			A[maxLoc] = A[lastPlace];
			A[lastPlace] = temp;
		} // end of for loop
	}

	/**
	 * Sorts array by Java built-in sort
	 * @param array
	 */
	public static void builtInSort(int[] A) {
		Arrays.sort(A);
	}

	/**
	 * Main method
	 * @param arguments
	 */
	public static void main(String[] args) {
		int[] arraySizes = {1000, 10000, 100000};
		
		for (int size: arraySizes) {
			int[] unsortedArr = createArrayWithRandomInts(size);

			System.out.println("Array size: "+ size);
			// Getting the run time
			long startTime1 = System.currentTimeMillis();
			insertionSort(unsortedArr);
			long runTime1 = System.currentTimeMillis() - startTime1;
			System.out.println("Run time of the insertion sort is: "+ runTime1 / 1000.0 +" sec");

			long startTime2 = System.currentTimeMillis();
			selectionSort(unsortedArr);
			long runTime2 = System.currentTimeMillis() - startTime2;
			System.out.println("Run time of the selection sort is: "+ runTime2 / 1000.0 +" sec");

			long startTime3 = System.currentTimeMillis();
			builtInSort(unsortedArr);
			long runTime3 = System.currentTimeMillis() - startTime3;
			System.out.println("Run time of the built-in sort method is: "+ runTime3 / 1000.0 +" sec");

			System.out.println();
		}
	}
}