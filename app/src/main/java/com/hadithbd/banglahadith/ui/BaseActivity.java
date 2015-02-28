package com.hadithbd.banglahadith.ui;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.util.Utils;

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
            case R.id.action_latest_update:
                openLatestUpdateActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void openLatestUpdateActivity() {

        final boolean isInternetConnectionAvailable = Utils.isNetworkAvailable(this);

        if (!isInternetConnectionAvailable) {
            Toast.makeText(this, getString(R.string.text_internet_connection_failed),
                    Toast.LENGTH_SHORT).show();
        }

        if (isInternetConnectionAvailable) {

        }

    }


}
