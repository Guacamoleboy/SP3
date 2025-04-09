import util.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

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
                                    int mi = Integer.parseInt(movieInput)-1;
                                    int ai = m.getCurrentPage()*9 + mi;
                                    if(ai < 0 && ai > m.getMovies().size()){
                                        ui.displayMsg("ERROR!");
                                    }
                                    Media c = m.getMovies().get(ai);
                                    playMovie(c, username);
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

                            seriesInput = ui.promptTextLine("Choose a series: ");

                            switch (seriesInput){
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
                                    int mi = Integer.parseInt(seriesInput)-1;
                                    int ai = s.getCurrentPage()*9 + mi;

                                    if(ai < 0 && ai > s.getSeries().size()){
                                        ui.displayMsg("ERROR!");
                                    }
                                    Media c = s.getSeries().get(ai);
                                    playSeries(c, username);

                                    break;

                            }

                        }


                    }
                }

            case "3": // HISTORY
                ui.displayMsg("Jonas skal tilføje sin HISTORY!");
                Sleep(20000);
                break;
            case "4": // BOOKMARKED
                ui.displayMsg("Jonas skal tilføje sin BOOKMARKED!");
                Sleep(20000);
                break;
            case "5": // SEARCH BY CATEGORY
                searchCategory(username);
                break;
            case "6": // SEARCH BY TITLE
                searchTitle(username);
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

    public void endSession() {
        //Logout method?
    };

    // ________________________________________________________

    private void searchTitle(String username) {

        ui.displayMsg("\nPlease choose what you want to watch\n1) Movie \n2) Serie\n_____________________");
        String choice = ui.promptTextLine("Input: ").toLowerCase();

        if (!choice.equals("1") && !choice.equals("2")) {
            ui.displayMsg("Invalid choice. Please type '1' for movie or '2' for serie.");
            Sleep(2500);
            searchTitle(username);
            return;
        }

        ui.displayMsg("\nPlease enter the title of the " + (choice.equals("1") ? "movie" : "series") + " you want to watch\n_____________________");
        String titleInput = ui.promptTextLine("Input: ").toLowerCase();

        ArrayList<String> matchingTitles;

        if (choice.equals("1")) {
            matchingTitles = Movies.getMovies().stream()
            .map(Media::getTitle)
            .filter(title -> title.toLowerCase().contains(titleInput))
            .collect(Collectors.toCollection(ArrayList::new));
        } else {
            matchingTitles = Series.getSeries().stream()
            .map(Media::getTitle)
            .filter(title -> title.toLowerCase().contains(titleInput))
            .collect(Collectors.toCollection(ArrayList::new));
        }

        if (matchingTitles.isEmpty()) {
            ui.displayMsg("No " + (choice.equals("1") ? "movie" : "series") + " matches your search: " + titleInput);
            return;
        }

        final int pageSize = 10;
        int currentPage = 0;
        boolean browsing = true;

        while (browsing) {
            int start = currentPage * pageSize;
            int end = Math.min(start + pageSize, matchingTitles.size());

            ui.displayMsg("\nResults " + (start + 1) + " to " + end + " of " + matchingTitles.size() + ":");
            for (int i = start; i < end; i++) {
                ui.displayMsg((i + 1) + ". " + matchingTitles.get(i));
            }

            ui.displayMsg("\nType the number you want to watch\n'next', 'prev' to browse between the pages\n\n" +
            "Type " + ui.promptTextFormat("outline") + " back " + ui.promptTextFormat("outline reset") + " to go back to Dev Menu..");

            String movieChoice = ui.promptTextLine("Choice: ").toLowerCase();

            switch (movieChoice) {
                case "next":
                    if ((currentPage + 1) * pageSize < matchingTitles.size()) {
                        currentPage++;
                    }
                    break;
                case "prev":
                    if (currentPage > 0) {
                        currentPage--;
                    }
                    break;
                case "back":
                    startSession(username);
                    browsing = false;
                    break;
                default:
                    try {
                        int selectedIndex = Integer.parseInt(movieChoice) - 1;
                        if (selectedIndex >= 0 && selectedIndex < matchingTitles.size()) {
                            String selectedTitle = matchingTitles.get(selectedIndex);
                            if (choice.equals("1")) {
                                Media c = getMedia(selectedTitle, "movie");
                                playMovie(c, username);
                                startSession(username);
                            } else {
                                Media c = getMedia(selectedTitle, "series");
                                playSeries(c, username);
                                startSession(username);
                            }
                            browsing = false;
                        } else {
                            ui.displayMsg("Wrong input. Number out of bounds!");
                            Sleep(2500);
                        }
                    } catch (NumberFormatException e) {
                        ui.displayMsg("Wrong input (<Number>, <Next>, <Prev> or <Back>). Try again!.");
                        Sleep(2500);
                    }
                    break;
            }
        }
    }

    // ________________________________________________________

    private void searchCategory(String username) {

        ui.displayMsg("\nPlease choose what you want to watch\n1) Movie \n2) Serie\n_____________________");
        String choice = ui.promptTextLine("Input: ").toLowerCase();

        if (!choice.equals("1") && !choice.equals("2")) {
            ui.displayMsg("Invalid choice. Please type '1' for movie or '2' for serie.");
            Sleep(2500);
            searchTitle(username);  // Recursion for retrying
            return;
        }

        // Vælg kategori
        ui.displayMsg("\nWhat category do you want to watch?\n1) Crime \n2) Drama\n_____________________");
        String categoryInput = ui.promptTextLine("Input: ").toLowerCase();
        String category = "";
        switch (categoryInput) {
            case "1":
                category = "crime";
                break;
            case "2":
                category = "drama";
                break;
            default:
                ui.displayMsg("Invalid category. Please choose 1 for Crime or 2 for Drama.");
                Sleep(2500);
                searchCategory(username);
                return;
        }
        String tmpcategory = category;
        ArrayList<String> matchingTitles = new ArrayList<>();  // Til at holde de matchende titler

        // Filtrer film eller serier baseret på kategori
        if (choice.equals("1")) {  // Movie
            matchingTitles = Movies.getMovies().stream()
                    .filter(movie -> movie.getCategory().toLowerCase().contains(tmpcategory))  // Filter baseret på kategori
                    .map(Media::getTitle)  // Ekstraher filmens titel
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {  // Serie
            matchingTitles = Series.getSeries().stream()
                    .filter(series -> series.getCategory().toLowerCase().contains(tmpcategory))  // Filter baseret på kategori
                    .map(Media::getTitle)  // Ekstraher seriens titel
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        // Hvis der ikke er nogen matchende titler
        if (matchingTitles.isEmpty()) {
            ui.displayMsg("No titles found for category: " + category);
            return;
        }

        // Pagination (visning af resultater)
        final int pageSize = 10;
        int currentPage = 0;
        boolean browsing = true;

        while (browsing) {
            int start = currentPage * pageSize;
            int end = Math.min(start + pageSize, matchingTitles.size());

            ui.displayMsg("\nResults " + (start + 1) + " to " + end + " of " + matchingTitles.size() + ":");
            for (int i = start; i < end; i++) {
                ui.displayMsg((i + 1) + ". " + matchingTitles.get(i));  // Vis titlerne
            }

            ui.displayMsg("\nType the number you want to watch\n'next', 'prev' to browse between the pages\n\n" +
                    "Type " + ui.promptTextFormat("outline") + " back " + ui.promptTextFormat("outline reset") + " to go back to Dev Menu..");

            String movieChoice = ui.promptTextLine("Choice: ").toLowerCase();

            switch (movieChoice) {
                case "next":
                    if ((currentPage + 1) * pageSize < matchingTitles.size()) {
                        currentPage++;
                    }
                    break;
                case "prev":
                    if (currentPage > 0) {
                        currentPage--;
                    }
                    break;
                case "back":
                    ui.displayMsg("BACK");
                    startSession(username);  // Go back to main menu
                    browsing = false;
                    break;
                default:
                    try {
                        int selectedIndex = Integer.parseInt(movieChoice) - 1;
                        if (selectedIndex >= 0 && selectedIndex < matchingTitles.size()) {
                            String selectedTitle = matchingTitles.get(selectedIndex);
                            if (choice.equals("1")) {  // If it's a movie
                                Media c = getMedia(selectedTitle, "movie");
                                playMovie(c, username);
                                startSession(username);
                            } else {  // If it's a series
                                Media c = getMedia(selectedTitle, "movie");
                                playSeries(c, username);
                                startSession(username);
                            }
                            browsing = false;
                        } else {
                            ui.displayMsg("Wrong input. Number out of bounds!");
                            Sleep(2500);
                        }
                    } catch (NumberFormatException e) {
                        ui.displayMsg("Wrong input (<Number>, <Next>, <Prev> or <Back>). Try again!.");
                        Sleep(2500);
                    }
                    break;
            }
        }
    }


    // ________________________________________________________

    public void playMovie(Media c, String username) {
        try{
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
        } catch (NumberFormatException e){
            ui.displayMsg("Invalid input. Try again!");
        } // Try-catch
    }

    // ________________________________________________________

    public void playSeries(Media c, String username) {
        try{

            /*int mi = Integer.parseInt(seriesInput)-1;
            int ai = s.getCurrentPage()*9 + mi;

            if(ai >= 0 && ai < s.getSeries().size()){*/

            //Media c = getMedia(seriesInput, "series");
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

        } catch (NumberFormatException e){
            ui.displayMsg("Invalid input. Try again!");
        } // Try-catch
    }

    // ________________________________________________________


    public Media getMedia(String movieInput, String mediaType){
        if (mediaType.equalsIgnoreCase("movie")) {
            return Movies.getMovies().stream()
                    .filter(media -> media.getTitle().equalsIgnoreCase(movieInput))
                    .findFirst()
                    .orElse(null);
        } else {
            return Series.getSeries().stream()
                    .filter(media -> media.getTitle().equalsIgnoreCase(movieInput))
                    .findFirst()
                    .orElse(null);

        }
    }

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

    private void Sleep(int amount) {
        try {
            Thread.sleep(amount);
        } catch (InterruptedException e) {
            ui.displayMsg("Somewthing went wrong" + e);
        } // Try-catch end
    }

}
