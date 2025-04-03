public class History {

    // Attributes
    private Media media;
    private String name;
    private int ID;
    private float duration;

    // ________________________________________________________

    public History(Media media, String name, int ID, float duration){
        this.media = media;
        this.name = name;
        this.ID = ID;
        this.duration = duration;
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
