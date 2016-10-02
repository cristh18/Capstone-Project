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
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.HeaderMainView;

public class RegisterUserFragment extends FragmentView implements
        HeaderMainView.HeaderMainListener,
        View.OnClickListener {

    private FragmentRegisterUserBinding registerUserBinding;
    private RegisterUserListener registerUserListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_user, container, false);
        init();
        return registerUserBinding.getRoot();
    }

    private void init() {
        initListeners();
    }

    private void initListeners() {
        registerUserBinding.headerMainViewRegisterUser.setHeaderMainListener(this);
        registerUserBinding.buttonRegisterUser.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerUserListener = (RegisterUserListener) context;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_register_user:
                registerUserListener.onRegisterUser(registerUserBinding.editTextUserEmail.getText().toString(), registerUserBinding.editTextUserPassword.getText().toString());
                break;
        }
    }

    public interface RegisterUserListener {
        void onRegisterUser(String userEmail, String userPassword);
    }
}
