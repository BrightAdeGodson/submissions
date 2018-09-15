/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeeyescountchallenge;

/**
 *
 * @author mitz
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
            int trial;
            int trialMax;
            int timeOfSnakeEyes;
            int[] timesOfSnakeEyes;

            trialMax = 1000;
            timesOfSnakeEyes = new int[trialMax];
            
            for (trial = 0; trial < trialMax; trial++ ) {
                timeOfSnakeEyes = rollingDice();
                TextIO.putln(trial+ ": Snake Eyes after "+timeOfSnakeEyes+" rolls of the dice");
                timesOfSnakeEyes[trial] = timeOfSnakeEyes;
            }
            
            TextIO.putln("Average of Snake Eyes is "+average(timesOfSnakeEyes)+" rolls of the dice");
        }
        
        public static int rollingDice() {
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
            return countSnakeEyes;
        }
        
        public static int sum(int[] array) {
            int sum; 
            sum = 0;
            
            for (int value : array) {
                sum += value;
            }
            return sum;
        }
        
        public static double average(int[] array) {
            int total = sum(array);
            return (double) total / array.length;
        }
}
