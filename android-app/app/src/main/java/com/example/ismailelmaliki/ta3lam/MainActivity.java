package com.example.ismailelmaliki.ta3lam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     // Refers to activity_main.xml
    }

    // Letter Button will start LetterActivity
    public void letterClick(View view) {

        startActivity(new Intent(this, LetterActivity.class));
    }

    // Word Button will start WordActivity
    public void wordClick(View view) {

        startActivity(new Intent(this, WordActivity.class));
    }

    // Sentence button will start SentenceActivity
    public void sentenceClick(View view) {

        startActivity(new Intent(this, SentenceActivity.class));
    }
}
