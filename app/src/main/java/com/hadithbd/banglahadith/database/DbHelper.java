package com.hadithbd.banglahadith.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.database.tables.book.BookContent;
import com.hadithbd.banglahadith.database.tables.book.BookName;
import com.hadithbd.banglahadith.database.tables.book.BookSection;
import com.hadithbd.banglahadith.database.tables.book.BookType;
import com.hadithbd.banglahadith.database.tables.book.BookWriter;
import com.hadithbd.banglahadith.database.tables.hadith.HadithBook;
import com.hadithbd.banglahadith.database.tables.hadith.HadithChapter;
import com.hadithbd.banglahadith.database.tables.hadith.HadithExplanation;
import com.hadithbd.banglahadith.database.tables.hadith.HadithMain;
import com.hadithbd.banglahadith.database.tables.hadith.HadithPublisher;
import com.hadithbd.banglahadith.database.tables.hadith.HadithSection;
import com.hadithbd.banglahadith.database.tables.hadith.HadithStatus;
import com.hadithbd.banglahadith.database.tables.hadith.RabiHadith;
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

    private Dao<BookWriter, Integer> bookWriterDao = null;
    private Dao<BookType, Integer> bookTypeDao = null;
    private Dao<BookName, Integer> bookNameDao = null;
    private Dao<BookSection, Integer> bookSectionDao = null;
    private Dao<BookContent, Integer> bookContentDao = null;
    private Dao<HadithBook, Integer> hadithBookDao = null;
    private Dao<HadithChapter, Integer> hadithChapterDao = null;
    private Dao<HadithExplanation, Integer> hadithExplanationDao = null;
    private Dao<HadithMain, Integer> hadithMainDao = null;
    private Dao<HadithPublisher, Integer> hadithPublisherDao = null;
    private Dao<HadithSection, Integer> hadithSectionDao = null;
    private Dao<HadithStatus, Integer> hadithStatusDao = null;
    private Dao<RabiHadith, Integer> rabiHadithDao = null;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, BookWriter.class);
            TableUtils.createTable(connectionSource, BookType.class);
            TableUtils.createTable(connectionSource, BookSection.class);
            TableUtils.createTable(connectionSource, BookName.class);
            TableUtils.createTable(connectionSource, BookContent.class);

            TableUtils.createTable(connectionSource, HadithBook.class);
            TableUtils.createTable(connectionSource, HadithChapter.class);
            TableUtils.createTable(connectionSource, HadithExplanation.class);
            TableUtils.createTable(connectionSource, HadithMain.class);
            TableUtils.createTable(connectionSource, HadithPublisher.class);
            TableUtils.createTable(connectionSource, HadithSection.class);
            TableUtils.createTable(connectionSource, HadithStatus.class);
            TableUtils.createTable(connectionSource, RabiHadith.class);

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
            TableUtils.clearTable(connectionSource, BookWriter.class);
            TableUtils.clearTable(connectionSource, BookType.class);
            TableUtils.clearTable(connectionSource, BookSection.class);
            TableUtils.clearTable(connectionSource, BookName.class);
            TableUtils.clearTable(connectionSource, BookContent.class);

            CsvToDbHelper.sBulkInsert(context, R.raw.bookwriter, sqLiteDatabase);
            CsvToDbHelper.sBulkInsert(context, R.raw.booktype, sqLiteDatabase);
            CsvToDbHelper.sBulkInsert(context, R.raw.hadithpublisher, sqLiteDatabase);
            CsvToDbHelper.sBulkInsert(context, R.raw.hadithpublisher, sqLiteDatabase);
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

    public Dao<BookWriter, Integer> getBookWriterDao() {
        if (null == bookWriterDao) {
            try {
                bookWriterDao = getDao(BookWriter.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return bookWriterDao;
    }

    public Dao<BookType, Integer> getBookTypeDao() {
        if (null == bookTypeDao) {
            try {
                bookTypeDao = getDao(BookType.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return bookTypeDao;
    }

    public Dao<BookName, Integer> getBookNameDao() {
        if (null == bookNameDao) {
            try {
                bookNameDao = getDao(BookName.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return bookNameDao;
    }

    public Dao<BookSection, Integer> getBookSectionDao() {
        if (null == bookSectionDao) {
            try {
                bookSectionDao = getDao(BookSection.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return bookSectionDao;
    }

    public Dao<BookContent, Integer> getBookContentDao() {
        if (null == bookContentDao) {
            try {
                bookContentDao = getDao(BookContent.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return bookContentDao;
    }

    public Dao<HadithBook, Integer> getHadithBookDao() {
        if (null == hadithBookDao) {
            try {
                hadithBookDao = getDao(HadithBook.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return hadithBookDao;
    }

    public Dao<HadithChapter, Integer> getHadithChapterDao() {
        if (null == hadithChapterDao) {
            try {
                hadithChapterDao = getDao(HadithChapter.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return hadithChapterDao;
    }

    public Dao<HadithExplanation, Integer> getHadithExplanationDao() {
        if (null == hadithExplanationDao) {
            try {
                hadithExplanationDao = getDao(HadithExplanation.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return hadithExplanationDao;
    }

    public Dao<HadithMain, Integer> getHadithMainDao() {
        if (null == hadithMainDao) {
            try {
                hadithMainDao = getDao(HadithMain.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return hadithMainDao;
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


    public Dao<HadithSection, Integer> getHadithSectionDao() {
        if (null == hadithSectionDao) {
            try {
                hadithSectionDao = getDao(HadithSection.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return hadithSectionDao;
    }

    public Dao<HadithStatus, Integer> getHadithStatusDao() {
        if (null == hadithStatusDao) {
            try {
                hadithStatusDao = getDao(HadithStatus.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return hadithStatusDao;
    }

    public Dao<RabiHadith, Integer> getRabiHadithDao() {
        if (null == rabiHadithDao) {
            try {
                rabiHadithDao = getDao(RabiHadith.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return rabiHadithDao;
    }


}
