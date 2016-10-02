package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.ActivityAccountBinding;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.LoginFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RecoverPasswordFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RegisterUserFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.ColoredSnackbar;

public class AccountActivity extends BaseFragmentActivity implements
        LoginFragment.LoginListener,
        RegisterUserFragment.RegisterUserListener,
        RecoverPasswordFragment.RecoverPasswordListener,
        ValueEventListener {

    private ActivityAccountBinding accountBinding;
    private final static String TAG = AccountActivity.class.getName();
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        accountBinding = DataBindingUtil.setContentView(this, R.layout.activity_account);
        init();
        initViews();
        validateSession();
    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void initViews() {
        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        myRef.setValue("Hello, World!");
        // Read from the database
        myRef.addValueEventListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void goToMainActivity() {
        setResult(RESULT_OK);
        finish();
    }

    private void registerUser(String userEmail, String userPassword) {
        if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {
            progressDialog.setMessage(getString(R.string.copy_registering_user));
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                showSnackBar(accountBinding.containerAccount, getString(R.string.copy_register_user_successful), Snackbar.LENGTH_SHORT, false);
                                goToMainActivity();
                            } else {
                                showSnackBar(accountBinding.containerAccount, getString(R.string.copy_user_registration_failed), Snackbar.LENGTH_SHORT, true);
                            }
                            progressDialog.dismiss();
                        }
                    });
        } else {
            showSnackBar(accountBinding.containerAccount, getString(R.string.copy_error_register_user), Snackbar.LENGTH_SHORT, true);
        }
    }

    @Override
    public void onSignIn(String userEmail, String userPassword) {
//        if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {
//            progressDialog.setMessage(getString(R.string.copy_logging_user));
//            progressDialog.show();
//            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        goToMainActivity();
//                    } else {
//                        showSnackBar(containerAccount, getString(R.string.copy_sign_in_failed), Snackbar.LENGTH_SHORT, true);
//                    }
//                    progressDialog.dismiss();
//                }
//            });
//        } else {
//            showSnackBar(containerAccount, getString(R.string.copy_error_register_user), Snackbar.LENGTH_SHORT, true);
//        }
        goToMainActivity();
    }

    @Override
    public void onGoogleSignIn() {

    }

    @Override
    public void onForgotPassword() {
        addFragment(new RecoverPasswordFragment());
    }

    @Override
    public void onSignUp() {
        addFragment(new RegisterUserFragment());
    }

    @Override
    public void onRegisterUser(String userEmail, String userPassword) {
        registerUser(userEmail, userPassword);
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

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.

//        String value = dataSnapshot.getValue(String.class);
//        Log.d(TAG, "Value is: " + value);
    }

    @Override
    public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
    }


    private void validateSession() {

        if (firebaseAuth.getCurrentUser() != null) {
            Log.e(TAG, "UID VALUE: " + firebaseAuth.getCurrentUser().getUid());
            goToMainActivity();
        } else {
            addFragment(new LoginFragment());
        }
    }
}
