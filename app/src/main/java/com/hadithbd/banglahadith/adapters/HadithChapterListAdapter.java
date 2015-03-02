package com.hadithbd.banglahadith.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.viewmodel.HadithBookChapterInfo;
import com.hadithbd.banglahadith.views.BanglaTextView;

import java.util.List;

/**
 * Created by Sharif on 2/19/2015.
 */
public class HadithChapterListAdapter extends RecyclerView.Adapter<HadithChapterListAdapter.ViewHolder>
        implements View.OnClickListener {

    private String mHadith, mCountSuffix;
    private List<HadithBookChapterInfo> mHadithBookChapterInfoList;

    private HadithChapterItemClickListener mHadithChapterItemClickListener;

    public HadithChapterListAdapter(Context context,List<HadithBookChapterInfo> hadithBookChapterInfoList) {
        mHadithBookChapterInfoList = hadithBookChapterInfoList;
        mHadith = context.getResources().getString(R.string.hadith_bangla);
        mCountSuffix = context.getResources().getString(R.string.text_count_suffix);
    }

    public void setmHadithChapterItemClickListener(HadithChapterItemClickListener hadithChapterItemClickListener) {
        this.mHadithChapterItemClickListener = hadithChapterItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.hadith_chapter_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final HadithBookChapterInfo hadithBookChapterInfo = mHadithBookChapterInfoList.get(position);

        viewHolder.hadithChapterId.setBanglaText(String.valueOf(hadithBookChapterInfo.getChapterId()));
        viewHolder.hadithChapterName.setBanglaText(hadithBookChapterInfo.getChapterName());
        viewHolder.hadithCount.setBanglaText(mHadith + " " + String.valueOf(hadithBookChapterInfo.getHadithCount() + mCountSuffix));
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mHadithBookChapterInfoList != null ? mHadithBookChapterInfoList.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (mHadithChapterItemClickListener != null) {
            mHadithChapterItemClickListener.onHadithChapterItemClicked((int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public BanglaTextView hadithChapterId, hadithChapterName, hadithCount;

        public ViewHolder(View itemView) {
            super(itemView);
            hadithChapterId = (BanglaTextView) itemView.findViewById(R.id.hadith_chapter_id);
            hadithChapterName = (BanglaTextView) itemView.findViewById(R.id.hadith_chapter_name);
            hadithCount = (BanglaTextView) itemView.findViewById(R.id.hadith_count);
            itemView.setOnClickListener(HadithChapterListAdapter.this);
        }
    }

    public static interface HadithChapterItemClickListener {
        void onHadithChapterItemClicked(int position);
    }

}
