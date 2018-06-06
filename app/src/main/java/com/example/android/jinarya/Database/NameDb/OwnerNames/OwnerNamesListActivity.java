package com.example.android.jinarya.Database.NameDb.OwnerNames;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.android.jinarya.AddNameActivity;
import com.example.android.jinarya.Database.NameDb.NamesContract;
import com.example.android.jinarya.Database.NameDb.NamesDbHelper;
import com.example.android.jinarya.Objects.Name;
import com.example.android.jinarya.OwnerInfoViewActivity;
import com.example.android.jinarya.R;
import com.example.android.jinarya.RelationshipNameActivity;
import com.example.android.jinarya.ReportsListActivity;
import com.example.android.jinarya.WelcomeActivity;

import static com.example.android.jinarya.PreNameActivity.VIEW_NAME;
import static com.example.android.jinarya.PreNameActivity.aboutAppMenuChosen;
import static com.example.android.jinarya.RelationshipNameActivity.ownerGender;
import static com.example.android.jinarya.RelationshipNameActivity.relationshipGuestAge;
import static com.example.android.jinarya.RelationshipNameActivity.relationshipGuestName;
import static com.example.android.jinarya.RelationshipNameActivity.relationshipOwnerAge;
import static com.example.android.jinarya.RelationshipNameActivity.relationshipOwnerName;
import static com.example.android.jinarya.RelationshipNameActivity.relationshipOwnerViewName;
import static com.example.android.jinarya.RelationshipNameActivity.relationshipRelationship;
import static com.example.android.jinarya.RelationshipNameActivity.relationshipViewName;

/**
 * Created by Aadhyamo on 10/02/18.
 */

