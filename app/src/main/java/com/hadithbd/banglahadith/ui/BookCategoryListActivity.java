package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.BookCategoryListAdapter;
import com.hadithbd.banglahadith.bangla.UtilBanglaSupport;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.viewmodel.BookTypeInfo;

import java.util.List;

public class BookCategoryListActivity extends BaseActivity implements BookCategoryListAdapter.BookCategoryItemClickListener{

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
        setupActionBar();

        initRecyclerAdapter();
    }

    public void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(UtilBanglaSupport.getBanglaSpannableString(""));
        actionBar.setSubtitle(UtilBanglaSupport.getBanglaSpannableString(""));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerAdapter() {
        mRecyclerView.setHasFixedSize(true);
        mBookCategoryListAdapter = new BookCategoryListAdapter(this, mBookTypeInfoList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, Constants.NUMBER_OF_GRID_COLUMNS));
        mBookCategoryListAdapter.setBookCategoryItemClickListener(this);
        mRecyclerView.setAdapter(mBookCategoryListAdapter);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }


    @Override
    public void onCategoryItemClicked(int position) {
        final BookTypeInfo bookTypeInfo = mBookTypeInfoList.get(position);
        Intent intent = new Intent(this, BookListActivity.class);
        intent.putExtra(Constants.BOOK_TYPE_ID, bookTypeInfo.getTypeId());
        startActivity(intent);
    }
}
