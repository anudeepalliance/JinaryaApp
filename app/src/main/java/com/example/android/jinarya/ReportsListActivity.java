package com.example.android.jinarya;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.android.jinarya.Database.ReportsDb.ReportsContract;
import com.example.android.jinarya.Database.ReportsDb.ReportsDbHelper;
import com.example.android.jinarya.Database.ReportsDb.ReportsListAdapter;

import static com.example.android.jinarya.PreNameActivity.aboutAppMenuChosen;

public class ReportsListActivity extends AppCompatActivity
        implements ReportsListAdapter.ListItemClickListener,
        ReportsListAdapter.ListItemLongClickListener {

    private String LOG_TAG = this.getClass().getSimpleName();

    private long reportId;

    private ReportsDbHelper dbHelper;

    private SQLiteDatabase mDb;

    private ReportsListAdapter mAdapter;

    private RecyclerView mReportsList;

    private Cursor cursor;

    private TextView emptyView;

    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_list);

        mReportsList = findViewById(R.id.reports_list);

        emptyView = findViewById(R.id.all_reports_empty_view);

        animationView = findViewById(R.id.emptyResultsAnimationView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mReportsList.setLayoutManager(layoutManager);

        mReportsList.setHasFixedSize(true);

        dbHelper = new ReportsDbHelper(this);

        mDb = dbHelper.getReadableDatabase();

        cursor = getAllReports();

        mAdapter = new ReportsListAdapter(cursor,
                this, this);

        configureFloatingActionButton();

        mReportsList.setAdapter(mAdapter);

        configureEmptyOrFull();
    }

    private void configureAnimationView() {
        animationView.setAnimation("empty-box-animation.json");
        animationView.loop(true);
        animationView.playAnimation();

    }

    private void configureEmptyOrFull() {

        if ( mAdapter.getItemCount() == 0 ) {

            emptyView.setVisibility(View.VISIBLE);
            configureAnimationView();
            animationView.setVisibility(View.VISIBLE);
            mReportsList.setVisibility(View.GONE);

        }
    }


    private void configureFloatingActionButton() {

        FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(v -> intentToPreNameActivity());
    }

    private Cursor getAllReports() {

        String[] columns = {
                ReportsContract.ReportsEntry._ID,
                ReportsContract.ReportsEntry.COLUMN_OWNER_NAME,
                ReportsContract.ReportsEntry.COLUMN_GUEST_NAME,
                ReportsContract.ReportsEntry.COLUMN_TIMESTAMP,
                ReportsContract.ReportsEntry.COLUMN_REPORT_INSIGHTS_FOR_OWNER,
                ReportsContract.ReportsEntry.COLUMN_COMPATIBILITY_SCORE,
                ReportsContract.ReportsEntry.COLUMN_TEST_TYPE
        };

        Cursor cursor = mDb.query(
                ReportsContract.ReportsEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                ReportsContract.ReportsEntry._ID + " DESC"
        );

        Log.i(LOG_TAG, "Cursor retrieved size is "
                + cursor.getCount());

        return cursor;
    }

    private void configureAndStartIntent() {

        Intent reportActivity = new Intent(this, ReportActivity.class);
        String reportIdString = Long.toString(reportId);
        reportActivity.putExtra("reportId", reportIdString);
        startActivity(reportActivity);
    }

    @Override
    public void onBackPressed() {
        intentToPreNameActivity();

    }

    private void intentToPreNameActivity() {

        Intent preNameActivityIntent = new Intent(this, PreNameActivity.class);
        startActivity(preNameActivityIntent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.send_feedback_menu, menu);
        getMenuInflater().inflate(R.menu.reports_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

    private void openLegalsPageOnWebsite() {
        String url = getString(R.string.legal_disclaimer_page);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void intentToOwnerInfoActivity() {
        Intent intentToOwnerInfoActivity = new Intent(this, OwnerInfoViewActivity.class);
        startActivity(intentToOwnerInfoActivity);
    }

    public void intentToWelcomeActivity() {
        Intent welcomeActivity = new Intent(this,WelcomeActivity.class);
        startActivity(welcomeActivity);
    }

    public void sendFeedbackViaEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.developer_email_id)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body));

        startActivity(Intent.createChooser(intent, getString(R.string.email_title)));
    }

    @Override
    public void onListItemClick(long reportId) {

        this.reportId = reportId;

        Log.i(LOG_TAG, "clicked itemIndex is " + reportId);

        configureAndStartIntent();
    }

    @Override
    public void onListItemLongClick(long reportId) {

        this.reportId = reportId;

        deleteReportAlert(reportId);

    }

    private void deleteReportAlert(long reportId) {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to Delete this Report ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) ->
                        deleteReport(reportId))
                .setNegativeButton(android.R.string.no, null).show();

    }

    private void deleteReport(long reportId) {

        Log.i(LOG_TAG, "Adapter Size before deleting is "
                + cursor.getCount());

        mDb = dbHelper.getWritableDatabase();
        String whereClause = ReportsContract.ReportsEntry._ID + "= ?";
        String[] reportIdString = {Long.toString(reportId)};
        mDb.delete(ReportsContract.ReportsEntry.TABLE_NAME,
                whereClause, reportIdString);
        cursor = getAllReports();
        mAdapter.swapCursor(cursor);

        configureEmptyOrFull();

        Log.i(LOG_TAG, "Adapter Size after deleting is "
                + cursor.getCount());
        Toast.makeText(getBaseContext(), getString(R.string.report_deleted),
                Toast.LENGTH_SHORT).show();
    }

}
