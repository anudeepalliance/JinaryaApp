package com.example.android.jinarya;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.jinarya.Database.ReportsDb.ReportsContract;
import com.example.android.jinarya.Database.ReportsDb.ReportsDbHelper;

import static com.example.android.jinarya.PreNameActivity.aboutAppMenuChosen;

public class ReportActivity extends AppCompatActivity {

    private String LOG_TAG = this.getClass().getSimpleName();

    private String reportId;

    private String ownerName;
    private String guestName;

    private SQLiteDatabase mDb;

    private int reportCompatibilityScore;

    private ImageView compatibilityScoreCircleImageView;

    private String shareableReport;

    private ImageView restartButton;
    private ImageView shareButton;
    private ImageView feedbackButton;
    private ImageView reportListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        queryReportExtractAndAssignInfo();

        configureBottomFourButtons();
    }


    //extract Information from SQLite database here and add it to an appropriate object
    private void queryReportExtractAndAssignInfo() {

        TextView boyNameTextView = findViewById(R.id.report_boy_name);
        TextView boyAgeTextView = findViewById(R.id.report_boy_age);
        TextView girlNameTextView = findViewById(R.id.report_girl_name);
        TextView girlAgeTextView = findViewById(R.id.report_girl_age);
        TextView timeTextView = findViewById(R.id.report_date);
        TextView testTypeTexView = findViewById(R.id.report_test_type);

        View insightsForOwnerBackground = findViewById(R.id.report_insights_for_owner_heading);
        TextView insightsForOwnerTextView = findViewById(R.id.result_insights_for_owner_text);

        compatibilityScoreCircleImageView = findViewById(R.id.report_score_circle);
        TextView compatibilityScoreTextView = findViewById(R.id.report_compatibility_score);
        TextView compatibilitySummaryTextView = findViewById(R.id.report_compatibility_score_summary);

        View insightsForGuestBackground = findViewById(R.id.report_insights_for_guest_heading);
        TextView adviceForOwnerTextView = findViewById(R.id.report_advice_for_guest_text);

        restartButton = findViewById(R.id.report_restart_button);
        shareButton = findViewById(R.id.report_share_button);
        feedbackButton = findViewById(R.id.report_feedback_button);
        reportListButton = findViewById(R.id.report_reports_button);

        ReportsDbHelper dbHelper = new ReportsDbHelper(this);
        mDb = dbHelper.getReadableDatabase();
        Intent intent = getIntent();
        reportId = intent.getStringExtra("reportId");

        Log.i(LOG_TAG, "Report Id received is: "
                + reportId);

        String selection = ReportsContract.ReportsEntry._ID + "=?";
        String[] selectionArgs = {reportId};


        @SuppressLint("Recycle") Cursor cursor = mDb.query(
                ReportsContract.ReportsEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        ownerName = cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_OWNER_NAME));

        String ownerAge = cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_OWNER_AGE));

        ownerAge = "Aged : " + ownerAge;

        guestName = cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_GUEST_NAME));

        String guestAge = cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_GUEST_AGE));

        guestAge = "Aged : " + guestAge;

        String time = cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_TIMESTAMP));

        String testType = cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_TEST_TYPE));

        testType = testType + " Test";

        String reportInsights = cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_REPORT_INSIGHTS_FOR_OWNER));

        reportCompatibilityScore = cursor.getInt(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_COMPATIBILITY_SCORE));

        String reportScoreString = Integer.toString(reportCompatibilityScore);

        String reportCompatibilityFeedback = cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_COMPATIBILITY_SCORE_FEEDBACK));

        String reportAdviceSummary = cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_REPORT_ADVICE_FOR_OWNER));

        //TODO delete this after testing

        String test = cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_OWNER_NAME)) + cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_OWNER_GENDER)) + cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_GUEST_NAME)) + cursor.getString(cursor.getColumnIndex
                (ReportsContract.ReportsEntry.COLUMN_GUEST_GENDER));

        TextView textView = findViewById(R.id.gender_testing_text);

        textView.setText(test);
        //TODO until here

        cursor.close();


        //set the TextViews to display data from the database
        boyNameTextView.setText(ownerName);

        boyAgeTextView.setText(ownerAge);

        girlNameTextView.setText(guestName);

        girlAgeTextView.setText(guestAge);

        timeTextView.setText(time);

        testTypeTexView.setText(testType);

        insightsForOwnerTextView.setText(reportInsights);

        compatibilityScoreTextView.setText(reportScoreString);

        setScoreCircleColor();

        compatibilitySummaryTextView.setText(reportCompatibilityFeedback);

        adviceForOwnerTextView.setText(reportAdviceSummary);

        shareableReport = reportInsights + reportCompatibilityScore + reportCompatibilityFeedback + reportAdviceSummary;
    }


    public void intentToPreNameActivity() {

        Intent preNameActivity = new Intent(this, PreNameActivity.class);
        startActivity(preNameActivity);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }




    private void deleteReportAndNavigateToReportsList() {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to Delete this Report ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> deleteReport())
                .setNegativeButton(android.R.string.no, null).show();



    }

    private void deleteReport() {
        String[] reportIdString = {reportId};

        mDb.delete(ReportsContract.ReportsEntry.TABLE_NAME,
                ReportsContract.ReportsEntry._ID + "= ?", reportIdString );

        Intent reportsListActivity = new Intent(this, ReportsListActivity.class);
        startActivity(reportsListActivity);

        Toast mToast = Toast.makeText(getBaseContext(), "Report Deleted", Toast.LENGTH_SHORT);
        mToast.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.delete_report, menu);//Menu Resource, Menu
        getMenuInflater().inflate(R.menu.send_feedback_menu, menu);
        getMenuInflater().inflate(R.menu.reports_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_report_share_menu:
                shareReport();
                return true;
            case R.id.delete_report_delete_menu:
                deleteReportAndNavigateToReportsList();
                return true;
            case R.id.send_feedback_menu:
                sendFeedbackViaEmail();
                return true;
            case  R.id.pre_name_welcome_menu:
                aboutAppMenuChosen = true;
                intentToWelcomeActivity();
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

    private void setScoreCircleColor() {

        if ( reportCompatibilityScore <= 30 ) {
            compatibilityScoreCircleImageView.setColorFilter(Color.parseColor("#ff1a1a"));
        } else if ( reportCompatibilityScore > 30 && reportCompatibilityScore <= 60) {
            compatibilityScoreCircleImageView.setColorFilter(Color.parseColor("#ff6600"));
        } else if ( reportCompatibilityScore > 60 && reportCompatibilityScore <= 89) {
            compatibilityScoreCircleImageView.setColorFilter(Color.parseColor("#008ae0"));
        } else {
            compatibilityScoreCircleImageView.setColorFilter(Color.parseColor("#00e600"));
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

    public void intentToReportsListActivity() {
        Intent reportsListActivity = new Intent(this, ReportsListActivity.class);
        startActivity(reportsListActivity);
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

}
