package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.vehicle;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewCompat;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentVehicleDescriptionBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.VehicleDetailActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.vehicle.VehicleDescriptionViewModel;

public class VehicleDescriptionFragment extends FragmentView
        implements AppBarLayout.OnOffsetChangedListener {

    private static final String ARG_VEHICLE = "ARG_VEHICLE";
    private FragmentVehicleDescriptionBinding vehicleDescriptionBinding;
    private VehicleDescriptionViewModel vehicleDescriptionViewModel;
    private Vehicle vehicle;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;
    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;

    public static VehicleDescriptionFragment newInstance(Vehicle vehicle) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_VEHICLE, vehicle);
        VehicleDescriptionFragment vehicleDescriptionFragment = new VehicleDescriptionFragment();
        vehicleDescriptionFragment.setArguments(bundle);
        return vehicleDescriptionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vehicleDescriptionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_vehicle_description, container, false);
        vehicle = getArguments().getParcelable(ARG_VEHICLE);
        init();
        buildView();
        return vehicleDescriptionBinding.getRoot();
    }

    private void init() {
        vehicleDescriptionViewModel = new VehicleDescriptionViewModel((VehicleDetailActivity) getActivity());
        vehicleDescriptionBinding.setViewModelVehicle(this);
        vehicleDescriptionBinding.setViewModel(vehicleDescriptionViewModel);
        initListeners();
    }

    private void initListeners() {
        vehicleDescriptionBinding.appBarLayoutDetailVehicle.addOnOffsetChangedListener(this);
    }

    private void buildView() {
        vehicleDescriptionBinding.textViewLineVehicle.setMovementMethod(new LinkMovementMethod());
    }


    @Override
    public String getName() {
        return VehicleDescriptionFragment.class.getName();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (mMaxScrollSize == 0) {
            mMaxScrollSize = appBarLayout.getTotalScrollRange();
        }

        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100
                / mMaxScrollSize;

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;
                ViewCompat.animate(vehicleDescriptionBinding.fabShareVehicle).scaleY(0).scaleX(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(vehicleDescriptionBinding.fabShareVehicle).scaleY(1).scaleX(1).start();
            }
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void shareArticle(View view) {
        String TEXT_PLAIN = "text/plain";
        startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(getActivity())
                .setType(TEXT_PLAIN)
                .setText(vehicle.getUrl())
                .getIntent(), getString(R.string.action_share)));
    }
}
