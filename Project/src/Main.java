import util.TextUI;
import constants.Constants;

public class Main { // Client class

    // Attributes
    private static TextUI ui = new TextUI();

    // ________________________________________________________

    public static void main(String[] args) {

        // Display the version of the app
        ui.displayMsg(ui.promptTextColor("red") + Constants.versionControl + ui.promptTextColor("reset"));

        // Create an instance of MainMenu (this will handle the session and login)
        MainMenu mainMenu = new MainMenu();

        // Start the session
        mainMenu.startSession();
    }

} // Main end
