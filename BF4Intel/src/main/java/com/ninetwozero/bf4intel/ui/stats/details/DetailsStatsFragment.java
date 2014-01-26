package com.ninetwozero.bf4intel.ui.stats.details;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.details.StatsDetails;
import com.ninetwozero.bf4intel.model.stats.details.StatsDetailsGrouped;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.List;

public class DetailsStatsFragment extends BaseLoadingFragment {

    private static final int ID_LOADER = 2400;
    private TableLayout multiplayerScoreTable;
    private TableLayout generalTable;
    private TableLayout gameModesTable;
    private TableLayout teamTable;
    private TableLayout extraTable;
    private TableLayout gameModeExtraTable;

    public static DetailsStatsFragment newInstance(Bundle bundle) {
        DetailsStatsFragment fragment = new DetailsStatsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);
        View view = inflater.inflate(R.layout.fragment_details_stats, parent, false);
        multiplayerScoreTable = (TableLayout) view.findViewById(R.id.multiplayer_score_table);
        generalTable = (TableLayout) view.findViewById(R.id.general_table);
        gameModesTable = (TableLayout) view.findViewById(R.id.game_modes_table);
        teamTable = (TableLayout) view.findViewById(R.id.team_table);
        extraTable = (TableLayout) view.findViewById(R.id.extra_table);
        gameModeExtraTable = (TableLayout) view.findViewById(R.id.game_mode_extra_table);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        getLoaderManager().restartLoader(ID_LOADER, null, this);
    }

    @Override
    public Loader<Result> onCreateLoader(int i, Bundle bundle) {
        //showLoadingState(true);
        return new IntelLoader(getActivity(), new SimpleGetRequest(UrlFactory.buildDetailsURL(200661244, 1)));
    }

    @Override
    public void onLoadFinished(Loader<Result> resultLoader, Result result) {
        if (result == Result.SUCCESS) {
            onLoadSuccess(result.getResultMessage());
        } else {
            onLoadFailure(result.getResultMessage());
        }
    }

    @Override
    protected void onLoadSuccess(String resultMessage) {
        StatsDetails.GeneralStats details = fromJson(resultMessage, StatsDetails.class).getGeneralStats();
        StatsDetailsGrouped stats = new StatsDetailsGrouped(details);

        populateTable(multiplayerScoreTable, R.string.multiplayer_score, stats.getMultiplayerScore());
        populateTable(generalTable, R.string.general_score, stats.getGeneralScores());
        populateTable(gameModesTable, R.string.game_modes, stats.getGameModes());
        populateTable(teamTable, R.string.team_score, stats.getTeamScores());
        populateTable(extraTable, R.string.extra_score, stats.getExtraScores());
        populateTable(gameModeExtraTable, R.string.game_mode_extra, stats.getGameModeExtra());
    }

    private void populateTable(TableLayout table, int tableHeader, List<Pair<Integer, String>> pairList) {
        if (table.getChildCount() > 0) {
            table.removeAllViews();
        }

        TableRow header = (TableRow) layoutInflater.inflate(R.layout.table_row_header_stats_details, null);
        ((TextView) header.findViewById(R.id.header_label)).setText(tableHeader);
        table.addView(header);
        for (Pair<Integer, String> pair : pairList) {
            TableRow row = (TableRow) layoutInflater.inflate(R.layout.table_row_stats_details, null);
            ((TextView) row.findViewById(R.id.score_label)).setText(pair.first);
            ((TextView) row.findViewById(R.id.score_value)).setText(pair.second);
            table.addView(row);
        }
    }

    @Override
    protected void onLoadFailure(String resultMessage) {
        showToast(resultMessage);
    }
}
