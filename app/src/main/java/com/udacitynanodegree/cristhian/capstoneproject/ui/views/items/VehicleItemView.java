package com.udacitynanodegree.cristhian.capstoneproject.ui.views.items;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.ItemVehicleBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.MainActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.vehicle.VehicleItemViewModel;

public class VehicleItemView extends LinearLayout implements GenericItemView {

    private ItemVehicleBinding itemVehicleBinding;
    private MainActivity mainActivity;
    private Context context;
    private Vehicle vehicle;

    private VehicleItemViewModel vehicleItemViewModel;

    public VehicleItemView(Context context, MainActivity mainActivity) {
        super(context);
        this.context = context;
        this.mainActivity = mainActivity;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemVehicleBinding = DataBindingUtil.inflate(inflater, R.layout.item_vehicle, this, true);
        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void bind(GenericItem item) {
        vehicle = (Vehicle) item;
        itemVehicleBinding.textViewVehicleName.setText(vehicle.toString());
        setViewModel();
    }

    private void setViewModel() {
        vehicleItemViewModel = new VehicleItemViewModel(getContext(), mainActivity, vehicle);
        itemVehicleBinding.setViewModel(vehicleItemViewModel);
    }
}
