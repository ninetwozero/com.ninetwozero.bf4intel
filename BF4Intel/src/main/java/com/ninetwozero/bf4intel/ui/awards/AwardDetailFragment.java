package com.ninetwozero.bf4intel.ui.awards;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class AwardDetailFragment extends DialogFragment {
    public static final String TAG = "AwardDetailFragment";

    public static AwardDetailFragment newInstance(final Bundle data) {
        final AwardDetailFragment fragment = new AwardDetailFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(android.R.layout.simple_list_item_1, container, false);
        ((TextView) view.findViewById(android.R.id.text1)).setText(getClass().getSimpleName());
        return view;
    }
}
