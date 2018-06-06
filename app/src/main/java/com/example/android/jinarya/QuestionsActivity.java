package com.example.android.jinarya;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.android.jinarya.Objects.QuestionsAndOptions;
import java.util.ArrayList;

//question Paper Java class references added below here
import static com.example.android.jinarya.PreTestActivity.randomNumber;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper1.FQP1BoyQuestions;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper1.FQP1GirlQuestions;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper2.FQP2BoyQuestions;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper2.FQP2GirlQuestions;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper3.FQP3BoyQuestions;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper3.FQP3GirlQuestions;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper4.FQP4BoyQuestions;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper4.FQP4GirlQuestions;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper5.FQP5BoyQuestions;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper5.FQP5GirlQuestions;
import static com.example.android.jinarya.Questions.RelationshipQuestionPapers.RelationshipQuestionPaper1.*;
//import static com.example.android.jinarya.Questions.RelationshipQuestionPapers.RelationshipQuestionPaper2.*;
//import static com.example.android.jinarya.Questions.RelationshipQuestionPapers.RelationshipQuestionPaper3.*;
//import static com.example.android.jinarya.Questions.RelationshipQuestionPapers.RelationshipQuestionPaper4.*;
//import static com.example.android.jinarya.Questions.RelationshipQuestionPapers.RelationshipQuestionPaper5.*;

public class QuestionsActivity extends AppCompatActivity {

    private String LOG_TAG = this.getClass().getSimpleName();

    private String ownerName;
    private String guestName;

    private String ownerAge;
    private String guestAge;

    private String ownerGender;
    private String guestGender;

    private String relationshipHistory;
    private String testType;

    private TextView ownerNameTextView;
    private TextView guestNameTextView;

    private TextView ownerInstructionsTextView;
    private TextView guestInstructionsTextView;

    private Button ownerInstructionsAcknowledgeButton;
    private Button guestInstructionsAcknowledgeButton;

    private TextView guestQuestion;
    private TextView ownerQuestion;

    private TextView guestOptionA;
    private TextView guestOptionB;
    private TextView guestOptionC;
    private TextView guestOptionD;

    private TextView ownerOptionA;
    private TextView ownerOptionB;
    private TextView ownerOptionC;
    private TextView ownerOptionD;

    private TextView guestWaitText;
    private TextView ownerWaitText;

    TextView guestChooseAnswerTextView;
    TextView ownerChooseAnswerTextView;

    public ArrayList<QuestionsAndOptions> guestQuestions;
    public ArrayList<QuestionsAndOptions> ownerQuestions;

    private int guestQuestionNumber;
    // owner questions & answers collected in reverse order
    private int ownerQuestionNumber;

    private Button guestNextButton;
    private Button ownerNextButton;

    public ArrayList<String> guestSelectedAnswers;
    public ArrayList<String> ownerSelectedAnswers;

    RadioGroup guestRadioGroup;
    RadioGroup ownerRadioGroup;

    Intent resultsActivity;

    private int questionPaperNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        getIntentInfoAndAssign();

        createQuestions();

