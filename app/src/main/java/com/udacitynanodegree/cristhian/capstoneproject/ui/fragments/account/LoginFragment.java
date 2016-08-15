package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.LoginListener;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.AccountActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.MainActivity;

public class LoginFragment extends FragmentView implements View.OnClickListener {

    private Button buttonSignIn;
    private Button buttonGoogleSignIn;
    private TextView textViewForgotPassword;
    private TextView textViewSignUp;
    private EditText editTextUserEmail;
    private EditText editTextUserPassword;
    private LoginListener loginListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        initViews(view);
        initListeners();
        return view;
    }

    private void initViews(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_icon_sign_in);
        buttonSignIn = (Button) view.findViewById(R.id.button_sign_in);
        buttonGoogleSignIn = (Button) view.findViewById(R.id.button_google_sign_in);
        textViewForgotPassword = (TextView) view.findViewById(R.id.textView_forgot_password);
        textViewSignUp = (TextView) view.findViewById(R.id.textView_sign_up);
        editTextUserEmail = (EditText) view.findViewById(R.id.editText_user_email);
        editTextUserPassword = (EditText) view.findViewById(R.id.editText_user_password);
    }

    private void initListeners() {
        buttonSignIn.setOnClickListener(this);
        buttonGoogleSignIn.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
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
                loginListener.onSignIn();
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
}
