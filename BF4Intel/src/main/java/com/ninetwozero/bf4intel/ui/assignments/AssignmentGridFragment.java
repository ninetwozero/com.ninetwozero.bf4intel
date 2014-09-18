package com.ninetwozero.bf4intel.ui.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.database.dao.assignments.AssignmentSorter;
import com.ninetwozero.bf4intel.database.dao.assignments.AssignmentsDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.events.assignments.AssignmentsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.SortedAssignmentContainer;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.AssignmentService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class AssignmentGridFragment
    extends BaseLoadingFragment
    implements AdapterView.OnItemClickListener {
    final Map<String, String> expansionNumberMapping = new HashMap<String, String>() {
        {
            put("xp0", "524288");
            put("xp1", "1048576");
            put("ghost1", "1048576");
            put("xp2", "2097152");
            put("ghost2", "2097152");
            put("xp3", "4194304");
            put("xp4", "8388608");
        }
    };

    public static final String ASSIGNMENT_SORT_MODE = "assignmentSortMode";
    private static final String ASSIGNMENTS_AB_SUBTITLE = "assignments_ab_subtitle";
    private static final String ASSIGNMENT_SORT_MODE_CATEGORY = "assignmentSortModeCategory";
    private Map<String, List<Long>> expansionMap;
    private GridView gridView;

    public static AssignmentGridFragment newInstance(final Bundle data) {
        final AssignmentGridFragment fragment = new AssignmentGridFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
            AssignmentsDAO.class,
            "SELECT * " +
                "FROM " + AssignmentsDAO.TABLE_NAME + " " +
                "WHERE soldierId = ? AND platformId = ? AND version = ?",
            arguments.getString(Keys.Soldier.ID, ""),
            arguments.getInt(Keys.Soldier.PLATFORM, 0),
            BuildConfig.VERSION_CODE
        ).getAsync(
            getLoaderManager(),
            new OneQuery.ResultHandler<AssignmentsDAO>() {
                @Override
                public boolean handleResult(AssignmentsDAO assignmentsDAO) {
                    if (assignmentsDAO == null) {
                        startLoadingData(false);
                        return true;
                    }

                    final SortedAssignmentContainer container = assignmentsDAO.getSortedAssignmentContainer();
                    expansionMap = container.getExpansions();
                    sendDataToGridView(view, container.getItems());
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

        final Intent intent = new Intent(getActivity(), AssignmentService.class);
        intent.putExtra(AssignmentService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Subscribe
    public void onAssignmentsRefreshed(AssignmentsRefreshedEvent event) {
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

        gridView = (GridView) view.findViewById(R.id.assignments_grid);
        gridView.setOnItemClickListener(this);
        gridView.setEmptyView(emptyView);

        setCustomEmptyText(emptyView, R.string.empty_text_assignments);
    }

    private void sendDataToGridView(final View view, List<Assignment> assignments) {
        AssignmentsAdapter adapter = (AssignmentsAdapter) gridView.getAdapter();
        if (adapter == null) {
            adapter = new AssignmentsAdapter(getActivity());
            gridView.setAdapter(adapter);
        }
        adapter.setItems(assignments);

        if (sharedPreferences.getInt(ASSIGNMENT_SORT_MODE, 0) == SortMode.CATEGORIZED.ordinal()) {
            adapter.getFilter().filter(sharedPreferences.getString(ASSIGNMENT_SORT_MODE_CATEGORY, ""));
        }

        actionBarSetSubtitleFromSharedPref(ASSIGNMENTS_AB_SUBTITLE, R.string.label_sort_all);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Assignment assignment = ((AssignmentsAdapter) adapterView.getAdapter()).getItem(i);
        final Bundle dataToPass = getArgumentsBundle();
        dataToPass.putSerializable(AssignmentDetailFragment.INTENT_ASSIGNMENT, assignment);
        dataToPass.putBoolean(
            AssignmentDetailFragment.INTENT_USER_HAS_EXPANSION, fetchExpansionStatus(assignment)
        );
        openDetailFragment(FragmentFactory.Type.SOLDIER_ASSIGNMENT_DETAILS, dataToPass, AssignmentDetailFragment.TAG);
    }

    private boolean fetchExpansionStatus(Assignment assignment) {
        final String key = expansionNumberMapping.get(assignment.getAward().getExpansionPack());
        if (expansionMap.containsKey(key)) {
            for (long value : expansionMap.get(key)) {
                if (value > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.filter_sort, menu);

        sortTitleResources = getResourceStringArray(R.array.ab_sort_menus);
        addMenuProviderFor(R.id.ab_action_sort, menu, sortTitleResources);

        filterTitleResources = getResourceStringArray(R.array.ab_assignment_filter_menu);
        addMenuProviderFor(R.id.ab_action_filter, menu, filterTitleResources);

        sortingKeys = AssignmentSorter.ASSIGNMENT_TYPE.toArray(new String[AssignmentSorter.ASSIGNMENT_TYPE.size()]);
    }

    @Override
    protected void handleFilterRequest(final String category, final String subtitleResString) {
        setActionBarSubTitle(subtitleResString);
        final View view = getView();
        if (view == null) {
            return;
        }

        final AssignmentsAdapter adapter = (AssignmentsAdapter) gridView.getAdapter();
        if (adapter == null) {
            return;
        }
        adapter.getFilter().filter(category);

        sharedPreferences.edit()
            .putInt(ASSIGNMENT_SORT_MODE, SortMode.CATEGORIZED.ordinal())
            .putString(ASSIGNMENT_SORT_MODE_CATEGORY, category)
            .putString(ASSIGNMENTS_AB_SUBTITLE, subtitleResString)
            .commit();
    }

    @Override
    protected void handleSortingRequest(SortMode sortMode, final String subtitleResString) {
        setActionBarSubTitle(subtitleResString);
        sharedPreferences.edit().putInt(ASSIGNMENT_SORT_MODE, sortMode.ordinal())
            .putString(ASSIGNMENTS_AB_SUBTITLE, subtitleResString)
            .putString(ASSIGNMENT_SORT_MODE_CATEGORY, "")
            .commit();
        BusProvider.getInstance().post(new RefreshEvent());
    }
}
