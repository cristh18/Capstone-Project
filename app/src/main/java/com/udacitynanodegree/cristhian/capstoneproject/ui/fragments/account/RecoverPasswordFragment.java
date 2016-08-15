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
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.RecoverPasswordListener;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.HeaderMainView;

public class RecoverPasswordFragment extends FragmentView implements
        HeaderMainView.HeaderMainListener,
        View.OnClickListener {

    private RecoverPasswordListener recoverPasswordListener;
    private HeaderMainView headerMainView;
    private Button buttonRecoverPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recover_password, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        initViews(view);
        initListeners();
    }

    private void initViews(View view) {
        headerMainView = (HeaderMainView) view.findViewById(R.id.headerMainView_recover_password);
        buttonRecoverPassword = (Button) view.findViewById(R.id.button_recover_password);
    }

    private void initListeners() {
        headerMainView.setHeaderMainListener(this);
        buttonRecoverPassword.setOnClickListener(this);
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
        recoverPasswordListener.onBackRecoverPassword();
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
}
