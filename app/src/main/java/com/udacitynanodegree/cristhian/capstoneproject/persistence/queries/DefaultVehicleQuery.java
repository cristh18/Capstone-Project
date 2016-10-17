package com.udacitynanodegree.cristhian.capstoneproject.persistence.queries;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;

public interface DefaultVehicleQuery {
    String[] PROJECTION = IronHideApplication.getApp().getResources().getStringArray(R.array.default_vehicle_table_columns);

    int VEHICLE_ID = 0;
    int YEAR = 1;
    int MAKE = 2;
    int MODEL = 3;
    int SUBMODEL = 4;
    int ENGINE = 5;
    int IMAGE = 6;
    int DESCRIPTION = 7;
}
