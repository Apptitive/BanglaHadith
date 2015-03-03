package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.BookCategoryListAdapter;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.viewmodel.BookTypeInfo;

import java.util.List;

public class BookCategoryListActivity extends BaseActivity implements BookCategoryListAdapter.BookCategoryItemClickListener{

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<BookTypeInfo> mBookTypeInfoList;
    private BookCategoryListAdapter mBookCategoryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_category_list);

        mBookTypeInfoList = DbManager.getInstance().getAllBookTypeInfo();

        setHomeBackground();

        initViews();

        setUpToolbar();

        initRecyclerAdapter();
    }

    private void initRecyclerAdapter() {
        mRecyclerView.setHasFixedSize(true);
        mBookCategoryListAdapter = new BookCategoryListAdapter(this, mBookTypeInfoList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, Constants.NUMBER_OF_GRID_COLUMNS));
        mBookCategoryListAdapter.setBookCategoryItemClickListener(this);
        mRecyclerView.setAdapter(mBookCategoryListAdapter);
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCategoryItemClicked(int position) {
        final BookTypeInfo bookTypeInfo = mBookTypeInfoList.get(position);
        Intent intent = new Intent(this, BookListActivity.class);
        intent.putExtra(Constants.BOOK_TYPE_ID, bookTypeInfo.getTypeId());
        startActivity(intent);
    }
}
