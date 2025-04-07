/*

    Send a confirmation email to an email in our userData.csv file
    This is done through my webhost on a 3rd party website
    using javax.mail.jar from libs as a module.

    Made by: Jonas
    Date: 5-4-2025

*/

import util.TextUI;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Random;

public class EmailConfirmation {

    // Attributes

    // static as the attribute isn't an instance but the same for all classes.
    private static String ourEmail;
    private static String ourPassword;
    private static int randomCode;
    private static TextUI ui = new TextUI();
    private static Random random = new Random();

    // ________________________________________________________

    /*

        How to use:
        ___________

        EmailConfirmation.sendPassword(mailFromCSV);

        Current issues:
        _______________

        Mail goes to Spam folder. Might not be able to fix that for now.

    */

    public static void sendPassword(String email){

        ourEmail = "sp3@consumr.dk";
        ourPassword = "Dinmorertyk123!";
        // Makes our random code
        randomCodeGen();
        int code = randomCode;

        // Stores that code in HashMap
        HashMapStorage.saveCode(toString(code));


        String subject = "Password confirmation email from Netflix (SP3)";
        String msg = "Your password confirmation code is:\n " + code + "\n\nCode works for 5 minutes.\n"
                + "\nThanks for using Netflix.";

        // Email setup
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.simply.com"); // Custom
        p.put("mail.smtp.port", "587");

        // Starting our send session
        Session s = Session.getInstance(p,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(ourEmail, ourPassword);
                    }
                });

        // Try-catch to catch exceptions
        try{

            // Sets our email up
            Message message = new MimeMessage(s);
            message.setFrom(new InternetAddress(ourEmail));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email)
            );

            // Adds our subject and msg (text)
            message.setSubject(subject);
            message.setText(msg);

            // Sends it
            Transport.send(message);

            // User display (Console for now). If GUI then change this to DEBUG only (dev)
            //ui.displayMsg("Password sent! DEV");

        } catch (MessagingException e){
            ui.displayMsg("Error. Try again.." + " | Dev debug msg: " +e.getMessage());
        }

    } // Method end

    // ________________________________________________________

    /*

        Rainy day:
        __________

        Change this so we are able to have numbers in our confirmation code (int -> String).
        This allows for more unique codes to make sure more people don't get the same code.

    */

    public static int randomCodeGen(){

        randomCode = random.nextInt(100000);
        return randomCode;

    }

    // ________________________________________________________

    /*

        int -> String -> return
        Currently not used
        Can be used for if we make the Rainy Day thing under randomCode()

    */


    public static String toString(int confirmCode){

        return String.valueOf(confirmCode);

    }


}
