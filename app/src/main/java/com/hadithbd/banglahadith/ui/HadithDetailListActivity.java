package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.HadithDetailListAdapter;
import com.hadithbd.banglahadith.util.Utils;

public class HadithDetailListActivity extends BaseActivity implements HadithDetailListAdapter.HadithDetailItemListener{

    private static final int NUMBER_OF_COLUMNS = 2;

    private RecyclerView mRecyclerView;

    private HadithDetailListAdapter mHadithDetailListAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith_detail_list);
        setHomeBackground();
        initViews();

        setUpToolbar();

        initRecyclerAdapter();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerAdapter() {
        mRecyclerView.setHasFixedSize(true);
        mHadithDetailListAdapter = new HadithDetailListAdapter(Utils.getDummyHaditsData());
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        mHadithDetailListAdapter.setHadithDetailItemListener(this);
        mRecyclerView.setAdapter(mHadithDetailListAdapter);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.hadith_detail_recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public void onHadithDetailItemClicked(int position) {
        Intent intent = new Intent(HadithDetailListActivity.this, HadithDetailActivity.class);
        startActivity(intent);
    }
}
