package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.vehicle;

import android.support.v4.util.ArrayMap;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.model.AutoPart;
import com.udacitynanodegree.cristhian.capstoneproject.model.Person;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.MainActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.vehicle.RegisterVehicleFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.account.LoginViewModel;
import com.udacitynanodegree.cristhian.capstoneproject.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterVehicleViewModel implements ChildEventListener {

    private MainActivity mainActivity;
    private RegisterVehicleFragment registerVehicleFragment;
    private ArrayMap<String, Person> users;
    private ArrayMap<String, AutoPart> autoParts;
    private boolean searchAutoParts;
    private Vehicle vehicle;
    public static int count = 0;

    public RegisterVehicleViewModel(MainActivity mainActivity, RegisterVehicleFragment registerVehicleFragment) {
        this.mainActivity = mainActivity;
        this.registerVehicleFragment = registerVehicleFragment;
        getRegisteredUsers();
    }

    private void getRegisteredUsers() {
        searchAutoParts = false;
        IronHideApplication.getFirebaseDatabase().getReference().child("").addChildEventListener(this);
    }

    public void registerVehicle(View view) {
        getVehicle();
        saveVehicle();
        getVehicleAutoParts();

    }

    private void saveVehicle() {
        String uId = IronHideApplication.getFirebaseAuth().getCurrentUser().getUid();
        if (users.get(uId) != null) {
            Map<String, Object> vehicleValues = vehicle.toMap();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/users/" + uId + "/" + "vehicles/" + vehicle.hashCode(), vehicleValues);
            IronHideApplication.getFirebaseDatabase().getReference().updateChildren(childUpdates);
            // IronHideApplication.getFirebaseDatabase().getReference().child("users").child(uId).child("vehicles").setValue(vehicle);
        }
    }

    private void saveAutoParts(List<AutoPart> autoParts) {
        String uId = IronHideApplication.getFirebaseAuth().getCurrentUser().getUid();
        if (autoParts != null && !autoParts.isEmpty() && users.get(uId) != null) {
            int autoPartsNumber = autoParts.size();
            for (int i = 0; i < autoPartsNumber; i++) {
                Map<String, Object> autoPartValues = autoParts.get(i).toMap();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/users/"
                        .concat(uId)
                        .concat("/vehicles/")
                        .concat(String.valueOf(vehicle.hashCode()))
                        .concat("/autoParts/")
                        .concat(String.valueOf(autoParts.get(i).hashCode() + i)), autoPartValues);
                IronHideApplication.getFirebaseDatabase().getReference().updateChildren(childUpdates);
            }
        }

    }

    private void getVehicle() {
        vehicle = new Vehicle();
        vehicle.setMake((String) registerVehicleFragment.getRegisterVehicleBinding().spinnerVehicleMake.getSelectedItem());
        vehicle.setModel(registerVehicleFragment.getRegisterVehicleBinding().editTextVehicleModel.getEditText().getText().toString());
        vehicle.setSubmodel(registerVehicleFragment.getRegisterVehicleBinding().editTextVehicleSubmodel.getEditText().getText().toString());
        vehicle.setYear(registerVehicleFragment.getRegisterVehicleBinding().editTextVehicleYear.getEditText().getText().toString());
        vehicle.setEngine(registerVehicleFragment.getRegisterVehicleBinding().editTextVehicleEngine.getEditText().getText().toString());
    }

    private void getVehicleAutoParts() {
        searchAutoParts = true;
        IronHideApplication.getFirebaseDatabase().getReference().child("vehicles").addChildEventListener(this);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        if (searchAutoParts) {
            searchVehicleAutoParts(dataSnapshot);
        } else {
            searchUsers(dataSnapshot);
        }
    }

    private void searchUsers(DataSnapshot dataSnapshot) {
        users = new ArrayMap<>();
        try {
            for (DataSnapshot child : dataSnapshot.getChildren()) {
                users.put(child.getKey(), child.getValue(Person.class));
            }
        } catch (ClassCastException | DatabaseException e) {
            users = new ArrayMap<>();
            LogUtil.e(LoginViewModel.class.getName(), e.getMessage());
        }
    }

    private void searchVehicleAutoParts(DataSnapshot dataSnapshot) {
        autoParts = new ArrayMap<>();
        for (DataSnapshot child : dataSnapshot.child("autoParts").getChildren()) {
            autoParts.put(child.getKey(), child.getValue(AutoPart.class));
        }
        if (!autoParts.values().isEmpty()) {
//            vehicle.setAutoParts(new ArrayList<>(autoParts.values()));
            saveAutoParts(new ArrayList<>(autoParts.values()));
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
