import util.FileIO;
import util.TextUI;

import java.util.ArrayList;

public class Series {

    // Attributes
    private ArrayList <Media> series;
    private int page = 0;
    private static FileIO io = new FileIO();
    private static TextUI ui = new TextUI(Main.exitWord);

    // ________________________________________________________

    public Series(){
        series = new ArrayList<>();
        loadSeries();
    }

    // ________________________________________________________

    public void loadSeries(){
        ArrayList <String> data = io.readData("data/seriesData.csv");

        for(String s : data){

            String[] values = s.split("; ");

            if(values.length >= 5){

                try{

                    String title = values[0].trim();
                    String releaseYearSeries = values[1].trim();
                    String category = values[2].trim();
                    String rating = values[3].trim();
                    String seasonAndEpisode = values[4].trim();

                    Media m = new Media(title, releaseYearSeries, category, rating, 50, seasonAndEpisode);
                    series.add(m);

                } catch (Exception e){
                    ui.displayMsg("Error. Contact dev: " + e.getMessage());
                } // Try-catch end

            } // If (1)

        } // For-each end

    }

    // ________________________________________________________

    public void seriesDisplay(int page){

        // Initial setting to prevent bugs and a display at 9 at a time
        int start = page * 9;
        int end = Math.min(start+9, series.size());

        // Checks for movies
        if(start >= series.size()){
            ui.displayMsg("No movies..");
            return;
        }

        // Load movies
        for(int i = start; i < end; i++){
            Media m = series.get(i);
            ui.displayMsg((i + 1) + ") " + m.getTitle());
        }

        ui.displayMsg("Type NEXT, PREV OR BACK to swap between pages\n______________________________");


    }

    // ________________________________________________________

    public ArrayList<Media> getSeries(){
        return this.series;
    }

    // ________________________________________________________

    public int getCurrentPage(){
        return this.page;
    }

    // ________________________________________________________

    public void resetPage(){
        this.page = 0;
    }

    // ________________________________________________________

    public void setNextPage(){
        this.page++;
    }


} // Class end
