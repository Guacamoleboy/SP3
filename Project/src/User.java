public class User {

    // Attributes
    private String username;
    private String password;
    private int ID;
    private int age;
    private String gender;
    private String banned;

    // ________________________________________________________

    public User(String username, int ID, int age, String gender, String banned, String password){

        this.username = username;
        this.ID = ID;
        this.age = age;
        this.gender = gender;
        this.banned = banned;
        this.password = password;

    } // Constructor

    // ________________________________________________________

    public String toCSV(){

        return this.username + ", " + this.ID + ", " + this.age + ", " + this.gender + ", " + this.password + ", " + this.banned;

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

}
