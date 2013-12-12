package com.ninetwozero.bf4intel.ui.stats.weapons;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseListFragment;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingListFragment;
import com.ninetwozero.bf4intel.json.weaponstats.Weapon;
import com.ninetwozero.bf4intel.json.weaponstats.WeaponStatistics;
import com.ninetwozero.bf4intel.network.ConnectionRequest;
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.Collections;
import java.util.List;

public class WeaponStatsFragment extends BaseLoadingListFragment {

    private static final int ID_LOADER = 2100;
    private static final String WEAPON_STATS_URL = "http://battlelog.battlefield.com/bf4/warsawWeaponsPopulateStats/200661244/1/stats/";
    private WeaponStatsAdapter adapter;
    private ListView listView;

    public static WeaponStatsFragment newInstance(final Bundle data) {
        final WeaponStatsFragment fragment = new WeaponStatsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);
        final View view = this.layoutInflater.inflate(R.layout.fragment_list_stats, parent, false);
        listView = (ListView) view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        getActivity().getSupportLoaderManager().initLoader(ID_LOADER, null, this);
    }

    @Override
    public Loader<Result> onCreateLoader(int i, Bundle bundle) {
        showLoadingState(true);
        return new IntelLoader(getActivity().getApplicationContext(), new ConnectionRequest(WEAPON_STATS_URL));
    }

    @Override
    public void onLoadFinished(Loader<Result> resultLoader, Result result) {
        if (result == Result.SUCCESS) {
            onLoadSuccess(result.getResultMessage());
        } else {
            onLoadFailure(result.getResultMessage());
        }
    }

    @Override
    protected void onLoadSuccess(String resultMessage) {
        JsonObject dataJson = extractFromJson(resultMessage);
        WeaponStatistics ws = gson.fromJson(dataJson, WeaponStatistics.class);
        showLoadingState(false);
        List<Weapon> weaponList = ws.getWeaponsList();
        Collections.sort(weaponList);
        adapter = new WeaponStatsAdapter(weaponList, getContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onLoadFailure(String resultMessage) {
        Log.e(WeaponStatsFragment.class.getSimpleName(), resultMessage);
    }
}
