package com.ninetwozero.bf4intel.ui.search;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.search.ProfileSearchResult;
import com.ninetwozero.bf4intel.json.search.ProfileSearchResults;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimplePostRequest;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.List;

public class ProfileSearchFragment extends BaseLoadingListFragment {
    private static final int ID_LOADER = 10303;

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
        final EditText searchField = (EditText) getView().findViewById(R.id.input_search);
        final Bundle postData = new Bundle();
        final String searchTerm = searchField.getText().toString();

        if (searchTerm.length() == 0) {
            searchField.setError("Enter a search string!");
            searchField.requestFocus();
            return;
        }

        postData.putString("query", searchTerm);
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

    }

    private void initialize(final View view) {
        setupForm(view);
        setupListView(view);
    }

    private void setupForm(final View view) {
        view.findViewById(R.id.button_search).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    startLoadingData();
                }
            }
        );
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
        listView.setAdapter(new ProfileSearchAdapter(results, getContext()));
    }
}
