package com.ninetwozero.bf4intel.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.dao.login.SummarizedSoldierStatsDAO;
import com.ninetwozero.bf4intel.resources.maps.profile.PlatformStringMap;
import com.ninetwozero.bf4intel.resources.maps.profile.SoldierImageMap;
import com.ninetwozero.bf4intel.resources.maps.ranks.RankImageMap;

import java.util.List;

public class SoldierSpinnerAdapter extends BaseIntelAdapter<SummarizedSoldierStatsDAO> implements SpinnerAdapter {

    public SoldierSpinnerAdapter(final Context context, final List<SummarizedSoldierStatsDAO> items) {
        super(context, items);
    }

    @Override
    public long getItemId(final int position) {
        final String id = getItem(position).getSoldierId();
        return id == null ? 0L : Long.parseLong(id);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final SummarizedSoldierStatsDAO item = getItem(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_side_soldier, parent, false);
        }

        populateBasicViews(convertView, item);
        return convertView;
    }

    @Override
    public View getDropDownView(final int position, View convertView, final ViewGroup parent) {
        final SummarizedSoldierStatsDAO item = getItem(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_side_soldier_dropdown, parent, false);
        }

        populateBasicViews(convertView, item);
        return convertView;
    }

    private void populateBasicViews(final View view, final SummarizedSoldierStatsDAO item) {
        setImage(view, R.id.soldier_image, SoldierImageMap.get(item.getPicture()));
        setText(view, R.id.soldier_name, item.getSoldierName());
        setText(view, R.id.soldier_platform, PlatformStringMap.get(item.getPlatformId()));
        setImage(view, R.id.soldier_rank,  RankImageMap.get(item.getRank()));
    }
}
