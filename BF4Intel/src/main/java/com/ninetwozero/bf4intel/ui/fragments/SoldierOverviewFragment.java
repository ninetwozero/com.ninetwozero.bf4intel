package com.ninetwozero.bf4intel.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.dao.soldieroverview.SoldierOverviewDAO;
import com.ninetwozero.bf4intel.datatypes.Skill;
import com.ninetwozero.bf4intel.events.soldieroverview.SoldierOverviewRefreshedEvent;
import com.ninetwozero.bf4intel.json.soldieroverview.BaseStatsModel;
import com.ninetwozero.bf4intel.json.soldieroverview.CompletionProgress;
import com.ninetwozero.bf4intel.json.soldieroverview.GameModeServiceStar;
import com.ninetwozero.bf4intel.json.soldieroverview.SkillOverview;
import com.ninetwozero.bf4intel.json.soldieroverview.SoldierOverview;
import com.ninetwozero.bf4intel.json.soldieroverview.TopLeaderboardItem;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.resources.maps.CompletionStringMap;
import com.ninetwozero.bf4intel.resources.maps.leaderboards.LeaderboardStringMap;
import com.ninetwozero.bf4intel.resources.maps.profile.PlatformStringMap;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankImageMap;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankStringMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;
import com.ninetwozero.bf4intel.services.SoldierOverviewService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.ninetwozero.bf4intel.utils.LeaderboardUtils;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class SoldierOverviewFragment extends BaseLoadingFragment {
    public SoldierOverviewFragment() {
    }

    public static SoldierOverviewFragment newInstance(final Bundle data) {
        final SoldierOverviewFragment fragment = new SoldierOverviewFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);
        return layoutInflater.inflate(R.layout.fragment_soldier_overview, parent, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle arguments = getArgumentsBundle();
        Query.one(
            SoldierOverviewDAO.class,
            "SELECT * " +
            "FROM " + SoldierOverviewDAO.TABLE_NAME + " " +
            "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<SoldierOverviewDAO>() {
                @Override
                public boolean handleResult(SoldierOverviewDAO soldierOverviewDAO) {
                    final View view = getView();
                    if (view == null || soldierOverviewDAO == null) {
                        return true;
                    }

                    displayInformation(view, soldierOverviewDAO.getSoldierOverview());
                    showLoadingState(false);
                    return true;
                }
            }
        );
    }

    @Override
    protected void startLoadingData() {
        if (isReloading) {
            return;
        }

        showLoadingState(true);
        isReloading = true;

        final Intent intent = new Intent(getActivity(), SoldierOverviewService.class);
        intent.putExtra(SoldierOverviewService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        startLoadingData();
    }

    @Subscribe
    public void onSoldierOverviewRefreshed(SoldierOverviewRefreshedEvent event) {
        isReloading = false;
    }

    private void displayInformation(final View baseView, final SoldierOverview soldierOverview) {
        displayGeneralInformation(baseView, soldierOverview);
        displaySkills(baseView, soldierOverview.getBasicSoldierStats());
        displayServiceStars(baseView, soldierOverview.getBasicSoldierStats());
        displayToplist(baseView, R.id.wrap_soldier_top3_weapons, soldierOverview.getTopWeapons(), true);
        displayToplist(baseView, R.id.wrap_soldier_top3_vehicles, soldierOverview.getTopVehicles(), false);
        displayTopLeaderboards(baseView, soldierOverview.getLeaderboardItems());
        displayTopGameModes(baseView, soldierOverview.getBasicSoldierStats());
        displayCompletions(baseView, soldierOverview.getCompletions());
    }

    private void displayGeneralInformation(final View baseView, final SoldierOverview soldierOverview) {
        final Bundle args = getArguments();
        final View root = baseView.findViewById(R.id.wrap_soldier_general);
        final int maxScore = soldierOverview.getMaxScoreCurrentRank();
        final int progressToNextRankToNextRank = soldierOverview.getProgressToNextRank();

        setText(root, R.id.soldier_name, args.getString(Keys.Soldier.NAME));
        setText(root, R.id.soldier_platform, PlatformStringMap.get(soldierOverview.getPlatformId()));
        setText(root, R.id.current_rank_title, RankStringMap.get(soldierOverview.getCurrentRank().getName()));
        setText(
            root,
            R.id.value_rank_progress,
            String.format(getString(R.string.generic_x_of_y), format(progressToNextRankToNextRank), format(maxScore))
        );

        setImage(root, R.id.image_rank, RankImageMap.get(soldierOverview.getCurrentRank().getName()));
        setProgress(root, R.id.progress_rank, progressToNextRankToNextRank, maxScore);
    }

    private void displayServiceStars(final View baseView, final SkillOverview basicSoldierStats) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_service_stars);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area_service_stars);
        final Map<Integer, Integer> serviceStars = basicSoldierStats.getServiceStars();
        final Map<Integer, Double> serviceStarProgress = basicSoldierStats.getServiceStarProgress();

        contentArea.removeAllViews();
        List<Integer> keys = new ArrayList<Integer>(serviceStars.keySet());
        Collections.sort(keys);

        for (int key : keys) {
            final View parent = layoutInflater.inflate(R.layout.list_item_soldier_service_stars, null, false);
            final int serviceStarCount = serviceStars.get(key);
            final int roundedProgress = (int) Math.round(serviceStarProgress.get(key));

            setProgress(parent, R.id.progressbar, roundedProgress);
            setText(parent, R.id.title, fetchKitTitleFromId(key));
            setText(parent, R.id.service_star_count, String.valueOf(serviceStarCount));
            setText(parent, R.id.progress_text, roundedProgress + "%");

            contentArea.addView(parent);
        }
    }

    private void displayTopGameModes(final View baseView, final SkillOverview basicSoldierStats) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_top3_game_modes);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area_game_modes);

        contentArea.removeAllViews();

        for (GameModeServiceStar serviceStar : basicSoldierStats.getGameModeServiceStars()) {
            final View parent = layoutInflater.inflate(R.layout.list_item_soldier_service_stars, null, false);
            final int progress = Math.round(serviceStar.getServiceStarProgress());

            setProgress(parent, R.id.progressbar, progress);
            setText(parent, R.id.title, fetchGameModeTitleFromKey(serviceStar.getCriteria()));
            setText(parent, R.id.service_star_count, String.valueOf(serviceStar.getServiceStarCount()));
            setText(parent, R.id.progress_text, progress + "%");

            contentArea.addView(parent);
        }
    }

    private int fetchGameModeTitleFromKey(String key) {
        if (key.equals("sc_conquest")) {
            return R.string.gamemode_conquest;
        } else if (key.equals("sc_rush")) {
            return R.string.gamemode_rush;
        } else if (key.equals("sc_obliteration")) {
            return R.string.gamemode_obliteration;
        } else if (key.equals("sc_capturetheflag")) {
            return R.string.gamemode_capture_the_flag;
        } else if (key.equals("sc_airsuperiority")) {
            return R.string.gamemode_air_superiority;
        } else if (key.equals("sc_defuse")) {
            return R.string.gamemode_defuse;
        } else if (key.equals("sc_domination")) {
            return R.string.gamemode_domination;
        } else if (key.equals("sc_deathmatch")) {
            return R.string.gamemode_deathmatch;
        } else {
            return R.string.na;
        }
    }

    private void displaySkills(final View baseView, final SkillOverview basicSoldierStats) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_skills);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.skills_table);
        final Activity activity = getActivity();
        final List<Skill> skillsList = skillsListFrom(basicSoldierStats);

        final TableLayout.LayoutParams rowLayoutParams = new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        final TableRow.LayoutParams cellLayoutParams = new TableRow.LayoutParams(
            0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f
        );

        contentArea.removeAllViews();
        for (int i = 0, counter = 0, maxRows = 2; i < maxRows; i++) {
            final TableRow tableRow = new TableRow(activity);
            tableRow.setLayoutParams(rowLayoutParams);
            tableRow.setWeightSum(3f);

            for (int j = 0, maxCols = 3; j < maxCols; j++, counter++) {
                final View cell = layoutInflater.inflate(R.layout.list_item_soldier_skills, null, false);
                setText(cell, R.id.title, skillsList.get(counter).getStringResource());
                setText(cell, R.id.value, skillsList.get(counter).getValue());
                tableRow.addView(cell, cellLayoutParams);
            }
            contentArea.addView(tableRow);
        }
        setText(root, R.id.rating, String.valueOf(basicSoldierStats.getSkillRating()));
    }

    private void displayToplist(final View baseView, final int wrapId, final List<BaseStatsModel> stats, final boolean isWeapon) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(wrapId);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area);
        contentArea.removeAllViews();

        for (int i = 0, max = 3; i < max; i++) {
            final View parent = layoutInflater.inflate(R.layout.list_item_soldier_toplist, null, false);
            final String name = stats.get(i).getName();
            setText(parent, R.id.title, isWeapon ? WeaponStringMap.get(name) : VehicleStringMap.get(name));
            setText(
                parent,
                R.id.value,
                String.format(
                    getString(R.string.num_kills),
                    format(stats.get(i).getKillCount())
                )
            );
            contentArea.addView(parent);
        }
    }

    private void displayTopLeaderboards(final View baseView, final List<TopLeaderboardItem> leaderboardItems) {
        final Activity activity = getActivity();
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_top_leaderboards);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area_leaderboards);

        contentArea.removeAllViews();
        Collections.sort(leaderboardItems);

        for (TopLeaderboardItem item : leaderboardItems) {
            final View parent = layoutInflater.inflate(R.layout.list_item_soldier_leaderboard, null, false);

            setText(parent, R.id.title, LeaderboardStringMap.Category.get(item.getInformation().getName()));
            setText(parent, R.id.subtitle, fetchSubtitleForLeaderboard(item));
            setText(
                parent,
                R.id.score,
                LeaderboardUtils.getScoreString(activity, item.getStatId(), item.getScore())
            );

            contentArea.addView(parent);
        }
    }

    private String fetchSubtitleForLeaderboard(TopLeaderboardItem item) {
        return String.format(
            getString(R.string.leaderboard_division),
            item.getDivision(),
            getString(LeaderboardStringMap.AreaType.get(item.getArea().getType()))
        );
    }

    private void displayCompletions(final View baseView, final List<CompletionProgress> completions) {
        final ViewGroup root = (ViewGroup) baseView.findViewById(R.id.wrap_soldier_completions);
        final ViewGroup contentArea = (ViewGroup) root.findViewById(R.id.content_area_completions);
        contentArea.removeAllViews();

        for (CompletionProgress completionProgress : completions) {
            final View parent = layoutInflater.inflate(R.layout.list_item_soldier_completion, null, false);

            setText(parent, R.id.title, CompletionStringMap.get(completionProgress.getName()));
            setText(
                parent,
                R.id.progress_text,
                String.format(
                    getString(R.string.generic_x_of_y),
                    completionProgress.getCurrentValue(),
                    completionProgress.getMaxValue()
                )
            );
            setProgress(parent, R.id.progressbar, completionProgress.getCurrentValue(), completionProgress.getMaxValue());
            contentArea.addView(parent);
        }
    }

    private List<Skill> skillsListFrom(SkillOverview so) {
        final List<Skill> skillList = new ArrayList<Skill>(6);
        skillList.add(new Skill(R.string.skills_kd, so.getKillDeathRatio()));
        skillList.add(new Skill(R.string.skills_spm, format(so.getScorePerMinute())));
        skillList.add(new Skill(R.string.skills_kpm, format(so.getKillsPerMinute())));
        skillList.add(new Skill(R.string.skills_kills, format(so.getKillCount())));
        skillList.add(new Skill(R.string.skills_score, format(so.getScore())));
        skillList.add(new Skill(R.string.skills_time, DateTimeUtils.toLiteral(so.getTimePlayed())));
        return skillList;
    }

    private int fetchKitTitleFromId(final int key) {
        switch (key) {
            case 1:
                return R.string.class_assault;
            case 2:
                return R.string.class_engineer;
            case 8:
                return R.string.class_recon;
            case 32:
                return R.string.class_support;
            case 2048:
                return R.string.class_commander;
            default:
                return R.string.na;
        }
    }
}
