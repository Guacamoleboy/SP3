/*

    Authors: Andreas, Daud, Olivia, Jonas, Christopher
    Version 0.5.5
    SP3

    Comments:
    N/A

*/

import util.TextUI;
import constants.Constants;

public class Main { // Client class

    // Attributes
    private static TextUI ui = new TextUI();
    public static Program p;

    // ________________________________________________________

    public static void main(String[] args) {

        // Checks if the program is up to date

        EmailConfirmation.sendPassword("jonas68@live.dk");


        if(UpdateChecker.checkVersion()){
            // Exit prompt information
            ui.displayMsg("\nUse " + ui.promptTextFormat("outline") + " Bananflue " + ui.promptTextFormat("outline reset") +
                    " at any point to exit the program!");

            p = new Program(ui.promptTextColor("red") +"Netflix" + ui.promptTextColor("reset"));

            // Uses our value "program" to startSession and so on
            p.startSession();
        }

    }

    // ________________________________________________________



} // Main end

