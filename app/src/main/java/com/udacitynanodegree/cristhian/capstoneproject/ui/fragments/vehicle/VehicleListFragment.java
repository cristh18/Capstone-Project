package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.vehicle;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentVehicleListBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.adapters.GenericAdapter;
import com.udacitynanodegree.cristhian.capstoneproject.ui.factories.GenericAdapterFactory;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.SimpleDividerItemDecoration;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.items.VehicleItemView;

import java.util.ArrayList;
import java.util.List;

public class VehicleListFragment extends FragmentView implements View.OnClickListener {

    private FragmentVehicleListBinding vehicleListBinding;
    private GenericAdapter adapter;
    private List<Vehicle> stockVehicles;
    private final static String ARG_VEHICLES = "ARG_VEHICLES";
    private VehicleListListener vehicleListListener;

    public static VehicleListFragment newInstance(List<Vehicle> vehicles) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARG_VEHICLES, (ArrayList<? extends Parcelable>) vehicles);
        VehicleListFragment vehicleListFragment = new VehicleListFragment();
        vehicleListFragment.setArguments(bundle);
        return vehicleListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vehicleListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_vehicle_list, container, false);
        stockVehicles = getArguments().getParcelableArrayList(ARG_VEHICLES);
        init();
        buildView();
        return vehicleListBinding.getRoot();
    }

    private void init() {
        initRecyclerViewAdapter();
        initListeners();
    }

    private void initRecyclerViewAdapter() {
        adapter = new GenericAdapter(new GenericAdapterFactory() {
            @Override
            public GenericItemView onCreateViewHolder(ViewGroup parent, int viewType) {
                return new VehicleItemView(parent.getContext());
            }
        });
    }

    private void initListeners() {
        vehicleListBinding.buttonSignOut.setOnClickListener(this);
    }

    private void buildView() {
        vehicleListBinding.recyclerViewMyVehicles.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        vehicleListBinding.recyclerViewMyVehicles.setVerticalScrollBarEnabled(true);
        vehicleListBinding.recyclerViewMyVehicles.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        vehicleListBinding.recyclerViewMyVehicles.setHasFixedSize(true);
        vehicleListBinding.recyclerViewMyVehicles.setVerticalScrollBarEnabled(true);
        vehicleListBinding.recyclerViewMyVehicles.setAdapter(adapter);
        adapter.setItems(getVehicleItems(stockVehicles));
    }


    private List<? extends GenericItem> getVehicleItems(List<Vehicle> items) {
        List<? extends GenericItem> vehicleItems = items;
        return vehicleItems;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        vehicleListListener = (VehicleListListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public String getName() {
        return VehicleListFragment.class.getName();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_out:
                vehicleListListener.onSignOut();
                break;
        }
    }

    public interface VehicleListListener {
        void onSignOut();
    }
}
