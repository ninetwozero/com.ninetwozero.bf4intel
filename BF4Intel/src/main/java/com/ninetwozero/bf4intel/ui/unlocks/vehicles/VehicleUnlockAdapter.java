package com.ninetwozero.bf4intel.ui.unlocks.vehicles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.resources.maps.vehicles.unlocks.VehicleUnlockStringMap;

import java.text.NumberFormat;
import java.util.List;

public class VehicleUnlockAdapter extends BaseIntelAdapter<VehicleUnlock> {
    private static final int VIEW_TYPE_HEADING = 0;
    private static final int VIEW_TYPE_CONTENT = 1;

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
        return getItem(position).getGuid() == null ? VIEW_TYPE_HEADING : VIEW_TYPE_CONTENT;
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

        if (viewType == VIEW_TYPE_HEADING) {
            populateHeadingView(view, unlock);
        } else {
            populateContentRow(view, unlock);
        }
        return view;
    }

    private void populateHeadingView(final View view, final VehicleUnlock unlock) {
        ((TextView) view.findViewById(R.id.text1)).setText(unlock.getName());
    }

    private void populateContentRow(final View view, final VehicleUnlock unlock) {
        ((TextView) view.findViewById(R.id.title)).setText(VehicleUnlockStringMap.get(unlock.getName()));
        ((TextView) view.findViewById(R.id.subtitle)).setText(unlock.getCriteria().getLabel());
        ((TextView) view.findViewById(R.id.completion)).setText(
            NumberFormat.getPercentInstance().format(
                unlock.getCriteria().getCalculatedCompletion()
            )
        );

        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress);
        progressBar.setMax(100);
        progressBar.setProgress((int)unlock.getCriteria().getCompletion());
    }

    private int fetchLayoutForType(final int viewType) {
        if (viewType == VIEW_TYPE_HEADING) {
            return R.layout.list_item_unlocks_heading;
        } else {
            return R.layout.list_item_unlocks;
        }
    }
}
