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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.adapters.BanglaArrayAdapter;
import com.hadithbd.banglahadith.bangla.UtilBanglaSupport;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.database.tables.hadith.HadithBook;
import com.hadithbd.banglahadith.util.UIUtils;

import java.util.List;

/**
 * Created by Sharif on 3/4/2015.
 */
public class SearchFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    public static final String TAG = "SEARCG TAG";

    private RadioGroup mRgSearchFromHadithOrBook, mRgSearchFromTextOrHadithNumber, mRgSearchFromHadithBook;

    private LinearLayout mRowHadithBook, mSearchRootLayout;

    private SparseArray<CheckBox> bookCheckBoxes = new SparseArray<>();

    private List<HadithBook> mHadithBooks;

    private Spinner mSpinnerBooks;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHadithBooks = DbManager.getInstance().getAllHadithBooks();

        loadBookCheckboxes();
    }

    private void loadBookCheckboxes() {

        for (HadithBook hadithBook: mHadithBooks) {
            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setText(UtilBanglaSupport.getBanglaSpannableString(hadithBook.getNameBengali()));
            bookCheckBoxes.put(hadithBook.getId(), checkBox);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        setBanglaTextToRadioButtons();

        setupListeners();

        setSpinnerBooksAdapter();
    }

    private void setSpinnerBooksAdapter() {
        mSpinnerBooks.setAdapter(new BanglaArrayAdapter(getActivity(), mHadithBooks));
    }


    /**
     * Set bangla text to radio in each buttons
     *
     * */
    private void setBanglaTextToRadioButtons() {
        UIUtils.setRadioButtonTexts(mRgSearchFromHadithOrBook,getString(R.string.search_from_hadith),
                getString(R.string.search_from_book));
        UIUtils.setRadioButtonTexts(mRgSearchFromTextOrHadithNumber,getString(R.string.search_with_plain_text),
                getString(R.string.search_with_hadith_number));
        UIUtils.setRadioButtonTexts(mRgSearchFromHadithBook,getString(R.string.search_all_book),
                getString(R.string.search_favorite_book));
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
        mSpinnerBooks = (Spinner) view.findViewById(R.id.spinner_books);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.rb_search_from_hadiths:
                mRgSearchFromTextOrHadithNumber.setVisibility(View.VISIBLE);
                mRowHadithBook.setVisibility(View.VISIBLE);
                RadioButton radioButton = (RadioButton) mRgSearchFromHadithBook.getChildAt(0);
                radioButton.setChecked(true);
                RadioButton button = (RadioButton) mRgSearchFromTextOrHadithNumber.getChildAt(0);
                button.setChecked(true);
                break;
            case R.id.rb_search_from_books:
                mRgSearchFromTextOrHadithNumber.setVisibility(View.GONE);
                mRowHadithBook.setVisibility(View.GONE);
                removeCheckBoxes();
                break;
            case R.id.rb_search_with_text:
                mRowHadithBook.setVisibility(View.VISIBLE);
                if (mRgSearchFromTextOrHadithNumber.getCheckedRadioButtonId() == R.id.rb_search_with_text) {
                    RadioButton radioButton1 = (RadioButton) mRgSearchFromHadithBook.getChildAt(0);
                    radioButton1.setChecked(true);
                }

                break;
            case R.id.rb_search_with_hadith_number:
                mRowHadithBook.setVisibility(View.GONE);
                removeCheckBoxes();
                break;
            case R.id.rb_search_all_book:
                removeCheckBoxes();
                break;
            case R.id.rb_search_favorite_book:
                showBooksToCheck();
                break;
        }

    }

    private void removeCheckBoxes() {
        for (HadithBook hadithBook: mHadithBooks) {
            mSearchRootLayout.removeView(bookCheckBoxes.get(hadithBook.getId()));
        }

    }

    private void showBooksToCheck() {
        int childIndex = 4;
        for (HadithBook hadithBook: mHadithBooks) {
            mSearchRootLayout.addView(bookCheckBoxes.get(hadithBook.getId()), childIndex++);
        }
    }
}
