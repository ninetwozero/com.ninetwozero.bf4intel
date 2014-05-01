package com.ninetwozero.bf4intel.ui.assignments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class AssignmentDetailFragment extends DialogFragment {
    public static final String TAG = "AssignmentDetailFragment";

    public static AssignmentDetailFragment newInstance(final Bundle data) {
        final AssignmentDetailFragment fragment = new AssignmentDetailFragment();
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
