package com.hadithbd.banglahadith.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadithbd.banglahadith.BanglaHadithApp;
import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.viewmodel.BookTypeInfo;
import com.hadithbd.banglahadith.views.BanglaTextView;

import java.util.List;

/**
 * Created by Sharif on 3/3/2015.
 */
public class BookCategoryListAdapter extends RecyclerView.Adapter<BookCategoryListAdapter.ViewHolder> implements View.OnClickListener {


    private BookCategoryItemClickListener mBookCategoryItemClickListener;

    private List<BookTypeInfo> mBookTypeInfoList;

    private String  mBook, mCountSuffix;

    public BookCategoryListAdapter(Context context, List<BookTypeInfo> bookTypeInfoList) {
        mBookTypeInfoList = bookTypeInfoList;
        mBook = context.getResources().getString(R.string.book_category_preffix_text);
        mCountSuffix = context.getResources().getString(R.string.text_count_suffix);
    }


    public void setBookCategoryItemClickListener(BookCategoryItemClickListener bookCategoryItemClickListener) {
        this.mBookCategoryItemClickListener = bookCategoryItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.book_category_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final BookTypeInfo bookTypeInfo = mBookTypeInfoList.get(position);
        viewHolder.categoryName.setBanglaText(bookTypeInfo.getCategoryName());
        viewHolder.bookCount.setBanglaText(mBook + " " + String.valueOf(bookTypeInfo.getBookCount()) + mCountSuffix);
        viewHolder.bookItemColor.setBackgroundColor(BanglaHadithApp.itemStripColors.get(position % 8));
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mBookTypeInfoList != null ? mBookTypeInfoList.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (mBookCategoryItemClickListener != null) {
            mBookCategoryItemClickListener.onCategoryItemClicked((int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public BanglaTextView categoryName, bookCount;
        public View bookItemColor;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryName = (BanglaTextView) itemView.findViewById(R.id.category_name);
            bookCount = (BanglaTextView) itemView.findViewById(R.id.book_count);
            bookItemColor = itemView.findViewById(R.id.book_item_color);
            itemView.setOnClickListener(BookCategoryListAdapter.this);
        }
    }


    public static interface BookCategoryItemClickListener {
        void onCategoryItemClicked(int position);
    }

}
