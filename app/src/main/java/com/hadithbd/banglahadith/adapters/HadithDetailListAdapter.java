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
public class HadithDetailListAdapter extends RecyclerView.Adapter<HadithDetailListAdapter.ViewHolder> {

    private List<String> mHadithDetailLists;

    public HadithDetailListAdapter(List<String> hadithDetailLists) {
        mHadithDetailLists = hadithDetailLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_hadith_detail_lists, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTextDrawable.setBanglaText(mHadithDetailLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mHadithDetailLists != null ? mHadithDetailLists.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public BanglaTextView mTextDrawable;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextDrawable = (BanglaTextView) itemView.findViewById(R.id.text_drawable);
        }
    }


}
