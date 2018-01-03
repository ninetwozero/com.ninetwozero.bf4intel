package com.ninetwozero.bf4intel.base.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.resources.Keys;
import com.splunk.mint.Mint;

import java.util.HashMap;

public abstract class BaseIntelActivity extends AppCompatActivity {
    private static final String BUGSENSE_TOKEN = "f42265ac";

    protected Menu optionsMenu;
    protected SharedPreferences sharedPreferences;
    private Toast toast;

    private boolean sw600dp;
    private boolean sw720dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!BuildConfig.isDebug) {
            Mint.initAndStartSession(this, BUGSENSE_TOKEN);
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sw600dp = getResources().getBoolean(R.bool.is_sw600dp);
        sw720dp = getResources().getBoolean(R.bool.is_sw720dp);

        reloadSession();
    }

    @Override
    protected void onDestroy() {
        Mint.closeSession(this);
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
        if (sharedPreferences.getBoolean(Keys.Settings.USER_IN_CRASH_REPORT, true)) {
            setExtraInformationForBugsense();
        } else {
            setDummyInformationForBugsense();
        }
    }

    private void setExtraInformationForBugsense() {
        Mint.addExtraData(Keys.Splunk.SOLDIER, sharedPreferences.getString(Keys.Menu.LATEST_PERSONA, ""));
        Mint.addExtraData(Keys.Splunk.PLATFORM, String.valueOf(sharedPreferences.getInt(Keys.Menu.LATEST_PERSONA_PLATFORM, 0)));
    }

    private void setDummyInformationForBugsense() {
        final String notApplicable = getString(R.string.na);
        Mint.addExtraData(Keys.Splunk.SOLDIER, notApplicable);
        Mint.addExtraData(Keys.Splunk.PLATFORM, notApplicable);
    }
}
