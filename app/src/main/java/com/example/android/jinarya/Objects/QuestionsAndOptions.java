package com.example.android.jinarya.Objects;

/**
 * Created by Aadhyamo on 17/12/17.
 */

public class QuestionsAndOptions {

    private String mQuestion;

    private Option mOptionA;

    private Option mOptionB;

    private Option mOptionC;

    private Option mOptionD;


    public QuestionsAndOptions(String question, Option optionA, Option optionB,
                               Option optionC, Option optionD) {

        mQuestion = question;
        mOptionA = new Option(optionA.getOptionText(), optionA.getOptionId() );
        mOptionB = new Option(optionB.getOptionText(), optionB.getOptionId() );
        mOptionC = new Option(optionC.getOptionText(), optionC.getOptionId() );
        mOptionD = new Option(optionD.getOptionText(), optionD.getOptionId() );
    }

    public String getQuestion() {
        return mQuestion;
    }

    public Option getOptionA() {
        return mOptionA;
    }

    public Option getOptionB() {
        return mOptionB;
    }

    public Option getOptionC() {
        return mOptionC;
    }

    public Option getOptionD() {
        return mOptionD;
    }

}
