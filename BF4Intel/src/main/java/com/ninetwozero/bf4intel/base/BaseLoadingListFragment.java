package com.ninetwozero.bf4intel.base;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

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

    protected <T extends Object> T fromJson(final String jsonString, final Class<T> outClass) {
        final JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject().getAsJsonObject("data");
        return gson.fromJson(jsonObject, outClass);
    }

    protected <T extends Object> List<T> fromJsonArray(final String jsonString, final Class<T> outClass, final String container) {
        final List<T> objects = new ArrayList<T>();
        final JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject().getAsJsonObject("data");
        if (jsonObject.has(container)) {
            final JsonArray elements = jsonObject.getAsJsonArray(container);
            for (JsonElement element : elements) {
                objects.add(gson.fromJson(element, outClass));
            }
        }
        return objects;
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
        final View loadingContainer = activity.findViewById(R.id.wrap_loading_progress);
        if (isLoading) {
            final ProgressBar progressBar = (ProgressBar) loadingContainer.findViewById(R.id.progress_bar);
            final Drawable progressDrawable = progressBar.getIndeterminateDrawable();
            if (progressDrawable != null) {
                progressDrawable.setColorFilter(R.color.blue, PorterDuff.Mode.MULTIPLY);
            }
        }
        loadingContainer.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
    protected abstract void onLoadSuccess(final String resultMessage);
    protected abstract void onLoadFailure(final String resultMessage);
    protected abstract void startLoadingData();
}
