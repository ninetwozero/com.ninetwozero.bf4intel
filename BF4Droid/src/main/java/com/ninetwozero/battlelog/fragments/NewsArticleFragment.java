package com.ninetwozero.battlelog.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.AbstractListFragment;
import com.ninetwozero.battlelog.adapters.NewsItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsArticleFragment extends AbstractListFragment {
    public static final String ID = "articleId";

    private long mId;

    public NewsArticleFragment() {
    }

    public static NewsArticleFragment newInstance() {
        final NewsArticleFragment fragment = new NewsArticleFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static NewsArticleFragment newInstance(final Bundle data) {
        final NewsArticleFragment fragment = new NewsArticleFragment();
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

    @Override
    public void onListItemClick(final ListView listView, final View view, final int position, final long id) {
        final int actualPosition = position-1; // -Header view
        final FragmentTransaction transaction = mFragmentManager.beginTransaction();

        /* TODO: CommentListFragment, which the user drags up from the bottom in some way like G+ app a while back? */
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "Loading...", R.drawable.ic_actionbar_news);
        setupListView(view);
        setupForm(view);
    }

    private void setupListView(final View view) {
        final ListView listView = (ListView) view.findViewById(android.R.id.list);
        final NewsItemAdapter adapter = new NewsItemAdapter(getActivity(), getDummyItems());

        // TODO: Pass Bundle instead of new Object() in the future when things are in place
        listView.addHeaderView(mInflater.inflate(R.layout.list_header_news_item, null, false), new Object(), false);
        listView.setHeaderDividersEnabled(true);
        listView.setAdapter(adapter);
    }

    private void setupForm(final View view) {
        view.findViewById(R.id.button_send).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    doSendComment();
                }
            }
        );
    }

    private void doSendComment() {
        final View container = getView();
        if( container == null ) {
            return;
        }

        final Button button = (Button) container.findViewById(R.id.button_send);
        final EditText input = (EditText) container.findViewById(R.id.input_content);
        final String comment = input.getText().toString();

        if( "".equals(comment) ) {
            input.setError(getString(R.string.msg_enter_comment));
            return;
        }

        button.setEnabled(false);
        button.setText(R.string.msg_sending);

        // TODO: Do actual network transmission

        button.setEnabled(true);
        button.setText(R.string.ab_action_send);
        showToast(R.string.msg_comment_ok);
    }

    public void loadArticle(final long id) {
        mId = id;
        if (mId > 0) {
            /* TODO: Do actual loading */
        }
    }

    public List<Object> getDummyItems() {
        final List<Object> list = new ArrayList<Object>();
        list.add(this);
        list.add(this);
        list.add(this);
        list.add(this);
        list.add(this);
        list.add(this);
        return list;
    }
}
