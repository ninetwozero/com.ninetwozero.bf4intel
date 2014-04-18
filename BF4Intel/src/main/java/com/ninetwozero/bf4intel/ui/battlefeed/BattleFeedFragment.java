package com.ninetwozero.bf4intel.ui.battlefeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.events.battlefeed.BattleFeedRefreshedEvent;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.json.battlefeed.FeedItem;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.battlefeed.BattleFeedService;
import com.ninetwozero.bf4intel.services.news.NewsListingService;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

public class BattleFeedFragment extends BaseLoadingListFragment {
    public BattleFeedFragment() {
    }

    public static BattleFeedFragment newInstance(final Bundle data) {
        final BattleFeedFragment fragment = new BattleFeedFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = layoutInflater.inflate(R.layout.generic_list, parent, false);
        initialize(view);
        return view;
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        startLoadingData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_feed, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ab_action_new) {
            Intent intent = new Intent(getActivity(), SingleFragmentActivity.class);
            intent = intent.putExtra(SingleFragmentActivity.INTENT_FRAGMENT_DATA, new Bundle());
            intent = intent.putExtra(SingleFragmentActivity.INTENT_FRAGMENT_TYPE, FragmentFactory.Type.BATTLE_FEED_POSTING.ordinal());
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void startLoadingData() {
        if (isReloading) {
            return;
        }

        showLoadingState(true);
        isReloading = true;

        final int count = 0;
        final String userId = getArguments().getString(Keys.Profile.ID, "");

        final Intent intent = new Intent(getActivity(), BattleFeedService.class);
        intent.putExtra(BattleFeedService.INTENT_COUNT, count);
        intent.putExtra(BattleFeedService.INTENT_USERID, userId);
        getActivity().startService(intent);
    }

    @Subscribe
    public void onBattlefeedRefreshed(BattleFeedRefreshedEvent battleFeed) {
        sendDataToListView(battleFeed.getFeed().getFeedItems());
        showLoadingState(false);
        isReloading = false;
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "BATTLE FEED", R.drawable.ic_actionbar_feed);
        setupListView(view);
    }

    private void setupListView(final View view) {
        if (view == null) {
            return;
        }

        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        final TextView emptyView = (TextView) view.findViewById(android.R.id.empty);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        emptyView.setText(R.string.msg_empty_feed);
    }

    private void sendDataToListView(final List<FeedItem> feedItems) {
        BattleFeedAdapter battleFeedAdapter = (BattleFeedAdapter) getListAdapter();
        if (battleFeedAdapter == null) {
            battleFeedAdapter = new BattleFeedAdapter(getActivity());
            setListAdapter(battleFeedAdapter);
        }
        battleFeedAdapter.setItems(feedItems);
    }
}
