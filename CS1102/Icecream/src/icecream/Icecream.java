/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icecream;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author mitz
 */
public class Icecream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	Path cwd = Paths.get(System.getProperty("user.dir"));
        Path dataFile;
        dataFile = Paths.get(cwd.toString(), "src", "icecream", "icecream.dat");
        try {
            TextIO.readFile(dataFile.toString());
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
        
        int cones = 0;
        int strawberry = 0;
        double percent = 0;
        
        while (!TextIO.eof()) {
            String data;
            data = TextIO.getln();
            
            cones++;
            if (data.equals("Strawberry")) {
                strawberry++;
            }
        }
        
        percent = (double) Math.round(100.0 * strawberry / cones);

        System.out.println("Number of cones: "+ cones);
        System.out.println("Number of Strawberry cones: "+ strawberry);
        System.out.println("Percentage of Strawberry cones: "+ percent);
    }
}
