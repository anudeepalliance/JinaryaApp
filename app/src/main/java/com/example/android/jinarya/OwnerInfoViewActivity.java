package com.example.android.jinarya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_AGE_KEY;
import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_GENDER_KEY;
import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_INFO_FILE_NAME;
import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_NAME_KEY;
import static com.example.android.jinarya.PreNameActivity.aboutAppMenuChosen;

public class OwnerInfoViewActivity extends AppCompatActivity {

    private TextView ownerName;

    private TextView ownerAge;

    private TextView ownerGender;

    private FloatingActionButton ownerViewFab;

    private SharedPreferences ownerInfoPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_info_view);
        assignViews();
        getOwnerInfoAndPopulateView();
        configureFab();
    }

    private void assignViews() {

        ownerName = findViewById(R.id.owner_name_name_edit);

        ownerAge = findViewById(R.id.owner_name_age_spinner);

        ownerGender = findViewById(R.id.owner_name_gender_spinner);

        ownerViewFab = findViewById(R.id.owner_name_view_fab);
    }

    private void getOwnerInfoAndPopulateView() {

        ownerInfoPref = getApplicationContext().getSharedPreferences(OWNER_INFO_FILE_NAME, MODE_PRIVATE);

        if ( ownerInfoPref.contains(OWNER_NAME_KEY)) {


            ownerName.setText(ownerInfoPref.getString(OWNER_NAME_KEY, null));
            ownerAge.setText(ownerInfoPref.getString(OWNER_AGE_KEY,null));
            ownerGender.setText(ownerInfoPref.getString(OWNER_GENDER_KEY,null));
        }
    }

    private void configureFab() {

        ownerViewFab.setOnClickListener((View v) -> {

            intentToOwnerInfoEditActivity();
        });
    }

    private void intentToOwnerInfoEditActivity() {
        startActivity(new Intent(this, OwnerInfoEditActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.owner_info_menu, menu);//Menu Resource, Menu
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
            case R.id.send_feedback_menu:
                sendFeedbackViaEmail();
                return true;
            case R.id.pre_name_legal:
                openLegalsPageOnWebsite();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void intentToWelcomeActivity() {
        startActivity(new Intent(this,WelcomeActivity.class));
    }

    private void intentToResultListActivity() {
        startActivity(new Intent(this, ReportsListActivity.class));
    }

    private void intentToPreNameActivity() {
        startActivity(new Intent(this, PreNameActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    private void openLegalsPageOnWebsite() {
        String url = getString(R.string.legal_disclaimer_page);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intentToPreNameActivity();

    }

    public void sendFeedbackViaEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.developer_email_id)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body));

        startActivity(Intent.createChooser(intent, getString(R.string.email_title)));
    }

}
