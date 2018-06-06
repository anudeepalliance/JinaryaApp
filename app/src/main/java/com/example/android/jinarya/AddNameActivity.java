package com.example.android.jinarya;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
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
import com.example.android.jinarya.Database.NameDb.GuestNames.GuestNamesListActivity;
import com.example.android.jinarya.Database.NameDb.NamesContract;
import com.example.android.jinarya.Database.NameDb.NamesDbHelper;
import com.example.android.jinarya.Database.NameDb.OwnerNames.OwnerNamesListActivity;
import static com.example.android.jinarya.OwnerInfoEditActivity.formatAndShortenName;
import static com.example.android.jinarya.PreNameActivity.REPORTS_DATABASE_SIZE_LIMIT;
import static com.example.android.jinarya.PreNameActivity.VIEW_NAME;
import static com.example.android.jinarya.PreNameActivity.aboutAppMenuChosen;

public class AddNameActivity extends AppCompatActivity {

    private EditText addNameEditText;

    private Spinner addNameAgeSpinner;

    private Spinner addNameGenderSpinner;

    private String name;

    private String age;

    private String gender;

    private Button saveButton;

    private SQLiteDatabase mDb;

    private Cursor cursor;

    private NamesDbHelper dbHelper;

    private View dummyView;

    private ImageView addNameContactPicker;

    private static final int ADD_NAME_PICK_CONTACT= 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);

        assignViews();
        configureSaveButton();

        hideKeyBoardOnSpinnersClick();

        configureContactPicker();
    }

    private void assignViews() {

        addNameEditText = findViewById(R.id.add_name_name_edit);

        addNameAgeSpinner = findViewById(R.id.add_name_age_spinner);

        addNameGenderSpinner = findViewById(R.id.add_name_gender_spinner);

        saveButton = findViewById(R.id.add_name_info_save_button);

        dummyView = findViewById(R.id.add_name_dummy_View);

        addNameContactPicker = findViewById(R.id.add_name_contact_picker);
    }

    private void configureSaveButton() {

        saveButton.setOnClickListener((View v) -> {

            if ( addNameEditText.getText().toString().length() > 0 &&
                    addNameAgeSpinner.getSelectedItemPosition() > 0 &&
                    addNameGenderSpinner.getSelectedItemPosition() > 0 ) {

                addNameAndGoToSourceIntent();
            } else {
                Toast.makeText(getBaseContext(), getString(R.string.check_all_entries_entered),
                        Toast.LENGTH_SHORT).show();
            }


        });
    }

    //Start of code for Contact Name picker
    private void pickNameFromContacts() {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, ADD_NAME_PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be using multiple startActivityForResult
            switch (requestCode) {
                case ADD_NAME_PICK_CONTACT:
                    addContactPicked(data);
                    Log.i("MainActivity", "Contact One Picked");
                    break;
            }
        } else {
            Log.i("AddNameActivity", "Failed to pick contact");
        }
    }

    @SuppressLint("Recycle")
    private void addContactPicked(Intent data) {

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
            addNameEditText.setText(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configureContactPicker() {
        addNameContactPicker.setOnClickListener(v -> pickNameFromContacts());

    }
    //End of code for contact name picker

    private void addNameAndGoToSourceIntent() {

        NamesDbHelper dbHelper = new NamesDbHelper(this);

        mDb = dbHelper.getReadableDatabase();

        name = addNameEditText.getText().toString();
        name = formatAndShortenName(name);

        age = addNameAgeSpinner.getSelectedItem().toString();
        gender = addNameGenderSpinner.getSelectedItem().toString();

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

            ContentValues userTwoCvs = new ContentValues();

            userTwoCvs.put(NamesContract.NamesEntry.COLUMN_NAME, name);
            userTwoCvs.put(NamesContract.NamesEntry.COLUMN_AGE, age);
            userTwoCvs.put(NamesContract.NamesEntry.COLUMN_GENDER, gender);

            mDb.insert(NamesContract.NamesEntry.TABLE_NAME, null, userTwoCvs);

            goBackToSourceIntent();

            deleteEntryFromNamesTableIfLimitExceeds();

        } else {
            Toast.makeText(getBaseContext(),getString(R.string.name_already_exists),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void hideKeyboard(){

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(
                addNameAgeSpinner.getWindowToken(), 0);

    }

    private void hideKeyBoardOnSpinnersClick() {

        addNameAgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addNameGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dummyView.setOnClickListener(v -> hideKeyboard());

    }


    private void deleteEntryFromNamesTableIfLimitExceeds() {

        dbHelper = new NamesDbHelper(this);
        mDb = dbHelper.getReadableDatabase();
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
            String[] oldestEntryIdString = {Integer.toString(oldestEntryId)};
            mDb.delete(NamesContract.NamesEntry.TABLE_NAME,
                    NamesContract.NamesEntry._ID + "= ?", oldestEntryIdString );
        }
    }

    @Override
    public void onBackPressed() {

        if ( addNameEditText.getText().toString().length() > 0 ) {
            new AlertDialog.Builder(this)
                    .setMessage(getString(R.string.no_save_quit_sure))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, (dialog, whichButton) ->
                            super.onBackPressed())
                    .setNegativeButton(android.R.string.no, null).show();
        } else {
            super.onBackPressed();
        }

    }

    private void goBackToSourceIntent() {

        Intent intent = getIntent();

        String intentReceived = intent.getStringExtra(VIEW_NAME);

        if ( intentReceived.equals(AllNamesListActivity.class.getName())) {
            startActivity(new Intent(this, AllNamesListActivity.class));
        } else if (intentReceived.equals(OwnerNamesListActivity.class.getName())) {
            startActivity(new Intent(this, OwnerNamesListActivity.class));
        } else if (intentReceived.equals(GuestNamesListActivity.class.getName())) {
            startActivity(new Intent(this, GuestNamesListActivity.class));
        }
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
}
