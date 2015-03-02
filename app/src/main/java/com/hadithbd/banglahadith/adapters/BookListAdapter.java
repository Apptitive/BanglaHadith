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
 * Created by Sharif on 3/1/2015.
 */
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> implements View.OnClickListener {


    private BookItemClickListener mBookItemClickListener;

    private List<HadithBookInfo> mBookInfoList;

    private String mChapter, mHadith, mCountSuffix;

    public BookListAdapter(Context context, List<HadithBookInfo> bookInfoList) {
        mBookInfoList = bookInfoList;
        mChapter = context.getResources().getString(R.string.chapter_bangla);
        mHadith = context.getResources().getString(R.string.hadith_bangla);
        mCountSuffix = context.getResources().getString(R.string.text_count_suffix);
    }


    public void setBookItemClickListener(BookItemClickListener bookItemClickListener) {
        this.mBookItemClickListener = bookItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final HadithBookInfo bookInfo = mBookInfoList.get(position);
        viewHolder.bookName.setBanglaText(bookInfo.getBookName());
        viewHolder.bookChapterCount.setBanglaText(mChapter + " " + String.valueOf(bookInfo.getChapterCount()) + mCountSuffix);
        viewHolder.hadithCount.setBanglaText(mHadith + " " + String.valueOf(bookInfo.getHadithCount()) + mCountSuffix);
        viewHolder.bookItemColor.setBackgroundColor(BanglaHadithApp.itemStripColors.get(position % 8));
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mBookInfoList != null ? mBookInfoList.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (mBookItemClickListener != null) {
            mBookItemClickListener.onBookItemClicked((int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public BanglaTextView bookName, bookChapterCount, hadithCount;


        public View bookItemColor;

        public ViewHolder(View itemView) {
            super(itemView);
            bookName = (BanglaTextView) itemView.findViewById(R.id.book_name);
            bookChapterCount = (BanglaTextView) itemView.findViewById(R.id.book_chapter_count);
            hadithCount = (BanglaTextView) itemView.findViewById(R.id.hadith_count);
            bookItemColor = itemView.findViewById(R.id.book_item_color);
            itemView.setOnClickListener(BookListAdapter.this);
        }
    }


    public static interface BookItemClickListener {
        void onBookItemClicked(int position);
    }

}
