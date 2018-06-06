package com.example.android.jinarya;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.jinarya.Database.NameDb.AllNames.AllNamesListActivity;
import com.example.android.jinarya.Database.NameDb.NamesContract;
import com.example.android.jinarya.Database.NameDb.NamesDbHelper;
import com.example.android.jinarya.Database.RelationshipHistroyDb.RelationshipHistoryContract.RelationshipHistoryEntry;
import com.example.android.jinarya.Database.RelationshipHistroyDb.RelationshipHistoryDbHelper;

import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_AGE_KEY;
import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_GENDER_KEY;
import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_INFO_FILE_NAME;
import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_NAME_KEY;
import static com.example.android.jinarya.OwnerInfoEditActivity.formatAndShortenName;
import static com.example.android.jinarya.PreNameActivity.RELATIONSHIP_HISTORY_DATABASE_SIZE_LIMIT;
import static com.example.android.jinarya.PreNameActivity.REPORTS_DATABASE_SIZE_LIMIT;
import static com.example.android.jinarya.PreNameActivity.aboutAppMenuChosen;

public class FriendshipNameActivity extends AppCompatActivity {

    // Declare all the views in the Name Activity
    private String LOG_TAG = this.getClass().getSimpleName();

    private EditText ownerNameText;
    private EditText guestNameText;

    private Spinner ownerAgeSpinner;
    private Spinner guestAgeSpinner;

    private Spinner ownerGenderSpinner;
    private Spinner guestGenderSpinner;

    private Spinner friendshipHistorySpinner;

    private Button startFriendshipTestButton;

    private String guestName;

    private String guestAge;

    private String guestGender;

    private String friendshipHistory;

    private String testType;

    Intent preTestActivity;

    private Boolean entriesValid = false;

    private ImageView ownerContactPicker;

    private ImageView guestContactPicker;

    private SQLiteDatabase mDb;

    private ImageView ownerImage;

    private ImageView guestImage;

    private View dummyView;

    private static final int RESULT_PICK_CONTACT_ONE = 100;

    private static final int RESULT_PICK_CONTACT_TWO = 200;

    private String ownerName;

    private String ownerAge;

    private String ownerGender;

    private Boolean userDetailsEntered;

    private Cursor cursor;

    private Boolean allValuesRecheckingByUser = false;

    public static String friendshipOwnerName = "friendshipOwnerName";

    public static String friendshipGuestName = "friendshipGuestName";

    public static String friendshipOwnerAge = "friendshipOwnerAge";

    public static String friendshipGuestAge = "friendshipGuestAge";

    public static String friendshipOwnerGender = "friendshipOwnerGender";

    public static String friendshipGuestGender = "friendshipGuestGender";

    public static String friendshipViewName = "friendshipViewName";

    public static String friendshipOwnerViewName = "friendshipOwnerViewName";

    public static String friendshipGuestViewName = "friendshipGuestViewName";