        setFirstGuestQuestionAndConfigureNextButton();
        setFirstOwnerQuestionAndConfigureNextButton();

    }

    //gets all the info from the getIntent and assigns them to the variables
    @SuppressLint("SetTextI18n")
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

        guestNameTextView = findViewById(R.id.guest_name_label_question_activity);
        guestNameTextView.setText(guestName);

        ownerNameTextView = findViewById(R.id.owner_name_label_question_activity);
        ownerNameTextView.setText(ownerName);

        ownerInstructionsTextView = findViewById(R.id.owner_question_instructions);
        ownerInstructionsTextView.setText(getString(R.string.instructions_for_test) + " " + guestName);

        ownerInstructionsAcknowledgeButton = findViewById(R.id.owner_instructions_acknowledge);

        guestInstructionsTextView = findViewById(R.id.guest_question_instructions);
        guestInstructionsTextView.setText(getString(R.string.instructions_for_test) + " " + ownerName);

        guestInstructionsAcknowledgeButton = findViewById(R.id.guest_instructions_acknowledge);

        guestWaitText = findViewById(R.id.guest_wait_message);
        guestWaitText.setText("Great! Lets wait for " + ownerName + " to Finish.");
        ownerWaitText = findViewById(R.id.owner_wait_message);
        ownerWaitText.setText("Great! Lets wait for " + guestName + " to Finish.");

        guestQuestion = findViewById(R.id.guest_question);
        ownerQuestion = findViewById(R.id.owner_question);

        guestOptionA = findViewById(R.id.option_GuestA);
        guestOptionB = findViewById(R.id.option_GuestB);
        guestOptionC = findViewById(R.id.option_GuestC);
        guestOptionD = findViewById(R.id.option_GuestD);

        ownerOptionA = findViewById(R.id.option_OwnerA);
        ownerOptionB = findViewById(R.id.option_OwnerB);
        ownerOptionC = findViewById(R.id.option_OwnerC);
        ownerOptionD = findViewById(R.id.option_OwnerD);

        guestNextButton = findViewById(R.id.guest_next_button);
        ownerNextButton = findViewById(R.id.owner_next_button);

        guestSelectedAnswers = new ArrayList<>();
        ownerSelectedAnswers = new ArrayList<>();

        guestChooseAnswerTextView = findViewById(R.id.guest_choose_answer);
        ownerChooseAnswerTextView = findViewById(R.id.owner_choose_answer);

        Log.i(LOG_TAG, "owner name is " + ownerName);

    }

    private void showOwnerInstructions() {

        ownerQuestion.setVisibility(View.GONE);
        ownerOptionA.setVisibility(View.GONE);
        ownerOptionB.setVisibility(View.GONE);
        ownerOptionC.setVisibility(View.GONE);
        ownerOptionD.setVisibility(View.GONE);
        ownerNextButton.setVisibility(View.GONE);

        ownerInstructionsTextView.setVisibility(View.VISIBLE);
        ownerInstructionsAcknowledgeButton.setVisibility(View.VISIBLE);

        ownerInstructionsAcknowledgeButton.setOnClickListener(v -> {
            ownerInstructionsTextView.setVisibility(View.GONE);
            ownerInstructionsAcknowledgeButton.setVisibility(View.GONE);

            ownerQuestion.setVisibility(View.VISIBLE);
            ownerOptionA.setVisibility(View.VISIBLE);
            ownerOptionB.setVisibility(View.VISIBLE);
            ownerOptionC.setVisibility(View.VISIBLE);
            ownerOptionD.setVisibility(View.VISIBLE);
            ownerNextButton.setVisibility(View.VISIBLE);
        });

    }

    private void showGuestInstructions() {

        guestQuestion.setVisibility(View.GONE);
        guestOptionA.setVisibility(View.GONE);
        guestOptionB.setVisibility(View.GONE);
        guestOptionC.setVisibility(View.GONE);
        guestOptionD.setVisibility(View.GONE);
        guestNextButton.setVisibility(View.GONE);

        guestInstructionsTextView.setVisibility(View.VISIBLE);
        guestInstructionsAcknowledgeButton.setVisibility(View.VISIBLE);

        guestInstructionsAcknowledgeButton.setOnClickListener(v -> {
            guestInstructionsTextView.setVisibility(View.GONE);
            guestInstructionsAcknowledgeButton.setVisibility(View.GONE);

            guestQuestion.setVisibility(View.VISIBLE);
            guestOptionA.setVisibility(View.VISIBLE);
            guestOptionB.setVisibility(View.VISIBLE);
            guestOptionC.setVisibility(View.VISIBLE);
            guestOptionD.setVisibility(View.VISIBLE);
            guestNextButton.setVisibility(View.VISIBLE);
        });

    }



    private void randomGenerator() {

        questionPaperNumber = randomNumber(5,1);
    }


    public void createQuestions() {

        //Configure Female Questions
        showOwnerInstructions();
        showGuestInstructions();

        ownerQuestions = new ArrayList<>();
        guestQuestions = new ArrayList<>();

        randomGenerator();

        //TODO remove this test method that chooses a question paper that I want

        questionPaperNumber = 1;

        if ( testType.equals(getString(R.string.relationship_test_type))) {

            if ( ownerGender.equals(getString(R.string.female))) {

                switch (questionPaperNumber) {

                    case 1: ownerQuestions = RQP1GirlQuestions();
                            guestQuestions = RQP1BoyQuestions();
                        break;
//                    case 2: ownerQuestions = RQP2GirlQuestions();
//                            guestQuestions = RQP2BoyQuestions();
//                        break;
//                    case 3: ownerQuestions = RQP3GirlQuestions();
//                            guestQuestions = RQP3BoyQuestions();
//                        break;
//                    case 4: ownerQuestions = RQP4GirlQuestions();
//                            guestQuestions = RQP5BoyQuestions();
//                        break;
//                    case 5: ownerQuestions = RQP5GirlQuestions();
//                            guestQuestions = RQP5BoyQuestions();
//                        break;
                    default:
                            ownerQuestions = RQP1GirlQuestions();
                            guestQuestions = RQP1BoyQuestions();
                        break;

                }
            } else if ( ownerGender.equals(getString(R.string.male))){
                switch (questionPaperNumber) {

                    case 1: ownerQuestions = RQP1BoyQuestions();
                            guestQuestions = RQP1GirlQuestions();
                        break;
//                    case 2: ownerQuestions = RQP2BoyQuestions();
//                            guestQuestions = RQP2GirlQuestions();
//                        break;
//                    case 3: ownerQuestions = RQP3BoyQuestions();
//                            guestQuestions = RQP3GirlQuestions();
//                        break;
//                    case 4: ownerQuestions = RQP4BoyQuestions();
//                            guestQuestions = RQP1GirlQuestions();
//                        break;
//                    case 5: ownerQuestions = RQP5BoyQuestions();
//                            guestQuestions = RQP5GirlQuestions();
//                        break;
                    default:
                        ownerQuestions = RQP1BoyQuestions();
                        guestQuestions = RQP1GirlQuestions();
                        break;
                }
            }

        } else if ( testType.equals(getString(R.string.friendship_test_type))) {


//                switch (questionPaperNumber) {
//
//                    case 1: ownerQuestions = FQP1GirlQuestions();
//                        guestQuestions = FQP1BoyQuestions();
//                        break;
//                    case 2: ownerQuestions = FQP2GirlQuestions();
//                        guestQuestions = FQP2BoyQuestions();
//                        break;
//                    case 3: ownerQuestions = FQP3GirlQuestions();
//                        guestQuestions = FQP3BoyQuestions();
//                        break;
//                    case 4: ownerQuestions = FQP4GirlQuestions();
//                        guestQuestions = FQP4BoyQuestions();
//                        break;
//                    case 5: ownerQuestions = FQP5GirlQuestions();
//                        guestQuestions = FQP5BoyQuestions();
//                        break;
//                    default:
//                        ownerQuestions = FQP1GirlQuestions();
//                        guestQuestions = FQP1BoyQuestions();
//                        break;
//                }
        }

        //questions for owner appear in reverse order whereas for guest they appear in normal order
        ownerQuestionNumber = ownerQuestions.size() - 1;

        ownerRadioGroup = findViewById(R.id.owner_radio_group);

        guestQuestionNumber = 0;

        guestRadioGroup = findViewById(R.id.guest_radio_group);

    }

    public void setFirstOwnerQuestionAndConfigureNextButton() {

        ownerChooseAnswerTextView.setVisibility(View.INVISIBLE);

        ownerQuestion.setText(ownerQuestions.get(ownerQuestionNumber).getQuestion());

        ownerOptionA.setText(ownerQuestions.get(ownerQuestionNumber)
                .getOptionA().getOptionText());
        ownerOptionA.setTag(ownerQuestions.get(ownerQuestionNumber)
                .getOptionA().getOptionId());

        ownerOptionB.setText(ownerQuestions.get(ownerQuestionNumber)
                .getOptionB().getOptionText());
        ownerOptionB.setTag(ownerQuestions.get(ownerQuestionNumber)
                .getOptionB().getOptionId());

        ownerOptionC.setText(ownerQuestions.get(ownerQuestionNumber)
                .getOptionC().getOptionText());
        ownerOptionC.setTag(ownerQuestions.get(ownerQuestionNumber)
                .getOptionC().getOptionId());

        ownerOptionD.setText(ownerQuestions.get(ownerQuestionNumber)
                .getOptionD().getOptionText());
        ownerOptionD.setTag(ownerQuestions.get(ownerQuestionNumber)
                .getOptionD().getOptionId());

        ownerQuestionNumber = ownerQuestionNumber - 1;

        ownerNextButton.setOnClickListener(this::assignNextOwnerQuestion);

    }


    public void assignNextOwnerQuestion(View v) {


        String selectedOption = null;
        String selectedId;

        if ( //case: answer option is selected, so add the answer option to the answers Array List
                ownerRadioGroup.getCheckedRadioButtonId() != -1 ) {

            ownerChooseAnswerTextView.setVisibility(View.INVISIBLE);

            int selectedOptionId = ownerRadioGroup.getCheckedRadioButtonId();

            RadioButton selectedRadioButton = findViewById(selectedOptionId);

            selectedOption = selectedRadioButton.getText().toString();

            if ( selectedOption == ownerOptionA.getText() ) {
                selectedId = ownerOptionA.getTag().toString();
            } else if ( selectedOption == ownerOptionB.getText() ) {
                selectedId = ownerOptionB.getTag().toString();
            } else if ( selectedOption == ownerOptionC.getText() ) {
                selectedId = ownerOptionC.getTag().toString();
            } else {
                selectedId = ownerOptionD.getTag().toString();
            }

            ownerSelectedAnswers.add(selectedId);

            Log.i(LOG_TAG, "added "+ selectedOption + " to the arraylist");

            Log.i(LOG_TAG, "ownerSelectedAnswers: " + ownerSelectedAnswers.toString());

        } else {//no answer option selected

            ownerChooseAnswerTextView.setVisibility(View.VISIBLE);
        }

        if ( //answer option selected & added in the previous case, more questions to come so assign it
                ownerQuestionNumber != -1 && selectedOption != null ) {

            ownerChooseAnswerTextView.setVisibility(View.INVISIBLE);

            //TODO reactivate this before launch : clear the option selection as a new question is displayed now
//            ownerRadioGroup.clearCheck();

            ownerQuestion.setText(ownerQuestions.get(ownerQuestionNumber).getQuestion());

            ownerOptionA.setText(ownerQuestions.get(ownerQuestionNumber)
                    .getOptionA().getOptionText());
            ownerOptionA.setTag(ownerQuestions.get(ownerQuestionNumber)
                    .getOptionA().getOptionId());

            ownerOptionB.setText(ownerQuestions.get(ownerQuestionNumber)
                    .getOptionB().getOptionText());
            ownerOptionB.setTag(ownerQuestions.get(ownerQuestionNumber)
                    .getOptionB().getOptionId());

            ownerOptionC.setText(ownerQuestions.get(ownerQuestionNumber)
                    .getOptionC().getOptionText());
            ownerOptionC.setTag(ownerQuestions.get(ownerQuestionNumber)
                    .getOptionC().getOptionId());

            ownerOptionD.setText(ownerQuestions.get(ownerQuestionNumber)
                    .getOptionD().getOptionText());
            ownerOptionD.setTag(ownerQuestions.get(ownerQuestionNumber)
                    .getOptionC().getOptionId());

            ownerQuestionNumber--;
            Log.i(LOG_TAG, "ran Female question change method");
            Log.i(LOG_TAG, "number of questions left is: " + ownerQuestionNumber);

        } else if (//no more questions left, boy still answering, so display a wait for boy message
                ownerQuestionNumber == -1 && guestNameTextView.getVisibility() == View.VISIBLE ) {

            ownerNameTextView.setVisibility(View.GONE);

            ownerQuestion.setVisibility(View.GONE);
            ownerChooseAnswerTextView.setVisibility(View.GONE);

            ownerOptionA.setVisibility(View.GONE);
            ownerOptionB.setVisibility(View.GONE);
            ownerOptionC.setVisibility(View.GONE);
            ownerOptionD.setVisibility(View.GONE);

            ownerNextButton.setVisibility(View.GONE);

            ownerWaitText.setVisibility(View.VISIBLE);

        } else if ( // no more questions left, answer option selected and added in the previous case, boy has finished answering so move to the ResultActivity
                ownerQuestionNumber == -1 && guestWaitText.getVisibility() == View.VISIBLE ) {

            addIntentInfoAndMoveToResultActivity();

        }

    }

    public void setFirstGuestQuestionAndConfigureNextButton() {

        guestQuestion.setText(guestQuestions.get(0).getQuestion());

        //used setTag method to assign the options IDs to each option as setting ID is not supported
        guestOptionA.setText(guestQuestions.get(0)
                .getOptionA().getOptionText());
        guestOptionA.setTag(guestQuestions.get(0)
                .getOptionA().getOptionId());

        guestOptionB.setText(guestQuestions.get(0)
                .getOptionB().getOptionText());
        guestOptionB.setTag(guestQuestions.get(0)
                .getOptionB().getOptionId());

        guestOptionC.setText(guestQuestions.get(0)
                .getOptionC().getOptionText());
        guestOptionC.setTag(guestQuestions.get(0)
                .getOptionC().getOptionId());

        guestOptionD.setText(guestQuestions.get(0)
                .getOptionD().getOptionText());
        guestOptionD.setTag(guestQuestions.get(0)
                .getOptionD().getOptionId());



        guestNextButton.setOnClickListener(this::assignNextGuestQuestion);

        Log.i(LOG_TAG, "number male questions left initially: " + guestQuestionNumber);
    }


    public void assignNextGuestQuestion(View v) {

        guestQuestionNumber = guestQuestionNumber + 1;
        String selectedOption = null;
        String selectedId;

        //case : option selected, so add the selected option ID to the answerArrayList
        if ( guestRadioGroup.getCheckedRadioButtonId() != -1 ) {

            guestChooseAnswerTextView.setVisibility(View.INVISIBLE);

            int selectedOptionId = guestRadioGroup.getCheckedRadioButtonId();

            RadioButton selectedRadioButton = findViewById(selectedOptionId);

            selectedOption = selectedRadioButton.getText().toString();
            //extract option ID using TAG that was added before
            if ( selectedOption == guestOptionA.getText() ) {
                selectedId = guestOptionA.getTag().toString();
            } else if ( selectedOption == guestOptionB.getText() ) {
                selectedId = guestOptionB.getTag().toString();
            } else if ( selectedOption == guestOptionC.getText() ) {
                selectedId = guestOptionC.getTag().toString();
            } else {
                selectedId = guestOptionD.getTag().toString();
            }

            guestSelectedAnswers.add(selectedId);

            Log.i(LOG_TAG, "added "+ selectedOption + " to the arraylist");

            Log.i(LOG_TAG, "guestSelectedAnswers: " + guestSelectedAnswers.toString());

        } else {
            //case : no option selected
            guestChooseAnswerTextView.setVisibility(View.VISIBLE);
        }

        if (//case : option was selected and added and more questions to come
                guestQuestionNumber < guestQuestions.size() && selectedOption != null ) {

            guestChooseAnswerTextView.setVisibility(View.INVISIBLE);

            //TODO reactive this before launch : cleared option selection as next question is being assigned
//            guestRadioGroup.clearCheck();

            guestQuestion.setText(guestQuestions.get(guestQuestionNumber).getQuestion());

            guestOptionA.setText(guestQuestions.get(guestQuestionNumber)
                    .getOptionA().getOptionText());
            guestOptionA.setTag(guestQuestions.get(guestQuestionNumber)
                    .getOptionA().getOptionId());

            guestOptionB.setText(guestQuestions.get(guestQuestionNumber)
                    .getOptionB().getOptionText());
            guestOptionB.setTag(guestQuestions.get(guestQuestionNumber)
                    .getOptionB().getOptionId());

            guestOptionC.setText(guestQuestions.get(guestQuestionNumber)
                    .getOptionC().getOptionText());
            guestOptionC.setTag(guestQuestions.get(guestQuestionNumber)
                    .getOptionC().getOptionId());

            guestOptionD.setText(guestQuestions.get(guestQuestionNumber)
                    .getOptionD().getOptionText());
            guestOptionD.setTag(guestQuestions.get(guestQuestionNumber)
                    .getOptionD().getOptionId());

            Log.i(LOG_TAG, "ran Male question change method");
            Log.i(LOG_TAG, "number of Male questions left is: " + guestQuestionNumber);

        } else if // case: no more questions left, owner still answering questions, so show wait message
                ( guestQuestionNumber == guestQuestions.size() && ownerNameTextView.getVisibility() == View.VISIBLE ) {
            guestNameTextView.setVisibility(View.GONE);

            guestQuestion.setVisibility(View.GONE);
            guestChooseAnswerTextView.setVisibility(View.GONE);

            guestOptionA.setVisibility(View.GONE);
            guestOptionB.setVisibility(View.GONE);
            guestOptionC.setVisibility(View.GONE);
            guestOptionD.setVisibility(View.GONE);

            guestNextButton.setVisibility(View.GONE);

            guestWaitText.setVisibility(View.VISIBLE);
        } else if ( //no more questions left, girl has finished answering so move to the resultActivity
                guestQuestionNumber == guestQuestions.size() && ownerWaitText.getVisibility() == View.VISIBLE ) {

            addIntentInfoAndMoveToResultActivity();

        }

    }


    //Adds the info to the getIntent that needs to be sent to the Result activity and then move to it
    private void addIntentInfoAndMoveToResultActivity() {

        resultsActivity = new Intent(this, ResultActivity.class);
        resultsActivity.putExtra("questionPaperNumber", questionPaperNumber);
        resultsActivity.putExtra("ownerName", ownerName);
        resultsActivity.putExtra("ownerAge", ownerAge);
        resultsActivity.putExtra("ownerGender", ownerGender);
        resultsActivity.putExtra("ownerAnswers", ownerSelectedAnswers);
        Log.i(LOG_TAG, "guestSelectedAnswers being sent to the next activity is "
                + ownerSelectedAnswers.toString());

        resultsActivity.putExtra("relationshipHistory", relationshipHistory);
        resultsActivity.putExtra("testType", testType);

        resultsActivity.putExtra("guestName", guestName);
        resultsActivity.putExtra("guestAge", guestAge);
        resultsActivity.putExtra("guestGender", guestGender);
        resultsActivity.putExtra("guestAnswers", guestSelectedAnswers);
        Log.i(LOG_TAG, "ownerSelectedAnswers being sent to the next activity is "
                + guestSelectedAnswers.toString());

        resultsActivity.putExtra("testType", testType);

        startActivity(resultsActivity);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        ownerName = null;
        guestName = null;

        ownerAge = null;
        guestAge = null;

        guestQuestions = null;
        ownerQuestions = null;

        guestSelectedAnswers = null;
        ownerSelectedAnswers = null;

        Log.i(LOG_TAG, "Questions Activity onStop method called");

    }

    //alert dialog pops to double check if users indeed want to restart the test
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to Restart ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> intentToPreNameActivity())
                .setNegativeButton(android.R.string.no, null).show();
    }

    //called when back button is pressed, all information is kept intact without losing any info
    private void intentToPreNameActivity() {

        Log.i(LOG_TAG, "backButton pressed Called");
        Intent preNameActivity = new Intent(this, PreNameActivity.class);
        preNameActivity.putExtra("ownerName", ownerName);
        preNameActivity.putExtra("ownerAge", ownerAge);
        preNameActivity.putExtra("guestName", guestName);
        preNameActivity.putExtra("guestAge", guestAge);

        startActivity(preNameActivity);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

}
