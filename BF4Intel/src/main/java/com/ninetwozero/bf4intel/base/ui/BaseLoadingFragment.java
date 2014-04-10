package com.ninetwozero.bf4intel.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.NumberFormatter;
import com.squareup.otto.Subscribe;

public abstract class BaseLoadingFragment extends BaseFragment implements Response.ErrorListener {
    protected final Gson gson = GsonProvider.getInstance();
    protected final JsonParser parser = new JsonParser();

    protected boolean isReloading;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        startLoadingData();
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onErrorResponse(final VolleyError error) {
        Log.w(getClass().getSimpleName(), "[onLoadFailure] " + error.getMessage());
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        startLoadingData();
    }

    protected <T extends Object> T fromJson(final String jsonString, final Class<T> outClass) {
        final JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject().getAsJsonObject("data");
        return gson.fromJson(jsonObject, outClass);
    }

    protected void showLoadingState(final boolean isLoading) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        toggleFullScreenProgressBar(activity, isLoading);
        if (activity instanceof BaseLoadingIntelActivity) {
            ((BaseLoadingIntelActivity) activity).showLoadingStateInActionBar(isLoading);
        }
    }

    private void toggleFullScreenProgressBar(final Activity activity, final boolean isLoading) {
        if (activity == null) {
            return;
        }

        final View loadingView = getView().findViewById(R.id.wrap_loading_progress);
        if (loadingView == null) {
            return;
        }
        loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    protected String format(double value) {
        return NumberFormatter.format(value);
    }

    protected String format(long value) {
        return NumberFormatter.format(value);
    }

    protected abstract void startLoadingData();
}
