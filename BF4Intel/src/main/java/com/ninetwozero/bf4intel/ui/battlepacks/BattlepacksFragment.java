package com.ninetwozero.bf4intel.ui.battlepacks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.events.battlefeed.BattleFeedRefreshedEvent;
import com.ninetwozero.bf4intel.services.battlepacks.BattlepacksService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

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
        startLoadingData(false);
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
}
