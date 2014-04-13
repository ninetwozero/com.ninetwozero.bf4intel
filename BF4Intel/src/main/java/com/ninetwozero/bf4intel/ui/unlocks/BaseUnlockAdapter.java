package com.ninetwozero.bf4intel.ui.unlocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.resources.maps.UnlockCriteriaStringMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentStringMap;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

import java.util.Locale;

public abstract class BaseUnlockAdapter<T> extends BaseIntelAdapter<T> {
    public BaseUnlockAdapter(final Context context) {
        super(context);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        throw new UnsupportedOperationException(
            "You need to implement getChildView(...) in " + getClass().getSimpleName()
        );
    }

    protected String resolveCriteriaLabel(final UnlockCriteria criteria) {
        if (criteria.isScoreCriteria()) {
            final int resource = UnlockCriteriaStringMap.get(criteria.getLabel());
            final int currentValue = criteria.getCurrentValue();
            final int targetValue = criteria.getTargetValue();
            return String.format(
                    context.getString(resource),
                    NumberFormatter.format(currentValue < targetValue ? currentValue : targetValue),
                    NumberFormatter.format(targetValue)
            );
        } else {
            final String label = criteria.getLabel().toLowerCase(Locale.getDefault());
            final String labelPrefix = label.substring(0, label.lastIndexOf("_"));
            if (labelPrefix.contains("as") || labelPrefix.contains("xp23")) {
                return String.format(
                        context.getString(R.string.unlock_complete_as),
                        context.getString(AssignmentStringMap.get(criteria.getAward().getName()))
                );
            }
        }
        return criteria.getLabel();
    }

    protected void setProgressText(View convertView, int textViewId, UnlockCriteria criteria) {
        final String text = resolveCriteriaLabel(criteria);
        setText(convertView, textViewId, text);
    }

    @Override
    public void setImage(View view, int resourceId, int imageResource) {
        super.setImage(view, resourceId, imageResource, R.drawable.kit_none);
    }

    protected abstract int getCategoryString(final String key);
}
