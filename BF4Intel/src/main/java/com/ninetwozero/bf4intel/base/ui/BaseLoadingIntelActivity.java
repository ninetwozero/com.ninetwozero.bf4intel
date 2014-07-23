package com.ninetwozero.bf4intel.base.ui;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.ui.animations.SimpleFadeInAnimation;
import com.ninetwozero.bf4intel.ui.animations.SimpleFadeOutAnimation;

public abstract class BaseLoadingIntelActivity extends BaseIntelActivity implements Response.ErrorListener {
    protected final Gson gson = GsonProvider.getInstance();

    private TextView errorMessageView;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        errorMessageView = null;
    }


    @Override
    public void onErrorResponse(final VolleyError error) {
        Log.w(getClass().getSimpleName(), "[" + error.getClass().getSimpleName() + " in onLoadFailure] " + error.getMessage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.base_intel_activity, menu);
        optionsMenu = menu;
        return true;
    }

    public JsonObject extractFromJson(String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject().getAsJsonObject("data");
    }

    protected void setupErrorMessage() {
        errorMessageView = (TextView) findViewById(R.id.inline_error_message);
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
    }

    protected void showErrorMessage(final int textResource) {
        showErrorMessage(getString(textResource));
    }

    protected void clearErrorMessage() {
        errorMessageView.startAnimation(new SimpleFadeOutAnimation(errorMessageView));
    }
}
