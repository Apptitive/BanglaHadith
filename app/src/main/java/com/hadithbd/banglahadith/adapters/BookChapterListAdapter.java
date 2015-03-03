package com.hadithbd.banglahadith.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.util.Utils;
import com.hadithbd.banglahadith.viewmodel.BookContentTitleInfo;
import com.hadithbd.banglahadith.views.BanglaTextView;

import java.util.List;

/**
 * Created by Sharif on 2/28/2015.
 */
public class BookChapterListAdapter extends RecyclerView.Adapter<BookChapterListAdapter.ViewHolder>
        implements View.OnClickListener {

    private BookChapterItemClickListener mBookChapterItemClickListener;

    private List<BookContentTitleInfo> mContentTitleInfoList;
    private String mQuestionPrefix;
    private int mColorCode;


    public BookChapterListAdapter(Context context, List<BookContentTitleInfo> contentTitleInfoList) {
        mContentTitleInfoList = contentTitleInfoList;
        mQuestionPrefix = context.getString(R.string.book_question_prefix);
        mColorCode = context.getResources().getColor(R.color.hadith_detail_title_color);
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
        final BookContentTitleInfo bookContentTitleInfo = mContentTitleInfoList.get(position);
        holder.itemView.setTag(position);
        holder.bookContentQuestion.setBanglaText(Utils.getColoredSpannable(mQuestionPrefix +" " +bookContentTitleInfo.getBookContentId(), mColorCode) +" "+ bookContentTitleInfo.getQuestion());
    }

    @Override
    public int getItemCount() {
        return mContentTitleInfoList != null ? mContentTitleInfoList.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (mBookChapterItemClickListener != null) {
            mBookChapterItemClickListener.onItemClicked((int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public BanglaTextView bookContentQuestion;

        public ViewHolder(View itemView) {
            super(itemView);
            bookContentQuestion = (BanglaTextView) itemView.findViewById(R.id.book_content_question);
            itemView.setOnClickListener(BookChapterListAdapter.this);
        }
    }

    public static interface BookChapterItemClickListener {
        void onItemClicked(int position);
    }
}