    public static String friendshipRelationship = "friendshipRelationship";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendship_name);

        assignAllViews();

        getIntentInfoAndAssign();

        retrieveAndPopulateWithOwnerInfo();

        configureKeyboardHide();

        configureStartFriendshipTestButton();

        configureContactOnePicker();

        configureContactTwoPicker();

        configureAllNamesListActivity();

        configureDummyViewClick();

    }

    private void intentToAllNamesActivity(String viewName) {

        Intent intent = new Intent(this,AllNamesListActivity.class);
        intent.putExtra(friendshipViewName,viewName);

        intent.putExtra(friendshipOwnerName, ownerNameText.getText().toString());
        intent.putExtra(friendshipOwnerAge, ownerAgeSpinner.getSelectedItemPosition());
        intent.putExtra(friendshipOwnerGender, ownerGenderSpinner.getSelectedItemPosition());
        intent.putExtra(friendshipGuestName, guestNameText.getText().toString());
        intent.putExtra(friendshipGuestAge, guestAgeSpinner.getSelectedItemPosition());
        intent.putExtra(friendshipGuestGender, guestGenderSpinner.getSelectedItemPosition());
        intent.putExtra(friendshipRelationship, friendshipHistorySpinner.getSelectedItemPosition());

        startActivity(intent);
    }

    private void getIntentInfoAndAssign() {

        Intent intent = getIntent();

        if ( getIntent().getExtras() != null ) {

            Log.i(LOG_TAG,"getIntent exists");

            String ownerName = intent.getStringExtra(friendshipOwnerName);
            int ownerAgeSelection = intent.getIntExtra(friendshipOwnerAge,
                        0);
            int ownerGenderSelection = intent.getIntExtra(friendshipOwnerGender,
                        0);

            extractAndPopulateWithNameDetails(ownerName, ownerAgeSelection,
                        ownerGenderSelection, ownerNameText, ownerAgeSpinner,
                        ownerGenderSpinner);

            String guestName = intent.getStringExtra(friendshipGuestName);
            int guestAgeSelection = intent.getIntExtra(friendshipGuestAge,
                        0);
            int guestGenderSelection = intent.getIntExtra(friendshipGuestGender,
                        0);


            extractAndPopulateWithNameDetails(guestName, guestAgeSelection,
                        guestGenderSelection, guestNameText, guestAgeSpinner,
                        guestGenderSpinner);

            int relationshipHistory = intent.getIntExtra(friendshipRelationship,
                    0);
            friendshipHistorySpinner.setSelection(relationshipHistory);
            getRelationShipHistoryIfUserDetailsEntered();
        }

        else {
            Log.i(LOG_TAG,"No getIntent exists");
        }
    }


    private void extractAndPopulateWithNameDetails(String name,
                                                   int ageSelection,
                                                   int genderSelection,
                                                   EditText editText,
                                                   Spinner ageSpinner,
                                                   Spinner genderSpinner) {
        editText.setText(name);
        ageSpinner.setSelection(ageSelection);
        genderSpinner.setSelection(genderSelection);
    }

    private void configureAllNamesListActivity() {

        ownerImage.setOnClickListener(v -> {
            intentToAllNamesActivity(friendshipOwnerViewName);
        });

        guestImage.setOnClickListener(v -> {
            intentToAllNamesActivity(friendshipGuestViewName);
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PreNameActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    private void retrieveOwnerInfo() {
        SharedPreferences ownerInfoSharedPreferences = getSharedPreferences(OWNER_INFO_FILE_NAME, MODE_PRIVATE);
        ownerName = ownerInfoSharedPreferences.getString(OWNER_NAME_KEY, null);
        ownerAge = ownerInfoSharedPreferences.getString(OWNER_AGE_KEY, null);
        ownerGender = ownerInfoSharedPreferences.getString(OWNER_GENDER_KEY, null);
    }


    private void populateOwnerAgeAndGender(Spinner ageSpinner, Spinner genderSpinner) {
        if ( ownerAge.equals(getString(R.string.eighteen))) {
            ageSpinner.setSelection(1);
        } else if ( ownerAge.equals(getString(R.string.twentyFive))) {
            ageSpinner.setSelection(2);
        } else if ( ownerAge.equals(getString(R.string.thirtyFive))) {
            ageSpinner.setSelection(3);
        } else if ( ownerAge.equals(getString(R.string.fortyFive))) {
            ageSpinner.setSelection(4);
        } else {
            ageSpinner.setSelection(5);
        }

        if ( ownerGender.equals(getString(R.string.male))) {
            genderSpinner.setSelection(1);
        } else if ( ownerGender.equals(getString(R.string.female))) {
            genderSpinner.setSelection(2);
        }
    }

    private void retrieveAndPopulateWithOwnerInfo() {

        if ( ownerNameText.getText().length() == 0 ) {
            retrieveOwnerInfo();
            ownerNameText.setText(ownerName);
            populateOwnerAgeAndGender(ownerAgeSpinner, ownerGenderSpinner);
            guestNameText.requestFocus();
        }
    }

    private void checkIfUserDetailsEntered() {

        retrieveUserDetailsNoRelationshipHistory();
        userDetailsEntered = ownerName != null && ownerAgeSpinner.getSelectedItemPosition() > 0 &&
                ownerGenderSpinner.getSelectedItemPosition() > 0
                && guestName != null && guestAgeSpinner.getSelectedItemPosition() > 0 &&
                guestGenderSpinner.getSelectedItemPosition() > 0;
    }

    // delete if database entries exceed the specified limit
    private void deleteEntryFromRelationshipHistoryTableIfLimitExceeds() {

        String[] columns = {RelationshipHistoryEntry._ID};

        cursor = mDb.query(
                RelationshipHistoryEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if ( cursor.getCount() > RELATIONSHIP_HISTORY_DATABASE_SIZE_LIMIT) {

            cursor.moveToFirst();
            int oldestEntryId = cursor.getInt
                    (cursor.getColumnIndex(RelationshipHistoryEntry._ID));
            Log.i(LOG_TAG, "Oldest column id is" + oldestEntryId);

            String[] oldestEntryIdString = {Integer.toString(oldestEntryId)};
            mDb.delete(RelationshipHistoryEntry.TABLE_NAME,
                    RelationshipHistoryEntry._ID + "= ?", oldestEntryIdString );
        }

    }

    // delete if database entries exceed the specified limit
    private void deleteEntryFromNamesTableIfLimitExceeds() {

        String[] columns = {NamesContract.NamesEntry._ID};

         cursor = mDb.query(
                NamesContract.NamesEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if ( cursor.getCount() > REPORTS_DATABASE_SIZE_LIMIT) {

            cursor.moveToFirst();
            int oldestEntryId = cursor.getInt
                    (cursor.getColumnIndex(NamesContract.NamesEntry._ID));
            Log.i(LOG_TAG, "Oldest column id is" + oldestEntryId);

            String[] oldestEntryIdString = {Integer.toString(oldestEntryId)};
            mDb.delete(NamesContract.NamesEntry.TABLE_NAME,
                    NamesContract.NamesEntry._ID + "= ?", oldestEntryIdString );
        }

    }

    private void addOwnerNameToNamesDatabase(
            String user1Name, String user1Age, String user1Gender)
    {

        NamesDbHelper dbHelper = new NamesDbHelper(this);

        mDb = dbHelper.getReadableDatabase();

        String selection =
                NamesContract.NamesEntry.COLUMN_NAME+ " = ? AND "
                        + NamesContract.NamesEntry.COLUMN_AGE+ " = ? AND "
                        + NamesContract.NamesEntry.COLUMN_GENDER+ " = ? ";

        String[] selectionArgs = {user1Name, user1Age, user1Gender};

         cursor = mDb.query(
                NamesContract.NamesEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if ( cursor.getCount() == 0 ) {

            mDb = dbHelper.getWritableDatabase();

            ContentValues userOneCvs = new ContentValues();

            userOneCvs.put(NamesContract.NamesEntry.COLUMN_NAME, user1Name);
            userOneCvs.put(NamesContract.NamesEntry.COLUMN_AGE, user1Age);
            userOneCvs.put(NamesContract.NamesEntry.COLUMN_GENDER, user1Gender);

            mDb.insert(NamesContract.NamesEntry.TABLE_NAME, null, userOneCvs);
            Log.i(LOG_TAG, "owner name added " + userOneCvs.toString());
        }

        deleteEntryFromNamesTableIfLimitExceeds();

    }

    private void addGuestNameToNamesDatabase(
            String user2Name, String user2Age, String user2Gender)
    {

        NamesDbHelper dbHelper = new NamesDbHelper(this);

        mDb = dbHelper.getReadableDatabase();

        String selection =
                NamesContract.NamesEntry.COLUMN_NAME+ " = ? AND "
                        + NamesContract.NamesEntry.COLUMN_AGE+ " = ? AND "
                        + NamesContract.NamesEntry.COLUMN_GENDER+ " = ? ";

        String[] selectionArgs = {user2Name, user2Age, user2Gender};

         cursor = mDb.query(
                NamesContract.NamesEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if ( cursor.getCount() == 0 ) {

            mDb = dbHelper.getWritableDatabase();

            ContentValues userTwoCvs = new ContentValues();

            userTwoCvs.put(NamesContract.NamesEntry.COLUMN_NAME, user2Name);
            userTwoCvs.put(NamesContract.NamesEntry.COLUMN_AGE, user2Age);
            userTwoCvs.put(NamesContract.NamesEntry.COLUMN_GENDER, user2Gender);

            mDb.insert(NamesContract.NamesEntry.TABLE_NAME, null, userTwoCvs);
            Log.i(LOG_TAG, "guest name added " + userTwoCvs.toString());
        }

        deleteEntryFromNamesTableIfLimitExceeds();

    }

    private void getRelationShipHistoryIfUserDetailsEntered() {

        checkIfUserDetailsEntered();
        Log.i(LOG_TAG, "userDetails Entered or not is " + userDetailsEntered.toString());

        if (userDetailsEntered) {

            RelationshipHistoryDbHelper dbHelper = new RelationshipHistoryDbHelper(this);

            mDb = dbHelper.getReadableDatabase();

            String relationshipHistoryColumn [] = {
                    RelationshipHistoryEntry._ID,
                    RelationshipHistoryEntry.COLUMN_RELATIONSHIP_HISTORY
            };

            String selection =
                    RelationshipHistoryEntry.COLUMN_OWNER_NAME + " = ? AND "
                            + RelationshipHistoryEntry.COLUMN_OWNER_AGE + " = ? AND "
                            + RelationshipHistoryEntry.COLUMN_OWNER_GENDER + " = ? AND "
                            + RelationshipHistoryEntry.COLUMN_GUEST_NAME + " = ? AND "
                            + RelationshipHistoryEntry.COLUMN_GUEST_AGE + " = ? AND "
                            + RelationshipHistoryEntry.COLUMN_GUEST_GENDER + " = ? ";

            String[] selectionArgs = { ownerName, ownerAgeSpinner.getSelectedItem().toString(),
                    ownerGenderSpinner.getSelectedItem().toString(),
                    guestName, guestAgeSpinner.getSelectedItem().toString(),
                    guestGenderSpinner.getSelectedItem().toString() };


             cursor = mDb.query(
                    RelationshipHistoryEntry.TABLE_NAME,
                    relationshipHistoryColumn,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    RelationshipHistoryEntry._ID + " DESC"
            );

            // check for relationship history if the values entered by users are in reverse fashion
            if ( cursor.getCount() == 0 ) {

                String[] reverseSelectionArgs = {guestName, guestAgeSpinner.getSelectedItem().toString(),
                        guestGenderSpinner.getSelectedItem().toString(),
                        ownerName, ownerAgeSpinner.getSelectedItem().toString(),
                        ownerGenderSpinner.getSelectedItem().toString() };


                cursor = mDb.query(
                        RelationshipHistoryEntry.TABLE_NAME,
                        relationshipHistoryColumn,
                        selection,
                        reverseSelectionArgs,
                        null,
                        null,
                        RelationshipHistoryEntry._ID + " DESC"
                );
            }

            if ( cursor.getCount() > 0) {
                cursor.moveToFirst();
                String friendShipRecord = cursor.getString(cursor.getColumnIndex(RelationshipHistoryEntry.COLUMN_RELATIONSHIP_HISTORY));
                Log.i(LOG_TAG,"friendShipRecord " + friendShipRecord);

                if ( friendShipRecord != null) {
                    if ( friendShipRecord.equals(getString(R.string.just_met))) {
                        friendshipHistorySpinner.setSelection(1);
                    } else if ( friendShipRecord.equals(getString(R.string.met_for_long_but_dont_know))) {
                        friendshipHistorySpinner.setSelection(2);
                    } else if ( friendShipRecord.equals(getString(R.string.less_than_6_months))) {
                        friendshipHistorySpinner.setSelection(3);
                    } else if ( friendShipRecord.equals(getString(R.string.six_months_three_years))) {
                        friendshipHistorySpinner.setSelection(4);
                    } else if ( friendShipRecord.equals(getString(R.string.three_seven_years))) {
                        friendshipHistorySpinner.setSelection(5);
                    } else if ( friendShipRecord.equals(getString(R.string.seven_fifteen_years))) {
                        friendshipHistorySpinner.setSelection(6);
                    } else if ( friendShipRecord.equals(getString(R.string.more_than_fifteen_years))) {
                        friendshipHistorySpinner.setSelection(7);
                    }
                }
            }

            if ( cursor.getCount() == 0) {
                friendshipHistorySpinner.setSelection(0);
            }

            cursor.close();
        }
    }



    private void assignAllViews() {

        testType = getString(R.string.friendship_test_type);

        ownerNameText = findViewById(R.id.friendship_owner_name);
        guestNameText = findViewById(R.id.friendship_guest_name);

        ownerAgeSpinner = findViewById(R.id.friendship_owner_age_spinner);
        guestAgeSpinner = findViewById(R.id.friendship_guest_age_spinner);

        ownerGenderSpinner = findViewById(R.id.friendship_owner_gender_spinner);
        guestGenderSpinner = findViewById(R.id.friendship_guest_gender_spinner);

        friendshipHistorySpinner = findViewById(R.id.friendship_history_spinner);

        startFriendshipTestButton = findViewById(R.id.friendship_test_start_test_button);

        ownerContactPicker = findViewById(R.id.friendship_owner_contact_picker);

        guestContactPicker = findViewById(R.id.friendship_guest_contact_picker);

        ownerImage = findViewById(R.id.friendship_owner_icon);
        guestImage = findViewById(R.id.friendship_guest_icon);

        dummyView = findViewById(R.id.friendship_name_dummy_view);
    }

    private void retrieveUserDetailsNoRelationshipHistory() {

        ownerName = ownerNameText.getText().toString();
        guestName = guestNameText.getText().toString();

        ownerAge = ownerAgeSpinner.getSelectedItem().toString();
        guestAge = guestAgeSpinner.getSelectedItem().toString();

        ownerGender= ownerGenderSpinner.getSelectedItem().toString();
        guestGender = guestGenderSpinner.getSelectedItem().toString();
    }


    private void RetrieveEntries() {

        retrieveUserDetailsNoRelationshipHistory();

        friendshipHistory = friendshipHistorySpinner.getSelectedItem().toString();

        if ( ownerName.length() > 0 && guestName.length() > 0 &&
                (ownerAgeSpinner.getSelectedItemPosition() > 0) &&
                (guestAgeSpinner.getSelectedItemPosition() > 0) &&
                (ownerGenderSpinner.getSelectedItemPosition() > 0) &&
                (guestGenderSpinner.getSelectedItemPosition() > 0) &&
                (friendshipHistorySpinner.getSelectedItemPosition() > 0) ) {

            entriesValid = true;
            ownerName = formatAndShortenName(ownerName);
            guestName = formatAndShortenName(guestName);

            if ( allValuesRecheckingByUser) {
                ownerNameText.setBackgroundResource(0);
                guestNameText.setBackgroundResource(0);
                ownerAgeSpinner.setBackgroundResource(0);
                guestAgeSpinner.setBackgroundResource(0);
                ownerGenderSpinner.setBackgroundResource(0);
                guestGenderSpinner.setBackgroundResource(0);
                friendshipHistorySpinner.setBackgroundResource(0);
            }

        } else {

            allValuesRecheckingByUser = true;

            if ( ownerName.length() == 0 ) {
                ownerNameText.setBackgroundResource(R.drawable.border_primary_color);
            } else {
                ownerNameText.setBackgroundResource(0);
            }

            if ( guestName.length() == 0 ) {
                guestNameText.setBackgroundResource(R.drawable.border_primary_color);
            } else {
                guestNameText.setBackgroundResource(0);
            }

            if ( ownerAgeSpinner.getSelectedItemPosition() == 0 ) {
                ownerAgeSpinner.setBackgroundResource(R.drawable.border_primary_color);
            } else {
                ownerAgeSpinner.setBackgroundResource(0);
            }

            if ( guestAgeSpinner.getSelectedItemPosition() == 0 ) {
                guestAgeSpinner.setBackgroundResource(R.drawable.border_primary_color);
            } else {
                guestAgeSpinner.setBackgroundResource(0);
            }

            if ( ownerGenderSpinner.getSelectedItemPosition() == 0 ) {
                ownerGenderSpinner.setBackgroundResource(R.drawable.border_primary_color);
            } else {
                ownerGenderSpinner.setBackgroundResource(0);
            }

            if ( guestGenderSpinner.getSelectedItemPosition() == 0 ) {
                guestGenderSpinner.setBackgroundResource(R.drawable.border_primary_color);
            } else {
                guestGenderSpinner.setBackgroundResource(0);
            }

            if ( friendshipHistorySpinner.getSelectedItemPosition() == 0 ) {
                friendshipHistorySpinner.setBackgroundResource(R.drawable.border_primary_color);
            } else {
                friendshipHistorySpinner.setBackgroundResource(0);
            }

            Toast.makeText(getBaseContext(),getString(R.string.check_all_entries_entered),
                    Toast.LENGTH_LONG).show();
            entriesValid = false;
            Log.i(LOG_TAG, "all entries not entered");
        }

    }

    //Adds the info to the getIntent that needs to be sent to the next activity.
    private void addIntentInfo() {

        preTestActivity = new Intent(this, PreTestActivity.class);

        preTestActivity.putExtra("ownerName", ownerName);
        preTestActivity.putExtra("ownerAge", ownerAge);
        preTestActivity.putExtra("ownerGender", ownerGender);
        preTestActivity.putExtra("guestName", guestName);
        preTestActivity.putExtra("guestAge", guestAge);
        preTestActivity.putExtra("guestGender", guestGender);
        preTestActivity.putExtra("friendshipHistory", friendshipHistory);
        preTestActivity.putExtra("testType", testType);

    }

    private void configureStartFriendshipTestButton() {
        startFriendshipTestButton.setOnClickListener(view -> {

            RetrieveEntries();

            if (entriesValid) {

                addIntentInfo();
                startActivity(preTestActivity);
                configureRelationHistoryDatabaseActions(ownerName, ownerAge, ownerGender,
                        guestName, guestAge, guestGender, friendshipHistory);
                addOwnerNameToNamesDatabase(ownerName, ownerAge, ownerGender);
                addGuestNameToNamesDatabase(guestName, guestAge, guestGender);
            }
        });
    }

    private void configureRelationHistoryDatabaseActions(
            String userOneName, String userOneAge, String userOneGender,
            String userTwoName, String userTwoAge, String userTwoGender, String relationshipHistory
    ) {
        RelationshipHistoryDbHelper dbHelper = new RelationshipHistoryDbHelper(this);
        mDb = dbHelper.getReadableDatabase();

        String selection =
                RelationshipHistoryEntry.COLUMN_OWNER_NAME + " = ? AND "
                + RelationshipHistoryEntry.COLUMN_OWNER_AGE + " = ? AND "
                + RelationshipHistoryEntry.COLUMN_OWNER_GENDER + " = ? AND "
                + RelationshipHistoryEntry.COLUMN_GUEST_NAME + " = ? AND "
                + RelationshipHistoryEntry.COLUMN_GUEST_AGE + " = ? AND "
                + RelationshipHistoryEntry.COLUMN_GUEST_GENDER + " = ? ";

        String[] selectionArgs = {userOneName, userOneAge, userOneGender, userTwoName, userTwoAge,
                            userTwoGender};
         cursor = mDb.query(
                RelationshipHistoryEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if ( cursor.getCount() > 0 ) {

            mDb = dbHelper.getWritableDatabase();
            ContentValues userCvs = new ContentValues();
            cursor.moveToFirst();
            String[] id = {cursor.getString(cursor.getColumnIndex(RelationshipHistoryEntry._ID))};

            userCvs.put(RelationshipHistoryEntry.COLUMN_RELATIONSHIP_HISTORY, relationshipHistory);

            mDb.update(RelationshipHistoryEntry.TABLE_NAME, userCvs, RelationshipHistoryEntry._ID + " = ? " , id);
            Log.i(LOG_TAG,"Relationship history details added to table :" + relationshipHistory);
        }

        Log.i(LOG_TAG,"Relationship history cursor size is :" + cursor.getCount());

        if ( cursor.getCount() < 1 ) {

            mDb = dbHelper.getWritableDatabase();
            ContentValues userCvs = new ContentValues();

            userCvs.put(RelationshipHistoryEntry.COLUMN_OWNER_NAME, userOneName);
            userCvs.put(RelationshipHistoryEntry.COLUMN_OWNER_AGE, userOneAge);
            userCvs.put(RelationshipHistoryEntry.COLUMN_OWNER_GENDER, userOneGender);
            userCvs.put(RelationshipHistoryEntry.COLUMN_GUEST_NAME, userTwoName);
            userCvs.put(RelationshipHistoryEntry.COLUMN_GUEST_AGE, userTwoAge);
            userCvs.put(RelationshipHistoryEntry.COLUMN_GUEST_GENDER, userTwoGender);
            userCvs.put(RelationshipHistoryEntry.COLUMN_RELATIONSHIP_HISTORY, relationshipHistory);

            mDb.insert(RelationshipHistoryEntry.TABLE_NAME, null, userCvs);
            Log.i(LOG_TAG,"Relationship history details added to table :" + relationshipHistory);
        }

        cursor.close();
        deleteEntryFromRelationshipHistoryTableIfLimitExceeds();
    }


    public void hideKeyboard() {

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(
                ownerNameText.getWindowToken(), 0);

    }

    private void configureKeyboardHide() {
        //hides keyboard when a spinner item mentioned below is selected
        ownerAgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        guestAgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ownerGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        guestGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        friendshipHistorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    private void openLegalsPageOnWebsite() {
        String url = getString(R.string.legal_disclaimer_page);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
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


    //the next 7 methods are for picking contact name from the phone contact app so the user
    //need not type the entire name before conducting the test
    public void pickContactOne() {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT_ONE);
    }

    public void pickContactTwo() {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT_TWO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be using multiple startActivityForResult
            switch (requestCode) {
                case RESULT_PICK_CONTACT_ONE:
                    contactOnePicked(data);
                    Log.i("MainActivity", "Contact One Picked");
                    break;
                case RESULT_PICK_CONTACT_TWO:
                    contactTwoPicked(data);
                    Log.i("MainActivity", "Contact Two Picked");
                    break;
            }
        } else {
            Log.i("MainActivity", "Failed to pick contact");
        }
    }

    @SuppressLint("Recycle")
    private void contactOnePicked(Intent data) {

        try {
            String name;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            assert uri != null;
            cursor = getContentResolver().query
                    (uri, null, null, null, null);

            assert cursor != null;
            cursor.moveToFirst();
            // column index of the contact name
            name = cursor.getString
                    (cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            // Set the value to the nameText
            ownerNameText.setText(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("Recycle")
    private void contactTwoPicked(Intent data) {

        try {
            String name;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            assert uri != null;
            cursor = getContentResolver().query
                    (uri, null, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();
            // column index of the contact name
            name = cursor.getString
                    (cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            // Set the value to the nameText
            guestNameText.setText(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void configureContactOnePicker(){
        ownerContactPicker.setOnClickListener(v -> pickContactOne());
    }

    private void configureContactTwoPicker(){
        guestContactPicker.setOnClickListener(v -> pickContactTwo());
    }

    private void configureDummyViewClick() {

        dummyView.setOnClickListener(v -> {
                hideKeyboard();
            });
        }

    public void sendFeedbackViaEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.developer_email_id)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body));

        startActivity(Intent.createChooser
                (intent, getString(R.string.email_title)));
    }

}
