package com.example.android.jinarya.Objects;

/**
 * Created by Aadhyamo on 17/12/17.
 */

public class Option {

    String mAnswer;
    String mId;

    public Option(String answer, String Id) {
        mAnswer = answer;
        mId = Id;
    }


    public String getOptionText() {
        return mAnswer;
    }

    public String getOptionId() {
        return mId;
    }



}
