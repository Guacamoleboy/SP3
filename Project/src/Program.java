import util.FileIO;
import util.TextUI;

import java.util.*;

public class Program {

    // Attributes
    private static TextUI ui = new TextUI();
    private static FileIO io = new FileIO();

    private String name;
    private String startSessionAnswer;
    private String gender;
    private String banned;
    private int ID;
    private int age;
    private int userCount = 0;
    private ArrayList <User> user;
    private User currentUser;

    // ________________________________________________________

    public Program(String name){

        this.name = name;
        this.user = new ArrayList<>();

    } // Constructor

    // ________________________________________________________

    public void startSession(){

        ArrayList <String> data = io.readData("data/userData.csv");
        ui.displayMsg("\nWelcome to " + this.name + ".\n");

        // Allows us to keep adding accounts. Increases ID by 1 each time.
        int maxID = 0;

        if (!data.isEmpty()){

            for (String s : data){

                String[] values = s.split(",");
                String username = values[0].trim();
                int userID = Integer.parseInt(values[1].trim());
                int userAge = Integer.parseInt(values[2].trim());
                String userGender = values[3].trim();
                String userPassword = values[4].trim();
                String userBanned = values[5].trim();
                String userStatus = values[6].trim();

                createUser(username, userID, userAge, userGender, userPassword, userBanned, userStatus);
                ID++;
                userCount++;

                // Latest ID = maxID. This allows us to keep going from the last one saved.
                if (userID > maxID) {
                    maxID = userID;
                }

            } // For-loop

        } // If-statement

        userCount = user.size();
        ID = maxID + 1;

        checkForAccount();

    }

    // ________________________________________________________

    public void checkForAccount(){

        startSessionAnswer = ui.promptText("Do you have an account?").toLowerCase();

        switch (startSessionAnswer){
            case "y", "yes", "yea", "yup", "yeah", "ya", "yessir", "yur":
                login();
                break;
            case "n", "no", "na", "nah", "nope":
                registerUser();
                break;
            case "dev", "admin", "administrator", "developer":
                devLogin();
                break;
            default:

                boolean suggest = ui.promptBinary("\nWrong input.. Do you think it's a valid input?");

                if(suggest){
                    ui.displayMsg("\nThanks for your suggestion.. We will check it soon!\n");
                    saveDataSuggest();
                }

                checkForAccount();

        }

    }

    // ________________________________________________________

    public void registerUser(){

        // Currently only supports normal characters. Maybe make it so numbers and symbols are included?
        String playerName = ui.promptText("Please enter a username..");

        // Don't allow blank or invalid usernames
        if(playerName.isBlank() || !playerName.matches("[a-zA-Z0-9]+")){
            ui.displayMsg("Invalid username.. Please only use alphabetic characters!\n");
            registerUser(); // Recursion
        }

        //Checks if username is already taken
        for(User u : user) {
            if (playerName.equalsIgnoreCase(u.getName())) {
                ui.displayMsg("Account name already exist.. Please choose another!\n");
                registerUser();
            }
        }

        // Put this into the while loop over passwordTest if you want it to ask user to redo entire password
        String playerPassword = ui.promptText("Please enter a password..");
        boolean passwordTest = false;

        // Allows us to loop over the password part
        while(!passwordTest){

            System.out.print(ui.promptTextColor("red"));
            passwordTest = ui.promptPasswordConfirmation(playerPassword);
            System.out.print(ui.promptTextColor("reset"));

            if(!passwordTest){
                ui.displayMsg("\nPasswords don't match.. Try again.\n");
            }

        }

        String playerGender = ui.promptGender("Please enter your gender..");
        int playerAge = ui.promptNumeric("Please enter your age..");
        String playerBanned = "No";
        String playerStatus = "Active";

        switch (ID){
            case 1:
                ui.displayMsg("\nWow! First user ever.. Thank you so much.\n");
                break;
            case 100:
                ui.displayMsg("You're our customer number 100! Thank you so much.");
                break;
            case 1000:
                ui.displayMsg("There were 999 accounts before you signed up. On behalf of COMPANY - Thank you for being number 1000!");
                break;
            case 10000:
                ui.displayMsg("You are account number 10.0000! Amazing. Thank you so much for being part of our journey.");
                break;
        }

        createUser(playerName, ID, playerAge, playerGender, playerPassword, playerBanned, playerStatus);

        ui.displayMsg("\nThanks for making an account. Sending you to login page..\n");

        // Saves our data
        saveData();

        // I think it's great having some sort of slowdown here
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Error. Contact a developer..");
        }

