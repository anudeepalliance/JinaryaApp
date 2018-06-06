package com.example.android.jinarya.Database.NameDb.AllNames;

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
import com.example.android.jinarya.AddNameActivity;
import com.example.android.jinarya.Database.NameDb.NamesContract;
import com.example.android.jinarya.Database.NameDb.NamesDbHelper;
import com.example.android.jinarya.FriendshipNameActivity;
import com.example.android.jinarya.Objects.Name;
import com.example.android.jinarya.OwnerInfoViewActivity;
import com.example.android.jinarya.R;
import com.example.android.jinarya.ReportsListActivity;
import com.example.android.jinarya.WelcomeActivity;

import static com.example.android.jinarya.FriendshipNameActivity.friendshipGuestAge;
import static com.example.android.jinarya.FriendshipNameActivity.friendshipGuestGender;
import static com.example.android.jinarya.FriendshipNameActivity.friendshipGuestName;
import static com.example.android.jinarya.FriendshipNameActivity.friendshipGuestViewName;
import static com.example.android.jinarya.FriendshipNameActivity.friendshipOwnerAge;
import static com.example.android.jinarya.FriendshipNameActivity.friendshipOwnerGender;
import static com.example.android.jinarya.FriendshipNameActivity.friendshipOwnerName;
import static com.example.android.jinarya.FriendshipNameActivity.friendshipOwnerViewName;
import static com.example.android.jinarya.FriendshipNameActivity.friendshipRelationship;
import static com.example.android.jinarya.FriendshipNameActivity.friendshipViewName;
import static com.example.android.jinarya.PreNameActivity.VIEW_NAME;
import static com.example.android.jinarya.PreNameActivity.aboutAppMenuChosen;

public class AllNamesListActivity extends AppCompatActivity
        implements AllNamesListAdapter.allNamesListItemClickListener,
        AllNamesListAdapter.allNamesListItemLongClickListener{

    private String LOG_TAG = this.getClass().getSimpleName();

    private NamesDbHelper dbHelper;

    private SQLiteDatabase mDb;

    private AllNamesListAdapter mAdapter;

    private RecyclerView mNamesList;

    private Cursor cursor;

    private FloatingActionButton addNameFab;

    private TextView emptyView;

    private LottieAnimationView animationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_names_list);

        mNamesList = findViewById(R.id.all_names_list);

        emptyView = findViewById(R.id.all_names_empty_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mNamesList.setLayoutManager(layoutManager);

        animationView = findViewById(R.id.emptyNamesAnimationView);

        mNamesList.setHasFixedSize(true);

        dbHelper = new NamesDbHelper(this);

        mDb = dbHelper.getReadableDatabase();

        cursor = getAllNames();

        mAdapter = new AllNamesListAdapter(cursor,
                this, this);

        configureFloatingActionButton();

        mNamesList.setAdapter(mAdapter);

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
            emptyView.setVisibility(View.VISIBLE);
            animationView.setVisibility(View.VISIBLE);
            mNamesList.setVisibility(View.GONE);

        }
    }


    private Cursor getAllNames () {

         cursor = mDb.query(
                NamesContract.NamesEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                NamesContract.NamesEntry.COLUMN_NAME + " ASC"
        );

        Log.i(LOG_TAG, "Cursor retrieved size is "
                + cursor.getCount());
        return cursor;
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
        startActivity(new Intent(this, FriendshipNameActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void onListItemClick(long namesId) {

        Intent intent = getIntent();

        Name name = retrieveNameOnClick(namesId);

        int ageSelection;
        int genderSelection;

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

        if ( name.getGender().equals(getString(R.string.male))) {
            genderSelection = 1;
        } else if ( name.getGender().equals(getString(R.string.female))) {
            genderSelection = 2;
        } else {
            genderSelection = 0;
        }

        Intent friendshipActivityIntent = new Intent(this,
                FriendshipNameActivity.class);

        friendshipActivityIntent.putExtra(friendshipViewName,
                intent.getStringExtra(friendshipViewName));

        if ( intent.getStringExtra(friendshipViewName) != null) {


            if ( intent.getStringExtra(friendshipViewName).equals(friendshipOwnerViewName) ) {

                friendshipActivityIntent.putExtra(friendshipOwnerName, name.getName());
                friendshipActivityIntent.putExtra(friendshipOwnerAge, ageSelection);
                friendshipActivityIntent.putExtra(friendshipOwnerGender, genderSelection);

                friendshipActivityIntent.putExtra(friendshipGuestName,
                        intent.getStringExtra(friendshipGuestName));
                friendshipActivityIntent.putExtra(friendshipGuestAge,
                        intent.getIntExtra(friendshipGuestAge,0));
                friendshipActivityIntent.putExtra(friendshipGuestGender,
                        intent.getIntExtra(friendshipGuestGender, 0));

            } else if ( intent.getStringExtra(friendshipViewName).equals(friendshipGuestViewName)) {

                friendshipActivityIntent.putExtra(friendshipGuestName, name.getName());
                friendshipActivityIntent.putExtra(friendshipGuestAge, ageSelection);
                friendshipActivityIntent.putExtra(friendshipGuestGender, genderSelection);

                friendshipActivityIntent.putExtra(friendshipOwnerName,
                        intent.getStringExtra(friendshipOwnerName));
                friendshipActivityIntent.putExtra(friendshipOwnerAge,
                        intent.getIntExtra(friendshipOwnerAge, 0));
                friendshipActivityIntent.putExtra(friendshipOwnerGender,
                        intent.getIntExtra(friendshipOwnerGender,0));
            }
        } else {

            friendshipActivityIntent.putExtra(friendshipGuestName, name.getName());
            friendshipActivityIntent.putExtra(friendshipGuestAge, ageSelection);
            friendshipActivityIntent.putExtra(friendshipGuestGender, genderSelection);

            friendshipActivityIntent.putExtra(friendshipOwnerName,
                    intent.getStringExtra(friendshipOwnerName));
            friendshipActivityIntent.putExtra(friendshipOwnerAge,
                    intent.getIntExtra(friendshipOwnerAge, 0));
            friendshipActivityIntent.putExtra(friendshipOwnerGender,
                    intent.getIntExtra(friendshipOwnerGender,0));
        }



        friendshipActivityIntent.putExtra(friendshipRelationship,
                intent.getIntExtra(friendshipRelationship,0));

        cursor.close();
        startActivity(friendshipActivityIntent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    public void onListItemLongClick(long namesId) {

        deleteNameAlert(namesId);

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
        cursor = getAllNames();
        mAdapter.swapAllNamesCursor(cursor);

        Log.i(LOG_TAG, "Adapter Size after deleting is "
                + cursor.getCount());
        Toast.makeText(getBaseContext(), getString(R.string.name_deleted),
                Toast.LENGTH_SHORT).show();

        configureEmptyOrFull();

    }

}
