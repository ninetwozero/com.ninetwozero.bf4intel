package com.ninetwozero.bf4intel.ui.stats.reports;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseItem;
import com.ninetwozero.bf4intel.base.adapter.BaseListAdapter;
import com.ninetwozero.bf4intel.base.adapter.BaseListHeader;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.reports.BattleReportStatistics;
import com.ninetwozero.bf4intel.json.stats.reports.GameReport;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.ArrayList;
import java.util.List;

public class BattleReportListingFragment extends BaseLoadingListFragment {
    private int ID_LOADER = 2300;

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

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
    }

    /*
            TODO: We need to figure out a way to not download new data upon rotating the screen!
        */
    @Override
    protected void startLoadingData() {
        getLoaderManager().restartLoader(ID_LOADER, getArguments(), this);
    }

    @Override
    public Loader<Result> onCreateLoader(final int i, final Bundle bundle) {
        showLoadingState(true);
        return new IntelLoader(
            getActivity(),
            new SimpleGetRequest(
                UrlFactory.buildBattleReportsURL(
                    bundle.getLong(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }

    @Override
    public void onLoadFinished(Loader<Result> resultLoader, Result result) {
        if (result == Result.SUCCESS) {
            onLoadSuccess(result.getResultMessage());
        } else {
            onLoadFailure(result.getResultMessage());
        }
    }

    @Override
    protected void onLoadSuccess(final String resultMessage) {
        BattleReportStatistics statistics = fromJson(resultMessage, BattleReportStatistics.class);
        sendDataToListView(statistics);
        showLoadingState(false);
    }

    @Override
    protected void onLoadFailure(final String resultMessage) {
        showToast(resultMessage);
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
        showToast("Unimplemented functionality.");
    }

    private void initialize(final View view) {
        setupListView(view);
    }

    private void setupListView(final View view) {
        if (view == null) {
            return;
        }

        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        final TextView emptyView = (TextView) view.findViewById(android.R.id.empty);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        emptyView.setText(R.string.msg_no_battlereports);
    }

    private void sendDataToListView(final BattleReportStatistics statistics) {
        List<BaseItem> itemList = new ArrayList<BaseItem>();
        if(statistics.getFavoriteReports().size() > 0) {
            itemList.add(new BaseListHeader(R.string.header_favorite_battlereport));
            itemList.addAll(buildBaseItemList(new ArrayList<GameReport>(statistics.getFavoriteReports()), statistics.getSoldierId()));
        }
        itemList.add(new BaseListHeader(R.string.header_latest_battlereport));
        itemList.addAll(buildBaseItemList(new ArrayList<GameReport>(statistics.getStatsGameReports()), statistics.getSoldierId()));
        BaseListAdapter adapter = new BaseListAdapter(getActivity(), itemList);
        setListAdapter(adapter);
    }

    private List<BaseItem> buildBaseItemList(final List<GameReport> reports, final int soldierId) {
        List<BaseItem> itemsList = new ArrayList<BaseItem>();
        for (GameReport report : reports) {
            itemsList.add(new BattleReportItem(report, soldierId, getActivity()));
        }
        return itemsList;
    }
}
