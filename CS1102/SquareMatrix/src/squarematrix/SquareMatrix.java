/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package squarematrix;

/**
 *
 * @author mitz
 */
public class SquareMatrix {
	public static void secondaryDiagonal(int matrix[][]) {
		// Check if matrix[][] is square matrix
		for(int i = 0; i < matrix.length; i++){
			if(matrix.length != matrix[i].length){
				System.out.println("This is not a square matrix.");
				return;
			}
		}
		
		int sum = 0;
		for(int i = 0; i < matrix.length; i++){
			System.out.println("Element = "+matrix[i][matrix.length - i - 1]);
			sum += matrix[i][matrix.length - i - 1];
		}
		System.out.println("Secondary Diagonal = " + sum);
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		int[][] m1 = {{10, 12, 11}, {9, 8, 31}, {2, 16, 24}};
		int[][] m2 = {{10, 12, 11}, {9, 8, 31}};

		System.out.println("{{10, 12, 11}, {9, 8, 31}, {2, 16, 24}}");
		secondaryDiagonal(m1);
		System.out.println("");
		System.out.println("{{10, 12, 11}, {9, 8, 31}}");
		secondaryDiagonal(m2);
	}
}
