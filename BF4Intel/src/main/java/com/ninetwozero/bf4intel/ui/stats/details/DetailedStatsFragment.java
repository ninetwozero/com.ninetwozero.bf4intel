package com.ninetwozero.bf4intel.ui.stats.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.database.dao.stats.details.DetailedStatsDAO;
import com.ninetwozero.bf4intel.events.stats.details.DetailedStatsRefreshedEvent;
import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsGroup;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.stats.details.DetailedStatsService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class DetailedStatsFragment extends BaseLoadingListFragment {
    public static DetailedStatsFragment newInstance(final Bundle bundle) {
        final DetailedStatsFragment fragment = new DetailedStatsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);
        final View view = inflater.inflate(R.layout.generic_list, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle arguments = getArgumentsBundle();
        Query.one(
            DetailedStatsDAO.class,
            "SELECT * " +
            "FROM " + DetailedStatsDAO.TABLE_NAME + " " +
            "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<DetailedStatsDAO>() {
                @Override
                public boolean handleResult(DetailedStatsDAO detailedStatsDAO) {
                    if (detailedStatsDAO == null) {
                        startLoadingData(false);
                        return true;
                    }

                    sendDataToListView(view, detailedStatsDAO.getDetailedStats().getGroups());
                    showLoadingState(false);
                    return true;
                }
            }
        );
    }

    @Override
    protected void startLoadingData(boolean showLoading) {
        if (isReloading || !Bf4Intel.isConnectedToNetwork()) {
            return;
        }

        showLoadingState(showLoading);
        isReloading = true;

        final Intent intent = new Intent(getActivity(), DetailedStatsService.class);
        intent.putExtra(DetailedStatsService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Subscribe
    public void onDetailedStatsRefreshed(DetailedStatsRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    private void initialize(View view) {
        setupErrorMessage(view);
        setupSwipeRefreshLayout(view);
        setupListView(view);
    }

    private void setupListView(final View view) {
        if (view == null) {
            return;
        }

        setCustomEmptyText(view, R.string.empty_text_statistics);

        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        listView.setSelector(R.drawable.empty);
    }

    private void sendDataToListView(View view, final List<DetailedStatsGroup> detailedStats) {
        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        DetailedStatsAdapter adapter = (DetailedStatsAdapter) listView.getAdapter();
        if (adapter == null) {
            adapter = new DetailedStatsAdapter(getActivity());
            listView.setAdapter(adapter);
        }
        adapter.setItems(detailedStats);
    }
}
