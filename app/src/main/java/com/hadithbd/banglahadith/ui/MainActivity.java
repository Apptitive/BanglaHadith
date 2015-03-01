package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.database.CsvToDbHelper;
import com.hadithbd.banglahadith.database.DbHelper;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.database.tables.book.BookContent;
import com.hadithbd.banglahadith.database.tables.book.BookName;
import com.hadithbd.banglahadith.database.tables.book.BookSection;
import com.hadithbd.banglahadith.database.tables.book.BookType;
import com.hadithbd.banglahadith.database.tables.book.BookWriter;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private RelativeLayout mLayoutAllHadiths;
    private RelativeLayout mLayoutAllBooks;
    private DbHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbManager.init(this);
        mDbHelper = new DbHelper(getApplicationContext());
        mDatabase = mDbHelper.getWritableDatabase();

        mDatabase.beginTransaction();
        try {
            Log.e("Insertion","Start");
            CsvToDbHelper.sBulkInsert(this, R.raw.bookwriter, mDatabase);
            CsvToDbHelper.sBulkInsert(this, R.raw.booktype, mDatabase);
            CsvToDbHelper.sBulkInsert(this, R.raw.bookname, mDatabase);
            CsvToDbHelper.sBulkInsert(this, R.raw.bookcontent, mDatabase);
            CsvToDbHelper.sBulkInsert(this, R.raw.booksection, mDatabase);

            mDatabase.setTransactionSuccessful();
            Log.e("Insertion","End");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDatabase.endTransaction();
        }

        List<BookType> list = DbManager.getInstance().getAllBookTypes();
        Log.e("Size", ""+list.size());
        for(BookType t : list){
            Log.e("Type Name : ", t.getCategoryName());
        }

        List<BookWriter> writers = DbManager.getInstance().getAllBookWriters();
        Log.e("Size", ""+writers.size());
        for(BookWriter writer : writers){
            Log.e("Writer Name : ", writer.getNameEnglish());
        }

        List<BookName> names = DbManager.getInstance().getAllBookNames();
        Log.e("Size", ""+names.size());
        for(BookName name : names){
            Log.e("Book Name : ", name.getNameBengali());
        }

        List<BookContent> contents = DbManager.getInstance().getAllBookContents();
        Log.e("Size", ""+contents.size());
        for(BookContent content : contents){
            Log.e("Content Name : ", content.getQuestion());
        }

        List<BookSection> sections = DbManager.getInstance().getAllBookSections();
        Log.e("Size", ""+sections.size());
        for(BookSection section : sections){
            Log.e("Section Name : ", section.getName());
        }


        initLayouts();

        mLayoutAllHadiths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), HadithDetailListActivity.class));
            }
        });

        mLayoutAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void initLayouts() {
        mLayoutAllHadiths = (RelativeLayout)findViewById(R.id.layout_all_hadiths);
        mLayoutAllBooks = (RelativeLayout)findViewById(R.id.layout_all_books);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
