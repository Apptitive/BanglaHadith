package com.hadithbd.banglahadith.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.views.BanglaTextView;

import java.util.List;

/**
 * Created by Sharif on 2/19/2015.
 */
public class HadithDetailListAdapter extends RecyclerView.Adapter<HadithDetailListAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<String> mHadithDetailLists;

    private HadithDetailItemListener mHadithDetailItemListener;

    public HadithDetailListAdapter(List<String> hadithDetailLists) {
        mHadithDetailLists = hadithDetailLists;
    }


    public void setHadithDetailItemListener(HadithDetailItemListener hadithDetailItemListener) {
        this.mHadithDetailItemListener = hadithDetailItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.hadith_detail_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTextDrawable.setBanglaText(mHadithDetailLists.get(position));
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mHadithDetailLists != null ? mHadithDetailLists.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (mHadithDetailItemListener != null) {
            mHadithDetailItemListener.onHadithDetailItemClicked((int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public BanglaTextView mTextDrawable;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextDrawable = (BanglaTextView) itemView.findViewById(R.id.text_drawable);
            itemView.setOnClickListener(HadithDetailListAdapter.this);
        }
    }

    public static interface HadithDetailItemListener {
        void onHadithDetailItemClicked(int position);
    }

}
