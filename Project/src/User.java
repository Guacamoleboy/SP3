import java.util.ArrayList;

public class User {

    // Attributes
    private String username;
    private String password;
    private String status;
    private int ID;
    private int age;
    private String gender;
    private String banned;
    protected ArrayList <History> history;
    protected ArrayList <Bookmarked> bookmarked;

    // ________________________________________________________

    public User(String username, int ID, int age, String gender, String password, String banned, String status){

        this.username = username;
        this.ID = ID;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.banned = banned;
        this.status = status;

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

}
