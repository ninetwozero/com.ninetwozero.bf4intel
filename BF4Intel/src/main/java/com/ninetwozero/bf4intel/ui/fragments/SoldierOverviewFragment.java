package com.ninetwozero.bf4intel.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.network.ConnectionRequest;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.datatypes.Skill;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.resources.maps.*;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankImageMap;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.ninetwozero.bf4intel.utils.PersonaUtils;
import com.ninetwozero.bf4intel.utils.Result;
import com.ninetwozero.bf4intel.json.soldieroverview.BaseStatsModel;
import com.ninetwozero.bf4intel.json.soldieroverview.CompletionProgress;
import com.ninetwozero.bf4intel.json.soldieroverview.SkillOverview;
import com.ninetwozero.bf4intel.json.soldieroverview.SoldierOverview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SoldierOverviewFragment extends BaseLoadingFragment {
    private static final int ID_LOADER = SoldierOverview.class.hashCode();

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

        final View view = layoutInflater.inflate(R.layout.fragment_soldier_overview, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
    }

    /*
        TODO: We need to figure out a way to not download new data upon rotating the screen!
    */
    @Override
    protected void startLoadingData() {
        final LoaderManager manager = getActivity().getSupportLoaderManager();
        if (manager.getLoader(ID_LOADER) == null) {
            manager.initLoader(ID_LOADER, getArguments(), this);
        } else {
            manager.restartLoader(ID_LOADER, getArguments(), this);
        }
    }

    @Override
    public Loader<Result> onCreateLoader(final int i, final Bundle bundle) {
        displayAsLoading(true);
        return new IntelLoader(
            getActivity(),
            new ConnectionRequest(
                UrlFactory.build(
                    UrlFactory.Type.SOLDIER_OVERVIEW,
                    bundle.getString(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }

    @Override
    public void onLoadFinished(final Loader<Result> resultLoader, final Result result) {
        if (result == Result.SUCCESS) {
            onLoadSuccess(result.getResultMessage());
        } else {
            onLoadFailure(result.getResultMessage());
        }
    }

    @Override
    protected void onLoadSuccess(final String resultMessage) {
        final SoldierOverview soldierOverview = fromJson(resultMessage, SoldierOverview.class);
        displayInformation(getView(), soldierOverview);
        displayAsLoading(false);
    }

    @Override
    protected void onLoadFailure(final String resultMessage) {
        showToast(resultMessage);
    }

    private void initialize(final View view) {
        /* TODO: NEED TO INIT? */
    }

    private void displayInformation(final View baseView, final SoldierOverview soldierOverview) {
        final Bundle args = getArguments();

        displayGeneralInformation(baseView, soldierOverview);
        displaySkills(baseView, soldierOverview.getBasicSoldierStats());
        displayServiceStars(baseView, soldierOverview.getBasicSoldierStats());
        displayToplist(baseView, R.id.wrap_soldier_top3_weapons, soldierOverview.getTopWeapons(), true);
        displayToplist(baseView, R.id.wrap_soldier_top3_vehicles, soldierOverview.getTopVehicles(), false);
        displayCompletions(baseView, soldierOverview.getCompletions());

        updateActionBar(getActivity(), args.getString(Keys.Soldier.NAME), R.drawable.test_soldier);
    }

    private void displayGeneralInformation(final View baseView, final SoldierOverview soldierOverview) {
        final Bundle args = getArguments();
        final View root = baseView.findViewById(R.id.wrap_soldier_general);
        final ProgressBar progressBar = (ProgressBar) baseView.findViewById(R.id.progress_rank);
        final int maxScore = soldierOverview.getMaxScoreCurrentRank();
        final int scoreLeftToNextRank = soldierOverview.getScoreLeftToNextRank();
        final int currentScoreThisRank = maxScore - scoreLeftToNextRank;

        ((TextView) root.findViewById(R.id.soldier_name)).setText(args.getString(Keys.Soldier.NAME));
        ((TextView) root.findViewById(R.id.current_rank_title)).setText(
            RankStringMap.get(soldierOverview.getCurrentRank().getName())
        );
        ((TextView) root.findViewById(R.id.value_rank_progress)).setText(
            String.format(getString(R.string.generic_x_of_y), currentScoreThisRank, maxScore)
        );

        // FIXME: Display appropriate image in ActionBar
        ((ImageView) root.findViewById(R.id.image_rank)).setImageResource(RankImageMap.get(soldierOverview.getCurrentRank().getLevel()));

        progressBar.setMax(maxScore);
        progressBar.setProgress(currentScoreThisRank);
    }

    private void displayServiceStars(final View baseView, final SkillOverview basicSoldierStats) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_service_stars);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area);
        final Map<Integer, Integer> serviceStars = basicSoldierStats.getServiceStars();
        final Map<Integer, Double> serviceStarProgress = basicSoldierStats.getServiceStarProgress();

        contentArea.removeAllViews();
        List<Integer> keys = new ArrayList<Integer>(serviceStars.keySet());
        Collections.sort(keys);
        for (int key : keys) {
            final View parent = layoutInflater.inflate(R.layout.list_item_soldier_service_stars, null, false);
            final ProgressBar progressBar = (ProgressBar) parent.findViewById(R.id.progressbar);

            progressBar.setProgress((int) Math.round(serviceStarProgress.get(key)));
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
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        );
        final List<Skill> skillsList = skillsListFrom(basicSoldierStats);

        contentArea.removeAllViews();
        ((TextView) root.findViewById(R.id.rating)).setText(
            String.valueOf(basicSoldierStats.getSkillRating())
        );

        for (int i = 0, counter = 0, maxRows = 2; i < maxRows; i++) {
            final TableRow tableRow = new TableRow(activity);
            tableRow.setLayoutParams(rowLayoutParams);
            tableRow.setWeightSum(3f);

            for (int j = 0, maxCols = 3; j < maxCols; j++, counter++) {
                final View cell = layoutInflater.inflate(R.layout.list_item_soldier_skills, null, false);
                ((TextView) cell.findViewById(R.id.title)).setText(skillsList.get(counter).getStringResource());
                ((TextView) cell.findViewById(R.id.value)).setText(skillsList.get(counter).getValue());
                tableRow.addView(cell, cellLayoutParams);
            }
            contentArea.addView(tableRow);
        }
    }

    private void displayToplist(final View baseView, final int wrapId, final List<BaseStatsModel> stats, final boolean isWeapon) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(wrapId);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area);
        contentArea.removeAllViews();

        for (int i = 0, max = 3; i < max; i++) {
            final View parent = layoutInflater.inflate(R.layout.list_item_soldier_toplist, null, false);
            ((TextView) parent.findViewById(R.id.title)).setText(
                isWeapon ? WeaponStringMap.get(stats.get(i).getName()) : VehicleStringMap.get(stats.get(i).getName())
            );
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
            final View parent = layoutInflater.inflate(R.layout.list_item_soldier_completion, null, false);
            final ProgressBar progressBar = (ProgressBar) parent.findViewById(R.id.progressbar);

            progressBar.setProgress(completionProgress.getCurrentValue());
            progressBar.setMax(completionProgress.getMaxValue());

            ((TextView) parent.findViewById(R.id.title)).setText(CompletionStringMap.get(completionProgress.getName()));
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

    private List<Skill> skillsListFrom(SkillOverview so) {
        final List<Skill> skillList = new ArrayList<Skill>(6);
        skillList.add(new Skill(R.string.skills_kd, so.getKillDeathRatio()));
        skillList.add(new Skill(R.string.skills_spm, so.getScorePerMinute()));
        skillList.add(new Skill(R.string.skills_kpm, String.format("%.2f", so.getKillsPerMinute())));
        skillList.add(new Skill(R.string.skills_kills, so.getKillCount()));
        skillList.add(new Skill(R.string.skills_score, String.format("%,d", so.getScore())));
        skillList.add(new Skill(R.string.skills_time, DateTimeUtils.toLiteral(so.getTimePlayed())));
        return skillList;
    }
}
