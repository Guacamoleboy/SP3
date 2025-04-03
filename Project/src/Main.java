/*

    Authors: Andreas, Daud, Olivia, Jonas
    Version 0.3.9
    SP3

    Comments:
    N/A

*/

import util.TextUI;
import constants.Constants;

public class Main { // Client class

    // Attributes
    private static TextUI ui = new TextUI();

    // ________________________________________________________

    public static void main(String[] args) {

        // Toggles our program as "p"
        ui.displayMsg(ui.promptTextColor("red") + Constants.versionControl + ui.promptTextColor("reset"));
        Program p = new Program(ui.promptTextColor("red") +"Netflix" + ui.promptTextColor("reset"));

        // Uses our value "p" to startSession and so on
        p.startSession();

    }

} // Main end

