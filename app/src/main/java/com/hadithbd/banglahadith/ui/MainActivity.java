package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.database.CsvToDbHelper;
import com.hadithbd.banglahadith.database.DbHelper;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.database.tables.hadith.HadithPublisher;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private static final int BISMILLAH_MARGIN_TOP = 72;

    private RelativeLayout mLayoutAllHadiths;

    private RelativeLayout mLayoutAllBooks;

    private DbHelper mDbHelper;

    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setHomeBackgroundLayer();

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
                startActivity(new Intent(getBaseContext(), BookChapterListActivity.class));
            }
        });

    }

    private void initLayouts() {
        mLayoutAllHadiths = (RelativeLayout) findViewById(R.id.layout_all_hadiths);
        mLayoutAllBooks = (RelativeLayout) findViewById(R.id.layout_all_books);
    }

    public void setHomeBackgroundLayer() {

        BitmapDrawable whiteLayer = (BitmapDrawable) getResources().getDrawable(R.drawable.home_white_layer);
        BitmapDrawable iconCaliography = (BitmapDrawable) getResources().getDrawable(R.drawable.bg_caliography);
        iconCaliography.setGravity(Gravity.BOTTOM);

        BitmapDrawable blueLayer = (BitmapDrawable) getResources().getDrawable(R.drawable.home_blue_layer);

        BitmapDrawable iconBismillah = (BitmapDrawable) getResources().getDrawable(R.drawable.bg_bismillah);
        iconBismillah.setGravity(Gravity.CENTER_HORIZONTAL);
        iconBismillah.setGravity(Gravity.TOP);

        Drawable[] drawables = new Drawable[]{blueLayer, iconCaliography, whiteLayer, iconBismillah};


        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        layerDrawable.setLayerInset(3, 0, BISMILLAH_MARGIN_TOP, 0, 0);

        setLayerToBackground(layerDrawable);

    }


    private void setLayerToBackground(LayerDrawable layerDrawable) {
        LinearLayout view = (LinearLayout) findViewById(R.id.home_layout_main);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(layerDrawable);
        } else {
            view.setBackground(layerDrawable);
        }
    }
}
