package com.udacitynanodegree.cristhian.capstoneproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

public class OrientationUtil {

    public static void adjustScreenOrientation(Activity activity) {
        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        if (isLarge(activity)) {
            orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }
        activity.setRequestedOrientation(orientation);
    }

    public static boolean isLarge(Context context) {
        return (getScreenSize(context)) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static int getScreenSize(Context context) {
        return context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
    }

}
