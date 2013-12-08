package com.ninetwozero.bf4intel.json.battlefeed.uibinders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.events.BattlePackEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.SharedGameEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.SharedGameEventItem;
import com.ninetwozero.bf4intel.resources.maps.ServiceStarStringMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentImageMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentStringMap;
import com.ninetwozero.bf4intel.resources.maps.awards.AwardImageMap;
import com.ninetwozero.bf4intel.resources.maps.awards.AwardStringMap;
import com.ninetwozero.bf4intel.resources.maps.battlepacks.MiscBattlePackImageMap;
import com.ninetwozero.bf4intel.resources.maps.battlepacks.MiscBattlePackStringMap;
import com.ninetwozero.bf4intel.resources.maps.dogtags.DogtagStringMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleImageMap;
import com.ninetwozero.bf4intel.resources.maps.vehicles.VehicleStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponAccessoryImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponAccessoryStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;

public class SharedGameEventUiBinder implements EventUiBinder<SharedGameEvent> {
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
        int resolvedItemIcon;
        int resolvedItemName;

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

                final String itemKey = items[counter].getName();

                // TODO: Should getCategory() return an enum so that we can switch() over it instead?
                final View cell = layoutInflater.inflate(R.layout.list_item_feed_award_item, null, false);
                final String category = event.getCategory();
                if (category.equals("BF4AWARDS")) {
                    if (itemKey.contains("STAR_NAME")) {
                        resolvedItemIcon = R.drawable.award_service_star;
                        resolvedItemName = ServiceStarStringMap.get(itemKey);
                    } else {
                        resolvedItemIcon = AwardImageMap.get(itemKey);
                        resolvedItemName = AwardStringMap.get(itemKey);
                    }
                } else if (category.equals("BF4ASSIGNMENTS")) {
                    resolvedItemIcon = AssignmentImageMap.get(itemKey);
                    resolvedItemName = AssignmentStringMap.get(itemKey);
                } else if (category.equals("BF4GAMEREPORT")) {
                    if (itemKey.contains("WNAME") || itemKey.contains("INAME")) {
                        resolvedItemIcon = WeaponImageMap.get(itemKey);
                        resolvedItemName = WeaponStringMap.get(itemKey);
                    } else if (itemKey.contains("VNAME")) {
                        resolvedItemIcon = VehicleImageMap.get(itemKey);
                        resolvedItemName = VehicleStringMap.get(itemKey);
                    } else if (itemKey.contains("ANAME")) {
                        resolvedItemIcon = WeaponAccessoryImageMap.get(itemKey);
                        resolvedItemName = WeaponAccessoryStringMap.get(itemKey);
                    } else {
                        resolvedItemIcon = MiscBattlePackImageMap.get(BattlePackEvent.fetchPackType(itemKey));
                        resolvedItemName = MiscBattlePackStringMap.get(itemKey);
                    }
                } else if (category.equals("BF4DOGTAGS")) {
                    resolvedItemIcon = R.drawable.test_dogtag;
                    resolvedItemName = DogtagStringMap.get(itemKey);
                } else {
                    resolvedItemIcon = R.drawable.acc_none;
                    resolvedItemName = R.string.na;
                }

                ((ImageView) cell.findViewById(R.id.content_icon)).setImageResource(resolvedItemIcon);
                ((TextView) cell.findViewById(R.id.content_name)).setText(resolvedItemName);

                tableRow.addView(cell, cellLayoutParams);
            }
            contentArea.addView(tableRow);
        }
    }
}
