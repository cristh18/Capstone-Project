package com.udacitynanodegree.cristhian.capstoneproject.ui.views.items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.VehicleListener;
import com.udacitynanodegree.cristhian.capstoneproject.managers.persistence.models.Vehicle;

public class VehicleItemView extends LinearLayout implements GenericItemView, View.OnClickListener {

    private VehicleListener vehicleListener;
    private Vehicle vehicle;
    private TextView textViewVehicleName;

    public VehicleItemView(Context context) {
        super(context);
        init();
    }

    public VehicleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VehicleItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.item_vehicle, this);
        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        initViews();
        initListener();
    }

    private void initViews(){
        textViewVehicleName = (TextView)findViewById(R.id.textView_vehicle_name);
    }

    private void initListener() {

    }

    public void setVehicleListener(VehicleListener vehicleListener) {
        this.vehicleListener = vehicleListener;
    }

    @Override
    public void bind(GenericItem item) {
        vehicle = (Vehicle) item;
        textViewVehicleName.setText(vehicle.getMake());
    }

    @Override
    public void onClick(View v) {

    }
}
