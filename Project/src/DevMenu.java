import util.*;

public class DevMenu extends Menu { // Menu for devs (Subclass)

    // Attributes
    private static FileIO io = new FileIO();
    private static TextUI ui = new TextUI();
    private static final Admin admin = new Admin();

    // ________________________________________________________

    @Override
    public void startSession(String username){

        //Display data
        ui.displayMsg("\nWelcome to the Dev Menu, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset"));

        // Visuals
        ui.displayMsg("\nPlease choose one of the following:\n1) Add Movie\n2) Add Series\n3) Ban Account\n4) Remove Account\n5) Set" +
        " Account As Inactive\n6) Set Account As Paused\n7) Change Password\n8) Change Username\n9) ban/unban\n\nType " + ui.promptTextFormat("outline")+
        " NEXT " + ui.promptTextFormat("outline reset") + " " + ui.promptTextFormat("outline") + " PREV " + ui.promptTextFormat("outline reset") +
        " To Load More | " + "Use " + ui.promptTextFormat("outline") + " BACK " + ui.promptTextFormat("outline reset") +
        " to return\n_________________________________");

        String input = ui.promptTextLine("Input: ");

                switch (input){
            case "1":
                // something
                break;
            case "2":
                // something
                break;
            case "3": //Ban user
                admin.banUser(username);
                break;
            case "4":
                accountSettings(username);
                break;
            case "5":
                // Something
                break;
            case "6":
                // Something
                break;
            case "7":
                // Something
                break;
            case "8":
                // Something
                break;
            case "9":
                Admin.forbiddenWords();
                break;
            case "next":
                // Something
                break;
            case "prev":
                // Something
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
