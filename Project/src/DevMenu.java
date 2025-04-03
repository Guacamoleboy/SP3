import util.*;

public class DevMenu extends Menu { // Menu for devs (Subclass)

    // Attributes
    private static FileIO io = new FileIO();
    private static TextUI ui = new TextUI();

    // ________________________________________________________

    @Override
    public void startSession(String username){

        //Display data
        ui.displayMsg("\nWelcome to the Dev Menu, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset"));

        int input = ui.promptNumeric("\nPlease choose one of the following:\n1) Add Movie\n2) Add Series\n3) Ban Account\n4) Remove Account\n5) Set" +
                " Account As Inactive\n6) Set Account As Paused\n7) Display Total Users\n8) Change Username\n\nType " + ui.promptTextFormat("outline")+
                " NEXT " + ui.promptTextFormat("outline reset") + " To Load More..");

        switch (input){
            case 1:
                // something
                break;
            case 2:
                // something
                break;
            case 3:
                // Something
                break;
            case 4:
                accountSettings(username);
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
