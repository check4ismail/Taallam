package database.ArabicDbSchema;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IsmailElmaliki on 8/9/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "arabicManager";

    // Table names
    private static final String TABLE_LETTERS = "letters";
    private static final String TABLE_WORDS = "words";

    // Common column name
    private static final String KEY_ID = "id";

    // TABLE_LETTERS Table - column names
    private static final String KEY_LETTER_ID = "letters_id";
    private static final String KEY_LETTER_LETTER = "letters_letter";
    private static final String KEY_LETTER_AUDIO = "letters_audio";

    // TABLE_WORDS Table - column names
    private static final String KEY_WORD_ID = "words_id";
    private static final String KEY_WORD_WORD = "words_word";
    private static final String KEY_WORD_IMAGE = "words_image";

    // Table Create Statements
    // Letter table create statement
    private static final String CREATE_TABLE_LETTERS =
                                    "CREATE TABLE " + TABLE_LETTERS +
                                    "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                                        KEY_LETTER_ID + " INTEGER," +
                                        KEY_LETTER_LETTER + " TEXT," +
                                        KEY_LETTER_AUDIO + " BLOB" +
                                    ")";

    private static final String CREATE_TABLE_WORDS =
                                    "CREATE TABLE " + TABLE_LETTERS +
                                    "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                                        KEY_WORD_ID + " INTEGER," +
                                        KEY_WORD_WORD + " TEXT," +
                                        KEY_WORD_IMAGE + " BLOB" +
                                    ")";



    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_LETTERS);
        db.execSQL(CREATE_TABLE_WORDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LETTERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);

        // create new tables
        onCreate(db);
    }

    public long createLetters(Letters letter) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LETTER_ID, letter.getId());
        values.put(KEY_LETTER_LETTER, letter.getLetter());
        values.put(KEY_LETTER_AUDIO, letter.getAudio());

        // insert row
        long letter_id = db.insert(TABLE_LETTERS, null, values);


        return letter_id;
    }

        /*
     * get single todo
     */
    public Letters getLetter(long letter_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_LETTERS + " WHERE "
                + KEY_ID + " = " + letter_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Letters letter = new Letters();
        letter.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        letter.setLetter((c.getString(c.getColumnIndex(KEY_LETTER_LETTER))));
        letter.setAudio(c.getBlob(c.getColumnIndex(KEY_LETTER_AUDIO)));

        return letter;
    }

    /*
 * getting all todos
 * */
    public List<Letters> getAllLetters() {

        List<Letters> lettersList = new ArrayList<Letters>();
        String selectQuery = "SELECT  * FROM " + TABLE_LETTERS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Letters letter = new Letters();
                letter.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                letter.setLetter((c.getString(c.getColumnIndex(KEY_LETTER_LETTER))));
                letter.setAudio(c.getBlob(c.getColumnIndex(KEY_LETTER_AUDIO)));

                // adding to todo list
                lettersList.add(letter);
            } while (c.moveToNext());
        }

        return lettersList;
    }

    /*
 * Updating a todo
 */
    public int updateLetters(Letters letter) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LETTER_LETTER, letter.getLetter());
        values.put(KEY_LETTER_AUDIO, letter.getAudio());

        // updating row
        return db.update(TABLE_LETTERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(letter.getId()) });
    }


        /*
     * Deleting a todo
     */
    public void deleteLetter(long letter_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LETTERS, KEY_ID + " = ?",
                new String[] { String.valueOf(letter_id) });
    }

    public long createWords(Words word) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD_ID, word.getId());
        values.put(KEY_WORD_WORD, word.getWord());
        values.put(KEY_WORD_IMAGE, word.getImage());

        // insert row
        long word_id = db.insert(TABLE_WORDS, null, values);


        return word_id;
    }

    /*
 * get single todo
 */
    public Words getWord(long word_id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_WORDS + " WHERE "
                + KEY_ID + " = " + word_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Words word = new Words();
        word.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        word.setWord((c.getString(c.getColumnIndex(KEY_WORD_WORD))));
        word.setImage(c.getBlob(c.getColumnIndex(KEY_WORD_IMAGE)));

        return word;
    }

    /*
 * getting all todos
 * */
    public List<Words> getAllWords() {

        List<Words> wordsList = new ArrayList<Words>();
        String selectQuery = "SELECT  * FROM " + TABLE_WORDS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Words word = new Words();
                word.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                word.setWord((c.getString(c.getColumnIndex(KEY_WORD_WORD))));
                word.setImage(c.getBlob(c.getColumnIndex(KEY_WORD_IMAGE)));

                // adding to todo list
                wordsList.add(word);
            } while (c.moveToNext());
        }

        return wordsList;
    }

    /*
 * Updating a todo
 */
    public int updateLetters(Words word) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD_WORD, word.getWord());
        values.put(KEY_WORD_IMAGE, word.getImage());

        // updating row
        return db.update(TABLE_WORDS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(word.getId()) });
    }


        /*
     * Deleting a todo
     */
    public void deleteWord(long word_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORDS, KEY_ID + " = ?",
                new String[] { String.valueOf(word_id) });
    }

    // closing database
    public void closeDB() {

        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
