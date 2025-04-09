import util.FileIO;
import util.TextUI;

public class MainMenu extends Menu { // Menu for everyone other than devs & admins (Subclass)

    // Attributes
    private static TextUI ui = new TextUI(Main.exitWord);
    private static FileIO io = new FileIO();

    // ________________________________________________________

    public void startSession(String username){

        super.startSession(username);

    }

    // ________________________________________________________

    public void accountSettings(String username){

        super.accountSettings(username);

    }

    // ________________________________________________________

    @Override
    public void endSession(){

    }

}
