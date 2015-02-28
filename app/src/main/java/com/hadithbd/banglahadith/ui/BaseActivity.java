package com.hadithbd.banglahadith.ui;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hadithbd.banglahadith.R;

/**
 * Created by Sharif on 2/28/2015.
 */
public class BaseActivity extends ActionBarActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                Log.e("TAG", "menu item is selected");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
