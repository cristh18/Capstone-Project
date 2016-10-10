package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.vehicle;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentRegisterVehicleBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.HeaderMainView;

import java.util.ArrayList;
import java.util.List;

public class RegisterVehicleFragment extends FragmentView implements
        HeaderMainView.HeaderMainListener,
        AdapterView.OnItemSelectedListener,
        View.OnClickListener{

    private static final String ARG_VEHICLES = "ARG_VEHICLES";
    private FragmentRegisterVehicleBinding registerVehicleBinding;
    private RegisterVehicleListener registerVehicleListener;
    private List<Vehicle> stockVehicles;

    public static RegisterVehicleFragment newInstance(List<Vehicle> vehicles) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARG_VEHICLES, (ArrayList<? extends Parcelable>) vehicles);
        RegisterVehicleFragment registerVehicleFragment = new RegisterVehicleFragment();
        registerVehicleFragment.setArguments(bundle);
        return registerVehicleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerVehicleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_vehicle, container, false);
        stockVehicles = getArguments().getParcelableArrayList(ARG_VEHICLES);
        init();
        buildView();
        return registerVehicleBinding.getRoot();
    }

    private void init() {
        initListeners();
    }

    private void buildView() {
        registerVehicleBinding.editTextVehicleModel.getEditText().setEnabled(false);
        registerVehicleBinding.editTextVehicleSubmodel.getEditText().setEnabled(false);
        registerVehicleBinding.editTextVehicleYear.getEditText().setEnabled(false);
        registerVehicleBinding.editTextVehicleEngine.getEditText().setEnabled(false);

        List<String> makes = new ArrayList<>(stockVehicles.size());
        for (Vehicle vehicle : stockVehicles) {
            makes.add(vehicle.getMake());
        }
        makes.add(0, getString(R.string.copy_register_vehicle_make));
        setSpinnerData(makes);
    }

    public void setSpinnerData(List<String> provinceNames) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, provinceNames) {
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                return super.getView(position, convertView, parent);
            }

            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                TextView textViewItem = (TextView) super.getView(position, convertView, parent);
                textViewItem.setCompoundDrawables(null, null, null, null);
                return textViewItem;
            }
        };

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        registerVehicleBinding.spinnerVehicleMake.setAdapter(dataAdapter);
    }

    private void initListeners() {
        registerVehicleBinding.headerMainViewRegisterVehicle.setHeaderMainListener(this);
        registerVehicleBinding.buttonRegisterVehicle.setOnClickListener(this);
        registerVehicleBinding.spinnerVehicleMake.setOnItemSelectedListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerVehicleListener = (RegisterVehicleListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getName() {
        return RegisterVehicleFragment.class.getName();
    }

    @Override
    public void onClickBackHeader() {
        close();
    }

    @Override
    public void onClickRightHeader() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_register_vehicle:
                registerVehicleListener.onRegisterVehicle();
//                close();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            setFormValues(stockVehicles.get(position - 1));
        }
    }

    private void setFormValues(Vehicle vehicle) {
        registerVehicleBinding.editTextVehicleModel.getEditText().setText(vehicle.getModel());
        registerVehicleBinding.editTextVehicleSubmodel.getEditText().setText(vehicle.getSubmodel());
        registerVehicleBinding.editTextVehicleYear.getEditText().setText(vehicle.getYear());
        registerVehicleBinding.editTextVehicleEngine.getEditText().setText(vehicle.getEngine());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface RegisterVehicleListener {
        void onRegisterVehicle();
    }
}
