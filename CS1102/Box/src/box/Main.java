/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package box;

/**
 *
 * @author mitz
 */
public class Main {
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// MatchBox object
		MatchBox matchbox = new MatchBox(5, 10, 3);
		// Print width, height, depth, weight and volume
		matchbox.getVolume();
	}
}
