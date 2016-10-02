package com.udacitynanodegree.cristhian.capstoneproject.utils;


import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class FontCache {

    public static final String BOLD = "bold";
    public static final String LIGHT = "light";
    public static final String REGULAR = "regular";

    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    public static void add(String key, String name, Context context) {

        Typeface tf = fontCache.get(key);

        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            } catch (Exception e) {
                LogUtil.e("FontCache", e.toString());
            }
            fontCache.put(key, tf);
        }
    }

    public static Typeface get(String key) {
        return fontCache.get(key);
    }


}