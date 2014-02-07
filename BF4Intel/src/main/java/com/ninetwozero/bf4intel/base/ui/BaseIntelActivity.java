package com.ninetwozero.bf4intel.base.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.resources.Keys;

public abstract class BaseIntelActivity extends FragmentActivity {
    protected Menu optionsMenu;
    protected SharedPreferences sharedPreferences;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        reloadSession();
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
            sharedPreferences.getString(Keys.SESSION_ID, ""),
            sharedPreferences.getString(Keys.Profile.ID, ""),
            sharedPreferences.getString(Keys.Profile.USERNAME, "")
        );
    }
}
