/**
 * This program lists the files in a directory specified by the user. The user
 * is asked to type in a directory name. If the name entered by the user is not
 * a directory, a message is printed and the program ends.
 * 
 * OUTPUT: 
 * Enter a directory name: /home/mitz/UoPeople/CS1103/PA6
 * Files in directory "/home/mitz/UoPeople/CS1103/PA6":
 * 
 *  Code Unit 6-20181220.zip
 *  PA6.zip
 * 
 * Files in directory /home/mitz/UoPeople/CS1103/PA6/Code Unit 6-20181220:
 * 
 *  TextCollage.java
 *  DrawTextItem.java
 *  DirectoryList.java
 *  DrawTextPanel.java
 *  SimpleFileChooser.java
 * 
 * Files in directory /home/mitz/UoPeople/CS1103/PA6/PA6:
 * 
 *  DrawTextPanel.java
 *  text.txt
 *  textimage.png
 * 
 */

import java.io.File;
import java.util.Scanner;

public class DirectoryList {
	/**
	 * It displays nested files and directories recursively.
	 * This method is recursive.
	 *
	 * @param All files and directories, part of a directory.
	 */
	public static void displayFiles(File[] files) {
		for (File file : files) {
			if (!file.isDirectory()) {
				System.out.println(" " + file.getName());
			}
		}
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("\nFiles in directory " + file.getAbsolutePath() + ":\n");
				displayFiles(file.listFiles());
			}
		}
	}

	public static void main(String[] args) {
		String directoryName; // Directory name entered by the user.
		File directory;       // File object referring to the directory.
		File[] files;         // Array of files in the directory.
		Scanner scanner;      // For reading a line of input from the user.

		scanner = new Scanner(System.in); // scanner reads from standard input.

		System.out.print("Enter a directory name: ");
		directoryName = scanner.nextLine().trim();
		directory = new File(directoryName);

		if (!directory.isDirectory()) {
			if (directory.exists() == false)
				System.out.println("There is no such directory!");
			else
				System.out.println("That file is not a directory.");
		} else {
			files = directory.listFiles();
			System.out.println("Files in directory \"" + directory + "\":\n");
			displayFiles(files);
		}
		// We should close all resources after utilization.
		scanner.close();
	} // end main()
} // end class DirectoryList