        // Sending user to login method
        login();

    }

    // ________________________________________________________

    public void devLogin(){ // Hidden login for devs & admins

        String devUser = ui.promptText("\nDeveloper login page\nEnter username:");
        String devPass = "";

        if(devUser.equalsIgnoreCase("dev") || devUser.equalsIgnoreCase("admin")){
            devPass = ui.promptText("\nHello, " + devUser + "\nEnter password:");

            if(devUser.equalsIgnoreCase("dev") && devPass.equalsIgnoreCase("dev")){
                // Forward to dev menu
                System.out.println("Dev access gained"); // Placeholder & Debug
            }

            if(devUser.equalsIgnoreCase("admin") && devPass.equalsIgnoreCase("admin")){
                // Forward to dev menu
                System.out.println("Dev access gained"); // Placeholder & Debug
            }

        }

        /*
        // Allows devs to try again if failed
        ui.promptBinary("Access denied. Try again?");
        */
    }

    // ________________________________________________________

    public void login(){ // All users except devs & admins

        // Have to use while loop else it'll infinite loop
        while(true) {

            String playerUser = ui.promptText("\nPlease log in!\nUsername:");

            // Checks if username exist
            if (!usernameCheck(playerUser)) {
                System.out.println("\nAccount not found..");
                continue;
            }

            String playerPass = ui.promptText("Password:");

            // Checks if username and password match
            if (!passwordCheck(playerPass, playerUser)) {
                System.out.println("\nPassword doesn't match the username..");
                continue;
            }

            ui.displayMsg("\nWelcome, " + playerUser + "! Loading Main Menu..");
            //menu.mainMenu();
            break;

        } // While end

    }

    // ________________________________________________________

    public boolean usernameCheck(String username){

        String path = "data/userData.csv";
        ArrayList <String> data = io.readData(path);

        for (String s : data){
            String[] values = s.split(", ");
            if(values[0].equals(username)){
                return true;
            }
        }

        return false;
    }

    // ________________________________________________________

    public boolean passwordCheck(String password, String username){

        String path = "data/userData.csv";
        ArrayList <String> data = io.readData(path);

        for (String s : data){
            String[] values = s.split(", ");
            if(values[0].equals(username) && values[4].equals(password)){
                return true;
            }
        }

        return false;
    }

    // ________________________________________________________

    public void banUser(int ID){

        // Allow a specific ID to be banned by dev & admin

    }

    // ________________________________________________________

    public void createUser(String username, int ID, int age, String gender, String password, String banned, String status){

        User u = new User(username, ID, age, gender, password, banned, status);
        user.add(u);

    }

    // ________________________________________________________

    public void displayUsersDev(){ // Debug & Dev only

        for(User u : user){
            System.out.println(u);
        }

    }

    // ________________________________________________________

    public void saveData(){

        ArrayList <String> playerData = new ArrayList<>();

        for(User u : user){

            String s = u.toCSV();
            playerData.add(s);

        }

        io.saveData(playerData, "data/userData.csv", "Username, ID, Age, Gender, Password, Banned, Status");

        //ui.displayMsg("Program has saved data."); || DEBUG

    }

    // ________________________________________________________

    public void saveDataSuggest(){

        ArrayList <String> suggestData = new ArrayList<>();
        int suggestID = 0;
        String added = "No";

        for(User u : user){

            suggestID++;
            String s = u.toCSVSuggest(startSessionAnswer, suggestID, added);
            suggestData.add(s);

        }

        io.saveData(suggestData, "data/suggestPrompts.csv", "Value, ID, Added");

        //ui.displayMsg("Suggestion Prompt has saved data."); || DEBUG

    }

} // Program end
