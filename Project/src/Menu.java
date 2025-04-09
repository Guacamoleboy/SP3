import util.*;

import java.util.ArrayList;

public abstract class Menu { // (Superclass)

    // Attributes
    private static FileIO io = new FileIO();
    private static TextUI ui = new TextUI(Main.exitWord);
    private static Movies m = new Movies();
    private static Series s = new Series();

    // ________________________________________________________

    public void startSession(String username){
        // Load / toggle data

        //Display data
        ui.displayMsg("\nWelcome to the Main Menu, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset") + "!\nYou were last seen "+ Main.p.currentUser.getLastSeen());

        Main.p.currentUser.updateLastLogin();

        ui.displayMsg("\nPlease choose one of the following:\n1) Movies\n2) Series\n3) History\n4) Bookmarked\n5) Search by category\n6) Search by title\n7) Account Settings\n_____________________");
        String input = ui.promptTextLine("Input: ");
        String seriesInput = "";
        switch (input){
            case "1":

                m.resetPage();
                boolean running = true;

                for(User u : Program.user){

                    if(u.getStatus().equalsIgnoreCase("inactive") && username.equalsIgnoreCase(u.getName())){
                        ui.displayMsg(ui.promptTextColor("red") + "\nYou don't have access to the Movie Menu as your status is inactive.." + ui.promptTextColor("reset"));
                        Program.sleep(2000);
                        return;
                    }

                    if(u.getStatus().equalsIgnoreCase("active") && username.equalsIgnoreCase(u.getName())){

                        while(running){

                            ui.displayMsg("\nPage " + m.getCurrentPage() + 1);
                            m.movieDisplay(m.getCurrentPage());

                            String movieInput = ui.promptTextLine("Choose a movie: ");

                            switch (movieInput){
                                case "next":
                                    m.setNextPage();
                                    break;
                                case "prev":
                                    m.resetPage();
                                    break;
                                case "back":
                                    running = false;
                                    startSession(username);
                                    break;
                                default:
                                    try{

                                        int mi = Integer.parseInt(movieInput)-1;
                                        int ai = m.getCurrentPage()*9 + mi;

                                        if(ai >= 0 && ai < m.getMovies().size()){
                                            Media c = m.getMovies().get(ai);
                                            ui.displayMsg("\nMovie selected: " + ui.promptTextColor("green") + c.getTitle() + ui.promptTextColor("reset"));

                                            boolean secondMenu = true;

                                            while(secondMenu){

                                                ui.displayMsg("\n1) Play\n2) Bookmark\n3) Back\n________________________");
                                                String userIn = ui.promptTextLine("Input: ");

                                                switch (userIn){

                                                    case "1":
                                                        saveData(username, c.getTitle(), "data/userHistory.csv");

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

                                                            ui.displayMsg("Playing: " + c.getTitle() + "\n" + timeInSeconds +  " "+ progress + remainingProgress+ " " + duration + ".");

                                                            Program.sleep(1000);
                                                        }

                                                        ui.displayMsg("\nMovies has ended. Thanks for watching!\n");
                                                        //Wait 2,5 second, so the user sees the message above!^^
                                                        Program.sleep(2500);

                                                        if(duration >= c.getDuration()){
                                                            ui.displayMsg("Movie has ended. Thanks for watching!");
                                                        }

                                                        break;
                                                    case "2":
                                                        ui.displayMsg("Bookmarked: " + c.getTitle());
                                                        saveData(username, c.getTitle(), "data/userBookmark.csv");
                                                        break;
                                                    case "3":
                                                        secondMenu = false;
                                                        break;
                                                    default:
                                                        ui.displayMsg("Invalid input. Try again!");
                                                        break;
                                                }

                                            }

                                        } else {
                                            ui.displayMsg("Invalid. Try again!");
                                        } // If (1)

                                    } catch (NumberFormatException e){
                                        ui.displayMsg("Invalid input. Try again!");
                                    } // Try-catch

                                    break;

                            } // Switch-case

                        }


                    }
                }



            case "2":

                s.resetPage();
                boolean runningSeries = true;

