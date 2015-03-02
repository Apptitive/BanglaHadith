package com.hadithbd.banglahadith.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hadithbd.banglahadith.R;

public class AboutUsActivity extends BaseActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setHomeBackground();

        initViews();
        setUpToolbar();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
