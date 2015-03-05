package com.hadithbd.banglahadith.bangla;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import com.hadithbd.banglahadith.BanglaHadithApp;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.util.Utils;

import androidbangladesh.bengali.support.BengaliUnicodeString;

/**
 * Created by Sharif on 2/22/2015.
 */
public class UtilBanglaSupport {

    public static String SUTONNI_MJ = "sutonni.TTF";

    public static String SOLAIMAN_LIPI = "solaimanlipinormal.ttf";

    public static String WEBVIEW_LOADED = "loaded";

    /**
     * Logs messages based on the class it gets sent from
     *
     * @param obj
     * @param msg
     */
    public static void log(Object obj, String msg) {
        Log.d(obj.getClass().getSimpleName(), msg);
    }

    public static SpannableString getSpannableWithFont(String text, Typeface font, float size) {
        SpannableString retVal = new SpannableString(text);
        TypefaceSpan span = new TypefaceSpan(font);
        retVal.setSpan(span, 0, retVal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (size != -1) {
            retVal.setSpan(new RelativeSizeSpan(size), 0, retVal.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return retVal;
    }

    public static SpannableString getBanglaSpannableString(String banglaText) {
        if (banglaText == null) {
            return new SpannableString("");
        }
        if (Utils.IS_BUILD_ABOVE_HONEYCOMB) {
            SpannableString spannableString = new SpannableString(banglaText);
            if (Utils.isLocaleAvailable(Constants.LOCALE_BENGALI)) {
                TypefaceSpan span = new TypefaceSpan(BanglaHadithApp.typefaceBangla);
                spannableString.setSpan(span, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            return spannableString;
        }
        return AndroidCustomFontSupport.getCorrectedBengaliFormat(banglaText, BanglaHadithApp.typefaceBangla, -1);
    }

    public static SpannableString getArabicSpannableString(String arabicText) {
        if (arabicText == null) {
            return new SpannableString("");
        }
        if (Utils.IS_BUILD_ABOVE_HONEYCOMB) {
            SpannableString spannableString = new SpannableString(arabicText);
            if (Utils.isLocaleAvailable(Constants.LOCALE_ARABIC)) {
                TypefaceSpan span = new TypefaceSpan(BanglaHadithApp.typefaceArabic);
                spannableString.setSpan(span, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            return spannableString;
        }
        return AndroidCustomFontSupport.getCorrectedBengaliFormat(arabicText, BanglaHadithApp.typefaceArabic, -1);
    }

    /**
     * Used to get bangla texts for UI components.
     *
     * @param text
     * @param size
     * @param banglaSupported TODO
     * @param fontName        TODO
     */
    public static SpannableString getBanglaSpannableWithSize(String text, Typeface font, float size, boolean banglaSupported) {
        SpannableString retVal = null;
        if (banglaSupported)//device supports bangla
        {
            log(UtilBanglaSupport.class, "Bangla Supported Device");
            retVal = new SpannableString(text);
        } else {
            //device doesn't support,need unicode.
            retVal = new SpannableString(BengaliUnicodeString.getBengaliUTF(text));
        }
        TypefaceSpan span = new TypefaceSpan(font);

        retVal.setSpan(span, 0, retVal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (size != -1) {
            retVal.setSpan(new RelativeSizeSpan(size), 0, retVal.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return retVal;
    }
}