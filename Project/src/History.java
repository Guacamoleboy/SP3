import java.util.ArrayList;
import util.FileIO;
import util.TextUI;

public class History {

    // Attributes
    private Media media;
    private ArrayList<Media> historyList;

    // ________________________________________________________

    public History(){
        historyList = new ArrayList<>();
    }

    // ________________________________________________________

    public void addToHistory(Media media){
        historyList.add(media);
    }

    // ________________________________________________________

    public ArrayList<Media> getHistoryList() {
        return historyList;
    }

    public ArrayList<String> toCSVHistory(int userID) {
        ArrayList<String> lines = new ArrayList<>();

        for (Media i : historyList) {
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


} // Class end
