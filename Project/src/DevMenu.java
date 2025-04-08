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
        ui.displayMsg("\nPlease choose one of the following:\n1) Add Movie\n2) Add Series\n3) Ban menu\n4) Manage users\n5) Set" +
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
                admin.banMenu(username);
                break;
            case "4":
                admin.manageUsers(username);
                //accountSettings(username);
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
                Admin.forbiddenWords("data/bannedWords.csv", "word");
                break;
            case "next":

                ui.displayMsg("1) Change Membership");
                ui.displayMsg("\n\nType " + ui.promptTextFormat("outline")+
                " NEXT " + ui.promptTextFormat("outline reset") + " " + ui.promptTextFormat("outline") + " PREV " + ui.promptTextFormat("outline reset") +
                " To Load More | " + "Use " + ui.promptTextFormat("outline") + " BACK " + ui.promptTextFormat("outline reset") +
                " to return\n_________________________________");
                String in3 = ui.promptTextLine("Input: ");

                switch (in3){

                    case "1":
                        //
                        String in4 = ui.promptTextLine("Username: ");

                        for (User u : Program.user){

                            if(u.getName().equalsIgnoreCase(in4)){
                                ui.displayMsg("Users current membership: " + u.getMembership());
                                String membershipType = ui.promptTextLine("Membership: ");

                                if(u.getMembership().equalsIgnoreCase("normal") && membershipType.equalsIgnoreCase("normal")){
                                    ui.displayMsg("Can't set a normal membership to normal.");
                                } else if (u.getMembership().equalsIgnoreCase("premium") && membershipType.equalsIgnoreCase("premium")){
                                    ui.displayMsg("Can't set premium membership to premium");
                                } else if(u.getMembership().equalsIgnoreCase("normal") && membershipType.equalsIgnoreCase("Premium")){
                                    u.setMembership(membershipType);
                                } else if(u.getMembership().equalsIgnoreCase("premium") && membershipType.equalsIgnoreCase("normal")){
                                    u.setMembership(membershipType);
                                } else {
                                    ui.displayMsg("Invalid membership!");
                                }

                            } // If-end

                        } // For-each loop end

                        break;

                    case "next":
                        ui.displayMsg("\nYou're as far as you can get.");
                        break;
                    case "prev", "back":
                        startSession(username);
                        break;
                    default:
                        ui.displayMsg("Invalid input.");

                } // Switch end

                // Something
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
