import util.*;

public class Admin {

    // Attributes
    private static FileIO io = new FileIO();
    private static TextUI ui = new TextUI();

    // ________________________________________________________

    public Admin(){

    } // Constructor

    // ________________________________________________________

    public void changePassword(){

    }

    // ________________________________________________________

    public void changeUsername(){

    }

    // ________________________________________________________

    public void clearByID(){

    }

    // ________________________________________________________

    public static void banUser(String adminUsername){
        String choice = ui.promptText("\nban by ID or username?\n\nType " + ui.promptTextFormat("outline") + " back " + ui.promptTextFormat("outline reset") +
                " to go back to Dev Menu..");
        User u = null;
        switch(choice){
            case "ID":
                int userID = ui.promptNumeric("Enter the username of the user you want to ban..");
                u = Main.p.getUserByID(userID);
                break;
            case "Username":
                String username = ui.promptText("Enter the username of the user you want to ban..");
                u = Main.p.getUserByName(username);
                break;
            case "back":
                Main.p.devmenu.startSession(adminUsername);
                break;
            default:
                System.out.println();
                break;
        }

        if (u == null) {
            System.out.println("User does not exist");
            Main.p.devmenu.startSession(adminUsername);
            return;
        }

        String confirm = ui.promptText(ui.promptTextColor("red") + "Are you sure you want to ban: " + u.getName() + "?" + ui.promptTextColor("reset"));

        if (confirm.equals("yes")) {
            u.banUser();
        } else {
            Main.p.devmenu.startSession(adminUsername);
        }
    }

} // Admin end
