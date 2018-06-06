package com.example.android.jinarya.Database.NameDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aadhyamo on 27/01/18.
 */

public class NamesDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "names.db";

    private static final int DATABASE_VERSION = 1;

    public  NamesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_NAMES_TABLE =

                "CREATE TABLE " + NamesContract.NamesEntry.TABLE_NAME + " (" +

                        NamesContract.NamesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        NamesContract.NamesEntry.COLUMN_NAME + " TEXT, " +

                        NamesContract.NamesEntry.COLUMN_AGE + " TEXT, " +

                        NamesContract.NamesEntry.COLUMN_GENDER + " TEXT" +

                        ");";

        db.execSQL(SQL_CREATE_NAMES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NamesContract.NamesEntry.TABLE_NAME);
        onCreate(db);
    }
}
