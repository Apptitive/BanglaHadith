package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    private int currentHadithIdPos;
    private HadithMainInfo hadithInView;
    private HashMap<Integer, View> tabToMarkerMap;
    private View currentVisibleTabMarker;
    private View footnoteView;
    private ImageView imageViewTickHadith;
    private TextView textViewChapter;
    private TextView textViewHadith;
    private BanglaTextView textViewHadithNumber;
    private BanglaTextView textViewFootnote;
    private BanglaTextView textViewHadithStatus;
    private BanglaTextView textViewRabiName;
    private ImageButton imageButtonNavBack;
    private ImageButton imageButtonNavForward;
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

    private void handleNavButtons() {
        imageButtonNavBack.setEnabled(currentHadithIdPos - 1 >= 0);
        imageButtonNavForward.setEnabled(currentHadithIdPos + 1 < hadithIdList.size());
    }

    private void createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, Constants.HADITH_SHARE_URL + hadithInView.getId());
        startActivity(Intent.createChooser(shareIntent
                , UtilBanglaSupport.getBanglaSpannableString(getString(R.string.share_title))));
    }

    private void createShareViaEmailIntent() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, hadithInView.getBookName() + ", " + hadithInView.getSectionBengali()
                + ", " + getString(R.string.hadith_number) + Utils.translateNumber(hadithInView.getHadithNo()));
        emailIntent.putExtra(Intent.EXTRA_TEXT, hadithInView.getHadithBengali());
        startActivity(Intent.createChooser(emailIntent
                , UtilBanglaSupport.getBanglaSpannableString(getString(R.string.share_title_email))));
    }

    private void createReportViaEmailIntent() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", Constants.REPORT_SENDTO_ADDRESS, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, hadithInView.getBookName() + ", "
                + getString(R.string.hadith_number) + Utils.translateNumber(hadithInView.getHadithNo()));
        emailIntent.putExtra(Intent.EXTRA_TEXT, hadithInView.getHadithBengali());
        startActivity(Intent.createChooser(emailIntent
                , UtilBanglaSupport.getBanglaSpannableString(getString(R.string.report_hadith_title))));
    }

    private void createHadithListPopupMenu(View anchorView, List<Integer> hadithIdList) {
        hadithListPopupMenu = new PopupMenu(this, anchorView);
        Menu menu = hadithListPopupMenu.getMenu();
        for (int hadithId : hadithIdList) {
            menu.add(Menu.NONE, hadithId, Menu.NONE, UtilBanglaSupport.getBanglaSpannableString(getString(R.string.hadith_number)
                    + " " + Utils.translateNumber(hadithId)));
        }
        hadithListPopupMenu.setOnMenuItemClickListener(this);
        MenuInflater inflater = hadithListPopupMenu.getMenuInflater();
        inflater.inflate(R.menu.hadith_detail_popup_menu, hadithListPopupMenu.getMenu());
    }

    private void setHadithView(int hadithId) {
        hadithInView = DbManager.getInstance().getHadithInformationForHadith(hadithId);
        if (hadithInView == null) {
            return;
        }
        actionBar.setTitle(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getBookName()));
        actionBar.setSubtitle(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getSectionBengali()));
        imageViewTickHadith.setVisibility(hadithInView.getCheckStatus() == 1 ? View.VISIBLE : View.INVISIBLE);
        textViewHadithNumber.setBanglaText(getString(R.string.hadith_number) + " " + Utils.translateNumber(hadithId));
        textViewChapter.setText(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getChapterBengali()));
        textViewHadith.setText(UtilBanglaSupport.getBanglaSpannableString(hadithInView.getHadithBengali()));
        String note = hadithInView.getNote();
        if (note == null || note.isEmpty() || note.equals("NULL")) {
            footnoteView.setVisibility(View.GONE);
        } else {
            footnoteView.setVisibility(View.VISIBLE);
            textViewFootnote.setBanglaText(hadithInView.getNote());
        }
        textViewHadithStatus.setBanglaText(hadithInView.getStatusBengali());
        textViewRabiName.setBanglaText(hadithInView.getRabiBengali());
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

        imageViewTickHadith = (ImageView) findViewById(R.id.tick_hadith);
        textViewChapter = (TextView) findViewById(R.id.textView_chapter_name);
        textViewHadith = (TextView) findViewById(R.id.textView_hadith);
        textViewHadithNumber = (BanglaTextView) findViewById(R.id.textView_hadith_number);
        textViewFootnote = (BanglaTextView) findViewById(R.id.textView_footnote);
        footnoteView = findViewById(R.id.footnote_view);
        textViewHadithStatus = (BanglaTextView) findViewById(R.id.textView_hadith_status);
        textViewRabiName = (BanglaTextView) findViewById(R.id.textView_rabi_name);
        imageButtonNavBack = (ImageButton) findViewById(R.id.nav_back);
        imageButtonNavForward = (ImageButton) findViewById(R.id.nav_forward);

        currentHadithIdPos = 0;
        setHadithView(hadithIdList.get(currentHadithIdPos));
        imageButtonNavBack.setEnabled(false);
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
                hadithListPopupMenu.show();
                break;
            case R.id.btn_share_via_email:
                createShareViaEmailIntent();
                break;
            case R.id.btn_report:
                createReportViaEmailIntent();
                break;
            case R.id.nav_back:
                --currentHadithIdPos;
                setHadithView(hadithIdList.get(currentHadithIdPos));
                handleNavButtons();
                break;
            case R.id.nav_forward:
                ++currentHadithIdPos;
                setHadithView(hadithIdList.get(currentHadithIdPos));
                handleNavButtons();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int hadithId = menuItem.getItemId();
        currentHadithIdPos = hadithIdList.indexOf(hadithId);
        handleNavButtons();
        setHadithView(hadithId);
        return true;
    }
}
