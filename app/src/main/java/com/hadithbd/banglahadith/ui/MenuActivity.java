package com.hadithbd.banglahadith.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.ui.fragments.MenuFactory;
import com.hadithbd.banglahadith.util.Constants;

public class MenuActivity extends BaseActivity {

    public static final String TAG = MenuActivity.class.getSimpleName();

    private int mMenuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setHomeBackground();
        initMessageFromBundle();

        addMenuFragmentToThisLayout();
    }

    private void addMenuFragmentToThisLayout() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.menu_content, MenuFactory.createMenuScreen(mMenuId));
        fragmentTransaction.commit();
    }

    private void initMessageFromBundle() {
        Bundle data = getIntent().getExtras();
        if (data != null) {
            mMenuId = data.getInt(Constants.MENU_ITEM_ID);
            Log.e(TAG, "menu id: " + mMenuId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
