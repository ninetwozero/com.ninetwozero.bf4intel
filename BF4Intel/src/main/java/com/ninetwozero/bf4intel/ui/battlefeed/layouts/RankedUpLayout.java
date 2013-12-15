package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.RankedUpEvent;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankImageMap;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankStringMap;

public class RankedUpLayout implements EventLayout<RankedUpEvent> {
    @Override
    public void populateView(final Context context, final View view, final RankedUpEvent event) {
        ((TextView) view.findViewById(R.id.rank_title)).setText(RankStringMap.get(event.getName()));
        ((ImageView) view.findViewById(R.id.rank_image)).setImageResource(RankImageMap.get(event.getName()));
    }
}
