package com.udacitynanodegree.cristhian.capstoneproject.persistence.database;

import android.content.Context;
import android.net.Uri;

import com.udacitynanodegree.cristhian.capstoneproject.R;


public class DatabaseContract {

//    public static final String CONTENT_AUTHORITY = context.getString(R.string.content_authority);
//    public static final Uri BASE_CONTENT_URI = Uri.parse(context.getString(R.string.prefix_base_content_uri).concat(CONTENT_AUTHORITY));
//    public static final String PATH_USER_TABLE = context.getString(R.string.path_user_table);
//    public static final String PATH_VEHICLE_TABLE = context.getString(R.string.path_vehicle_table);
//    public static final String PATH_PART_CATEGORY_TABLE = context.getString(R.string.path_part_category_table);
//    public static final String PATH_PART_TABLE = context.getString(R.string.path_part_table);
//    public static final String PATH_DEFAULT_VEHICLE_TABLE = context.getString(R.string.path_default_vehicle_table);

    public static String getContentAuthority(Context context) {
        return context.getString(R.string.content_authority);
    }

    public static Uri getBaseContentUri(Context context) {
        return Uri.parse(context.getString(R.string.prefix_base_content_uri).concat(context.getString(R.string.content_authority)));
    }

    public static String getPathUserTable(Context context) {
        return context.getString(R.string.path_user_table);
    }

    public static String getPathVehicleTable(Context context) {
        return context.getString(R.string.path_vehicle_table);
    }

    public static String getPathPartVehicleTable(Context context) {
        return context.getString(R.string.path_part_category_table);
    }

    public static String getPathPartTable(Context context) {
        return context.getString(R.string.path_part_table);
    }

    public static String getPathDefaultVehicleTable(Context context) {
        return context.getString(R.string.path_default_vehicle_table);
    }
}