public class OwnerNamesListActivity extends AppCompatActivity
        implements OwnerNamesListAdapter.OwnerNamesListItemClickListener,
        OwnerNamesListAdapter.OwnerNamesListItemLongClickListener {

    private String LOG_TAG = this.getClass().getSimpleName();

    private NamesDbHelper dbHelper;

    private SQLiteDatabase mDb;

    private OwnerNamesListAdapter mAdapter;

    private RecyclerView mNamesList;

    private Cursor cursor;

    private FloatingActionButton addNameFab;

    private TextView emptyView;

    private LottieAnimationView animationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_names_list);

        mNamesList = findViewById(R.id.all_names_list);

        emptyView = findViewById(R.id.all_names_empty_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mNamesList.setLayoutManager(layoutManager);

        mNamesList.setHasFixedSize(true);

        dbHelper = new NamesDbHelper(this);

        mDb = dbHelper.getReadableDatabase();

        cursor = getAllOwnerNames();

        mAdapter = new OwnerNamesListAdapter(cursor,
                this, this);

        mNamesList.setAdapter(mAdapter);

        animationView = findViewById(R.id.emptyNamesAnimationView);

        configureFloatingActionButton();

        configureEmptyOrFull();
    }

    private void configureAnimationView() {
        animationView.setAnimation("empty-box-animation.json");
        animationView.loop(true);
        animationView.playAnimation();

    }

    private void configureEmptyOrFull() {

        if ( mAdapter.getItemCount() == 0 ) {
            configureAnimationView();
            animationView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.VISIBLE);
            mNamesList.setVisibility(View.GONE);

        }
    }

    @Override
    public void onListItemClick(long namesId) {

        Intent intent = getIntent();

        Name name = retrieveNameOnClick(namesId);

        int ageSelection;

        if ( name.getAge().equals(getString(R.string.eighteen))) {
            ageSelection = 1;
        } else if (name.getAge().equals(getString(R.string.twentyFive))) {
            ageSelection = 2;
        } else if ( name.getAge().equals(getString(R.string.thirtyFive))) {
            ageSelection = 3;
        } else if ( name.getAge().equals(getString(R.string.fortyFive))) {
            ageSelection = 4;
        } else if ( name.getAge().equals(getString(R.string.fiftyFive))){
            ageSelection = 5;
        } else {
            ageSelection = 0;
        }

        Intent relationshipNameActivityIntent = new Intent(this,
                RelationshipNameActivity.class);

        relationshipNameActivityIntent.putExtra(relationshipViewName, relationshipOwnerViewName);

        relationshipNameActivityIntent.putExtra(relationshipOwnerName, name.getName());
        relationshipNameActivityIntent.putExtra(relationshipOwnerAge, ageSelection);

        relationshipNameActivityIntent.putExtra(relationshipGuestName,
                intent.getStringExtra(relationshipGuestName));
        relationshipNameActivityIntent.putExtra(relationshipGuestAge,
                intent.getIntExtra(relationshipGuestAge, 0));

        relationshipNameActivityIntent.putExtra(relationshipRelationship,
                intent.getIntExtra(relationshipRelationship,0));
        cursor.close();
        startActivity(relationshipNameActivityIntent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    @Override
    public void onListItemLongClick(long namesId) {

        deleteNameAlert(namesId);

    }

    private Cursor getAllOwnerNames () {

        String mOwnerGender = ownerGender;

        String selection = NamesContract.NamesEntry.COLUMN_GENDER + " = ?";

        String[] ownerGender = {mOwnerGender};

        Cursor cursor = mDb.query(
                NamesContract.NamesEntry.TABLE_NAME,
                null,
                selection,
                ownerGender,
                null,
                null,
                NamesContract.NamesEntry.COLUMN_NAME + " ASC"
        );

        Log.i(LOG_TAG, "Cursor retrieved size is "
                + cursor.getCount());
        return cursor;
    }


    private void configureFloatingActionButton() {

        addNameFab = findViewById(R.id.all_names_list_fab);
        addNameFab.setOnClickListener(v -> intentToAddNameActivity());
    }

    public void intentToAddNameActivity() {
        Intent intent = new Intent(this, AddNameActivity.class);
        intent.putExtra(VIEW_NAME,this.getClass().getName());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, RelationshipNameActivity.class));
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

    public void intentToWelcomeActivity() {
        startActivity(new Intent(this,WelcomeActivity.class));
    }


    private void intentToResultListActivity() {
        startActivity(new Intent(this, ReportsListActivity.class));
    }

    private void intentToOwnerInfoActivity() {
        startActivity(new Intent(this, OwnerInfoViewActivity.class));
    }

    private void openLegalsPageOnWebsite() {
        String url = getString(R.string.legal_disclaimer_page);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void sendFeedbackViaEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.developer_email_id)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body));

        startActivity(Intent.createChooser(intent, getString(R.string.email_title)));
    }

    private Name retrieveNameOnClick(long namesId) {

        mDb = dbHelper.getReadableDatabase();
        String selection = NamesContract.NamesEntry._ID + "= ?";
        String[] selectionArgs = {Long.toString(namesId)};

        cursor = mDb.query(
                NamesContract.NamesEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);

        cursor.moveToFirst();
        String name = cursor.getString((cursor.getColumnIndex
                (NamesContract.NamesEntry.COLUMN_NAME)));
        String age = cursor.getString((cursor.getColumnIndex
                (NamesContract.NamesEntry.COLUMN_AGE)));
        String gender = cursor.getString((cursor.getColumnIndex
                (NamesContract.NamesEntry.COLUMN_GENDER)));


        Name nameObject = new Name(name, age, gender);

        return nameObject;

    }

    private void deleteNameAlert(long namesIdId) {

        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.name_delete_sure))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) ->
                        deleteName(namesIdId))
                .setNegativeButton(android.R.string.no, null).show();

    }

    private void deleteName(long namesIdId) {

        Log.i(LOG_TAG, "Adapter Size before deleting is "
                + cursor.getCount());

        mDb = dbHelper.getWritableDatabase();
        String whereClause = NamesContract.NamesEntry._ID + "= ?";
        String[] namesIdString = {Long.toString(namesIdId)};
        mDb.delete(NamesContract.NamesEntry.TABLE_NAME,
                whereClause, namesIdString);
        cursor = getAllOwnerNames();
        mAdapter.swapOwnerNamesCursor(cursor);

        Log.i(LOG_TAG, "Adapter Size after deleting is "
                + cursor.getCount());
        Toast.makeText(getBaseContext(), getString(R.string.name_deleted),
                Toast.LENGTH_SHORT).show();

        configureEmptyOrFull();
    }


}
