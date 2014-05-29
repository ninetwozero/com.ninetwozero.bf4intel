package com.ninetwozero.bf4intel.ui.awards;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.database.dao.awards.AwardsDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.events.awards.AwardsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.json.awards.Award;
import com.ninetwozero.bf4intel.json.awards.SortedAwardContainer;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.AwardService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.List;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class AwardGridFragment
    extends BaseLoadingFragment
    implements AdapterView.OnItemClickListener {
    public static final String KEY_SORT_MODE = "awardSortMode";

    public static AwardGridFragment newInstance(final Bundle data) {
        final AwardGridFragment fragment = new AwardGridFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_card_grid, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle arguments = getArgumentsBundle();
        Query.one(
            AwardsDAO.class,
            "SELECT * " +
                "FROM " + AwardsDAO.TABLE_NAME + " " +
                "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<AwardsDAO>() {
                @Override
                public boolean handleResult(AwardsDAO awardsDao) {
                    if (awardsDao == null) {
                        startLoadingData();
                        return true;
                    }

                    final SortedAwardContainer container = awardsDao.getSortedAwardContainer();
                    sendDataToGridView(view, container.getItems());
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

        final Intent intent = new Intent(getActivity(), AwardService.class);
        intent.putExtra(AwardService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Subscribe
    public void onAwardsRefreshed(AwardsRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    private void initialize(View view) {
        setupErrorMessage(view);
        setupGrid(view);
    }

    private void setupGrid(final View view) {
        if (view == null) {
            return;
        }

        final GridView gridView = (GridView) view.findViewById(R.id.assignments_grid);
        gridView.setOnItemClickListener(this);
    }

    private void sendDataToGridView(final View view, List<Award> awards) {
        final GridView gridView = (GridView) view.findViewById(R.id.assignments_grid);
        AwardsAdapter adapter = (AwardsAdapter) gridView.getAdapter();
        if (adapter == null) {
            adapter = new AwardsAdapter(getActivity());
            gridView.setAdapter(adapter);
        }
        adapter.setItems(awards);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Award award = ((AwardsAdapter) adapterView.getAdapter()).getItem(i);
        final Bundle dataToPass = getArgumentsBundle();
        dataToPass.putSerializable(AwardDetailFragment.INTENT_AWARD, award);
        openDetailFragment(FragmentFactory.Type.SOLDIER_AWARD_DETAILS, dataToPass, AwardDetailFragment.TAG);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_awards, menu);

        setupSortModeMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.ab_action_sort_all:
                handleSortingRequest(SortMode.ALL);
                return true;
            case R.id.ab_action_sort_cat_game_modes:
            case R.id.ab_action_sort_cat_kits:
            case R.id.ab_action_sort_cat_weapons:
            case R.id.ab_action_sort_cat_vehicles:
            case R.id.ab_action_sort_cat_team:
                handleFilterRequest(item.getItemId());
                return true;

            case R.id.ab_action_sort_progress:
                handleSortingRequest(SortMode.PROGRESS);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleFilterRequest(final int itemId) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final GridView gridView = (GridView) view.findViewById(R.id.assignments_grid);
        final AwardsAdapter adapter = (AwardsAdapter) gridView.getAdapter();
        adapter.getFilter().filter(fetchKeyForCategoryItem(itemId));
    }

    private CharSequence fetchKeyForCategoryItem(final int itemId) {
        switch (itemId) {
            case R.id.ab_action_sort_cat_game_modes:
                return "gamemode";
            case R.id.ab_action_sort_cat_kits:
                return "kits";
            case R.id.ab_action_sort_cat_weapons:
                return "weapon";
            case R.id.ab_action_sort_cat_vehicles:
                return "vehicles";
            case R.id.ab_action_sort_cat_team:
                return "team";
            default:
                return "";
        }
    }

    private void handleSortingRequest(SortMode sortMode) {
        sharedPreferences.edit().putInt(KEY_SORT_MODE, sortMode.ordinal()).commit();
        BusProvider.getInstance().post(new RefreshEvent());
    }

    private void setupSortModeMenu(Menu menu) {
        final SortMode mode = SortMode.fromOrdinal(sharedPreferences.getInt(KEY_SORT_MODE, 0));
        switch (mode) {
            case CATEGORIZED:
                menu.findItem(R.id.ab_action_sort_categorized).setChecked(true);
                break;
            case PROGRESS:
                menu.findItem(R.id.ab_action_sort_progress).setChecked(true);
                break;
            default:
                menu.findItem(R.id.ab_action_sort_categorized).setChecked(true);
                break;
        }
    }
}
