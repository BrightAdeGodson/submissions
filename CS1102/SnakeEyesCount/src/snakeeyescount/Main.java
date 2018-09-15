/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeeyescount;

/**
 *
 * @author mitz
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
            boolean snakeEyes;
            int countSnakeEyes;
            int die1;
            int die2;
            
            snakeEyes = false;
            countSnakeEyes = 0;
            
            do {
                countSnakeEyes++;
                die1 = (int)(Math.random()*6)+1;
                die2 = (int)(Math.random()*6)+1;
                
                if (die1 == 1 && die2 == 1) {
                    snakeEyes = true;
                }
            } while ( !snakeEyes );
            TextIO.putln("Snake Eyes after "+countSnakeEyes+" rolls of the dice");
        }
}
