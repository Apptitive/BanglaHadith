package com.hadithbd.banglahadith.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hadithbd.banglahadith.R;

import java.util.HashMap;

public class HadithDetailActivity extends ActionBarActivity implements View.OnClickListener {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hadith_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            switchTabMarkerVisibilty(v.getId());
        }
    }
}
