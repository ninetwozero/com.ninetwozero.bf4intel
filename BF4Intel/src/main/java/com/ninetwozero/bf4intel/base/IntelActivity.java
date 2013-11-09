package com.ninetwozero.bf4intel.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.utils.Result;

public class IntelActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Result> {

    protected Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
    }

    @Override
    public Loader<Result> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Result> resultLoader, Result result) {

    }

    @Override
    public void onLoaderReset(Loader<Result> resultLoader) {

    }

    public JsonObject extractFromJson(String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject().getAsJsonObject("data");
    }

    private void showProgressBar() {
        //show progress bar
    }

    private void hideProgressBar() {
        //hide progress bar
    }
}
