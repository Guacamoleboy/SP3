import java.util.Scanner;

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

    /**
     * Laver Metode til at registrer en ny bruger.
     * Så brugerene bliver bedt om at indtaste et brugerenavn, alder, køn og password.
     * Og hvis password ikke matcher bliver brugeren bedt om at prøve igen
     *
     *
     * @return En ny User-intand med brugerenn oplysninger.
     */

    public static User registerAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        int age;
        // Laver en løkkke der sikrer at alderen er mellem 12 og 120
        while (true) {
            System.out.print("Enter age: ");
            age = scanner.nextInt();
            scanner.nextLine();

            if (age >= 12 && age <= 120) {
                break; // Stopper loopet her, hvis alderen er gyldige
            } else {
                System.out.println("Invalid age. Age must be between 12 and 120. Please try again.");
            }
        }
    }

        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();

        String password;
        String confirmPassword;

        while (true) {
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            System.out.print("Confirm password: ");
            confirmPassword = scanner.nextLine();
            if (password.equals(confirmPassword)) { // Hvis passwords matcher, afsluttes loopet
                System.out.println("Account successfully created!");
                return new User(username, generateID(), age, gender, "No", password);
            } else {
                System.out.println("Passwords do not match. Please try again.");
            }
          }
       }

    }
     // ______________________________________________________________

    /**
     * Konverterer brugerens oplysninger til en CSV format stren, som fc kan bruges til filgaming.
     * @return
     */

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

    // ________________________________________________________

    public String getBanned(){
        return this.banned;
    }

    /**
     * Genererer et tilfældigt ID til brugeren
     * @return Et unikt ID
     */
    private static int generateID() {
        return (int) (Math.random() * 10000); // Genererer en tilfældige ID mellem 0 og 9999
    }

}