                for(User u : Program.user){

                    if(u.getStatus().equalsIgnoreCase("inactive") && username.equalsIgnoreCase(u.getName())){
                        ui.displayMsg(ui.promptTextColor("red") + "\nYou don't have access to the Movie Menu as your status is inactive.." + ui.promptTextColor("reset"));
                        Program.sleep(2000);
                        return;
                    }

                    if(u.getStatus().equalsIgnoreCase("active") && username.equalsIgnoreCase(u.getName())){

                        while(runningSeries){

                            ui.displayMsg("\nPage " + s.getCurrentPage() + 1);
                            s.seriesDisplay(s.getCurrentPage());

                            String seriesInput2 = ui.promptTextLine("Choose a series: ");

                            switch (seriesInput2){
                                case "next":
                                    s.setNextPage();
                                    break;
                                case "prev":
                                    s.resetPage();
                                    break;
                                case "back":
                                    runningSeries = false;
                                    startSession(username);
                                    break;
                                default:
                                    try{

                                        int mi = Integer.parseInt(seriesInput2)-1;
                                        int ai = s.getCurrentPage()*9 + mi;

                                        if(ai >= 0 && ai < s.getSeries().size()){
                                            Media c = s.getSeries().get(ai);
                                            ui.displayMsg("\nSeries selected: " + ui.promptTextColor("green") + c.getTitle() + ui.promptTextColor("reset"));

                                            boolean secondMenu = true;

                                            while(secondMenu){

                                                ui.displayMsg("\n1) Seasons & Episodes\n2) Bookmark\n3) Back\n________________________");
                                                String userIn = ui.promptTextLine("Input: ");

                                                switch (userIn){

                                                    case "1":

                                                        String se = c.getSeasonAndEpisode().trim();

                                                        if(se.isEmpty()){
                                                            ui.displayMsg("No seasons or episodes available.");
                                                            break;
                                                        }

                                                        ArrayList <Integer> seasons = new ArrayList<>();
                                                        ArrayList <Integer> episodes = new ArrayList<>();

                                                        // Removes the annoying ; after each data entry in seriesData.csv
                                                        if(se.endsWith(";")){
                                                            se = se.substring(0, se.length() -1);
                                                        }

                                                        String[] values = se.split(", ");

                                                        for (String s : values){

                                                            // Has to have trim first or it bugs. lol. gg.
                                                            String[] splitIntoValues = s.trim().split("-");

                                                            if(splitIntoValues.length == 2){

                                                                try{
                                                                    int season = Integer.parseInt(splitIntoValues[0].trim());
                                                                    int episode = Integer.parseInt(splitIntoValues[1].trim());

                                                                    seasons.add(season);
                                                                    episodes.add(episode);

                                                                } catch (NumberFormatException e){
                                                                    ui.displayMsg("Error. Contact dev: " + e.getMessage());
                                                                } // Try-catch end

                                                            } // If

                                                        } // For-each

                                                        if(seasons.isEmpty()){
                                                            ui.displayMsg("No seasons found.");
                                                            break;
                                                        }

                                                        ui.displayMsg("Seasons & Episodes");

                                                        for(int i = 0; i < seasons.size(); i++){

                                                            ui.displayMsg("Season: " + seasons.get(i) + ": " + episodes.get(i) + " Episodes");

                                                        }

                                                        while(true){

                                                            String userInputSeasonChooser = ui.promptTextLine("Choose a season: ");

                                                            if(userInputSeasonChooser.equalsIgnoreCase("back")){
                                                                break;
                                                            }

                                                            try{

                                                                int seasonsPicked = Integer.parseInt(userInputSeasonChooser);
                                                                int episodeCounter = 0;
                                                                boolean seasonFound = false;

                                                                for(int i = 0; i < seasons.size(); i++){

                                                                    if(seasons.get(i) == seasonsPicked){
                                                                        seasonFound = true;
                                                                        episodeCounter = episodes.get(i);
                                                                        break;
                                                                    }

                                                                } // For-end

                                                                if(!seasonFound){
                                                                    ui.displayMsg("Season wasnt found..");
                                                                    continue;
                                                                }

                                                                String episodeInput = ui.promptTextLine("Choose an episode: ");
                                                                int episodeChosen = Integer.parseInt(episodeInput);

                                                                if(episodeChosen >= 1 && episodeChosen <= episodeCounter){

                                                                    saveData(username, c.getTitle() + " Season " + seasonsPicked + " Episode " + episodeInput, "data/userHistory.csv");

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

                                                                        ui.displayMsg("Playing: " + c.getTitle() + " Season " + seasonsPicked + " Episode " + episodeChosen + "\n" + timeInSeconds +  " "+ progress + remainingProgress+ " " + duration + ".");

                                                                        Program.sleep(1000);
                                                                    }

                                                                    ui.displayMsg("\nSeries has ended. Thanks for watching!\n");
                                                                    //Wait 2,5 second, so the user sees the message above!^^
                                                                    Program.sleep(2500);

                                                                    startSession(username);

                                                                } else {
                                                                    ui.displayMsg("Invalid episode.. Try again");
                                                                }

                                                            } catch (NumberFormatException e){
                                                                ui.displayMsg("Invalid input. Try again!");
                                                            } // Try-catch end

                                                        } // While end (true)

                                                        break;
                                                    case "2":
                                                        ui.displayMsg("Bookmarked: " + c.getTitle());
                                                        saveData(username, c.getTitle(), "data/userBookmark.csv");
                                                        break;
                                                    case "3":
                                                        secondMenu = false;
                                                        break;
                                                    default:
                                                        ui.displayMsg("Invalid input. Try again!");
                                                        break;
                                                }

                                            }

                                        } else {
                                            ui.displayMsg("Invalid. Try again!");
                                        } // If (1)

                                    } catch (NumberFormatException e){
                                        ui.displayMsg("Invalid input. Try again!");
                                    } // Try-catch

                                    break;

                            } // Switch-case

                        }


                    }
                }

            case "3": // HISTORY
                ui.displayMsg("Debug");
                break;
            case "4": // BOOKMARKED
                ui.displayMsg("Debug");
                break;
            case "5": // SEARCH BY CATEGORY
                ui.displayMsg("Debug");
                break;
            case "6": // SEARCH BY TITLE
                ui.displayMsg("Debug");
                break;
            case "7":
                accountSettings(username);
                break;
            default:
                startSession(username);
                break;
        }

    }

    // ________________________________________________________

    public abstract void endSession();

    // ________________________________________________________

    public abstract void runMenuLoop();

    // ________________________________________________________

    public void accountSettings(String username){

        User user = Main.p.getUserByName(username);
        ui.displayMsg("\nAccount settings for, " + ui.promptTextColor("red") + username + ui.promptTextColor("reset") + ":");
        ui.displayMsg("\n1) Change Username\n2) Change Password\n3) Pause Membership\n4) Take a Break\n5) Change Membership\n6) Remove Account\n7) back");
        ui.displayMsg("_________________________\n");
        String input = ui.promptTextLine("Input: ");

        switch (input.toLowerCase()){
            case "1", "change Username", "1) change Username":
                user.changeUsername(username);
                break;
            case "2", "change Password", "2) change Password":
                user.changePassword();
                break;
            case "3", "Pause", "3) Pause Membership":
                //user.pauseMembership();
                break;
            case "4", "break", "4) Take a Break":
                ui.displayMsg("Status changed to 'inactive'!");
                user.changeStatus(username);
                break;
            case "5", "change", "5) Change Membership":

                ui.displayMsg("\n1) Upgrade Membership\n2) Downgrade Membership\n3) Back");
                String input2 = ui.promptTextLine("Input: ");

                    switch (input2){
                        case "1":

                            for(User u : Program.user){

                                if(u.getName().equalsIgnoreCase(username)){

                                    ui.displayMsg("Your current membership: " + u.getMembership());

                                    if(u.getMembership().equalsIgnoreCase("Normal")){
                                        ui.displayMsg("\n1) Premium");
                                        String in = ui.promptTextLine("Input: ");

                                        if(in.equalsIgnoreCase("1") || in.equalsIgnoreCase("premium")){
                                            u.setMembership("Premium");
                                        }
                                    }

                                    if(u.getMembership().equalsIgnoreCase("Premium")){
                                        ui.displayMsg("Can't upgrade any further! You're maxed out.");

                                    }



                                }

                            } // For-each end

                            break;

                        case "2":

                            for (User u : Program.user){

                                if(u.getName().equalsIgnoreCase(username)){

                                    ui.displayMsg("Your current membership: " + u.getMembership());

                                    if(u.getMembership().equalsIgnoreCase("Premium")){

                                        ui.displayMsg("1) Normal");
                                        String in2 = ui.promptTextLine("Input: ");

                                        if(in2.equalsIgnoreCase("1") || in2.equalsIgnoreCase("normal")){
                                            u.setMembership("Normal");
                                        }

                                    }

                                }

                            } // For-each loop end
                            break;

                        default:
                            ui.displayMsg("Invalid input.");

                    }

                    break;

            case "6", "remove", "6) remove account":
                ui.displayMsg("Account removed");
                String confirmName = ui.promptTextLine("Please confirm by typing username once more: ");
                Main.p.removeAccount(confirmName);
                Main.p.login();
                break;
            case "7", "back", "7) back":
                startSession(username);
                break;
            default:
                ui.displayMsg("Invalid input");
                break;
        }

        Main.p.mainmenu.startSession(user.getName());

    }

    // ________________________________________________________

    public void saveData(String username, String title, String path){

        ArrayList<String> data = new ArrayList<>();

        String s = toCSVHistory(username, title);
        data.add(s);

        io.saveData(data, path, "Username, History");

    }

    // ________________________________________________________

    public String toCSVHistory(String user, String title){
        return user + ", " + title;
    }

}
