package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.CompletedLevelEvent;
import com.ninetwozero.bf4intel.resources.maps.LevelStringMap;

public class CompletedLevelLayout extends BaseLayoutPopulator implements EventLayout<CompletedLevelEvent> {
    @Override
    public void populateView(final Context context, final View view, final CompletedLevelEvent event) {
        setText(view, R.id.level, LevelStringMap.get(event.getLevelName()));
        setText(view, R.id.difficulty, event.getDifficulty());
        if (event.hasPartner()) {
            setText(view, R.id.partner, event.getPartner().getUsername());
            setVisibilty(view, R.id.wrap_partner, View.VISIBLE);
        } else {
            setVisibilty(view, R.id.wrap_partner, View.GONE);
        }
    }
}
