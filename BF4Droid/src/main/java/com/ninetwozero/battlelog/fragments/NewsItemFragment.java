package com.ninetwozero.battlelog.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.AbstractListFragment;

public class NewsItemFragment extends AbstractListFragment {
    public static final String ID = "articleId";

    private long mId;

    public NewsItemFragment() {}

    public static NewsItemFragment newInstance() {
        final NewsItemFragment fragment = new NewsItemFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static NewsItemFragment newInstance(final Bundle data) {
        final NewsItemFragment fragment = new NewsItemFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = mInflater.inflate(R.layout.fragment_news_item, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadArticle(mId);
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "Loading...", R.drawable.ic_actionbar_news);
    }

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        /* TODO: CommentListFragment, which the user drags up from the bottom in some way like G+ app a while back? */
    }

    public void loadArticle(final long id) {
        mId = id;
        if( mId > 0 ) {
            /* TODO: Do actual loading */

        }
    }
}
