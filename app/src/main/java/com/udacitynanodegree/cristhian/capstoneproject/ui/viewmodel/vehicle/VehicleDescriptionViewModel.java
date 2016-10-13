package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.vehicle;

import android.view.View;

import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.VehicleDetailActivity;

public class VehicleDescriptionViewModel {

    private VehicleDetailActivity vehicleDetailActivity;

    public VehicleDescriptionViewModel(VehicleDetailActivity vehicleDetailActivity) {
        this.vehicleDetailActivity = vehicleDetailActivity;
    }

    public void showVehicleParts(View view) {
        vehicleDetailActivity.showPartCategories();
    }
}
