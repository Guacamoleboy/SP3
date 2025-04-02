import util.FileIO;
import util.TextUI;

public class MainMenu extends Menu{ // Menu for everyone other than devs & admins

    // Attributes
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    // ________________________________________________________

    @Override
    public void startSession(){

        // Load / toggle data

        //Display data
        ui.displayMsg("\nWelcome to the Main Menu, NAME.\n");


    }

    // ________________________________________________________

    @Override
    public void runMenuLoop(){

    }

    // ________________________________________________________

    @Override
    public void endSession(){

    }

}
