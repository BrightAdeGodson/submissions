/*
 *  Tape control class
 *  
 *  TestTape.java Output:
 *  Tape Conents:  Hello World
 *  Final position at the W
 *  
 *  TestTapeGUI.Java Output:
 *  Attached as another file.
 *  
 *  TestTuringMachine.Java Output:
 *  Running machine #1.  Output should be:  Hello
 *  Actual output is:                       Hello
 *  
 *  Running machine #2.  Should throw an IllegalStateExcpetion.
 *  Caught Illegal Argument Exception, with error message:
 *  Cannot find an applicable rule; tape contents = ERROR
 *  
 *  Running machine #3.  Output should be: aababbbababbabbaba aababbbababbabbaba
 *  Actual output is:                      aababbbababbabbaba aababbbababbabbaba
 */


package turing;

public class Tape {
	private Cell currentCell;

	public Tape() {
		Cell newCell = new Cell();
		newCell.content = ' ';
		newCell.prev = null;
		newCell.next = null;
		currentCell = newCell;
	}

	/*
	 * The pointer to current cell
	 * @param None
	 * @return current Cell
	 */
	public Cell getCurrentCell() {
		return currentCell;
	}

	/*
	 * The content of current cell.
	 * @return content string of current cell.
	 */
	public char getContent() {
		return currentCell.content;
	}

	/*
	 * Set a character to the current cell.
	 * @param a character
	 */
	public void setContent(char ch) {
		currentCell.content = ch;
	}

	/*
	 * Moves the current cell one position to the left along the tape.
	 */
	public void moveLeft() {
		if (currentCell.prev == null) {
			Cell newCell = new Cell();
			newCell.content = ' ';
			newCell.prev = null;
			newCell.next = currentCell;
			currentCell.prev = newCell;
		}
		currentCell = currentCell.prev;
	}

	/* 
	 * Moves the current cell one position to the right along the tape.
	 */
	public void moveRight() {
		if (currentCell.next == null) {
			Cell newCell = new Cell();
			newCell.content = ' ';
			newCell.next = null;
			newCell.prev = currentCell;
			currentCell.next = newCell;
		}
		currentCell = currentCell.next;
	}

	/*
	 * Returns a String consisting of the chars from all the cells on the tape.
	 * @return string of contents from all the cells on the tape.
	 */
	public String getTapeContents() {
		Cell pointer = currentCell;
		while (pointer.prev != null)
			pointer = pointer.prev;
		String strContent = "";
		while (pointer != null) {
			strContent += pointer.content;
			pointer = pointer.next;
		}
		// Trim whitespace
		strContent = strContent.trim();
		return strContent;
	}
}