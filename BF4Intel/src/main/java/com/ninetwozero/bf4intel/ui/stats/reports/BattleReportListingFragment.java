package com.ninetwozero.bf4intel.ui.stats.reports;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.BaseListItem;
import com.ninetwozero.bf4intel.ui.SimpleListAdapter;
import com.ninetwozero.bf4intel.ui.SimpleListHeader;
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

    @Override
    protected void startLoadingData() {
        getLoaderManager().initLoader(ID_LOADER, getArguments(), this);
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
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        if (loader.getId() == ID_LOADER) {
            JsonObject baseObject = extractFromJson(resultMessage, false);
            if ((baseObject.get("statsTemplate").getAsString().equals("common.warsawerror"))) {
                ((TextView) getView().findViewById(android.R.id.empty)).setText(R.string.msg_error_private_user);
            } else {
                final BattleReportStatistics statistics = gson.fromJson(baseObject, BattleReportStatistics.class);
                sendDataToListView(statistics);
            }
        }
        showLoadingState(false);
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
        List<BaseListItem> itemList = new ArrayList<BaseListItem>();
        if(statistics.getFavoriteReports().size() > 0) {
            itemList.add(new SimpleListHeader(R.string.header_favorite_battlereport));
            itemList.addAll(buildBaseItemList(new ArrayList<GameReport>(statistics.getFavoriteReports()), statistics.getSoldierId()));
        }
        itemList.add(new SimpleListHeader(R.string.header_latest_battlereport));
        itemList.addAll(buildBaseItemList(new ArrayList<GameReport>(statistics.getStatsGameReports()), statistics.getSoldierId()));
        SimpleListAdapter adapter = new SimpleListAdapter(getActivity(), itemList);
        setListAdapter(adapter);
    }

    private List<BaseListItem> buildBaseItemList(final List<GameReport> reports, final int soldierId) {
        List<BaseListItem> itemsList = new ArrayList<BaseListItem>();
        for (GameReport report : reports) {
            itemsList.add(new BattleReportItem(report, soldierId, getActivity()));
        }
        return itemsList;
    }
}
