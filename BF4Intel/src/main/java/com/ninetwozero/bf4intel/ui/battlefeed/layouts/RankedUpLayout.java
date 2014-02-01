package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.RankedUpEvent;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankImageMap;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankStringMap;

public class RankedUpLayout extends BaseLayoutPopulator implements EventLayout<RankedUpEvent> {
    @Override
    public void populateView(final Context context, final View view, final RankedUpEvent event) {
        setText(view, R.id.rank_title, RankStringMap.get(event.getName()));
        setImage(view, R.id.rank_image, RankImageMap.get(event.getName()));
    }
}
