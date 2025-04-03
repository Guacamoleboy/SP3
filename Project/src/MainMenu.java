import util.FileIO;
import util.TextUI;

public class MainMenu extends Menu { // Menu for everyone other than devs & admins (Subclass)

    // Attributes
    private static TextUI ui = new TextUI();
    private static FileIO io = new FileIO();

    // ________________________________________________________

    public void startSession(String username){

        super.startSession(username);

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
