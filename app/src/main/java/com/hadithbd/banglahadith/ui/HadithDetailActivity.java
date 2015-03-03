package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.bangla.UtilBanglaSupport;
import com.hadithbd.banglahadith.database.DbManager;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.util.Utils;
import com.hadithbd.banglahadith.viewmodel.HadithMainInfo;
import com.hadithbd.banglahadith.views.BanglaTextView;

import java.util.HashMap;
import java.util.List;

public class HadithDetailActivity extends BaseActivity implements View.OnClickListener {

    private HadithMainInfo hadithInView;
    private HashMap<Integer, View> tabToMarkerMap;
    private View currentVisibleTabMarker;
    private TextView textViewChapter;
    private TextView textViewHadith;
    private PopupMenu hadithListPopupMenu;
    private List<Integer> hadithIdList;

    private void initTabMarkers() {
        tabToMarkerMap = new HashMap<>();
        View tabMarkerBangla = findViewById(R.id.tab_marker_bangla);
        tabToMarkerMap.put(R.id.tab_text_bangla, tabMarkerBangla);
        currentVisibleTabMarker = tabMarkerBangla;
        tabToMarkerMap.put(R.id.tab_text_english, findViewById(R.id.tab_marker_english));
        tabToMarkerMap.put(R.id.tab_text_arabic, findViewById(R.id.tab_marker_arabic));
        tabToMarkerMap.put(R.id.tab_text_hadith_explanation, findViewById(R.id.tab_marker_hadith_explanation));
    }

    private void switchTabMarkerVisibilty(int selectedTabId) {
        currentVisibleTabMarker.setVisibility(View.INVISIBLE);
        currentVisibleTabMarker = tabToMarkerMap.get(selectedTabId);
        if (currentVisibleTabMarker != null) {
            currentVisibleTabMarker.setVisibility(View.VISIBLE);
        }
    }

    private void createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_hadith));
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_title)));
    }

    private void createHadithListPopupMenu(View anchorView, List<Integer> hadithIdList) {
        hadithListPopupMenu = new PopupMenu(this, anchorView);
        Menu menu = hadithListPopupMenu.getMenu();
        for (int hadithId : hadithIdList) {
            Log.i("id", "" + hadithId);
            menu.add(UtilBanglaSupport.getBanglaSpannableString(getString(R.string.hadith_number)
                    + " " + Utils.translateNumber(hadithId)));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith_detail);
        DbManager.init(this);

        int chapter_id = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            chapter_id = extras.getInt(Constants.HADITH_CHAPTER_ID);
        }

        hadithIdList = DbManager.getInstance().getHadithNoListForChapter(chapter_id);
        if (hadithIdList == null || hadithIdList.isEmpty()) {
            return;
        }
        hadithInView = DbManager.getInstance().getHadithInformationForHadith(hadithIdList.get(0));

        setHomeBackground();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.black_20));
        toolbar.setTitle(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getBookName()));
        toolbar.setTitleTextColor(getResources().getColor(R.color.hadith_detail_title_color));
        toolbar.setSubtitle(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getSectionBengali()));
        toolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initTabMarkers();

        textViewChapter = (TextView) findViewById(R.id.textView_chapter_name);
        textViewChapter.setText(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getChapterBengali()));
        textViewHadith = (TextView) findViewById(R.id.textView_hadith);
        textViewHadith.setText(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getHadithBengali()));
        BanglaTextView footNote = (BanglaTextView) findViewById(R.id.textView_footnote);
        footNote.setBanglaText(hadithInView.getNote());
        BanglaTextView hadithStatus = (BanglaTextView) findViewById(R.id.textView_hadith_status);
        hadithStatus.setBanglaText(hadithInView.getStatusBengali());
        BanglaTextView rabiName = (BanglaTextView) findViewById(R.id.textView_rabi_name);
        rabiName.setBanglaText(hadithInView.getRabiBengali());
    }

    @Override
    public void onClick(View v) {
        if (hadithInView == null) {
            return;
        }
        int id = v.getId();
        if (v instanceof TextView) {
            switchTabMarkerVisibilty(v.getId());
            switch (id) {
                case R.id.tab_text_bangla:
                    textViewHadith.setText(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getHadithBengali()));
                    break;
                case R.id.tab_text_english:
                    textViewChapter.setVisibility(View.GONE);
                    textViewHadith.setText(hadithInView.getHadithEnglish());
                    return;
                case R.id.tab_text_arabic:
                    textViewHadith.setText(UtilBanglaSupport.getArabicSpannableString(hadithInView.getHadithArabic()));
                    break;
                case R.id.tab_hadith_explanation:
                    textViewHadith.setText(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getHadithExplanation()));
                    break;
            }
            if (textViewChapter.getVisibility() == View.GONE) {
                textViewChapter.setVisibility(View.VISIBLE);
            }
            return;
        }
        switch (id) {
            case R.id.menu_share:
                createShareIntent();
                break;
            case R.id.menu_overflow:
                if (hadithListPopupMenu == null) {
                    createHadithListPopupMenu(v, hadithIdList);
                }
                MenuInflater inflater = hadithListPopupMenu.getMenuInflater();
                inflater.inflate(R.menu.hadith_detail_popup_menu, hadithListPopupMenu.getMenu());
                hadithListPopupMenu.show();
                break;
        }
    }
}
