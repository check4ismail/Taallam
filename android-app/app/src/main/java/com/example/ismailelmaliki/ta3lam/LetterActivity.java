package com.example.ismailelmaliki.ta3lam;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class LetterActivity extends AppCompatActivity {

    private TextView[] choiceByIndex;       // Refers to multiple choice from Letter layout
    private TextView questionAmount;        // Refers to text to display number of questions

    private Creation<Integer> ltrCreate;    // Object which references LetterCreation
    private String[] choices;               // Used to store multiple choice generated from LetterCreation object (Integer represents R.id.file of audio, which is Integer)
    private MediaPlayer mp;                 // This object is used to play the audio file

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);   // References activity_letter.xml layout
        this.setTitle("Letters");                   // Title is set to Letters
        setVars();                                  // Variables are initialized

        Intent i = getIntent();
        if(i.hasExtra("letterObject"))              // If user chooses "Continue option", this will
        {                                           // reference LetterCreation object from last activity
            ltrCreate = (LetterCreation)i.
                    getSerializableExtra("letterObject");
            ltrCreate.refreshCurrentChoice();
        }

        else
            ltrCreate = new LetterCreation();       // If user chooses start over or
                                                    // it's user's first time, new instance of object is created
        choices = ltrCreate.getChoices();           // Fills in all choices with letters
        fillChoices();                              // Method is called to set TextView members
        questionAmount.setText(ltrCreate.getCurrentPosition() + " / " +
                ltrCreate.getTotalQuestions());     // Displays number of current question of the total
    }

    // Initializes members
    private void setVars() {

        choiceByIndex = new TextView[]
        {
            (TextView)findViewById(R.id.txtLtr1),
            (TextView)findViewById(R.id.txtLtr2),
            (TextView)findViewById(R.id.txtLtr3),
            (TextView)findViewById(R.id.txtLtr4)
        };
        questionAmount = (TextView)findViewById(R.id.txtQuesLtr);
    }

    // onClick function
    public void onClick(View view) {

        // Based on choice selected, the following is executed:
        /*
            In each case, the choice selected by user is processed by calling
            refreshMultipleChoice method, then new set of multiple choice
            is returned and stored as choices

            From there, we check current progress of questions.

            If all questions aren't completed, then choices are filled via TextView by calling
            fillChoices()
         */
        switch(view.getId())
        {
            case(R.id.txtLtr1):
                choices = ltrCreate.refreshMultipleChoice(choiceByIndex[0].
                                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
                break;

            case(R.id.txtLtr2):
                choices = ltrCreate.refreshMultipleChoice(choiceByIndex[1].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
                break;

            case(R.id.txtLtr3):
                choices = ltrCreate.refreshMultipleChoice(choiceByIndex[2].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
                break;

            case(R.id.txtLtr4):
                choices = ltrCreate.refreshMultipleChoice(choiceByIndex[3].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
        }

        // Here, audio is reset to prevent delay or wearing out of audio object
        if(mp != null)
            mp.reset();
    }

    // Plays the audio file if sound icon is tapped by user
    // Correct audio file is played in accordance to current letter
    public void onClickIcon(View view) {

        mp = MediaPlayer.create(this, ltrCreate.getFileOrSentence());
        mp.start();
    }

    // Populates textView
    private void fillChoices() {

        if(!ltrCreate.questionsComplete())
        {
            choiceByIndex[0].setText(choices[0]);
            choiceByIndex[1].setText(choices[1]);
            choiceByIndex[2].setText(choices[2]);
            choiceByIndex[3].setText(choices[3]);
        }
    }

    private void checkQuestionsProgress() {

        // If the questions are completed, the LetterCreation object
        // is passed off to CompletedActivity
        if(ltrCreate.questionsComplete())
        {
            Creation<Integer> letterCreation = ltrCreate;
            Intent i = new Intent(this, CompletedActivity.class);
            i.putExtra("Title", "Letters");
            i.putExtra("letterObject", letterCreation);
            startActivity(i);
        }

        // Otherwise, the correct question amount is displayed via TextView
        else
            questionAmount.setText(ltrCreate.getCurrentPosition() + " / " +
                                   ltrCreate.getTotalQuestions());
    }

}
