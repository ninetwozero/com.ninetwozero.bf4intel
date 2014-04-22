package com.ninetwozero.bf4intel.ui.unlocks;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;

public abstract class BaseUnlockAdapter<T> extends BaseIntelAdapter<T> {
    public BaseUnlockAdapter(final Context context) {
        super(context);
    }

    protected void displayInformationForCriteria(final View view, final UnlockCriteria criteria) {
        setProgress(view, R.id.unlock_completion, criteria.getCompletion(), 100);
        setVisibility(view, R.id.unlock_status_icon, criteria.isCompleted() ? View.VISIBLE : View.GONE);
    }

    protected abstract int getCategoryString(final String key);
}
