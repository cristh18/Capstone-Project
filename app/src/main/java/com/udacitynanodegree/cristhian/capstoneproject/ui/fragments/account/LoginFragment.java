package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentSignInBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.AccountActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.account.LoginViewModel;

public class LoginFragment extends FragmentView {

    private FragmentSignInBinding signInBinding;
    private LoginViewModel loginViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signInBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);
        init();
        return signInBinding.getRoot();
    }

    private void init() {
        loginViewModel = new LoginViewModel(this, (AccountActivity) getActivity(), getContext());
        signInBinding.setViewModel(loginViewModel);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public String getName() {
        return LoginFragment.class.getName();
    }

    public FragmentSignInBinding getSignInBinding() {
        return signInBinding;
    }
}
