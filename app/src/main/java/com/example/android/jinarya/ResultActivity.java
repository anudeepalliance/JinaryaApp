package com.example.android.jinarya;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.android.jinarya.Database.ReportsDb.ReportsContract;
import com.example.android.jinarya.Database.ReportsDb.ReportsDbHelper;
import com.example.android.jinarya.BackGroundJobs.Notifications.NotificationUtils;
import com.example.android.jinarya.Objects.ReportObject;

import static com.example.android.jinarya.OwnerInfoEditActivity.dateFormat;
import static com.example.android.jinarya.OwnerInfoEditActivity.formatAndShortenName;
import static com.example.android.jinarya.PreNameActivity.REPORTS_DATABASE_SIZE_LIMIT;
import static com.example.android.jinarya.PreNameActivity.aboutAppMenuChosen;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper1.calculateFriendshipQuestionsPaper1Results;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper2.calculateFriendshipQuestionsPaper2Results;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper3.calculateFriendshipQuestionsPaper3Results;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper4.calculateFriendshipQuestionsPaper4Results;
//import static com.example.android.jinarya.Questions.FriendshipQuestionPapers.FriendshipQuestionPaper5.calculateFriendshipQuestionsPaper5Results;
//import static com.example.android.jinarya.Questions.RelationshipQuestionPapers.RelationshipQuestionPaper2.calculateRelationshipQuestionsPaper2Results;
//import static com.example.android.jinarya.Questions.RelationshipQuestionPapers.RelationshipQuestionPaper3.calculateRelationshipQuestionsPaper3Results;
//import static com.example.android.jinarya.Questions.RelationshipQuestionPapers.RelationshipQuestionPaper4.calculateRelationshipQuestionsPaper4Results;
//import static com.example.android.jinarya.Questions.RelationshipQuestionPapers.RelationshipQuestionPaper5.calculateRelationshipQuestionsPaper5Results;
import static com.example.android.jinarya.Questions.RelationshipQuestionPapers.RelationshipQuestionPaper1.*;

