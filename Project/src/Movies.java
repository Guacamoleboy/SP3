import java.io.File;
import java.util.ArrayList;

public class Movies extends Media{

    // Attributes
    private ArrayList <Media> movies;
    private int ID;
    private String name;
    private File file;

    // ________________________________________________________

    //Movie constructor *midlertidigt harcoded med dummy data for at faa Menu til at virke
    public Movies(){
        movies = new ArrayList<>();
        movies.add(new Media(1, "The Godfather", new File(""), 0, 0.0, "category1", 0f));
        movies.add(new Media(2, "The Shawshank Redemption", new File(""), 0, 0.0, "category1", 0f));
        movies.add(new Media(3, "Schindler's List", new File(""), 0, 0.0, "category1", 0f));
        movies.add(new Media(4, "Raging Bull", new File(""), 0, 0.0, "category1", 0f));
        movies.add(new Media(5, "Casablanca", new File(""), 0, 0.0, "category1", 0f));
        movies.add(new Media(6, "Citizen Kane", new File(""), 0, 0.0, "category1", 0f));
        movies.add(new Media(7, "Gone With The Wind", new File(""), 0, 0.0, "category1", 0f));
        movies.add(new Media(8, "The Wizard Of Oz", new File(""), 0, 0.0, "category1", 0f));
        movies.add(new Media(9, "One Flew Over The Cuckoo's Nest", new File(""), 0, 0.0, "category1", 0f));
        movies.add(new Media(10, "Lawrence Of Arabia", new File(""), 0, 0.0, "category1", 0f));
    }

    // ________________________________________________________

    public void addMovie(int ID, String name, File file){

    }

    // ________________________________________________________

    public void removeMovie(int ID, String name, File file){

    }

    // ________________________________________________________

    public int getID(){
        return this.ID;
    }

    // ________________________________________________________

    public String getName(){
        return this.name;
    }

    // ________________________________________________________

    public ArrayList <Media> getMovies(){

        // Placeholder
        return movies;
    }

}
