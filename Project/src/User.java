import java.util.ArrayList;
import util.*;

import static java.awt.SystemColor.menu;

public class User {

    // Attributes
    private static TextUI ui = new TextUI();

    private String ipAdress;
    private String username;
    private String password;
    private String status;
    private String ID;
    private int age;
    private String gender;
    private String banned;
    private String email;
    private String membership;
    protected History history;
    protected ArrayList <Bookmarked> bookmarked;
    protected MainMenu mainmenu = new MainMenu();


    // ________________________________________________________

    public User(String username, String ID, int age, String gender, String password, String banned, String status, String ipAdress, String email, String membership){
        this.ipAdress = ipAdress;
        this.username = username;
        this.ID = ID;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.banned = banned;
        this.status = status;
        this.email = email;
        this.membership = membership;
        this.history = new History();

    } // Constructor

    // ________________________________________________________

    public String toCSV(){

        return this.username + ", " + this.ID + ", " + this.age + ", " + this.gender + ", " + this.password + ", " + this.banned + ", " + this.status + ", "
        + this.ipAdress + ", " + this.email + ", " + this.membership;

    }

    // ________________________________________________________

    public String getName(){
        return this.username;
    }

    // ________________________________________________________

    public String getIpAdress(){
        return this.ipAdress;
    }

    // ________________________________________________________

    public String getID(){
        return this.ID;
    }

    // ________________________________________________________

    public String getEmail(){
        return this.email;
    }

    // ________________________________________________________

    public String getMembership(){
        return this.membership;
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

    public String getIP() {
        return this.ipAdress;
    }

    // ________________________________________________________

    public String getPassword(){
        return this.password;
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

    public void changeStatus(String username) {
        if (this.status.equalsIgnoreCase("Active")){
            boolean choice = ui.promptBinary("Are you sure you want to take a break?");
            if (choice){
                this.status = "Inactive";
            }
        } else if (this.status.equalsIgnoreCase("Inactive")) {
            boolean choice = ui.promptBinary("Are you sure you want to activate your account?");
            if (choice) {
                this.status = "Active";
            }
        }
        Main.p.saveData();
        mainmenu.accountSettings(username);
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

    public void setUsername(String username){
        this.username = username;
        Main.p.saveData();
    }

    // ________________________________________________________

    public void setPassword(String password){
        this.password = password;
        Main.p.saveData();
    }

    // ________________________________________________________

    public void setStatus(String status){
        this.status = status;
        Main.p.saveData();
    }

    // ________________________________________________________

    public void banUser() {
        this.banned = "Yes";
        Main.p.saveData();
    }

    // ________________________________________________________

    public void unbanUser() {
        this.banned = "No";
        Main.p.saveData();
    }

    // ________________________________________________________

}
