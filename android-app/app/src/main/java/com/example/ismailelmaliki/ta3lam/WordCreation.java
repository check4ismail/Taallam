package com.example.ismailelmaliki.ta3lam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IsmailElmaliki on 8/14/17.
 */

// Class extends Creation and uses Integer to refer to specific photo file
public class WordCreation extends Creation<Integer> {

    // stores Arabic words
    private String[] words;

    // Stores photo file associated to correct word
    private ArrayList<Integer> resIDPhoto;         // ResID for specific photo file


    public WordCreation() {

        super();

        words = new String[]
        {
            "قُط", "طُفْل", "كَلْب", "رَجُل", "إمْرَأة"
        };

        resIDPhoto = new ArrayList<>();
        resIDPhoto.add(R.drawable.cat);
        resIDPhoto.add(R.drawable.child);
        resIDPhoto.add(R.drawable.dog);
        resIDPhoto.add(R.drawable.man);
        resIDPhoto.add(R.drawable.woman);

        // Passes words to Creation class for processing
        super.setElements(words);

        // Passes resIDPhoto to Creation class for processing
        super.setReturnFileOrSentence(resIDPhoto);
    }
}
