import util.*;

import java.util.ArrayList;

public class SupportMenu extends Menu{

    // Attributes
    private static TextUI ui = new TextUI();
    private static FileIO io = new FileIO();

    // ________________________________________________________

    @Override
    public void startSession(String username){

        ui.displayMsg("\nWelcome to the Support Menu, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset"));
        ui.displayMsg("_______________________________________\n");

        ui.displayMsg("Open Tickets: 1\nSolved Tickets: 0\n__________________________________\n1) Answer Tickets\n2) Log Out\n");

        String input = ui.promptTextLine("Input: ");

        switch (input) {

            case "1":

                ArrayList <String> data = io.readData("data/supportTickets.csv");

                for (String s : data){

                    String[] values = s.split(", ");

                    if(!data.isEmpty()){

                        String statusColor = " ";

                        if(values[3].equalsIgnoreCase("Open")){
                            statusColor = "green";
                        } else if (values[3].equalsIgnoreCase("Closed")){
                            statusColor = "red";
                        } else if (values[3].equalsIgnoreCase("Pending")){
                            statusColor = "yellow";
                        } else {
                            ui.displayMsg("Something went wrong.. Dev Info: !VALIDCOLOR");
                        }

                        int i = 1;
                        ui.displayMsg(i + ") " + values[0] + " | Message: " + values[2] + "\nStatus: " + ui.promptTextColor(statusColor) + values[3] );

                    }

                }
                break;
            case "2":
                // Something
                break;
            case "bananflue":
                ui.displayMsg("Closing program.. Thanks. Till next time!");
                System.exit(0);
        }

    }

    // ________________________________________________________

    @Override
    public void endSession(){

    }

    // ________________________________________________________

    @Override
    public void runMenuLoop(){

    }

    // ________________________________________________________

    public void saveTicket(){



    }

}
