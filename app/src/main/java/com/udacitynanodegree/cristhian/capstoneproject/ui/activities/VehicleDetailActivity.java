package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.ActivityVehicleDetailBinding;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.PagerFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.vehicle.VehicleDescriptionFragment;

public class VehicleDetailActivity extends BaseFragmentActivity {

    private ActivityVehicleDetailBinding vehicleDetailBinding;
    private Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_activities, R.anim.slide_out_activities);
        vehicleDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle_detail);
        Intent intent = getIntent();
        vehicle = intent.getParcelableExtra("ARG_VEHICLE");
        VehicleDescriptionFragment vehicleDescriptionFragment = VehicleDescriptionFragment.newInstance(vehicle);
        addFragment(vehicleDescriptionFragment);
    }

    public void showPartCategories() {
        PagerFragment pagerFragment = PagerFragment.newInstance(vehicle.getAutoParts());
        addFragment(pagerFragment);
    }

}
