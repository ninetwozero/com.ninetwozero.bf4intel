package com.ninetwozero.bf4intel.ui.stats.reports;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.events.stats.reports.BattleReportsRefreshedEvent;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.stats.reports.BattleReportService;
import com.ninetwozero.bf4intel.ui.BaseListItem;
import com.ninetwozero.bf4intel.ui.SimpleListAdapter;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

public class BattleReportListingFragment extends BaseLoadingListFragment {
    public BattleReportListingFragment() {
    }

    public static BattleReportListingFragment newInstance(final Bundle data) {
        final BattleReportListingFragment fragment = new BattleReportListingFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = inflater.inflate(R.layout.generic_list, parent, false);
        initialize(view);
        return view;
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        onRefreshEventReceived(event);
    }

    @Subscribe
    public void onBattleReportsRefreshed(BattleReportsRefreshedEvent event) {
        List<BaseListItem> items = event.getItems();
        if (items == null) {
            setCustomEmptyText(
                getView(),
                String.format(
                    getString(R.string.msg_error_private_user),
                    getArguments().getString(Keys.Soldier.NAME)
                )
            );
        } else {
            sendDataToListView(event.getItems());
        }
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

        final Intent intent = new Intent(getActivity(), BattleReportService.class);
        intent.putExtra(BattleReportService.SOLDIER_BUNDLE, getArgumentsBundle());
        getActivity().startService(intent);
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        // TODO: Not sure what to display in the next view, website is crammed!

        /*
        final Fragment itemFragment = fragmentManager.findFragmentByTag("BattleReportFragment");
        if (itemFragment != null && itemFragment instanceof BattleReportFragment) {
            ((BattleReportFragment) itemFragment).loadReport(id);
        } else {
            final Bundle data = new Bundle();
            data.putLong(BattleReportFragment.ID, id);

            final Intent intent = new Intent(getActivity(), SingleFragmentActivity.class)
                .putExtra(SingleFragmentActivity.INTENT_FRAGMENT_DATA, data)
                .putExtra(SingleFragmentActivity.INTENT_FRAGMENT_TYPE, FragmentFactory.Type.BATTLE_REPORT.ordinal());
            startActivity(intent);
        }
        */
        showToast(R.string.toast_unimplemented_functionality);
    }

    private void initialize(final View view) {
        setupErrorMessage(view);
        setupSwipeRefreshLayout(view);
        setupListView(view);
    }

    @Override
    protected void setupListView(final View view) {
        super.setupListView(view);
        setCustomEmptyText(view, R.string.empty_text_battle_reports);
    }

    private void sendDataToListView(final List<BaseListItem> statistics) {
        setListAdapter(new SimpleListAdapter(getActivity(), statistics));
    }
}
