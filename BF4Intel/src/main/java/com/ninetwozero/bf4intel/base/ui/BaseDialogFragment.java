package com.ninetwozero.bf4intel.base.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.utils.GoogleAnalytics;
import com.squareup.picasso.Picasso;

public class BaseDialogFragment extends DialogFragment {
    public static final String FLAG_DISABLE_AUTOMATIC_ANALYTICS = "flag_disable_automated_analytics";
    public static final String FLAG_DISABLE_RETAIN_STATE = "flag_disable_retain_instance_state";

    protected static final float ALPHA_ENABLED = 1f;
    protected static final float ALPHA_DISABLED = 0.5f;

    protected FragmentManager fragmentManager;
    protected LayoutInflater layoutInflater;
    protected SharedPreferences sharedPreferences;

    private boolean sw600dp;
    private boolean sw720dp;

    private static Toast toast;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(isSw600dp() || isSw720dp()) {
            //Hide default dialog title that is added on top of our layout
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
    }

    protected void setTitle(TextView view, String title) {
        if (!isSw720dp() && !isSw600dp()) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
            view.setVisibility(View.GONE);
        } else {
            view.setText(title);
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

    protected void postGoogleAnalytics() {
        GoogleAnalytics.post(getActivity(), getClass().getSimpleName());
    }

    protected void updateActionBar(final AppCompatActivity activity, final String text) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle(text);
    }

    protected void updateActionBar(final AppCompatActivity activity, final String text, final int icon) {
        if (activity == null) {
            return;
        }

        final ActionBar actionBar = activity.getSupportActionBar();
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

    protected void setImage(final View view, final int viewId, int imageResource, int placeholder) {
        Picasso.with(getActivity())
            .load(imageResource)
            .placeholder(placeholder)
            .into((ImageView) view.findViewById(viewId));
    }

    public void setVisibility(final View view, final int resourceId, final int state) {
        view.findViewById(resourceId).setVisibility(state);
    }

    public void setAlpha(final View view, final int resourceId, final float alpha) {
        view.findViewById(resourceId).setAlpha(alpha);
    }
}
