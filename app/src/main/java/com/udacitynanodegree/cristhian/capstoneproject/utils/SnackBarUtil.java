package com.udacitynanodegree.cristhian.capstoneproject.utils;

import android.view.View;

import com.androidadvance.topsnackbar.TSnackbar;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.ColoredSnackbar;

public class SnackBarUtil {

    public static void showSnackBar(View view, String message, int duration, boolean isError) {
        TSnackbar snackbar = TSnackbar.make(view, message, duration);
        if (isError) {
            ColoredSnackbar.error(snackbar).show();
        } else {
            ColoredSnackbar.success(snackbar).show();
        }
    }
}
