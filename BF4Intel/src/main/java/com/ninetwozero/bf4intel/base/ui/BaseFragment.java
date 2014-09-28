package com.ninetwozero.bf4intel.base.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.provider.MenuProvider;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;
import com.ninetwozero.bf4intel.utils.GoogleAnalytics;
import com.splunk.mint.Mint;
import com.splunk.mint.MintLogLevel;
import com.squareup.picasso.Picasso;

public abstract class BaseFragment extends Fragment implements MenuProvider.OnMenuProviderSelectedListener {
    public static final String FLAG_DISABLE_AUTOMATIC_ANALYTICS = "flag_disable_automated_analytics";
    public static final String FLAG_DISABLE_RETAIN_STATE = "flag_disable_retain_instance_state";

    protected static final float ALPHA_ENABLED = 0.8f;
    protected static final float ALPHA_DISABLED = 0.3f;

    protected FragmentManager fragmentManager;
    protected LayoutInflater layoutInflater;
    protected SharedPreferences sharedPreferences;

    private boolean sw600dp;
    private boolean sw720dp;

    private static Toast toast;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setRetainInstance(!getArgumentsBundle().getBoolean(FLAG_DISABLE_RETAIN_STATE, false));
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        layoutInflater = inflater;
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();

        final Bundle bundle = getArgumentsBundle();
        if (!bundle.getBoolean(FLAG_DISABLE_AUTOMATIC_ANALYTICS, false)) {
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
        GoogleAnalytics.post(getActivity(), getClass().getSimpleName());
    }

    protected void updateActionBar(final Activity activity, final String text) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = ((ActionBarActivity) activity).getSupportActionBar();
        actionBar.setTitle(text);
    }

    protected void updateActionBar(final Activity activity, final String text, final int icon) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = ((ActionBarActivity) activity).getSupportActionBar();
        actionBar.setTitle(text);
        actionBar.setIcon(icon);
    }

    protected void setActionBarSubTitle(String subtitle) {
        try {
            ((ActionBarActivity)getActivity()).getSupportActionBar().setSubtitle(subtitle);
        } catch (NullPointerException npe){
            String message = String.format(BaseFragment.class.getSimpleName()
                    + " Some of following objects maybe null getActivity() getActivity().getActionBar()  subtitle");
            Mint.logEvent(message, MintLogLevel.Error);
        }
    }

    protected void addMenuProviderFor(int menuId, Menu menu, String[] providerTitles) {
        MenuItem sortMenu = menu.findItem(menuId);
        MenuProvider sortMenuProvider = (MenuProvider) MenuItemCompat.getActionProvider(sortMenu);
        sortMenuProvider.setMenuTitles(providerTitles);
        sortMenuProvider.setOnMenuSelectedListener(this);
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

    protected void setText(final View view, final int resourceId, final int textResource) {
        ((TextView) view.findViewById(resourceId)).setText(textResource);
    }

    protected void setText(final View view, final int resourceId, final String text) {
        ((TextView) view.findViewById(resourceId)).setText(text);
    }

    protected void setProgress(final View view, final int resourceId, final int current) {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(resourceId);
        progressBar.setMax(100);
        progressBar.setProgress(current);
    }

    protected void setProgress(final View view, final int resourceId, final int current, final int max) {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(resourceId);
        progressBar.setMax(max);
        progressBar.setProgress(current);
    }

    protected void setImage(final View view, final int viewId, int imageResource) {
        Picasso.with(getActivity()).load(imageResource).into((ImageView) view.findViewById(viewId));
    }

    public void setVisibility(final View view, final int resourceId, final int state) {
        view.findViewById(resourceId).setVisibility(state);
    }

    @Override
    public void onMenuSelected(MenuItem item) {
    }

    protected String getResourceString(int resourceId) {
        return getActivity().getResources().getString(resourceId);
    }

    protected String[] getResourceStringArray(int resourceId) {
        return getActivity().getResources().getStringArray(resourceId);
    }

    protected void actionBarSetSubtitleFromSharedPref(String sharedPrefKey, int defaultResource) {
        String defaultSubtitle = getResourceString(defaultResource);
        String subtitle = sharedPreferences.contains(sharedPrefKey)
            ? sharedPreferences.getString(sharedPrefKey, defaultSubtitle)
            : defaultSubtitle;

        setActionBarSubTitle(subtitle);
    }
}
