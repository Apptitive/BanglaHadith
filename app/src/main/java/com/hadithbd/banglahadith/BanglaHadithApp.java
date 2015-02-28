package com.hadithbd.banglahadith;

import android.app.Application;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.hadithbd.banglahadith.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sharif on 2/5/2015.
 */
public class BanglaHadithApp extends Application {

    public static final String TAG = "VolleyTag";

    public static Typeface typefaceBangla;

    public static Typeface typefaceArabic;

    private RequestQueue mRequestQueue;

    private static BanglaHadithApp mInstance;

    public static final List<Integer> itemStripColors = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        typefaceBangla = Typeface.createFromAsset(getAssets(),
                Constants.FONT_FOLDER_LOCATION + Constants.FONT_NAME_BANGLA);
        typefaceArabic = Typeface.createFromAsset(getAssets(),
                Constants.FONT_FOLDER_LOCATION + Constants.FONT_NAME_ARABIC);
        mInstance = this;
        preLoadListStripColors();
    }

    private void preLoadListStripColors() {
        TypedArray ta = getResources().obtainTypedArray(R.array.hadith_item_strip_colors);

        for (int i = 0; i < ta.length(); i++) {
            itemStripColors.add(ta.getColor(i, 0));
        }

        ta.recycle();
    }

    public static synchronized BanglaHadithApp getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding to request queue %s", request.getUrl());

        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequest(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
