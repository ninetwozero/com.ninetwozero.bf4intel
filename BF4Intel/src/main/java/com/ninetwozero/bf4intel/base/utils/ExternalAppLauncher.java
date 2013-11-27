package com.ninetwozero.bf4intel.base.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class ExternalAppLauncher {
    public static Intent getIntent(final Activity activity, final String packageName) {
        final PackageManager manager = activity.getPackageManager();
        if (hasPackageInstalled(manager, packageName)) {
            return manager.getLaunchIntentForPackage(packageName);
        } else {
            final Uri marketUri = Uri.parse("market://details?id=" + packageName);
            return new Intent(Intent.ACTION_VIEW, marketUri);
        }
    }

    private static boolean hasPackageInstalled(final PackageManager manager, final String packageName) {
        try {
            manager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException ex) {
            return false;
        }
    }
}
