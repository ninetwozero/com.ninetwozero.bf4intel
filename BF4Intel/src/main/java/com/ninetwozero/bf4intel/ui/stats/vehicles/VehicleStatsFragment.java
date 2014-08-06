package com.ninetwozero.bf4intel.ui.stats.vehicles;

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
import com.ninetwozero.bf4intel.database.dao.stats.vehicles.VehicleStatsDAO;
import com.ninetwozero.bf4intel.events.stats.vehicles.VehicleStatsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStats;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.stats.vehicles.VehicleStatsService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class VehicleStatsFragment extends BaseLoadingListFragment {
    public static VehicleStatsFragment newInstance(final Bundle data) {
        final VehicleStatsFragment fragment = new VehicleStatsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_list_stats, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle arguments = getArgumentsBundle();
        Query.one(
            VehicleStatsDAO.class,
            "SELECT * " +
                "FROM " + VehicleStatsDAO.TABLE_NAME + " " +
                "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<VehicleStatsDAO>() {
                @Override
                public boolean handleResult(VehicleStatsDAO vehiclestatsDAO) {
                    if (vehiclestatsDAO == null) {
                        startLoadingData();
                        return true;
                    }

                    sendDataToListView(vehiclestatsDAO.getVehicleStats().getItems());
                    showLoadingState(false);
                    return true;
                }
            }
        );
    }

    @Override
    protected void startLoadingData() {
        if (isReloading || !Bf4Intel.isConnectedToNetwork()) {
            return;
        }

        showLoadingState(true);
        isReloading = true;

        final Intent intent = new Intent(getActivity(), VehicleStatsService.class);
        intent.putExtra(VehicleStatsService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int i, long l) {
        final GroupedVehicleStats groupedVehicles = (GroupedVehicleStats) getListAdapter().getItem(i);
        final Bundle dataToPass = getArgumentsBundle();
        dataToPass.putSerializable(VehicleDetailFragment.INTENT_VEHICLES_GROUP, groupedVehicles);
        openDetailFragment(FragmentFactory.Type.VEHICLE_DETAIL_STATS, dataToPass, VehicleDetailFragment.TAG);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Subscribe
    public void onVehiclesRefreshed(VehicleStatsRefreshedEvent event) {
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

        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private void sendDataToListView(final List<GroupedVehicleStats> vehiclestats) {
        if (getView() == null) {
            return;
        }

        VehicleStatsAdapter adapter = (VehicleStatsAdapter) getListAdapter();
        if (adapter == null) {
            adapter = new VehicleStatsAdapter(getActivity());
            setListAdapter(adapter);
        }
        adapter.setItems(vehiclestats);
    }
}
