import util.*;

import java.io.File;
import java.util.ArrayList;

public class Media {

    // Attributes
    private String title;
    private int releaseYear;
    private String releaseYearSeries;
    private String category;
    private String rating;
    private int duration = 50;
    private String seasonAndEpisode;

    private static TextUI ui = new TextUI(Main.exitWord);
    private static FileIO io = new FileIO();

    // ________________________________________________________

    public Media(String title, int releaseYear, String category, String rating, int duration){
        this.title = title;
        this.releaseYear = releaseYear;
        this.category = category;
        this.rating = rating;
        this.duration = duration;
    }

    // ________________________________________________________

    // OVERLOADING WITH DIFFERENT PARAMETERS ALLOWS 2 CONSTRUCTORS. 1 FOR MOVIES AND 1 FOR SERIES
    public Media(String title, String releaseYearSeries, String category, String rating, int duration, String seasonAndEpisode){
        this.title = title;
        this.releaseYearSeries = releaseYearSeries;
        this.category = category;
        this.rating = rating;
        this.duration = duration;
        this.seasonAndEpisode = seasonAndEpisode;
    }

    // ________________________________________________________

    public void getMedia(int ID){

    }

    // ________________________________________________________

    public String getSeasonAndEpisode(){
        return this.seasonAndEpisode;
    }

    // ________________________________________________________

    public String getTitle(){
        return this.title;
    }

    // ________________________________________________________

    public String getCategory(){
        return this.category;
    }

    // ________________________________________________________

    public int getReleaseYear(){
        return this.releaseYear;
    }

    // ________________________________________________________

    public String getRating(){
        return this.rating;
    }

    // ________________________________________________________

    public int getDuration(){
        return this.duration;
    }

} // Class end
