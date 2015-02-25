package com.hadithbd.banglahadith.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadithbd.banglahadith.BanglaHadithApp;
import com.hadithbd.banglahadith.R;

/**
 * Created by Sharif on 2/24/2015.
 */
public class HadithListAdapter extends RecyclerView.Adapter<HadithListAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;

    private HadithItemClickListener mHadithItemClickListener;

    public HadithListAdapter(Context context) {
        mContext = context;
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
        viewHolder.mHadithItemColor.setBackgroundColor(BanglaHadithApp.itemStripColors.get(position % 8));
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onClick(View v) {
        if (mHadithItemClickListener != null) {
            mHadithItemClickListener.onHadithItemClicked((int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View mHadithItemColor;

        public ViewHolder(View itemView) {
            super(itemView);
            mHadithItemColor = itemView.findViewById(R.id.hadith_item_color);
            itemView.setOnClickListener(HadithListAdapter.this);
        }
    }


    public static interface HadithItemClickListener {
        void onHadithItemClicked(int position);
    }

}
