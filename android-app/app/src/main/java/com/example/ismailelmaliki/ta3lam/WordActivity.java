package com.example.ismailelmaliki.ta3lam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by IsmailElmaliki on 8/5/17.
 */

public class WordActivity extends AppCompatActivity {

    private TextView[] choiceByIndex;       // Refers to multiple choice from Word layout
    private TextView questionAmount;        // Refers to text to display number of questions
    private ImageView imageView;            // This object is used to display the image file

    private Creation<Integer> wordCreate;   // Object which references WordCreation (Integer represents R.id.file of photo, which is Integer)
    private String[] choices;               // Used to store multiple choice generated from WordCreation object

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);         // References activity_word.xml layout
        this.setTitle("Words");                         // Title is set to Words
        setVars();                                      // Variables are initialized

        Intent i = getIntent();

        if(i.hasExtra("wordObject"))                     // If user chooses "Continue option", this will
        {                                               // reference WordCreation object from last activity
            wordCreate = (WordCreation) i.
                    getSerializableExtra("wordObject");
            wordCreate.refreshCurrentChoice();
        }

        else
            wordCreate = new WordCreation();            // If user chooses start over or
                                                        // it's user's first time, new instance of object is created
        choices = wordCreate.getChoices();              // Fills in all choices with letters
        fillChoices();                                  // Method is called to set TextView members
        questionAmount.setText(wordCreate.getCurrentPosition() + " / " +
                wordCreate.getTotalQuestions());        // Displays number of current question of the total
        imageView.setImageResource(wordCreate.getFileOrSentence());         // Displays current image associated to correct answer/choice
    }

    // Initializes members
    private void setVars() {

        choiceByIndex = new TextView[]
        {
            (TextView)findViewById(R.id.txtWord1),
            (TextView)findViewById(R.id.txtWord2),
            (TextView)findViewById(R.id.txtWord3),
            (TextView)findViewById(R.id.txtWord4)
        };
        questionAmount = (TextView)findViewById(R.id.txtQuesWord);
        imageView = (ImageView)findViewById(R.id.photoWord);
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
            case(R.id.txtWord1):
                choices = wordCreate.refreshMultipleChoice(choiceByIndex[0].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
                break;

            case(R.id.txtWord2):
                choices = wordCreate.refreshMultipleChoice(choiceByIndex[1].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
                break;

            case(R.id.txtWord3):
                choices = wordCreate.refreshMultipleChoice(choiceByIndex[2].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
                break;

            case(R.id.txtWord4):
                choices = wordCreate.refreshMultipleChoice(choiceByIndex[3].
                        getText().toString());
                checkQuestionsProgress();
                fillChoices();
        }
    }

    // Populates textView and ImageView
    private void fillChoices() {

        if(!wordCreate.questionsComplete())
        {
            choiceByIndex[0].setText(choices[0]);
            choiceByIndex[1].setText(choices[1]);
            choiceByIndex[2].setText(choices[2]);
            choiceByIndex[3].setText(choices[3]);

            imageView.setImageResource(wordCreate.getFileOrSentence());
        }
    }

    private void checkQuestionsProgress() {

        // If the questions are completed, the WordCreation object
        // is passed off to CompletedActivity
        if(wordCreate.questionsComplete())
        {
            Creation<Integer> wordCreation = wordCreate;
            Intent i = new Intent(this, CompletedActivity.class);
            i.putExtra("Title", "Words");
            i.putExtra("wordObject", wordCreation);
            startActivity(i);
        }

        // Otherwise, the correct question amount is displayed via TextView
        else
            questionAmount.setText(wordCreate.getCurrentPosition() + " / " +
                    wordCreate.getTotalQuestions());
    }
}
