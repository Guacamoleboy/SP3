/*

    Authors: Andreas, Daud, Olivia, Nestor & Jonas.
    SP3

    Comments:
    N/A

*/

import util.TextUI;

public class Main { // Client class

    // Very important exit mode!!!!!!
    public static String exitWord = "bananflue";

    // Attributes
    private static TextUI ui = new TextUI(Main.exitWord);
    public static Program p;

    // ________________________________________________________

    public static void main(String[] args) {

        // Checks if the program is up to date

        if(UpdateChecker.checkVersion()){

            // Exit prompt information
            ui.displayMsg("_____________________________________");
            ui.displayMsg("\nUse " + ui.promptTextFormat("outline") + " Bananflue " + ui.promptTextFormat("outline reset") +
                    " at any point to exit the program!");
            ui.displayMsg("_____________________________________");

            p = new Program(ui.promptTextColor("red") +"Netflix" + ui.promptTextColor("reset"));

            // System.out.println(p.randomID()); ID CHECK DEBUG

            // Uses our value "program" to startSession and so on
            p.startSession();
        }

    }

} // Main end

