package com.udacitynanodegree.cristhian.capstoneproject.persistence.repository;

import android.content.ContentValues;
import android.net.Uri;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.persistence.database.IronHideDBHelper;

public class DefaultVehicleRegistration {

    private static DefaultVehicleRegistration defaultVehicleRegistrationInstance = new DefaultVehicleRegistration();

    private DefaultVehicleRegistration() {
    }

    public static DefaultVehicleRegistration getDefaultVehicleRegistrationInstance() {
        return defaultVehicleRegistrationInstance;
    }

    public void insertDefaultVehicle(Vehicle vehicle) {
        String[] columns = IronHideApplication.getApp().getResources().getStringArray(R.array.default_vehicle_table_columns);
        ContentValues values = new ContentValues();
        values.put(columns[1], vehicle.getYear());
        values.put(columns[2], vehicle.getMake());
        values.put(columns[3], vehicle.getModel());
        values.put(columns[4], vehicle.getSubmodel());
        values.put(columns[5], vehicle.getEngine());
        values.put(columns[6], vehicle.getImage());
        values.put(columns[7], vehicle.getDescription());

        Uri uri = IronHideApplication.getApp().getContentResolver().insert(
                IronHideDBHelper.getContentUri2(IronHideApplication.getApp(), IronHideApplication.getApp().getString(R.string.path_default_vehicle_table)), values);
    }
}
