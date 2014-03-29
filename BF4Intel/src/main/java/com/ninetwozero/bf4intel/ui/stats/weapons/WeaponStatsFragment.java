package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.json.stats.weapons.WeaponStatistics;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.Collections;
import java.util.List;

public class WeaponStatsFragment extends BaseLoadingListFragment {
    public static WeaponStatsFragment newInstance(final Bundle data) {
        final WeaponStatsFragment fragment = new WeaponStatsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);
        return layoutInflater.inflate(R.layout.fragment_list_stats, parent, false);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        showLoadingState(true);
        Bf4Intel.getRequestQueue().add(fetchRequest(getArguments()));
    }

    private Request<List<Weapon>> fetchRequest(Bundle bundle) {
        return new SimpleGetRequest<List<Weapon>>(
            UrlFactory.buildWeaponStatsURL(
                bundle.getLong(Keys.Soldier.ID),
                bundle.getInt(Keys.Soldier.PLATFORM)
            ),
            this
        ) {
            @Override
            protected List<Weapon> doParse(String json) {
                final WeaponStatistics ws = fromJson(json, WeaponStatistics.class);
                final List<Weapon> weaponList = ws.getWeaponsList();
                Collections.sort(weaponList);
                return weaponList;
            }

            @Override
            protected void deliverResponse(List<Weapon> response) {
                setListAdapter(new WeaponStatsAdapter(getActivity(), response));
                showLoadingState(false);
            }
        };
    }
}
