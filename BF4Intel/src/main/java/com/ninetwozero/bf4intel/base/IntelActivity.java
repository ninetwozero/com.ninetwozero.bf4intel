package com.ninetwozero.bf4intel.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.utils.Result;

public class IntelActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Result> {

    protected Gson gson;
    private Menu optionsMenu;

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

    /*@Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem refresh = (MenuItem)menu.findItem(R.id.assignment_completion);
        return true;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        optionsMenu = menu;
        getMenuInflater().inflate(R.menu.intel_activity, menu);
        return true;
    }

    protected void setLoadingState(boolean isLoading) {
        if(optionsMenu == null) {
            return;
        }

        final MenuItem refreshItem = optionsMenu.findItem(R.id.menu_refresh);
        if(refreshItem != null) {
            if(isLoading) {
                //refreshItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
            } else {
                //refreshItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                refreshItem.setActionView(null);
            }
        }
    }
}
