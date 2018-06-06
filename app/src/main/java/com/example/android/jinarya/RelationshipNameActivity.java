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

import com.example.android.jinarya.Database.NameDb.GuestNames.GuestNamesListActivity;
import com.example.android.jinarya.Database.NameDb.OwnerNames.OwnerNamesListActivity;
import com.example.android.jinarya.Database.NameDb.NamesContract;
import com.example.android.jinarya.Database.NameDb.NamesDbHelper;
import com.example.android.jinarya.Database.RelationshipHistroyDb.RelationshipHistoryContract.RelationshipHistoryEntry;
import com.example.android.jinarya.Database.RelationshipHistroyDb.RelationshipHistoryDbHelper;

import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_AGE_KEY;
import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_GENDER_KEY;
import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_INFO_FILE_NAME;
import static com.example.android.jinarya.OwnerInfoEditActivity.OWNER_NAME_KEY;
import static com.example.android.jinarya.OwnerInfoEditActivity.formatAndShortenName;
import static com.example.android.jinarya.PreNameActivity.NAMES_DATABASE_SIZE_LIMIT;
import static com.example.android.jinarya.PreNameActivity.RELATIONSHIP_HISTORY_DATABASE_SIZE_LIMIT;
import static com.example.android.jinarya.PreNameActivity.aboutAppMenuChosen;

public class RelationshipNameActivity extends AppCompatActivity {

    // Declare all the views in the Name Activity
    private String LOG_TAG = this.getClass().getSimpleName();

    private EditText ownerNameText;
    private EditText guestNameText;

    private Spinner ownerAgeSpinner;
    private Spinner guestAgeSpinner;

    private Spinner relationshipHistorySpinner;

    private Button startTestButton;

    private String guestName;

    private String guestAge;

    private String relationshipHistory;
    private String testType;

    Intent preTestActivity;

    private Boolean entriesValid = false;

    private ImageView ownerContactPicker;

    private ImageView guestContactPicker;

    private SQLiteDatabase mDb;

    private ImageView ownerPicker;

    private ImageView guestPicker;

    private View dummyView;

    private static final int RESULT_PICK_CONTACT_OWNER = 100;

    private static final int RESULT_PICK_CONTACT_GUEST = 200;

    public SharedPreferences ownerInfoSharedPreferences;

    private String ownerName;

    private String ownerAge;

    public static String ownerGender;

    public static String guestGender;

    private Boolean userDetailsEntered;

    private Boolean allValuesRecheckingByUser = false;

    private Cursor cursor;

    public static String relationshipOwnerName = "relationshipOwnerName";

    public static String relationshipGuestName = "relationshipGuestName";

    public static String relationshipOwnerAge = "relationshipOwnerAge";

    public static String relationshipGuestAge = "relationshipGuestAge";

    public static String relationshipOwnerGender = "relationshipOwnerGender";

    public static String relationshipGuestGender = "relationshipGuestGender";

    public static String relationshipViewName = "relationshipViewName";

    public static String relationshipOwnerViewName = "relationshipOwnerViewName";

    public static String relationshipGuestViewName = "relationshipGuestViewName";

