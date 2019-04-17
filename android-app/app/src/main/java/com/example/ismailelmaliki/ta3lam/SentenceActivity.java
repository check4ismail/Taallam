package com.example.ismailelmaliki.ta3lam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by IsmailElmaliki on 8/5/17.
 */

public class SentenceActivity extends AppCompatActivity{

    private TextView[] choiceByIndex;               // Refers to multiple choice from Sentence layout
    private TextView questionAmount;                // Refers to text to display number of questions
    private TextView engSentence;                   // Refers to text to display english sentence corresponding to correct Arabic sentence

    private Creation<String> sentenceCreate;        // Object which references LetterCreation(String represents the sentence in English, which is a String)
    private String[] choices;                       // Used to store multiple choice generated from SentenceCreation object

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);     // References activity_sentences.xml layout
        this.setTitle("Sentences");                     // Title is set to Sentences
        setVars();                                      // Variables are initialized

        Intent i = getIntent();
        if(i.hasExtra("sentenceObject"))                 // If user chooses "Continue option", this will
        {                                                   // reference SentenceCreation object from last activity
            sentenceCreate = (SentenceCreation) i.
                    getSerializableExtra("sentenceObject");
            sentenceCreate.refreshCurrentChoice();
        }

        else
            sentenceCreate = new SentenceCreation();        // If user chooses start over or
                                                            // it's user's first time, new instance of object is created
        choices = sentenceCreate.getChoices();              // Fills in all choices with letters


        fillChoices();                                      // Method is called to set TextView members
        questionAmount.setText(sentenceCreate.getCurrentPosition() + " / " +
                sentenceCreate.getTotalQuestions());        // Displays number of current question of the total
        engSentence.setText(sentenceCreate.getFileOrSentence());        // Displays correct English sentence
    }

    // Initializes members
    private void setVars() {

        choiceByIndex = new TextView[]
        {
            (TextView)findViewById(R.id.choice1),
            (TextView)findViewById(R.id.choice2),
            (TextView)findViewById(R.id.choice3),
            (TextView)findViewById(R.id.choice4)
        };
        questionAmount = (TextView)findViewById(R.id.txtQuesSentence);
        engSentence = (TextView) findViewById(R.id.sentEng);
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
            case(R.id.choice1):
                choices = sentenceCreate.refreshMultipleChoice(choiceByIndex[0].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
                break;

            case(R.id.choice2):
                choices = sentenceCreate.refreshMultipleChoice(choiceByIndex[1].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
                break;

            case(R.id.choice3):
                choices = sentenceCreate.refreshMultipleChoice(choiceByIndex[2].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
                break;

            case(R.id.choice4):
                choices = sentenceCreate.refreshMultipleChoice(choiceByIndex[3].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
        }
    }

    // Populates textView & English sentence
    private void fillChoices() {

        if(!sentenceCreate.questionsComplete())
        {
            choiceByIndex[0].setText(choices[0]);
            choiceByIndex[1].setText(choices[1]);
            choiceByIndex[2].setText(choices[2]);
            choiceByIndex[3].setText(choices[3]);

            engSentence.setText(sentenceCreate.
                                getFileOrSentence());
        }
    }

    private void checkQuestionsProgress() {

        // If the questions are completed, the SentenceCreation object
        // is passed off to CompletedActivity
        if(sentenceCreate.questionsComplete())
        {
            Creation<String> sentenceCreation = sentenceCreate;
            Intent i = new Intent(this, CompletedActivity.class);
            i.putExtra("Title", "Sentences");
            i.putExtra("sentenceObject", sentenceCreation);
            startActivity(i);
        }

        // Otherwise, the correct question amount is displayed via TextView
        else
            questionAmount.setText(sentenceCreate.getCurrentPosition() + " / " +
                    sentenceCreate.getTotalQuestions());
    }

}
