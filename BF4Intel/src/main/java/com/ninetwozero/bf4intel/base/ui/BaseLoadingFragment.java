package com.ninetwozero.bf4intel.base.ui;

import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.ui.animations.SimpleFadeInAnimation;
import com.ninetwozero.bf4intel.ui.animations.SimpleFadeOutAnimation;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

public abstract class BaseLoadingFragment extends BaseFragment implements Response.ErrorListener {
    protected final Gson gson = GsonProvider.getInstance();
    protected final JsonParser parser = new JsonParser();
    protected String[] filterTitleResources;
    protected String[] sortingKeys;
    protected String[] sortTitleResources;

    protected boolean isReloading;

    private TextView errorMessageView;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);

        final ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(
                Bf4Intel.isConnectedToNetwork() ? null : getString(R.string.label_offline_mode)
            );
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        errorMessageView = null;
    }

    @Override
    public void onErrorResponse(final VolleyError error) {
        Log.w(getClass().getSimpleName(), "[" + error.getClass().getSimpleName() + " in onLoadFailure] " + error.getMessage());
        if (getView() != null) {
            final String message = error.getMessage();
            showErrorMessage(message == null ? error.getClass().getSimpleName() : message);
        }
    }

    protected void onRefreshEventReceived(RefreshEvent event) {
        if (!Bf4Intel.isConnectedToNetwork()) {
            showToast(R.string.msg_no_network);
            return;
        }
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
    }

    private void toggleFullScreenProgressBar(final Activity activity, final boolean isLoading) {
        final View view = getView();
        if (activity == null || view == null) {
            return;
        }

        final View loadingView = view.findViewById(R.id.wrap_loading_progress);
        if (loadingView == null) {
            return;
        }
        loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    protected void setupErrorMessage(final View view) {
        errorMessageView = (TextView) view.findViewById(R.id.inline_error_message);
        if (errorMessageView == null) {
            throw new IllegalStateException("Missing TextView with ID: @id/inline_error_message");
        }

        errorMessageView.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearErrorMessage();
                }
            }
        );
    }

    protected void showErrorMessage(final String textResource) {
        errorMessageView.setText(textResource);
        errorMessageView.startAnimation(new SimpleFadeInAnimation(errorMessageView));
        errorMessageView.setVisibility(View.VISIBLE);
    }

    protected void showErrorMessage(final int textResource) {
        showErrorMessage(getString(textResource));
    }

    protected void clearErrorMessage() {
        errorMessageView.startAnimation(new SimpleFadeOutAnimation(errorMessageView));
    }

    protected String format(double value) {
        return NumberFormatter.format(value);
    }

    protected String format(long value) {
        return NumberFormatter.format(value);
    }

    protected abstract void startLoadingData();
}
