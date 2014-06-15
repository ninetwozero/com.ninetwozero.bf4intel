package com.ninetwozero.bf4intel.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.search.ProfileSearchResult;
import com.ninetwozero.bf4intel.json.search.ProfileSearchResults;
import com.ninetwozero.bf4intel.network.SimplePostRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.resources.maps.profile.PlatformStringMap;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.PersonaUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileSearchFragment extends BaseLoadingListFragment {
    public static final String INTENT_QUERY_STRING = "query_string";

    private String queryString;

    public static ProfileSearchFragment newInstance(final Bundle data) {
        final ProfileSearchFragment fragment = new ProfileSearchFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(Bundle icicle) {
        queryString = getArguments().getString(INTENT_QUERY_STRING, "");
        super.onCreate(icicle);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = layoutInflater.inflate(R.layout.fragment_profile_search, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoadingState(false);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        final MenuItem searchItem = menu.findItem(R.id.ab_action_search);
        Log.d("YOLO", "searchItem => " + searchItem);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        Log.d("YOLO", "searchView => " + searchView);
        if (searchView != null) {
            searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextChange(final String s) {
                        queryString = s;
                        return true;
                    }

                    @Override
                    public boolean onQueryTextSubmit(final String s) {
                        queryString = s;
                        startLoadingData();
                        return true;
                    }
                }
            );
            searchView.setIconifiedByDefault(true);
            searchView.setIconified(false);
            searchView.clearFocus();
            searchView.setQuery(queryString, true);
            searchItem.setVisible(true);
        }
    }

    @Override
    protected void startLoadingData() {
        if (isReloading || !Bf4Intel.isConnectedToNetwork()) {
            return;
        }

        final Bundle postData = new Bundle();
        if (queryString == null || queryString.length() < 3) {
            showToast(R.string.msg_search_error_length);
            return;
        }

        postData.putString("query", queryString);
        postData.putString(Keys.CHECKSUM, "0xCAFEBABE");

        showLoadingState(true);
        isReloading = true;

        // TODO: Service in the future?
        Bf4Intel.getRequestQueue().add(fetchRequestForSearch(postData));
    }

    @Override
    public void onListItemClick(final ListView l, final View v, final int position, final long id) {
        final Activity activity = getActivity();
        final ProfileSearchAdapter adapter = (ProfileSearchAdapter) getListAdapter();
        final ProfileSearchResult result = adapter.getItem(position);

        // Calling activity will be NULL when triggered from ActionBar
        if (activity.getCallingActivity() == null) {
            if (activity instanceof SearchActivity) {
                handleSelectionWhenCalledFromSearchActivity(result);
            } else {
                BusProvider.getInstance().post(result);
            }
        } else {
            activity.setResult(
                Activity.RESULT_OK,
                new Intent()
                    .putExtra(SearchActivity.RESULT_SEARCH_RESULT, result.getProfile())
                    .putExtra(SearchActivity.RESULT_SEARCH_RESULT_PLATFORM, PlatformStringMap.getIdFromName(result.getPlatform()))
            );
            activity.finish();
        }
    }

    private void handleSelectionWhenCalledFromSearchActivity(final ProfileSearchResult result) {
        final Profile profile = result.getProfile();
        final Bundle dataBundle = new Bundle();
        dataBundle.putString(Keys.Profile.ID, profile.getId());
        dataBundle.putString(Keys.Profile.USERNAME, profile.getUsername());
        dataBundle.putString(Keys.Profile.GRAVATAR_HASH, profile.getGravatarHash());

        final Intent intent = new Intent(getActivity(), SingleFragmentActivity.class)
            .putExtra(SingleFragmentActivity.INTENT_FRAGMENT_DATA, dataBundle)
            .putExtra(
                SingleFragmentActivity.INTENT_FRAGMENT_TYPE,
                FragmentFactory.Type.ACCOUNT_PROFILE.ordinal()
            );
        startActivity(intent);
    }

    private void initialize(final View view) {
        setupErrorMessage(view);
        setupListView(view);
    }

    private void setupListView(final View view) {
        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        final TextView emptyTextView = (TextView) view.findViewById(android.R.id.empty);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        emptyTextView.setText(R.string.msg_no_users_found);
    }

    private Request<List<ProfileSearchResult>> fetchRequestForSearch(Bundle postData) {
        return new SimplePostRequest<List<ProfileSearchResult>>(
            UrlFactory.buildUserSearchURL(),
            postData,
            this
        ) {
            @Override
            protected List<ProfileSearchResult> doParse(String json) {
                ProfileSearchResults results = fromJson(json, ProfileSearchResults.class, true);
                return fetchBf4Accounts(results.getResults());
            }

            @Override
            protected void deliverResponse(List<ProfileSearchResult> response) {
                sendDataToListView(response);
                showLoadingState(false);
                isReloading = false;
            }
        };
    }

    private List<ProfileSearchResult> fetchBf4Accounts(List<ProfileSearchResult> results) {
        final List<ProfileSearchResult> validAccounts = new ArrayList<ProfileSearchResult>();
        for (ProfileSearchResult result : results) {
            Map<Integer, Integer> games = result.getGames();
            for (int platformId : games.keySet()) {
                final int gameForPlatform = games.get(platformId);
                if (PersonaUtils.isBf4Soldier(gameForPlatform)) {
                    validAccounts.add(
                        new ProfileSearchResult(
                            result.getPersonaId(),
                            result.getPersonaName(),
                            PlatformStringMap.get(platformId),
                            result.getProfile(),
                            result.getGames()
                        )
                    );
                }
            }
        }
        return validAccounts;
    }

    private void sendDataToListView(final List<ProfileSearchResult> results) {
        if (getView() == null) {
            return;
        }

        ProfileSearchAdapter adapter = (ProfileSearchAdapter) getListAdapter();
        if (adapter == null) {
            adapter = new ProfileSearchAdapter(getActivity());
            setListAdapter(adapter);
        }
        adapter.setItems(results);
    }
}
