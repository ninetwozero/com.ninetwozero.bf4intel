package com.ninetwozero.bf4intel.ui.battlepacks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.database.dao.battlepacks.BattlepacksDAO;
import com.ninetwozero.bf4intel.events.battlefeed.BattleFeedRefreshedEvent;
import com.ninetwozero.bf4intel.json.battlepacks.Battlepacks;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.battlepacks.BattlepacksService;
import com.ninetwozero.bf4intel.ui.SimpleListAdapter;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class BattlepacksFragment extends BaseLoadingListFragment {

    public static BattlepacksFragment newInstance(final Bundle data) {
        final BattlepacksFragment fragment = new BattlepacksFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = inflater.inflate(R.layout.fragment_list_stats, parent, false);
        //initialize(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle arguments = getArgumentsBundle();
        Query.one(
            BattlepacksDAO.class,
            "SELECT * " +
                "FROM " + BattlepacksDAO.TABLE_NAME + " " +
                "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<BattlepacksDAO>() {
                @Override
                public boolean handleResult(BattlepacksDAO battlepacksDAO) {
                    if (battlepacksDAO == null) {
                        startLoadingData(false);
                        return true;
                    }

                    sendDataToListView(battlepacksDAO);
                    showLoadingState(false);
                    return true;
                }
            }
        );
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Subscribe
    public void onWeaponsRefreshed(BattleFeedRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData(true);
    }

    @Override
    protected void startLoadingData(boolean showLoading) {
        if (isReloading || !Bf4Intel.isConnectedToNetwork()) {
            return;
        }

        showLoadingState(showLoading);
        isReloading = true;

        final Intent intent = new Intent(getActivity(), BattlepacksService.class);
        intent.putExtra(BattlepacksService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    private void sendDataToListView(final BattlepacksDAO battlepacksDAO){
        setListAdapter(new SimpleListAdapter(getActivity(), battlepacksDAO.getBattlepacks(getActivity())));
    }
}
