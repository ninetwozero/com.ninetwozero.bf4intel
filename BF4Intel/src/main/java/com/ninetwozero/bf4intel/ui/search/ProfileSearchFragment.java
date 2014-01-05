package com.ninetwozero.bf4intel.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.search.ProfileSearchResult;
import com.ninetwozero.bf4intel.json.search.ProfileSearchResults;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimplePostRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.List;

public class ProfileSearchFragment extends BaseLoadingListFragment {
    public static final String INTENT_SEARCH_RESULT = "profile_search_result";
    private static final int ID_LOADER = 10303;

    private String queryString;

    public static ProfileSearchFragment newInstance(final Bundle data) {
        final ProfileSearchFragment fragment = new ProfileSearchFragment();
        fragment.setArguments(data);
        return fragment;
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
        final SearchView searchView = (SearchView) searchItem.getActionView();
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
            searchView.setIconifiedByDefault(false);
            searchView.setQuery(queryString, true);
            searchItem.setVisible(true);
        }
    }

    @Override
    protected void onLoadSuccess(final String resultMessage) {
        ProfileSearchResults results = fromJson(resultMessage, ProfileSearchResults.class, true);
        sendDataToListView(results.getResults());
        showLoadingState(false);
    }

    @Override
    protected void onLoadFailure(final String resultMessage) {
        Log.d(getClass().getSimpleName(), "[onLoadFailure] resultMessage => " + resultMessage);
    }


    @Override
    protected void startLoadingData() {
        final Bundle postData = new Bundle();

        if (queryString == null && queryString.length() < 3) {
            showToast("Min length: 3 characters");
            return;
        }

        postData.putString("query", queryString);
        postData.putString("post-check-sum", "0xCAFEBABE");
        getLoaderManager().restartLoader(ID_LOADER, postData, this);
    }

    @Override
    public Loader<Result> onCreateLoader(final int i, final Bundle bundle) {
        showLoadingState(true);

        return new IntelLoader(
            getActivity(),
            new SimplePostRequest(
                UrlFactory.buildUserSearchURL(),
                bundle
            )
        );
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
                new Intent().putExtra(INTENT_SEARCH_RESULT, result.getProfile())
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

        final Intent intent = new Intent(getContext(), SingleFragmentActivity.class)
            .putExtra(SingleFragmentActivity.INTENT_FRAGMENT_DATA, dataBundle)
            .putExtra(
                SingleFragmentActivity.INTENT_FRAGMENT_TYPE,
                FragmentFactory.Type.ACCOUNT_PROFILE.ordinal()
            );
        startActivity(intent);
    }

    private void initialize(final View view) {
        setupDataFromBundle(getArguments());
        setupListView(view);
    }

    private void setupDataFromBundle(final Bundle arguments) {
        queryString = arguments.getString(INTENT_SEARCH_RESULT, "");
    }

    private void setupListView(final View view) {
        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        final TextView emptyTextView = (TextView) view.findViewById(android.R.id.empty);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        emptyTextView.setText(R.string.msg_no_users_found);
    }

    private void sendDataToListView(final List<ProfileSearchResult> results) {
        final ListView listView = getListView();
        if (listView == null) {
            return;
        }
        setListAdapter(new ProfileSearchAdapter(results, getContext()));
    }
}
