package com.udacitynanodegree.cristhian.capstoneproject.ui.views.items;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.ItemVehicleBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;

public class VehicleItemView extends LinearLayout implements GenericItemView, View.OnClickListener {

    private ItemVehicleBinding itemVehicleBinding;
    private Vehicle vehicle;
    private Context context;

    public VehicleItemView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public VehicleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public VehicleItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemVehicleBinding = DataBindingUtil.inflate(inflater, R.layout.item_vehicle, this, true);
        setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        initListener();
    }

    private void initListener() {
        itemVehicleBinding.containerVehicleItem.setOnClickListener(this);
    }

    @Override
    public void bind(GenericItem item) {
        vehicle = (Vehicle) item;
        itemVehicleBinding.textViewVehicleName.setText(vehicle.toString());
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "FIRST AUTOPART: ".concat(vehicle.getAutoParts().get(0).getName()), Toast.LENGTH_LONG).show();
    }
}
