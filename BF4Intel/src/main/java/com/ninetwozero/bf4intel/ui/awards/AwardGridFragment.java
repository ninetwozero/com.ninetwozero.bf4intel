package com.ninetwozero.bf4intel.ui.awards;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
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
    public static final String AWARD_SORT_MODE = "awardSortMode";
    private static final String AWARDS_AB_SUBTITLE = "awards_ab_subtitle";
    private static final String AWARD_SORT_MODE_CATEGORY = "awardSortModeCategory";
    private GridView gridView;

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
        setupSwipeRefreshLayout(view);
        setupGrid(view);
    }

    private void setupGrid(final View view) {
        if (view == null) {
            return;
        }

        final View emptyView = view.findViewById(android.R.id.empty);
        setCustomEmptyText(emptyView, R.string.empty_text_awards);

        final GridView gridView = (GridView) view.findViewById(R.id.assignments_grid);
        gridView.setOnItemClickListener(this);
        gridView.setEmptyView(emptyView);

    }

    private void sendDataToGridView(final View view, List<Award> awards) {
        gridView = (GridView) view.findViewById(R.id.assignments_grid);
        AwardsAdapter adapter = (AwardsAdapter) gridView.getAdapter();
        if (adapter == null) {
            adapter = new AwardsAdapter(getActivity());
            gridView.setAdapter(adapter);
        }
        adapter.setItems(awards);

        if (sharedPreferences.getInt(AWARD_SORT_MODE, 0) == SortMode.CATEGORIZED.ordinal()) {
            adapter.getFilter().filter(sharedPreferences.getString(AWARD_SORT_MODE_CATEGORY, ""));
        }

        actionBarSetSubtitleFromSharedPref(AWARDS_AB_SUBTITLE, R.string.label_sort_all);
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
        inflater.inflate(R.menu.filter_sort, menu);

        sortTitleResources = getResourceStringArray(R.array.ab_sort_menus);
        addMenuProviderFor(R.id.ab_action_sort, menu, sortTitleResources);

        filterTitleResources = getResourceStringArray(R.array.ab_award_filter_menu);
        addMenuProviderFor(R.id.ab_action_filter, menu, filterTitleResources);
        sortingKeys = new String[]{"kits", "gamemode", "weapon", "vehicles", "team"};
    }


    @Override
    protected void handleFilterRequest(final String category, final String subtitleResString) {
        setActionBarSubTitle(subtitleResString);
        final View view = getView();
        if (view == null) {
            return;
        }

        final AwardsAdapter adapter = (AwardsAdapter) gridView.getAdapter();
        if (adapter == null) {
            return;
        }
        adapter.getFilter().filter(category);

        sharedPreferences.edit()
            .putInt(AWARD_SORT_MODE, SortMode.CATEGORIZED.ordinal())
            .putString(AWARD_SORT_MODE_CATEGORY, category)
            .putString(AWARDS_AB_SUBTITLE, subtitleResString)
            .commit();
    }

    @Override
    protected void handleSortingRequest(SortMode sortMode, final String subtitleResString) {
        setActionBarSubTitle(subtitleResString);
        sharedPreferences.edit().putInt(AWARD_SORT_MODE, sortMode.ordinal())
            .putString(AWARDS_AB_SUBTITLE, subtitleResString)
            .putString(AWARD_SORT_MODE_CATEGORY, "")
            .commit();
        BusProvider.getInstance().post(new RefreshEvent());
    }
}
