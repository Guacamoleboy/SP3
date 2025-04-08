import util.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static util.TextUI.*;

public class Admin {

    // Attributes
    private static final FileIO io = new FileIO();
    private static final TextUI ui = new TextUI();
    public static ArrayList <String> bannedWords = new ArrayList<>();

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

    public static void manageUsers(String adminUsername){
        String choice = ui.promptText("\n1) Ban menu\n2) Change username\n3) Change password\n4) Change status\n5) User list\n\nType " + ui.promptTextFormat("outline") + " back " + ui.promptTextFormat("outline reset") +
                " to go back to Dev Menu..");
        switch(choice){
            case "1", "1)", "1) Ban menu", "ban":
                banMenu(adminUsername);
                break;
            case "2", "2)", "2) Change username":
                changeUsername(adminUsername);
                break;
            case "3", "3)", "3) Change password":
                changePassword(adminUsername);
                break;
            case "4", "4)", "4) Change status":
                changeStatus(adminUsername);
                break;
            case "list", "5", "5)", "5) User list":
                if (Program.user.isEmpty()){
                    ui.displayMsg("There are no users!");
                } else {
                    ui.displayMsg("This is the list of users: ");
                    for (User user : Program.user) {
                        String msg =
                        "ID: "+ user.getID() +
                        " Username: " + user.getName()+
                        " User is banned: " + user.getBanned()+
                        " User is: " + user.getStatus();

                        ui.displayMsg(msg);
                    }
                }
                manageUsers(adminUsername);
                return;
            case "back":
                Main.p.devmenu.startSession(adminUsername);
                break;
            default:
                System.out.println();
                break;
        }
        manageUsers(adminUsername);
    }

    // ________________________________________________________

    public static void listUsers(){
        for (User user : Program.user) {
            String msg =
            "ID: "+ user.getID() +
            " Username: " + user.getName()+
            " User is banned: " + user.getBanned()+
            " User is: " + user.getStatus();

            ui.displayMsg(msg);
        }
    }

    // ________________________________________________________

    public static void banMenu(String adminUsername){
        String choice = ui.promptText("\n1) ban by ID\n2) ban by username?\n3) unban by ID\n4) unban by username?\n5) list banned users\n\nType " + ui.promptTextFormat("outline") + " back " + ui.promptTextFormat("outline reset") +
                " to go back to Dev Menu..");
        User u = null;
        String userID;
        String username;
        switch(choice){
            case "1", "1)", "1) ban by ID":
                userID = ui.promptText("Enter the ID of the user you want to ban..");
                u = Main.p.getUserByID(userID);
                banUser(u);
                break;
            case "username", "2", "2)", "2) ban by username":
                username = ui.promptText("Enter the username of the user you want to ban..");
                u = Main.p.getUserByName(username);
                banUser(u);
                break;
            case "3", "3)", "3) unban by ID":
                userID = ui.promptText("Enter the ID of the user you want to unban..");
                u = Main.p.getUserByID(userID);
                unbanUser(u);
                break;
            case "4", "4)", "4) unban by username":
                username = ui.promptText("Enter the username of the user you want to unban..");
                u = Main.p.getUserByName(username);
                unbanUser(u);
                break;
            case "list", "5", "5)", "5) list banned users":
                ArrayList<String> bannedUsers = Program.getBannedUsers();
                if (bannedUsers.isEmpty()){
                    ui.displayMsg("There are no banned users!");
                } else {
                    ui.displayMsg("This is the list of banned users: ");
                    for (String user : bannedUsers) {
                        ui.displayMsg(user);
                    }
                }
                banMenu(adminUsername);
                return;
            case "back":
                manageUsers(adminUsername);
                break;
            default:
                System.out.println();
                break;
        }
        banMenu(adminUsername);
    }

    // ________________________________________________________

    public static void banUser(User u){
        if (u == null) {
            System.out.println("User does not exist");
            return;
        }

        String confirm = ui.promptText(ui.promptTextColor("red") + "Are you sure you want to ban: " + u.getName() + "?" + ui.promptTextColor("reset"));

        if (confirm.equalsIgnoreCase("yes")) {
            ui.displayMsg("You banned " + u.getName() + "!");
            u.banUser();
        }
    }

    // ________________________________________________________

    public static void unbanUser(User u){
        if (u == null) {
            System.out.println("User does not exist");
            return;
        }

        String confirm = ui.promptText(ui.promptTextColor("red") + "Are you sure you want to unban: " + u.getName() + "?" + ui.promptTextColor("reset"));

        if (confirm.equals("yes")) {
            u.unbanUser();
        }
    }

    // ________________________________________________________

    public static void changeUsername(String adminUsername){
        String choice = ui.promptText("\n1) Change username by ID\n2) Change username by username?\n3) list users\n\nType " + ui.promptTextFormat("outline") + " back " + ui.promptTextFormat("outline reset") +
                " to go back to Dev Menu..");
        User u = null;
        String userID;
        String username;
        String newusername;
        switch(choice){
            case "1", "1)", "1) Change username by ID":
                userID = ui.promptText("Enter the ID of the user you want to change.");
                u = Main.p.getUserByID(userID);
                break;
            case "username", "2", "2)", "2) Change username by username":
                username = ui.promptText("Enter the username of the user you want to change.");
                u = Main.p.getUserByName(username);
                break;
            case "list", "3", "3)", "3) List users":
                listUsers();
                changeUsername(adminUsername);
                return;
            case "back":
                manageUsers(adminUsername);
                break;
            default:
                break;
        }
        if (u == null) {
            ui.displayMsg("User does not exist");
            changeUsername(adminUsername);
            return;
        }
        
        newusername = ui.promptText("Enter the new username for "+u.getName()+":");
        if (newusername.isEmpty()) {
            ui.displayMsg("You must enter a username!");
            changeUsername(adminUsername);
            return;
        } else if (Main.p.getUserByName(newusername) != null) {
            ui.displayMsg("Username already exists!");
            changeUsername(adminUsername);
            return;
        }

        String confirm = ui.promptText(ui.promptTextColor("red") + "Are you sure you want to change " + u.getName() + "'s username to "+newusername+ "?" + ui.promptTextColor("reset"));

        if (confirm.equalsIgnoreCase("yes")) {
            ui.displayMsg("Username is updated!");
            u.setUsername(newusername);
        }
        changeUsername(adminUsername);
    }

