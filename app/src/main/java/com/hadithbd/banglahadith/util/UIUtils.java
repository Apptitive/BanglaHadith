package com.hadithbd.banglahadith.util;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hadithbd.banglahadith.bangla.UtilBanglaSupport;

/**
 * Created by Sharif on 3/5/2015.
 */
public class UIUtils {

    public static void setRadioButtonTexts(RadioGroup radioGroup, String buttonTextOne, String buttonTextTwo) {
        String[] radioButtonTexts = new String[]{buttonTextOne, buttonTextTwo};
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton) radioGroup.getChildAt(i))
                    .setText(UtilBanglaSupport.getBanglaSpannableString(radioButtonTexts[i]));
        }
    }
}
