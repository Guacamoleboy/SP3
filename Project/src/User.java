import java.util.*;

public class User {

    // Attributes
    private String name;
    private int ID;

    // ________________________________________________________

    public User(String name, int ID){

        this.name = name;
        this.ID = ID;

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

        return name + ", " + ID;

    }

    // ________________________________________________________

    public String getName(){

        return this.name;

    }

    // ________________________________________________________

    public int getID(){

        return this.ID;

    }

}
