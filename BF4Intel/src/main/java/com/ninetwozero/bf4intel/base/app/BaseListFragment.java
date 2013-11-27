package com.ninetwozero.bf4intel.base.app;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class BaseListFragment extends ListFragment {
    private static Toast toast;

    protected FragmentManager fragmentManager;
    protected LayoutInflater layoutInflater;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setRetainInstance(true);
        fragmentManager = getFragmentManager();
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        this.layoutInflater = inflater;
        return new View(getActivity());
    }

    protected void updateActionBar(final Activity activity, final String text) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(text.toUpperCase());
    }

    protected void updateActionBar(final Activity activity, final int resource) {
        if( activity == null ) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(getString(resource).toUpperCase());
    }

    protected void updateActionBar(final Activity activity, final String text, final int icon) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(text.toUpperCase());
        actionBar.setIcon(icon);
    }

    protected void updateActionBar(final Activity activity, final int text, final int icon) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(getString(text).toUpperCase());
        actionBar.setIcon(icon);
    }

    protected void showToast(final int resource) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        doShowToast(activity, activity.getString(resource));
    }

    protected void showToast(final String text) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        doShowToast(activity, text);
    }

    private void doShowToast(final Activity activity, final String text) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }

        toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
