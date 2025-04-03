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

        String input = ui.promptText("\nPlease choose one of the following:\n1) Add Movie\n2) Add Series\n3) Ban Account\n4) Remove Account\n5) Set" +
                " Account As Inactive\n6) Set Account As Paused\n7) Change Password\n8) Change Username\n\nType " + ui.promptTextFormat("outline")+
                " NEXT " + ui.promptTextFormat("outline reset") + " To Load More..");

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
            case "next":
                // Something
                break;
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
