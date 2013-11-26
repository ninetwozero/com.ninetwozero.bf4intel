package com.ninetwozero.bf4intel.ui.battlereport;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.network.ConnectionRequest;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.Result;
import com.ninetwozero.bf4intel.json.battlereports.SummaryBattleReport;

import java.util.List;

public class BattleReportListingFragment extends BaseLoadingListFragment {
    private int ID_LOADER = BattleReportListingFragment.class.hashCode();

    public BattleReportListingFragment() {
    }

    public static BattleReportListingFragment newInstance() {
        final BattleReportListingFragment fragment = new BattleReportListingFragment();
        fragment.setArguments(new Bundle());
        return fragment;
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
        final LoaderManager manager = getActivity().getSupportLoaderManager();
        if (manager.getLoader(ID_LOADER) == null) {
            manager.initLoader(ID_LOADER, getArguments(), this);
        } else {
            manager.restartLoader(ID_LOADER, getArguments(), this);
        }
    }

    @Override
    public Loader<Result> onCreateLoader(final int i, final Bundle bundle) {
        displayAsLoading(true);
        return new IntelLoader(
            getActivity(),
            new ConnectionRequest(
                UrlFactory.build(
                    UrlFactory.Type.BATTLE_REPORT_LISTING,
                    bundle.getString(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }

    @Override
    protected void onLoadSuccess(final String resultMessage) {
        final List<SummaryBattleReport> reports = fromJsonArray(resultMessage, SummaryBattleReport.class, "gameReports");
        sendDataToListView(reports);
        displayAsLoading(false);
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
        updateActionBar(getActivity(), "BATTLE REPORTS", R.drawable.ic_actionbar_feed);
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

    private void sendDataToListView(final List<SummaryBattleReport> reports) {
        final String personaId = getArguments().getString(Keys.Soldier.ID);
        BattleReportAdapter reportAdapter = (BattleReportAdapter) getListAdapter();
        if (reportAdapter == null) {
            reportAdapter = new BattleReportAdapter(getActivity(), personaId);
        }
        reportAdapter.setItems(reports);
        setListAdapter(reportAdapter);
    }

}
