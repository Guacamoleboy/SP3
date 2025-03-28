import java.io.*;
import java.util.*;

public class CSVFixer {

    public static void fixCSV(String inputFilePath, String outputFilePath) {
        List<String> fixedLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String header = br.readLine();

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
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the fixed lines to output
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String fixedLine : fixedLines) {
                bw.write(fixedLine);
                bw.newLine();
            }
            System.out.println("Fixed CSV written to: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Example usage
    public static void main(String[] args) {
        fixCSV("data/filmData.csv", "data/filmData.csv");
    }
}
