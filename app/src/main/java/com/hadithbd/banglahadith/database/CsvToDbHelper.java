package com.hadithbd.banglahadith.database;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by rayhan on 02/24/2015.
 */
public class CsvToDbHelper {
    public static void sBulkInsert(Context ctx, int resourceId, SQLiteDatabase mDatabase) {
        InputStream mInsertStream;
        BufferedReader mInsertReader;
        try {
            mInsertStream = ctx.getResources().openRawResource(resourceId);
            mInsertReader = new BufferedReader(new InputStreamReader(mInsertStream));
            while (mInsertReader.ready()) {
                String insertStmt = mInsertReader.readLine();
                mDatabase.execSQL(insertStmt);
            }
        } catch (SQLiteConstraintException | IOException e) {
            e.printStackTrace();
        }
    }
}

