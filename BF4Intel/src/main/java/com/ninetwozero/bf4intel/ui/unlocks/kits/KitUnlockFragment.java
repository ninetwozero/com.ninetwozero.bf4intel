package com.ninetwozero.bf4intel.ui.unlocks.kits;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.database.dao.unlocks.kits.KitUnlockDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.kits.KitUnlockMapSorter;
import com.ninetwozero.bf4intel.events.unlocks.kits.KitUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.unlocks.kits.KitUnlockService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.ui.unlocks.BaseUnlockFragment;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.List;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class KitUnlockFragment extends BaseUnlockFragment {
    private static final String KIT_SORT_MODE = "kitSortMode";
    private static final String KIT_AB_SUBTITLE = "kit_ab_subtitle";
    private static final String KIT_SORT_MODE_CATEGORY = "kitSortModeCategory";

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
                    if (kitUnlockDAO == null) {
                        startLoadingData();
                        return true;
                    }

                    sendDataToListView(kitUnlockDAO.getKitUnlocks().getSortedUnlocks());
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

        final Intent intent = new Intent(getActivity(), KitUnlockService.class);
        intent.putExtra(KitUnlockService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.filter_sort, menu);

        sortTitleResources = getResourceStringArray(R.array.ab_sort_menus);
        addMenuProviderFor(R.id.ab_action_sort, menu, sortTitleResources);

        filterTitleResources = getResourceStringArray(R.array.ab_kit_unlocks_filter_menu);
        addMenuProviderFor(R.id.ab_action_filter, menu, filterTitleResources);

        sortingKeys = KitUnlockMapSorter.CATEGORY_ORDER;
    }

    @Override
    protected void handleFilterRequest(String category, String subtitleResString) {
        setActionBarSubTitle(subtitleResString);
        if (getView() == null) {
            return;
        }

        final KitUnlockAdapter adapter = (KitUnlockAdapter) gridView.getAdapter();
        adapter.getFilter().filter(category);
        sharedPreferences.edit()
            .putInt(KIT_SORT_MODE, SortMode.CATEGORIZED.ordinal())
            .putString(KIT_SORT_MODE_CATEGORY, category)
            .putString(KIT_AB_SUBTITLE, subtitleResString)
            .commit();
    }

    @Override
    protected void handleSortingRequest(SortMode sortMode, String subtitleResString) {
        setActionBarSubTitle(subtitleResString);
        sharedPreferences.edit()
            .putInt(KIT_SORT_MODE, sortMode.ordinal())
            .putString(KIT_AB_SUBTITLE, subtitleResString)
            .putString(KIT_SORT_MODE_CATEGORY, "")
            .commit();
        BusProvider.getInstance().post(new RefreshEvent());
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Subscribe
    public void onKitUnlocksRefreshed(KitUnlocksRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    private void sendDataToListView(final List<KitItemUnlockContainer> unlocks) {
        KitUnlockAdapter adapter = (KitUnlockAdapter) gridView.getAdapter();
        if (adapter == null) {
            adapter = new KitUnlockAdapter(getActivity());
            gridView.setAdapter(adapter);
        }
        adapter.setItems(unlocks);

        if (sharedPreferences.getInt(KIT_SORT_MODE, 0) == SortMode.CATEGORIZED.ordinal()) {
            adapter.getFilter().filter(sharedPreferences.getString(KIT_SORT_MODE_CATEGORY, ""));
        }
        actionBarSetSubtitleFromSharedPref(KIT_AB_SUBTITLE, R.string.label_sort_all);
    }
}
