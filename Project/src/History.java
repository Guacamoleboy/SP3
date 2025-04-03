import java.util.ArrayList;

public class History {

    // Attributes
    private Media media;
    private String name;
    private int ID;
    private float duration;
    private ArrayList<String> history;

    // ________________________________________________________



    public History(Media media, String name, int ID, float duration){
        this.media = media;
        this.name = name;
        this.ID = ID;
        this.duration = duration;
        this.history = new ArrayList<>(); //*er det mest optimalt at saette initialiseringen af Arraylisten i konstruktoeren,
        // som her, eller uden for? Hvis den saettes her vil der oprettes en ny ArrayList hver gang at det oprettes et nyt history-objekt.
        //men skal der ikke ogsaa oprettes et nyt history objekt for hver bruger.
        // note: tjek hvornaar konstruktoren kaldes og om den kaldes flere gange for hver oprettet bruger.
        // Hvis den goer skal den saettes uden for konstruktoren i stedet.
    }

    // ________________________________________________________

    public void addToHistory(Media media, String name, int ID, float duration){

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

    // ________________________________________________________

    public float getDuration(){
        return this.duration;
    }

} // Class end
