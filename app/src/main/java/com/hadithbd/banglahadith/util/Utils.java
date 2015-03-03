package com.hadithbd.banglahadith.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import java.util.Locale;

/**
 * Created by Sharif on 2/22/2015.
 */
public class Utils {

    public static final boolean IS_BUILD_ABOVE_HONEYCOMB = Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR2;

    public static boolean isLocaleAvailable(String localeName) {
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            if (locale.getDisplayName().toLowerCase().contains(localeName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check internet connection availability
     * */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectionManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectionManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static Spannable getColoredSpannableWithBanglaSupport(String text, int colorCode) {
        Spannable spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(colorCode), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }
}
