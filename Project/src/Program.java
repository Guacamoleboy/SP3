import util.FileIO;
import util.TextUI;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Program {

    // Attributes
    private static TextUI ui = new TextUI(Main.exitWord);
    private static FileIO io = new FileIO();
    protected MainMenu mainmenu = new MainMenu();
    protected DevMenu devmenu = new DevMenu();
    protected SupportMenu supMenu = new SupportMenu();
    protected static Random random = new Random();

    private String programName;
    private String playerUser;
    private String userName;
    private String startSessionAnswer;
    private String gender;
    private String banned;
    private String ID;
    private int age;
    private int userCount = 0;
    public static ArrayList <User> user;
    public User currentUser;
    // ________________________________________________________

    public Program(String programName){

        this.programName = programName;
        this.user = new ArrayList<>();

    } // Constructor

    // ________________________________________________________

    public void startSession(){

        ArrayList <String> data = io.readData("data/userData.csv");
        ui.loadBannedWords("data/bannedWords.csv");
        ui.displayMsg("\nWelcome to " + this.programName + ".\n");

        if (!data.isEmpty()){

            for (String s : data){

                String[] values = s.split(",");
                String username = values[0].trim();
                String userID = values[1].trim();
                int userAge = Integer.parseInt(values[2].trim());
                String userGender = values[3].trim();
                String userPassword = values[4].trim();
                String userBanned = values[5].trim();
                String userStatus = values[6].trim();
                String userIP = values[7].trim();
                String userEmail = values[8].trim();
                String userMembership = values[9].trim();
                String lastLogin = values[10].trim();
                createUser(username, userID, userAge, userGender, userPassword, userBanned, userStatus, userIP, userEmail, userMembership, lastLogin);
                userCount++;

            } // For-loop

        } // If-statement

        checkForAccount();

    }

    // ________________________________________________________

    public void checkForAccount(){

        ui.displayMsg("Do you have an account?");
        startSessionAnswer = ui.promptTextLine("Input: ").toLowerCase();

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
            case "support":
                supportLogin();
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
        ui.displayMsg("Please enter a username..");
        String playerName = ui.promptTextLine("Input: ");

        if(!usernameNotAllowed(playerName)){
            registerUser();
        }

        // Don't allow blank or invalid usernames
        if(playerName.isBlank() || !playerName.matches("[a-zA-Z0-9]+")){
            ui.displayMsg("Invalid input. Please only use A-Z and Numbers!");
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
        String playerPassword = "";

        while(true) {

            ui.displayMsg("Please enter a password..");
            playerPassword = ui.promptTextLine("Input: ");

            if (playerPassword.isBlank() || !playerPassword.matches("[a-zA-Z0-9]+")) {
                ui.displayMsg(ui.promptTextColor("red") + "Invalid Password input." + ui.promptTextColor("reset") + "\nPlease try again!");
                continue;
            }

            break;
        }

        boolean passwordTest = false;

        // Allows us to loop over the password part
        while(!passwordTest){

            ui.displayMsg(ui.promptTextColor("red"));
            passwordTest = ui.promptPasswordConfirmation(playerPassword);
            ui.displayMsg(ui.promptTextColor("reset"));

            if(!passwordTest){
                ui.displayMsg("\nPasswords don't match.. Try again.\n");
            }

        }

        String playerEmail = ui.promptEmail("Please enter your email..");

        //Checks if email is already being used
        for(User u : user) {
            if (playerEmail.equalsIgnoreCase(u.getEmail())) {
                ui.displayMsg("\nEmail already exist.. Forgot username?..\n_________________________________________");
                boolean value = ui.promptBinaryLine("Input: ");
                if(value){
                    String userEmail = ui.promptTextLine("Please enter your email: ");
                    if(!emailCheck(userEmail)){
                        ui.displayMsg("Email not found..");
                    }
                    EmailConfirmation.sendPassword(userEmail);
                    // Visuals
                    ui.displayMsg("Username has been sent to " + ui.promptTextFormat("outline") + " " + userEmail + " "
                    + ui.promptTextFormat("outline reset"));
                }
                registerUser();
            }
        } // Email checker end

        String playerGender = ui.promptGender("Please enter your gender..");

        int playerAge;

        while(true) {

            playerAge = ui.promptNumeric("Please enter your age..");

            if (playerAge < 12 || playerAge > 120) {
                ui.displayMsg("Invalid age. Please try again");
                continue;
            }

            break;

        } // While end

        String playerBanned = "No";
        String playerStatus = "Active";

        // Gets user IP adress and add to userData.csv
        String ipAdress = "unknown";
        try {
            InetAddress inetAdress = InetAddress.getLocalHost();
            ipAdress = inetAdress.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        User existingUser = getUserByIP(ipAdress);
        if (existingUser != null) {
            ui.displayMsg("\nCan't have two accounts on the same IP adress!");
            login();
            return;
        }

        // IP Add
        ID = randomID();
        idCheck(ID);

        // All members have Normal as membership
        String playerMembership = "Normal";
        String lastLogin = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        createUser(playerName, ID, playerAge, playerGender, playerPassword, playerBanned, playerStatus, ipAdress, playerEmail, playerMembership, lastLogin);

        ui.displayMsg("\nThanks for making an account. Sending you to login page..\n");

        // Saves our data
        saveData();

        // I think it's great having some sort of slowdown here
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            ui.displayMsg("Error contact a developer" + " | Dev msg: " + e.getMessage());
        }*/
        sleep(2000);

        // Sending user to login method
        login();

    }

    // ________________________________________________________

    public void supportLogin(){

        ui.displayMsg("\n____________________\nSupport login page.");
        String supUser = ui.promptTextLine("Username: ");
        String supPass = "";

        if(supUser.equalsIgnoreCase("support")){

            ui.displayMsg("\nHello, " + ui.promptTextColor("red") + supUser + ui.promptTextColor("reset") + "!");
            supPass = ui.promptTextLine("Password: ");

            if(supUser.equalsIgnoreCase("support") && supPass.equals("SupportPassword123")){

                supMenu.startSession(supUser);

            } else {

                ui.displayMsg("Wrong input. Try again.");
                supportLogin();

            } // If-end (INNER)

        } // If-end (OUTTER)

    }

    // ________________________________________________________

    public void devLogin(){ // Hidden login for devs & admins

        /*

            Values are pretty much final hence not going in userData.csv

        */

        String devUser = ui.promptText("\nDeveloper login page\nEnter username:");
        String devPass = "";

        if(devUser.equalsIgnoreCase("dev") || devUser.equalsIgnoreCase("admin")){
            devPass = ui.promptText("\nHello, " + ui.promptTextColor("red") + devUser + ui.promptTextColor("reset") + "!\nEnter password:");

            if(devUser.equalsIgnoreCase("dev") && devPass.equalsIgnoreCase("dev")){
                // Forward to dev menu
                System.out.println("Dev access gained"); // Placeholder & Debug
            }

            if(devUser.equalsIgnoreCase("admin") && devPass.equalsIgnoreCase("admin")){
                // Forward to dev menu
                System.out.println("\nDev access gained"); // Placeholder & Debug
            }

        }

        devmenu.startSession(devUser);
    }

    // ________________________________________________________

    public void login(){ // All users except devs & admins

        playerUser = "";
        int counter = 0;

        // Have to use while loop else it'll infinite loop
        while(true) {

            if(counter <= 3) {

                // Visuals
                ui.displayMsg("\nPlease log in!\n\nForgot something? " + ui.promptTextFormat("outline") + " PASSWORD " + ui.promptTextFormat("outline reset")
                        + " " + ui.promptTextFormat("outline") + " USERNAME " + ui.promptTextFormat("outline reset") + " " + ui.promptTextFormat("outline")
                        + " EMAIL " + ui.promptTextFormat("outline reset") + "\n___________________________");

                playerUser = ui.promptTextLine("Enter Username: ");

                // __________________________ PASSWORD ________________________________

                if(playerUser.equalsIgnoreCase("password")){

                    String userEmail = "";

                    while(true) {

                        userEmail = ui.promptTextLine("Please enter your email: ");

                        if (!emailCheck(userEmail)) {
                            ui.displayMsg("Email not found..");
                            continue;
                        }

                        break;

                    }

                    EmailConfirmation.sendPassword(userEmail);

                    // Visuals
                    ui.displayMsg("\nPassword has been sent to " + ui.promptTextFormat("outline") + " " + userEmail + " "
                            + ui.promptTextFormat("outline reset"));

                    // Confirmation code for password
                    String confirmCode = ui.promptTextLine("Please enter confirmation code: ");

                    if(!HashMapStorage.validation(confirmCode)){
                        ui.displayMsg("Incorrect confirmation code..");
                    }

                    ui.displayMsg("Confirmation code accepted..\n_____________________");
                    String newPasswordInput = ui.promptTextLine("Please enter a new password: ");
                    if(!ui.promptPasswordConfirmation(newPasswordInput)){
                        ui.displayMsg("Passwords don't match.");
                    }

                    // SET PASSWORD HERE

                    for(User u : user){

                        if(u.getEmail().equalsIgnoreCase(userEmail)){
                            u.setPassword(newPasswordInput);
                            ui.displayMsg(ui.promptTextColor("green") + "\nPassword changed.. Sending you back to login!" + ui.promptTextColor("reset"));
                            login();
                        }

                    }

                    // Delay for visual approach
                    sleep(2000);

                    login();

                    // __________________________ USERNAME ________________________________

                } else if (playerUser.equalsIgnoreCase("username")){

                    String userEmail = ui.promptTextLine("Please enter your email: ");

                    if(!emailCheck(userEmail)){
                        ui.displayMsg("Email not found..");
                    }

                    String userPassword = ui.promptTextLine("Please enter password: ");

                    for(User u : user){

                        if(userEmail.equalsIgnoreCase(u.getEmail()) && userPassword.equalsIgnoreCase(u.getPassword())){
                            ui.displayMsg("\nYour username is: " + ui.promptTextColor("green") + u.getName() + ui.promptTextColor("reset"));
                        }

                    }

                    sleep(1000);
                    login();

                    // __________________________ EMAIL ________________________________

                } else if (playerUser.equalsIgnoreCase("email")){

                    ui.displayMsg("\nIn order to reset your email you must contact support..\n1) Contact Support\n2) Exit");
                    String emailPromptInput = ui.promptTextLine("Input: ");
                    switch (emailPromptInput){

                        case "1":

                            String userInput = "";
                            String passInput = "";

                            while(true){

                                userInput = ui.promptTextLine("Please enter your username: ");
                                passInput = ui.promptTextLine("Please enter your password: ");

                                    if(!passwordCheck(userInput, passInput)){
                                        ui.displayMsg("Doesn't match an existing user. Try again.");
                                        continue;
                                    }

                                break;

                            }

                            String emailResetMsg = ui.promptTextLine("Message: ");
                            int rndID = (int) random.nextInt(10000);

                            for(User u : user){

                                if(u.getName().equalsIgnoreCase(userInput) && u.getPassword().equalsIgnoreCase(passInput)){
                                    saveDataTicket(ticketID(rndID), u.getID(), emailResetMsg, "Open", "No response yet", "N/A");
                                    ui.displayMsg("\nWe have received your ticket. We will contact you within 48 hours. Thank you for your patience!");
                                    ui.displayMsg("You can see your open tickets under your Account Settings in your Menu when logged in!");
                                }

                            }

                            login();


                        case "2":
                            ui.displayMsg("Shutting down.");
                            System.exit(0);
                        default:
                            ui.displayMsg("Invalid input.");

                    }

                } else {

                    // Checks if username exist
                    if (!usernameCheck(playerUser)) {
                        ui.displayMsg("\nAccount not found..");
                        counter++;
                        continue;
                    }

                }

                String playerPass = ui.promptTextLine("Enter Password: ");

                // sets to 0 if passed username prompt
                counter = 0;

                // Checks if username and password match
                if (!passwordCheck(playerPass, playerUser)) {
                    ui.displayMsg("\nPassword doesn't match the username..");
                    counter++;
                    continue;
                }

                // Checks if user is banned
                for(User u : user) {

                    String bannedStatus = u.getBanned();

                    if (playerUser.equalsIgnoreCase(u.getName()) && bannedStatus.equalsIgnoreCase("Yes")) {
                        ui.displayMsg("\nAccount is "+ ui.promptTextColor("red") + "banned" + ui.promptTextColor("reset")
                        + ". Contact support if you think it's unjustified.\n");
                        ui.displayMsg("_____________________________\n1) Write a Ticket\n2) Close Program\n");
                        String banInput = ui.promptTextLine("Input: ");

                        switch (banInput){

                            case "1":
                                ui.displayMsg("Please enter your message under here\n____________________________ ");
                                String bannedMsg = ui.promptTextLine("Message: ");
                                int ticketID = (int) random.nextInt(10000);
                                String ticketStatus = "Open";

                                // Check if user already has a ticket

                                if(!ticketIDCheck(u.getID())){
                                    ui.displayMsg("You already have an open ticket.\n1) Show Ticket\n2) Back");
                                    String idReviewInput = ui.promptTextLine("Input: ");
                                    switch (idReviewInput){

                                        case "1":
                                            // Something
                                            break;
                                        case "2":
                                            login();
                                            break;
                                        default:
                                            ui.displayMsg("Invalid input");
                                    }


                                }

                                ui.displayMsg("\nTicket sent..");
                                String s1 = "No response yet";
                                String s2 = "N/A";

                                // Saves data if code passed all tests
                                saveDataTicket(ticketID, u.getID(), bannedMsg, ticketStatus, s1, s2);

                                login();

                            case "2":
                                ui.displayMsg("Thanks for your understanding. Have a great day!");
                                break;
                            case "bananflue":
                                System.exit(0);
                            default:
                                ui.displayMsg("Invalid input.");

                        }

                        break;

                    }

                } // Ban checker end

                ui.displayMsg("\nWelcome, " + ui.promptTextColor("red") +playerUser + ui.promptTextColor("reset") + "! Loading Main Menu..");
                ui.displayMsg("______________________________________");

                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    ui.displayMsg("Error. Contact a dev...");
                }*/

                sleep(1000);
                currentUser = Main.p.getUserByName(playerUser);
                mainmenu.startSession(playerUser);
                break;

            }

            ui.displayMsg("________________________________________\n\nToo many fail attempts. Shutting down...");

            sleep(2000);
            System.exit(0);

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

    public boolean ticketIDCheck(String ID){

        String path = "data/supportTickets.csv";
        ArrayList <String> data = io.readData(path);

        for (String s : data){
            String[] values = s.split(", ");

            if(values[1] == ID){
                ui.displayMsg("You already have a ticket open.. Please wait for a response!");
                return true;
            }

        } // For-loop end

        return true;

    }

    // ________________________________________________________

    public int ticketID(int ID){

        String path = "data/supportTickets.csv";
        ArrayList <String> data = io.readData(path);
        int finalID;

        for (String s : data){
            String[] values = s.split(", ");

            int checkID = Integer.parseInt(values[0].trim());

            if(checkID == ID){
                return (int) random.nextInt(10000);
            }

        }

        return ID;
    }

    // ________________________________________________________

    public boolean emailCheck(String email){

        String path = "data/userData.csv";
        ArrayList <String> data = io.readData(path);

        for (String s : data){
            String[] values = s.split(", ");
            if(values[8].equals(email)){
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

    public String idCheck(String ID){

        String path = "data/userData.csv";
        ArrayList <String> data = io.readData(path);
        ID = randomID();

        for (String s : data){

            String[] values = s.split(", ".trim());
            if(values[1].equals(ID)){
                randomID();
            }

        } // For-loop end

        return ID;

    }

    // ________________________________________________________

    public void banUser(int ID){

        // Allow a specific ID to be banned by dev & admin

    }

    // ________________________________________________________

    public void createUser(String username, String ID, int age, String gender, String password, String banned, String status, String ipAdress, String email, String membership, String lastLogin){


        User u = new User(username, ID, age, gender, password, banned, status, ipAdress, email, membership, lastLogin);
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

        io.saveData(playerData, "data/userData.csv", "Username, ID, Age, Gender, Password, Banned, Status, IP, Email, LastLogin");

        //ui.displayMsg("Program has saved data."); || DEBUG

    }

    // ________________________________________________________

    public void saveDataSuggest(){

        ArrayList <String> suggestData = new ArrayList<>();
        int suggestID = 0;
        String added = "No";

        suggestID++;
        String s = toCSVSuggest(startSessionAnswer, suggestID, added);
        suggestData.add(s);

        io.saveData(suggestData, "data/suggestPrompts.csv", "Value, ID, Added");

        //ui.displayMsg("Suggestion Prompt has saved data."); || DEBUG

    }

    // ________________________________________________________

    public static void saveDataTicket(int ticketID, String userID, String msg, String status, String response, String responseBy){

        ArrayList <String> ticketData = new ArrayList<>();

        String s = toCSVTicket(ticketID, userID, msg, status, response, responseBy);
        ticketData.add(s);
        io.saveData(ticketData, "data/supportTickets.csv", "Ticket ID, User ID, Message, Status, Response, Response by");

    }

    // ________________________________________________________

    private static String toCSVSuggest(String value, int ID, String added){

        return value + ", " + ID + ", " + added;

    }

    // ________________________________________________________

    private static String toCSVTicket(int ticketID, String ID, String status, String msg, String response, String responseBy){

        return ticketID + ", " + ID + ", " + status + ", " + msg + ", " + response + ", " + responseBy;

    }

    // ________________________________________________________

    public User getUserByName(String username) {
        User u = user.stream().filter(s -> s.getName().equals(username)).findFirst().orElse(null);
        return u;
    }

    // ________________________________________________________

    public User getUserByID(String ID) {
        User u = user.stream().filter(s -> (s.getID().equals(ID))).findFirst().orElse(null);
        return u;
    }

    // ________________________________________________________

    public User getUserByIP(String ipAdress) {
        User u = user.stream().filter(s -> s.getIP().equals(ipAdress)).findFirst().orElse(null);
        return u;
    }

    // ________________________________________________________

    public static ArrayList<String> getBannedUsers(){
        ArrayList<String> bannedUsers = new ArrayList<>();
        for (User u : user) {
            if (u.getBanned().equals("Yes")) {
                bannedUsers.add("ID: "+u.getID()+" Username: "+u.getName());
            }
        }
        return bannedUsers;
    }

    // ________________________________________________________

    public String randomID(){

        String output = "";
        int randomNum = ui.randomNumber(100000);
        String lastDigit = ui.randomLetterAZ();

        for(int i = 0; i <= 3; i++){

            String randomAZ = ui.randomLetterAZ();
            output += randomAZ;

        }

        return output + randomNum + lastDigit;

    }

    // ________________________________________________________

    public boolean usernameNotAllowed(String input){

        if(input.contains("password") || input.contains("admin")
        || input.contains("dev") || input.contains("email")){

            ui.displayMsg(ui.promptTextColor("red") + "\nNot allowed as username!\n" + ui.promptTextColor("reset"));
            return false;

        }

        return true;

    }

    // ________________________________________________________

    public void removeAccount(String name){

        ArrayList <String> data = io.readData("data/userData.csv");
        ArrayList <String> fixedData = new ArrayList<>();

        for (String s : data){

            String[] values = s.split(", ");
            String removeName = values[0].trim();

            // Re-writes the .csv file but skips the line where it matches the username
            if(!removeName.equalsIgnoreCase(name)){
                fixedData.add(s);
            }

        }

        // Re-write the original userData.csv
        io.saveData(fixedData, "data/userData.csv", "Username, ID, Age, Gender, Password, Banned, Status, IP, Email, LastLogin");

    }

    // ________________________________________________________

    public static void sleep(int amount) {

        try {
            Thread.sleep(amount);
        } catch (InterruptedException e) {
            ui.displayMsg("Somewthing went wrong" + e);
        } // Try-catch end

    }

} // Program end
