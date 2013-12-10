package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.CommentedGameReportEvent;
import com.ninetwozero.bf4intel.resources.maps.GameModeStringMap;
import com.ninetwozero.bf4intel.resources.maps.LevelStringMap;

public class CommentedGameReportLayout implements EventLayout<CommentedGameReportEvent> {
    @Override
    public void populateView(final Context context, final View view, final CommentedGameReportEvent event) {
        ((TextView) view.findViewById(R.id.server_name)).setText(event.getServer());
        ((TextView) view.findViewById(R.id.map_name)).setText(LevelStringMap.get(event.getMap()));
        ((TextView) view.findViewById(R.id.game_mode)).setText(GameModeStringMap.get(event.getGameMode()));
        ((TextView) view.findViewById(R.id.comment)).setText(event.getComment());
    }
}
