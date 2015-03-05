package com.hadithbd.banglahadith.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.util.Constants;

public class BookQnActivity extends BaseActivity implements View.OnClickListener {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_qn);
        DbManager.init(this);

        int contentId = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            contentId = extras.getInt(Constants.BOOK_CONTENT_ID);
        }

        setHomeBackground();

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

    }
}
