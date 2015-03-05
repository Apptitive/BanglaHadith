package com.hadithbd.banglahadith.bangla;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Sharif on 2/22/2015.
 */
public class AndroidCustomFontSupport {

    public static SpannableString getStringRepresentationInCustomFont(Context context, String text, Typeface customFont, float size) {
        return UtilBanglaSupport.getSpannableWithFont(text, customFont, size);
    }

    /**
     * Get a spannable string with proper bangla font encoded in UTF-8.
     * The spannable string can be set for any view elements which has
     * setTitle(String),setTitle(String) or any other method that takes
     * a string to display.
     *
     * @param text The non-unicode bangla text
     * @param size The font size; this wont have much effect if the view has
     *             a fixed text size.(e.g.textView.setTextSize);give a value of -1 if you
     *             don't need to change font size and wish to use the default.
     * @return
     */
    public static SpannableString getCorrectedBengaliFormat(String text, Typeface font, float size) {
        SpannableString spannable = null;
        boolean banglaSupported = false;
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            if (locale.getDisplayName().toLowerCase().contains("bengali")) {
                banglaSupported = true;
                break;
            }
        }
        spannable = UtilBanglaSupport.getBanglaSpannableWithSize(text, font, size, banglaSupported);
        return spannable;
    }

    /**
     * This method is same as the old bls native library.Provided just to ensure backward
     * compatibility. Recommended to use getBengaliUTFWithFontSize.
     *
     * @param text The non-unicode bangla text
     * @param view The view which has some text attributes to be displayed.
     *             (e.g.Button,TextView,EditText)
     */
    @Deprecated
    public static void getBengaliUTF(String text, Typeface font, View view) {
        SpannableString string = UtilBanglaSupport.getBanglaSpannableWithSize(text, font, -1, false);
        if (view instanceof TextView) {
            ((TextView) view).setText(string);
        } else if (view instanceof Button) {
            ((Button) view).setText(string);
        } else if (view instanceof EditText) {
            ((EditText) view).setText(string);
        }
    }
}