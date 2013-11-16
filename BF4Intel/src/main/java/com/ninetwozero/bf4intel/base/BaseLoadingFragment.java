package com.ninetwozero.bf4intel.base;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.utils.Result;

/*
    TODO: Handle ActionBar from this class too (provide methods)
 */

public abstract class BaseLoadingFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Result> {
    private Gson gson = new Gson();
    private JsonParser parser = new JsonParser();

    @Override
    public void onLoaderReset(final Loader<Result> resultLoader) {}

    protected <T> T fromJson(final String jsonString, final Class<T> outClass) {
        final JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject().getAsJsonObject("data");
        return gson.fromJson(jsonObject, outClass);
    }

    protected abstract void onLoadSuccess(final String resultMessage);
    protected abstract void onLoadFailure(final String resultMessage);
}
