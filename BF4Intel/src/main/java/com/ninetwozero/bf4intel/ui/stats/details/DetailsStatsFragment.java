package com.ninetwozero.bf4intel.ui.stats.details;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.stats.SimpleStatsListAdapter;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.details.StatsDetails;
import com.ninetwozero.bf4intel.model.stats.details.StatsDetailsGrouped;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.Result;

public class DetailsStatsFragment extends BaseLoadingListFragment {

    private static final int ID_LOADER = 2400;

    public static DetailsStatsFragment newInstance(final Bundle bundle) {
        final DetailsStatsFragment fragment = new DetailsStatsFragment();
        fragment.setArguments(bundle);
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
                UrlFactory.buildDetailsURL(
                    bundle.getLong(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }

    @Override
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        final StatsDetails.GeneralStats details = fromJson(resultMessage, StatsDetails.class).getGeneralStats();
        final StatsDetailsGrouped stats = new StatsDetailsGrouped(details);

        sendDataToListView(stats);
        showLoadingState(false);
    }

    @Override
    protected void onLoadFailure(final Loader loader, final String resultMessage) {
        super.onLoadFailure(loader, resultMessage);
        showToast(resultMessage);
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
        emptyView.setText(R.string.msg_no_stats_details);
    }

    private void sendDataToListView(final StatsDetailsGrouped stats) {
        setListAdapter(new SimpleStatsListAdapter(getActivity(), stats.getDetailsList()));
    }
}
