package com.ninetwozero.bf4intel.base.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ninetwozero.bf4intel.utils.GoogleAnalytics;

public abstract class BaseFragment extends Fragment {
    public static final String FLAG_DISABLE_AUTOMATIC_ANALYTICS = "flag_disable_automated_analytics";
    public static final String FLAG_DISABLE_RETAIN_STATE = "flag_disable_retain_instance_state";

    protected static final float ALPHA_ENABLED = 0.8f;
    protected static final float ALPHA_DISABLED = 0.3f;

    protected FragmentManager fragmentManager;
    protected LayoutInflater layoutInflater;

    private static Toast toast;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setRetainInstance(!getArgumentsBundle().getBoolean(FLAG_DISABLE_RETAIN_STATE, false));
        fragmentManager = getFragmentManager();
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

    protected Bundle getArgumentsBundle() {
        return getArguments() == null ? new Bundle() : getArguments();
    }

    protected void postGoogleAnalytics() {
        GoogleAnalytics.post(getActivity(), this.getClass().getSimpleName());
    }

    protected void updateActionBar(final Activity activity, final String text) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(text);
    }

    protected void updateActionBar(final Activity activity, final String text, final int icon) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(text);
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

    protected void setText(final View view, final int resourceId, final int textResource) {
        ((TextView) view.findViewById(resourceId)).setText(textResource);
    }

    protected void setText(final View view, final int resourceId, final String text) {
        ((TextView) view.findViewById(resourceId)).setText(text);
    }

    protected void setProgress(final View view, final int resourceId, final int current) {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(resourceId);
        progressBar.setProgress(current);
        progressBar.setMax(100);
    }

    protected void setProgress(final View view, final int resourceId, final int current, final int max) {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(resourceId);
        progressBar.setProgress(current);
        progressBar.setMax(max);
    }

    protected void setImage(final View view, final int viewId, int imageResource) {
        ((ImageView) view.findViewById(viewId)).setImageResource(imageResource);
    }
}
