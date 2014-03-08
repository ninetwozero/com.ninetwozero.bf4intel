package com.ninetwozero.bf4intel.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;

public class CreditListFragment extends BaseFragment {

    public static CreditListFragment newInstance() {
        final CreditListFragment fragment = new CreditListFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public CreditListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = inflater.inflate(R.layout.generic_webview_card_fragment, parent, false);
        initialize(view);
        return view;
    }

    @Override
    protected void postGoogleAnalytics() {
    }

    private void initialize(View view) {
        final WebView webView = (WebView) view.findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/credits.htm");
    }
}
