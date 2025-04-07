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

        ui.displayMsg("Open Tickets: " + getTicketsByStatus("Open") +  "\nSolved Tickets: " + getTicketsByStatus("Solved") +  "\n__________________________________\n1) Answer Tickets\n2) Log Out\n");

        String input = ui.promptTextLine("Input: ");

        switch (input) {

            case "1":

                ArrayList <String> data = io.readData("data/supportTickets.csv");

                for (String s : data){

                    String[] values = s.split(", ");

                    if(!data.isEmpty()){

                        String statusColor = " ";
                        int i = 0;

                        if(values[3].equalsIgnoreCase("Open")){

                            statusColor = "green";

                            i++;
                            ui.displayMsg(i + ") Ticket ID: " + values[0] + " | Message: " + values[2] + "\nStatus: " + ui.promptTextColor(statusColor)
                            + values[3] + ui.promptTextColor("reset") + "\n");

                        } else if (values[3].equalsIgnoreCase("Solved")){
                            statusColor = "red";
                        } else if (values[3].equalsIgnoreCase("Pending")){
                            statusColor = "yellow";
                        } else {
                            ui.displayMsg("Something went wrong.. Dev Info: !VALIDCOLOR");
                        }

                    }

                    if(getTicketsByStatus("Open") == 0){
                        ui.displayMsg(ui.promptTextColor("red") + "\nNo tickets open right now!" + ui.promptTextColor("reset"));
                        startSession(username);
                    }

                    String supportInput = ui.promptTextLine("Choose a ticket by ID: ");

                    if(supportInput.equals(values[0]) && values[3].equalsIgnoreCase("Open")){

                        ui.displayMsg("\n_____________________________________\nReplying to ticket ID: " + values[0] + "\n\nMessage by user: \n" + values[2] + "\n");
                        ui.displayMsg("Enter your message\n_________________________________");
                        String supportMsg = ui.promptTextLine("Response: ");
                        String status = ui.promptTextLine("Status on ticket: ");

                        if(status.equalsIgnoreCase("Open")){
                            status = "Open";
                        } else if (status.equalsIgnoreCase("Solved")){
                            status = "Solved";
                        } else if (status.equalsIgnoreCase("Pending")){
                            status = "Pending";
                        } else {
                            ui.displayMsg("Invalid status. Please only use (OPEN, SOLVED, PENDING)!");
                            startSession(username);
                        }

                        Program.saveDataTicket(Integer.parseInt(values[0]), values[1], values[2], status, supportMsg, username);

                        ui.displayMsg(ui.promptTextColor("green") + "\nMessage sent to " + values[0]
                                + ui.promptTextColor("reset") + "\n____________________________");

                        startSession(username);

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

    public int getTicketsByStatus(String input){

        ArrayList <String> data = io.readData("data/supportTickets.csv");

        int statusByStatus = 0;

        for (String s : data){

            String[] values = s.split(", ");
            String status = values[3].trim();

            if(status.equalsIgnoreCase("Open") && input.equalsIgnoreCase("Open")){
                statusByStatus++;
            } else if (status.equalsIgnoreCase("Pending") && input.equalsIgnoreCase("Pending")){
                statusByStatus++;
            } else if (status.equalsIgnoreCase("Solved") && input.equalsIgnoreCase("Solved")) {
                statusByStatus++;
            }

        } // For-loop end

        return statusByStatus;

    }

    // ________________________________________________________

    @Override
    public void endSession(){

    }

    // ________________________________________________________

    @Override
    public void runMenuLoop(){

    }

}
