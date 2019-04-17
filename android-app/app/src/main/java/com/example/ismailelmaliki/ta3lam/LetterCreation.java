package com.example.ismailelmaliki.ta3lam;


import java.util.ArrayList;

public class LetterCreation extends Creation<Integer> {

    private String[] letters;                    // Stores all Arabic letters
    private ArrayList<Integer> resIDAudio;       // ResID for specific audio file corresponding to Arabic letter


    public LetterCreation()
    {
        super();
        letters = new String[]{
                "ا", "ب", "ت", "ث", "ج", "ح", "خ",
                "د", "ذ", "ر", "ز", "س", "ش", "ص",
                "ض", "ط", "ظ", "ع", "غ", "ف", "ق",
                "ك", "ل", "م", "ن", "ه", "و", "ي"};

        resIDAudio = new ArrayList<>();

        resIDAudio.add(R.raw.audio_1);   resIDAudio.add(R.raw.audio_2);
        resIDAudio.add(R.raw.audio_3);   resIDAudio.add(R.raw.audio_4);
        resIDAudio.add(R.raw.audio_5);   resIDAudio.add(R.raw.audio_6);
        resIDAudio.add(R.raw.audio_7);   resIDAudio.add(R.raw.audio_8);
        resIDAudio.add(R.raw.audio_9);   resIDAudio.add(R.raw.audio_10);
        resIDAudio.add(R.raw.audio_11);   resIDAudio.add(R.raw.audio_12);
        resIDAudio.add(R.raw.audio_13);   resIDAudio.add(R.raw.audio_14);
        resIDAudio.add(R.raw.audio_15);   resIDAudio.add(R.raw.audio_16);
        resIDAudio.add(R.raw.audio_17);   resIDAudio.add(R.raw.audio_18);
        resIDAudio.add(R.raw.audio_19);   resIDAudio.add(R.raw.audio_20);
        resIDAudio.add(R.raw.audio_21);   resIDAudio.add(R.raw.audio_22);
        resIDAudio.add(R.raw.audio_23);   resIDAudio.add(R.raw.audio_24);
        resIDAudio.add(R.raw.audio_25);   resIDAudio.add(R.raw.audio_26);
        resIDAudio.add(R.raw.audio_27);   resIDAudio.add(R.raw.audio_28);

        // Passes letters to Creation class for processing
        super.setElements(letters);

        // Passes resIDAudio to Creation class for processing
        super.setReturnFileOrSentence(resIDAudio);
    }
}
