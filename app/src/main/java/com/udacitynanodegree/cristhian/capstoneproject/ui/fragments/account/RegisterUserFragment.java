package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.RegisterUserListener;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.HeaderMainView;

public class RegisterUserFragment extends FragmentView implements
        HeaderMainView.HeaderMainListener,
        View.OnClickListener {

    private HeaderMainView headerMainView;
    private RegisterUserListener registerUserListener;
    private Button buttonContinue;
    private EditText editTextUserEmail;
    private EditText editTextUserPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_user, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        initViews(view);
        initListeners();
    }

    private void initViews(View view) {
        headerMainView = (HeaderMainView) view.findViewById(R.id.headerMainView_register_user);
        editTextUserEmail = (EditText) view.findViewById(R.id.editText_user_email);
        editTextUserPassword = (EditText) view.findViewById(R.id.editText_user_password);
        buttonContinue = (Button) view.findViewById(R.id.button_register_user);
    }

    private void initListeners() {
        headerMainView.setHeaderMainListener(this);
        buttonContinue.setOnClickListener(this);
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
        Toast.makeText(getContext(), "BACK", Toast.LENGTH_SHORT).show();
        registerUserListener.onBackRegisterUser();
    }

    @Override
    public void onClickRightHeader() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_register_user:
                registerUserListener.onRegisterUser(editTextUserEmail.getText().toString(), editTextUserPassword.getText().toString());
                break;
        }
    }
}
