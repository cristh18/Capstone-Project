package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.vehicle;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.MainActivity;

public class VehicleItemViewModel {

    private Context context;
    private MainActivity mainActivity;
    private Vehicle vehicle;


    public VehicleItemViewModel(Context context, MainActivity mainActivity, Vehicle vehicle) {
        this.context = context;
        this.mainActivity = mainActivity;
        this.vehicle = vehicle;
    }

    public void selectVehicle(View view) {
        if (vehicle.getAutoParts() != null && !vehicle.getAutoParts().isEmpty()) {
            Toast.makeText(context, "FIRST AUTOPART: ".concat(vehicle.getAutoParts().get(0).getName()), Toast.LENGTH_LONG).show();
            mainActivity.showVehicleDescription(vehicle);
        } else {
            Toast.makeText(context, "THIS VEHICLE HAS NOT AUTOPARTS", Toast.LENGTH_LONG).show();
        }
    }

}
