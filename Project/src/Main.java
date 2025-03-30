/*

    Authors: Andreas Rovelt, Jonas Meinert Larsen & Ebou
    Version 0.2.0
    SP3

    Comments:
    N/A

*/

public class Main { // Client class

    // Attributes
    private static TextUI ui = new TextUI();

    // ________________________________________________________

    public static void main(String[] args) {

        // Toggles our program as "p"
        Program p = new Program(ui.promptTextColor("red") + "Drakeflix" + ui.promptTextColor("reset"));

        /*

        Testing backup when data is cleared. Please don't remove.

        FileIO io = new FileIO();
        io.clearData("data/userData.csv");
        */

        // Uses our value "p" to startSession and so on
        p.startSession();

    }

} // Main end