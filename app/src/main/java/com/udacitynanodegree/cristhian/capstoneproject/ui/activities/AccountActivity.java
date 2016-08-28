package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.LoginListener;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.RecoverPasswordListener;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.RegisterUserListener;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.RegisterVehicleListener;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.LoginFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RecoverPasswordFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RegisterUserFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RegisterVehicleFragment;

public class AccountActivity extends BaseFragmentActivity implements
        LoginListener,
        RegisterUserListener,
        RegisterVehicleListener,
        RecoverPasswordListener {

    private FrameLayout frameLayout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        init();
        initViews();
        addFragment(new LoginFragment());
    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void initViews() {
        frameLayout = (FrameLayout) findViewById(R.id.container);
    }

    private void goToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void registerUser() {
        firebaseAuth.createUserWithEmailAndPassword("user email here", "user password here")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                        } else {
                            //display some message here
                        }

                    }
                });
    }

    @Override
    public void onSignIn() {
        goToMainActivity();
    }

    @Override
    public void onGoogleSignIn() {

    }

    @Override
    public void onForgotPassword() {
        replaceFragment(new RecoverPasswordFragment());
    }

    @Override
    public void onSignUp() {
        replaceFragment(new RegisterUserFragment());
    }

    @Override
    public void onBackRegisterUser() {
        replaceFragment(new LoginFragment());
    }

    @Override
    public void onContinueRegister() {
        replaceFragment(new RegisterVehicleFragment());
    }

    @Override
    public void onBackRegisterVehicle() {
        replaceFragment(new RegisterUserFragment());
    }

    @Override
    public void onFinish() {
        Toast.makeText(this, "USER REGISTERED", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackRecoverPassword() {
        replaceFragment(new LoginFragment());
    }

    @Override
    public void onRecoverPassword() {
        Toast.makeText(this, "PASSWORD RECOVERED", Toast.LENGTH_SHORT).show();
        replaceFragment(new LoginFragment());
    }
}
