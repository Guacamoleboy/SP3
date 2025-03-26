/*

    Featured in this TextUI
    _______________________

    Prompts:                        Displays:
    Numeric                         Msg
    Binary                          List
    Text
    Choises (ArrayList)

    Last updated: 26-03-2025

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

        String choice = this.promptText(msg);

        if(choice.equalsIgnoreCase("Y")){
            return true;
        } else if (choice.equalsIgnoreCase("N")){
            return false;
        }

        return false; // Default

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
