import util.*;

import java.util.ArrayList;

import static util.TextUI.banWord;
import static util.TextUI.unbanWord;

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
                String userID = ui.promptText("Enter the username of the user you want to ban..");
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


    public static void forbiddenWords() {
        String choice = ui.promptText("Do you want to ban or unban a harsh word? (ban/unban)");
        if (choice.equals("ban")) {
            String word = ui.promptText("What's the word you want to ban?");
            if (word != null) {
                banWord(word);
            }
        }else if(choice.equals("unban")) {
            String word = ui.promptText("What's the word you want to unban?");
            if (word != null) {
                unbanWord(word);
            }

        }
    }

} // Admin end
