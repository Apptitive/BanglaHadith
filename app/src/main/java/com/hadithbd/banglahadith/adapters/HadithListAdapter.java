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
public class HadithListAdapter extends RecyclerView.Adapter<HadithListAdapter.ViewHolder> {

    private Context mContext;

    public HadithListAdapter(Context context) {
        mContext = context;
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
        viewHolder.mHadithItemColor.setBackgroundColor(BanglaHadithApp.itemStripColors.get(position%8));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View mHadithItemColor;

        public ViewHolder(View itemView) {
            super(itemView);
            mHadithItemColor = (View) itemView.findViewById(R.id.hadith_item_color);
        }
    }


}
