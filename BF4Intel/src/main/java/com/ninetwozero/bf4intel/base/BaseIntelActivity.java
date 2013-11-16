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

public class BaseIntelActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Result> {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        optionsMenu = menu;
        getMenuInflater().inflate(R.menu.intel_activity, menu);
        return true;
    }

    protected void hideABProgressBar(boolean hideProgressBar) {
        if (optionsMenu == null) {
            return;
        }

        final MenuItem refreshItem = optionsMenu.findItem(R.id.indeterminated_progress);
        if (refreshItem != null && hideProgressBar) {
            refreshItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        }
    }
}
