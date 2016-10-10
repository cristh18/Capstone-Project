package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.AccountActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RegisterUserFragment;
import com.udacitynanodegree.cristhian.capstoneproject.utils.SnackBarUtil;

public class RegisterUserViewModel {

    private Context context;
    private AccountActivity accountActivity;
    private RegisterUserFragment registerUserFragment;

    public RegisterUserViewModel(RegisterUserFragment registerUserFragment, AccountActivity accountActivity, Context context) {
        this.context = context;
        this.accountActivity = accountActivity;
        this.registerUserFragment = registerUserFragment;
    }

    public void registerUser(View view) {
        String userEmail = registerUserFragment.getRegisterUserBinding().editTextUserEmail.getText().toString();
        String userPassword = registerUserFragment.getRegisterUserBinding().editTextUserPassword.getText().toString();
        if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {
            accountActivity.showProgressDialog(context.getString(R.string.copy_registering_user));
            IronHideApplication.getFirebaseAuth().createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(accountActivity, task -> {
                        if (task.isSuccessful()) {
                            SnackBarUtil.showSnackBar(registerUserFragment.getView(), context.getString(R.string.copy_register_user_successful), Snackbar.LENGTH_SHORT, false);
                            accountActivity.goToMainActivity();
                        } else {
                            SnackBarUtil.showSnackBar(registerUserFragment.getView(), context.getString(R.string.copy_user_registration_failed), Snackbar.LENGTH_SHORT, true);
                        }
                        accountActivity.dismissProgressDialog();
                    });
        } else {
            SnackBarUtil.showSnackBar(registerUserFragment.getView(), context.getString(R.string.copy_error_register_user), Snackbar.LENGTH_SHORT, true);
        }

    }

}
