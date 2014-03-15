package com.ninetwozero.bf4intel.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
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
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.network.SimplePostRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.resources.maps.WebsiteErrorMessageMap;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.List;

public class NewsListingFragment extends BaseLoadingListFragment {
    public static final String ID = "articleId";
    
    private static final int ID_REQUEST_REFRESH_LIST = 4000;
    private static final int ID_REQUEST_HOOAH = 4100;
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
        initialize();
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
        doRequest(ID_REQUEST_REFRESH_LIST, getArguments());
    }

    private void doRequest(int requestId, Bundle bundle) {
        switch(requestId) {
            case ID_REQUEST_REFRESH_LIST:
                showLoadingState(true);
                requestQueue.add(fetchRequestForRefresh(bundle));
                break;

            case ID_REQUEST_HOOAH:
                requestQueue.add(fetchRequestForHooah(bundle));
                break;

            default:
                throw new IllegalArgumentException("No request matching " + requestId);
        }
    }

    private Request<NewsListRequest> fetchRequestForRefresh(Bundle bundle) {
        return new SimpleGetRequest<NewsListRequest>(
            UrlFactory.buildNewsListingURL(pageId),
            BaseSimpleRequest.RequestType.FROM_NAVIGATION,
            this
        ) {
            @Override
            protected NewsListRequest doParse(String json) {
                final Gson gson = new Gson();
                final JsonParser parser = new JsonParser();
                final JsonElement rootObject = parser.parse(json).getAsJsonObject().get("context");
                final NewsListRequest container = gson.fromJson(rootObject, NewsListRequest.class);
                return container;
            }

            @Override
            protected void deliverResponse(NewsListRequest response) {
                sendItemsToListView(response.getArticles());
                showLoadingState(false);
            }
        };
    }

    private Request<Object> fetchRequestForHooah(Bundle bundle) {
        return new SimplePostRequest<Object>(
            UrlFactory.buildNewsArticleHooahURL(bundle.getString(ID)),
            bundle,
            this
        ) {
            @Override
            protected Object doParse(String json) {
                return json;
            }

            @Override
            protected void deliverResponse(Object response) {
                startLoadingData();
            }
        };
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        showToast(WebsiteErrorMessageMap.get(error.getMessage()));
    }

    @Subscribe
    public void onUserPressedHooah(final HooahToggleRequest request) {
        final Bundle data = new Bundle();
        data.putString(Keys.CHECKSUM, SessionStore.getChecksum());
        data.putString(ID, request.getId());

        doRequest(ID_REQUEST_HOOAH, data);
    }

    private void initialize() {
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
}
