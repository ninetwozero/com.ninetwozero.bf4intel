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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.factories.GsonFactory;
import com.ninetwozero.bf4intel.utils.Result;

public abstract class BaseLoadingFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Result> {
    protected final Gson gson = GsonFactory.getInstance();
    protected final JsonParser parser = new JsonParser();

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
    }

    @Override
    public void onLoadFinished(Loader<Result> resultLoader, Result result) {
        if (result == Result.SUCCESS) {
            onLoadSuccess(resultLoader, result.getResultMessage());
        } else {
            onLoadFailure(resultLoader, result.getResultMessage());
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

    protected <T extends Object> T fromJson(final String jsonString, final Class<T> outClass) {
        final JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject().getAsJsonObject("data");
        return gson.fromJson(jsonObject, outClass);
    }

    @Deprecated
    protected void displayAsLoading(final boolean isLoading) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        activity.findViewById(R.id.wrap_loading_progress).setVisibility(isLoading ? View.VISIBLE : View.GONE);
        if (activity instanceof BaseLoadingIntelActivity) {
            ((BaseLoadingIntelActivity) activity).showLoadingStateInActionBar(isLoading);
        }
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
        final View loadingView = activity.findViewById(R.id.wrap_loading_progress);
        if (loadingView == null) {
            return;
        }
        loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }


    /*
        TODO:
        Deprecating current methods as they're used at many places
        However, when no more onLoadSuccess(String) nor onLoadFailure(String)
        implementations are left, we remove them below and make the

            onLoadX(Loader, String)

        methods abstract
     */

    @Deprecated
    protected void onLoadSuccess(final String resultMessage) {}

    @Deprecated
    protected void onLoadFailure(final String resultMessage) {}

    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        onLoadSuccess(resultMessage);
    }

    protected void onLoadFailure(final Loader loader, final String resultMessage) {
        onLoadFailure(resultMessage);
    }

    protected abstract void startLoadingData();
}
