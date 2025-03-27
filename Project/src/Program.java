import java.util.*;

public class Program {

    // Attributes
    private static TextUI ui = new TextUI();
    private static FileIO io = new FileIO();

    private String name;
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
        ui.displayMsg("Welcome to " + this.name + ".");

        // Allows us to keep adding accounts. Increases ID by 1 each time.
        int maxID = 0;

        if (!data.isEmpty()){

            for (String s : data){

                String[] values = s.split(",");
                String username = values[0].trim();
                int userID = Integer.parseInt(values[1].trim());
                int userAge = Integer.parseInt(values[2].trim());
                String userGender = values[3].trim();
                String userBanned = values[4].trim();
                String userPassword = values[5].trim();

                createUser(username, userID, userAge, userGender, userBanned, userPassword);
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

        String startSessionAnswer = ui.promptText("Do you have an account?").toLowerCase();

        switch (startSessionAnswer){
            case "yes":
                login();
                break;
            case "y":
                login();
                break;
            case "no":
                registerUser();
                break;
            case "n":
                registerUser();
                break;
            case "dev":
                devLogin();
                break;
            case "admin":
                devLogin();
                break;
            default:
                checkForAccount();
        }

    }

    // ________________________________________________________

    public void registerUser(){

        String playerName = ui.promptText("Please enter a username..");

        // Don't allow blank or invalid usernames
        if(playerName.isBlank() || !playerName.matches("[a-zA-Z]+")){
            ui.displayMsg("Invalid username.. Please only use alphabetic characters!");
            return;
        }

        // Put this into the while loop over passwordTest if you want it to ask user to redo entire password
        String playerPassword = ui.promptText("Please enter a password..");
        boolean passwordTest = false;

        // Allows us to loop over the password part
        while(!passwordTest){

            passwordTest = ui.promptPasswordConfirmation(playerPassword);

            if(!passwordTest){
                ui.displayMsg("\nPasswords don't match.. Try again.\n");
            }

        }

        int playerAge = ui.promptNumeric("Please enter your age..");
        String playerGender = ui.promptText("Please enter your gender..");
        String playerBanned = "No";

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

        this.createUser(playerName, ID, playerAge, playerGender, playerBanned, playerPassword);

        ui.displayMsg("\nThanks for making an account. Sending you to login page..\n");

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

        String playerUser = ui.promptText("Please log in!\nUsername:");
        String playerPass = ui.promptText("Password:");

    }

    // ________________________________________________________

    public void banUser(int ID){

        // Allow a specific ID to be banned by dev & admin

    }

    // ________________________________________________________

    public void createUser(String name, int ID, int age, String gender, String banned, String password){

        User u = new User(name, ID, age, gender, banned, password);
        user.add(u);

    }

    // ________________________________________________________

    public void displayUsersDev(){ // Debug & Dev only

        for(User u : user){
            System.out.println(u);
        }

    }

    // ________________________________________________________

    public void runProgramLoop(){

    }

    // ________________________________________________________

    public void endSession(){

        ArrayList <String> playerData = new ArrayList<>();

        for(User u : user){

            String s = u.toCSV();
            playerData.add(s);

        }

        io.saveData(playerData, "data/userData.csv", "Name, ID, Age, Gender, Banned, Password");
        ui.displayMsg("Program has saved data. Shutting down...");

    }

} // Program end
