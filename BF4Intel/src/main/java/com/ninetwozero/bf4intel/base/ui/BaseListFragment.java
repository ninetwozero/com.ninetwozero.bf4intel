package com.ninetwozero.bf4intel.base.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;
import com.ninetwozero.bf4intel.utils.GoogleAnalytics;

import java.util.Locale;

public abstract class BaseListFragment extends ListFragment {
    private static Toast toast;

    protected FragmentManager fragmentManager;
    protected LayoutInflater layoutInflater;
    protected SharedPreferences sharedPreferences;

    private boolean sw600dp;
    private boolean sw720dp;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setRetainInstance(!getArgumentsBundle().getBoolean(BaseFragment.FLAG_DISABLE_RETAIN_STATE, false));
        fragmentManager = getFragmentManager();
        sw600dp = getResources().getBoolean(R.bool.is_sw600dp);
        sw720dp = getResources().getBoolean(R.bool.is_sw720dp);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    @Override
    public void onStart() {
        super.onStart();

        final Bundle bundle = getArgumentsBundle();
        if (!bundle.getBoolean(BaseFragment.FLAG_DISABLE_AUTOMATIC_ANALYTICS, false)) {
            postGoogleAnalytics();
        }
    }

    protected boolean isSw600dp() {
        return sw600dp;
    }

    protected boolean isSw720dp() {
        return sw720dp;
    }

    protected Bundle getArgumentsBundle() {
        return getArguments() == null ? new Bundle() : getArguments();
    }

    protected void openDetailFragment(final FragmentFactory.Type fragmentType, final Bundle dataToPass, final String tag) {
        if (isSw720dp() || isSw600dp()) {
            final FragmentManager fragmentManager = getFragmentManager();
            DialogFragment fragment = (DialogFragment) fragmentManager.findFragmentByTag(tag);
            if (fragment == null) {
                fragment = (DialogFragment) FragmentFactory.get(fragmentType, dataToPass);
            }

            if (!fragment.isAdded()) {
                fragment.show(fragmentManager, tag);
            }
        } else {
            final Intent intent = new Intent(getActivity(), SingleFragmentActivity.class);
            intent.putExtra(SingleFragmentActivity.INTENT_FRAGMENT_TYPE, fragmentType.ordinal());
            intent.putExtra(SingleFragmentActivity.INTENT_FRAGMENT_DATA, dataToPass);
            startActivity(intent);
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

        final ActionBar actionBar = ((ActionBarActivity)activity).getSupportActionBar();
        actionBar.setTitle(text.toUpperCase(Locale.getDefault()));
    }

    protected void updateActionBar(final Activity activity, final int resource) {
        if( activity == null ) {
            return;
        }

        final ActionBar actionBar = ((ActionBarActivity) activity).getSupportActionBar();
        actionBar.setTitle(getString(resource));
    }

    protected void updateActionBar(final Activity activity, final String text, final int icon) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = ((ActionBarActivity) activity).getSupportActionBar();
        actionBar.setTitle(text.toUpperCase(Locale.getDefault()));
        actionBar.setIcon(icon);
    }

    protected void updateActionBar(final Activity activity, final int text, final int icon) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = ((ActionBarActivity) activity).getSupportActionBar();
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
