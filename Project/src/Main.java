/*

    Authors: Andreas Rovelt & Jonas Meinert Larsen
    Version 1.0
    SP3

    Comments:
    N/A

*/

public class Main { // Client class

    public static void main(String[] args) {

        // Toggles our program as "p"
        Program p = new Program();

        // Uses our value "p" to startSession and so on
        p.startSession();
        //p.runProgramLoop();
        p.endSession();
    }
}