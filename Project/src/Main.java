/*

    Authors: Andreas, Daud, Olivia, Jonas
    Version 0.4.0
    SP3

    Comments:
    N/A

*/

import util.TextUI;
import constants.Constants;

public class Main { // Client class

    // Attributes
    private static TextUI ui = new TextUI();
    public static Program program;

    // ________________________________________________________

    public static void main(String[] args) {

        // Toggles our program as "p"
        ui.displayMsg(ui.promptTextColor("red") + Constants.versionControl + ui.promptTextColor("reset"));
        Program program = new Program(ui.promptTextColor("red") +"Netflix" + ui.promptTextColor("reset"));

        // Uses our value "p" to startSession and so on
        program.startSession();

    }

} // Main end

