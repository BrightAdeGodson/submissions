/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

/**
 *
 * @author mitz
 */
public class Statistics {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		StatCalc myStatCalc;
		myStatCalc = new StatCalc();

		int intArray[] = { 5, 7, 12, 23, 3, 2, 8, 14, 10, 5, 9, 13 };
		
		for (int i : intArray) {
			myStatCalc.enter(i);
		}
		
		System.out.println("Count: " + myStatCalc.getCount());
		System.out.println("Mean: " + myStatCalc.getMean());
		System.out.println("Standard Deviation: " + myStatCalc.getStandardDeviation())
	}
}
