package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.BookListAdapter;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.viewmodel.BookInfo;

import java.util.List;

public class BookListActivity extends BaseActivity implements BookListAdapter.BookItemClickListener {

    private RecyclerView mRecyclerView;

    private BookListAdapter mBookListAdapter;
    private Toolbar mToolbar;

    private List<BookInfo> mBookInfoList;
    private int mBookTypeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        setHomeBackground();

        getMessageFromBundle();

        mBookInfoList = DbManager.getInstance().getAllBookInfoForType(mBookTypeId);

        initViews();

        setUpToolbar();

        initRecyclerAdapter();
    }

        private void getMessageFromBundle() {
            Bundle data = getIntent().getExtras();
            if (data != null) {
                mBookTypeId = data.getInt(Constants.BOOK_TYPE_ID);
                Log.i("TAG", "mBookTypeId id: " + mBookTypeId);
            }
        }


    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerAdapter() {
        mRecyclerView.setHasFixedSize(true);
        mBookListAdapter = new BookListAdapter(this, mBookInfoList);
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
        final BookInfo bookInfo = mBookInfoList.get(position);
        Intent intent = new Intent(this, BookSectionListActivity.class);
        intent.putExtra(Constants.BOOK_ID, bookInfo.getBookId());
        startActivity(intent);
    }


}
