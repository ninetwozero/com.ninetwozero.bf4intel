package com.ninetwozero.bf4intel.ui.awards;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.dao.awards.AwardsDAO;
import com.ninetwozero.bf4intel.events.awards.AwardsRefreshedEvent;
import com.ninetwozero.bf4intel.json.awards.Award;
import com.ninetwozero.bf4intel.json.awards.SortedAwardContainer;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.AwardService;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

import se.emilsjolander.sprinkles.OneQuery;
import se.emilsjolander.sprinkles.Query;

public class AwardGridFragment
    extends BaseLoadingFragment
    implements AdapterView.OnItemClickListener {

    public static AwardGridFragment newInstance(final Bundle data) {
        final AwardGridFragment fragment = new AwardGridFragment();
        fragment.setArguments(data);
        return fragment;
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_assignments, container, false);
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
                    final View view = getView();
                    if (view == null) {
                        return true;
                    } else if (awardsDao == null) {
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
        if (isReloading) {
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
        startLoadingData();
    }

    @Subscribe
    public void onAwardsRefreshed(AwardsRefreshedEvent event) {
        isReloading = false;
        showLoadingState(false);
    }

    private void initialize(View view) {
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
        // TODO: Open details
    }
}
