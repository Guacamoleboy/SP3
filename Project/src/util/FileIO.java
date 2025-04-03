package util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class FileIO {

    // Attributes
    private static final String red = "\u001B[31m";
    private static final String reset = "\u001B[0m";

    // ________________________________________________________

    public void saveData(ArrayList<String> list, String path, String header) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(header + "\n");
            for (String s : list) {
                writer.write(s + "\n");
            }
            writer.close(); // Closes our writer
        } catch (IOException e) {
            System.out.println("There was a problem.. Problem found: " + e.getMessage());
        }
    }

    // ________________________________________________________

    public ArrayList<String> readData(String path) {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine(); // Skip the header
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error - File not found...");
        }
        return data; // Return ArrayList of type String
    }

    // ________________________________________________________

    public void changeUsername(String oldUsername, String newUsername, String path, String header) {
        ArrayList<String> data = readData(path);
        for (int i = 0; i < data.size(); i++) {
            String[] userDetails = data.get(i).split(",");
            if (userDetails[0].equals(oldUsername)) {
                userDetails[0] = newUsername; // Change the username
                data.set(i, String.join(",", userDetails)); // Update the user in the list
                saveData(data, path, header); // Save the updated data
                System.out.println("Username updated successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    // ________________________________________________________

    public void changePassword(String username, String newPassword, String path, String header) {
        ArrayList<String> data = readData(path);
        for (int i = 0; i < data.size(); i++) {
            String[] userDetails = data.get(i).split(",");
            if (userDetails[0].equals(username)) {
                userDetails[4] = newPassword; // Update the password
                data.set(i, String.join(",", userDetails)); // Update the user in the list
                saveData(data, path, header); // Save the updated data
                System.out.println("Password updated successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    // ________________________________________________________

    public String[] readData(String path, int length) {
        String[] data = new String[length];
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine(); // Skip header;
            int i = 0; // Counter
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                data[i] = line;
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return data;
    }

    // ________________________________________________________

    public void fixCSV(String inputFilePath, String outputFilePath) {
        ArrayList<String> fixedLines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
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
            System.out.println("Error. Please fix and recompile.");
        }

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

    // ________________________________________________________

    public void clearData(String path, String dirPath, String fileEnding, String fileFormat) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please confirm twice that you want to remove all data from this .csv file:");
        String response1 = scanner.nextLine().trim();

        if (!response1.equalsIgnoreCase("yes")) {
            System.out.println("You have typed no. Prompt ending...");
            return;
        }

        System.out.println(red + "Are you really sure you want to delete all data?: " + reset);
        String response2 = scanner.nextLine().trim();

        if (!response2.equalsIgnoreCase("yes")) {
            System.out.println("You have typed no. Prompt ending...");
            return;
        }

        File originalFile = new File(path);

        if (!originalFile.exists()) {
            System.out.println("Error. File not found. Check path and try again...");
            return;
        }

        String header = null;
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile))) {
            String line;
            int lineNumber = 0;
            if ((header = reader.readLine()) != null) {
                System.out.println("Header: '" + header + "'");
            }

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                fileContent.append(line).append("\n");
                System.out.println("Line " + (lineNumber + 1) + ": '" + line + "'");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }

        if (header != null && !header.trim().isEmpty()) {
            createBackupAndClearFile(path, header, fileContent.toString(), dirPath, fileEnding, fileFormat);
        } else {
            System.out.println("Original CSV file is empty or header is invalid.");
        }
    }

    // ________________________________________________________

    private void createBackupAndClearFile(String path, String header, String fileContent, String dirPath, String fileEnding, String fileFormat) {
        File originalFile = new File(path);
        String originalFileName = new File(path).getName().replace(fileFormat, "");
        String backupDir = dirPath;
        String backupBaseName = backupDir + originalFileName + fileEnding;
        String backupFilePath = backupBaseName + fileFormat;

        int count = 1;
        while (new File(backupFilePath).exists()) {
            backupFilePath = backupBaseName + "_" + count + fileFormat;
            count++;
        }

        try {
            File backupDirectory = new File(backupDir);
            if (!backupDirectory.exists()) {
                backupDirectory.mkdirs();
            }

            try (BufferedWriter backupWriter = new BufferedWriter(new FileWriter(backupFilePath));
                 BufferedWriter originalWriter = new BufferedWriter(new FileWriter(originalFile, false))) {

                backupWriter.write(header);
                backupWriter.newLine();
                backupWriter.write(fileContent);

                originalWriter.write(header);
                originalWriter.newLine();

                System.out.println("Backup created: " + backupFilePath);
                System.out.println("Original file cleared, only header retained.");
            }

        } catch (IOException e) {
            System.out.println("Error while processing the file.");
        }
    }

    // ________________________________________________________

    public void deleteAfter30Days(String folderPath, String endsWith) {
        File file = new File(folderPath);

        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles((dir, name) -> name.endsWith(endsWith));

            if (files != null && files.length > 0) {
                for (File f : files) {
                    try {
                        Path p = Paths.get(f.getAbsolutePath());
                        BasicFileAttributes attributes = Files.readAttributes(p, BasicFileAttributes.class);
                        long creationTimeMilliseconds = attributes.creationTime().toMillis();
                        long currentTimeMilliseconds = System.currentTimeMillis();

                        long ageInMilliseconds = currentTimeMilliseconds - creationTimeMilliseconds;
                        long ageInDays = TimeUnit.MILLISECONDS.toDays(ageInMilliseconds);

                        if (ageInDays >= 30) {
                            boolean deleted = f.delete();
                            if (deleted) {
                                System.out.println("File deleted: " + f.getName());
                            } else {
                                System.out.println("Failed to remove file: " + f.getName());
                            }
                        } else {
                            System.out.println("Not yet 30 days: " + f.getName() + " | Age: " + ageInDays + " days old.");
                        }

                    } catch (Exception e) {
                        System.out.println("Error. Please try again.");
                    }
                }
            } else {
                System.out.println("No " + endsWith + " files found in this directory..");
            }
        } else {
            System.out.println("Invalid directory path.. Please try again.");
        }
    }
}
