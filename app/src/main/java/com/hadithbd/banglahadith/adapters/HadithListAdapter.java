package com.hadithbd.banglahadith.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadithbd.banglahadith.BanglaHadithApp;
import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.viewmodel.HadithBookInfo;
import com.hadithbd.banglahadith.views.BanglaTextView;

import java.util.List;

/**
 * Created by Sharif on 2/24/2015.
 */
public class HadithListAdapter extends RecyclerView.Adapter<HadithListAdapter.ViewHolder> implements View.OnClickListener {


    private HadithItemClickListener mHadithItemClickListener;


    private List<HadithBookInfo> mBookInfoList;

    private String mChapter, mHadith, mCountSuffix;

    public HadithListAdapter(Context context, List<HadithBookInfo> bookInfoList) {
        mBookInfoList = bookInfoList;
        mChapter = context.getResources().getString(R.string.chapter_bangla);
        mHadith = context.getResources().getString(R.string.hadith_bangla);
        mCountSuffix = context.getResources().getString(R.string.text_count_suffix);
    }


    public void setHadithItemClickListener(HadithItemClickListener hadithItemClickListener) {
        this.mHadithItemClickListener = hadithItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.hadith_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final HadithBookInfo bookInfo = mBookInfoList.get(position);

        viewHolder.hadithName.setBanglaText(bookInfo.getBookName());
        viewHolder.hadithChapterCount.setBanglaText(mChapter + " " + String.valueOf(bookInfo.getChapterCount()) + mCountSuffix);
        viewHolder.hadithCount.setBanglaText(mHadith + " " + String.valueOf(bookInfo.getHadithCount()) + mCountSuffix);
        viewHolder.hadithItemColor.setBackgroundColor(BanglaHadithApp.itemStripColors.get(position % 8));
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mBookInfoList != null ? mBookInfoList.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (mHadithItemClickListener != null) {
            mHadithItemClickListener.onHadithItemClicked((int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View hadithItemColor;

        public BanglaTextView hadithName, hadithChapterCount, hadithCount;


        public ViewHolder(View itemView) {
            super(itemView);
            hadithName = (BanglaTextView) itemView.findViewById(R.id.hadith_name);
            hadithChapterCount = (BanglaTextView) itemView.findViewById(R.id.hadith_chapter_count);
            hadithCount = (BanglaTextView) itemView.findViewById(R.id.hadith_count);
            hadithItemColor = itemView.findViewById(R.id.hadith_item_color);
            itemView.setOnClickListener(HadithListAdapter.this);
        }
    }


    public static interface HadithItemClickListener {
        void onHadithItemClicked(int position);
    }

}
