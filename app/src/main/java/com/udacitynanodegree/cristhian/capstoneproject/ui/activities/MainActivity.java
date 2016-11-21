package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.ActivityMainBinding;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.persistence.database.IronHideDBHelper;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.vehicle.RegisterVehicleFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.vehicle.VehicleListFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity implements
        RegisterVehicleFragment.RegisterVehicleListener {

    private final static String TAG = MainActivity.class.getName();
    private ActivityMainBinding mainBinding;

    private final int SPLASH_REQUEST_CODE = 0;
    private final int INTRO_REQUEST_CODE = 1;
    private List<Vehicle> stockVehicles;
    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_activities, R.anim.slide_out_activities);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        IronHideDBHelper ironHideDBHelper = new IronHideDBHelper(this);
        SQLiteDatabase bd = ironHideDBHelper.getWritableDatabase();
        startActivityForResult(new Intent(this, SplashActivity.class), SPLASH_REQUEST_CODE);
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
        mainViewModel = new MainViewModel(this);
    }

    public void signOut() {
        IronHideApplication.getFirebaseAuth().signOut();
        stockVehicles = null;
        goToAccountActivity();
    }

    private void goToAccountActivity() {
        startActivityForResult(new Intent(this, AccountActivity.class), INTRO_REQUEST_CODE);
    }

    @Override
    public void onRegisterVehicle() {
        Toast.makeText(this, "VEHICLE REGISTERED", Toast.LENGTH_SHORT).show();
        showMyVehicles();
    }


    public void showMyVehicles() {
        //VehicleListFragment vehicleListFragment = VehicleListFragment.newInstance(stockVehicles);
        VehicleListFragment vehicleListFragment = VehicleListFragment.newInstance(new ArrayList<>());
        addFragment(vehicleListFragment);
    }

    public void registerVehicle() {
        RegisterVehicleFragment registerVehicleFragment = RegisterVehicleFragment.newInstance(stockVehicles);
        addFragment(registerVehicleFragment);
    }


    @Override
    public void showProgressDialog(String message) {
        super.showProgressDialog(message);
    }

    @Override
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
    }

    public List<Vehicle> getStockVehicles() {
        return stockVehicles;
    }

    public void setStockVehicles(List<Vehicle> stockVehicles) {
        this.stockVehicles = stockVehicles;
    }

    public void showVehicleDescription(Vehicle vehicle) {
        Intent intent = new Intent(this, VehicleDetailActivity.class);
        intent.putExtra("ARG_VEHICLE", vehicle);
        startActivity(intent);
        finish();
    }
}
