package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.vehicle;

import android.view.View;
import android.widget.Toast;

import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.MainActivity;

public class VehicleItemViewModel {

    private MainActivity mainActivity;
    private Vehicle vehicle;


    public VehicleItemViewModel(MainActivity mainActivity, Vehicle vehicle) {
        this.mainActivity = mainActivity;
        this.vehicle = vehicle;
    }

    public void selectVehicle(View view) {
        if (vehicle.getAutoParts() != null && !vehicle.getAutoParts().isEmpty()) {
            mainActivity.showVehicleDescription(vehicle);
        } else {
            Toast.makeText(IronHideApplication.getApp().getApplicationContext(), "THIS VEHICLE HAS NOT AUTOPARTS", Toast.LENGTH_LONG).show();
        }
    }

}
