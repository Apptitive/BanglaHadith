package com.hadithbd.banglahadith.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.hadithbd.banglahadith.R;
import com.hadithbd.banglahadith.util.Constants;
import com.hadithbd.banglahadith.util.Utils;

/**
 * Created by U on 3/2/2015.
 */
public class LatestUpdate extends Fragment {

    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!Utils.isNetworkAvailable(getActivity())) {
            Toast.makeText(getActivity(), getString(R.string.text_internet_connection_failed),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest_update, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        mWebView = (WebView) view.findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(Constants.LATEST_UPDATE_URL);
    }
}
