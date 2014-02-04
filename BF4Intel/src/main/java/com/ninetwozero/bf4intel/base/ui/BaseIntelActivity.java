package com.ninetwozero.bf4intel.base.ui;

import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

public abstract class BaseIntelActivity extends FragmentActivity {
    protected Menu optionsMenu;
    private Toast toast;

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
}
