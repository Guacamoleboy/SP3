/*

    Featured in this TextUI
    _______________________

    Prompts:                        Displays:                   Random:
    Numeric                         Msg                         Single (min - max)
    Binary                          List
    Text
    Double
    Char
    CharAZ
    Email
    Date
    PhoneNumber
    PasswordConfirmation
    Gender
    Choice (ArrayList)
    rollDice
    RandomLetterAZ

    Last updated: 28-03-2025
    Updated by: Jonas

*/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TextUI { // Our own custom generic TextUI class

    // Attributes
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    // ________________________________________________________

    public void displayList(ArrayList <String> list, String msg){

        for(int i = 0; i < list.size(); i++){
            System.out.println(i+1 + ". " + list.get(i));
        }

    }

    // ________________________________________________________

    public int randomSingle(){

        int numberOne = promptNumeric("Please enter a minimum number");
        int numberTwo = promptNumeric("Please enter a max number");

        int randomNumber = random.nextInt(numberOne, numberTwo);
        return randomNumber;

    }

    // ________________________________________________________

    public String promptRandomLetterAZ(){

        String input = promptText("Please enter a random letter from A-Z..");

        try {

            if(input.matches("\\d+")){ // Invalid characters handle
                displayMsg("Please only use valid inputs.");
            }

            char singleLetter = (char) ('A' + random.nextInt(26)); // A-Z (26)
            return String.valueOf(singleLetter);

        } catch (IllegalArgumentException e) {

            displayMsg("Invalid input. Try again.");
            return promptRandomLetterAZ();

        }

    }

    // ________________________________________________________

    public int rollDice(){

        int amountOfDice = promptNumeric("Please enter how many dices you wish to use");
        int totalSum = 0;
        int[] dice = new int[amountOfDice];

        for(int i = 0; i < dice.length; i++){
            dice[i] = random.nextInt(1, 7);
            displayMsg("You rolled a " + dice[i]);
            totalSum += dice[i];
        }

        return totalSum;

    }

    // ________________________________________________________

    public void displayMsg(String msg){

        System.out.println(msg);

    }

    // ________________________________________________________

    public String promptText(String msg){

        displayMsg(msg);
        String input = scanner.nextLine();

        return input;

    }

    // ________________________________________________________

    public String promptGender(String msg){

        boolean valid = false;
        String input = "";

        while(!valid){

            displayMsg(msg);
            input = scanner.nextLine();

            if(input.equalsIgnoreCase("Male") || input.equalsIgnoreCase("Female")){

                valid = true;

            } else {

                displayMsg("\nInvalid input..\n");

            } // If-else end

        } // While end

        return input;
    }

    // ________________________________________________________

    public boolean promptBinary(String msg){

        String choice = this.promptText(msg).toLowerCase();

        // Added most common user replies so we don't have to rely on y/n only.

        switch (choice){
            case "y", "yes", "yea", "yup", "yeah", "ya", "yessir", "yur":
                return true;
            case "n", "no", "na", "nah", "nope":
                return false;
            default:
                promptText("Invalid input.. Try again\n");
                promptBinary(msg);
        }

        /*

        Allow users to suggest new keywords that have failed? Like if I type "nopers"
        and I genuinely think it's correct and a valid response.. Should I as user
        be allowed to press "Keyword failed.. Think it's correct? Suggest it for future updates.." ?

        I think it would be a cool little feature.

        */


        return promptBinary(msg); // Default return value

    }

    // ________________________________________________________

    public int promptNumeric(String msg){

        int numInput = 0;
        boolean valid = false;

        while (!valid){

            displayMsg(msg);
            String input = scanner.nextLine();

            try {

                numInput = Integer.parseInt(input);
                valid = true;

            } catch (NumberFormatException e){

                displayMsg("Error. Please write a valid number..");

            } // Try-catch end

        } // While loop end

        return numInput;

    }

    // ________________________________________________________

    public double promptDecimal(String msg){

        double numInput = 1.0;
        boolean valid = false;

        while (!valid){

            displayMsg(msg);
            String input = scanner.nextLine();

            try{

                numInput = Double.parseDouble(input);
                valid = true;

            } catch (NumberFormatException e){

                displayMsg("Please enter a valid decimal number..");

            } // Try-catch end

        } // While end

        return numInput;

    }

    // ________________________________________________________

    public String promptEmail(String msg){

        String emailInput;
        displayMsg(msg);
        boolean valid = false;

        while(!valid){

            emailInput = scanner.nextLine().trim();

            if(emailInput.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){

                valid = true;
                return emailInput;

            } else {

                displayMsg("Invalid format. Please try again..");

            } // If end

        } // While end

        // Un-reachable cuz of if-else statement
        return null;

    }

    // ________________________________________________________

    public LocalDate promptDate(String msg){

        /*

        How to use
        __________

        LocalDate testDate = ui.promptDate("Please enter a date..");

        */

        String dateInput;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // MM not mm | mm apparently is minutes
        boolean valid = false;

        displayMsg(msg + "(dd-mm-yyyy)");

        while(true){

            dateInput = scanner.nextLine().trim();

            try{

                return LocalDate.parse(dateInput, dtf);

            } catch (DateTimeParseException e) {

                displayMsg("Invalid input. Try again.. Hint: dd-mm-yyyy");

            } // Try-catch end

        } // While end

    }

    // ________________________________________________________

    public String promptPhoneNumber(String msg){

        String numberInput;
        displayMsg(msg);

        while(true){

            numberInput = scanner.nextLine().trim();

            if(numberInput.matches("^\\+?[0-9]{8,8}$")){ // 8,8 allows danish only phone numbers

                return numberInput;

            } else {

                displayMsg("Invalid number.. Try again!");

            }

        }

    }

    // ________________________________________________________

    public boolean promptPasswordConfirmation(String msg){

        /*

        How to use | Example
        ____________________

        String playerPassword = ui.promptText("Please enter a password..");
        boolean passwordTest = false;

        // Allows us to loop over the password part
        while(!passwordTest){

            passwordTest = ui.promptPasswordConfirmation(playerPassword);

            if(!passwordTest){
                ui.displayMsg("\nPasswords don't match.. Try again.\n");
            }

        }

        */

        String passwordConfirmation = promptText("Please confirm your password:");
        return msg.equalsIgnoreCase(passwordConfirmation);

    }

    // ________________________________________________________

    public char promptChar(String msg){

        // Basically just takes the first character of a given String and stores it as a char

        char charInput = '0';
        boolean valid = false;

        while (!valid){

            displayMsg(msg);
            String input = scanner.nextLine();

            if(input.length() > 0){

                charInput = input.charAt(0);
                valid = true;

            } else {

                displayMsg("Please enter a valid character..");
            }

        }

        return charInput;

    }

    // ________________________________________________________

    public char promptCharAZ(String msg){

        // Only allow single letters.

        char charInput = '0';
        boolean valid = false;

        while (!valid){

            displayMsg(msg);
            String input = scanner.nextLine();

            if(input.length() == 1){

                charInput = Character.toUpperCase(input.charAt(0));

                if(charInput >= 'A' && charInput <= 'Z'){

                    valid = true;

                } else {

                    displayMsg("Wrong input. Please only use A - Z..");

                } // if end (INNER)

            } else {

                displayMsg("Please only enter a single character..");

            } // if end (OUTTER)

        }

        return charInput;

    }

    // ________________________________________________________

    public ArrayList <String> promptChoice(ArrayList <String> options, int limit, String msg){

        displayList(options, "");
        ArrayList <String> choices = new ArrayList<>();

        while(choices.size() < limit){
            int choice = promptNumeric(msg);
            choices.add(options.get(choice-1));
        }

        return choices;

    }

} // TextUI end
