package com.hadithbd.banglahadith.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hadithbd.banglahadith.BanglaHadithApp;
import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.bangla.AndroidCustomFontSupport;
import com.hadithbd.banglahadith.util.Utils;

/**
 * Created by Iftekhar on 2/25/2015.
 */
public class ArabicTextView extends TextView {

    public static final boolean IS_ARABIC_AVAILABLE = Utils.isLocaleAvailable("arabic");

    private TypedArray typedArray;
    private String arabicText;

    public ArabicTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public ArabicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ArabicTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public void setArabicText(String banglaText) {
        setArabicSupportedText(banglaText);
        if (typedArray != null) {
            typedArray.recycle();
        }
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        if (attrs != null) {
            try {
                typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ArabicTextView);
                arabicText = typedArray.getString(R.styleable.ArabicTextView_arabicText);

            } catch (Exception e) {
                e.printStackTrace();
            }

            setUpTypeFace();

            setArabicSupportedText(arabicText);
            typedArray.recycle();
        }
    }

    private void setUpTypeFace() {
        if (isArabicAvailableAndAboveHoneyComb()) {
            setTypeface(BanglaHadithApp.typefaceArabic);
        }
    }

    private boolean isArabicAvailableAndAboveHoneyComb() {
        return IS_ARABIC_AVAILABLE & Utils.IS_BUILD_ABOVE_HONEYCOMB;
    }

    private void setArabicSupportedText(String arabicText) {
        if (arabicText != null) {
            setText(Utils.IS_BUILD_ABOVE_HONEYCOMB ? arabicText :
                    AndroidCustomFontSupport.getCorrectedBengaliFormat(arabicText, BanglaHadithApp.typefaceArabic, -1));
        }
    }
}
