package com.ninetwozero.bf4intel;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Bf4Intel extends Application {
    private static Bf4Intel instance;

    public static Context getContext() {
        return instance;
    }

    public static boolean isConnectedToNetwork() {
        final ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo network = manager.getActiveNetworkInfo();
        return network != null && network.isConnected();
    }

    public static boolean isServiceRunning(final String className) {
        final ActivityManager manager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (className.equals(service.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
