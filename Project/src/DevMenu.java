import util.*;

public class DevMenu extends Menu { // Menu for devs (Subclass)

    // Attributes
    private static final FileIO io = new FileIO();
    private static final TextUI ui = new TextUI(Main.exitWord);

    // ________________________________________________________

    @Override
    public void startSession(String username){

        //Display data
        ui.displayMsg("\nWelcome to the Dev Menu, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset"));

        // Visuals
        ui.displayMsg("\nPlease choose one of the following:\n1) Add Movie\n2) Add Series\n3) Manage users\n4) filter harsh words\n\nType " + ui.promptTextFormat("outline")+
        " NEXT " + ui.promptTextFormat("outline reset") + " " + ui.promptTextFormat("outline") + " PREV " + ui.promptTextFormat("outline reset") +
        " To Load More | " + "Use " + ui.promptTextFormat("outline") + " BACK " + ui.promptTextFormat("outline reset") +
        " to return\n_________________________________");

        String input = ui.promptTextLine("Input: ");

                switch (input){
            case "1":
                //Needs to be made
                Admin.addMovie();
                break;
            case "2":
                //Needs to be made
                Admin.addSeries();
                break;
            case "3":
                Admin.manageUsers(username);
                break;
            case "4":
                Admin.forbiddenWords("data/bannedWords.csv", "word");
                break;
            case "next":
                ui.displayMsg("Nothing to go next to. There's only one page!");
                break;
            case "prev":
                ui.displayMsg("Nothing to go back to. You're on page 1.");
                break;
            case "back":
                startSession(username);
            default:
                startSession(username);
        }

    }

    // ________________________________________________________

    @Override
    public void endSession(){

        // En random kommentar

    }

    // ________________________________________________________

    @Override
    public void accountSettings(String username){

        ui.displayMsg(ui.promptTextColor("red") + username + ui.promptTextColor("reset") + ", settings:");
        ui.displayMsg("\n1) Change Username\n2) Change Password");


    }

    // ________________________________________________________

    @Override
    public void runMenuLoop(){

    }

}
