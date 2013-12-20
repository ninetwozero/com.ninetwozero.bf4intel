package com.ninetwozero.bf4intel.ui.unlocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseExpandableIntelAdapter;
import com.ninetwozero.bf4intel.json.unlocks.ScoreCriteria;

import java.util.List;
import java.util.Map;

public abstract class BaseUnlockAdapter<T> extends BaseExpandableIntelAdapter<T> {
    public BaseUnlockAdapter(final Map<String, List<T>> itemMap, final Context context) {
        super(itemMap, context);
    }

    @Override
    public View getGroupView(final int position, final boolean isExpanded, View convertView, final ViewGroup viewGroup) {
        final String title = getGroup(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_unlocks_heading, viewGroup, false);
        }
        setText(convertView, R.id.text1, title);
        return convertView;
    }

    @Override
    public View getChildView(final int group, final int child, final boolean isLastChild, final View convertView, final ViewGroup viewGroup) {
        throw new UnsupportedOperationException(
            "You need to implement getChildView(...) in " + getClass().getSimpleName()
        );
    }

    protected abstract String resolveCriteriaLabel(final ScoreCriteria criteria);
}
