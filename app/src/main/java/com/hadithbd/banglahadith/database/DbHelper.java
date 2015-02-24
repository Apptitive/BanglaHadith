package com.hadithbd.banglahadith.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.database.tables.hadith.HadithPublisher;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rayhan on 02/24/2015.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "bengaliHadith.sqlite";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    private Dao<HadithPublisher, Integer> hadithPublisherDao = null;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, HadithPublisher.class);
        } catch (SQLException e) {
            Log.e(DbHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        List<String> allSql = new ArrayList<String>();

        try {
            TableUtils.clearTable(connectionSource, HadithPublisher.class);

            CsvToDbHelper.sBulkInsert(context, R.raw.hadithpublisher, sqLiteDatabase);
            Log.e("Upgrade", "Success");

            for (String sql : allSql) {
                sqLiteDatabase.execSQL(sql);
            }
        } catch (Exception e) {
            Log.e(DbHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }
    }


    public Dao<HadithPublisher, Integer> getHadithPublisherDao() {
        if (null == hadithPublisherDao) {
            try {
                hadithPublisherDao = getDao(HadithPublisher.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return hadithPublisherDao;
    }


    public void clearTable(Class cls) {
        try {
            TableUtils.clearTable(getConnectionSource(), cls);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
