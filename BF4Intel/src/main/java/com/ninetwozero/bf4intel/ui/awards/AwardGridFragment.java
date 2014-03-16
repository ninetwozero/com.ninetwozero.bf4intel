package com.ninetwozero.bf4intel.ui.awards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.awards.Award;
import com.ninetwozero.bf4intel.json.awards.Awards;
import com.ninetwozero.bf4intel.json.awards.Medal;
import com.ninetwozero.bf4intel.json.awards.Ribbon;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AwardGridFragment extends BaseLoadingFragment {
    public static AwardGridFragment newInstance(final Bundle data) {
        final AwardGridFragment fragment = new AwardGridFragment();
        fragment.setArguments(data);
        return fragment;
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_assignments, container, false);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        final Bundle bundle = getArguments();

        showLoadingState(true);
        requestQueue.add(
            new SimpleGetRequest<List<Award>>(
                UrlFactory.buildAwardsURL(
                    bundle.getLong(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                ),
                this
            ) {
                @Override
                protected List<Award> doParse(String json) {
                    final Awards awards = fromJson(json, Awards.class);
                    return processAwards(awards);
                }

                @Override
                protected void deliverResponse(List<Award> response) {
                    setupGrid(response);
                    showLoadingState(false);
                }
            }
        );
    }

    private List<Award> processAwards(final Awards awards) {
        return awards != null ? fetchSortedAwards(awards) : new ArrayList<Award>();
    }

    private List<Award> fetchSortedAwards(final Awards awards) {
        List<Award> orderedAwards = new ArrayList<Award>();
        Map<String, List<String>> awardsGroups = awards.getAwardsGroups();
        Set<String> awardTypes = awardsGroups.keySet();
        for(String group : awardTypes) {
            List<String> awardsInGroup = awardsGroups.get(group);
            Collections.sort(awardsInGroup);
            orderedAwards.addAll(fetchGroupedAwards(awards, awardsInGroup));
        }
        return orderedAwards;
    }

    private List<Award> fetchGroupedAwards(final Awards awards, final List<String> awardsInGroup) {
        List<Award> orderedGroup = new ArrayList<Award>();
        for (String key : awardsInGroup) {
            if(awards.getMedals().containsKey(key)) {
                Medal medal = awards.getMedals().get(key);
                String ribbonCode = medal.getMedalAward().getMedalDepencies().get(0).getRibbonDependency();
                Ribbon ribbon = awards.getRibbons().get(ribbonCode);
                orderedGroup.add(new Award(key, medal, ribbonCode, ribbon ));
            }
        }
        return orderedGroup;
    }

    private void setupGrid(final List<Award> awards) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final GridView gridView = (GridView) view.findViewById(R.id.assignments_grid);
        gridView.setAdapter(new AwardsAdapter(getActivity(), awards));
    }
}
