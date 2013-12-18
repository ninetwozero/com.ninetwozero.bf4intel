package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.resources.maps.vehicles.unlocks.VehicleUnlockStringMap;

import java.util.List;

public class VehicleUnlockAdapter extends BaseIntelAdapter<VehicleUnlock> {

    public VehicleUnlockAdapter(final List<VehicleUnlock> itemsList, final Context context) {
        super(itemsList, context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(final int position) {
        return getItem(position).getGuid() == null ? 0 : 1;
    }

    @Override
    public View getView(final int position, View view, final ViewGroup parent) {
        final VehicleUnlock unlock = itemsList.get(position);
        final int viewType = getItemViewType(position);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(
                fetchLayoutForType(viewType),
                parent,
                false
            );
        }

        if (viewType == 0) {
            ((TextView) view.findViewById(android.R.id.text1)).setText(unlock.getName());
        } else {
            ((TextView) view.findViewById(android.R.id.text1)).setText(VehicleUnlockStringMap.get(unlock.getName()));
        }
        return view;
    }

    private int fetchLayoutForType(final int position) {
        if (position == 0) {
            return android.R.layout.simple_list_item_1;
        } else {
            return android.R.layout.simple_list_item_2;
        }
    }
}
