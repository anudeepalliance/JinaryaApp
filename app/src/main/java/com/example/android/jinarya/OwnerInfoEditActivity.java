package com.example.android.jinarya;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class OwnerInfoEditActivity extends AppCompatActivity {

    private String LOG_TAG = this.getClass().getSimpleName();

    public String ownerName;
    public String ownerAge;
    public String ownerGender;

    private EditText ownerNameEditText;
    private Spinner ownerAgeSpinner;
    private Spinner ownerGenderSpinner;
    private Button saveButton;
    private View dummyView;

    private TextView headingTextView;

    private Boolean allValuesEntered = false;

    private Boolean previousValuesExist = false;

    public static  Boolean ownerInfoAvailable = false;

    public SharedPreferences ownerInfoPref;
    public SharedPreferences.Editor editor;

    private ConstraintLayout constraintLayout;

    private ImageView cancelImage;

    public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);

    // Shared preferences file name
    public static final String OWNER_INFO_FILE_NAME = "owner-info";
    public static final String OWNER_NAME_KEY = "owner-name";
    public static final String OWNER_AGE_KEY = "owner-age";
    public static final String OWNER_GENDER_KEY = "owner-gender";
    public static final String OWNER_INFO_AVAILABLE_KEY = "owner-info-available";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_info_edit);

        assignViews();

        configureSaveButton();

        hideKeyBoardOnSpinnersClick();

        configureCancelImage();

        addParentActivityIfNotFirstLaunch();

    }


    private void addParentActivityIfNotFirstLaunch(){
        SharedPreferences ownerInfoSharedPreferences = getSharedPreferences(OWNER_INFO_FILE_NAME, MODE_PRIVATE);
        String existingOwnerName = ownerInfoSharedPreferences.getString(OWNER_NAME_KEY, null);

        if ( getSupportActionBar() != null ) {

            if (existingOwnerName != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                headingTextView.setText(R.string.add_your_profile);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            }
        }
    }

    private void assignViews() {

        headingTextView = findViewById(R.id.add_name_info_title_text);

        dummyView = findViewById(R.id.activity_owner_dummy_view);

        ownerNameEditText = findViewById(R.id.add_name_name_edit);

        ownerAgeSpinner = findViewById(R.id.add_name_age_spinner);

        ownerGenderSpinner = findViewById(R.id.add_name_gender_spinner);

        saveButton = findViewById(R.id.add_name_info_save_button);

        cancelImage = findViewById(R.id.owner_name_edit_cross_mark);



        ownerInfoPref = getApplicationContext().getSharedPreferences(OWNER_INFO_FILE_NAME, MODE_PRIVATE);

        if ( ownerInfoPref.contains(OWNER_NAME_KEY)) {

            previousValuesExist = true;

            ownerNameEditText.setText(ownerInfoPref.getString(OWNER_NAME_KEY, null));
            ownerAgeSpinner.setSelection(populateOwnerAgeSpinner());
            ownerGenderSpinner.setSelection(populateOwnerGenderSpinner());

        }

    }

    private int populateOwnerAgeSpinner() {

        if (Objects.equals(ownerInfoPref.getString(OWNER_AGE_KEY, null), getString(R.string.eighteen))) {
            return 1;
        } else if (Objects.equals(ownerInfoPref.getString(OWNER_AGE_KEY, null), getString(R.string.twentyFive))) {
            return 2;
        } else if (Objects.equals(ownerInfoPref.getString(OWNER_AGE_KEY, null), getString(R.string.thirtyFive))) {
            return 3;
        } else if (Objects.equals(ownerInfoPref.getString(OWNER_AGE_KEY, null), getString(R.string.fortyFive))) {
            return 4;
        } else {
            return 5;
        }

    }

    private int populateOwnerGenderSpinner() {

        if (Objects.equals(ownerInfoPref.getString(OWNER_GENDER_KEY, null), getString(R.string.male))) {
            return 1;
        } else if (Objects.equals(ownerInfoPref.getString(OWNER_GENDER_KEY, null), getString(R.string.female))) {
            return 2;
        } else {
            return 0;
        }
    }

    private Boolean ownerNameAvailable() {

        if ( ownerNameEditText.getText().toString().length() > 0 ) {
            ownerName = ownerNameEditText.getText().toString();
            return true;
        } else if ( previousValuesExist ) {
            ownerName = ownerInfoPref.getString(OWNER_NAME_KEY, null);
            return true;
        } else {
            return false;
        }
    }

    private Boolean ownerAgeAvailable() {

        if ( ownerAgeSpinner.getSelectedItemPosition() > 0 ) {
            ownerAge = ownerAgeSpinner.getSelectedItem().toString();
            return true;
        } else if ( previousValuesExist ) {
            ownerAge = ownerInfoPref.getString(OWNER_AGE_KEY, null);
            return true;
        } else {
            return false;
        }
    }

    private Boolean ownerGenderAvailable() {

        if ( ownerGenderSpinner.getSelectedItemPosition() > 0 ) {
            ownerGender = ownerGenderSpinner.getSelectedItem().toString();
            return true;
        } else if ( previousValuesExist ) {
            ownerGender = ownerInfoPref.getString(OWNER_GENDER_KEY, null);
            return true;
        } else {
            return false;
        }
    }

    private void configureSaveButton() {

        saveButton.setOnClickListener(v -> {
            Log.i("Owner Activity","value of allValues boolean is " + allValuesEntered);

            if ( ownerNameEditText.getText().toString().length() > 0 &&
                    ownerAgeSpinner.getSelectedItemPosition() > 0 &&
                    ownerGenderSpinner.getSelectedItemPosition() > 0)  {

                saveOwnerInfoInSharedPreferences();
                Toast.makeText(getApplicationContext(),
                        "Your Information has been saved",Toast.LENGTH_SHORT).show();
                intentToPreNameActivity();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Please enter all Values",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        if ( previousValuesExist && ownerNameEditText.getText().toString().length() > 0 ) {
            new AlertDialog.Builder(this)
                    .setMessage(getString(R.string.no_save_quit_sure))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, (dialog, whichButton) ->
                            intentToOwnerNameViewActivity())
                    .setNegativeButton(android.R.string.no, null).show();
        } else if ( previousValuesExist ) {
            intentToOwnerNameViewActivity();
        } else  {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.save_your_profile),Toast.LENGTH_SHORT).show();
        }
    }



    public static String formatAndShortenName(String name) {

        name = name.trim();

        if (name.length() > 20) {
            name = name.substring(0, 19) + "..";
        }

        name = name.toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        return name;
    }

    public static String formatAndShortenNameForRecyclerView(String name) {

        if (name.length() > 9) {
            name = name.substring(0,9) + "..";
        }

        return name;
    }

    //convert string to date
    //check if date is current date, if so then just display time else display only time
    //check if date is current year if so display date without year else display
    //date without time

    public static String formatDateString(String dateString) {

        Date givenDate = null;
        Date currentDate = null;

        int givenYear;
        int currentYear;

        String finalDateString = null;

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        givenYear = Integer.parseInt(dateString.substring(7,11));
        currentYear = Calendar.getInstance().get((Calendar.YEAR));

        try {

            givenDate = simpleDateFormat.parse(dateString.substring(0,11));

            currentDate =
                    simpleDateFormat.parse(dateFormat.format(Calendar.getInstance().getTime()));

        } catch (Exception e) {

            e.printStackTrace();
        }

        if ( givenDate != null && currentDate != null ) {

            if ( givenDate.before(currentDate) && givenYear == currentYear ) {

                finalDateString =  dateString.substring(0,6);

            } else if ( givenDate.before(currentDate) && givenYear < currentYear ) {

                finalDateString =  dateString.substring(0,11);

            } else {

                finalDateString =  dateString.substring(11,dateString.length());

            }
        }

        return finalDateString;
    }

    private void configureCancelImage() {

        ownerNameEditText.setOnClickListener(v -> cancelImage.setVisibility(View.VISIBLE));

        cancelImage.setOnClickListener(v -> ownerNameEditText.setText(""));

    }


    private void saveOwnerInfoInSharedPreferences() {
        ownerInfoPref = getApplicationContext().getSharedPreferences(OWNER_INFO_FILE_NAME, MODE_PRIVATE);
        editor = ownerInfoPref.edit();
        editor.putString(OWNER_NAME_KEY, formatAndShortenName(ownerNameEditText.getText().toString()));
        editor.putString(OWNER_AGE_KEY, ownerAgeSpinner.getSelectedItem().toString());
        editor.putString(OWNER_GENDER_KEY, ownerGenderSpinner.getSelectedItem().toString());
        editor.putBoolean(OWNER_INFO_AVAILABLE_KEY, ownerInfoAvailable);
        editor.apply();

    }

    private void hideKeyBoardOnSpinnersClick() {

        ownerAgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                hideKeyboard();
                cancelImage.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ownerGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                hideKeyboard();
                cancelImage.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dummyView.setOnClickListener(v -> {
            hideKeyboard();
            cancelImage.setVisibility(View.INVISIBLE);
        });


    }

    private void intentToOwnerNameViewActivity() {
        Intent preNameActivityIntent = new Intent(this, OwnerInfoViewActivity.class);
        startActivity(preNameActivityIntent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    private void intentToPreNameActivity() {
        Intent preNameActivityIntent = new Intent(this, PreNameActivity.class);
        startActivity(preNameActivityIntent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    public void hideKeyboard(){

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(
                ownerAgeSpinner.getWindowToken(), 0);

    }
}
