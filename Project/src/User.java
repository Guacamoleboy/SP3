import java.util.ArrayList;
import util.*;

public class User {

    // Attributes
    private static TextUI ui = new TextUI();

    private String username;
    private String password;
    private String status;
    private int ID;
    private int age;
    private String gender;
    private String banned;
    protected History history;
    protected Bookmarked bookmarked;

    // ________________________________________________________

    public User(String username, int ID, int age, String gender, String password, String banned, String status){

        this.username = username;
        this.ID = ID;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.banned = banned;
        this.status = status;
        this.history = new History();
        this.bookmarked = new Bookmarked();

    } // Constructor

    // ________________________________________________________

    public String toCSV(){

        return this.username + ", " + this.ID + ", " + this.age + ", " + this.gender + ", " + this.password + ", " + this.banned + ", " + this.status;

    }

    // ________________________________________________________

    public String toCSVSuggest(String value, int ID, String added){

        return value + ", " + ID + ", " + added;

    }

    // ________________________________________________________

    public String getName(){
        return this.username;
    }

    // ________________________________________________________

    public int getID(){
        return this.ID;
    }

    // ________________________________________________________

    public int getAge(){
        return this.age;
    }

    // ________________________________________________________

    public String getGender(){
        return this.gender;
    }

    // ________________________________________________________

    public String getBanned(){
        return this.banned;
    }

    // ________________________________________________________

    public String getStatus(){
        return this.status;
    }


    // ________________________________________________________

    public void changeUsername(String username){

        String choice = ui.promptText("New username: ");
        String password = ui.promptText("Enter your password: ");

        if (!(password.equalsIgnoreCase(this.password))){
            ui.displayMsg("Wrong password!");
            return;
        }

        this.username = choice;
        Main.p.saveData();
    }

    // ________________________________________________________

    public void changePassword() {

        String password = ui.promptText("Write your old password: ");
        if (!(password == this.password)){
            ui.displayMsg("Wrong password!");
            return;
        }

        // Confirm that newPassword is correct by double prompting
        String newPassword = ui.promptText("New password: ");
        boolean confirm = ui.promptPasswordConfirmation(newPassword);

        if (!confirm){
            ui.displayMsg("Wrong password!");
            return;
        }

        this.username = newPassword;
        Main.p.saveData();
    }

    // ________________________________________________________

    public void banUser() {
        this.banned = "Yes";
        Main.p.saveData();
    }

    // ________________________________________________________

}
