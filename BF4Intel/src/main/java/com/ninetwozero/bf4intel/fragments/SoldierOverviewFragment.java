package com.ninetwozero.bf4intel.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.abstractions.BaseFragment;
import com.ninetwozero.bf4intel.datatypes.Skill;
import com.ninetwozero.bf4intel.jsonmodels.BaseStatsModel;
import com.ninetwozero.bf4intel.jsonmodels.CompletionProgress;
import com.ninetwozero.bf4intel.jsonmodels.SkillOverview;
import com.ninetwozero.bf4intel.jsonmodels.SoldierOverview;
import com.ninetwozero.bf4intel.utils.PersonaUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

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

    @Override
    public void onResume() {
        super.onResume();
        mockSettingUpLayoutFromJson();
    }

    private void mockSettingUpLayoutFromJson() {
        try {
            final Reader reader = new InputStreamReader(
                getActivity().getAssets().open("soldieroverview.json")
            );
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();

            JsonObject rootObject = parser.parse(reader).getAsJsonObject().getAsJsonObject("data");
            SoldierOverview soldierOverview = gson.fromJson(rootObject, SoldierOverview.class);
            displayInformation(getView(), soldierOverview);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initialize(final View view) {
    }

    private void displayInformation(final View baseView, final SoldierOverview soldierOverview) {
        displayGeneralInformation(baseView, soldierOverview);
        displaySkills(baseView, soldierOverview.getBasicSoldierStats());
        displayServiceStars(baseView, soldierOverview.getBasicSoldierStats());
        displayToplist(baseView, R.id.wrap_soldier_top3_weapons, soldierOverview.getTopWeapons());
        displayToplist(baseView, R.id.wrap_soldier_top3_vehicles, soldierOverview.getTopVehicles());
        displayCompletions(baseView, soldierOverview.getCompletions());

        updateActionBar(getActivity(), "Some user here", R.drawable.test_soldier);
    }

    private void displayGeneralInformation(final View baseView, final SoldierOverview soldierOverview) {
        final View root = baseView.findViewById(R.id.wrap_soldier_general);
        final ProgressBar progressBar = (ProgressBar) baseView.findViewById(R.id.progress_rank);
        final int maxScore = soldierOverview.getMaxScoreCurrentRank();
        final int scoreLeftToNextRank = soldierOverview.getScoreLeftToNextRank();
        final int currentScoreThisRank = maxScore-scoreLeftToNextRank;

        ((TextView) root.findViewById(R.id.soldier_name)).setText("Some soldier name");
        ((TextView) root.findViewById(R.id.current_rank_title)).setText(
            soldierOverview.getCurrentRank().getName()
        );
        ((TextView) root.findViewById(R.id.value_rank_progress)).setText(
            String.format(getString(R.string.generic_x_of_y), currentScoreThisRank, maxScore)
        );
        ((ImageView) root.findViewById(R.id.image_rank)).setImageResource(R.drawable.test_rank31);

        progressBar.setProgress(currentScoreThisRank);
        progressBar.setMax(maxScore);
    }

    private void displayServiceStars(final View baseView, final SkillOverview basicSoldierStats) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_service_stars);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area);
        final Map<Integer, Integer> serviceStars = basicSoldierStats.getServiceStars();
        final Map<Integer, Double> serviceStarProgress = basicSoldierStats.getServiceStarProgress();

        contentArea.removeAllViews();
        for (int key : serviceStars.keySet()) {
            final View parent = mInflater.inflate(R.layout.list_item_soldier_service_stars, null, false);
            final ProgressBar progressBar = (ProgressBar) parent.findViewById(R.id.progressbar);

            progressBar.setProgress((int)Math.round(serviceStarProgress.get(key)));
            progressBar.setMax(100);

            ((ImageView) parent.findViewById(R.id.image)).setImageResource(
                PersonaUtils.getIconForKit(key)
            );
            contentArea.addView(parent);
        }
    }

    private void displaySkills(final View baseView, final SkillOverview basicSoldierStats) {
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
        final List<Skill> skillsList = basicSoldierStats.asList();

        contentArea.removeAllViews();
        ((TextView) root.findViewById(R.id.rating)).setText(
            String.valueOf(basicSoldierStats.getSkillRating())
        );

        // TODO: Why is this not creating three equally large cells per row?
        for (int i = 0, counter = 0, maxRows = 2; i < maxRows; i++) {
            final TableRow tableRow = new TableRow(activity);
            tableRow.setLayoutParams(rowLayoutParams);
            tableRow.setWeightSum(3f);

            for (int j = 0, maxCols = 3; j < maxCols; j++, counter++) {
                final View cell = mInflater.inflate(R.layout.list_item_soldier_skills, null, false);
                ((TextView) cell.findViewById(R.id.title)).setText(skillsList.get(counter).getStringResource());
                ((TextView) cell.findViewById(R.id.value)).setText(skillsList.get(counter).getValue());
                tableRow.addView(cell, cellLayoutParams);
            }
            contentArea.addView(tableRow);
        }
    }

    private void displayToplist(final View baseView, final int wrapId, final List<BaseStatsModel> stats) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(wrapId);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area);
        contentArea.removeAllViews();

        for (int i = 0, max = 3; i < max; i++) {
            final View parent = mInflater.inflate(R.layout.list_item_soldier_toplist,  null, false);
            ((TextView) parent.findViewById(R.id.title)).setText(stats.get(i).getName());
            ((TextView) parent.findViewById(R.id.value)).setText(
                    String.format(getString(R.string.soldier_num_kills), stats.get(i).getKillCount())
            );
            contentArea.addView(parent);
        }
    }

    private void displayCompletions(final View baseView, final List<CompletionProgress> completions) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_completions);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area);
        contentArea.removeAllViews();

        for (CompletionProgress completionProgress : completions) {
            final View parent = mInflater.inflate(R.layout.list_item_soldier_completion, null, false);
            final ProgressBar progressBar = (ProgressBar) parent.findViewById(R.id.progressbar);

            progressBar.setProgress(completionProgress.getCurrentValue());
            progressBar.setMax(completionProgress.getMaxValue());

            ((TextView) parent.findViewById(R.id.title)).setText(completionProgress.getName());
            ((TextView) parent.findViewById(R.id.progress_text)).setText(
                    String.format(
                        getString(R.string.generic_x_of_y),
                        completionProgress.getCurrentValue(),
                        completionProgress.getMaxValue()
                    )
            );
            contentArea.addView(parent);
        }
    }
}
