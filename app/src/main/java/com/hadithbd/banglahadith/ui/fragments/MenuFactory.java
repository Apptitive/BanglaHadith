package com.hadithbd.banglahadith.ui.fragments;

import android.support.v4.app.Fragment;

import com.hadithbd.banglahadith.R;

/**
 * Created by U on 3/2/2015.
 */
public class MenuFactory {
    public static Fragment createMenuScreen(int menuId) {

        Fragment fragment = null;

        if (menuId == R.id.action_settings) {

        } else if (menuId == R.id.action_about_us) {
            fragment = new AboutUsFragment();
        } else if (menuId == R.id.action_favorite) {

        } else if (menuId == R.id.action_data_sync) {

        } else if (menuId == R.id.action_donation) {

        } else if (menuId == R.id.action_latest_update) {

        }
        return fragment;
    }
}