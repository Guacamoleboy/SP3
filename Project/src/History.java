import java.util.ArrayList;

public class History {

    // Attributes
    private Media media;
    private ArrayList<Media> mediaList;

    // ________________________________________________________

    public History(){
        mediaList = new ArrayList<>();
    }

    // ________________________________________________________

    public void addToHistory(Media media){
        mediaList.add(media);
    }

    // ________________________________________________________

    public Media getMedia(){
        // Placeholder
        return media;
    }


} // Class end
