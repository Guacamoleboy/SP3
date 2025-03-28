/*

    Featured in this FileIO
    _______________________

    saveData
    readData (ArrayList)
    readData (Array)

    Last updated: 28-03-2025

*/

import java.util.*;
import java.io.*;

public class FileIO { // Custom generic FileIO

    // Attributes

    // ________________________________________________________

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

    public String[] readData(String path, int length) {

        String[] data = new String[length];
        File file = new File(path);

        try{
            //new scaner created
            Scanner scan = new Scanner(file);
            scan.nextLine(); //skip header;

            int i = 0;  // Counter

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                data[i]=line;
                i++;
            }

        } catch (FileNotFoundException e){
            System.out.println("File not found: "+ e.getMessage());
        }

        return data;

    }

}
