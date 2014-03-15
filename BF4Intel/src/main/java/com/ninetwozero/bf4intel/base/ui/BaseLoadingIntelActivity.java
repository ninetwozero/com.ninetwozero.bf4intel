package com.ninetwozero.bf4intel.base.ui;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

public abstract class BaseLoadingIntelActivity extends BaseIntelActivity implements Response.ErrorListener {
    protected final Gson gson = GsonProvider.getInstance();
    protected final RequestQueue requestQueue = Volley.newRequestQueue(this);

    @Override
    public void onErrorResponse(final VolleyError error) {
        Log.w(getClass().getSimpleName(), "[onLoadFailure] " + error.getMessage());
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

    public JsonObject extractFromJson(String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject().getAsJsonObject("data");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.base_intel_activity, menu);
        optionsMenu = menu;
        return true;
    }

    protected void showLoadingStateInActionBar(boolean isLoading) {
        if (optionsMenu == null) {
            return;
        }

        final MenuItem refreshItem = optionsMenu.findItem(R.id.ab_action_refresh);
        if (refreshItem != null) {
            refreshItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            if (isLoading) {
                refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
            } else {
                refreshItem.setActionView(null);
            }
        }
    }
}
