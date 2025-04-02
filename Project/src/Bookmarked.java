import java.util.ArrayList;

public class Bookmarked {

    // Attributes
    private int ID;
    private ArrayList <Media> savedMedia;
    private String name;
    private Media media;

    // ________________________________________________________

    public Bookmarked(){

    } // Constructor

    // ________________________________________________________

    public void addBookmark(Media media, String name, int ID){

    }

    // ________________________________________________________

    public void removeBookmark(Media media, String name, int ID){

    }

    // ________________________________________________________

    public Media getMedia(){

        // Placeholder
        return media;

    }

    // ________________________________________________________

    public String getName(){
        return this.name;
    }

    // ________________________________________________________

    public int getID(){
        return this.ID;
    }

}
