package com.ninetwozero.bf4intel.interfaces;


import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public interface EventLayout<T extends BaseEvent> {
    public void populateView(final Context context, final View view, final T event);
}
