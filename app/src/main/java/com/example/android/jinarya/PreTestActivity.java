package com.example.android.jinarya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PreTestActivity extends AppCompatActivity {

    private String LOG_TAG = this.getClass().getSimpleName();

    private Button doneLetsStart;

    private String ownerName;
    private String guestName;

    private String ownerAge;
    private String guestAge;

    private String ownerGender;
    private String guestGender;

    private String relationshipHistory;
    private String testType;

    Animation blinkAnimation;

    Intent questionsActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_test);

        getIntentInfoAndAssign();

    }

    //gets all the info from the getIntent and assigns them to the variables
    private void getIntentInfoAndAssign() {

        Intent intent = getIntent();
        ownerName = intent.getStringExtra("ownerName");
        guestName = intent.getStringExtra("guestName");

        ownerAge = intent.getStringExtra("ownerAge");
        guestAge = intent.getStringExtra("guestAge");

        ownerGender = intent.getStringExtra("ownerGender");
        guestGender = intent.getStringExtra("guestGender");

        relationshipHistory = intent.getStringExtra("relationshipHistory");
        testType = intent.getStringExtra("testType");

        Log.i(LOG_TAG, ownerName + ownerAge + guestName + guestAge + relationshipHistory + testType);


        ImageView upArrow = findViewById(R.id.up_arrow);
        ImageView downArrow = findViewById(R.id.down_arrow);

        TextView ownerNameView = findViewById(R.id.owner_name);
        TextView guestNameView = findViewById(R.id.guest_name);

        TextView ownerPositionPrompt = findViewById(R.id.owner_position_prompt);
        TextView guestPositionPrompt = findViewById(R.id.guest_position_prompt);

        Log.i(LOG_TAG, "owner name is " + ownerName);

        ownerNameView.setText(ownerName);
        guestNameView.setText(guestName);

        ownerPositionPrompt.setText(getString(R.string.position_prompt));
        guestPositionPrompt.setText(getString(R.string.position_prompt));

        doneLetsStart = findViewById(R.id.positions_taken_button);

        //coded out as it seems like it is creating confusion so no blinking
        blink(upArrow);
        blink(downArrow);


        questionsActivity = new Intent(this, QuestionsActivity.class);
        startQuestions();

    }

    private void startQuestions() {
        doneLetsStart.setOnClickListener(view -> {
            addIntentInfo();

            startActivity(questionsActivity);
        });
    }

    //Adds the info to the getIntent that needs to be sent to the next activity.
    private void addIntentInfo() {

        questionsActivity.putExtra("ownerName", ownerName);
        questionsActivity.putExtra("ownerAge", ownerAge);
        questionsActivity.putExtra("ownerGender", ownerGender);
        questionsActivity.putExtra("guestName", guestName);
        questionsActivity.putExtra("guestAge", guestAge);
        questionsActivity.putExtra("guestGender", guestGender);
        questionsActivity.putExtra("relationshipHistory", relationshipHistory);
        questionsActivity.putExtra("testType", testType);

    }


    public void blink(View view) {
        blinkAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        view.startAnimation(blinkAnimation);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        ownerName = null;
        guestName = null;

        ownerAge = null;
        guestAge = null;

        Log.i(LOG_TAG, "PreTest Activity onDestroy method called");

    }

    public static int randomNumber(int max, int min) {
        Random r = new Random();
        return r.nextInt(max) + min;
    }

}
