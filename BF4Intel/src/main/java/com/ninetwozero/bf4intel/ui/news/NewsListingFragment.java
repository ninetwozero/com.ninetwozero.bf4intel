package com.ninetwozero.bf4intel.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.datatypes.HooahToggleRequest;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.news.NewsArticle;
import com.ninetwozero.bf4intel.json.news.NewsListRequest;
import com.ninetwozero.bf4intel.network.BaseSimpleRequest;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.network.SimplePostRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.resources.maps.WebsiteErrorMessageMap;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;
import com.squareup.otto.Subscribe;

import java.util.List;

public class NewsListingFragment extends BaseLoadingListFragment {
    public static final String TAG = "NewsListingFragment";
    public static final String ID = "articleId";
    
    private static final int ID_LOADER_REFRESH_LIST = 4000;
    private static final int ID_LOADER_HOOAH = 4100;
    private static final int pageId = 1;

    public NewsListingFragment() {
    }

    public static NewsListingFragment newInstance(final Bundle data) {
        final NewsListingFragment fragment = new NewsListingFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = layoutInflater.inflate(R.layout.fragment_news_listing, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        final Bundle data = new Bundle();
        data.putString(NewsArticleFragment.ID, String.valueOf(id));

        startActivity(
            new Intent(getActivity(), SingleFragmentActivity.class).putExtra(
                SingleFragmentActivity.INTENT_FRAGMENT_TYPE, FragmentFactory.Type.NEWS_ITEM.ordinal()
            ).putExtra(
                SingleFragmentActivity.INTENT_FRAGMENT_DATA, data
            )
        );
    }

    @Override
    protected void startLoadingData() {
        getLoaderManager().restartLoader(ID_LOADER_REFRESH_LIST, getArguments(), this);
    }

    @Override
    public Loader<Result> onCreateLoader(int loaderId, Bundle bundle) {
        BaseSimpleRequest request;
        switch(loaderId) {
            case ID_LOADER_REFRESH_LIST:
                showLoadingState(true);
                request = new SimpleGetRequest(
                    UrlFactory.buildNewsListingURL(pageId),
                    BaseSimpleRequest.RequestType.FROM_NAVIGATION
                );
                break;

            case ID_LOADER_HOOAH:
                request = new SimplePostRequest(
                    UrlFactory.buildNewsArticleHooahURL(bundle.getString(ID)),
                    bundle
                );
                break;

            default:
                throw new IllegalArgumentException("No loader matching " + loaderId);
        }
        return new IntelLoader(getActivity(), request);
    }

    @Override
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        if (loader.getId() == ID_LOADER_REFRESH_LIST) {
            onListRefreshed(resultMessage);
        } else if (loader.getId() == ID_LOADER_HOOAH) {
            startLoadingData();
        }
    }

    @Override
    protected void onLoadFailure(final Loader loader, final String resultMessage) {
        // TODO: Display error message in bar below ActionBar
        showToast(WebsiteErrorMessageMap.get(resultMessage));
    }

    @Subscribe
    public void onUserPressedHooah(final HooahToggleRequest request) {
        final Bundle data = new Bundle();
        data.putString(Keys.CHECKSUM, SessionStore.getChecksum());
        data.putString(ID, request.getId());

        getLoaderManager().restartLoader(ID_LOADER_HOOAH, data, this);
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), R.string.navigationdrawer_news);
    }

    private void sendItemsToListView(final List<NewsArticle> items) {
        final View parent = getView();
        if (parent == null) {
            return;
        }

        NewsListAdapter adapter = (NewsListAdapter) getListAdapter();
        if (adapter == null) {
            adapter = new NewsListAdapter(getActivity());
            setListAdapter(adapter);
        }
        adapter.setItems(items);
    }

    private void onListRefreshed(final String resultMessage) {
        final Gson gson = new Gson();
        final JsonParser parser = new JsonParser();
        final JsonElement rootObject = parser.parse(resultMessage).getAsJsonObject().get("context");
        final NewsListRequest container = gson.fromJson(rootObject, NewsListRequest.class);

        sendItemsToListView(container.getArticles());
        showLoadingState(false);
    }
}
