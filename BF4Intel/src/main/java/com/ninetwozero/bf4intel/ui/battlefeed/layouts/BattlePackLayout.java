package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
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

import java.util.List;

public class BattlePackLayout implements EventLayout<BattlePackEvent> {
    private final String BATTLEPACK_SOLDIERS = "WARSAW_ID_M_BATTLEPACK_ITEM_ICON";

    @Override
    public void populateView(final Context context, final View view, final BattlePackEvent event) {
        populateDataTable(context, view, event.getItems());
        ((TextView) view.findViewById(R.id.battlepack_type)).setText(MiscBattlePackStringMap.get(event.getName()));
        ((ImageView) view.findViewById(R.id.battlepack_icon)).setImageResource(MiscBattlePackImageMap.get(BattlePackEvent.fetchPackType(event.getName())));
    }

    private void populateDataTable(final Context context, final View view, final List<BattlePackItem> items) {
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final ViewGroup contentArea = (ViewGroup) view.findViewById(R.id.content_grid);
        final TableLayout.LayoutParams rowLayoutParams = new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        final TableRow.LayoutParams cellLayoutParams = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);

        Log.d(getClass().getSimpleName(), "List<BattlePackItem> => " + items);
        contentArea.removeAllViews();
        final int maxRows = (int) Math.ceil(items.size() / 3.0f);
        for (int i = 0, counter = 0; i < maxRows; i++) {
            final TableRow tableRow = new TableRow(context);
            tableRow.setLayoutParams(rowLayoutParams);
            tableRow.setWeightSum(3f);

            for (int j = 0, maxCols = 3; j < maxCols; j++, counter++) {
                if (counter == items.size()) {
                    break;
                }

                final String itemKey = items.get(counter).getName();
                final String parentKey = items.get(counter).getParentName();

                // TODO: Should getCategory() return an enum so that we can switch() over it instead?
                final View cell = layoutInflater.inflate(R.layout.list_item_feed_battlepack_item, null, false);
                final String category = items.get(counter).getCategory();
                if (category.equals("weaponaccessory")) {
                    populateCell(
                        cell,
                        WeaponAccessoryImageMap.get(itemKey),
                        WeaponAccessoryStringMap.get(itemKey),
                        WeaponStringMap.get(parentKey)
                    );
                } else if (category.equals("battlepackitems")) {
                    populateCell(
                        cell,
                        EmblemImageMap.get(itemKey),
                        EmblemStringMap.get(itemKey),
                        itemKey.equals(BATTLEPACK_SOLDIERS) ? -1 : R.string.battlefeed_emblem
                    );
                } else if (category.equals("camo") || category.equals("appearance")) {
                    populateCell(
                        cell,
                        CamoImageMap.get(itemKey),
                        CamoStringMap.get(itemKey),
                        category.equals("camo") ? R.string.battlefeed_paint : R.string.battlefeed_appearance
                    );
                } else {
                    populateCell(
                        cell,
                        MiscBattlePackImageMap.get(itemKey),
                        MiscBattlePackStringMap.get(itemKey),
                        -1
                    );
                }
                tableRow.addView(cell, cellLayoutParams);
            }
            contentArea.addView(tableRow);
        }
    }

    private void populateCell(final View cell, final int icon, final int name, final int parent) {
        ((ImageView) cell.findViewById(R.id.content_icon)).setImageResource(icon);
        ((TextView) cell.findViewById(R.id.content_name)).setText(name);
        if (parent > 0) {
            ((TextView) cell.findViewById(R.id.content_parent_name)).setText(parent);
        } else {
            ((TextView) cell.findViewById(R.id.content_parent_name)).setText("");
        }
    }
}
