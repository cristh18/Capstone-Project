package com.udacitynanodegree.cristhian.capstoneproject.persistence.loader;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.persistence.database.IronHideDBHelper;
import com.udacitynanodegree.cristhian.capstoneproject.persistence.queries.DefaultVehicleQuery;

public class DefaultVehicleLoader extends CursorLoader {
    public static DefaultVehicleLoader getDefaultVehicles(Context context) {
        Uri uri = IronHideDBHelper.getContentUri2(context, context.getString(R.string.path_default_vehicle_table));
        return new DefaultVehicleLoader(context, uri);
    }

    public static DefaultVehicleLoader getDefaultVehicleById(Context context, long itemId) {
        Uri contentUri = IronHideDBHelper.getContentUri2(context, context.getString(R.string.path_default_vehicle_table));
        return new DefaultVehicleLoader(context, IronHideDBHelper.buildUri2(contentUri, itemId));
    }

    private DefaultVehicleLoader(Context context, Uri uri) {
        super(context, uri, DefaultVehicleQuery.PROJECTION, null, null, null);
    }
}
