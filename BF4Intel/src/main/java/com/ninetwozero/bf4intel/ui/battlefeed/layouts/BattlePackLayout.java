package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.BattlePackItem;
import com.ninetwozero.bf4intel.json.battlefeed.events.BattlePackEvent;
import com.ninetwozero.bf4intel.resources.maps.battlepacks.MiscBattlePackImageMap;
import com.ninetwozero.bf4intel.resources.maps.battlepacks.MiscBattlePackStringMap;
import com.ninetwozero.bf4intel.resources.maps.camoflagues.CamoImageMap;
import com.ninetwozero.bf4intel.resources.maps.camoflagues.CamoStringMap;
import com.ninetwozero.bf4intel.resources.maps.emblems.EmblemImageMap;
import com.ninetwozero.bf4intel.resources.maps.emblems.EmblemStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponAccessoryImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponAccessoryStringMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponImageMap;
import com.ninetwozero.bf4intel.resources.maps.weapons.WeaponStringMap;

import java.util.List;

public class BattlePackLayout extends BaseLayoutPopulator implements EventLayout<BattlePackEvent> {
    private static final int NO_SUBTITLE = -1;
    private static final String BATTLEPACK_SOLDIERS = "WARSAW_ID_M_BATTLEPACK_ITEM_ICON";

    @Override
    public void populateView(final Context context, final View view, final BattlePackEvent event) {
        populateDataTable(context, view, event.getItems());
        setText(view, R.id.battlepack_type, MiscBattlePackStringMap.get(event.getName()));
        setImage(view, R.id.battlepack_icon, MiscBattlePackImageMap.get(BattlePackEvent.fetchPackType(event.getName())));
    }

    private void populateDataTable(final Context context, final View view, final List<BattlePackItem> items) {
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final ViewGroup contentArea = (ViewGroup) view.findViewById(R.id.content_grid);
        final TableLayout.LayoutParams rowLayoutParams = new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        final TableRow.LayoutParams cellLayoutParams = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);

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
                final View cell = layoutInflater.inflate(R.layout.list_item_feed_battlepack_item, (ViewGroup)view.getParent(), false);
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
                        itemKey.equals(BATTLEPACK_SOLDIERS) ? NO_SUBTITLE : R.string.battlefeed_emblem
                    );
                } else if (category.equals("camo") || category.equals("appearance")) {
                    populateCell(
                        cell,
                        CamoImageMap.get(itemKey),
                        CamoStringMap.get(itemKey),
                        category.equals("camo") ? R.string.battlefeed_paint : R.string.battlefeed_appearance
                    );
                } else if(category.equals("weapon")) {
                    populateCell(cell, WeaponImageMap.get(itemKey), WeaponStringMap.get(itemKey), NO_SUBTITLE);
                } else {
                    populateCell(
                        cell,
                        MiscBattlePackImageMap.get(itemKey),
                        MiscBattlePackStringMap.get(itemKey),
                        NO_SUBTITLE
                    );
                }
                tableRow.addView(cell, cellLayoutParams);
            }
            contentArea.addView(tableRow);
        }
    }

    private void populateCell(final View cell, final int icon, final int name, final int parent) {
        setImage(cell, R.id.content_icon, icon);
        setText(cell, R.id.content_name, name);
        setText(cell, R.id.content_parent_name, parent != NO_SUBTITLE ? parent : R.string.empty);
    }
}
