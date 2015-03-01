package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.BookListAdapter;
import com.hadithbd.banglahadith.util.Constants;

public class BookListActivity extends BaseActivity implements BookListAdapter.BookItemClickListener{

    private RecyclerView mRecyclerView;

    private BookListAdapter mBookListAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

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
        mBookListAdapter = new BookListAdapter(null);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, Constants.NUMBER_OF_GRID_COLUMNS));
        mBookListAdapter.setBookItemClickListener(this);
        mRecyclerView.setAdapter(mBookListAdapter);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }


    @Override
    public void onBookItemClicked(int position) {
        Intent intent = new Intent(this, BookChapterListActivity.class);
        startActivity(intent);
    }
}
