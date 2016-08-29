package com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets;

import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.udacitynanodegree.cristhian.capstoneproject.R;

public class ColoredSnackbar {

    private static final int red = 0xfff44336;
    private static final int green = 0xff4caf50;
    private static final int blue = 0xff2195f3;
    private static final int orange = 0xffffc107;

    private static View getSnackBarLayout(TSnackbar snackbar) {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }

    private static TSnackbar colorSnackBar(TSnackbar snackbar, int colorId) {

        View snackBarView = getSnackBarLayout(snackbar);

        if (snackBarView != null) {
            snackBarView.setBackgroundColor(colorId);
            TextView tv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(ContextCompat.getColor(tv.getContext(), R.color.white));
        }

        return snackbar;
    }

    public static TSnackbar info(TSnackbar snackbar) {
        return colorSnackBar(snackbar, blue);
    }

    public static TSnackbar warning(TSnackbar snackbar) {
        return colorSnackBar(snackbar, orange);
    }

    public static TSnackbar error(TSnackbar snackbar) {
        return colorSnackBar(snackbar, red);
    }

    public static TSnackbar success(TSnackbar snackbar) {
        return colorSnackBar(snackbar, green);
    }
}