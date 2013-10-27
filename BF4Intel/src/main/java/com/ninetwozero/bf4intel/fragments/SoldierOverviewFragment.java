package com.ninetwozero.bf4intel.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.abstractions.BaseFragment;

public class SoldierOverviewFragment extends BaseFragment {
    public SoldierOverviewFragment() {
    }

    public static SoldierOverviewFragment newInstance() {
        final SoldierOverviewFragment fragment = new SoldierOverviewFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static SoldierOverviewFragment newInstance(final Bundle data) {
        final SoldierOverviewFragment fragment = new SoldierOverviewFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = mInflater.inflate(R.layout.fragment_soldier_overview, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "NINETWOZERO", R.drawable.test_soldier);
    }

    private void displayInformation() {
        final View baseView = getView();
        if (baseView == null ) {
            return;
        }

        displayGeneralInformation(baseView);
        displayServiceStars(baseView);
        displaySkills(baseView);
        displayCompletions(baseView);
    }

    private void displayGeneralInformation(final View baseView) {
        final View root = baseView.findViewById(R.id.wrap_soldier_general);

        ((TextView) root.findViewById(R.id.soldier_name)).setText("Some username");
        ((TextView) root.findViewById(R.id.current_rank_title)).setText("Test Rank Title 1234567");
        ((TextView) root.findViewById(R.id.value_rank_progress)).setText(
            String.format(getString(R.string.generic_x_of_y), 12345, 56789)
        );
        ((TextView) root.findViewById(R.id.value_rank_left)).setText(
            String.format(getString(R.string.soldier_left_until_rankup), 56789-12345)
        );

        ((ImageView) root.findViewById(R.id.image_rank)).setImageResource(R.drawable.test_rank31);
        ((ImageView) root.findViewById(R.id.soldier_image)).setImageResource(R.drawable.test_soldier);
    }

    private void displayServiceStars(final View baseView) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_service_stars);
        root.removeAllViews();

        for (int i = 0, max = 5; i < max; i++) {
            final View parent = mInflater.inflate(R.layout.list_item_soldier_service_stars, null, false);
            final ProgressBar progressBar = (ProgressBar) parent.findViewById(R.id.progressbar);

            progressBar.setProgress(33);
            progressBar.setMax(100);

            ((ImageView) parent.findViewById(R.id.image)).setImageResource(R.drawable.test_gravatar);
            root.addView(parent);
        }
    }

    private void displaySkills(final View baseView) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_skills);
        // TODO
    }

    private void displayCompletions(final View baseView) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_completions);
        root.removeAllViews();

        for (int i = 0, max = 5; i < max; i++) {
            final View parent = mInflater.inflate(R.layout.list_item_soldier_completion,  null, false);
            final ProgressBar progressBar = (ProgressBar) parent.findViewById(R.id.progressbar);

            progressBar.setProgress(13);
            progressBar.setMax(249);

            ((TextView) parent.findViewById(R.id.progress_text)).setText(
                String.format(getString(R.string.generic_x_of_y), 13, 249)
            );
            ((ImageView) parent.findViewById(R.id.image)).setImageResource(R.drawable.test_gravatar);
            root.addView(parent);
        }
    }
}
