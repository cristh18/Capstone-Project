package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.ActivityMainBinding;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.vehicle.RegisterVehicleFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.vehicle.VehicleListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity implements
        View.OnClickListener,
        RegisterVehicleFragment.RegisterVehicleListener,
        VehicleListFragment.VehicleListListener,
        ChildEventListener {

    private final static String TAG = MainActivity.class.getName();
    private ActivityMainBinding mainBinding;

    private final int SPLASH_REQUEST_CODE = 0;
    private final int INTRO_REQUEST_CODE = 1;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private List<Vehicle> vehicles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_activities, R.anim.slide_out_activities);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        startActivityForResult(new Intent(this, SplashActivity.class), SPLASH_REQUEST_CODE);
    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SPLASH_REQUEST_CODE) {
            manageOnResultSplash(resultCode);
        } else if (requestCode == INTRO_REQUEST_CODE) {
            manageOnResultIntro();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void manageOnResultSplash(int resultCode) {
        if (resultCode == RESULT_OK) {
            startActivityForResult(new Intent(this, AccountActivity.class), INTRO_REQUEST_CODE);
        } else {
            finish();
        }
    }

    private void manageOnResultIntro() {
        init();
        getStockVehicles();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }


    private void signOut() {
        firebaseAuth.signOut();
        goToAccountActivity();
    }

    private void goToAccountActivity() {
        startActivityForResult(new Intent(this, AccountActivity.class), INTRO_REQUEST_CODE);
    }

    private void getStockVehicles() {
        vehicles = new ArrayList<>();
        mDatabase.child("").addChildEventListener(this);
    }

    @Override
    public void onBackRegisterVehicle() {

    }

    @Override
    public void onFinish() {
        Toast.makeText(this, "USER REGISTERED", Toast.LENGTH_SHORT).show();
    }

    private void validateVehicles() {
        if (!vehicles.isEmpty()) {
//            showMyVehicles();
            addFragment(new RegisterVehicleFragment());
        } else {
            addFragment(new RegisterVehicleFragment());
        }
    }

    private void showMyVehicles() {
        VehicleListFragment vehicleListFragment = VehicleListFragment.newInstance(vehicles);
        addFragment(vehicleListFragment);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            vehicles.add(child.getValue(Vehicle.class));
        }
        validateVehicles();
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

    @Override
    public void onSignOut() {
        signOut();
    }
}
