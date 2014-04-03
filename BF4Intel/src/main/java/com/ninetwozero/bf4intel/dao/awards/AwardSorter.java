package com.ninetwozero.bf4intel.dao.awards;

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

public class AwardSorter {

    public static SortedAwardContainer sort(final Awards awards) {
        return awards != null ? fetchSortedAwards(awards) : new SortedAwardContainer();
    }

    private static SortedAwardContainer fetchSortedAwards(final Awards awards) {
        List<Award> orderedAwards = new ArrayList<Award>();
        Map<String, List<String>> awardsGroups = awards.getAwardsGroups();
        Set<String> awardTypes = awardsGroups.keySet();
        for(String group : awardTypes) {
            List<String> awardsInGroup = awardsGroups.get(group);
            Collections.sort(awardsInGroup);
            orderedAwards.addAll(fetchGroupedAwards(awards, awardsInGroup));
        }
        return new SortedAwardContainer(orderedAwards);
    }

    private static List<Award> fetchGroupedAwards(final Awards awards, final List<String> awardsInGroup) {
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
}
