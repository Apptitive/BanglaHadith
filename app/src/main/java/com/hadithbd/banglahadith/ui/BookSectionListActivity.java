package com.hadithbd.banglahadith.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.BookSectionListAdapter;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.viewmodel.BookContentTitleInfo;
import com.hadithbd.banglahadith.views.SimpleItemDecoration;

import java.util.List;

public class BookSectionListActivity extends BaseActivity
        implements BookSectionListAdapter.BookChapterItemClickListener{

    public static final String TAG = BookSectionListActivity.class.getSimpleName();
    private Toolbar mToolbar;

    private BookSectionListAdapter mBookChapterListAdapter;

    private RecyclerView mRecyclerView;

    private int mBookId;
    private List<BookContentTitleInfo> mContentTitleInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_section_list);
        setHomeBackground();

        getMessageFromBundle();

        mContentTitleInfoList = DbManager.getInstance().getContentTitleInfoForBook(mBookId);

        initViews();

        setUpToolbar();

        initRecyclerAdapter();


    }

    private void getMessageFromBundle() {
        Bundle data= getIntent().getExtras();
        if (data!=null){
            mBookId = data.getInt(Constants.BOOK_ID);
            Log.e(TAG, "Book id: "+ mBookId);
        }
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerAdapter() {

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration =
                new SimpleItemDecoration(getResources().getDrawable(android.R.drawable.divider_horizontal_dark));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mBookChapterListAdapter = new BookSectionListAdapter(this, mContentTitleInfoList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBookChapterListAdapter.setBookChapterItemClickListener(this);
        mRecyclerView.setAdapter(mBookChapterListAdapter);
    }

    @Override
    public void onItemClicked(int position) {

    }
}
