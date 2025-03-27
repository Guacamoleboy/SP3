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

                createUser(username, userID, userAge, userGender, userBanned);
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

        String startSessionAnswer = ui.promptText("Do you have an account?");

        if(startSessionAnswer.equalsIgnoreCase("yes")){
            login();
        } else if (startSessionAnswer.equalsIgnoreCase("no")) {
            registerUser();
        } else if (startSessionAnswer.equalsIgnoreCase("y")) {
            login();
        } else if (startSessionAnswer.equalsIgnoreCase("dev")) {
            devLogin();
        } else if (startSessionAnswer.equalsIgnoreCase("admin")) {
            devLogin();
        } else if (startSessionAnswer.equalsIgnoreCase("n")) {
            registerUser();
        } else {
            System.out.println("Invalid input.. Please try again!");
            checkForAccount();
        }

    }

    // ________________________________________________________

    public void registerUser(){

        String playerName = ui.promptText("Please enter a username..");
        int playerAge = ui.promptNumeric("Please enter your age..");
        String playerGender = ui.promptText("Please enter your gender..");
        String playerBanned = "No";

        switch (ID){
            case 1:
                System.out.println("\nWow! First user ever.. Thank you so much.\n");
                break;
            case 100:
                System.out.println("You're our customer number 100! Thank you so much.");
                break;
            case 1000:
                System.out.println("There were 999 accounts before you signed up. On behalf of COMPANY - Thank you for being number 1000!");
                break;
        }

        // What if a user enters nothing? Blank. Or a number? We probably only want names.

        this.createUser(playerName, ID, playerAge, playerGender, playerBanned);


    }

    // ________________________________________________________

    public void devLogin(){ // Hidden login for devs & admins

        String devUser = ui.promptText("Developer login page\nEnter username:");
        String devPass = ui.promptText("Enter password:");

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

    public void createUser(String name, int ID, int age, String gender, String banned){

        User u = new User(name, ID, age, gender, banned);
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

        io.saveData(playerData, "data/userData.csv", "Name, ID, Age, Gender, Banned");
        ui.displayMsg("Program has saved data. Shutting down...");

    }

} // Program end
