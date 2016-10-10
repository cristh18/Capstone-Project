package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.vehicle;

import android.view.View;

import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.MainActivity;

public class VehicleListViewModel {

    private MainActivity mainActivity;

    public VehicleListViewModel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void addVehicle(View view) {
        mainActivity.registerVehicle();
    }

    public void signOut(View view) {
        mainActivity.signOut();
    }
}
