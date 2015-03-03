package com.hadithbd.banglahadith.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.bangla.UtilBanglaSupport;
import com.hadithbd.banglahadith.viewmodel.BookContentTitleInfo;
import com.hadithbd.banglahadith.views.BanglaTextView;

import java.util.List;

/**
 * Created by Sharif on 2/28/2015.
 */
public class BookQuestionListAdapter extends RecyclerView.Adapter<BookQuestionListAdapter.ViewHolder>
        implements View.OnClickListener {

    private BookQuestionItemClickListener mBookQuestionItemClickListener;

    private List<BookContentTitleInfo> mContentTitleInfoList;
    private String mQuestionPrefix;
    private int chapterPrefixColor;

    public BookQuestionListAdapter(Context context, List<BookContentTitleInfo> contentTitleInfoList) {
        mContentTitleInfoList = contentTitleInfoList;
        mQuestionPrefix = context.getString(R.string.book_question_prefix);
        chapterPrefixColor = context.getResources().getColor(R.color.book_chapter_prefix_color);
    }

    public void setBookQuestionItemClickListener(BookQuestionItemClickListener bookChapterItemClickListener) {
        this.mBookQuestionItemClickListener = bookChapterItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_question_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BookContentTitleInfo bookContentTitleInfo = mContentTitleInfoList.get(position);
        holder.itemView.setTag(position);
        final String data = getColoredText(bookContentTitleInfo);
        holder.bookContentQuestion.setText(Html.fromHtml(UtilBanglaSupport.getBanglaSpannableString(data).toString()));
    }


    private String getColoredText(BookContentTitleInfo bookContentTitleInfo) {
        return "<font color="+chapterPrefixColor+">" + mQuestionPrefix + "</font>" + " " + bookContentTitleInfo.getQuestion();
    }

    @Override
    public int getItemCount() {
        return mContentTitleInfoList != null ? mContentTitleInfoList.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (mBookQuestionItemClickListener != null) {
            mBookQuestionItemClickListener.onQuestionItemClicked((int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public BanglaTextView bookContentQuestion;

        public ViewHolder(View itemView) {
            super(itemView);
            bookContentQuestion = (BanglaTextView) itemView.findViewById(R.id.book_content_question);
            itemView.setOnClickListener(BookQuestionListAdapter.this);
        }
    }

    public static interface BookQuestionItemClickListener {
        void onQuestionItemClicked(int position);
    }
}
