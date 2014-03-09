package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.json.stats.weapons.WeaponStatistics;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.Collections;
import java.util.List;

public class WeaponStatsFragment extends BaseLoadingListFragment {
    private static final int ID_LOADER = 2100;

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

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        getLoaderManager().restartLoader(ID_LOADER, getArguments(), this);
    }

    @Override
    public Loader<Result> onCreateLoader(final int i, final Bundle bundle) {
        showLoadingState(true);
        return new IntelLoader(
            getActivity(),
            new SimpleGetRequest(
                UrlFactory.buildWeaponStatsURL(
                    bundle.getLong(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }

    @Override
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        final WeaponStatistics ws = fromJson(resultMessage, WeaponStatistics.class);
        final List<Weapon> weaponList = ws.getWeaponsList();
        Collections.sort(weaponList);

        getListView().setAdapter(new WeaponStatsAdapter(getActivity(), weaponList));
        showLoadingState(false);
    }

    @Override
    protected void onLoadFailure(final Loader loader, final String resultMessage) {
        Log.e(WeaponStatsFragment.class.getSimpleName(), resultMessage);
    }

}
