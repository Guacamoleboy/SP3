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

        ui.displayMsg("\nPlease choose one of the following:\n1) Movies\n2) Series\n3) History\n4) Account Settings\n_____________________");
        String input = ui.promptTextLine("Input: ");
        String newInput = "";
        String seriesInput = "";

        switch (input){
            case "1":

                newInput = ui.promptText("\nPlease choose a movie:\n1) Noget\n2) Noget\n3) Noget\n4) Noget\n5) Noget\n6) Noget\n7) Noget\n8) Noget\n9) Noget" +
                        " \n\nType " + ui.promptTextFormat("outline")  +" BACK "+ ui.promptTextFormat("outline reset")  + " to go back.");


                // For loop of loaded data instead of switch case
                switch (newInput){
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5":
                        break;
                    case "6":
                        break;
                    case "7":
                        break;
                    case "8":
                        break;
                    case "9":
                        break;
                    case "back":
                        startSession(username);
                        break;
                    default:
                        ui.displayMsg("Invalid input. Try again.");
                        break;
                }

                break;
            case "2":

                seriesInput = ui.promptText("\nPlease choose a series:\n1) Noget\n2) Noget\n3) Noget\n4) Noget\n5) Noget\n6) Noget\n7) Noget\n8) Noget\n9) Noget" +
                        " \n\nType " + ui.promptTextFormat("outline")  +" BACK "+ ui.promptTextFormat("outline reset")  + " to go back.");

                switch (seriesInput){
                    case "1":
                        // Something
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5":
                        break;
                    case "6":
                        break;
                    case "7":
                        break;
                    case "8":
                        break;
                    case "9":
                        break;
                    case "back":
                        startSession(username);
                        break;
                    default:
                        ui.displayMsg("Invalid input. Try again.");
                        break;
                }

                break;
            case "3": //History here :-)
                // Something abt. History
                //Remember to use for loop for loaded data.
                break;
            case "4":
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

        User user = Main.p.getUserByName(username);
        ui.displayMsg("Account settings for, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset") + ":");
        String input = ui.promptText("\n1) Change Username\n2) Change Password\n3) Something\n4) Something\n5) Something\n6) Pause Membership\n7) Set as account status to inactive\n9) Exit");
        switch (input.toLowerCase()){
            case "1", "change Username", "1) change Username":
                user.changeUsername(username);
                break;
            case "2", "change Password", "2) change Password":
                user.changePassword();
                break;


        }
        Main.p.mainmenu.startSession(user.getName());

    }

}