import java.util.Calendar;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    private String LOG_TAG = this.getClass().getSimpleName();

    int randomPaper;

    private String ownerName;
    private String guestName;

    private String ownerAge;
    private String guestAge;

    private String ownerGender;
    private String guestGender;


    private String relationshipHistory;
    private String testType;

    private ArrayList<String> ownerAnswers;
    private ArrayList<String> guestAnswers;

    private TextView pleaseWaitMessageTextView;

    private TextView ownerNameTextView;
    private TextView ownerAgeTextView;
    private TextView guestNameTextView;
    private TextView guestAgeTextView;
    private TextView dateTextView;
    private TextView testTypeTextView;

    private View nameLine;

    private View insightsForOwnerHeadingBackground;
    private TextView insightsForOwnerHeadingTextView;
    private TextView insightsForOwnerContentTextView;

    private View insightsForGuestHeadingBackground;
    private TextView insightsForGuestHeadingTextView;
    private TextView insightsForGuestContentTextView;

    private TextView compatibilityHeadingTextView;
    private TextView compatibilityScoreTextView;
    private ImageView compatibilityScoreCircleImageView;
    private TextView compatibilitySummaryTextView;

    private View adviceForOwnerHeadingBackground;
    private TextView adviceForOwnerHeadingTextView;
    private TextView adviceForOwnerContentTextView;

    private View adviceForGuestHeadingBackground;
    private TextView adviceForGuestHeadingTextView;
    private TextView adviceForGuestContentTextView;

    private View conclusionLine;

    private ImageView restartButton;
    private ImageView shareButton;
    private ImageView feedbackButton;
    private ImageView reportListButton;

    private TextView restartButtonText;
    private TextView shareButtonText;
    private TextView feedbackButtonText;
    private TextView allReportsButtonText;

    private int compatibilityScore;
    private String compatibilityFeedback;

    private String insightsForOwner;
    private String insightsForGuest;

    private String adviceForOwner;
    private String adviceForGuest;

    private String shareableReport;

    private SQLiteDatabase mDb;

    private String currentDateAndTimeString;

    private ReportObject reportObject;

    private LottieAnimationView animationView;

    private static final long WAIT_ANIMATION_DURATION = 2000;

    private static final long WAIT_DURATION_IF_ANIMATION_DISABLED = 1000;

    private Intent getIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ReportsDbHelper dbHelper = new ReportsDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        getIntentInfoAndAssign();

        calculateResultAndSetIt();

        hideAllViewsForWaitMessageDisplay();
        
        configureProgressAnimationThenSuccessAnimationThenShowResults();

        configureBottomFourButtons();

        saveReport();

        clearNotificationsIfConditionMet();
    }



    @SuppressLint("SetTextI18n")
    private void calculateResultAndSetIt() {
        //since owner answers are in revers -> reverse them so they can be compared
        Collections.reverse(ownerAnswers);

        if (testType.equals(getString(R.string.relationship_test_type))) {

            switch (randomPaper) {

                case 1:
                    if ( ownerGender.equals(getString(R.string.male))) {
                        reportObject = calculateRelationshipQuestionsPaper1Results(guestName, ownerName, guestAnswers, ownerAnswers);
                    } else {
                        reportObject = calculateRelationshipQuestionsPaper1Results(ownerName, guestName, ownerAnswers, guestAnswers);
                    }
                    break;
                case 2:
//                        reportObject = calculateRelationshipQuestionsPaper2Results(ownerName, guestName, ownerAnswers, guestAnswers);
                    break;
                case 3:
//                        reportObject = calculateRelationshipQuestionsPaper3Results(ownerName, guestName, ownerAnswers, guestAnswers);
                    break;
                case 4:
//                        reportObject = calculateRelationshipQuestionsPaper4Results(ownerName, guestName, ownerAnswers, guestAnswers);
                    break;
                case 5:
                    break;
            }
        } else if ( testType.equals(getString(R.string.friendship_test_type))) {

            switch (randomPaper) {

                case 1:
//                        reportObject = calculateFriendshipQuestionsPaper1Results(guestName, ownerName, guestAnswers, ownerAnswers);
                    break;
                case 2:
//                        reportObject = calculateFriendshipQuestionsPaper2Results(ownerName, guestName, ownerAnswers, guestAnswers);
                    break;
                case 3:
//                        reportObject = calculateFriendshipQuestionsPaper3Results(ownerName, guestName, ownerAnswers, guestAnswers);
                    break;
                case 4:
//                        reportObject = calculateFriendshipQuestionsPaper4Results(ownerName, guestName, ownerAnswers, guestAnswers);
                    break;
                case 5:
                    break;
            }

        }

        String partner1Name = reportObject.getPartner1Name();
        insightsForOwnerHeadingTextView.setText("Insights for " + partner1Name);

        insightsForOwner = reportObject.getInsightsForPartner1();
        insightsForOwnerContentTextView.setText(insightsForOwner);

        String partner2Name = reportObject.getPartner2Name();
        insightsForGuestHeadingTextView.setText("Insights for " + partner2Name);

        insightsForGuest = reportObject.getInsightsForPartner2();
        insightsForGuestContentTextView.setText(insightsForGuest);

        adviceForOwnerHeadingTextView.setText("Advice for " + partner1Name);
        adviceForOwner = reportObject.getAdviceForPartner1();
        adviceForOwnerContentTextView.setText(adviceForOwner);

        adviceForGuestHeadingTextView.setText("Advice for " + partner2Name);
        adviceForGuest = reportObject.getAdviceForPartner2();
        adviceForGuestContentTextView.setText(adviceForGuest);

        compatibilityScore = reportObject.getCompatibilityScore();
        compatibilityScoreTextView.setText(Integer.toString(compatibilityScore));

        compatibilityFeedback = reportObject.getCompatibilityScoreFeedback();
        compatibilitySummaryTextView.setText(compatibilityFeedback);


        // TODO shareable text report for whatsApp or fbMessenger
        shareableReport = " ";
    }

    private void setScoreCircleColor() {

        if ( compatibilityScore <= 30 ) {
            compatibilityScoreCircleImageView.setColorFilter(Color.parseColor("#ff1a1a"));
        } else if ( compatibilityScore > 30 && compatibilityScore <= 60) {
            compatibilityScoreCircleImageView.setColorFilter(Color.parseColor("#ff6600"));
        } else if ( compatibilityScore > 60 && compatibilityScore <= 89) {
            compatibilityScoreCircleImageView.setColorFilter(Color.parseColor("#008ae0"));
        } else {
            compatibilityScoreCircleImageView.setColorFilter(Color.parseColor("#00e600"));
        }
    }



    // delete if database entries exceed the specified limit
    private void deleteEntryFromReportIfLimitExceeds() {

        String[] columns = {ReportsContract.ReportsEntry._ID};

        @SuppressLint("Recycle") Cursor cursor = mDb.query(
                ReportsContract.ReportsEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                ReportsContract.ReportsEntry.COLUMN_TIMESTAMP
        );

        if ( cursor.getCount() > REPORTS_DATABASE_SIZE_LIMIT) {

            cursor.moveToFirst();
            int oldestEntryId = cursor.getInt
                    (cursor.getColumnIndex(ReportsContract.ReportsEntry._ID));
            Log.i(LOG_TAG, "Oldest column id is" + oldestEntryId);

            String[] oldestEntryIdString = {Integer.toString(oldestEntryId)};
            mDb.delete(ReportsContract.ReportsEntry.TABLE_NAME,
                    ReportsContract.ReportsEntry._ID + "= ?", oldestEntryIdString );
        }

    }


    // add to SQLite Database here
    private void saveReport() {

        deleteEntryFromReportIfLimitExceeds();

        ContentValues reportCvs = new ContentValues();

        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_OWNER_NAME, ownerName);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_OWNER_AGE, ownerAge);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_OWNER_GENDER, ownerGender);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_GUEST_NAME, guestName);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_GUEST_AGE, guestAge);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_GUEST_GENDER, guestGender);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_RELATIONSHIP_HISTORY, relationshipHistory);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_TEST_TYPE, testType);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_COMPATIBILITY_SCORE, compatibilityScore);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_COMPATIBILITY_SCORE_FEEDBACK, compatibilityFeedback);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_REPORT_INSIGHTS_FOR_OWNER, insightsForOwner);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_REPORT_INSIGHTS_FOR_GUEST, insightsForGuest);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_REPORT_ADVICE_FOR_OWNER, adviceForOwner);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_REPORT_ADVICE_FOR_GUEST, adviceForGuest);
        reportCvs.put(ReportsContract.ReportsEntry.COLUMN_TIMESTAMP, currentDateAndTimeString);

        mDb.insert(ReportsContract.ReportsEntry.TABLE_NAME, null, reportCvs);
    }


    public void intentToPreNameActivity() {
        Intent preNameActivity = new Intent(this, PreNameActivity.class);
        startActivity(preNameActivity);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    private void configureBottomFourButtons() {

        restartButton.setOnClickListener(view -> intentToPreNameActivity());

        reportListButton.setOnClickListener(view -> intentToReportsListActivity());

        feedbackButton.setOnClickListener(v -> sendFeedbackViaEmail());

        shareButton.setOnClickListener(view -> shareReport());
    }

    private void shareReport() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareableReport);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name) +
                        " test of " + ownerName + " and " + guestName);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getString(R.string.share_report_with_friends)));
    }


    @Override
    protected void onStop() {
        super.onStop();

        ownerName = null;
        guestName = null;

        ownerAge = null;
        guestAge = null;

        ownerAnswers = null;
        guestAnswers = null;

        shareableReport = null;

        Log.i(LOG_TAG, "Questions Activity onStop method called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.reports_menu, menu);//Menu Resource, Menu
        getMenuInflater().inflate(R.menu.send_feedback_menu, menu);
        getMenuInflater().inflate(R.menu.reports_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reports_menu:
                intentToReportsListActivity();
                return true;
            case R.id.report_share_menu:
                shareReport();
                return true;
            case  R.id.pre_name_welcome_menu:
                aboutAppMenuChosen = true;
                intentToWelcomeActivity();
                return true;
            case R.id.send_feedback_menu:
                 sendFeedbackViaEmail();
                return true;
            case  R.id.pre_name_owner_info_menu:
                intentToOwnerInfoActivity();
                return true;
            case R.id.pre_name_legal:
                openLegalsPageOnWebsite();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void intentToWelcomeActivity() {
        Intent welcomeActivity = new Intent(this,WelcomeActivity.class);
        startActivity(welcomeActivity);
    }


    public void intentToReportsListActivity() {
        Intent reportsListActivity = new Intent(this, ReportsListActivity.class);
        startActivity(reportsListActivity);
    }

    private void intentToOwnerInfoActivity() {
        Intent intentToOwnerInfoActivity = new Intent(this, OwnerInfoViewActivity.class);
        startActivity(intentToOwnerInfoActivity);
    }

    private void openLegalsPageOnWebsite() {
        String url = getString(R.string.legal_disclaimer_page);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @SuppressLint("SetTextI18n")
    private void getIntentInfoAndAssign() {

        getIntent = getIntent();
        randomPaper = getIntent.getIntExtra("randomPaper",1);
        ownerName = getIntent.getStringExtra("ownerName");
        guestName = getIntent.getStringExtra("guestName");

        ownerAge = getIntent.getStringExtra("ownerAge");
        guestAge = getIntent.getStringExtra("guestAge");

        ownerGender = getIntent.getStringExtra("ownerGender");
        guestGender = getIntent.getStringExtra("guestGender");

        DateFormat timeFormat = android.text.format.DateFormat.
                getTimeFormat(getApplicationContext());

        Calendar c = Calendar.getInstance();

        currentDateAndTimeString = dateFormat.format(c.getTime()) + "\n" +
                timeFormat.format(c.getTime());

//        currentDateAndTimeString = dateFormat.format(c.getTime()) + " " +
//                timeFormat.format(c.getTime());

        String currentTimeString = timeFormat.format(c.getTime());

        //retrieve the value but don't display it, added to the database for may be use later
        relationshipHistory = getIntent.getStringExtra("relationshipHistory");

        testType = getIntent.getStringExtra("testType");

        ownerAnswers = getIntent.getStringArrayListExtra("ownerAnswers");
        guestAnswers = getIntent.getStringArrayListExtra("guestAnswers");

        animationView = findViewById(R.id.animation_view);

        pleaseWaitMessageTextView = findViewById(R.id.please_wait_text);

        ownerNameTextView = findViewById(R.id.result_owner_name);
        ownerAgeTextView = findViewById(R.id.result_owner_age);

        ownerName = formatAndShortenName(ownerName);
        ownerNameTextView.setText(ownerName);
        ownerAgeTextView.setText("Age: " + ownerAge);

        guestNameTextView = findViewById(R.id.result_guest_name);
        guestAgeTextView = findViewById(R.id.result_guest_age);

        guestName = formatAndShortenName(guestName);
        guestNameTextView.setText(guestName);
        guestAgeTextView.setText("Age: " + guestAge);

        dateTextView = findViewById(R.id.result_date);
        dateTextView.setText(currentTimeString);

        testTypeTextView = findViewById(R.id.result_test_type);
        testTypeTextView.setText(testType + " Test");

        nameLine = findViewById(R.id.result_name_line);

        insightsForOwnerHeadingBackground = findViewById(R.id.result_insights_for_owner_background);
        insightsForOwnerHeadingTextView = findViewById(R.id.result_insights_for_owner_heading);
        insightsForOwnerContentTextView = findViewById(R.id.result_insights_for_owner_text);

        insightsForGuestHeadingBackground = findViewById(R.id.result_insights_for_guest_background);
        insightsForGuestHeadingTextView = findViewById(R.id.result_insights_for_guest_heading);
        insightsForGuestContentTextView = findViewById(R.id.result_insights_for_guest_text);

        compatibilityHeadingTextView = findViewById(R.id.result_compatibility_score_heading_label);
        compatibilityHeadingTextView.setText(R.string.compatibility_score);
        compatibilityScoreTextView = findViewById(R.id.result_score_percentage);
        compatibilityScoreCircleImageView = findViewById(R.id.result_score_circle);

        compatibilitySummaryTextView = findViewById(R.id.result_compatibility_score_summary);

        adviceForOwnerHeadingBackground = findViewById(R.id.result_advice_for_owner_background);
        adviceForOwnerHeadingTextView = findViewById(R.id.result_advice_for_owner_heading);
        adviceForOwnerContentTextView = findViewById(R.id.result_advice_for_owner_text);

        adviceForGuestHeadingBackground = findViewById(R.id.result_advice_for_guest_background);
        adviceForGuestHeadingTextView = findViewById(R.id.result_advice_for_guest_heading);
        adviceForGuestContentTextView = findViewById(R.id.result_advice_for_guest_text);

        conclusionLine = findViewById(R.id.result_advice_line);

        restartButton = findViewById(R.id.result_restart_button);
        shareButton = findViewById(R.id.result_share_button);
        feedbackButton = findViewById(R.id.result_feedback_button);
        reportListButton = findViewById(R.id.result_reports_button);

        restartButtonText = findViewById(R.id.result_restart_text);
        shareButtonText = findViewById(R.id.result_share_text);
        feedbackButtonText = findViewById(R.id.result_feedback_text);
        allReportsButtonText = findViewById(R.id.result_all_reports_text);

        Log.i(LOG_TAG, "guestSelectedAnswers received  answers are: "
                + ownerAnswers.toString());
        Log.i(LOG_TAG, "ownerSelectedAnswers received answers are: "
                + guestAnswers.toString());

    }

    private boolean areSystemAnimationsEnabled() {
        float duration, transition;
        duration = Settings.Global.getFloat(
                this.getContentResolver(),
                Settings.Global.ANIMATOR_DURATION_SCALE, 1);
        transition = Settings.Global.getFloat(
                this.getContentResolver(),
                Settings.Global.TRANSITION_ANIMATION_SCALE, 1);
        return (duration != 0 && transition != 0);
    }

    private void hideAllViewsForWaitMessageDisplay() {

        pleaseWaitMessageTextView.setVisibility(View.VISIBLE);
        animationView.setVisibility(View.VISIBLE);

        ownerNameTextView.setVisibility(View.GONE);
        ownerAgeTextView.setVisibility(View.GONE);
        guestNameTextView.setVisibility(View.GONE);
        guestAgeTextView.setVisibility(View.GONE);
        dateTextView.setVisibility(View.GONE);
        testTypeTextView.setVisibility(View.GONE);
        nameLine.setVisibility(View.GONE);

        insightsForOwnerHeadingBackground.setVisibility(View.GONE);
        insightsForOwnerHeadingTextView.setVisibility(View.GONE);
        insightsForOwnerContentTextView.setVisibility(View.GONE);
        insightsForGuestHeadingBackground.setVisibility(View.GONE);
        insightsForGuestHeadingTextView.setVisibility(View.GONE);
        insightsForGuestContentTextView.setVisibility(View.GONE);

        compatibilityHeadingTextView.setVisibility(View.GONE);

        compatibilityScoreTextView.setVisibility(View.GONE);
        compatibilityScoreCircleImageView.setVisibility(View.GONE);
        compatibilitySummaryTextView.setVisibility(View.GONE);

        adviceForOwnerHeadingBackground.setVisibility(View.GONE);
        adviceForOwnerHeadingTextView.setVisibility(View.GONE);
        adviceForOwnerContentTextView.setVisibility(View.GONE);
        adviceForGuestHeadingBackground.setVisibility(View.GONE);
        adviceForGuestHeadingTextView.setVisibility(View.GONE);
        adviceForGuestContentTextView.setVisibility(View.GONE);
        conclusionLine.setVisibility(View.GONE);

        restartButton.setVisibility(View.GONE);
        shareButton.setVisibility(View.GONE);
        feedbackButton.setVisibility(View.GONE);
        reportListButton.setVisibility(View.GONE);

        restartButtonText.setVisibility(View.GONE);
        shareButtonText.setVisibility(View.GONE);
        feedbackButtonText.setVisibility(View.GONE);
        allReportsButtonText.setVisibility(View.GONE);

    }

    private void displayResultViews() {

        pleaseWaitMessageTextView.setVisibility(View.GONE);
        animationView.setVisibility(View.GONE);

        ownerNameTextView.setVisibility(View.VISIBLE);
        ownerAgeTextView.setVisibility(View.VISIBLE);
        guestNameTextView.setVisibility(View.VISIBLE);
        guestAgeTextView.setVisibility(View.VISIBLE);
        dateTextView.setVisibility(View.VISIBLE);
        testTypeTextView.setVisibility(View.VISIBLE);
        nameLine.setVisibility(View.VISIBLE);

        insightsForOwnerHeadingBackground.setVisibility(View.VISIBLE);
        insightsForOwnerHeadingTextView.setVisibility(View.VISIBLE);
        insightsForOwnerContentTextView.setVisibility(View.VISIBLE);
        insightsForGuestHeadingBackground.setVisibility(View.VISIBLE);
        insightsForGuestHeadingTextView.setVisibility(View.VISIBLE);
        insightsForGuestContentTextView.setVisibility(View.VISIBLE);

        compatibilityHeadingTextView.setVisibility(View.VISIBLE);
        compatibilityScoreTextView.setVisibility(View.VISIBLE);
        compatibilityScoreCircleImageView.setVisibility(View.VISIBLE);
        compatibilitySummaryTextView.setVisibility(View.VISIBLE);

        adviceForOwnerHeadingBackground.setVisibility(View.VISIBLE);
        adviceForOwnerHeadingTextView.setVisibility(View.VISIBLE);
        adviceForOwnerContentTextView.setVisibility(View.VISIBLE);
        adviceForGuestHeadingBackground.setVisibility(View.VISIBLE);
        adviceForGuestHeadingTextView.setVisibility(View.VISIBLE);
        adviceForGuestContentTextView.setVisibility(View.VISIBLE);
        conclusionLine.setVisibility(View.VISIBLE);

        restartButton.setVisibility(View.VISIBLE);
        shareButton.setVisibility(View.VISIBLE);
        feedbackButton.setVisibility(View.VISIBLE);
        reportListButton.setVisibility(View.VISIBLE);

        restartButtonText.setVisibility(View.VISIBLE);
        shareButtonText.setVisibility(View.VISIBLE);
        feedbackButtonText.setVisibility(View.VISIBLE);
        allReportsButtonText.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {
        intentToPreNameActivity();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    public void clearNotificationsIfConditionMet() {
        Intent intent = getIntent();
        if (Objects.equals(intent.getStringExtra("clearNotifications"), "clearNotifications")) {
            NotificationUtils.clearAllNotifications(this);
            Log.i(LOG_TAG, "clearNotificationsIfConditionMet method called ");
        }
    }

    public void sendFeedbackViaEmail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.developer_email_id)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body));

        startActivity(Intent.createChooser(intent, getString(R.string.email_title)));
    }

    public void configureProgressAnimationThenSuccessAnimationThenShowResults() {

        if ( testType.equals(getString(R.string.friendship_test_type)) ) {

            animationView.setAnimation("bouncing_ball.json");
            animationView.loop(true);
            animationView.playAnimation();

        } else if ( testType.equals(getString(R.string.relationship_test_type)) ) {

            animationView.setAnimation("animation_love_shadow.json");
            animationView.loop(true);
            animationView.playAnimation();
        }

        // change animation after 5 seconds
        new Handler().postDelayed(this::showDoneAnimationThenResults, WAIT_ANIMATION_DURATION);
        setScoreCircleColor();

    }

    private void showDoneAnimationThenResults() {
        pleaseWaitMessageTextView.setVisibility(View.INVISIBLE);
        animationView.setAnimation("clipboard_tick_mark.json");
        animationView.loop(false);
        animationView.playAnimation();

        //checks if system animations are enabled, if not then directly display results
        // after 2 seconds without waiting for done animation to finish
        if ( areSystemAnimationsEnabled() ) {
            checkIfAnimationIsFinishedThenDisplayResult();
        } else {
            new Handler().postDelayed(this::displayResultViews,
                    WAIT_DURATION_IF_ANIMATION_DISABLED);
        }

    }

    private void checkIfAnimationIsFinishedThenDisplayResult(){
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                displayResultViews();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
