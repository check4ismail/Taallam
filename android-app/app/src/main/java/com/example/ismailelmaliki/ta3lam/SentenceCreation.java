package com.example.ismailelmaliki.ta3lam;

import java.io.Serializable;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IsmailElmaliki on 8/14/17.
 */

// Class extend Creation and uses String to reference correct English sentence
public class SentenceCreation extends Creation<String> {

    private String[] arabicSentences;           // Stores Arabic sentences
    private ArrayList<String> englishSentences;  // Stores English sentences corresponding to Arabic sentence


    public SentenceCreation() {

        super();
        arabicSentences = new String[]
        {
            "أنَا أسْكُن فِي مَدِينَة نِيويُورك",
            "وَلِدْتُ في الوَلايَات المُتَحِدة",
            "أنَا أُحِب أَن أضْحَك كَثِير",
            "هِيَ طَالِبَة في الجَامِعَة",
            "هُو يَدْرُس عِلْم الكَمْبِيُوتر",
            "هُم يَرْكَبُون القِطَار",
            "هَذَا المَكَان مُزْدَحَم",
            "هُم يَأْكُلُون فِي المَطْعَم",
            "هَذَا الطَعَام لَذِيذ"
        };

        englishSentences = new ArrayList<>();
        englishSentences.add("I live in New York");
        englishSentences.add("I was born in the United States");
        englishSentences.add("I love to laugh a lot");
        englishSentences.add("She’s a student in college");
        englishSentences.add("He studies computer science");
        englishSentences.add("They ride the train");
        englishSentences.add("This place is crowded");
        englishSentences.add("They eat at the restaurant");
        englishSentences.add("This food is delicious");

        // Passes arabicSentences for processing by Creation class
        super.setElements(arabicSentences);

        // Passes English sentences to Creation class for processing
        super.setReturnFileOrSentence(englishSentences);
    }
}
