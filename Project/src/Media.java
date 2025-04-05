import util.FileIO;

import java.io.File;

public class Media {

    // Attributes
    private int ID;
    private String title;
    private File file;
    private int releaseYear;
    private double rating;
    private String category;
    private float duration;

    // ________________________________________________________

    public Media(int ID, String title, File file, int releaseYear, double rating, String category, float duration){
        this.ID = ID;
        this.title = title;
        this.file = file;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.category = category;
        this.duration = duration;
    }

    // ________________________________________________________

    public void getMedia(int ID){

    }

    // ________________________________________________________

    public void getCategory(int ID){

    }

    // ________________________________________________________

    public void getReleaseYear(int ID){

    }

    // ________________________________________________________

    public void getRating(int ID){

    }

    // ________________________________________________________

    public float getDuration(){
        return this.duration;
    }

} // Class end
