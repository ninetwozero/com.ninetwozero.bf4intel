package com.ninetwozero.bf4intel.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.abstractions.BaseFragment;
import com.ninetwozero.bf4intel.datatypes.ListRow;

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
        updateActionBar(getActivity(), "Loading...");
        displayInformation(view );
    }

    private void displayInformation() {
        final View baseView = getView();
        if (baseView == null ) {
            return;
        }
        displayInformation(baseView);
    }

    private void displayInformation(final View baseView) {

        displayGeneralInformation(baseView);
        displayServiceStars(baseView);
        displaySkills(baseView);
        displayCompletions(baseView);
        updateActionBar(getActivity(), "Some soldier name", R.drawable.test_soldier);
    }

    private void displayGeneralInformation(final View baseView) {
        final View root = baseView.findViewById(R.id.wrap_soldier_general);
        final ProgressBar progressBar = (ProgressBar) baseView.findViewById(R.id.progress_rank);

        ((TextView) root.findViewById(R.id.soldier_name)).setText("Some soldier name");
        ((TextView) root.findViewById(R.id.current_rank_title)).setText("Test Rank Title 1234567");
        ((TextView) root.findViewById(R.id.value_rank_progress)).setText(
            String.format(getString(R.string.generic_x_of_y), 12345, 56789)
        );
        ((ImageView) root.findViewById(R.id.image_rank)).setImageResource(R.drawable.test_rank31);

        progressBar.setProgress(1337);
        progressBar.setMax(2674);
    }

    private void displayServiceStars(final View baseView) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_service_stars);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area);
        contentArea.removeAllViews();

        final int[] testImages = new int[] {
            R.drawable.kit_icon_engineer,
            R.drawable.kit_icon_medic,
            R.drawable.kit_icon_support,
            R.drawable.kit_icon_recon,
            R.drawable.kit_icon_commander
        };

        for (int i = 0, max = 5; i < max; i++) {
            final View parent = mInflater.inflate(R.layout.list_item_soldier_service_stars, null, false);
            final ProgressBar progressBar = (ProgressBar) parent.findViewById(R.id.progressbar);

            progressBar.setProgress(33);
            progressBar.setMax(100);

            ((ImageView) parent.findViewById(R.id.image)).setImageResource(testImages[i]);
            contentArea.addView(parent);
        }
    }

    private void displaySkills(final View baseView) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_skills);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.skills_table);
        final Activity activity = getActivity();
        final TableLayout.LayoutParams rowLayoutParams = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        final TableRow.LayoutParams cellLayoutParams = new TableRow.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        );

        contentArea.removeAllViews();
        ((TextView) root.findViewById(R.id.rating)).setText(String.valueOf(164));
        for (int i = 0, maxRows = 2; i < maxRows; i++) {
            final TableRow tableRow = new TableRow(activity);
            tableRow.setLayoutParams(rowLayoutParams);
            tableRow.setWeightSum(3f);

            for (int j = 0, maxCols = 3; j < maxCols; j++) {
                final View cell = mInflater.inflate(R.layout.list_item_soldier_skills, null, false);
                ((TextView) cell.findViewById(R.id.title)).setText("Title " + i + "x" + j);
                ((TextView) cell.findViewById(R.id.value)).setText("999,999,990");
                tableRow.addView(cell, cellLayoutParams);
            }
            contentArea.addView(tableRow);
        }
    }

    private void displayCompletions(final View baseView) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_completions);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area);
        contentArea.removeAllViews();

        for (int i = 0, max = 8; i < max; i++) {
            final View parent = mInflater.inflate(R.layout.list_item_soldier_completion,  null, false);
            final ProgressBar progressBar = (ProgressBar) parent.findViewById(R.id.progressbar);

            progressBar.setProgress(13);
            progressBar.setMax(249);

            ((TextView) parent.findViewById(R.id.progress_text)).setText(
                String.format(getString(R.string.generic_x_of_y), 13, 249)
            );
            ((ImageView) parent.findViewById(R.id.image)).setImageResource(R.drawable.test_gravatar);
            contentArea.addView(parent);
        }
    }
}
