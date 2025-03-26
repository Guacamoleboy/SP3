import java.util.*;

public class User {

    // Attributes
    private String name;
    private int ID;
    private int age;
    private String gender;

    // ________________________________________________________

    public User(String name, int ID, int age, String gender){

        this.name = name;
        this.ID = ID;
        this.age = age;
        this.gender = gender;

    } // Constructor

    // ________________________________________________________

    public String fixCSV(){

        /*

        Some fields in our data has under the max data fields. So by default we have like
        16 fields that needs to be filled. Some of our movies / series are under that. This
        means our github .csv file looks stupid. Need to fix that here by adding 0 untill maxNumber
        has been reached.

        */

        // Placeholder for now.
        return name;

    }

    // ________________________________________________________

    public String toCSV(){

        return name + ", " + ID + ", " + age + ", " + gender;

    }

    // ________________________________________________________

    public String getName(){

        return this.name;

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
