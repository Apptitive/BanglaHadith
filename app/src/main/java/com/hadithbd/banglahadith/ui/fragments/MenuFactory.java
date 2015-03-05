package com.hadithbd.banglahadith.ui.fragments;

import android.support.v4.app.Fragment;

import com.hadithbd.banglahadith.R;

/**
 * Created by Sharifur on 3/2/2015.
 */
public class MenuFactory {
    public static Fragment createMenuScreen(int menuId) {

        Fragment fragment = null;

        if (menuId == R.id.action_settings) {
            fragment = new AboutUsFragment();
        } else if (menuId == R.id.action_about_us) {
            fragment = new AboutUsFragment();
        } else if (menuId == R.id.action_favorite) {
            fragment = new AboutUsFragment();
        } else if (menuId == R.id.action_data_sync) {
            fragment = new AboutUsFragment();
        } else if (menuId == R.id.action_donation) {
            fragment = new DonationFragment();
        } else if (menuId == R.id.action_latest_update) {
            fragment = new LatestUpdate();
        }else if(menuId == R.id.action_search){
            fragment = new SearchFragment();
        }
        return fragment;
    }
}