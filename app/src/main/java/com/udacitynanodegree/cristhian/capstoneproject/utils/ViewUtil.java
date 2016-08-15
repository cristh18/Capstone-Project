package com.udacitynanodegree.cristhian.capstoneproject.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.view.View;

public class ViewUtil {

    public static void setBackgroundView(View view, Drawable drawable) {
        if (drawable != null) {
            if (isMinLollipop()) {
                view.setBackgroundDrawable(drawable);
            } else {
                view.setBackground(drawable);
            }
        }
    }

    public static Drawable getDrawable(Context context, int resourceId) {
        if (isMinLollipop()) {
            return context.getDrawable(resourceId);
        } else {
            return context.getResources().getDrawable(resourceId);
        }
    }

    public static float[] getARBGFromColor(int color) {
        float[] argb = new float[4];
        argb[0] = (color >> 24) & 0xFF;
        argb[1] = Color.red(color);
        argb[2] = Color.green(color);
        argb[3] = Color.blue(color);

        return argb;
    }

    public static boolean isMinLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isMinJellyBeen() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isMinMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static Drawable getDrawableVector(Context context, int srcImage) {
        Drawable icon;
        if (isMinMarshmallow()) {
            icon = context.getResources().getDrawable(srcImage, context.getTheme());
        } else {
            icon = VectorDrawableCompat.create(context.getResources(), srcImage, context.getTheme());
        }
        return icon;
    }
}
