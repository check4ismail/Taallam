package database.ArabicDbSchema;

/**
 * Created by IsmailElmaliki on 8/9/17.
 */

public class Letters {

    private int id;
    private String letter;
    private byte[] audio;

    public Letters() {
    }

    public Letters(int id, String letter, byte[] audio) {
        this.id = id;
        this.letter = letter;
        this.audio = audio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    public int getId() {
        return id;
    }

    public String getLetter() {
        return letter;
    }

    public byte[] getAudio() {
        return audio;
    }
}
