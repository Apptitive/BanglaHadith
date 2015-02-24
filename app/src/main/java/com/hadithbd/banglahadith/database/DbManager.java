package com.hadithbd.banglahadith.database;

import android.content.Context;
import com.hadithbd.banglahadith.database.tables.hadith.HadithPublisher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rayhan on 02/24/2015.
 */
public class DbManager {
    static private DbManager instance;
    private DbHelper helper;

    private DbManager(Context ctx) {
        helper = new DbHelper(ctx);
    }

    static public void init(Context ctx) {
        if (null == instance) {
            instance = new DbManager(ctx);
        }
    }

    static public DbManager getInstance() {
        return instance;
    }

    private DbHelper getHelper() {
        return helper;
    }

    public List<HadithPublisher> getAllHadithPublishers() {
        List<HadithPublisher> hadithPublisherList = new ArrayList<>();
        try {
            hadithPublisherList = getHelper().getHadithPublisherDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithPublisherList;
    }
}


