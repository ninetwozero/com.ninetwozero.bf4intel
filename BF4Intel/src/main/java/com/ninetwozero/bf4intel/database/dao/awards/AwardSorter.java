package com.ninetwozero.bf4intel.database.dao.awards;

import com.ninetwozero.bf4intel.database.dao.AbstractSorter;
import com.ninetwozero.bf4intel.json.awards.Award;
import com.ninetwozero.bf4intel.json.awards.Awards;
import com.ninetwozero.bf4intel.json.awards.Medal;
import com.ninetwozero.bf4intel.json.awards.Ribbon;
import com.ninetwozero.bf4intel.json.awards.SortedAwardContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AwardSorter extends AbstractSorter<SortedAwardContainer> {

    private final Awards awards;

    public AwardSorter(final Awards awards) {
        this.awards = awards;
    }

    @Override
    protected SortedAwardContainer sortByProgress() {
        List<Award> orderedAwards = new ArrayList<Award>();
        Map<String, List<String>> awardsGroups = awards.getAwardsGroups();
        Set<String> awardTypes = awardsGroups.keySet();
        for (String group : awardTypes) {
            List<String> awardsInGroup = awardsGroups.get(group);
            orderedAwards.addAll(fetchGroupedAwards(awardsInGroup, group));
        }
        Collections.sort(orderedAwards);
        return new SortedAwardContainer(orderedAwards);
    }

    @Override
    protected SortedAwardContainer sortByCategory() {
        List<Award> orderedAwards = new ArrayList<Award>();
        Map<String, List<String>> awardsGroups = awards.getAwardsGroups();
        Set<String> awardTypes = awardsGroups.keySet();
        for (String group : awardTypes) {
            List<String> awardsInGroup = awardsGroups.get(group);
            Collections.sort(awardsInGroup);
            orderedAwards.addAll(fetchGroupedAwards(awardsInGroup, group));
        }
        return new SortedAwardContainer(orderedAwards);
    }

    private List<Award> fetchGroupedAwards(final List<String> awardsInGroup, final String group) {
        List<Award> orderedGroup = new ArrayList<Award>();
        for (String key : awardsInGroup) {
            if (awards.getMedals().containsKey(key)) {
                Medal medal = awards.getMedals().get(key);
                String ribbonCode = medal.getMedalAward().getMedalDepencies().get(0).getRibbonDependency();
                Ribbon ribbon = awards.getRibbons().get(ribbonCode);
                orderedGroup.add(new Award(key, medal, ribbonCode, ribbon, group));
            }
        }
        return orderedGroup;
    }
}
