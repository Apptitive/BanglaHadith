package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.HadithListAdapter;
import com.hadithbd.banglahadith.bangla.UtilBanglaSupport;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.viewmodel.HadithBookInfo;

import java.util.List;

public class HadithListActivity extends BaseActivity implements HadithListAdapter.HadithItemClickListener {

    private RecyclerView mRecyclerView;

    private HadithListAdapter mHadithListAdapter;
    private List<HadithBookInfo> mBookInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith_list);
        setHomeBackground();
        initViews();
        setupActionBar();
        mBookInfoList = DbManager.getInstance().getAllHadithBookInfo();

        initRecyclerAdapter();
    }


    private void initRecyclerAdapter() {
        mRecyclerView.setHasFixedSize(true);
        mHadithListAdapter = new HadithListAdapter(this, mBookInfoList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, Constants.NUMBER_OF_GRID_COLUMNS));
        mHadithListAdapter.setHadithItemClickListener(this);
        mRecyclerView.setAdapter(mHadithListAdapter);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.hadith_list_recycler_view);
    }

    public void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(UtilBanglaSupport.getBanglaSpannableString(""));
        actionBar.setSubtitle(UtilBanglaSupport.getBanglaSpannableString(""));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onHadithItemClicked(int position) {
        final HadithBookInfo bookInfo = mBookInfoList.get(position);

        Intent intent = new Intent(this, HadithSectionListActivity.class);
        intent.putExtra(Constants.BOOK_ID, bookInfo.getBookId());
        intent.putExtra(Constants.HADITH_TITLE, bookInfo.getBookName());
        startActivity(intent);
    }
}