    // ________________________________________________________

    public static void changePassword(String adminUsername){
        String choice = ui.promptText("\n1) Change password by ID\n2) Change password by username?\n3) list users\n\nType " + ui.promptTextFormat("outline") + " back " + ui.promptTextFormat("outline reset") +
                " to go back to Dev Menu..");
        User u = null;
        String userID;
        String username;
        String newpassword;
        switch(choice){
            case "1", "1)", "1) Change password by ID":
                userID = ui.promptText("Enter the ID of the user you want to change.");
                u = Main.p.getUserByID(userID);
                break;
            case "username", "2", "2)", "2) Change password by username":
                username = ui.promptText("Enter the username of the user you want to change.");
                u = Main.p.getUserByName(username);
                break;
            case "list", "3", "3)", "3) List users":
                listUsers();
                changePassword(adminUsername);
                return;
            case "back":
                manageUsers(adminUsername);
                break;
            default:
                break;
        }
        if (u == null) {
            ui.displayMsg("User does not exist");
            changePassword(adminUsername);
            return;
        }
        newpassword = ui.promptText("Enter the new password:");
        if (newpassword.isEmpty()) {
            ui.displayMsg("You must enter a username!");
            changePassword(adminUsername);
        }

        String confirm = ui.promptText(ui.promptTextColor("red") + "Are you sure you want to change " + u.getName() + "'s password to "+newpassword+ "?" + ui.promptTextColor("reset"));

        if (confirm.equalsIgnoreCase("yes")) {
            ui.displayMsg("Password is updated!");
            u.setPassword(newpassword);
        }
        changePassword(adminUsername);
    }

    // ________________________________________________________

    public static void changeStatus(String adminUsername){
        String choice = ui.promptText("\n1) Change status by ID\n2) Change status by username?\n3) list users\n\nType " + ui.promptTextFormat("outline") + " back " + ui.promptTextFormat("outline reset") +
                " to go back to Dev Menu..");
        User u = null;
        String userID;
        String username;
        String newstatus;
        switch(choice){
            case "1", "1)", "1) Change password by ID":
                userID = ui.promptText("Enter the ID of the user you want to change.");
                u = Main.p.getUserByID(userID);
                break;
            case "username", "2", "2)", "2) Change password by username":
                username = ui.promptText("Enter the username of the user you want to change.");
                u = Main.p.getUserByName(username);
                break;
            case "list", "3", "3)", "3) List users":
                listUsers();
                changeStatus(adminUsername);
                return;
            case "back":
                manageUsers(adminUsername);
                break;
            default:
                break;
        }
        if (u == null) {
            ui.displayMsg("User does not exist");
            changeStatus(adminUsername);
            return;
        }
        newstatus = ui.promptText("Enter the new status (Yes, No):");
        if (newstatus.isEmpty() &! (newstatus.equals("Yes") || newstatus.equals("No"))) {
            ui.displayMsg("You must enter: Yes or No!");
            changeUsername(adminUsername);
        }

        String confirm = ui.promptText(ui.promptTextColor("red") + "Are you sure you want to change " + u.getName() + "'s status to "+newstatus+ "?" + ui.promptTextColor("reset"));

        if (confirm.equalsIgnoreCase("yes")) {
            ui.displayMsg("Status is updated!");
            u.setStatus(newstatus.substring(0, 1).toUpperCase() + newstatus.substring(1));
        }
        changeStatus(adminUsername);
    }

    // ________________________________________________________

    public static void forbiddenWords() {

        String choice = ui.promptText("Do you want to ban or unban a harsh word? (ban/unban)");

        if (choice.equals("ban")) {

            String word = ui.promptText("What's the word you want to ban?");
            if (word != null) {
                banWord(word);
            }

        } else if(choice.equals("unban")) {

            String word = ui.promptText("What's the word you want to unban?");
            if (word != null) {
                unbanWord(word);
            }

        } else {
            ui.displayMsg("Invalid input. Please try again using ban / unban only.");
            forbiddenWords();
            return;
        }

        saveData("data/bannedWords.csv", "word");
    }

    // ________________________________________________________

    public static boolean bannedWords(String word) {
        
        if (bannedWords.stream().anyMatch(w -> w.equals(word.toLowerCase()))) {
            ui.displayMsg("Please don't use offensive words!\n");
            return true;
        }
        return false;
    }

    // ________________________________________________________

    public static String banWord(String word){
        bannedWords.add(word.toLowerCase());
        return "You banned the word: "+ word +"!\n";
    }

    // ________________________________________________________

    public static String unbanWord(String word){
        bannedWords.remove(word.toLowerCase());
        return "You unbanned the word: "+ word +"!\n";
    }

    // ________________________________________________________

    public static void saveData(String path, String header){

        ArrayList<String> tmpbannedWords = new ArrayList<>(bannedWords);
        io.saveData(tmpbannedWords, path, header);

    }

    // ________________________________________________________

    public static void loadBannedWords(String path){

        bannedWords = io.readData(path);

    }


} // Admin end
