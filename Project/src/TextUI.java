/*

    Featured in this TextUI
    _______________________

    Prompts:                        Displays:
    Numeric                         Msg
    Binary                          List
    Text
    Double
    Char
    CharAZ
    Choises (ArrayList)

    Last updated: 27-03-2025
    Updated by: Jonas

*/

import java.util.ArrayList;
import java.util.Scanner;

public class TextUI { // Our own custom generic TextUI class

    // Attributes
    Scanner scanner = new Scanner(System.in);

    // ________________________________________________________

    public void displayList(ArrayList <String> list, String msg){

        for(int i = 0; i < list.size(); i++){
            System.out.println(i+1 + ". " + list.get(i));
        }

    }

    // ________________________________________________________

    public void displayMsg(String msg){

        System.out.println(msg);

    }

    // ________________________________________________________

    public String promptText(String msg){

        System.out.println(msg);
        String input = scanner.nextLine();

        return input;

    }

    // ________________________________________________________

    public boolean promptBinary(String msg){

        String choice = this.promptText(msg).toLowerCase();

        // Added most common user replies so we don't have to rely on y/n only.

        switch (choice){
            case "y":
                return true;
            case "yes":
                return true;
            case "yea":
                return true;
            case "yup":
                return true;
            case "yeah":
                return true;
            case "ya":
                return true;
            case "n":
                return false;
            case "no":
                return false;
            case "na":
                return false;
            case "nah":
                return false;
            case "nope":
                return false;
            default:
                promptText("Invalid input.. Try again\n");
                promptBinary(msg);
        }

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
