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
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.BattlePackItem;
import com.ninetwozero.bf4intel.resources.maps.battlepacks.MiscBattlePackImageMap;
import com.ninetwozero.bf4intel.resources.maps.battlepacks.MiscBattlePackStringMap;
import com.ninetwozero.bf4intel.resources.maps.camoflagues.CamoImageMap;
import com.ninetwozero.bf4intel.resources.maps.camoflagues.CamoStringMap;
import com.ninetwozero.bf4intel.resources.maps.emblems.EmblemImageMap;
import com.ninetwozero.bf4intel.resources.maps.emblems.EmblemStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponAccessoryImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponAccessoryStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;

public class BattlePackUiBinder implements EventUiBinder<BattlePackEvent> {
    @Override
    public void populateView(final Context context, final View view, final BattlePackEvent event) {
        populateDataTable(context, view, event.getItems());
        ((TextView) view.findViewById(R.id.battlepack_type)).setText(MiscBattlePackStringMap.get(event.getName()));
        ((ImageView) view.findViewById(R.id.battlepack_icon)).setImageResource(MiscBattlePackImageMap.get(event.fetchPackType()));

    }

    private void populateDataTable(final Context context, final View view, final BattlePackItem[] items) {
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final ViewGroup contentArea = (ViewGroup) view.findViewById(R.id.content_grid);
        final TableLayout.LayoutParams rowLayoutParams = new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        final TableRow.LayoutParams cellLayoutParams = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        int resolvedItemIcon;
        String resolvedItemName;
        String resolvedParentName;

        contentArea.removeAllViews();
        final int maxRows = (int) Math.ceil(items.length/3.0f);
        for (int i = 0, counter = 0; i < maxRows; i++) {
            final TableRow tableRow = new TableRow(context);
            tableRow.setLayoutParams(rowLayoutParams);
            tableRow.setWeightSum(3f);

            for (int j = 0, maxCols = 3; j < maxCols; j++, counter++) {
                if ( counter == items.length ) {
                    break;
                }

                final String itemName = items[counter].getName();
                final String parentName = items[counter].getParentName();

                // TODO: Should getCategory() return an enum so that we can switch() over it instead?
                final View cell = layoutInflater.inflate(R.layout.list_item_battlepack_item, null, false);
                final String category = items[counter].getCategory();
                if (category.equals("weaponaccessory")) {
                    resolvedItemIcon = WeaponAccessoryImageMap.get(itemName);
                    resolvedItemName = WeaponAccessoryStringMap.get(itemName);
                    resolvedParentName = WeaponStringMap.get(parentName);
                } else if(category.equals("battlepackitems")) {
                    resolvedItemIcon = EmblemImageMap.get(itemName);
                    resolvedItemName = EmblemStringMap.get(itemName);
                    resolvedParentName = context.getString(R.string.battlefeed_emblem);
                } else if (category.equals("camo")) {
                    resolvedItemIcon = CamoImageMap.get(itemName);
                    resolvedItemName = CamoStringMap.get(itemName);
                    resolvedParentName = context.getString(R.string.battlefeed_paint);
                } else {
                    resolvedItemIcon = MiscBattlePackImageMap.get(itemName);
                    resolvedItemName = MiscBattlePackStringMap.get(itemName);
                    resolvedParentName = "";
                }

                ((ImageView) cell.findViewById(R.id.content_icon)).setImageResource(resolvedItemIcon);
                ((TextView) cell.findViewById(R.id.content_name)).setText(resolvedItemName);
                ((TextView) cell.findViewById(R.id.content_parent_name)).setText(resolvedParentName);

                tableRow.addView(cell, cellLayoutParams);
            }
            contentArea.addView(tableRow);
        }
    }
}
