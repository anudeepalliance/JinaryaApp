package com.example.android.jinarya.Database.RelationshipHistroyDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aadhyamo on 28/01/18.
 */

public class RelationshipHistoryDbHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "relationshipHistory.db";

    private static final int DATABASE_VERSION = 1;

    public RelationshipHistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_RELATIONSHIP_HISTORY_TABLE =

                "CREATE TABLE " + RelationshipHistoryContract.RelationshipHistoryEntry.TABLE_NAME + " (" +

                        RelationshipHistoryContract.RelationshipHistoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        RelationshipHistoryContract.RelationshipHistoryEntry.COLUMN_OWNER_NAME + " TEXT, " +

                        RelationshipHistoryContract.RelationshipHistoryEntry.COLUMN_OWNER_AGE + " TEXT, " +

                        RelationshipHistoryContract.RelationshipHistoryEntry.COLUMN_OWNER_GENDER + " TEXT, " +

                        RelationshipHistoryContract.RelationshipHistoryEntry.COLUMN_GUEST_NAME + " TEXT, " +

                        RelationshipHistoryContract.RelationshipHistoryEntry.COLUMN_GUEST_AGE + " TEXT, " +

                        RelationshipHistoryContract.RelationshipHistoryEntry.COLUMN_GUEST_GENDER + " TEXT, " +

                        RelationshipHistoryContract.RelationshipHistoryEntry.COLUMN_RELATIONSHIP_HISTORY + " TEXT" +

                        ");";

        db.execSQL(SQL_CREATE_RELATIONSHIP_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                RelationshipHistoryContract.RelationshipHistoryEntry.TABLE_NAME);
        onCreate(db);
    }
}