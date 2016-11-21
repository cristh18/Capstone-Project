package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.persistence.repository.DefaultVehicleRegistration;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel implements ChildEventListener {

    private MainActivity mainActivity;

    public MainViewModel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
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

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        /*for (DataSnapshot child : dataSnapshot.getChildren()) {
            mainActivity.getStockVehicles().add(child.getValue(Vehicle.class));
            DefaultVehicleRegistration defaultVehicleRegistration = DefaultVehicleRegistration.getDefaultVehicleRegistrationInstance();
            defaultVehicleRegistration.insertDefaultVehicle(child.getValue(Vehicle.class));
        }*/
        Vehicle defaultVehicle = dataSnapshot.getValue(Vehicle.class);
        mainActivity.getStockVehicles().add(defaultVehicle);
        DefaultVehicleRegistration defaultVehicleRegistration = DefaultVehicleRegistration.getDefaultVehicleRegistrationInstance();
        defaultVehicleRegistration.insertDefaultVehicle(defaultVehicle);

        mainActivity.showMyVehicles();
        mainActivity.dismissProgressDialog();
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
