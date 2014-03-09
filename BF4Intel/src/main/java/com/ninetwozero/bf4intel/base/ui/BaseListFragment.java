package com.ninetwozero.bf4intel.base.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ninetwozero.bf4intel.ui.fragments.NavigationDrawerFragment;
import com.ninetwozero.bf4intel.utils.GoogleAnalytics;

import java.util.Locale;

public abstract class BaseListFragment extends ListFragment {
    private static Toast toast;

    protected FragmentManager fragmentManager;
    protected LayoutInflater layoutInflater;
    protected SharedPreferences sharedPreferences;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setRetainInstance(true);
        fragmentManager = getFragmentManager();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    @Override
    public void onStart() {
        super.onStart();


        final Bundle bundle = getArguments() == null ? new Bundle() : getArguments();
        if (!bundle.getBoolean(BaseFragment.DISABLE_AUTO_ANALYTICS, false)) {
            postGoogleAnalytics();
        }
    }

    protected void postGoogleAnalytics() {
        GoogleAnalytics.post(getActivity(), this.getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentManager = getFragmentManager();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        this.layoutInflater = inflater;
        return null;
    }

    protected void updateActionBar(final Activity activity, final String text) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(text.toUpperCase(Locale.getDefault()));
    }

    protected void updateActionBar(final Activity activity, final int resource) {
        if( activity == null ) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(getString(resource).toUpperCase(Locale.getDefault()));
    }

    protected void updateActionBar(final Activity activity, final String text, final int icon) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(text.toUpperCase(Locale.getDefault()));
        actionBar.setIcon(icon);
    }

    protected void updateActionBar(final Activity activity, final int text, final int icon) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(getString(text).toUpperCase(Locale.getDefault()));
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


    protected void setText(final View view, final int resourceId, final int textResource) {
        ((TextView) view.findViewById(resourceId)).setText(textResource);
    }

    protected void setText(final View view, final int resourceId, final String text) {
        ((TextView) view.findViewById(resourceId)).setText(text);
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
