import util.FileIO;
import util.TextUI;

public class MainMenu extends Menu { // Menu for everyone other than devs & admins

    // Attributes
    TextUI ui = new TextUI();
    FileIO io = new FileIO();
    private String loggedInUsername = null;

    // ________________________________________________________

    @Override
    public void startSession() {
        // Displaying greeting message and starting login
        ui.displayMsg("\nWelcome to the Main Menu\n");
        login();
    }

    // ________________________________________________________

    @Override
    public void runMenuLoop() {
        // Menu loop is handled inside showMenu() now
    }

    // ________________________________________________________

    @Override
    public void endSession() {
        // Clean up or close session if needed
    }

    // ________________________________________________________

    // Login function with loop for retry
    private void login() {
        boolean loginSuccess = false;
        while (!loginSuccess) {
            // Get username and password using promptText
            String username = ui.promptText("Enter username: ");
            String password = ui.promptText("Enter password: ");

            // Verify the credentials (this can be improved with proper validation)
            if (isValidLogin(username, password)) {
                loggedInUsername = username;
                ui.displayMsg("Login successful!\n");
                loginSuccess = true;
                showMenu();  // Call the method to show the menu after login
            } else {
                ui.displayMsg("Invalid credentials, please try again.\n");
            }
        }
    }

    // ________________________________________________________

    // Check if the login is valid (simple check)
    private boolean isValidLogin(String username, String password) {
        // Here you can check the credentials against the CSV file or other storage
        // For now, this is a dummy check:
        return username.equals("chris") && password.equals("Hej123");
    }

    // ________________________________________________________

    // Function to show the main menu and handle user choice
    private void showMenu() {
        boolean exit = false;
        while (!exit) {
            // Display options to the user
            System.out.println("\nMain Menu:");
            System.out.println("1. Change Username");
            System.out.println("2. Change Password");
            System.out.println("3. Logout");
            System.out.println("4. Exit");

            int choice = getValidMenuChoice();

            // Handling user choice
            switch (choice) {
                case 1:
                    // Change Username
                    changeUsername();
                    break;

                case 2:
                    // Change Password
                    changePassword();
                    break;

                case 3:
                    // Logout
                    loggedInUsername = null;
                    ui.displayMsg("You have been logged out.\n");
                    login();  // Call login again to re-authenticate
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // ________________________________________________________

    // Method to get valid menu choice from user
    private int getValidMenuChoice() {
        int choice = -1;
        while (choice < 1 || choice > 4) {
            try {
                String input = ui.promptText("Choose an option (1-4): ");
                choice = Integer.parseInt(input);  // Convert the input to integer
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please select a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return choice;
    }

    // ________________________________________________________

    // Change Username function
    private void changeUsername() {
        String newUsername = ui.promptText("Enter new username: ");
        io.changeUsername(loggedInUsername, newUsername, "data/userData.csv", "Username, ID, Age, Gender, Password, Banned");
        loggedInUsername = newUsername;  // Update the logged in username
        System.out.println("Username successfully updated to " + newUsername);
    }

    // ________________________________________________________

    // Change Password function
    private void changePassword() {
        String newPassword = ui.promptText("Enter new password: ");
        io.changePassword(loggedInUsername, newPassword, "data/userData.csv", "Username, ID, Age, Gender, Password, Banned");
        System.out.println("Password successfully updated.");
    }
}
