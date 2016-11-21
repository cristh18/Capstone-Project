package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.account;

import android.support.design.widget.Snackbar;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.model.Person;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.AccountActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.LoginFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RecoverPasswordFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RegisterUserFragment;
import com.udacitynanodegree.cristhian.capstoneproject.utils.LogUtil;
import com.udacitynanodegree.cristhian.capstoneproject.utils.SnackBarUtil;

import java.util.ArrayList;

public class LoginViewModel implements ChildEventListener {

    private AccountActivity accountActivity;
    private LoginFragment loginFragment;
    private ArrayMap<String, Person> users;

    public LoginViewModel(LoginFragment loginFragment, AccountActivity accountActivity) {
        this.accountActivity = accountActivity;
        this.loginFragment = loginFragment;
        getRegisteredUsers();
    }

    public void signIn(View view) {
        String userEmail = loginFragment.getSignInBinding().editTextEmail.getEditText().getText().toString();
        String userPassword = loginFragment.getSignInBinding().editTextPassword.getEditText().getText().toString();
        if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {
            accountActivity.showProgressDialog(IronHideApplication.getApp().getApplicationContext().getString(R.string.copy_logging_user));
            IronHideApplication.getFirebaseAuth().signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(accountActivity, task -> {
                if (task.isSuccessful()) {
                    if (!existUser()) {
                        remoteSaveUser();
                        localSaveUser();
                    }
                    accountActivity.goToMainActivity();
                } else {
                    SnackBarUtil.showSnackBar(loginFragment.getView(), IronHideApplication.getApp().getApplicationContext().getString(R.string.copy_sign_in_failed), Snackbar.LENGTH_SHORT, true);
                }
                accountActivity.dismissProgressDialog();
            });
        } else {
            SnackBarUtil.showSnackBar(loginFragment.getView(), IronHideApplication.getApp().getApplicationContext().getString(R.string.copy_error_register_user), Snackbar.LENGTH_SHORT, true);
        }
    }

    public void googleSignIn(View view) {
        Toast.makeText(IronHideApplication.getApp().getApplicationContext(), "GOOGLE SIGN IN", Toast.LENGTH_SHORT).show();
        accountActivity.googleSignIn();
    }

    public void forgotPassword(View view) {
        accountActivity.showFragment(new RecoverPasswordFragment());
    }

    public void signUp(View view) {
        accountActivity.showFragment(new RegisterUserFragment());
    }

    private boolean existUser() {
        boolean exist = false;
        if (users != null) {
            String uId = IronHideApplication.getFirebaseAuth().getCurrentUser().getUid();
            if (users.get(uId) != null) {
                exist = true;
            }
        }
        return exist;
    }

    private void remoteSaveUser() {
        String uId = IronHideApplication.getFirebaseAuth().getCurrentUser().getUid();
        String name = IronHideApplication.getFirebaseAuth().getCurrentUser().getDisplayName();
        String email = IronHideApplication.getFirebaseAuth().getCurrentUser().getEmail();
        Person person = new Person(uId, email, "", name, new ArrayList<>());
        IronHideApplication.getFirebaseDatabase().getReference().child("users").child(String.valueOf(uId)).setValue(person);
    }

    private void localSaveUser() {

    }

    private void getRegisteredUsers() {
        /*if (mainActivity.getStockVehicles() == null) {
            mainActivity.setStockVehicles(new ArrayList<>());
        }
        mainActivity.showProgressDialog(mainActivity.getString(R.string.copy_logging_user));*/
        IronHideApplication.getFirebaseDatabase().getReference().child("").addChildEventListener(this);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        users = new ArrayMap<>();
        try {
            for (DataSnapshot child : dataSnapshot.getChildren()) {
                users.put(child.getKey(), child.getValue(Person.class));
            }
        } catch (ClassCastException e) {
            LogUtil.e(LoginViewModel.class.getName(), e.getMessage());
        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
