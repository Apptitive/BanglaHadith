package com.hadithbd.banglahadith.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hadithbd.banglahadith.BanglaHadithApp;
import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.bangla.AndroidCustomFontSupport;

import java.util.Locale;

/**
 * Created by Sharif on 2/22/2015.
 */
public class BanglaTextView extends TextView {

    public static final boolean IS_BANGLA_AVAILABLE = isBanglaAvailable();

    public static final boolean IS_BUILD_ABOVE_HONEYCOMB = Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR2;

    private TypedArray typedArray;
    private String banglaText;

    public BanglaTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public BanglaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BanglaTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void setBanglaText(String banglaText) {
        setBanglaSupportedText(banglaText);
        if (typedArray != null) {
            typedArray.recycle();
        }
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        if (attrs != null) {
            try {
                typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BanglaTextView);
                banglaText = typedArray.getString(R.styleable.BanglaTextView_banglaText);

            } catch (Exception e) {
                e.printStackTrace();
            }

            setUpTypeFace();

            setBanglaSupportedText(banglaText);
            typedArray.recycle();
        }
    }

    private void setUpTypeFace() {
        if (isBanglaAvailableAndAboveHoneyComb()) {
            setTypeface(BanglaHadithApp.banglaHadithFont);
        }

    }

    private boolean isBanglaAvailableAndAboveHoneyComb() {
        return IS_BANGLA_AVAILABLE & !IS_BUILD_ABOVE_HONEYCOMB;
    }

    private void setBanglaSupportedText(String banglaText) {
        if (banglaText != null) {
            setText(IS_BUILD_ABOVE_HONEYCOMB ? banglaText :
                    AndroidCustomFontSupport.getCorrectedBengaliFormat(banglaText, BanglaHadithApp.banglaHadithFont, -1));
        }
    }



    private static boolean isBanglaAvailable() {
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            if (locale.getDisplayName().toLowerCase().contains("bengali")) {
                return true;
            }
        }
        return false;
    }

}