    public static String relationshipRelationship = "relationshipRelationship";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship_name);

        assignAllViews();

        getIntentInfoAndAssign();

        retrieveAndPopulateWithOwnerInfo();

        configureHintText();

        configureKeyboardHide();

        configureRelationshipTestButton();

        configureOwnerContactsPicker();

        configureGuestContactsPicker();

        configureOwnerAndGuestListActivity();

        configureDummyViewClick();

    }

    private void intentToOwnerNamesActivity(String viewName) {

        Intent intent = new Intent(this,OwnerNamesListActivity.class);

        intent.putExtra(relationshipOwnerViewName,viewName);

        intent.putExtra(relationshipOwnerName, ownerNameText.getText().toString());
        intent.putExtra(relationshipOwnerAge, ownerAgeSpinner.getSelectedItemPosition());

        intent.putExtra(relationshipGuestName, guestNameText.getText().toString());
        intent.putExtra(relationshipGuestAge, guestAgeSpinner.getSelectedItemPosition());

        if ( ownerGender.equals(getString(R.string.male))) {
            guestGender = getString(R.string.female);
        } else {
            guestGender = getString(R.string.male);
        }

        intent.putExtra(relationshipOwnerGender, ownerGender);
        intent.putExtra(relationshipGuestGender, guestGender);

        intent.putExtra(relationshipRelationship,
                relationshipHistorySpinner.getSelectedItemPosition());

        startActivity(intent);
    }

    private void intentToGuestNamesActivity(String viewName) {

        Intent intent = new Intent(this,GuestNamesListActivity.class);

        intent.putExtra(relationshipGuestViewName,viewName);

        intent.putExtra(relationshipGuestName, guestNameText.getText().toString());
        intent.putExtra(relationshipGuestAge, guestAgeSpinner.getSelectedItemPosition());

        intent.putExtra(relationshipOwnerName, ownerNameText.getText().toString());
        intent.putExtra(relationshipOwnerAge, ownerAgeSpinner.getSelectedItemPosition());

        intent.putExtra(relationshipRelationship,
                relationshipHistorySpinner.getSelectedItemPosition());

        startActivity(intent);
    }

    private void getIntentInfoAndAssign() {

        Intent intent = getIntent();

        if ( getIntent().getExtras() != null ) {

            Log.i(LOG_TAG,"getIntent exists");

            String ownerName = intent.getStringExtra(relationshipOwnerName);
            ownerNameText.setText(ownerName);

            int ownerAgeSelection = intent.getIntExtra(relationshipOwnerAge,
                    0);
            ownerAgeSpinner.setSelection(ownerAgeSelection);

            String guestName = intent.getStringExtra(relationshipGuestName);
            guestNameText.setText(guestName);

            int guestAgeSelection = intent.getIntExtra(relationshipGuestAge,
                    0);
            guestAgeSpinner.setSelection(guestAgeSelection);

            int relationshipHistory = intent.getIntExtra(relationshipRelationship,
                    0);
            relationshipHistorySpinner.setSelection(relationshipHistory);

            getRelationShipHistoryIfUserDetailsEntered();
        }

        else {
            Log.i(LOG_TAG,"No getIntent exists");
        }
    }


    public void retrieveOwnerInfo() {
        ownerInfoSharedPreferences = getSharedPreferences(OWNER_INFO_FILE_NAME, MODE_PRIVATE);
        ownerName = ownerInfoSharedPreferences.getString(OWNER_NAME_KEY, null);
        ownerAge = ownerInfoSharedPreferences.getString(OWNER_AGE_KEY, null);
        ownerGender = ownerInfoSharedPreferences.getString(OWNER_GENDER_KEY, null);

        if ( ownerGender.equals(getString(R.string.male))) {
            guestGender = (getString(R.string.female));
        } else { guestGender = (getString(R.string.male)); }
    }

    private void populateOwnerAge(Spinner spinner) {
        if ( ownerAge.equals(getString(R.string.eighteen))) {
            spinner.setSelection(1);
        } else if ( ownerAge.equals(getString(R.string.twentyFive))) {
            spinner.setSelection(2);
        } else if ( ownerAge.equals(getString(R.string.thirtyFive))) {
            spinner.setSelection(3);
        } else if ( ownerAge.equals(getString(R.string.fortyFive))) {
            spinner.setSelection(4);
        } else {
            spinner.setSelection(5);
        }
    }

    private void retrieveAndPopulateWithOwnerInfo() {

        if ( ownerNameText.getText().length() == 0 ) {

            retrieveOwnerInfo();
            ownerNameText.setText(ownerName);
            populateOwnerAge(ownerAgeSpinner);
            guestNameText.requestFocus();
        }

        if ( ownerGender.equals(getString(R.string.female))) {
            ownerPicker.setImageResource(R.drawable.ic_woman);
            guestPicker.setImageResource(R.drawable.ic_man);
        }
    }

    private void configureOwnerAndGuestListActivity() {

        ownerPicker.setOnClickListener(v -> {

            intentToOwnerNamesActivity(relationshipOwnerViewName);
        });

        guestPicker.setOnClickListener(v -> {

            intentToGuestNamesActivity(relationshipOwnerViewName);
        });
    }

    private void checkIfUserDetailsEntered() {

        retrieveUserDetailsNoRelationshipHistory();
        userDetailsEntered = ownerName != null && ownerAgeSpinner.getSelectedItemPosition() > 0 &&
                guestName != null && guestAgeSpinner.getSelectedItemPosition() > 0 ;
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

        cursor.close();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PreNameActivity.class));
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
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

        if ( cursor.getCount() > NAMES_DATABASE_SIZE_LIMIT) {

            cursor.moveToFirst();
            int oldestEntryId = cursor.getInt
                    (cursor.getColumnIndex(NamesContract.NamesEntry._ID));
            Log.i(LOG_TAG, "Oldest column id is" + oldestEntryId);

            String[] oldestEntryIdString = {Integer.toString(oldestEntryId)};
            mDb.delete(NamesContract.NamesEntry.TABLE_NAME,
                    NamesContract.NamesEntry._ID + "= ?", oldestEntryIdString );
        }

        cursor.close();

    }

    private void addOwnerNameToNamesDatabase(
            String name, String age, String gender)
    {

        NamesDbHelper dbHelper = new NamesDbHelper(this);

        mDb = dbHelper.getReadableDatabase();

        String selection =
                NamesContract.NamesEntry.COLUMN_NAME+ " = ? AND "
                        + NamesContract.NamesEntry.COLUMN_AGE+ " = ? AND "
                        + NamesContract.NamesEntry.COLUMN_GENDER+ " = ? ";

        String[] selectionArgs = {name, age, gender};

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

            ContentValues ownerCvs = new ContentValues();

            ownerCvs.put(NamesContract.NamesEntry.COLUMN_NAME, name);
            ownerCvs.put(NamesContract.NamesEntry.COLUMN_AGE, age);
            ownerCvs.put(NamesContract.NamesEntry.COLUMN_GENDER, gender);

            mDb.insert(NamesContract.NamesEntry.TABLE_NAME, null, ownerCvs);
        }

        cursor.close();

        deleteEntryFromNamesTableIfLimitExceeds();

    }

    private void addGuestNameToNamesDatabase(
            String name, String age, String gender)
    {

        NamesDbHelper dbHelper = new NamesDbHelper(this);

        mDb = dbHelper.getReadableDatabase();

        String selection =
                NamesContract.NamesEntry.COLUMN_NAME+ " = ? AND "
                        + NamesContract.NamesEntry.COLUMN_AGE+ " = ? AND "
                        + NamesContract.NamesEntry.COLUMN_GENDER+ " = ? ";

        String[] selectionArgs = {name, age, gender};

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

            deleteEntryFromNamesTableIfLimitExceeds();
            ContentValues guestCvs = new ContentValues();

            guestCvs.put(NamesContract.NamesEntry.COLUMN_NAME, name);
            guestCvs.put(NamesContract.NamesEntry.COLUMN_AGE, age);
            guestCvs.put(NamesContract.NamesEntry.COLUMN_GENDER, gender);

            mDb.insert(NamesContract.NamesEntry.TABLE_NAME, null, guestCvs);
        }

        cursor.close();

        deleteEntryFromNamesTableIfLimitExceeds();
    }

    private void getRelationShipHistoryIfUserDetailsEntered() {

        checkIfUserDetailsEntered();
        Log.i(LOG_TAG, "userDetails Entered or not is " + userDetailsEntered.toString());

        if (userDetailsEntered) {

            RelationshipHistoryDbHelper dbHelper = new RelationshipHistoryDbHelper(this);

            mDb = dbHelper.getReadableDatabase();

            String relationshipHistoryColumn [] = {RelationshipHistoryEntry.COLUMN_RELATIONSHIP_HISTORY};

            String selection =
                    RelationshipHistoryEntry.COLUMN_OWNER_NAME + " = ? AND "
                            + RelationshipHistoryEntry.COLUMN_OWNER_AGE + " = ? AND "
                            + RelationshipHistoryEntry.COLUMN_OWNER_GENDER + " = ? AND "
                            + RelationshipHistoryEntry.COLUMN_GUEST_NAME + " = ? AND "
                            + RelationshipHistoryEntry.COLUMN_GUEST_AGE + " = ? AND "
                            + RelationshipHistoryEntry.COLUMN_GUEST_GENDER + " = ? ";

            String[] selectionArgs = { ownerName, ownerAgeSpinner.getSelectedItem().toString(),
                    ownerGender,
                    guestName, guestAgeSpinner.getSelectedItem().toString(),
                    guestGender };


            cursor = mDb.query(
                    RelationshipHistoryEntry.TABLE_NAME,
                    relationshipHistoryColumn,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    RelationshipHistoryEntry._ID + " DESC"
            );

            if ( cursor.getCount() > 0) {
                cursor.moveToFirst();
                String relationShipRecord = cursor.getString(0);
                Log.i(LOG_TAG,"relationShipRecord: " + relationShipRecord);

                if ( relationShipRecord != null) {
                    if ( relationShipRecord.equals(getString(R.string.just_met))) {
                        relationshipHistorySpinner.setSelection(1);
                    } else if ( relationShipRecord.equals(getString(R.string.met_for_long_but_dont_know))) {
                        relationshipHistorySpinner.setSelection(2);
                    } else if ( relationShipRecord.equals(getString(R.string.less_than_6_months))) {
                        relationshipHistorySpinner.setSelection(3);
                    } else if ( relationShipRecord.equals(getString(R.string.six_months_three_years))) {
                        relationshipHistorySpinner.setSelection(4);
                    } else if ( relationShipRecord.equals(getString(R.string.three_seven_years))) {
                        relationshipHistorySpinner.setSelection(5);
                    } else if ( relationShipRecord.equals(getString(R.string.seven_fifteen_years))) {
                        relationshipHistorySpinner.setSelection(6);
                    } else if ( relationShipRecord.equals(getString(R.string.more_than_fifteen_years))) {
                        relationshipHistorySpinner.setSelection(7);
                    }
                }
            }

            if ( cursor.getCount() == 0) {
                relationshipHistorySpinner.setSelection(0);
            }

            cursor.close();

        }
    }

    private void assignAllViews() {

        testType = getString(R.string.relationship_test_type);

        dummyView = findViewById(R.id.relationship_name_dummy_view);

        relationshipHistorySpinner = findViewById(R.id.relationship_activity_relationship_history_spinner);

        startTestButton = findViewById(R.id.relationship_test_start_test_button);

        ownerNameText = findViewById(R.id.relationship_activity_owner_name);
        guestNameText = findViewById(R.id.relationship_activity_guest_name);

        ownerAgeSpinner = findViewById(R.id.add_name_age_spinner);
        guestAgeSpinner = findViewById(R.id.relationship_activity_guest_age_spinner);

        ownerPicker = findViewById(R.id.relationship_activity_ownerIcon);

        guestPicker = findViewById(R.id.relationship_activity_guestIcon);

        ownerContactPicker = findViewById(R.id.relationship_activity_owner_contact_picker);
        guestContactPicker = findViewById(R.id.relationship_activity_guest_contact_picker);


    }

    private void configureHintText() {

        if ( ownerGender.equals(getString(R.string.male))) {

            ownerNameText.setHint(R.string.enter_boy_name);
            guestNameText.setHint(R.string.enter_girl_name);

        } else if ( ownerGender.equals(getString(R.string.female))) {

            ownerNameText.setHint(R.string.enter_girl_name);
            guestNameText.setHint(R.string.enter_boy_name);
        }

    }

    private void retrieveUserDetailsNoRelationshipHistory() {

        ownerName = ownerNameText.getText().toString();
        guestName = guestNameText.getText().toString();

        ownerAge = ownerAgeSpinner.getSelectedItem().toString();
        guestAge = guestAgeSpinner.getSelectedItem().toString();
    }


    private void RetrieveEntries() {

        retrieveUserDetailsNoRelationshipHistory();

        relationshipHistory = relationshipHistorySpinner.getSelectedItem().toString();

        if ( ownerName.length() > 0 && guestName.length() > 0 &&
                (ownerAgeSpinner.getSelectedItemPosition() > 0) &&
                (guestAgeSpinner.getSelectedItemPosition() > 0) &&
                (relationshipHistorySpinner.getSelectedItemPosition() > 0) ) {

            entriesValid = true;
            ownerName= formatAndShortenName(ownerName);
            guestName = formatAndShortenName(guestName);

            if ( allValuesRecheckingByUser) {
                ownerNameText.setBackgroundResource(0);
                guestNameText.setBackgroundResource(0);
                ownerAgeSpinner.setBackgroundResource(0);
                guestAgeSpinner.setBackgroundResource(0);
                relationshipHistorySpinner.setBackgroundResource(0);
            }


        } else {

            allValuesRecheckingByUser = true;

            if ( ownerNameText.length() == 0 ) {
                ownerNameText.setBackgroundResource(R.drawable.border_primary_color);
            } else {
                ownerNameText.setBackgroundResource(0);
            }

            if ( guestNameText.length() == 0 ) {
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

            if ( relationshipHistorySpinner.getSelectedItemPosition() == 0 ) {
                relationshipHistorySpinner.setBackgroundResource(R.drawable.border_primary_color);
            } else {
                relationshipHistorySpinner.setBackgroundResource(0);
            }

            Toast.makeText(getApplicationContext(),getString(R.string.check_all_entries_entered),
                    Toast.LENGTH_SHORT).show();

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
        preTestActivity.putExtra("relationshipHistory", relationshipHistory);
        preTestActivity.putExtra("testType", testType);


    }

    private void configureRelationshipTestButton() {
        startTestButton.setOnClickListener(view -> {

            RetrieveEntries();

            if (entriesValid) {

                addIntentInfo();
                startActivity(preTestActivity);
                configureRelationHistoryDatabaseActions(ownerName, ownerAge, ownerGender,
                        guestName, guestAge, guestGender, relationshipHistory);
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

        if ( cursor.getCount() == 0 ) {

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
            Log.i(LOG_TAG,"Relationship history details added to table" + relationshipHistory);

        }

        cursor.close();
        deleteEntryFromRelationshipHistoryTableIfLimitExceeds();
    }


    public void hideKeyboard(){

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(
                ownerNameText.getWindowToken(), 0);

    }


    private void configureKeyboardHide() {
        //hides keyboard when a spinner item mentioned below is selected
        guestAgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ownerAgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        relationshipHistorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void intentToWelcomeActivity() {
        Intent welcomeActivity = new Intent(this,WelcomeActivity.class);
        startActivity(welcomeActivity);
    }

    private void intentToResultListActivity() {
        Intent reportsListActivity = new Intent(this, ReportsListActivity.class);
        startActivity(reportsListActivity);
    }

    private void intentToOwnerInfoActivity() {
        Intent intentToOwnerInfoActivity = new Intent(this, OwnerInfoViewActivity.class);
        startActivity(intentToOwnerInfoActivity);
    }

    public void pickOwnerContact()
    {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT_OWNER);
    }

    public void pickGuestContact()
    {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT_GUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be using multiple startActivityForResult
            switch (requestCode) {
                case RESULT_PICK_CONTACT_OWNER:
                    contactOwnerPicked(data);
                    break;
                case RESULT_PICK_CONTACT_GUEST:
                    contactGuestPicked(data);
                    break;
            }
        } else {
            Log.i("MainActivity", "Failed to pick contact");
        }
    }

    @SuppressLint("Recycle")
    private void contactOwnerPicked(Intent data) {

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
        cursor.close();
    }

    @SuppressLint("Recycle")
    private void contactGuestPicked(Intent data) {

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
        cursor.close();
    }

    private void configureOwnerContactsPicker(){
        ownerContactPicker.setOnClickListener(v -> pickOwnerContact());
    }

    private void configureGuestContactsPicker(){
        guestContactPicker.setOnClickListener(v -> pickGuestContact());
    }

    private void configureDummyViewClick() {

        dummyView.setOnClickListener(v -> {
            hideKeyboard();
        });
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
