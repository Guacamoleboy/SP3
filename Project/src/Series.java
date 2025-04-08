import java.io.File;
import java.util.ArrayList;

public class Series extends Media{

    // Attributes
    private int ID;
    private String name;
    private ArrayList <Media> series;
    private Media media;

    // ________________________________________________________

    public Series(){
        series = new ArrayList<>();

    }

    // ________________________________________________________

    public void addToSeries(Media media){

    }

    // ________________________________________________________

    public void removeFromSeries(Media media){

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

    public ArrayList <Media> getSeries(){

        return series;
    }

    // ________________________________________________________

    public Media getFile(int ID){

        // Placeholder
        return media;
    }

} // Class end
