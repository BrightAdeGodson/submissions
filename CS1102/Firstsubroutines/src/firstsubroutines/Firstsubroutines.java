/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstsubroutines;

import java.util.Scanner;

/**
 *
 * @author mitz
 */
public class Firstsubroutines {
	// reverses the given string
	public static String reverseString(String str){
		String reverse = "";
		int i, len;
		
		len = str.length();
		for(i = len - 1; i >= 0; i--){
			reverse = reverse + str.charAt(i);
		}
		return reverse;
	}
	
	// returns a string by removing all the non-letters from the input string
	public static String stripString(String str){
		String strip = "";
		int i, len;
		char ch;
		
		len = str.length();
		str = str.toLowerCase();
		
		for(i = 0; i < len; i++){
			ch = str.charAt(i);
			if(ch >= 'a' && ch <= 'z' ){
				strip = strip + ch;
			}
		}
		return strip;
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		String str, reversed, stripped;
		Scanner sc = new Scanner(System.in);
		
		// Get string from command line argument
		System.out.println("Enter a string");
		str = sc.nextLine();
		
		// Get stripped string
		stripped = stripString(str);
		if(!str.equals(stripped)) {
			System.out.println("stripped: "+ stripped);
		}
		
		// Get reversed string
		reversed = reverseString(stripped);
		System.out.println("reversed: "+ reversed);
		
		if(stripped.equals(reversed)) {
			System.out.println("This IS a palindrome");
		}
		else {
			System.out.println("This is NOT a palindrome");
		}
	}
}
