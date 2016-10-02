package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentRecoverPasswordBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.HeaderMainView;

public class RecoverPasswordFragment extends FragmentView implements
        HeaderMainView.HeaderMainListener,
        View.OnClickListener {

    private FragmentRecoverPasswordBinding recoverPasswordBinding;
    private RecoverPasswordListener recoverPasswordListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recoverPasswordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recover_password, container, false);
        init();
        return recoverPasswordBinding.getRoot();
    }

    private void init() {
        initListeners();
    }


    private void initListeners() {
        recoverPasswordBinding.headerMainViewRecoverPassword.setHeaderMainListener(this);
        recoverPasswordBinding.buttonRecoverPassword.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        recoverPasswordListener = (RecoverPasswordListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getName() {
        return RecoverPasswordFragment.class.getName();
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
            case R.id.button_recover_password:
                recoverPasswordListener.onRecoverPassword();
                break;
        }
    }

    public interface RecoverPasswordListener {
        void onRecoverPassword();
    }
}
