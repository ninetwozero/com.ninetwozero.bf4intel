package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.SharedGameEventCategory;
import com.ninetwozero.bf4intel.json.battlefeed.SharedGameEventItem;
import com.ninetwozero.bf4intel.json.battlefeed.events.BattlePackEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.SharedGameEvent;
import com.ninetwozero.bf4intel.resources.maps.ServiceStarStringMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentImageMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentStringMap;
import com.ninetwozero.bf4intel.resources.maps.awards.AwardImageMap;
import com.ninetwozero.bf4intel.resources.maps.awards.AwardStringMap;
import com.ninetwozero.bf4intel.resources.maps.battlepacks.MiscBattlePackImageMap;
import com.ninetwozero.bf4intel.resources.maps.battlepacks.MiscBattlePackStringMap;
import com.ninetwozero.bf4intel.resources.maps.dogtags.DogtagStringMap;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankImageMap;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankStringMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponAccessoryImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponAccessoryStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;

public class SharedGameEventLayout extends BaseLayoutPopulator implements EventLayout<SharedGameEvent> {
    @Override
    public void populateView(final Context context, final View view, final SharedGameEvent event) {
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final ViewGroup contentArea = (ViewGroup) view.findViewById(R.id.content_grid);
        final TableLayout.LayoutParams rowLayoutParams = new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        final TableRow.LayoutParams cellLayoutParams = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        final SharedGameEventItem[] items = event.getItems();

        contentArea.removeAllViews();
        final int maxRows = (int) Math.ceil(items.length / 3.0f);
        for (int i = 0, counter = 0; i < maxRows; i++) {
            final TableRow tableRow = new TableRow(context);
            tableRow.setLayoutParams(rowLayoutParams);
            tableRow.setWeightSum(3f);

            for (int j = 0, maxCols = 3; j < maxCols; j++, counter++) {
                if (counter == items.length) {
                    break;
                }

                final View cell = layoutInflater.inflate(R.layout.list_item_feed_award_item, null, false);
                final SharedGameEventCategory category = event.getCategory();
                final String itemKey = items[counter].getName();
                switch (category) {
                    case ASSIGNMENTS:
                        populateCell(cell, AssignmentImageMap.get(itemKey), AssignmentStringMap.get(itemKey));
                        break;
                    case AWARDS:
                        populateAwardCell(cell, itemKey);
                        break;
                    case DOGTAGS:
                        populateCell(cell, R.drawable.test_dogtag, DogtagStringMap.get(itemKey));
                        break;
                    case GAME_REPORT:
                    case TRACKING_COMPLETE:
                        populateGameReportCell(cell, itemKey);
                        break;
                    case RANKUP:
                        populateRankUpCell(cell, itemKey);
                        break;
                    default:
                        Log.d(getClass().getSimpleName(), "[populateView] Unable to populate view for '" + category + "'");
                        populateCell(cell, R.drawable.acc_none, R.string.na);
                }
                tableRow.addView(cell, cellLayoutParams);
            }
            contentArea.addView(tableRow);
        }
    }

    private void populateCell(final View cell, final int icon, final int name) {
        setImage(cell, R.id.content_icon, icon);
        setText(cell, R.id.content_name, name);
    }

    private void populateAwardCell(final View cell, final String itemKey) {
        if (itemKey.contains("STAR_NAME")) {
            populateCell(cell, R.drawable.service_star, ServiceStarStringMap.get(itemKey));
        } else {
            populateCell(cell, AwardImageMap.get(itemKey), AwardStringMap.get(itemKey));
        }
    }

    private void populateGameReportCell(final View cell, final String itemKey) {
        if (itemKey.contains("WNAME") || itemKey.contains("INAME")) {
            populateCell(cell, WeaponImageMap.get(itemKey), WeaponStringMap.get(itemKey));
        } else if (itemKey.contains("VNAME")) {
            populateCell(cell, VehicleImageMap.get(itemKey), VehicleStringMap.get(itemKey));
        } else if (itemKey.contains("ANAME")) {
            populateCell(cell, WeaponAccessoryImageMap.get(itemKey), WeaponAccessoryStringMap.get(itemKey));
        } else if (itemKey.contains("AWARD_AS")) {
            populateCell(cell, AssignmentImageMap.get(itemKey), AssignmentStringMap.get(itemKey));
        } else {
            populateCell(
                cell,
                MiscBattlePackImageMap.get(itemKey),
                MiscBattlePackStringMap.get(BattlePackEvent.fetchPackType(itemKey))
            );
        }
    }

    private void populateRankUpCell(final View cell, final String itemKey) {
        final String prefixedKey = "WARSAW_" + itemKey;
        populateCell(cell, RankImageMap.get(prefixedKey), RankStringMap.get(prefixedKey));
    }
}
