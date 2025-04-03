import java.util.ArrayList;

public class Series {

    // Attributes
    private int ID;
    private String name;
    private ArrayList <Media> files;
    private Media media;

    // ________________________________________________________

    public Series(int ID, String name){
        this.ID = ID;
        this.name = name;
        files = new ArrayList<>();
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

    public ArrayList <Media> getFiles(){

        // Placeholder
        return files;
    }

    // ________________________________________________________

    public Media getFile(int ID){

        // Placeholder
        return media;
    }

} // Class end
