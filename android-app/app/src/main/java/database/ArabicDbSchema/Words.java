package database.ArabicDbSchema;

/**
 * Created by IsmailElmaliki on 8/9/17.
 */

public class Words {

    private int id;
    private String word;
    private byte[] image;

    public Words() {
    }

    public Words(int id, String word, byte[] image) {

        this.id = id;
        this.word = word;
        this.image = image;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setWord(String word) {

        this.word = word;
    }

    public void setImage(byte[] image) {

        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getWord(){
        return word;
    }

    public byte[] getImage(){
        return image;
    }
}
