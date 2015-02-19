package com.hadithbd.banglahadith.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sharif on 2/5/2015.
 */
public class HadithDatabaseHelper  extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "banglahadith.db";
    private static final int DATABASE_VERSION = 1;


    public HadithDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
