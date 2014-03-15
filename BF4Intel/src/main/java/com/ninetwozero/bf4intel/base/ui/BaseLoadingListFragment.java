package com.ninetwozero.bf4intel.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.factories.GsonProvider;

public abstract class BaseLoadingListFragment extends BaseListFragment implements Response.ErrorListener {
    protected Gson gson = GsonProvider.getInstance();
    protected RequestQueue requestQueue;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        requestQueue = Volley.newRequestQueue(activity);
    }

    @Override
    public void onStop(){
        requestQueue.cancelAll(
            new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return request.getMethod() == Request.Method.GET;
                }
            }
        );
        super.onStop();
    }

    @Override
    public void onErrorResponse(final VolleyError error) {
        Log.w(getClass().getSimpleName(), "[onLoadFailure] " + error.getMessage());
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        final MenuItem menuItem = menu.findItem(R.id.ab_action_refresh);
        if ( menuItem != null ) {
            menuItem.setVisible(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.ab_action_refresh) {
            startLoadingData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected <T> T fromJson(final String json, final Class<T> outClass) {
        final JsonObject jsonObject = extractFromJson(json, false);
        return gson.fromJson(jsonObject, outClass);
    }

    protected <T> T fromJson(final String json, final Class<T> outClass, final boolean returnTopLevelJson) {
        final JsonObject jsonObject = extractFromJson(json, returnTopLevelJson);
        return gson.fromJson(jsonObject, outClass);
    }

    protected <T> T fromJson(final Gson gsonToUse, final String json, final Class<T> outClass) {
        final JsonObject jsonObject = extractFromJson(json, false);
        return gsonToUse.fromJson(jsonObject, outClass);
    }

    protected <T> T fromJson(final Gson gsonToUse, final String json, final Class<T> outClass, final boolean returnTopLevelJson) {
        final JsonObject jsonObject = extractFromJson(json, returnTopLevelJson);
        return gsonToUse.fromJson(jsonObject, outClass);
    }

    protected JsonObject extractFromJson(final String json, boolean returnTopLevel) {
        JsonParser parser = new JsonParser();
        JsonObject topLevel = parser.parse(json).getAsJsonObject();
        return returnTopLevel? topLevel : topLevel.getAsJsonObject("data");
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

    protected abstract void startLoadingData();
}
