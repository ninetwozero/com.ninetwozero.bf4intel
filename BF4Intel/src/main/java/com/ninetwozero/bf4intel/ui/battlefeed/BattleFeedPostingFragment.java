package com.ninetwozero.bf4intel.ui.battlefeed;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;
import com.ninetwozero.bf4intel.ui.activities.SingleFragmentActivity;

public class BattleFeedPostingFragment extends BaseFragment {
    public BattleFeedPostingFragment() {
    }


    public static BattleFeedPostingFragment newInstance(final Bundle data) {
        final BattleFeedPostingFragment fragment = new BattleFeedPostingFragment();
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

        final View view = this.layoutInflater.inflate(R.layout.fragment_feed_posting, parent, false);
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
            doPostStatus();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "UPDATE STATUS", R.drawable.ic_actionbar_feed);
    }

    private void doPostStatus() {
        final View baseView = getView();
        final EditText inputContent = (EditText) baseView.findViewById(R.id.update_status_input);
        final EditText inputExtra = (EditText) baseView.findViewById(R.id.input_extra);

        final String content = inputContent.getText().toString();
        final String extra = inputExtra.getText().toString();

        if ("".equals(content)) {
            inputContent.setError(getString(R.string.msg_empty_content));
            return;
        }

        if (!"".equals(extra) && !extra.matches(Patterns.WEB_URL.pattern())) {
            inputExtra.setError(getString(R.string.msg_invalid_url));
            return;
        }

        // TODO: Run Loader that posts to website (via SimplePostRequest)
        showToast("TODO: Trigger API call (POST)");
        closeScreen();
    }

    private void closeScreen() {
        final Activity activity = getActivity();
        if (activity == null || !(activity instanceof SingleFragmentActivity)) {
            fragmentManager.popBackStackImmediate();
        } else {
            activity.finish();
        }
    }
}
