package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.util.Constants;
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
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(Constants.MENU_ITEM_ID, item.getItemId());
        startActivity(intent);
        return super.onOptionsItemSelected(item);
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
