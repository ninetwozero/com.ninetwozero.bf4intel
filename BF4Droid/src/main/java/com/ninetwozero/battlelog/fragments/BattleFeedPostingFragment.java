package com.ninetwozero.battlelog.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.AbstractFragment;
import com.ninetwozero.battlelog.abstractions.AbstractListFragment;
import com.ninetwozero.battlelog.adapters.FeedAdapter;
import com.ninetwozero.battlelog.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BattleFeedPostingFragment extends AbstractFragment {
    public BattleFeedPostingFragment() {}

    public static BattleFeedPostingFragment newInstance() {
        final BattleFeedPostingFragment fragment = new BattleFeedPostingFragment();
        fragment.setArguments(new Bundle());
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

        final View view = mInflater.inflate(R.layout.fragment_feed_posting, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_posting, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == R.id.action_new ) {
            doPostStatus();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "UPDATE STATUS", R.drawable.ic_actionbar_feed);
    }

    private void doPostStatus() {
        final View root = getView();
        final EditText inputContent = (EditText) root.findViewById(R.id.input_content);
        final EditText inputExtra = (EditText) root.findViewById(R.id.input_extra);

        final String content = inputContent.getText().toString();
        final String extra = inputExtra.getText().toString();

        if( "".equals(content) ) {
            inputContent.setError("You need to enter some content!");
            return;
        }

        if( !"".equals(extra) && !extra.matches(Patterns.WEB_URL.pattern()) ) {
            inputExtra.setError("Invalid URL!");
            return;
        }

        showToast("TODO: Trigger API call (POST)");
        mFragmentManager.popBackStackImmediate();
    }
}
