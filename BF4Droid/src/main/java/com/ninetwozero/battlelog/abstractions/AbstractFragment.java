package com.ninetwozero.battlelog.abstractions;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class AbstractFragment extends Fragment {
    private static Toast mToast;

    protected FragmentManager mFragmentManager;
    protected LayoutInflater mInflater;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setRetainInstance(true);
        mFragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        mInflater = inflater;
        return null;
    }

    protected void updateActionBar(final Activity activity, final String text) {
        if( activity == null ) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(text);
    }

    protected void updateActionBar(final Activity activity, final String text, final int icon) {
        if( activity == null ) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(text);
        actionBar.setIcon(icon);
    }

    protected void showToast(final int resource) {
        final Activity activity = getActivity();
        if( activity == null ) {
            return;
        }
        doShowToast(activity, activity.getString(resource));
    }

    protected void showToast(final String text) {
        final Activity activity = getActivity();
        if( activity == null ) {
            return;
        }
        doShowToast(activity, text);
    }

    private void doShowToast(final Activity activity, final String text) {
        if( mToast != null ) {
            mToast.cancel();
            mToast = null;
        }

        mToast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
