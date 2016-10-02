package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentRegisterVehicleBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.HeaderMainView;

public class RegisterVehicleFragment extends FragmentView implements
        HeaderMainView.HeaderMainListener,
        View.OnClickListener {

    private FragmentRegisterVehicleBinding registerVehicleBinding;
    private RegisterVehicleListener registerVehicleListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerVehicleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_vehicle, container, false);
        init();
        buildView();
        return registerVehicleBinding.getRoot();
    }

    private void init() {
        initListeners();
    }

    private void buildView() {

    }

    private void initListeners() {
        registerVehicleBinding.headerMainViewRegisterVehicle.setHeaderMainListener(this);
        registerVehicleBinding.buttonRegisterFinish.setOnClickListener(this);
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
        close();
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

    public interface RegisterVehicleListener {
        void onBackRegisterVehicle();

        void onFinish();
    }
}
