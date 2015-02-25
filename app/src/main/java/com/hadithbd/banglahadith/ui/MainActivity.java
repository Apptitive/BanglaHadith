package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.database.CsvToDbHelper;
import com.hadithbd.banglahadith.database.DbHelper;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.database.tables.hadith.HadithPublisher;

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
            Log.e("Insertion", "Start");
            CsvToDbHelper.sBulkInsert(this, R.raw.hadithpublisher, mDatabase);
            mDatabase.setTransactionSuccessful();
            Log.e("Insertion", "End");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDatabase.endTransaction();
        }

        List<HadithPublisher> hadithPublishers = DbManager.getInstance().getAllHadithPublishers();
        Log.e("Size", "" + hadithPublishers.size());
        for (HadithPublisher hp : hadithPublishers) {
            Log.e("Publisher Name : ", hp.getNameBengali());
        }
        initLayouts();

        mLayoutAllHadiths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), HadithListActivity.class));
            }
        });

        mLayoutAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void initLayouts() {
        mLayoutAllHadiths = (RelativeLayout) findViewById(R.id.layout_all_hadiths);
        mLayoutAllBooks = (RelativeLayout) findViewById(R.id.layout_all_books);
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
