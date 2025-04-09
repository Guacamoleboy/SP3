import java.io.File;
import java.util.ArrayList;
import util.*;

public class Movies {

    // Attributes
    private ArrayList <Media> movies;
    private int page = 0;
    private static FileIO io = new FileIO();
    private static TextUI ui = new TextUI(Main.exitWord);

    // ________________________________________________________

    public Movies(){
        movies = new ArrayList<>();
        loadMovies();
    }

    // ________________________________________________________

    public void loadMovies(){
        ArrayList <String> data = io.readData("data/filmData.csv");

        for(String s : data){


            String[] values = s.split("; ");

            if(values.length >= 4){

                try{

                    String title = values[0].trim();
                    int releaseYear = Integer.parseInt(values[1].trim());
                    String category = values[2].trim();
                    String rating = values[3].trim();

                    Media m = new Media(title, releaseYear, category, rating, 50);
                    movies.add(m);

                } catch (Exception e){
                    ui.displayMsg("Error.");
                } // Try-catch end


            } // If (1)

        } // For-each end


    }

    // ________________________________________________________

    public void movieDisplay(int page){

        // Initial setting to prevent bugs and a display at 9 at a time
        int start = page * 9;
        int end = Math.min(start+9, movies.size());

        // Checks for movies
        if(start >= movies.size()){
            ui.displayMsg("No movies..");
            return;
        }

        // Load movies
        for(int i = start; i < end; i++){
            Media m = movies.get(i);
            ui.displayMsg((i + 1) + ") " + m.getTitle());
        }

        ui.displayMsg("Type NEXT, PREV OR BACK to swap between pages\n______________________________");


    }

    // ________________________________________________________

    public int totalPages(){
        return (int) Math.ceil((double) movies.size() / 9);
    }

    // ________________________________________________________

    public int getCurrentPage(){
        return this.page;
    }

    // ________________________________________________________

    public void setNextPage(){
        if(page < totalPages() - 1){
            page++;
        }
    }

    // ________________________________________________________

    public void resetPage(){
        page = 0;
    }

    // ________________________________________________________

    public ArrayList <Media> getMovies(){

        // Placeholder
        return movies;
    }

}
