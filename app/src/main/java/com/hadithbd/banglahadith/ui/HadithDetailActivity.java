package com.hadithbd.banglahadith.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hadithbd.banglahadith.R;

import java.util.HashMap;

public class HadithDetailActivity extends BaseActivity implements View.OnClickListener {

    private HashMap<Integer, View> tabToMarkerMap;
    private View currentVisibleTabMarker;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.black_20));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initTabMarkers();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            switchTabMarkerVisibilty(v.getId());
        }
    }
}
