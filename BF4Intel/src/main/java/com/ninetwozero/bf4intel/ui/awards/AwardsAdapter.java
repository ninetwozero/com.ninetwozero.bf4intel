package com.ninetwozero.bf4intel.ui.awards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ninetwozero.bf4intel.json.awards.Awards;

import java.util.List;

public class AwardsAdapter extends BaseAdapter {

    private final List<Awards> awards;
    private final Context context;

    public AwardsAdapter(List<Awards> awards, Context context) {
        this.awards = awards;
        this.context = context;
    }

    @Override
    public int getCount() {
        return awards.size();
    }

    @Override
    public Object getItem(int position) {
        return awards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
