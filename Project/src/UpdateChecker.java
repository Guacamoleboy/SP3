/*

    Checks for outdated versions for a given branch vs main.
    If the branch currently being worked in is outdated, then the
    branch user will be prompted.

*/

    import java.io.File;
    import java.io.FileNotFoundException;
    import java.net.HttpURLConnection;
    import java.net.URL;
    import java.util.Scanner;
    import util.*;


    // ________________________________________________________

public class UpdateChecker {

        // Attributes
        private static TextUI ui = new TextUI();

        // ________________________________________________________

        public static boolean checkVersion(){

            String currentVersion = getCurrentVersion();
            String url = "https://raw.githubusercontent.com/Guacamoleboy/SP3/main/Project/src/constants/version.txt";
            try {

                String latestVersion = fetchVersionFrom(url);

                if (!isOutdated(currentVersion, latestVersion)) {
                    ui.displayMsg(ui.promptTextColor("red") + "New update is available ("+latestVersion+")\nYour version: "+ currentVersion +" " + ui.promptTextColor("reset"));
                    return false;
                } else {
                    ui.displayMsg(ui.promptTextColor("green") + "You are up to date ("+latestVersion+")!"+ ui.promptTextColor("reset"));
                    return true;
                }

            } catch(Exception e) {
                System.out.println("Error while checking for updates:" +e.getMessage());
            }

            return false;

         }

        // ________________________________________________________

        private static String fetchVersionFrom(String tmpurl) throws Exception {

            URL url = new URL(tmpurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            try {
                int response = connection.getResponseCode();

                if (response != HttpURLConnection.HTTP_OK) {
                    throw new Exception("HTTP request failed" + response);
                }
                try (Scanner scan = new Scanner(connection.getInputStream())) {
                    if (scan.hasNextLine()) {
                        String version = scan.nextLine().trim();
                        if (version.isEmpty()) {
                            throw new Exception("Version file is empty");
                        }
                        return version;
                    } else {
                        throw new Exception("No data found in file!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error fetching version from url: " + tmpurl);
                throw new Exception("Error fetching version: "+e.getMessage(), e);
            } finally {
                connection.disconnect();
            }

        }

        // ________________________________________________________

        private static boolean isOutdated(String current, String latest) {

            return current.equals(latest);

        }


        private static String getCurrentVersion() {
            try {
                File file = new File("src/constants/version.txt");
                Scanner scan = new Scanner(file);
                if (scan.hasNextLine()) {
                    return scan.nextLine().trim();
                }
            } catch (FileNotFoundException e) {
                System.out.println("No file path found");
            }
            return "0.0.0";
        }



} // UpdateChecker end
