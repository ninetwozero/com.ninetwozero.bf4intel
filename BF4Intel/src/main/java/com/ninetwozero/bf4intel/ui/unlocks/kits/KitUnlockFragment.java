package com.ninetwozero.bf4intel.ui.unlocks.kits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.dao.unlocks.kits.KitUnlockDAO;
import com.ninetwozero.bf4intel.events.unlocks.kits.KitUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.unlocks.kits.KitUnlockService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;
import com.squareup.otto.Subscribe;

import java.util.List;
import java.util.Map;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class KitUnlockFragment extends BaseUnlockFragment {
    public static KitUnlockFragment newInstance(final Bundle data) {
        final KitUnlockFragment fragment = new KitUnlockFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle arguments = getArgumentsBundle();
        Query.one(
            KitUnlockDAO.class,
            "SELECT * " +
                "FROM " + KitUnlockDAO.TABLE_NAME + " " +
                "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<KitUnlockDAO>() {
                @Override
                public boolean handleResult(KitUnlockDAO kitUnlockDAO) {
                    final View view = getView();
                    if (view == null || kitUnlockDAO == null) {
                        startLoadingData();
                        return true;
                    }

                    sendDataToListView(kitUnlockDAO.getKitUnlocks().getSortedKitUnlockMap());
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

        final Intent intent = new Intent(getActivity(), KitUnlockService.class);
        intent.putExtra(KitUnlockService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int i, long l) {
        // TODO: Open details
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        startLoadingData();
    }

    @Subscribe
    public void onKitUnlocksRefreshed(KitUnlocksRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    private void sendDataToListView(final Map<String, List<KitItemUnlockContainer>> unlockMap) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }
        listView.setAdapter(new KitUnlockAdapter(getActivity(), unlockMap));
    }
}
