import util.*;

public class DevMenu extends Menu { // Menu for devs (Subclass)

    // Attributes
    private static FileIO io = new FileIO();
    private static TextUI ui = new TextUI();

    // ________________________________________________________

    @Override
    public void startSession(String username){

        //Display data
        ui.displayMsg("\nWelcome to the Dev Menu, " + username);

    }

    // ________________________________________________________

    @Override
    public void endSession(){

        // En random kommentar

    }

    // ________________________________________________________

    @Override
    public void runMenuLoop(){

    }

}
