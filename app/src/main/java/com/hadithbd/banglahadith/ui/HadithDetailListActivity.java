package com.hadithbd.banglahadith.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.HadithDetailListAdapter;
import com.hadithbd.banglahadith.util.Utils;

public class HadithDetailListActivity extends ActionBarActivity implements HadithDetailListAdapter.HadithDetailItemListener{

    private static final int NUMBER_OF_COLUMNS = 2;

    private RecyclerView mRecyclerView;

    private HadithDetailListAdapter mHadithDetailListAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith_detail_list);

        initViews();

        setUpToolbar();

        initRecyclerAdapter();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerAdapter() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hadith_detail_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onHadithDetailItemClicked(int position) {

    }
}
