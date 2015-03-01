package com.hadithbd.banglahadith.database;

import android.content.Context;

import com.hadithbd.banglahadith.database.tables.book.BookContent;
import com.hadithbd.banglahadith.database.tables.book.BookName;
import com.hadithbd.banglahadith.database.tables.book.BookSection;
import com.hadithbd.banglahadith.database.tables.book.BookType;
import com.hadithbd.banglahadith.database.tables.book.BookWriter;
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
}


