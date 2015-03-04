package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class HadithDetailActivity extends BaseActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private HadithMainInfo hadithInView;
    private HashMap<Integer, View> tabToMarkerMap;
    private View currentVisibleTabMarker;
    private TextView textViewChapter;
    private TextView textViewHadith;
    private BanglaTextView footNote;
    private BanglaTextView hadithStatus;
    private BanglaTextView rabiName;
    private ActionBar actionBar;
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

    private void switchTabMarkerVisibility(int selectedTabId) {
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
            menu.add(Menu.NONE, hadithId, Menu.NONE, UtilBanglaSupport.getBanglaSpannableString(getString(R.string.hadith_number)
                    + " " + Utils.translateNumber(hadithId)));
        }
        hadithListPopupMenu.setOnMenuItemClickListener(this);
    }

    private void setHadithView(int hadithId) {
        hadithInView = DbManager.getInstance().getHadithInformationForHadith(hadithId);
        if (hadithInView == null) {
            return;
        }
        actionBar.setTitle(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getBookName()));
        actionBar.setSubtitle(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getSectionBengali()));
        textViewChapter.setText(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getChapterBengali()));
        textViewHadith.setText(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getHadithBengali()));
        String note = hadithInView.getNote();
        if (note == null || note.isEmpty() || note.equals("NULL")) {
            footNote.setVisibility(View.GONE);
        } else {
            footNote.setVisibility(View.VISIBLE);
            footNote.setBanglaText(hadithInView.getNote());
        }
        hadithStatus.setBanglaText(hadithInView.getStatusBengali());
        rabiName.setBanglaText(hadithInView.getRabiBengali());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith_detail);
        DbManager.init(this);

        int sectionId = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sectionId = extras.getInt(Constants.HADITH_SECTION_ID);
        }

        setHomeBackground();

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initTabMarkers();

        hadithIdList = DbManager.getInstance().getHadithNoListForSection(sectionId);
        if (hadithIdList == null || hadithIdList.isEmpty()) {
            return;
        }

        textViewChapter = (TextView) findViewById(R.id.textView_chapter_name);
        textViewHadith = (TextView) findViewById(R.id.textView_hadith);
        footNote = (BanglaTextView) findViewById(R.id.textView_footnote);
        hadithStatus = (BanglaTextView) findViewById(R.id.textView_hadith_status);
        rabiName = (BanglaTextView) findViewById(R.id.textView_rabi_name);

        setHadithView(hadithIdList.get(0));
    }

    @Override
    public void onClick(View v) {
        if (hadithInView == null) {
            return;
        }
        int id = v.getId();
        if (v instanceof TextView) {
            switchTabMarkerVisibility(v.getId());
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
                case R.id.tab_text_hadith_explanation:
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

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        setHadithView(menuItem.getItemId());
        return true;
    }
}
