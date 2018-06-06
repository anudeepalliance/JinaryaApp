package com.example.android.jinarya.Database.ReportsDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aadhyamo on 19/12/17.
 */

public class ReportsDbHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "reports.db";

    private static final int DATABASE_VERSION = 1;

    public ReportsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_REPORTS_TABLE =

                "CREATE TABLE " + ReportsContract.ReportsEntry.TABLE_NAME + " (" +

                        ReportsContract.ReportsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        ReportsContract.ReportsEntry.COLUMN_OWNER_NAME + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_OWNER_AGE + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_OWNER_GENDER + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_GUEST_NAME + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_GUEST_AGE + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_GUEST_GENDER + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_RELATIONSHIP_HISTORY + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_TEST_TYPE + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_REPORT_INSIGHTS_FOR_OWNER + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_REPORT_INSIGHTS_FOR_GUEST + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_COMPATIBILITY_SCORE + " INTEGER, " +

                        ReportsContract.ReportsEntry.COLUMN_COMPATIBILITY_SCORE_FEEDBACK + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_REPORT_ADVICE_FOR_OWNER + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_REPORT_ADVICE_FOR_GUEST + " TEXT, " +

                        ReportsContract.ReportsEntry.COLUMN_TIMESTAMP + " TEXT" +

                        ");";

        sqLiteDatabase.execSQL(SQL_CREATE_REPORTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReportsContract.ReportsEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
