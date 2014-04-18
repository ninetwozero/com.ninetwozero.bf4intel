package com.ninetwozero.bf4intel.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.utils.BusProvider;

public abstract class BaseApiService extends Service implements Response.ErrorListener {
    public static final String SOLDIER_BUNDLE = "soldierBundle";

    protected int intentCount;
    protected SharedPreferences sharedPreferences;
    protected Bundle soldier;

    private final Gson gson = GsonProvider.getInstance();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        soldier = intent.getBundleExtra(SOLDIER_BUNDLE);
        intentCount++;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        BusProvider.getInstance().post(error);
    }

    protected <T> T fromJson(final String json, final Class<T> outClass) {
        final JsonObject jsonObject = extractFromJson(json, false);
        return gson.fromJson(jsonObject, outClass);
    }

    protected JsonObject extractFromJson(final String json, boolean returnTopLevel) {
        JsonParser parser = new JsonParser();
        JsonObject topLevel = parser.parse(json).getAsJsonObject();
        return returnTopLevel ? topLevel : topLevel.getAsJsonObject("data");
    }
}
