package com.hadithbd.banglahadith.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.views.BanglaTextView;

/**
 * Created by Sharif on 2/28/2015.
 */
public class BookChapterListAdapter extends RecyclerView.Adapter<BookChapterListAdapter.ViewHolder>
        implements View.OnClickListener {

    private BookChapterItemClickListener mBookChapterItemClickListener;


    public BookChapterListAdapter() {
    }

    public void setBookChapterItemClickListener(BookChapterItemClickListener bookChapterItemClickListener) {
        this.mBookChapterItemClickListener = bookChapterItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_chapter_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onClick(View v) {
        if (mBookChapterItemClickListener != null) {
            mBookChapterItemClickListener.onItemClicked((int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public BanglaTextView bookChapterTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            bookChapterTitle = (BanglaTextView) itemView.findViewById(R.id.book_chapter_title);
            itemView.setOnClickListener(BookChapterListAdapter.this);
        }
    }

    public static interface BookChapterItemClickListener {
        void onItemClicked(int position);
    }
}
