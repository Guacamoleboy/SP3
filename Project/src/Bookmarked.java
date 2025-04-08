import java.util.ArrayList;
import util.FileIO;
import util.TextUI;

public class Bookmarked {

    // Attributes
    private int ID;
    private ArrayList <Media> bookmarkedList;
    private String name;
    private Media media;

    // ________________________________________________________

    public Bookmarked(){
    bookmarkedList = new ArrayList<>();


    } // Constructor

    // ________________________________________________________

    public void addBookmark(Media media, String name, int ID){

        bookmarkedList.add(media);
    }

    // ________________________________________________________

    public void removeBookmark(Media media, String name, int ID){

    }

    // ________________________________________________________

    public Media getMedia(){

        // Placeholder
        return media;

    }
    //__________________________________________________________
    public ArrayList<String> toCSVBookmarked(int userID) {
        ArrayList<String> lines = new ArrayList<>();

        for (Media i : bookmarkedList) {
            String mediaType;

            if (i instanceof Movies) {
                mediaType = "Movie";
            } else {
                mediaType = "Series";
            }
            lines.add(userID + ", " + i.getTitle() + ", " + mediaType);
        }

        return lines;
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
