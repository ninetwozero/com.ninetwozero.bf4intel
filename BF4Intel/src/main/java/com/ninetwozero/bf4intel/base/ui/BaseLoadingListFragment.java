package com.ninetwozero.bf4intel.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseLoadingListFragment extends BaseListFragment implements LoaderManager.LoaderCallbacks<Result> {
    private Gson gson = new Gson();
    private JsonParser parser = new JsonParser();

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
    }


    @Override
    public void onLoadFinished(final Loader<Result> resultLoader, final Result result) {
        if (result == Result.SUCCESS) {
            onLoadSuccess(result.getResultMessage());
        } else {
            onLoadFailure(result.getResultMessage());
        }
    }

    @Override
    public void onLoaderReset(final Loader<Result> resultLoader) {}

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

    protected <T extends Object> T fromJson(final String json, final Class<T> outClass) {
        final JsonObject jsonObject = parser.parse(json).getAsJsonObject().getAsJsonObject("data");
        return gson.fromJson(jsonObject, outClass);
    }

    protected <T extends Object> List<T> fromJsonArray(final Gson gsonToUse, final String json, final Class<T> outClass, final String container) {
        final List<T> objects = new ArrayList<T>();
        final JsonObject jsonObject = parser.parse(json).getAsJsonObject().getAsJsonObject("data");
        if (jsonObject.has(container)) {
            final JsonArray elements = jsonObject.getAsJsonArray(container);
            for (JsonElement element : elements) {
                objects.add(gsonToUse.fromJson(element, outClass));
            }
        }
        return objects;
    }

    protected <T extends Object> List<T> fromJsonArray(final String json, final Class<T> outClass, final String container) {
        return fromJsonArray(gson, json, outClass, container);
    }

    protected void displayAsLoading(final boolean isLoading) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        toggleFullScreenProgressBar(activity, isLoading);
        ((BaseIntelActivity) activity).showLoadingStateInActionBar(isLoading);
    }

    private void toggleFullScreenProgressBar(final Activity activity, final boolean isLoading) {
        activity.findViewById(R.id.wrap_loading_progress).setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
    protected abstract void onLoadSuccess(final String resultMessage);
    protected abstract void onLoadFailure(final String resultMessage);
    protected abstract void startLoadingData();
}
