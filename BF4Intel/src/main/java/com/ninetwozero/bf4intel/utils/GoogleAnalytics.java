package com.ninetwozero.bf4intel.utils;

import android.content.Context;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;

public class GoogleAnalytics {

    public static void post(Context context, String fragmentName) {
        EasyTracker tracker = EasyTracker.getInstance(context);
        tracker.set(Fields.SCREEN_NAME, fragmentName);
        tracker.send(MapBuilder.createAppView().build());
    }
}
