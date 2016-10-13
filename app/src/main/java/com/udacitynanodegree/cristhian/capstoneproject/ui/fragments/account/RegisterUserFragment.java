package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentRegisterUserBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.AccountActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.account.RegisterUserViewModel;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.HeaderMainView;

public class RegisterUserFragment extends FragmentView implements
        HeaderMainView.HeaderMainListener {

    private FragmentRegisterUserBinding registerUserBinding;
    private RegisterUserViewModel registerUserViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_user, container, false);
        init();
        return registerUserBinding.getRoot();
    }

    private void init() {
        initListeners();
        registerUserViewModel = new RegisterUserViewModel(this, (AccountActivity) getActivity());
        registerUserBinding.setViewModel(registerUserViewModel);
    }

    private void initListeners() {
        registerUserBinding.headerMainViewRegisterUser.setHeaderMainListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getName() {
        return RegisterUserFragment.class.getName();
    }

    @Override
    public void onClickBackHeader() {
        close();
    }

    @Override
    public void onClickRightHeader() {

    }

    public FragmentRegisterUserBinding getRegisterUserBinding() {
        return registerUserBinding;
    }
}
