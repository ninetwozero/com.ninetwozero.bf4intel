package com.ninetwozero.bf4intel.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.app.BaseFragment;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;

public class PostCreationFragment extends BaseFragment {
    public static final String FORUM_ID = "threadId";

    public PostCreationFragment() {
    }

    public static PostCreationFragment newInstance() {
        final PostCreationFragment fragment = new PostCreationFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public static PostCreationFragment newInstance(final Bundle data) {
        final PostCreationFragment fragment = new PostCreationFragment();
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

        final View view = this.layoutInflater.inflate(R.layout.fragment_post_creation, parent, false);
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
        if (item.getItemId() == R.id.ab_action_new) {
            doCreateThread();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "CREATE POST", R.drawable.ic_actionbar_forums);
    }

    private void doCreateThread() {
        final View root = getView();
        final EditText inputTitle = (EditText) root.findViewById(R.id.new_thread_title_input);
        final EditText inputContent = (EditText) root.findViewById(R.id.update_status_input);

        final String title = inputTitle.getText().toString();
        final String content = inputContent.getText().toString();

        if ("".equals(title)) {
            inputTitle.setError("You need to enter a title!");
            return;
        }

        if ("".equals(content)) {
            inputContent.setError("You need to enter some content!");
            return;
        }

        showToast("TODO: Trigger API call (POST)");

        final Activity activity = getActivity();
        if (activity == null || !(activity instanceof SingleFragmentActivity)) {
            fragmentManager.popBackStackImmediate();
        } else {
            activity.finish();
        }
    }
}
