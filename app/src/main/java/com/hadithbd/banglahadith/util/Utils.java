package com.hadithbd.banglahadith.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Sharif on 2/22/2015.
 */
public class Utils {

    private static final HashMap<Character, Character> digitsMap = new HashMap<>();

    static {
        digitsMap.put('0', '০');
        digitsMap.put('1', '১');
        digitsMap.put('2', '২');
        digitsMap.put('3', '৩');
        digitsMap.put('4', '৪');
        digitsMap.put('5', '৫');
        digitsMap.put('6', '৬');
        digitsMap.put('7', '৭');
        digitsMap.put('8', '৮');
        digitsMap.put('9', '৯');
    }

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

    public static String translateNumber(long count) {
        char[] digits = (String.valueOf(count)).toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char digit : digits) {
            sb.append(digitsMap.get(digit));
        }
        return sb.toString();
    }
}
