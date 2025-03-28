/*

    Featured in this FileIO
    _______________________

    saveData
    readData (ArrayList)
    readData (Array)
    CSVFixer

    Last updated: 28-03-2025
    Updated by: Jonas

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

    // ________________________________________________________

    // Overloading by NOT having same parameters as the original. This allows multiple with same name.

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

    // ________________________________________________________

    /*

    How to use:
    ___________

    Overwrite existing file:
    fixCSV("data/seriesData.csv", "data/seriesData.csv");

    Read BUT output as new file:
    fixCSV("data/seriesData.csv", "data/seriesData_fixCSV.csv");

    What it does:
    _____________

    Reads the header LENGTH and checks if each line has the same amount. If the amount is UNDER then
    it will add a ",0" so each line has the exact same length as the header.
    This will allow us to have a pretty setup on Github.

    */

    public void fixCSV(String inputFilePath, String outputFilePath) {

        ArrayList<String> fixedLines = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            String header = br.readLine();

            // Checks .csv file for a header
            if (header == null) {

                System.out.println("Empty CSV file.");
                return;

            }

            fixedLines.add(header);

            int headerCount = header.split(",", -1).length;
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1);
                int diff = headerCount - values.length;

                if (diff > 0) {

                    // Add "0" to the end of the array
                    StringBuilder fixedLine = new StringBuilder(line);

                    for (int i = 0; i < diff; i++) {
                        fixedLine.append(",0");
                    }

                    fixedLines.add(fixedLine.toString());

                } else {

                    fixedLines.add(line);

                } // If-else end

            } // While-end

        } catch (IOException e) {

            System.out.println("Error. Please fix and recompile.");

        }

        // Write the fixed lines to outputFilePath
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath));

            for (String fixedLine : fixedLines) {
                bw.write(fixedLine);
                bw.newLine();
            }

            System.out.println("\nSuccess..\nFixed CSV written to: " + outputFilePath);

        } catch (IOException e) {

            System.out.println("Error. Please fix and recompile.");

        }

    }

} // FileIO end
