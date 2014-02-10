package com.ninetwozero.bf4intel.ui.awards;

import android.os.Bundle;
import android.support.v4.content.Loader;
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
import com.ninetwozero.bf4intel.network.IntelLoader;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AwardGridFragment extends BaseLoadingFragment {
    private static final int ID_LOADER = 1100;
    private static final List<String> AWARD_TYPE = new ArrayList<String>(
        Arrays.asList("vehicles", "weapon", "gamemode", "general", "kits", "team")
    );

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

    @Override
    public void onResume() {
        super.onResume();
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        getLoaderManager().initLoader(ID_LOADER, getArguments(), this);
    }

    @Override
    public Loader<Result> onCreateLoader(final int loaderId, final Bundle bundle) {
        showLoadingState(true);
        return new IntelLoader(
            getActivity(),
            new SimpleGetRequest(
                UrlFactory.buildAwardsURL(
                    bundle.getLong(Keys.Soldier.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                )
            )
        );
    }

    @Override
    protected void onLoadSuccess(final Loader loader, final String resultMessage) {
        if (loader.getId() == ID_LOADER) {
            processResult(resultMessage);
        }
    }

    private void processResult(final String resultMessage) {
        final Awards awards = fromJson(resultMessage, Awards.class);
        setupGrid(awards);
        showLoadingState(false);
    }

    private void setupGrid(final Awards awards) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final GridView gridView = (GridView) view.findViewById(R.id.assignments_grid);
        gridView.setAdapter(new AwardsAdapter(getActivity(), processAwards(awards)));
    }

    private List<Award> processAwards(final Awards awards) {
        return awards != null ? fetchSortedAwards(awards) : new ArrayList<Award>();
    }

    private List<Award> fetchSortedAwards(final Awards awards) {
        List<Award> orderedAwards = new ArrayList<Award>();
        Map<String, List<String>> awardsGroups = awards.getAwardsGroups();
        for(String group : AWARD_TYPE) {
            List<String> awardsInGroup = awardsGroups.get(group);
            Collections.sort(awardsInGroup);
            orderedAwards.addAll(fetchGroupedAwards(awards, awardsInGroup));
        }
        return orderedAwards;
    }

    private List<Award> fetchGroupedAwards(final Awards awards, final List<String> awardsInGroup) {
        List<Award> orderedGroup = new ArrayList<Award>();
        for (String key : awardsInGroup) {
            if(awards.getMedals().containsKey(key.toLowerCase())) {
                Medal medal = awards.getMedals().get(key);
                String ribbonCode = medal.getMedalAward().getMedalDepencies().get(0).getRibbonDependency();
                Ribbon ribbon = awards.getRibbons().get(ribbonCode.toLowerCase());
                orderedGroup.add(new Award(key, medal, ribbonCode, ribbon ));
            }
        }
        return orderedGroup;
    }
}
