package com.hadithbd.banglahadith.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.hadithbd.banglahadith.R;

/**
 * Created by Sharif on 3/4/2015.
 */
public class SearchFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRgSearchFromHadithOrBook, mRgSearchFromTextOrHadithNumber, mRgSearchFromHadithBook;

    private LinearLayout mRowHadithBook, mSearchRootLayout;

    private SparseArray<CheckBox> bookCheckBoxes = new SparseArray<>();

    public static final String TAG="SEARCG TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        setupListeners();
    }

    private void setupListeners() {
        mRgSearchFromHadithOrBook.setOnCheckedChangeListener(this);
        mRgSearchFromTextOrHadithNumber.setOnCheckedChangeListener(this);
        mRgSearchFromHadithBook.setOnCheckedChangeListener(this);
    }

    private void initViews(View view) {
        mRgSearchFromHadithOrBook = (RadioGroup) view.findViewById(R.id.rg_search_from_hadith_or_book);
        mRgSearchFromTextOrHadithNumber = (RadioGroup) view.findViewById(R.id.rg_search_from_text_or_hadith_number);
        mRgSearchFromHadithBook = (RadioGroup) view.findViewById(R.id.rg_search_from_hadith_book);
        mRowHadithBook = (LinearLayout) view.findViewById(R.id.row_hadith_book);
        mSearchRootLayout = (LinearLayout) view.findViewById(R.id.search_root_layout);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){
            case R.id.rb_search_from_hadiths:
                mRgSearchFromTextOrHadithNumber.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_search_from_books:
                mRgSearchFromTextOrHadithNumber.setVisibility(View.GONE);
                mRowHadithBook.setVisibility(View.GONE);
                break;
            case R.id.rb_search_with_text:
                mRowHadithBook.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_search_with_hadith_number:
                mRowHadithBook.setVisibility(View.GONE);
                break;
            case R.id.rb_search_all_book:
                break;
            case R.id.rb_search_favorite_book:

                showBooksToCheck();

                break;

        }

    }

    private void showBooksToCheck() {
        int childIndex = 4;
        for (int i = 0; i<10; i++){
            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setText("Sharifur");
            checkBox.setTextColor(getResources().getColor(android.R.color.white));
            mSearchRootLayout.addView(checkBox, childIndex++);
        }
    }
}
