package com.ninetwozero.bf4intel.ui.stats.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.details.StatsDetails;
import com.ninetwozero.bf4intel.model.stats.details.StatsDetailsGrouped;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.SimpleListAdapter;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

public class DetailsStatsFragment extends BaseLoadingListFragment {
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

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        showLoadingState(true);
        requestQueue.add(fetchRequest(getArguments()));
    }

    private Request<StatsDetailsGrouped> fetchRequest(Bundle bundle) {
        return new SimpleGetRequest<StatsDetailsGrouped>(
            UrlFactory.buildDetailsURL(
                bundle.getString(Keys.Soldier.ID),
                bundle.getInt(Keys.Soldier.PLATFORM)
            ),
            this
        ) {
            @Override
            protected StatsDetailsGrouped doParse(String json) {
                final StatsDetails.GeneralStats details = fromJson(json, StatsDetails.class).getGeneralStats();
                final StatsDetailsGrouped stats = new StatsDetailsGrouped(details);
                return stats;
            }

            @Override
            protected void deliverResponse(StatsDetailsGrouped response) {
                sendDataToListView(response);
                showLoadingState(false);
            }
        };
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        showToast(error.getMessage());
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
        setListAdapter(new SimpleListAdapter(getActivity(), stats.getDetails()));
    }
}
