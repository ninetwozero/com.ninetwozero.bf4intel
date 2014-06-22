package com.ninetwozero.bf4intel.base.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.bugsense.trace.BugSenseHandler;
import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.resources.Keys;

import java.util.HashMap;

public abstract class BaseIntelActivity extends ActionBarActivity {
    private static final String BUGSENSE_TOKEN = "f42265ac";

    protected Menu optionsMenu;
    protected SharedPreferences sharedPreferences;
    private Toast toast;

    private boolean sw600dp;
    private boolean sw720dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BugSenseHandler.I_WANT_TO_DEBUG = BuildConfig.isDebug;
        if (!BugSenseHandler.I_WANT_TO_DEBUG) {
            BugSenseHandler.initAndStartSession(this, BUGSENSE_TOKEN);
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sw600dp = getResources().getBoolean(R.bool.is_sw600dp);
        sw720dp = getResources().getBoolean(R.bool.is_sw720dp);

        reloadSession();
    }

    @Override
    protected void onDestroy() {
        BugSenseHandler.closeSession(this);
        super.onDestroy();
    }

    protected boolean isSw600dp() {
        return sw600dp;
    }

    protected boolean isSw720dp() {
        return sw720dp;
    }

    public void showToast(final String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToast(final int stringResource) {
        showToast(getString(stringResource));
    }

    private void reloadSession() {
        SessionStore.load(
            sharedPreferences.getString(Keys.SESSION_ID, null),
            sharedPreferences.getString(Keys.Profile.ID, null),
            sharedPreferences.getString(Keys.Profile.USERNAME, null),
            sharedPreferences.getString(Keys.Profile.GRAVATAR_HASH, null)
        );

        if (sharedPreferences.getBoolean(Keys.Settings.USER_IN_CRASH_REPORT, true)) {
            BugSenseHandler.addCrashExtraMap(fetchExtraInformationForBugsense());
        } else {
            BugSenseHandler.addCrashExtraMap(fetchDummyInformationForBugsense());
        }
    }

    private HashMap<String, String> fetchExtraInformationForBugsense() {
        final HashMap<String, String> map = new HashMap<String, String>();
        map.put("bl_userid", SessionStore.getUserId());
        map.put("bl_username", SessionStore.getUsername());
        map.put("bl_soldierid", String.valueOf(sharedPreferences.getLong(Keys.Menu.LATEST_PERSONA, 0)));
        return map;
    }

    private HashMap<String, String> fetchDummyInformationForBugsense() {
        final String notApplicable = getString(R.string.na);
        final HashMap<String, String> map = new HashMap<String, String>();
        map.put("bl_userid", notApplicable);
        map.put("bl_username", notApplicable);
        map.put("bl_soldierid", notApplicable);
        return map;
    }
}
