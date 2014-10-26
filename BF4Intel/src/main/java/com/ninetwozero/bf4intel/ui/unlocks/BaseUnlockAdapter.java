package com.ninetwozero.bf4intel.ui.unlocks;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseFilterableIntelAdapter;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;

public abstract class BaseUnlockAdapter<T> extends BaseFilterableIntelAdapter<T> {
    public BaseUnlockAdapter(final Context context) {
        super(context);
    }

    protected void displayInformationForCriteria(final View view, final UnlockCriteria criteria) {
        setProgress(view, R.id.unlock_completion, criteria.getCompletion(), 100);
        setVisibility(view, R.id.unlock_status_icon, criteria.isCompleted() ? View.VISIBLE : View.INVISIBLE);
    }
    protected void displayInformationForCriteria(final UnlockHolder holder, final UnlockCriteria criteria) {
        holder.unlockCompletion.setProgress(criteria.getCompletion());
        holder.unlockStatusIcon.setVisibility(criteria.isCompleted() ? View.VISIBLE : View.INVISIBLE);
    }

    protected static class UnlockHolder {
        public ProgressBar unlockCompletion;
        public ImageView unlockStatusIcon;
    }
}
