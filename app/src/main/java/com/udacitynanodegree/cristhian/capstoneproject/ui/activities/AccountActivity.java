package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
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
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.ColoredSnackbar;

public class AccountActivity extends BaseFragmentActivity implements
        LoginListener,
        RegisterUserListener,
        RegisterVehicleListener,
        RecoverPasswordListener {

    private FrameLayout containerAccount;
    private FrameLayout frameLayout;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

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
        containerAccount = (FrameLayout) findViewById(R.id.container_account);
        frameLayout = (FrameLayout) findViewById(R.id.container);
        progressDialog = new ProgressDialog(this);
    }

    private void goToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void registerUser(String userEmail, String userPassword) {
        if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {
            progressDialog.setMessage("Registering Please Wait...");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                showSnackBar(containerAccount, getString(R.string.copy_register_user_successful), Snackbar.LENGTH_SHORT, false);
                                goToMainActivity();
                            } else {
                                showSnackBar(containerAccount, getString(R.string.copy_user_registration_failed), Snackbar.LENGTH_SHORT, true);
                            }
                            progressDialog.dismiss();
                        }
                    });
        } else {
            showSnackBar(containerAccount, getString(R.string.copy_error_register_user), Snackbar.LENGTH_SHORT, true);
        }
    }

    @Override
    public void onSignIn(String userEmail, String userPassword) {
        if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {
            progressDialog.setMessage("Registering Please Wait...");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        goToMainActivity();
                    } else {
                        showSnackBar(containerAccount, getString(R.string.copy_sign_in_failed), Snackbar.LENGTH_SHORT, true);
                    }
                    progressDialog.dismiss();
                }
            });
        } else {
            showSnackBar(containerAccount, getString(R.string.copy_error_register_user), Snackbar.LENGTH_SHORT, true);
        }
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
    public void onRegisterUser(String userEmail, String userPassword) {
        //        replaceFragment(new RegisterVehicleFragment());
        registerUser(userEmail, userPassword);
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

    private void showSnackBar(View view, String message, int duration, boolean isError) {
        TSnackbar snackbar = TSnackbar.make(view, message, duration);
        if (isError) {
            ColoredSnackbar.error(snackbar).show();
        } else {
            ColoredSnackbar.success(snackbar).show();
        }
    }
}
