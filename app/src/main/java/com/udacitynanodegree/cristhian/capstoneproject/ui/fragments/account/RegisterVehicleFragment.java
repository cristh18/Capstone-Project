package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.RegisterVehicleListener;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.HeaderMainView;

public class RegisterVehicleFragment extends FragmentView implements
        HeaderMainView.HeaderMainListener,
        View.OnClickListener {

    private HeaderMainView headerMainView;
    private RegisterVehicleListener registerVehicleListener;
    private Button buttonRegisterFinish;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_vehicle, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        initViews(view);
        initListeners();
    }

    private void initViews(View view) {
        headerMainView = (HeaderMainView) view.findViewById(R.id.headerMainView_register_vehicle);
        buttonRegisterFinish = (Button) view.findViewById(R.id.button_register_finish);
    }

    private void initListeners() {
        headerMainView.setHeaderMainListener(this);
        buttonRegisterFinish.setOnClickListener(this);
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
        registerVehicleListener.onBackRegisterVehicle();
    }

    @Override
    public void onClickRightHeader() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_register_finish:
                registerVehicleListener.onFinish();
                break;
        }
    }
}
