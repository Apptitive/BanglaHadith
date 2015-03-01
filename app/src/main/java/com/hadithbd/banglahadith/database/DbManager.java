package com.hadithbd.banglahadith.database;

import android.content.Context;

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

    public List<BookWriter> getAllBookWriters(){
        List<BookWriter> bookWriterList = new ArrayList<>();
        try {
            bookWriterList = getHelper().getBookWriterDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookWriterList;
    }

    public List<BookType> getAllBookTypes(){
        List<BookType> bookTypeList = new ArrayList<>();
        try {
            bookTypeList = getHelper().getBookTypeDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookTypeList;
    }

    public List<BookName> getAllBookNames(){
        List<BookName> bookNameList = new ArrayList<>();
        try {
            bookNameList = getHelper().getBookNameDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookNameList;
    }

    public List<BookContent> getAllBookContents(){
        List<BookContent> bookContentList = new ArrayList<>();
        try {
            bookContentList = getHelper().getBookContentDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookContentList;
    }

    public List<BookSection> getAllBookSections(){
        List<BookSection> bookSectionList = new ArrayList<>();
        try {
            bookSectionList = getHelper().getBookSectionDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookSectionList;
    }

    public List<RabiHadith> getAllRabiHadiths(){
        List<RabiHadith> rabiHadithList = new ArrayList<>();
        try {
            rabiHadithList = getHelper().getRabiHadithDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rabiHadithList;
    }

    public List<HadithStatus> getAllHadithStatus(){
        List<HadithStatus> hadithStatusList = new ArrayList<>();
        try {
            hadithStatusList = getHelper().getHadithStatusDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithStatusList;
    }

    public List<HadithSection> getAllHadithSections(){
        List<HadithSection> hadithSectionList = new ArrayList<>();
        try {
            hadithSectionList = getHelper().getHadithSectionDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithSectionList;
    }

    public List<HadithPublisher> getAllHadithPublishers(){
        List<HadithPublisher> hadithPublisherList = new ArrayList<>();
        try {
            hadithPublisherList = getHelper().getHadithPublisherDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithPublisherList;
    }

    public List<HadithMain> getAllHadithMains(){
        List<HadithMain> hadithMainList = new ArrayList<>();
        try {
            hadithMainList = getHelper().getHadithMainDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithMainList;
    }


    public List<HadithExplanation> getAllHadithExplanations(){
        List<HadithExplanation> hadithExplanationList = new ArrayList<>();
        try {
            hadithExplanationList = getHelper().getHadithExplanationDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithExplanationList;
    }

    public List<HadithChapter> getAllHadithChapters(){
        List<HadithChapter> hadithChapterList = new ArrayList<>();
        try {
            hadithChapterList = getHelper().getHadithChapterDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithChapterList;
    }

    public List<HadithBook> getAllHadithBooks(){
        List<HadithBook> hadithBookList = new ArrayList<>();
        try {
            hadithBookList = getHelper().getHadithBookDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hadithBookList;
    }
}


