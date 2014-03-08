package com.ninetwozero.bf4intel.base.ui;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

import com.bugsense.trace.BugSenseHandler;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.database.CupboardSQLiteOpenHelper;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.GoogleAnalytics;

public abstract class BaseIntelActivity extends FragmentActivity {
    private static final String BUGSENSE_TOKEN = "f42265ac";

    protected Menu optionsMenu;
    protected SharedPreferences sharedPreferences;
    private Toast toast;
    protected CupboardSQLiteOpenHelper cupboardHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BugSenseHandler.initAndStartSession(this, BUGSENSE_TOKEN);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        reloadSession();
    }

    @Override
    protected void onStart() {
        super.onStart();
        postGoogleAnalytics();
    }

    protected void postGoogleAnalytics() {
        GoogleAnalytics.post(this, this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        BugSenseHandler.closeSession(this);
        super.onDestroy();
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
    }

    public SQLiteDatabase getWritableDatabase() {
        if (cupboardHelper == null) {
            cupboardHelper = new CupboardSQLiteOpenHelper(this);
        }
        return cupboardHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase() {
        if (cupboardHelper == null) {
            cupboardHelper = new CupboardSQLiteOpenHelper(this);
        }
        return cupboardHelper.getReadableDatabase();
    }
}
