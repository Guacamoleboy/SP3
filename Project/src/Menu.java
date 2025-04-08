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
        String movieOptions = "";

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
                        accountSettings(username);
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
        ui.displayMsg("\nAccount settings for, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset") + ":");
        ui.displayMsg("\n1) Change Username\n2) Change Password\n3) Pause Membership\n4) Take a Break\n5) Change Membership\n6) Back");
        ui.displayMsg("_________________________\n");
        String input = ui.promptTextLine("Input: ");

        switch (input.toLowerCase()){
            case "1", "change Username", "1) change Username":
                user.changeUsername(username);
                break;
            case "2", "change Password", "2) change Password":
                user.changePassword();
                break;
            case "3", "Pause", "3) Pause Membership":
                //user.pauseMembership();
                break;
            case "4", "break", "4) Take a Break":
                ui.displayMsg("Status changed to 'inactive'!");
                user.changeStatus(username);
                break;
            case "5", "change", "5) Change Membership":

                ui.displayMsg("\n1) Upgrade Membership\n2) Downgrade Membership\n3) Back");
                String input2 = ui.promptTextLine("Input: ");

                switch (input2){
                    case "1":

                        for(User u : Program.user){

                            if(u.getName().equalsIgnoreCase(username)){

                                ui.displayMsg("Your current membership: " + u.getMembership());

                                if(u.getMembership().equalsIgnoreCase("Normal")){
                                    ui.displayMsg("\n1) Premium");
                                    String in = ui.promptTextLine("Input: ");

                                    if(in.equalsIgnoreCase("1") || in.equalsIgnoreCase("premium")){
                                        u.setMembership("Premium");
                                    }
                                }

                                if(u.getMembership().equalsIgnoreCase("Premium")){
                                    ui.displayMsg("Can't upgrade any further! You're maxed out.");
                                    accountSettings(username);
                                }

                                accountSettings(username);

                            }

                        } // For-each end

                        break;

                    case "2":

                        for (User u : Program.user){

                            if(u.getName().equalsIgnoreCase(username)){

                                ui.displayMsg("Your current membership: " + u.getMembership());

                                if(u.getMembership().equalsIgnoreCase("Premium")){

                                    ui.displayMsg("1) Normal");
                                    String in2 = ui.promptTextLine("Input: ");

                                    if(in2.equalsIgnoreCase("1") || in2.equalsIgnoreCase("normal")){
                                        u.setMembership("Normal");
                                    }

                                    accountSettings(username);

                                }

                            }

                        } // For-each loop end


                        break;
                    default:
                        ui.displayMsg("Invalid input.");
                        accountSettings(username);
                }

                break;
            case "6", "back", "6) back":
                startSession(username);
                break;
            default:
                ui.displayMsg("Invalid input");
                break;
        }
        Main.p.mainmenu.startSession(user.getName());

    }

}
