package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentSignInBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;

public class LoginFragment extends FragmentView implements View.OnClickListener {

    private FragmentSignInBinding signInBinding;
    private LoginListener loginListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signInBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);
        init();
        return signInBinding.getRoot();
    }

    private void init() {
        initListeners();
    }

    private void initListeners() {
        signInBinding.buttonSignIn.setOnClickListener(this);
        signInBinding.buttonGoogleSignIn.setOnClickListener(this);
        signInBinding.textViewForgotPassword.setOnClickListener(this);
        signInBinding.textViewSignUp.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        loginListener = (LoginListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public String getName() {
        return LoginFragment.class.getName();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_in:
                Toast.makeText(getContext(), "SIGN IN", Toast.LENGTH_SHORT).show();
                loginListener.onSignIn(signInBinding.editTextEmail.getEditText().getText().toString(),
                        signInBinding.editTextPassword.getEditText().getText().toString());
                break;
            case R.id.textView_forgot_password:
                Toast.makeText(getContext(), "FORGOT PASSWORD", Toast.LENGTH_SHORT).show();
                loginListener.onForgotPassword();
                break;
            case R.id.button_google_sign_in:
                Toast.makeText(getContext(), "GOOGLE SIGN IN", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView_sign_up:
                Toast.makeText(getContext(), "SIGN UP", Toast.LENGTH_SHORT).show();
                loginListener.onSignUp();
                break;
        }
    }

    public interface LoginListener {
        void onSignIn(String userEmail, String userPassword);

        void onGoogleSignIn();

        void onForgotPassword();

        void onSignUp();
    }
}
