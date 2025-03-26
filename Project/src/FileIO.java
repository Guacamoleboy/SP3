/*

    Featured in this FileIO
    _______________

    saveData
    readData

    Last updated: 26-03-2025

*/

import java.util.*;
import java.io.*;

public class FileIO { // Data

    public void saveData(ArrayList <String> list, String path, String header){

        try {

            FileWriter writer = new FileWriter(path);

            writer.write(header+"\n");

            for(String s : list){
                writer.write(s+"\n");
            }

            writer.close(); // Closes our writer

        } catch (IOException e) {

            System.out.println("There was a problem.. Problem found: " + e.getMessage());

        } // Try-catch end

    }

    // ________________________________________________________

    public ArrayList <String> readData(String path){

        ArrayList <String> data = new ArrayList<>();

        File file = new File(path);

        try {

            Scanner scan = new Scanner(file);
            scan.nextLine();

            while(scan.hasNextLine()){
                String line = scan.nextLine();
                data.add(line);
            } // While end

        } catch (FileNotFoundException e) {

            System.out.println("Error - File not found...");

        } // Try-catch end

        return data; // Return ArrayList of type String

    }

}
