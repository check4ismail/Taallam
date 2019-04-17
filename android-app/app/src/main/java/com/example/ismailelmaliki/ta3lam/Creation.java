package com.example.ismailelmaliki.ta3lam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IsmailElmaliki on 8/15/17.
 */

public abstract class Creation<T> implements Serializable {

    private String[] elements;              // elements stored from LetterCreator, WordCreator or SentenceCreator
    private List<Integer> currentChoice;    // current correct choice that will be display
    private int currentPosition = 0;        // Keeps track of the currentPosition
    private List<Integer> multipleChoice;   // Stores multipleChoice within specific function

    private int correct;                    // Amount that's correct by user
    private List<Integer> incorrectQs;      // stores the answer of correct in which question was answered incorrectly by user
    private int indexOfIncorrect = 0;       // used to keep track of amount incorrect by user

    private ArrayList<T> returnFileOrSentence;  // Stores either Integer (of audio file or photo) or String (to represent an English Sentence)

    public Creation() {

    }

    // Sets member
    protected void setReturnFileOrSentence(ArrayList<T> returnFileOrSentence) {

        this.returnFileOrSentence = returnFileOrSentence;
    }

    // Sets members
    protected void setElements(String[] elements) {

        this.elements = elements;

        correct = 0;
        incorrectQs = new ArrayList<>();
        currentChoice = new ArrayList<>(elements.length);
        for(int i = 0; i < elements.length; i++)
            currentChoice.add(i);

        Collections.shuffle(currentChoice);
    }

    // Generate a random number based on length of elements
    private int generateRandom() {
        return (int)(Math.random() * elements.length);
    }

    // This is where multiple choice is generated, then returned as a String
    // so it can be used by LetterActivity, WordActivity, and SentenceActivity
    public String[] getChoices() {

        generateRandomMultipleChoice();

        // After ensuring all values are now unique, multiple choice
        // is shuffled
        Collections.shuffle(multipleChoice);

        String[] elementChoices = new String[]
        {
            elements[multipleChoice.get(0)],
            elements[multipleChoice.get(1)],
            elements[multipleChoice.get(2)],
            elements[multipleChoice.get(3)]
        };

        return elementChoices;
    }

    /*
        Will ensure that the first three multiple choice answers
        are unique, random, and non-repetitive
     */
    private void generateRandomMultipleChoice() {

        multipleChoice = new ArrayList<Integer>(4);

        multipleChoice.add(generateRandom());
        multipleChoice.add(generateRandom());
        multipleChoice.add(generateRandom());
        multipleChoice.add(currentChoice.get(currentPosition));

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                // Avoid comparing the same position
                if(i == j)
                    continue;

                else
                {
                    // If it's equal to current choice, new number is generated
                    // and loop will reset
                    if(multipleChoice.get(i).equals(currentChoice.get(currentPosition)))
                    {
                        multipleChoice.set(i, generateRandom());
                        j = -1;
                    }

                    // If loop isn't reset, then multiple choice value is
                    // compared with another

                    // If values are the same, random number is generated, then
                    // inner loop resets
                    if(j != -1)
                    {
                        if(multipleChoice.get(i).equals(multipleChoice.get(j)))
                        {
                            multipleChoice.set(i, generateRandom());
                            j = -1;
                        }
                    }
                }
            }
        }
    }

    // Return correct audio file, image, or English sentence based on
    // correct answer displayed from elements[currentChoice.get(currentPosition)]
    // Is dynamic and applies to Letter, Word and Sentence Creation classes
    public T getFileOrSentence() {

        return returnFileOrSentence.get(currentChoice.
                            get(currentPosition));
    }

    // Once user selects a choice, correct is incremented
    // If answered incorrectly, then incorrectQ gets an added element
    // with correct answer and indexOfIncorrect is incremented
    // From there new choices become generated then returned
    public String[] refreshMultipleChoice(String answer) {

        if(answer.equals(elements[currentChoice.
                get(currentPosition)]))
            correct++;
        else
        {
            incorrectQs.add(currentChoice.get(currentPosition));
            indexOfIncorrect++;
        }

        currentPosition++;
        if(questionsComplete())
        {
            return null;
        }

        // Move on to next multiple choice question
        return getChoices();
    }

    // Determines if questions are complete
    public boolean questionsComplete() {

        if(currentPosition > (currentChoice.size() - 1))
            return true;
        else
            return false;
    }

    // Gets current position (or current question being answered)
    public int getCurrentPosition() {

        return (currentPosition + 1);
    }

    // Returns the number that are correct by user
    public int getCorrect() {

        return correct;
    }

    // Returns total questions
    public int getTotalQuestions() {

        return currentChoice.size();
    }

    // This will process questions answered incorrectly, and allow a fresh start
    // so user can attempt to answer those questions he/she got wrong
    public void refreshCurrentChoice() {

        currentChoice.clear();
        currentChoice = new ArrayList<>(indexOfIncorrect);

        for(int i = 0; i < indexOfIncorrect; i++)
        {
            currentChoice.add(incorrectQs.get(i));
        }

        Collections.shuffle(currentChoice);

        currentPosition = 0;
        correct = 0;
        incorrectQs = null;
        incorrectQs = new ArrayList<>();
        indexOfIncorrect = 0;
    }

}
