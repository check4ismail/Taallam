package com.example.ismailelmaliki.ta3lam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by IsmailElmaliki on 8/12/17.
 */

public class CompletedActivity extends AppCompatActivity {

    private TextView textView;                  // TextView to display correct amount to user
    private Button buttonContinue;              // Continue button
    private Button buttonStartOver;             // Start Over button
    private RelativeLayout layout;              // Relative layout
    private RelativeLayout.LayoutParams p;      // LayoutParams for Relative Layout
    private Intent i;                           // Intent
    private String title;                       // Stores title name

    private Creation creation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);                        // launches activity from activity_completed.xml

        // Members here are initialized
        layout = (RelativeLayout) findViewById(R.id.layoutCompleted);
        textView = (TextView)findViewById(R.id.answerText);
        buttonContinue = (Button)findViewById(R.id.continueButton);
        buttonStartOver = (Button)findViewById(R.id.startOverButton);

        i = getIntent();                    // gets intent from previous activity
        title = i.getStringExtra("Title");  // stores title from the previous activity
        this.setTitle(title);               // sets the title based on previous activity

        determineObjectType();              // This method determines value of creation object and type (whether it's LetterCreation, WordCreation,
                                            // or SentenceCreation

        // If user answered all questions correctly, only Start Over and Main Menu will be displayed (and Continue button will be removed
        // with Layout adjusting accordingly)
        if(creation.getCorrect() == creation.getTotalQuestions())
        {
            layout.removeView(buttonContinue);
            p = (RelativeLayout.LayoutParams)buttonStartOver.getLayoutParams();
            p.addRule(RelativeLayout.BELOW, R.id.answerText);
            buttonStartOver.setLayoutParams(p);
        }
    }

    // Based on the Title from previous activity, it will determine
    // correct object type then display to user amount of questions correct
    // out of total questions
    private void determineObjectType() {

        if(i.hasExtra("letterObject"))
        {
            creation = (LetterCreation)i.
                    getSerializableExtra("letterObject");
            textView.setText(creation.getCorrect() + " / " +
                    creation.getTotalQuestions() +
                    "\nCorrect");
        }

        else if(i.hasExtra("wordObject"))
        {
            creation = (WordCreation) i.
                    getSerializableExtra("wordObject");
            textView.setText(creation.getCorrect() + " / " +
                    creation.getTotalQuestions() +
                    "\nCorrect");
        }

        else if(i.hasExtra("sentenceObject"))
        {
            creation = (SentenceCreation) i.
                    getSerializableExtra("sentenceObject");
            textView.setText(creation.getCorrect() + " / " +
                    creation.getTotalQuestions() +
                    "\nCorrect");
        }
    }

    // onClick to control flow of button that's selected by user
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.continueButton:
                continueOptions();
                break;

            // Will create new activity of LetterActivity
            case R.id.startOverButton:
                startOverOptions();
                break;

            // Will return user to main menu
            case R.id.mainMenuButton:
                startActivity(new Intent(this, MainActivity.class));
        }
    }

    // If user hits continue, object would need to be passed over to ensure
    // continuity of questions that were answered incorrectly by the user
    private void continueOptions() {

        Intent intent;

        if(title.equals("Letters"))
        {
            intent = new Intent(this, LetterActivity.class);
            intent.putExtra("letterObject", creation);
            startActivity(intent);
        }

        else if(title.equals("Words"))
        {
            intent = new Intent(this, WordActivity.class);
            intent.putExtra("wordObject", creation);
            startActivity(intent);
        }

        else if(title.equals("Sentences"))
        {
            intent = new Intent(this, SentenceActivity.class);
            intent.putExtra("sentenceObject", creation);
            startActivity(intent);
        }
    }


    // Start over will create a fresh start of the previous activity
    private void startOverOptions() {

        if(title.equals("Letters"))
            startActivity(new Intent(this, LetterActivity.class));

        else if(title.equals("Words"))
            startActivity(new Intent(this, WordActivity.class));

        else if(title.equals("Sentences"))
            startActivity(new Intent(this, SentenceActivity.class));
    }

    // If back button is pressed by user when in CompletedActivity, then it will start
    // over the previous activity.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            startOverOptions();
            return true;
        }
        return false;
    }
}
