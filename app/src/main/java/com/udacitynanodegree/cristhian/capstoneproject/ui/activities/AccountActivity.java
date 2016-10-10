package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.LoginFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RecoverPasswordFragment;

public class AccountActivity extends BaseFragmentActivity implements
        RecoverPasswordFragment.RecoverPasswordListener,
        ValueEventListener {

    private final String USER = "user";
    private final static String TAG = AccountActivity.class.getName();
    private DatabaseReference myRef = IronHideApplication.getFirebaseDatabase().getReference(USER);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        DataBindingUtil.setContentView(this, R.layout.activity_account);
        init();
        initViews();
        validateSession();
    }

    private void init() {
    }

    private void initViews() {

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

    public void goToMainActivity() {
        setResult(RESULT_OK);
        finish();
    }

    public void showFragment(FragmentView fragmentView) {
        addFragment(fragmentView);
    }

    @Override
    public void onRecoverPassword() {
        Toast.makeText(this, "PASSWORD RECOVERED", Toast.LENGTH_SHORT).show();
        replaceFragment(new LoginFragment());
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
        FirebaseUser firebaseCurrentUser = IronHideApplication.getFirebaseAuth().getCurrentUser();
        if (firebaseCurrentUser != null) {
            Log.e(TAG, "UID VALUE: ".concat(firebaseCurrentUser.getUid()));
            goToMainActivity();
        } else {
            addFragment(new LoginFragment());
        }
    }

    @Override
    public void showProgressDialog(String message) {
        super.showProgressDialog(message);
    }

    @Override
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
    }
}
