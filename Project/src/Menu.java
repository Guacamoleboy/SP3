import util.*;
import java.util.ArrayList;

public abstract class Menu { // (Superclass)

    // Attributes
    private static FileIO io = new FileIO();
    private static TextUI ui = new TextUI();
    private static Movies movies = new Movies();
    private static Series series = new Series();

    // ________________________________________________________

    public void startSession(String username) {

        // Load / toggle data

        User currentUser = Main.p.getUserByName(username);

        // Display data
        ui.displayMsg("\nWelcome to the Main Menu, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset") + "!");

        ui.displayMsg("\nPlease choose one of the following:\n1) Movies\n2) Series\n3) History\n4) Account Settings\n_____________________");
        String input = ui.promptTextLine("Input: ");
        String newInput = "";
        String seriesInput = "";

        ArrayList<Media> movieArray = movies.getMovies();
        ArrayList<Media> serieArray = series.getSeries();

        switch (input) {
            case "1":

                ui.displayMsg("Please choose a movie:\n");

                int page = 0;
                int pageSize = 9;
                boolean running = true;

                while (running) {

                    int start = page * pageSize;
                    int end = Math.min(start + pageSize, movieArray.size());

                    ui.displayMsg("\npage " + (page + 1));
                    for (int i = start; i < end; i++) {
                        Media movie = movieArray.get(i);
                        System.out.println((i - start + 1) + " " + (movie.getTitle()));
                    }

                    ui.displayMsg(ui.promptTextFormat("outline") + " Next " + ui.promptTextFormat("outline reset") + ui.promptTextFormat("outline") + " Prev "
                    + ui.promptTextFormat("outline reset") + ui.promptTextFormat("outline") + " Back " + ui.promptTextFormat("outline reset"));

                    input = ui.promptTextLine("Choose: ");

                    switch (input.toLowerCase()) {

                        case "next", "n":
                            if (end < movieArray.size()) page++;
                            break;
                        case "prev", "p":
                            if (page > 0) page--;
                            break;
                        case "back", "b":
                            startSession(username);
                            return;
                        default:

                            try {

                                int selected = Integer.parseInt(input);
                                Media selectedMovie = movieArray.get(start + selected - 1);
                                if (selected >= 1 && selected <= (end - start)) {

                                    running = false;
                                    ui.displayMsg("you have selected: " + selectedMovie.getTitle());

                                } else {
                                    ui.displayMsg("Invalid number.");
                                }

                                //method for printing movie options and choosing option
                                ui.displayMsg("1) Play\n2) Bookmark\n3) Back");
                                input = ui.promptText("Choose: ");
                                switch (input.toLowerCase()) {

                                    // ____________ PLAYING _____________

                                    case "1":

                                        currentUser.history.addToHistory(selectedMovie);

                                        int duration = 10; // Seconds

                                        // Must watch movie till the end as of right now. Can't use promptTextLine as it would
                                        // Prompt each time (loop).

                                        // Sets up our frameRate delay
                                        long starttime = System.currentTimeMillis(); // current system time

                                        while ((System.currentTimeMillis() - starttime) - 1000<= duration*1000) {
                                            long timeInSeconds = (System.currentTimeMillis() - starttime) / 1000;
                                            int progressBar = (int) (((double) timeInSeconds / duration) * 50);
                                            String progress = ui.promptTextColor("RED")+"|".repeat(progressBar);
                                            String remainingProgress = ui.promptTextColor("BLACK")+"|".repeat(50 - progressBar) + ui.promptTextColor("RESET");

                                            ui.displayMsg("Playing: " + selectedMovie.getTitle() + "\n" + timeInSeconds +  " "+ progress + remainingProgress+ " " + duration + ".");

                                            Program.sleep(1000);
                                        }

                                        ui.displayMsg("Thanks for watching!\n");
                                        //Wait 2,5 second, so the user sees the message above!^^
                                        Program.sleep(2500);
                                        // Return to menu.
                                        startSession(username);

                                        break;

                                    // ___________ BOOKMARK ____________

                                    case "2":
                                        currentUser.bookmarked.addBookmark(selectedMovie, currentUser.getName(), currentUser.getID());
                                        break;

                                    // ___________ BACK ____________

                                    case "3":
                                        running = true;
                                        break;
                                }

                            } catch (NumberFormatException e) {
                                ui.displayMsg("Invalid input.");
                            }


                    } // Switch end

                } // While end

                break;

            case "2":

                ui.displayMsg("Please choose a serie:\n");
                int page2 = 0;
                int pageSize2 = 9;
                boolean running2 = true;

                        while (running2) {

                            int start = page2 * pageSize2;
                            int end = Math.min(start + pageSize2, serieArray.size());

                            ui.displayMsg("\n page " + (page2 + 1));
                            for (int i = start; i < end; i++) {
                                Media serie = serieArray.get(i);
                                ui.displayMsg((i - start + 1) + " " + (serie.getTitle()));
                            }

                            ui.displayMsg("n) next | p) prev | b) back");
                            input = ui.promptText("Choose: ");

                            switch (input.toLowerCase()) {
                                case "n":
                                    if (end < serieArray.size()) page2++;
                                    break;
                                case "p":
                                    if (page2 > 0) page2--;
                                    break;
                                case "b":
                                    startSession(username);
                                    return;

                                    default:
                                        try {
                                            int selected = Integer.parseInt(input);
                                            Media selectedSerie = serieArray.get(start + selected - 1);
                                            if (selected >= 1 && selected <= (end - start)) {

                                                running2 = false;
                                                ui.displayMsg("you have selected: " + selectedSerie.getTitle());
                                            } else {
                                                ui.displayMsg("Invalid number.");
                                            }

                                            ui.displayMsg("p) play | bm) bookmark | b) back");
                                            input = ui.promptText("Choose: ");
                                            switch (input.toLowerCase()) {
                                                case "p":
                                                    System.out.print("playing: " + selectedSerie.getTitle());
                                                    currentUser.history.addToHistory(selectedSerie);
                                                    running2 = false;
                                                    break;

                                                case "bm":
                                                    break;

                                                case "b":
                                                    running2 = true;
                                                    break;
                                            }
                                        } catch (NumberFormatException e) {
                                            throw new RuntimeException(e);
                                        }

                            }
                        }

                        break;

            case "3":
                // Implement History here :-)
                // You can load and display history with a for loop for the data
                ui.displayMsg("History: \n");

                for(Media i : currentUser.history.getHistoryList()){
                    ui.displayMsg(i.getTitle());
                }

                break;

            case "4":
                accountSettings(username);
                break;

            default:
                ui.displayMsg("Invalid input. Try again.");
                startSession(username); // Re-run the session if invalid input
                break;
        }
    }

    // ________________________________________________________

    public abstract void endSession();

    // ________________________________________________________

    public abstract void runMenuLoop();

    // ________________________________________________________

    public void accountSettings(String username) {
        User user = Main.p.getUserByName(username);
        ui.displayMsg("Account settings for, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset") + ":");
        String input = ui.promptText("\n1) Change Username\n2) Change Password\n3) Something\n4) Something\n5) Something\n6) Pause Membership\n7) Set as account status to inactive\n9) Exit");

        switch (input.toLowerCase()) {
            case "1":
            case "change username":
                user.changeUsername(username);
                break;

            case "2":
            case "change password":
                user.changePassword();
                break;

            // Additional cases for options 3-7 can be added here

            default:
                // If no valid input, just exit or return to the menu
                break;
        }
        Main.p.mainmenu.startSession(user.getName());
    }

    // ________________________________________________________

    public static void frameRateFix(int frameRate){

        try{
            Thread.sleep(frameRate);
        } catch (InterruptedException e){
            ui.displayMsg("Error with framerate. Contact dev.");
            Thread.currentThread().interrupt();
        }

    }

    }
