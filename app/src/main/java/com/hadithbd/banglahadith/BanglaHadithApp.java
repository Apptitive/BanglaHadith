package com.hadithbd.banglahadith;

import android.app.Application;
import android.graphics.Typeface;

import com.hadithbd.banglahadith.util.Constants;

/**
 * Created by Sharif on 2/5/2015.
 */
public class BanglaHadithApp extends Application {

    public static Typeface banglaHadithFont;

    @Override
    public void onCreate() {
        super.onCreate();

        banglaHadithFont = Typeface.createFromAsset(getAssets(),
                Constants.FONT_FOLDER_LOCATION + Constants.FONT_NAME);

    }
}
