package com.ninetwozero.bf4intel.ui.unlocks;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseExpandableIntelAdapter;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlocks;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.unlocks.weapons.WeaponUnlockAdapter;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseUnlockFragment extends BaseLoadingListFragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = layoutInflater.inflate(R.layout.fragment_unlocks, parent, false);
        initialize(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoadingState(false);
        startLoadingData();
    }

    private Map<String, List<WeaponUnlockContainer>> sortItemsInMap(final Map<String, List<WeaponUnlockContainer>> unlockMap) {
        final Map<String, List<WeaponUnlockContainer>> map = new HashMap<String, List<WeaponUnlockContainer>>();
        final Set<String> keySet = unlockMap.keySet();
        for (String key : keySet) {
            final List<WeaponUnlockContainer> unlocks = unlockMap.get(key);
            Collections.sort(unlocks);
            map.put(key, unlocks);
        }
        return map;
    }

    @Override
    protected void onLoadFailure(final String resultMessage) {
        Log.d(getClass().getSimpleName(), "[onLoadFailure] resultMessage => " + resultMessage);
    }


    @Override
    protected abstract void startLoadingData();

    @Override
    public abstract Loader<Result> onCreateLoader(final int i, final Bundle bundle);

    private void initialize(final View view) {
        setupListView(view);
    }

    private void setupListView(final View view) {
        final ExpandableListView listView = (ExpandableListView) view.findViewById(android.R.id.list);
        final TextView emptyTextView = (TextView) view.findViewById(android.R.id.empty);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
        emptyTextView.setText(R.string.msg_no_unlocks);
    }

    protected void toggleAllRows(final boolean expand) {
        final ExpandableListView listView = (ExpandableListView) getListView();
        if (listView == null) {
            return;
        }
        final BaseExpandableIntelAdapter adapter = (BaseExpandableIntelAdapter) listView.getExpandableListAdapter();
        for (int i = 0, max = adapter.getGroupCount(); i < max; i++) {
            if (expand) {
                listView.expandGroup(i);
            } else {
                listView.collapseGroup(i);
            }
        }
    }
}
