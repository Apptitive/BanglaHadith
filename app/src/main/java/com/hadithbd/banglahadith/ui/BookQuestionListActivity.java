package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.BookQuestionListAdapter;
import com.hadithbd.banglahadith.bangla.UtilBanglaSupport;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.viewmodel.BookContentTitleInfo;
import com.hadithbd.banglahadith.views.SimpleItemDecoration;

import java.util.List;

public class BookQuestionListActivity extends BaseActivity
        implements BookQuestionListAdapter.BookQuestionItemClickListener {

    public static final String TAG = BookQuestionListActivity.class.getSimpleName();

    private BookQuestionListAdapter mBookChapterListAdapter;

    private RecyclerView mRecyclerView;

    private int mBookId;
    private List<BookContentTitleInfo> mContentTitleInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_question_list);
        setHomeBackground();

        getMessageFromBundle();

        mContentTitleInfoList = DbManager.getInstance().getContentTitleInfoForBook(mBookId);

        initViews();
        setupActionBar();


        initRecyclerAdapter();


    }

    public void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(UtilBanglaSupport.getBanglaSpannableString(""));
        actionBar.setSubtitle(UtilBanglaSupport.getBanglaSpannableString(""));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getMessageFromBundle() {
        Bundle data = getIntent().getExtras();
        if (data != null) {
            mBookId = data.getInt(Constants.BOOK_ID);
            Log.e(TAG, "Book id: " + mBookId);
        }
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }


    private void initRecyclerAdapter() {

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration =
                new SimpleItemDecoration(getResources().getDrawable(android.R.drawable.divider_horizontal_dark));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mBookChapterListAdapter = new BookQuestionListAdapter(this, mContentTitleInfoList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBookChapterListAdapter.setBookQuestionItemClickListener(this);
        mRecyclerView.setAdapter(mBookChapterListAdapter);
    }

    @Override
    public void onQuestionItemClicked(int position) {
        startActivity(new Intent(this, BookQnActivity.class));
    }
}
