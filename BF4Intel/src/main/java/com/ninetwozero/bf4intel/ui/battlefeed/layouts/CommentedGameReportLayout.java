package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.CommentedGameReportEvent;
import com.ninetwozero.bf4intel.resources.maps.GameModeStringMap;
import com.ninetwozero.bf4intel.resources.maps.LevelStringMap;

public class CommentedGameReportLayout extends BaseLayoutPopulator implements EventLayout<CommentedGameReportEvent> {
    @Override
    public void populateView(final Context context, final View view, final CommentedGameReportEvent event) {
        setText(view, R.id.server_name, event.getServer());
        setText(view, R.id.map_name, LevelStringMap.get(event.getMap()));
        setText(view, R.id.game_mode, GameModeStringMap.get(event.getGameMode()));
        setText(view, R.id.comment, event.getComment());
    }
}
