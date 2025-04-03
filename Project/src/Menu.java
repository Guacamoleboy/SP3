import util.*;

public abstract class Menu { // (Superclass)

    // Attributes
    private static FileIO io = new FileIO();
    private static TextUI ui = new TextUI();

    // ________________________________________________________

    public void startSession(String username){
        // Load / toggle data

        //Display data
        ui.displayMsg("\nWelcome to the Main Menu, " + username);
    }

    // ________________________________________________________

    public abstract void endSession();

    // ________________________________________________________

    public abstract void runMenuLoop();

}
