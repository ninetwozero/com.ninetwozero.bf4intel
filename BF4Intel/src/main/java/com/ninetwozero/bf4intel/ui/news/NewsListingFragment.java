package com.ninetwozero.bf4intel.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.R;
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
import com.ninetwozero.bf4intel.resources.maps.WebsiteErrorMessageMap;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;
import com.squareup.otto.Subscribe;

import java.util.List;

public class NewsListingFragment extends BaseLoadingListFragment {
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
            new Intent(getActivity(), SingleFragmentActivity.class)
                .putExtra(
                    SingleFragmentActivity.INTENT_FRAGMENT_TYPE, FragmentFactory.Type.NEWS_ITEM.ordinal()
                )
                .putExtra(SingleFragmentActivity.INTENT_FRAGMENT_DATA, data)
        );
    }

    @Override
    protected void startLoadingData() {
        getLoaderManager().restartLoader(ID_LOADER_REFRESH_LIST, getArguments(), this);
    }

    @Override
    public Loader<Result> onCreateLoader(int loaderId, Bundle bundle) {
        if (loaderId == ID_LOADER_REFRESH_LIST) {
            return new IntelLoader(
                getActivity(),
                new SimpleGetRequest(
                    UrlFactory.buildNewsListingURL(pageId),
                    BaseSimpleRequest.RequestType.FROM_NAVIGATION
                )
            );
        } else if (loaderId == ID_LOADER_HOOAH) {
            return new IntelLoader(
                getActivity(),
                new SimplePostRequest(
                    UrlFactory.buildNewsArticleHooahURL(bundle.getString("articleId")),
                    bundle
                )
            );
        }
        throw new IllegalArgumentException("No loader matching " + loaderId);
    }

    @Override
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        if (loader.getId() == ID_LOADER_REFRESH_LIST) {
            final Gson gson = new Gson();
            final JsonParser parser = new JsonParser();
            final JsonElement rootObject = parser.parse(resultMessage).getAsJsonObject().get("context");
            final NewsListRequest container = gson.fromJson(rootObject, NewsListRequest.class);

            sendItemsToListView(container.getArticles());
        } else if (loader.getId() == ID_LOADER_HOOAH) {
            // TODO: Refresh?
            Log.d(getClass().getSimpleName(), "[onLoadSuccess] resultMessage => " + resultMessage);
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
        data.putString("post-check-sum", "0xCAFEBABE");
        data.putString("articleId", request.getId());

        getLoaderManager().restartLoader(ID_LOADER_HOOAH, data, this);
    }

    private void initialize(final View view) {
        setupListView(view);
        updateActionBar(getActivity(), "NEWS");
    }

    private void setupListView(final View view) {
        if (view == null) {
            return;
        }

        // Do we need to setup?
    }

    private void sendItemsToListView(final List<NewsArticle> items) {
        NewsListAdapter adapter = (NewsListAdapter) getListAdapter();
        if (adapter == null) {
            adapter = new NewsListAdapter(getActivity());
            setListAdapter(adapter);
        }

        // When we have some sort of "load more pages", this is how we append them: adapter.appendItems(items);
        adapter.setItems(items);
    }
}
