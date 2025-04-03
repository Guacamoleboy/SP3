import util.*;

public abstract class Menu { // (Superclass)

    // Attributes
    private static FileIO io = new FileIO();
    private static TextUI ui = new TextUI();

    // ________________________________________________________

    public void startSession(String username){
        // Load / toggle data

        //Display data
        ui.displayMsg("\nWelcome to the Main Menu, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset") + "!");

        int input = ui.promptNumeric("\nPlease choose one of the following:\n1) Watch Movie\n2) Bookmarked Movies\n3) History\n4) Account Settings");

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

    public abstract void endSession();

    // ________________________________________________________

    public abstract void runMenuLoop();

    // ________________________________________________________

    public void accountSettings(String username){

        ui.displayMsg("Account settings for, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset") + ":");
        String input = ui.promptText("\n1) Change Username\n2) Change Password\n3) Something\n4) Something\n5) Something\n6) Pause Membership\n7) Set as account status to inactive\n9) Exit");
        switch (input.toLowerCase()){
            case "1", "change Username", "1) change Username":
                User u = Main.program.getUserByName(username);

        }



    }

}
