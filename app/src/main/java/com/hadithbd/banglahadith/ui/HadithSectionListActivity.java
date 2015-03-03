package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.HadithSectionListAdapter;
import com.hadithbd.banglahadith.bangla.UtilBanglaSupport;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.viewmodel.HadithBookSectionInfo;

import java.util.List;

public class HadithSectionListActivity extends BaseActivity
        implements HadithSectionListAdapter.HadithSectionItemClickListener {

    public static final String TAG = HadithSectionListActivity.class.getSimpleName();

    private static final int NUMBER_OF_COLUMNS = 2;

    private RecyclerView mRecyclerView;

    private HadithSectionListAdapter mHadithDetailListAdapter;
    private int mBookId;
    private List<HadithBookSectionInfo> mHadithBookSectionInfoList;
    private String mBookTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith_section_list);

        getMessageFromBundle();

        mHadithBookSectionInfoList = DbManager.getInstance().getHadithBookSectionInfo(mBookId);

        setHomeBackground();
        initViews();

        setupActionBar();

        initRecyclerAdapter();
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
        mHadithDetailListAdapter = new HadithSectionListAdapter(this, mHadithBookSectionInfoList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        mHadithDetailListAdapter.setmHadithSectionItemClickListener(this);
        mRecyclerView.setAdapter(mHadithDetailListAdapter);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.hadith_detail_recycler_view);
    }

    public void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(UtilBanglaSupport.getBanglaSpannableString(""));
        actionBar.setSubtitle(UtilBanglaSupport.getBanglaSpannableString(""));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onHadithSectionItemClicked(int position) {
        final HadithBookSectionInfo hadithBookSectionInfo = mHadithBookSectionInfoList.get(position);

        Intent intent = new Intent(HadithSectionListActivity.this, HadithDetailActivity.class);
        intent.putExtra(Constants.HADITH_CHAPTER_ID, hadithBookSectionInfo.getChapterId());
        startActivity(intent);
    }
}
