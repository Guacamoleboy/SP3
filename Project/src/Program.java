import java.util.*;

public class Program {

    // Attributes
    private static TextUI ui = new TextUI();
    private static FileIO io = new FileIO();

    private String name;
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
        ui.displayMsg("Welcome to " + this.name);

        if(!data.isEmpty()){

            for(String s : data){
                String[] values = s.split(",");
                int userID = Integer.parseInt(values[1].trim());
                createUser(values[0], userID);
            }

        }

    }

    // ________________________________________________________

    public void registerUser(){

    }

    // ________________________________________________________

    public void createUser(String name, int ID){

        User u = new User(name, ID);
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

        io.saveData(playerData, "data/userData.csv", "Name, ID");
        ui.displayMsg("Program has saved data. Shutting down...");

    }

} // Program end
