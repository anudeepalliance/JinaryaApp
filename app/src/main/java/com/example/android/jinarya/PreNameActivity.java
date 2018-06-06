package com.example.android.jinarya;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.android.jinarya.BackGroundJobs.Notifications.NotificationFireBaseJobScheduler;

public class PreNameActivity extends AppCompatActivity {

    private String LOG_TAG = this.getClass().getSimpleName();

    private String testType;

    private TextView friendshipButton;

    private TextView relationshipButton;

    public static Boolean aboutAppMenuChosen = false;

    private ImageView friendshipImage;

    private ImageView relationshipImage;

    public static final int REPORTS_DATABASE_SIZE_LIMIT = 500;

    public static final int NAMES_DATABASE_SIZE_LIMIT = 50;

    public static final int RELATIONSHIP_HISTORY_DATABASE_SIZE_LIMIT = 50;

    public static final String VIEW_NAME = "viewName";

    private LottieAnimationView animationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_name);

        friendshipButton = findViewById(R.id.friendship_type_button);

        relationshipButton = findViewById(R.id.relationship_type_button);

        animationView = findViewById(R.id.pre_name_animation_view);

        friendshipImage = findViewById(R.id.friendship_image_view);

        relationshipImage = findViewById(R.id.relationship_image_view);

        configureLottieAnimationView();

        configureFriendShipButton();

        configureRelationShipButton();

        //TODO delete before launch.. it activates a notification immediately on activity launch
//        NotificationUtils.dailyMorningNotification(this);

        //TODO notifications job disabled, enable it before launch
        NotificationFireBaseJobScheduler.dailyUsageReminder(this);

    }

    private void configureLottieAnimationView() {

        animationView.setAnimation("emoji.json");
        animationView.loop(true);
        animationView.setSpeed(0.4f);
        animationView.playAnimation();
    }


    private void configureFriendShipButton() {

        friendshipButton.setOnClickListener(v -> {
            testType = getString(R.string.friendship_test_type);
            Log.i(LOG_TAG, "testype assigned in prenameActivity is " + testType);
            //getIntent to friendshipNameActivity
            intentToFriendshipNameActivity();
        });

        friendshipImage.setOnClickListener(v -> {
            testType = getString(R.string.friendship_test_type);
            Log.i(LOG_TAG, "testype assigned in prenameActivity is " + testType);
            //getIntent to friendshipNameActivity
            intentToFriendshipNameActivity();
        });

    }

    private void configureRelationShipButton() {

        relationshipButton.setOnClickListener(v -> {
            //getIntent to relationshipNameActivity
            intentToRelationshipNameActivity();
        });

        relationshipImage.setOnClickListener(v -> {
            //getIntent to relationshipNameActivity
            intentToRelationshipNameActivity();
        });
    }

    private void intentToRelationshipNameActivity() {
        Intent relationshipNameActivity = new Intent(this,RelationshipNameActivity.class);
        startActivity(relationshipNameActivity);
    }

    private void intentToFriendshipNameActivity() {
        Intent friendshipNameActivity = new Intent(this,FriendshipNameActivity.class);
        startActivity(friendshipNameActivity);
    }

    @Override
    public void onBackPressed() {

        quitApp();

    }

    private void quitApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pre_name_menu, menu);//Menu Resource, Menu
        getMenuInflater().inflate(R.menu.send_feedback_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pre_name_reports_menu:
                intentToResultListActivity();
                return true;
            case  R.id.pre_name_welcome_menu:
                aboutAppMenuChosen = true;
                intentToWelcomeActivity();
                return true;
            case  R.id.pre_name_owner_info_menu:
                intentToOwnerInfoActivity();
                return true;
            case R.id.send_feedback_menu:
                sendFeedbackViaEmail();
                return true;
            case R.id.pre_name_legal:
                    openLegalsPageOnWebsite();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //TODO change the URL
    private void openLegalsPageOnWebsite() {
        String url = getString(R.string.legal_disclaimer_page);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void intentToResultListActivity() {
        Intent reportsListActivity = new Intent(this, ReportsListActivity.class);
        startActivity(reportsListActivity);
    }

    private void intentToOwnerInfoActivity() {
        Intent intentToOwnerInfoActivity = new Intent(this, OwnerInfoViewActivity.class);
        startActivity(intentToOwnerInfoActivity);
    }

    public void intentToWelcomeActivity() {
        Intent welcomeActivity = new Intent(this,WelcomeActivity.class);
        startActivity(welcomeActivity);
    }

    public void sendFeedbackViaEmail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.developer_email_id)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body));

        startActivity(Intent.createChooser(intent, getString(R.string.email_title)));
    }

}
