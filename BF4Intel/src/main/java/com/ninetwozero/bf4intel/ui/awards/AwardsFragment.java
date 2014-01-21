package com.ninetwozero.bf4intel.ui.awards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;
import com.ninetwozero.bf4intel.json.awards.Award;
import com.ninetwozero.bf4intel.json.awards.Awards;
import com.ninetwozero.bf4intel.json.awards.Medal;
import com.ninetwozero.bf4intel.json.awards.Ribbon;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AwardsFragment extends BaseFragment {

    private static final List<String> AWARD_TYPE = new ArrayList<String>(Arrays.asList("vehicles", "weapon", "gamemode", "general", "kits", "team"));
    private Awards awards;
    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignments, container, false);
        gridView = (GridView) view.findViewById(R.id.assignments_grid);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        setupGrid();
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onAwardsLoaded(Awards awards) {
        this.awards = awards;
        setupGrid();
    }

    private void setupGrid() {
        AwardsAdapter adapter = new AwardsAdapter(getAwards(), getActivity());
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<Award> getAwards() {
        return awards != null ? fetchSortedAwards() : new ArrayList<Award>();
    }

    private List<Award> fetchSortedAwards() {
        List<Award> orderedAwards = new ArrayList<Award>();
        Map<String, List<String>> awardsGroups = awards.getAwardsGroups();
        for(String group : AWARD_TYPE) {
            List<String> awardsInGroup = awardsGroups.get(group);
            Collections.sort(awardsInGroup);
            orderedAwards.addAll(fetchGroupedAwards(awardsInGroup));
        }
        return orderedAwards;
    }

    private List<Award> fetchGroupedAwards(List<String> awardsInGroup) {
        List<Award> orderedGroup = new ArrayList<Award>();
        for (String key : awardsInGroup) {
            if(awards.getMedals().containsKey(key.toLowerCase())) {
                String medalCode = key;
                Medal medal = awards.getMedals().get(key);
                String ribbonCode = medal.getMedalAward().getMedalDepencies().get(0).getRibbonDependency();
                Ribbon ribbon = awards.getRibbons().get(ribbonCode.toLowerCase());
                orderedGroup.add(new Award(medalCode, medal, ribbonCode, ribbon ));
            }
        }
        return orderedGroup;
    }
}
