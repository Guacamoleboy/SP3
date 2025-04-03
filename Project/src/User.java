import java.util.ArrayList;
import java.util.Random;

public class User {

    // Attributes
    private String username;
    private String password;
    private int ID;
    private int age;
    private String gender;
    private String banned;
    protected ArrayList<History> history;
    protected ArrayList<Bookmarked> bookmarked;

    // Constructor
    public User(String username, int ID, int age, String gender, String banned, String password) {
        this.username = username;
        this.ID = ID;
        this.age = age;
        this.gender = gender;
        this.banned = banned;
        this.password = password;
    }

    // Convert User object to CSV format
    public String toCSV() {
        return String.format("%s, %d, %d, %s, %s, %s", username, ID, age, gender, password, banned);
    }

    // Convert suggested User data to CSV format
    public String toCSVSuggest(String value, int ID, String added) {
        return String.format("%s, %d, %s", value, ID, added);
    }

    // Getter methods
    public String getName() {
        return username;
    }

    public int getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBanned() {
        return banned;
    }

    // Change username
    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }

    // Change password
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    // Generate confirmation code (7-digit random number)
    public String generateConfirmationCode() {
        Random rand = new Random();
        int code = rand.nextInt(9000000) + 1000000; // Generates a random 7-digit number
        return String.valueOf(code);
    }
}
