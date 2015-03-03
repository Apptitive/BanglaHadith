package com.hadithbd.banglahadith.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.viewmodel.HadithBookSectionInfo;
import com.hadithbd.banglahadith.views.BanglaTextView;

import java.util.List;

/**
 * Created by Sharif on 2/19/2015.
 */
public class HadithSectionListAdapter extends RecyclerView.Adapter<HadithSectionListAdapter.ViewHolder>
        implements View.OnClickListener {

    private String mHadithListPrefixText, mCountSuffix;
    private List<HadithBookSectionInfo> mHadithBookSectionInfoList;

    private HadithSectionItemClickListener mHadithSectionItemClickListener;

    public HadithSectionListAdapter(Context context, List<HadithBookSectionInfo> hadithBookSectionInfoList) {
        mHadithBookSectionInfoList = hadithBookSectionInfoList;
        mHadithListPrefixText = context.getResources().getString(R.string.hadith_list_prefix_text);
        mCountSuffix = context.getResources().getString(R.string.text_count_suffix);
    }

    public void setmHadithSectionItemClickListener(HadithSectionItemClickListener hadithSectionItemClickListener) {
        this.mHadithSectionItemClickListener = hadithSectionItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.hadith_section_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final HadithBookSectionInfo hadithBookSectionInfo = mHadithBookSectionInfoList.get(position);
        viewHolder.hadithSectionName.setBanglaText(hadithBookSectionInfo.getSectionName());
        viewHolder.hadithCount.setBanglaText(mHadithListPrefixText + " " + String.valueOf(hadithBookSectionInfo.getHadithCount() + mCountSuffix));
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mHadithBookSectionInfoList != null ? mHadithBookSectionInfoList.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (mHadithSectionItemClickListener != null) {
            mHadithSectionItemClickListener.onHadithSectionItemClicked((int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public BanglaTextView hadithSectionName, hadithCount;

        public ViewHolder(View itemView) {
            super(itemView);
            hadithSectionName = (BanglaTextView) itemView.findViewById(R.id.hadith_chapter_name);
            hadithCount = (BanglaTextView) itemView.findViewById(R.id.hadith_count);
            itemView.setOnClickListener(HadithSectionListAdapter.this);
        }
    }

    public static interface HadithSectionItemClickListener {
        void onHadithSectionItemClicked(int position);
    }

}
