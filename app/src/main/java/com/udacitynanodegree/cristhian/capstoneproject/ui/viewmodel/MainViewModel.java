package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.persistence.repository.DefaultVehicleRegistration;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.MainActivity;
import com.udacitynanodegree.cristhian.capstoneproject.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainViewModel implements ChildEventListener {

    private MainActivity mainActivity;
    private boolean searchMyVehicles;

    public MainViewModel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        searchMyVehicles = false;
        getStockVehicles();
    }

    private void getStockVehicles() {
        if (mainActivity.getStockVehicles() == null) {
            mainActivity.setStockVehicles(new ArrayList<>());
        }
        mainActivity.showProgressDialog(mainActivity.getString(R.string.copy_logging_user));
        //IronHideApplication.getFirebaseDatabase().getReference().child("").addChildEventListener(this);
        IronHideApplication.getFirebaseDatabase().getReference().child("vehicles").addChildEventListener(this);
    }

    public void getMyVehicles() {
        searchMyVehicles = true;
        mainActivity.showProgressDialog(mainActivity.getString(R.string.copy_logging_user));
        IronHideApplication.getFirebaseDatabase().getReference().child("users").addChildEventListener(this);
    }

    private void searchStockVehicles(DataSnapshot dataSnapshot) {
        Vehicle defaultVehicle = dataSnapshot.getValue(Vehicle.class);
        mainActivity.getStockVehicles().add(defaultVehicle);
        DefaultVehicleRegistration defaultVehicleRegistration = DefaultVehicleRegistration.getDefaultVehicleRegistrationInstance();
        defaultVehicleRegistration.insertDefaultVehicle(defaultVehicle);

        mainActivity.showMyVehicles();
        mainActivity.dismissProgressDialog();
//        IronHideApplication.getFirebaseDatabase().getReference().child("vehicles").removeEventListener(this);
    }

    private void searchMyVehicles(DataSnapshot dataSnapshot) {
        LogUtil.e(MainViewModel.class.getName(), "SEARCH MY VEHICLES: ".concat(dataSnapshot.toString()));
        for (DataSnapshot child : dataSnapshot.child("vehicles").getChildren()) {
            LogUtil.e(MainViewModel.class.getName(), "CHILD - SEARCH MY VEHICLES: ".concat(child.toString()));
//            VehicleCollection vehicleCollection = child.getValue(VehicleCollection.class);
//            mainActivity.getMyVehicles().add((Vehicle) vehicleCollection.getMyVehicles().values());
            Map<String, Object> objectMap = (HashMap<String, Object>) child.getValue();
            LogUtil.e(MainViewModel.class.getName(), "CHILD - FINISH");
        }
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        /*for (DataSnapshot child : dataSnapshot.getChildren()) {
            mainActivity.getStockVehicles().add(child.getValue(Vehicle.class));
            DefaultVehicleRegistration defaultVehicleRegistration = DefaultVehicleRegistration.getDefaultVehicleRegistrationInstance();
            defaultVehicleRegistration.insertDefaultVehicle(child.getValue(Vehicle.class));
        }*/

        if (searchMyVehicles) {
            searchMyVehicles(dataSnapshot);
        } else {
            searchStockVehicles(dataSnapshot);
        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
