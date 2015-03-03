package com.hadithbd.banglahadith.ui;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.util.Utils;

import java.lang.reflect.Field;

/**
 * Created by Sharif on 2/28/2015.
 */
public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOverflowMenu();
    }

    public void setHomeBackground() {
        BitmapDrawable whiteLayer = (BitmapDrawable) getResources().getDrawable(R.drawable.home_white_layer);
        BitmapDrawable iconCaliography = (BitmapDrawable) getResources().getDrawable(R.drawable.bg_caliography);
        iconCaliography.setGravity(Gravity.BOTTOM);
        BitmapDrawable blueLayer = (BitmapDrawable) getResources().getDrawable(R.drawable.home_blue_layer);
        Drawable[] drawables = new Drawable[]{blueLayer, iconCaliography, whiteLayer};
        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        setLayerToBackground(layerDrawable);
    }

    private void getOverflowMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setLayerToBackground(LayerDrawable layerDrawable) {
        View view = findViewById(android.R.id.content);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(layerDrawable);
        } else {
            view.setBackground(layerDrawable);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != android.R.id.home) {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra(Constants.MENU_ITEM_ID, item.getItemId());
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

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
