package com.hadithbd.banglahadith.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.HadithListAdapter;

public class HadithListActivity extends ActionBarActivity {
    private static final int NUMBER_OF_COLUMNS = 2;

    private RecyclerView mRecyclerView;

    private HadithListAdapter mHadithListAdapter;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith_list);

        initViews();

        setUpToolbar();

        initRecyclerAdapter();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerAdapter() {
        mHadithListAdapter = new HadithListAdapter(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        mRecyclerView.setAdapter(mHadithListAdapter);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.hadith_list_recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hadith_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
