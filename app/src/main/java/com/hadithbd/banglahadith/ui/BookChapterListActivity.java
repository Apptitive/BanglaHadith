package com.hadithbd.banglahadith.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.BookChapterListAdapter;
import com.hadithbd.banglahadith.views.SimpleItemDecoration;

public class BookChapterListActivity extends BaseActivity
        implements BookChapterListAdapter.BookChapterItemClickListener{

    private Toolbar mToolbar;

    private BookChapterListAdapter mBookChapterListAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_chapter_list);
        setHomeBackground();
        initViews();

        initToolbar();

        initRecyclerAdapter();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerAdapter() {

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration =
                new SimpleItemDecoration(getResources().getDrawable(android.R.drawable.divider_horizontal_dark));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mBookChapterListAdapter = new BookChapterListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBookChapterListAdapter.setBookChapterItemClickListener(this);
        mRecyclerView.setAdapter(mBookChapterListAdapter);
    }

    @Override
    public void onItemClicked(int position) {

    }
}
