package com.example.android.jinarya.BackGroundJobs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aadhyamo on 14/01/18.
 */

public class PrefManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;


    // Shared preferences file name
    private static final String FIRST_LAUNCH_PREF_FILE_NAME = "androidhive-welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(FIRST_LAUNCH_PREF_FILE_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}
