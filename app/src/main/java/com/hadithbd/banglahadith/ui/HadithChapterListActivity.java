package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.HadithChapterListAdapter;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.viewmodel.HadithBookChapterInfo;

import java.util.List;

public class HadithChapterListActivity extends BaseActivity implements HadithChapterListAdapter.HadithChapterItemClickListener {

    public static final String TAG = HadithChapterListActivity.class.getSimpleName();

    private static final int NUMBER_OF_COLUMNS = 2;

    private RecyclerView mRecyclerView;

    private HadithChapterListAdapter mHadithDetailListAdapter;
    private Toolbar mToolbar;

    private int mBookId;
    private List<HadithBookChapterInfo> mHadithBookChapterInfoList;
    private String mBookTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith_chapter_list);

        getMessageFromBundle();

        mHadithBookChapterInfoList = DbManager.getInstance().getHadithBookChapterInfo(mBookId);

        setHomeBackground();
        initViews();


        setUpToolbar();

        initRecyclerAdapter();
    }

    private void setUpToolbar() {
      //  mToolbar.setTitle(UtilBanglaSupport.getBanglaSpannableString(mBookTitle));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.hadith_detail_title_color));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getMessageFromBundle() {
        Bundle data = getIntent().getExtras();
        if (data != null) {
            mBookId = data.getInt(Constants.BOOK_ID);
            mBookTitle = data.getString(Constants.HADITH_TITLE);
            Log.i(TAG, "Book id: " + mBookTitle);
        }
    }

    private void initRecyclerAdapter() {
        mRecyclerView.setHasFixedSize(true);
        mHadithDetailListAdapter = new HadithChapterListAdapter(this, mHadithBookChapterInfoList);
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHadithDetailListAdapter.setmHadithChapterItemClickListener(this);
        mRecyclerView.setAdapter(mHadithDetailListAdapter);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.hadith_detail_recycler_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public void onHadithChapterItemClicked(int position) {
        final HadithBookChapterInfo hadithBookChapterInfo = mHadithBookChapterInfoList.get(position);

        Intent intent = new Intent(HadithChapterListActivity.this, HadithDetailActivity.class);
        intent.putExtra(Constants.HADITH_CHAPTER_ID, hadithBookChapterInfo.getChapterId());
        startActivity(intent);
    }
}
